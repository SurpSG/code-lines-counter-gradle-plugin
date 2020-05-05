package com.github

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import java.io.File

class CodeLinesCounterPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.create("codeLines") { task ->
            val codeLinesExtension = project.extensions.create("codeLinesStat", CodeLinesExtension::class.java)
            task.doLast {
                printCodeLinesCount(project, codeLinesExtension)
            }
        }.apply {
            group = "stat"
        }
    }

    private fun printCodeLinesCount(project: Project, codeLinesExtension: CodeLinesExtension) {
        val fileFilter = codeLinesExtension.buildFileFilter()
        var totalCount = 0
        project.convention.getPlugin(JavaPluginConvention::class.java).sourceSets.forEach { sourceSet ->
            sourceSet.allSource
                .filter(fileFilter)
                .forEach { file ->
                    val lines = file.readLines()
                    totalCount += if (codeLinesExtension.sourceFilters.skipBlankLines) {
                        lines.count(CharSequence::isNotBlank)
                    } else {
                        lines.count()
                    }
                }
        }
        println("Total lines: $totalCount")
    }

    private fun CodeLinesExtension.buildFileFilter(): (File) -> Boolean = if (fileExtensions.isEmpty()) {
        { true }
    } else {
        { fileExtensions.contains(it.extension) }
    }

    open class CodeLinesExtension(
        var sourceFilters: SourceFiltersExtension = SourceFiltersExtension(),
        var fileExtensions: MutableList<String> = mutableListOf()
    ) {
        fun sourceFilters(action: Action<in SourceFiltersExtension>) {
            action.execute(sourceFilters)
        }
    }

    open class SourceFiltersExtension(
        var skipBlankLines: Boolean = false
    )
}
