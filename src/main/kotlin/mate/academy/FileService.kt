package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)

            if (!file.isFile) {
                throw IOException(
                    "File does not exist or is not a valid file $fileName"
                )
            }

            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)

        return result.fold(
            onSuccess = { content ->
                "Uppercase content:\n$content"
            },
            onFailure = { exception ->
                "Error: Cannot read file - ${exception.message}"
            }
        )
    }
}
