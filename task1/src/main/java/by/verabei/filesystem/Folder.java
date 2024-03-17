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
    public void display(int depth) {
        String tabs = "  ".repeat(depth);
        System.out.println(tabs + name + "/");
        content.forEach(component -> component.display(depth + 1));
    }

    public List<FileSystemComponent> getContent() {
        return content;
    }
}
