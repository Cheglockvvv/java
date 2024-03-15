package by.verabei.filesystem;

public class Folder implements FileComponent{

    private String name;
    private


    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isDir() {
        return true;
    }
}
