package mate.academy

import java.io.File
import java.io.IOException
import kotlin.jvm.Throws

class FileService {
    @Throws(IOException::class)
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)

        runCatching {
            if (!file.exists() || !file.isFile) {
                return Result.failure(IOException("File does not exist or is not a valid file: $fileName"))
            }
            file.readText()
        }
        return Result.success(file.readText().uppercase())
    }

    fun processFileContent(fileName: String): String {
        val readFile = readFile(fileName)

        return readFile.fold(
            onSuccess = { "Uppercase content:\n${it.uppercase()}" },
            onFailure = { "Error: Cannot read file - ${it.message}" }
        )
    }
}
