package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return try {
            if (!file.exists() || !file.isFile) {
                Result.failure(IOException("File does not exist or is not a valid file: $fileName"))
            } else {
                val content = file.readText().uppercase()
                Result.success(content)
            }
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            "Uppercase content:\n${result.getOrNull()}"
        } else {
            val exceptionMessage = result.exceptionOrNull()?.message ?: "Unknown error"
            "Error: Cannot read file - $exceptionMessage ---> package mate.academy"
        }
    }
}
