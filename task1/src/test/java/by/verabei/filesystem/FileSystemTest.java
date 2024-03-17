package by.verabei.filesystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileSystemTest {
    @Test
    public void testFolderCreation() {
        Folder folder = new Folder("folder1");
        assertNotNull(folder);
        assertEquals(folder.getName(), "folder1");
    }

    @Test
    public void testFileCreation() {
        File file = new File("file.txt");
        assertNotNull(file);
        assertEquals(file.getName(), "file.txt");
    }

    @Test
    public void testAddFileToFolder() {
        Folder folder = new Folder("folder1");
        File file = new File("file.txt");
        folder.addComponent(file);
        assertEquals(folder.getContent().size(), 1);
        assertInstanceOf(File.class, folder.getContent().get(0));
        assertEquals(folder.getContent().get(0).getName(), "file.txt");
    }

    @Test
    public void testAddFolderToFolder() {
        Folder folder1 = new Folder("folder1");
        Folder folder2 = new Folder("folder2");
        folder1.addComponent(folder2);
        assertEquals(folder1.getContent().size(), 1);
        assertInstanceOf(Folder.class, folder1.getContent().get(0));
        assertEquals(folder1.getContent().get(0).getName(), "folder2");
    }
}
