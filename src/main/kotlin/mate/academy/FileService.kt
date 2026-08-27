package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)
            if (!file.exists() || file.isDirectory) {
                throw IOException("File does not exist or is not a valid file $fileName")
            }
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).map { content ->
            val uppercased = content.uppercase()
            File(fileName).writeText(uppercased)
            "Uppercase content:\n$uppercased"
        }.getOrElse { exception ->
            "Error: Cannot read file - ${exception.message}"
        }
    }
}
