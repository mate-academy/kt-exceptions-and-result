package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return try {
            if (file.exists() && file.isFile) {
                val content = file.readText().uppercase()
                Result.success(content)
            } else {
                Result.failure(IOException("File not found or it's not a regular file"))
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    fun processFileContent(fileName: String): String {
        var resultMessage = ""
        val result = runCatching {
            readFile(fileName).getOrElse { throw it }
        }
        result.onSuccess { content ->
            resultMessage = if (content.isEmpty()) {
                "Uppercase content:\n"
            } else {
                val uppercaseContent = content.uppercase()
                println("File content (uppercase): $uppercaseContent")
                "Uppercase content:\n$uppercaseContent"
            }
        }.getOrElse { exception ->
            println("Cannot read file: ${exception.message}")
            resultMessage = "Error: Cannot read file"
        }
        return resultMessage
    }

}
