package mate.academy

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        val result = readFile(fileName)
        return if (result.isSuccess) {"Uppercase content:\n${result.getOrThrow()}"
        } else {
            return "Error: Cannot read file - ${result.exceptionOrNull()?.message}"
        }
    }
}
