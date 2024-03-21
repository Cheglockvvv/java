package by.verabei.builder;

import by.verabei.filesystem.File;
import by.verabei.filesystem.FileSystemComponent;
import by.verabei.filesystem.Folder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSystemBuilder {
    public static FileSystemComponent buildFileSystem(String input, Folder root) throws IllegalArgumentException {
        String regex = "^" + root.getName() + "(?:/[^/.]+)+(?:\\.[^/]+)?$"; // "^root(?:/[^/.]+)+(?:\\.[^/.]+)?$"
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> components = new ArrayList<>(List.of(input.split("/")));
        Folder currentFolder = root;

        for (int i = 1; i < components.size(); i++) {
            String componentName = components.get(i);

             if (currentFolder.getContent().stream()
                    .noneMatch(component1 -> componentName.equals(component1.getName()))) {
                if (componentName.contains(".")) {
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
