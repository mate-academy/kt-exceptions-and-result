package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val result = runCatching {
            val file = File(fileName)
            if (!file.exists() || !file.isFile) {
                throw IOException("File does not exist or is not a valid file $fileName")
            }
            file.readText().uppercase()
        }
        return result
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)

        return result.fold(
            onSuccess = { content -> "Uppercase content:\n${content.uppercase()}" },
            onFailure = { ex -> ("Error: Cannot read file - ${ex.message}") }
        )
    }
}
