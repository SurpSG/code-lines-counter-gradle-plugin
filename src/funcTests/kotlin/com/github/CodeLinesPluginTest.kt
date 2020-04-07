package com.github

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome.SUCCESS
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File


class CodeLinesPluginTest {

    @get:Rule
    var testProjectDir = TemporaryFolder()

    private lateinit var buildFile: File
    private lateinit var gradleRunner: GradleRunner

    @Before
    fun setup() {
        buildFile = testProjectDir.newFile("build.gradle")

        buildFile.appendText("""
            plugins {
                id 'java'
                id 'com.github.code-lines'
            }
            
        """.trimIndent())

        gradleRunner = GradleRunner.create()
            .withPluginClasspath()
            .withProjectDir(testProjectDir.root)
            .withTestKitDir(testProjectDir.newFolder())
            .apply {
                withArguments("test").build()
            }
    }

    @Test
    fun `diff-coverage should create diffCoverage dir and full coverage with html report`() {
        val result = gradleRunner
            .withArguments("codeLines")
            .build()

        println(result.output)

        assertEquals(SUCCESS, result.task(":codeLines")!!.outcome)
        assertTrue(result.output.contains("Total lines: 0"))
    }
}
