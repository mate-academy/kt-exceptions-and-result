package mate.academy

import java.io.File
import java.io.IOException

class FileService {
    fun readFile(fileName: String): Result<String> {
        val file = File(fileName)
        if (!file.exists() || !file.isFile) {
            return Result.failure(IOException("File does not exist or is not a valid file $fileName"))
        }

        return try {
            // Читаємо текст і одразу перетворюємо в UPPERCASE згідно з тестами readFile
            val content = file.readText().uppercase()
            Result.success(content)
        } catch (e: IOException) {
            Result.failure(e)
        }

    }

    fun processFileContent(fileName: String): String {
        return readFile(fileName).fold(
            onSuccess = { content ->
                // Додаємо префікс згідно з тестом "process valid file"
                "Uppercase content:\n$content"
            },
            onFailure = { exception ->
                // Повертаємо повідомлення про помилку згідно з технічним завданням
                "Error: Cannot read file - ${exception.message}"
            }
        )
    }
}
