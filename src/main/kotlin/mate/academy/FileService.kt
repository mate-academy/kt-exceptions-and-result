package mate.academy

import java.io.File

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        if (!file.exists() || !file.isFile) {
            println("File does not exist or is not a valid file $fileName")
        }
        val result = runCatching {
            file.readText()
        }
        return result.map { it.uppercase() }
    }

    fun processFileContent(fileName: String): String {
        var message: String = ""
        val result = readFile(fileName)
        result.onSuccess {
            message = "Uppercase content:\n${it.uppercase()}"
        }
        result.onFailure {
            message = "Error: Cannot read file - ${it.message}"
        }
        return message
    }
}
