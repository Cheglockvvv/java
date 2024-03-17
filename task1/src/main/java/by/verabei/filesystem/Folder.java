package by.verabei.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {

    private String name;
    private List<FileSystemComponent> contents;

    public Folder(String name) {
        this.name = name;
        contents = new ArrayList<>();
    }

    public boolean addComponent(FileSystemComponent component) {
        contents.add(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display() {
        System.out.println(name + "/");
        contents.forEach(FileSystemComponent::display);
    }
}
