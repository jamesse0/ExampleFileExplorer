package fileExplorer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileExplorer {
    public HashMap<String, Drive> storage; //public just for the sake of unitTesting
    public FileExplorer() {
        storage = new HashMap<>();
    }

    public void create (String type, String name, String path) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(path.split("\\\\")));
        if (type.equals("drive")) {
            Drive drive = new Drive(name, path);
            storage.putIfAbsent(name, drive);
        }
        else if (type.equals("folder")) {
            Folder folder = new Folder(name, path);
            Drive pathDrive = storage.get(tokens.get(0));
            tokens.remove(0);
            pathDrive.add(tokens, folder, name);
        }
        else if (type.equals("zip")) {
            ZipFile zipFile = new ZipFile(name, path);
            Drive pathDrive = storage.get(tokens.get(0));
            tokens.remove(0);
            pathDrive.add(tokens, zipFile, name);
        }
        else if (type.equals("text")) {
            TextFile textfile = new TextFile(name, path);
            Drive pathDrive = storage.get(tokens.get(0));
            tokens.remove(0);
            pathDrive.add(tokens, textfile, name);
        }

    }

    public void delete (String path) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(path.split("\\\\")));
        if (tokens.size() == 1) {
            storage.remove(tokens.get(0));
        }
        else {
            Drive pathDrive = storage.get(tokens.get(0));
            tokens.remove(0);
            pathDrive.delete(tokens);
        }
    }

    public void move (String sourcePath, String destinationPath) {
        ArrayList<String> sourceTokens = new ArrayList<>(Arrays.asList(sourcePath.split("\\\\")));
        ArrayList<String> destinationTokens = new ArrayList<>(Arrays.asList(destinationPath.split("\\\\")));
        Drive destinationDrive = storage.get(destinationTokens.get(0));
        destinationTokens.remove(0);
        Drive pathDrive = storage.get(sourceTokens.get(0));
        sourceTokens.remove(0);
        Entity entity = pathDrive.getEntity(new ArrayList<>(sourceTokens));
        entity.setPath(destinationPath);
        pathDrive.delete(sourceTokens);
        destinationDrive.add(destinationTokens, entity, entity.getName());
    }

    public void writeToFile (String path, String newContent) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(path.split("\\\\")));
        Drive pathDrive = storage.get(tokens.get(0));
        tokens.remove(0);
        TextFile textFile = (TextFile) pathDrive.getEntity(tokens); //path validity would need to be checked
        textFile.setContent(newContent);
    }

}
