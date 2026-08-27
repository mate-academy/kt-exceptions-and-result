package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    @Throws(IOException::class)
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        if (file.exists() && file.isFile) {
            return runCatching { file.readText().uppercase() }
        } else {
            return Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
    }


    fun processFileContent(fileName: String): String {
        try {
            return "Uppercase content:\n${readFile(fileName).getOrThrow()}"
        } catch (e: IOException) {
            return "Error: Cannot read file - ${e.message}"
        }
    }
}
