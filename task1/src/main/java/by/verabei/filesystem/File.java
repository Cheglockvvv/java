package by.verabei.filesystem;

import java.util.List;

public class File implements FileSystemComponent {

    private String name;

    public File (String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void display(int depth) {
        System.out.println("  ".repeat(depth) + name);
    }
}
