package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).fold(
            onSuccess = { content -> "Uppercase content:\n$content" },
            onFailure = { e -> "Error: Cannot read file - ${e.message}" }
        )
    }
}
