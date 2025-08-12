package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val result = runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }
        result.onFailure {
            return Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
        return result
    }

    fun processFileContent(fileName: String): String {
        return try {
            val result = readFile(fileName)
            "Uppercase content:\n${result.getOrThrow()}"
        } catch (e: IOException) {
            "Error: Cannot read file - ${e.message}"
        }
    }
}
