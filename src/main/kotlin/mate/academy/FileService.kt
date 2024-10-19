package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return try {
            Result.success(File(fileName).readText().uppercase())
        } catch (e: IOException) {
            Result.failure(IOException("File does not exist or is not a valid file ${e.message}"))
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).fold(
            onSuccess = { "Uppercase content:\n${it.uppercase()}"},
            onFailure = { "Error: Cannot read file - ${it.message}" })
    }
}
