package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        if (file.exists() && file.isFile) {
            return Result.success(file.readText().uppercase())
        }
        return Result.failure(IOException("File does not exist or is not a valid file ${fileName}"))
    }

    fun processFileContent(fileName: String): String {
        val content = readFile(fileName)

        if (content.isSuccess) {
            return "Uppercase content:\n${content.getOrThrow().uppercase()}"
        } else {
            val exceptionOrNull = content.exceptionOrNull()
            return "Error: Cannot read file - ${exceptionOrNull?.message}"
        }
    }
}
