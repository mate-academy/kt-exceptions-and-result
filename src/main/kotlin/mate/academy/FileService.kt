package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return kotlin.runCatching { File(fileName).readText().uppercase() }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {
            "Uppercase content:\n${result.getOrThrow()}"
        } else {
            "Error: Cannot read file - ${result.exceptionOrNull()?.message}"
        }
    }
}
