package mate.academy

import java.io.File

class FileService {
    fun readFile(fileName: String): Result<String> {
        return runCatching {
            File(fileName).readText().uppercase()
        }
    }

    fun processFileContent(fileName: String): String {
        val result = runCatching {
            File(fileName).readText().uppercase()
        }

        return if (result.isSuccess) {
            "Uppercase content:\n" + result.getOrDefault("")
        } else {
            "Error: Cannot read file - $fileName"
        }
    }
}
