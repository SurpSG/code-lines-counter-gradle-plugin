# code-lines-counter-gradle-plugin

Gradle plugin that counts lines of code. 

The Complete Gradle Plugin Tutorial
https://dzone.com/articles/the-complete-custom-gradle-plugin-building-tutoria

How to Test Gradle Plugins
https://dzone.com/articles/functional-tests-gradle-plugin

## Installation
1. Clone the repository
```
git clone https://github.com/SurpSG/code-lines-counter-gradle-plugin.git
```
2. Install the plugin to your maven local
```
gradlew install
``` 
3. Apply the plugin
```
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath 'com.github:code-lines-counter:0.0.1' // plugin’s artifact
    }
}

apply plugin: 'com.github.code-lines'

codeLinesStat {
    sourceFilters.skipBlankLines = true // optional, `false` by default
    fileExtensions = ['java', 'kt', ... ,'groovy'] // optional, empty by default 
}
```
## Run
```
gradlew codeLines
```
Output:
```
> Task :codeLines
Total lines: 44
```
