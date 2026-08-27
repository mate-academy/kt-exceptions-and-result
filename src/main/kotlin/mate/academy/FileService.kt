package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        var file = File(fileName)
        if (!file.exists() || !file.isFile) {
            return Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
        return kotlin.runCatching {
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        var file = File(fileName)
        val result = kotlin.runCatching {
            file.readText().uppercase()
        }

        return result.getOrElse {
            "Error: Cannot read file - ${it.message}"
        }
    }
}
