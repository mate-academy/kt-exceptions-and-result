package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        return if(File(fileName).isFile()) {
            Result.success(File(fileName).readText().uppercase())
        } else {
            Result.failure(IOException(
                "File does not exist or is not a valid file $fileName"))
        }
    }

    fun processFileContent(fileName: String): String {
        val result = runCatching {
            File(fileName).readText()
        }

        return if (result.isSuccess) {
            "Uppercase content:\n${result.getOrThrow().uppercase()}"
        } else {
            val exception = result.exceptionOrNull()
            "Error: Cannot read file - ${exception?.message}"
        }
    }
}
