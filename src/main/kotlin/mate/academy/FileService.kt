package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    companion object {
        const val MSG = "Uppercase content:\n"
    }
    fun readFile(fileName: String): Result<String> {
        val result = runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }
        return result
    }

    fun processFileContent(fileName: String): String {
        try {
            val file = File(fileName)
            return MSG + file.readText().uppercase()
        }catch (e: IOException) {
            return "Error: Cannot read file - ${e.message}"
        }
    }
}
