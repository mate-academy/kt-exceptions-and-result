# File Service

Create a Kotlin program that will read text from a file, convert it to uppercase, and output the result. If an error occurs (for example, the file does not exist), an appropriate error message must be returned.

To solve the problem, use the `Result<T>` construct to handle successes and errors in a safer and more expressive way.

**Examples of the program:**
- If the file exists and can be read, the program will return the contents of the file in uppercase.
- If the file does not exist or some other error occurs, the program will return a "Cannot read file" error message.

The task should be implemented with potential errors in mind and using `Result<T>` to ensure more stable and predictable behavior of the program in different situations.

## Implementation details
- implement `fun readFile(fileName: String): Result<String>` function to throw IOException with the `"File does not exist or is not a valid file <FILE_NAME>` message in case file does not exist or it is a directory (not a file)
- implement `fun processFileContent(fileName: String): String` function to return the uppercase content from the file or, in case of error, return the string message with the following content: `"Error: Cannot read file - <EXCEPTION_MESSAGE>`
