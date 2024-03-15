package by.verabei.filesystem;

public class File implements FileComponent {

    private String name;

    File (String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isDir() {
        return false;
    }
}
