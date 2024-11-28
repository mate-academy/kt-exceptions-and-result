package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    @Throws(IOException::class)
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            File(fileName).readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        return try {
            "Uppercase content:\n${File(fileName).readText().uppercase()}"
        } catch (exception: IOException) {
            "Error: Cannot read file - ${exception.message}"
        }
    }
}
