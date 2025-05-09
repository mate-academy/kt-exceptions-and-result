package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        if (!file.exists() || !file.isFile) {
            return Result.failure(IOException("File does not exist or is not a valid file <$fileName>"))
        }
        return Result.success(file.readText().uppercase())
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            "Uppercase content:\n" + result.getOrThrow()
        } else { "Error: Cannot read file - <${result.exceptionOrNull()?.message}>" }
    }
}
