package zeroOne

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default


import java.io.File

import kotlin.system.exitProcess

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("file-counter")


    class CountFiles : Subcommand("count", "Counts files in a directory") {
        val directory by argument(ArgType.String, description = "The directory to count files in")
        val recursive by option(ArgType.Boolean, shortName = "r", description = "Count files recursively").default(false)

        override fun execute() {
            val dir = File(directory)

            if (!dir.exists() || !dir.isDirectory) {
                println("Error: '$directory' is not a valid directory.")
                exitProcess(1)
            }

            val fileCount = countFiles(dir, recursive)
            println("Number of files: $fileCount")
        }

        private fun countFiles(dir: File, recursive: Boolean): Int {
            var count = 0
            dir.listFiles()?.forEach { file ->
                if (file.isFile) {
                    count++
                } else if (recursive && file.isDirectory) {
                    count += countFiles(file, recursive)
                }
            }
            return count
        }
    }

    parser.subcommands(CountFiles())
    parser.parse(args)
}

