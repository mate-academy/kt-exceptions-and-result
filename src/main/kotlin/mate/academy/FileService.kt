package mate.academy

import java.io.File
import java.io.IOException
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class FileService {
    fun readFile(fileName: String) = if (File(fileName).isFile) {
        success(File(fileName).readText().uppercase())
    } else {
        failure(IOException("File does not exist or is not a valid file <$fileName>"))
    }

    fun processFileContent(fileName: String) = if (readFile(fileName).isSuccess) {
        "Uppercase content:\n" + readFile(fileName).getOrThrow()
    } else {
        "Error: Cannot read file - <${readFile(fileName).exceptionOrNull()?.message}>"
    }
}
