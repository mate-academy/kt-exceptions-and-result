package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        if (file.isFile) {
            return Result.success(file.readText().uppercase())
        }
        return Result.failure(IOException("File does not exist or is not a valid file $fileName"))
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return result.fold(
            onSuccess = {content -> "Uppercase content:\n${content}"},
            onFailure = {content -> "Error: Cannot read file - ${content.message}"}
        )
    }
}
