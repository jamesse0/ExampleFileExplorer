package fileExplorer;

import java.util.ArrayList;

public class TextFile implements Entity {
    public String content; //public just for unit tests
    private final String name;
    private String path;
    private Integer size;
    public TextFile(String name, String parentPath) {
        this.name = name;
        path = parentPath + "\\" + name;
        size=0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path=path + "\\" + name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setContent (String newContent) {
        content = newContent;
    }

    @Override
    public void add(ArrayList<String> remainingPath, Entity entity, String name) {
        //does not do anything since text files don't contain anything,
        // optional if/catch statement to notify user that command is invalid
    }

    @Override
    public void calcSize() {
        size = content.length();
    }

    @Override
    public Entity getEntity(ArrayList<String> remainingPath) {
        return null;
    }

    @Override
    public void deleteEntity(ArrayList<String> remainingPath) {
    }
}
