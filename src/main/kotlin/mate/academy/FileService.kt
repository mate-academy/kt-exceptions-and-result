package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        return if (file.exists() && file.isFile) {
            val fileLine = file.readText()
            Result.success(fileLine.uppercase())
        } else {
            Result.failure<String>(
                IOException(
                    "File does not exist or is not a valid file $fileName"
                )
            )
        }
    }

    fun processFileContent(fileName: String): String {
        val readFile = readFile(fileName)
        return if (readFile.isSuccess) {
            "Uppercase content:\n${readFile.getOrThrow().uppercase()}"
        } else {
            "Error: Cannot read file - <EXCEPTION_MESSAGE>."
        }
    }
}
