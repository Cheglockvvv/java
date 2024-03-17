package by.verabei.filesystem;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {

    private String name;
    private List<FileSystemComponent> content;

    public Folder(String name) {
        this.name = name;
        content = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        content.add(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display() {
        System.out.println(name + "/");
        content.forEach(FileSystemComponent::display);
    }

    public List<FileSystemComponent> getContent() {
        return content;
    }
}
