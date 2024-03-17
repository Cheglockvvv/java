package by.verabei.builder;

import by.verabei.filesystem.File;
import by.verabei.filesystem.Folder;

public class FileSystemBuilder {
    private Folder root;

    public FileSystemBuilder(String rootName) {
        this.root = new Folder(rootName);
    }

    public FileSystemBuilder addFile(String fileName) {
        File file = new File(fileName);
        return new FileSystemBuilder("root");
    }
}
