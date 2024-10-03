package mate.academy

import java.io.File
import java.io.IOException

const val FILE_NOT_FOUND_MESSAGE = "File does not exist or is not a valid file"
const val ERROR_CANNOT_READ_FILE = "Error: Cannot read file - "
const val ERROR_EMPTY_FILE = "Uppercase content:\n"

class FileService {
    fun readFile(fileName: String): Result<String> {
        return try {
            val file = File(fileName)
            if (!file.exists() || !file.isFile) {
                throw IOException("$FILE_NOT_FOUND_MESSAGE <$fileName>")
            }
            Result.success(file.readText().uppercase())
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            val content = result.getOrNull()
            if (content.isNullOrEmpty()) ERROR_EMPTY_FILE else "Uppercase content:\n$content"
        } else {
            "$ERROR_CANNOT_READ_FILE${result.exceptionOrNull()?.message}"
        }
    }
}
