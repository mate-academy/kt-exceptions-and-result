package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)

        return if (file.exists() && file.isFile) {
            Result.success(file.readText(Charsets.UTF_8).uppercase())
        } else {
            Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }
    }

    fun processFileContent(fileName: String): String {
        val existence = readFile(fileName)

       existence.onSuccess {
            return "Uppercase content:\n$it"
        }

        return "Error: Cannot read file - ${existence.onFailure { it.message }}"
    }
}
