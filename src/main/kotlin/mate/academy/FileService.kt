package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return try {
            val file = File(fileName)
            if (!file.isFile || !file.exists()) {
                throw IOException("File does not exist or is not a valid file $fileName")
            }
            Result.success(file.readText().uppercase())
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            "Uppercase content:\n" + File(fileName).readText().uppercase()
        } else {
            val exception = result.exceptionOrNull()
            "Error: Cannot read file - $exception"
        }
    }
}
