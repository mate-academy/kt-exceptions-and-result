package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return try {
            val file = File(fileName)
            require(file.exists() && file.isFile) {
                "File does not exist or is not a valid file $fileName"
            }
            Result.success(file.readText().uppercase())
        } catch (ex: IllegalArgumentException) {
            Result.failure(IOException("File does not exist or is not a valid file $fileName", ex))
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).fold(
            onSuccess = { content -> "Uppercase content:\n$content" },
            onFailure = { ex -> "Error: Cannot read file - ${ex.message}" }
        )
    }
}
