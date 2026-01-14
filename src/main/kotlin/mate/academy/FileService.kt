package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            val file = File(fileName)
            if (!(file.exists() && file.isFile)) {
                throw IOException("File does not exist or is not a valid file: $fileName")
            }
            file.readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String =
        runCatching { "Uppercase content:\n" + File(fileName).readText().uppercase() }
            .getOrElse { "Error: Cannot read file - $fileName" }

}
