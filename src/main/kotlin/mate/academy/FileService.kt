package mate.academy

import java.io.File
import java.io.IOException

class FileService {

    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return if (file.exists() && file.isFile) {
            Result.success(file.readText().uppercase())
        } else {
            Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).fold(
            onSuccess = { content -> "Uppercase content:\n$content" },
            onValueFailure = { exception -> "Error: Cannot read file - ${exception.message}" }
        )
    }

    private inline fun <T> Result<T>.fold(
        onSuccess: (T) -> String,
        onValueFailure: (Throwable) -> String
    ): String {
        val exception = exceptionOrNull()
        return if (isSuccess) onSuccess(getOrThrow()) else onValueFailure(exception!!)
    }
}
