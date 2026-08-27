package mate.academy

import java.io.File
import java.io.FileNotFoundException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
         return when {
             !file.exists()
                     || !file.isFile
             -> Result.failure(FileNotFoundException("File does not exist or is not a valid file $fileName"))
             else -> runCatching{file.readText().uppercase()}
         }
    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName)
            .map{"Uppercase content:\n$it"}
            .getOrElse{throwable -> "Error: Cannot read file - ${throwable.message}"}
    }
}
