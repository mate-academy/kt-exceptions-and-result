package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            File(fileName).readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        return try {
            "Uppercase content:" + System.lineSeparator() + File(fileName).readText().uppercase()
        } catch (e: IOException) {
            "Error: Cannot read file - ${e.message}"
        }
    }
}
