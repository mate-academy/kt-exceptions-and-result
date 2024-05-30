package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return if (file.isFile && file.exists()) {
            Result.success(file.readText().uppercase())
        } else {
            Result.failure(IOException("File does not exist or is not a valid file: $fileName"))
        }
    }

    fun processFileContent(fileName: String): String {
        val readFile = this.readFile(fileName)
        return if (readFile.isSuccess) {
            "Uppercase content:\n${readFile.getOrNull()}"
        } else
            "Error: Cannot read file - ${readFile.exceptionOrNull()}"

    }
}
