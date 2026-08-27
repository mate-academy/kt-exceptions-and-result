package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val result = runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }.onFailure {
            return Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
        return result
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)

        return if (result.isSuccess) return "Uppercase content:\n${result.getOrDefault("")}"
        else "Error: Cannot read file - ${result.javaClass}"
    }
}
