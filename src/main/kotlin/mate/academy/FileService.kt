package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return if(!file.exists() || !file.isFile) {
            Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        } else {
            Result.success(file.readText().uppercase())
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            "Uppercase content:\n${result.getOrNull()}"
        } else {
            "Error: Cannot read file - ${result.exceptionOrNull()?.message}"
        }
    }
}
