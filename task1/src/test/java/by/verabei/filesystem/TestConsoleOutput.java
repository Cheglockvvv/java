package by.verabei.filesystem;

import by.verabei.builder.FileSystemBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestConsoleOutput {
    @Test
    public void testConsoleOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        String input = "root/folder1/folder2/file.txt";
        FileSystemComponent root = FileSystemBuilder.buildFileSystem(input);

        try {
            System.setOut(new PrintStream(outputStream));
            root.display();

            String consoleOutput = outputStream.toString().trim();
            String expectedOutput = "root/\n\tfolder1/\n\tfolder2/\n\tfile.txt";

            assertEquals(consoleOutput, expectedOutput);
        } finally {
            System.setOut(originalOut);
        }
    }
}
