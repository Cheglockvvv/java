package by.verabei.filesystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirstTest {
    @Test
    public void testLineProcessing() {
        String line = "root/folder1/file.txt";
        FileSystemComponent root = processor.processLine(line);
        assertEquals(root.getName(), "root");
        assertEquals(root.getContent().size(), 1);
        assertNull(root.getContent().getFirst().getContent());
        assertEquals(root.getContent().getFirst().getName(), "file.txt");
    }
}
