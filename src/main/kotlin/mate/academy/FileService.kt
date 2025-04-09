package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        try {
            val result = runCatching {
                val file = File(fileName)
                file.readText().uppercase()
            }
            return result
        } catch (e : Exception) {
            throw IOException("")
        }
    }

    fun processFileContent(fileName: String): String {
        return try {
            val content = readFile(fileName).getOrThrow()
            "Uppercase content:\n${content.uppercase()}"
        } catch (e : Exception) {
            "Error: Cannot read file - ${e.message}"
        }
    }
}
