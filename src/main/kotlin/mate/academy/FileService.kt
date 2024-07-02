package mate.academy

import java.io.File

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)

        return result.fold(
            onSuccess = { "Uppercase content:\n$it" },
            onFailure = { "Error: Cannot read file" }
        )
    }
}
