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
        Folder root = new Folder("root");
        FileSystemComponent madeRoot = FileSystemBuilder.buildFileSystem(input, root);

        try {
            System.setOut(new PrintStream(outputStream));
            madeRoot.display(0);

            String consoleOutput = outputStream.toString().trim();
            String expectedOutput = "root/\n  folder1/\n    folder2/\n      file.txt";

            assertEquals(consoleOutput, expectedOutput);
        } finally {
            System.setOut(originalOut);
        }
    }
}
