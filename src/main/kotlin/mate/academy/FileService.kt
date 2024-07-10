package mate.academy

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.Result
import kotlin.Result as Result1

class FileService {
    fun readFile(fileName: String): Result1<String> {
        val file = File(fileName)
        return if (file.exists() && file.isFile) {
            val readText = file.readText()
            Result.success(readText.uppercase())
        } else {
            Result.failure<String>(
                IOException(
                    "File does not exist or is not a valid file $fileName"
                )
            )
        }
    }

    fun processFileContent(fileName: String): String {
        val readFile = readFile(fileName)
        return if (readFile.isSuccess) {
            "Uppercase content:\n${readFile.getOrThrow().uppercase()}"
        } else {
            "Error: Cannot read file - <EXCEPTION_MESSAGE>."
        }
    }
}
