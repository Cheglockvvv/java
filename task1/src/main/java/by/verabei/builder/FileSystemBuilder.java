package by.verabei.builder;

import by.verabei.filesystem.File;
import by.verabei.filesystem.FileSystemComponent;
import by.verabei.filesystem.Folder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileSystemBuilder {
    public static FileSystemComponent buildFileSystem(String input, Folder root) {
        List<String> components = new ArrayList<>(List.of(input.split("/")));
        Folder currentFolder = root;

        for (String component : components) {
            if (!component.isEmpty()
                    && currentFolder.getContent().stream()
                    .anyMatch(component1 -> Objects.equals(component1.getName(), component))) {
                if (component.contains(".")) {
                    currentFolder.addComponent(new File(component));
                } else {
                    Folder folder = new Folder(component);
                    currentFolder.addComponent(folder);
                    currentFolder = folder;
                }
            }
        }

        return root;
    }
}
