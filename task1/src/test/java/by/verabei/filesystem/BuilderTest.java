package by.verabei.filesystem;

import by.verabei.builder.FileSystemBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {
    @Test
    public void testBuildFileSystem() {
        Folder root = new Folder("root");
        String input = "root/folder1/file.txt";
        FileSystemComponent madeRoot = FileSystemBuilder.buildFileSystem("root/folder1/folder2/file.txt", root);

        Folder rootFolder = (Folder) madeRoot;
        assertEquals(rootFolder.getContent().size(), 1);
        assertEquals(rootFolder.getName(), "root");

        FileSystemComponent f1 = rootFolder.getContent().get(0);
        assertEquals(f1.getName(), "folder1");

        Folder folder1 = (Folder) f1;
        assertEquals(folder1.getContent().size(), 1);

        FileSystemComponent f2 = folder1.getContent().get(0);
        assertEquals(f2.getName(), "folder2");

        Folder folder2 = (Folder) f2;
        assertEquals(folder2.getContent().size(), 1);

        FileSystemComponent txt = folder2.getContent().get(0);
        assertEquals(txt.getName(), "file.txt");
    }
}
