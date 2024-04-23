package mate.academy

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException
import java.nio.file.Path
import kotlin.io.path.createTempDirectory
import kotlin.io.path.deleteIfExists
import kotlin.io.path.createTempFile
import kotlin.io.path.writeText

class FileServiceTest {
    private val fileService: FileService = FileService()
    private var tempFile: Path? = null

    @BeforeEach
    fun setUp() {
        tempFile = null
    }

    @Test
    fun `file exists with content`() {
        tempFile = createTempFile()
        tempFile!!.writeText("hello world")

        val result = fileService.readFile(tempFile!!.toString())

        Assertions.assertTrue(result.isSuccess)
        Assertions.assertEquals("HELLO WORLD", result.getOrNull())
    }

    @Test
    fun `file does not exist`() {
        val randomFileName = "non_existent_file.txt"

        val result = fileService.readFile(randomFileName)

        Assertions.assertTrue(result.isFailure)
        Assertions.assertTrue(result.exceptionOrNull() is IOException)
    }

    @Test
    fun `file is a directory`() {
        val tempDir = createTempDirectory()

        val result = fileService.readFile(tempDir.toAbsolutePath().toString())

        Assertions.assertTrue(result.isFailure)
        Assertions.assertTrue(result.exceptionOrNull() is IOException)
    }

    @Test
    fun `file with empty content`() {
        tempFile = createTempFile()

        val result = fileService.readFile(tempFile!!.toString())

        Assertions.assertTrue(result.isSuccess)
        Assertions.assertEquals("", result.getOrNull())
    }

    @Test
    fun `file with special characters`() {
        tempFile = createTempFile()
        tempFile!!.writeText("@#*&!")

        val result = fileService.readFile(tempFile!!.toString())

        Assertions.assertTrue(result.isSuccess)
        Assertions.assertEquals("@#*&!", result.getOrNull())
    }

    @Test
    fun `file with mixed-case content`() {
        tempFile = createTempFile()
        tempFile!!.writeText("Hello World")

        val result = fileService.readFile(tempFile!!.toString())

        Assertions.assertTrue(result.isSuccess)
        Assertions.assertEquals("HELLO WORLD", result.getOrNull())
    }

    @Test
    fun `process valid file`() {
        tempFile = createTempFile()
        tempFile!!.writeText("hello world")

        val result = fileService.processFileContent(tempFile!!.toString())

        Assertions.assertEquals("Uppercase content:\nHELLO WORLD", result)
    }

    @Test
    fun `process non-existent file`() {
        val randomFileName = "non_existent_file.txt"

        val result = fileService.processFileContent(randomFileName)

        Assertions.assertTrue(result.startsWith("Error: Cannot read file"))
    }

    @Test
    fun `process empty file`() {
        tempFile = createTempFile()

        val result = fileService.processFileContent(tempFile!!.toString())

        Assertions.assertEquals("Uppercase content:\n", result)
    }

    @AfterEach
    fun cleanUp() {
        tempFile?.deleteIfExists()
    }
}
