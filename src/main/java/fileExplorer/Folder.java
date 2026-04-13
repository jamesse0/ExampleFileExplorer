package fileExplorer;
import java.util.ArrayList;
import java.util.HashMap;

public class Folder implements Entity {
    protected final String name;
    protected String path;
    protected HashMap<String, Entity> storage;
    protected int size;
    public Folder(String name, String parentPath) {
        this.name = name;
        path = parentPath + "\\" + name;
        size=0;
        storage = new HashMap<>();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path=path + "\\" + name;
        for (Entity entity: storage.values()) {
            entity.setPath(this.path);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(ArrayList<String> remainingPath, Entity entity, String name) {
        if (remainingPath.isEmpty()) {
            storage.putIfAbsent(name, entity);
            calcSize();
        }
        else {
            Entity pathEntity = storage.get(remainingPath.get(0));
            remainingPath.remove(0);
            pathEntity.add(remainingPath, entity, name);
            calcSize();
        }
    }

    @Override
    public void calcSize () {
        size = 0;
        for (Entity file: storage.values()) {
            size+= file.getSize();
        }
    }

    @Override
    public Entity getEntity(ArrayList<String> remainingPath) {
        if (remainingPath.size()==1) {
            return storage.get(remainingPath.get(0));
        }
        else {
            Entity pathEntity = storage.get(remainingPath.get(0));
            remainingPath.remove(0);
            return pathEntity.getEntity(remainingPath);
        }
    }

    @Override
    public void deleteEntity(ArrayList<String> remainingPath) {
        if (remainingPath.size()==1) {
            storage.remove(remainingPath.get(0));
            calcSize();
        }
        else {
            Entity pathEntity = storage.get(remainingPath.get(0));
            remainingPath.remove(0);
            pathEntity.deleteEntity(remainingPath);
            calcSize();
        }
    }
}
