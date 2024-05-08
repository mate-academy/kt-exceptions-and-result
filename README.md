# File Service

Create a Kotlin program to read text from a file, convert it to uppercase, and output the result. An appropriate error message must be returned if an error occurs (for example, the file does not exist).

To solve the problem, use the `Result<T>` construct to handle successes and errors more safely and expressively.

## Examples of the program

- If the file exists and can be read, the program will return the contents of the file in uppercase.
- If the file does not exist or some other error occurs, the program will return a `Cannot read file` error message.

When implementing the task, it's essential to consider potential errors. By using `Result<T>`, you can ensure the program behaves in a stable and predictable manner, even in unforeseen circumstances.

## Implementation Details

1. Implement the `fun readFile(fileName: String): Result<String>` function to throw `IOException` with the `File does not exist or is not a valid file <FILE_NAME>` message in case the file does not exist, or it is a directory (not a file).
2. Implement the `fun processFileContent(fileName: String): String` function to return the uppercase content from the file or, in case of error, return the string message with the following content: `Error: Cannot read file - <EXCEPTION_MESSAGE>`.
