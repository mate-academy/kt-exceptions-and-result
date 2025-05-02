package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return if (file.exists()) {
            if (file.isFile) {
                Result.success(file.readText().uppercase())
            } else {
                Result.failure(IOException("File does not exist or is not a valid file $fileName"))
            }
        } else {
            Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            "Uppercase content:\n" + result.getOrThrow().uppercase()
        } else {
            val exception = result.exceptionOrNull()
            "Error: Cannot read file - ${exception?.message}:"
        }
    }
}
