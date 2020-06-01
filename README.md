# code-lines-counter-gradle-plugin
![CI](https://github.com/SurpSG/code-lines-counter-gradle-plugin/workflows/CI/badge.svg)

Gradle plugin that counts lines of code. 

The Complete Gradle Plugin Tutorial
https://dzone.com/articles/the-complete-custom-gradle-plugin-building-tutoria

How to Test Gradle Plugins
https://dzone.com/articles/functional-tests-gradle-plugin

## Installation
```
buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath 'com.github.SurpSG:code-lines-counter-gradle-plugin:0.0.1'
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
