package mate.academy

import java.io.File

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        val result = runCatching {
            val file = File(fileName)
            "Uppercase content:\n" + file.readText().uppercase()
        }

        return result.getOrElse {
            "Error: Cannot read file - ${it.message}"
        }
    }
}
