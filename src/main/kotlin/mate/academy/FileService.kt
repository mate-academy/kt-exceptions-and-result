package mate.academy

import java.io.File
import java.io.IOException

class FileReadException(message: String) : Exception(message)

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)

        if (!file.exists() || !file.isFile) {
            return Result.failure(
                IOException(
                    "File does not exist " +
                            "or is not a valid file $fileName"
                )
            )
        }
        return runCatching {
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName)
            .map { "Uppercase content:\n${it.uppercase()}" }
            .getOrElse { "Error: Cannot read file - ${it.message}" }
    }
}
