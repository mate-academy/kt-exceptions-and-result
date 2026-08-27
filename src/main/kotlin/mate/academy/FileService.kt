package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {

        val file = File(fileName)
        return when {
            // first example
            false -> {
                if (file.exists() && file.isFile) {
                    val fileContent: String = file.readText().uppercase()
                    Result.success(fileContent)
                } else {
                    Result.failure(IOException("File does not exist or is not a valid file $fileName"))
                }
            }
            // second example
            else -> runCatching {
                if (!file.exists() || !file.isFile) {
                    throw IOException("File does not exist or is not a valid file $fileName")
                }
                file.readText().uppercase()
            }

        }
    }

    fun processFileContent(fileName: String): String {
        return runCatching {
            "Uppercase content:\n" + readFile(fileName).getOrElse { throw it }
        }.getOrElse {
            "Error: Cannot read file"
        }
    }
}
