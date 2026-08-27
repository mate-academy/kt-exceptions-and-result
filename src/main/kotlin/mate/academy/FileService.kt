package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return try {
            val file = File(fileName)
            if (!file.exists()) {
                Result.failure(IOException("File does not exist"))
            } else {
                val content = file.readText()
                Result.success(content.uppercase())
            }
        } catch (e: IOException) {
            Result.failure(IOException("Cannot read file", e))
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)

        return when {
            result.isSuccess -> {
                val content = result.getOrNull() ?: ""
                "Uppercase content:\n$content"
            }
            result.isFailure -> {
                val errorMessage = result.exceptionOrNull()?.message ?: "Unknown error"
                "Error: Cannot read file - $errorMessage"
            }
            else -> "Unknown error"
        }
    }
}
