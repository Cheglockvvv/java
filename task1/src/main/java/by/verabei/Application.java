package by.verabei;

import by.verabei.builder.FileSystemBuilder;
import by.verabei.filesystem.Folder;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Folder rootFolder = new Folder("root");
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Enter path to add
                Or enter print to print out tree structure
                Or enter quit to stop the program""");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equals("quit")) {
                break;
            } else if (line.equals("print")) {
                rootFolder.display(0);
            } else {
                try {
                    FileSystemBuilder.buildFileSystem(line, rootFolder);
                } catch (IllegalArgumentException e) {
                    System.err.println("Input line is invalid, try again");
                }
            }
        }
    }
}
