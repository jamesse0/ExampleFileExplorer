package fileExplorer;


import java.util.ArrayList;
import java.util.HashMap;

public class Drive {
    public HashMap<String, Entity> storage; //public just for the sake of unit tests
    private final String name;
    private String path;
    private Integer size;

    public Drive(String name, String parentPath) {
        this.name = name;
        path = parentPath + "\\" + name;
        size = 0;
        storage = new HashMap<>();
    }

    public int calcSize () {
        int currSize = 0;
        for (Entity file: storage.values()) {
            currSize+= file.getSize();
        }
        return currSize;
    }

    public void add (ArrayList<String> remainingPath, Entity entity, String name) {
        if (remainingPath.isEmpty()) {
            storage.putIfAbsent(name, entity);
            size = calcSize();
        }
        else {
            Entity pathEntity = storage.get(remainingPath.get(0));
            remainingPath.remove(0);
            pathEntity.add(remainingPath, entity, name);
            size = calcSize();
        }
    }

    public void delete (ArrayList<String> remainingPath) {
        if (remainingPath.size()==1) {
            storage.remove(remainingPath.get(0));
            size = calcSize();
        }
        else {
            Entity pathEntity = storage.get(remainingPath.get(0));
            remainingPath.remove(0);
            pathEntity.deleteEntity(remainingPath);
            size = calcSize();
        }
    }

    public Entity getEntity (ArrayList<String> remainingPath) {
        if (remainingPath.size()==1) {
            return storage.get(remainingPath.get(0));
        }
        else {
            Entity pathEntity = storage.get(remainingPath.get(0));
            remainingPath.remove(0);
            return pathEntity.getEntity(remainingPath);
        }
    }
}
