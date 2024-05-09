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
        try {
            val file = File(fileName)
            return "Uppercase content:" + System.lineSeparator() + file.readText().uppercase()
        } catch (e: IOException) {
            return "Error: Cannot read file - ${e.message}"
        }
    }
}
