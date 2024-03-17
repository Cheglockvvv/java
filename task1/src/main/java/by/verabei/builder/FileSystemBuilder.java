package by.verabei.builder;

import by.verabei.filesystem.File;
import by.verabei.filesystem.FileSystemComponent;
import by.verabei.filesystem.Folder;

import java.util.*;

public class FileSystemBuilder {
    public static FileSystemComponent buildFileSystem(String input, Folder root) {
        List<String> components = new ArrayList<>(List.of(input.split("/")));
        Folder changingFolder = new Folder(root);
        Folder currentFolder = root;

        for (int i = 1; i < components.size(); i++) {
            String componentName = components.get(i);
            if (componentName.isEmpty()) {
                continue;
            }

             if (currentFolder.getContent().stream()
                    .noneMatch(component1 -> componentName.equals(component1.getName()))) {
                if (componentName.contains(".")) {
                    currentFolder.addComponent(new File(componentName));
                    if (i != components.size() - 1) {
                        throw Ill
                    }
                } else {
                    Folder folder = new Folder(componentName);
                    currentFolder.addComponent(folder);
                    currentFolder = folder;
                }
            } else {
                if (!componentName.contains(".")) {
                    Optional<FileSystemComponent> similarFolderOpt = currentFolder.getContent().stream()
                            .filter(component1 -> component1.getName().equals(componentName))
                            .findFirst();
                    if (similarFolderOpt.isPresent()) {
                        currentFolder = (Folder) similarFolderOpt.get();
                    }
                }
            }
        }

        return root;
    }
}
