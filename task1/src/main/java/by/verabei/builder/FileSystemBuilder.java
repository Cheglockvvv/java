package by.verabei.builder;

import by.verabei.filesystem.File;
import by.verabei.filesystem.FileSystemComponent;
import by.verabei.filesystem.Folder;

import java.util.*;

public class FileSystemBuilder {
    public static FileSystemComponent buildFileSystem(String input, Folder root) throws IllegalArgumentException {
        List<String> components = new ArrayList<>(List.of(input.split("/")));
        Folder saveRootFolder = new Folder(root);
        Folder currentFolder = root;

        if (!components.get(0).equals("root")) {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i < components.size(); i++) {
            String componentName = components.get(i);
            if (componentName.isEmpty()) {
                continue;
            }

             if (currentFolder.getContent().stream()
                    .noneMatch(component1 -> componentName.equals(component1.getName()))) {
                if (componentName.contains(".")) {
                    if (i != components.size() - 1) {
                        root = saveRootFolder;
                        throw new IllegalArgumentException();
                    }
                    currentFolder.addComponent(new File(componentName));
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
