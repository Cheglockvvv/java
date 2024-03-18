package by.verabei;

import by.verabei.builder.FileSystemBuilder;
import by.verabei.filesystem.Folder;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Folder rootFolder = new Folder("root");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to add. \nYour root folder name is " +
                rootFolder.getName() +
                "\nOr enter print to print out tree structure" +
                "\nOr enter quit to stop the program");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            switch (line) {
                case "quit":
                    System.exit(0);
                    break;
                case "print":
                    rootFolder.display(0);
                    break;
                default:
                    try {
                        FileSystemBuilder.buildFileSystem(line, rootFolder);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Input line is invalid, try again");
                }
            }
        }
    }
}
