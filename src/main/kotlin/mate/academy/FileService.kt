package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return try {
            val file = File(fileName)
            if (!file.exists() || !file.isFile) {
                throw IOException("File does not exist or is not a valid file: $fileName")
            }
            Result.success(file.readText().uppercase())
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).fold(
            onSuccess = { content -> "Uppercase content:\n$content" },
            onFailure = { "Error: Cannot read file - ${it.message}" }
        )
    }
}
