package com.github

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginConvention

class CodeLinesCounterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.create("codeLines") { task ->
            task.doLast {
                printCodeLinesCount(project)
            }
        }.apply {
            group = "stat"
        }
    }

    private fun printCodeLinesCount(project: Project) {
        var totalCount = 0
        project.convention.getPlugin(JavaPluginConvention::class.java).sourceSets.forEach { sourceSet ->
            sourceSet.allSource.forEach { file ->
                totalCount += file.readLines().count { it.isNotBlank() }
            }
        }
        println("Total lines: $totalCount")
    }
}