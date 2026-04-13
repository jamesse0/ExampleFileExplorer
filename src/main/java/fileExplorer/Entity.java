package fileExplorer;
import java.util.ArrayList;

public interface Entity {

    int getSize();

    String getPath();

    void setPath(String path);

    String getName();

    void add(ArrayList<String> remainingPath, Entity entity, String name);

    void calcSize();

    Entity getEntity(ArrayList<String> remainingPath);

    void deleteEntity(ArrayList<String> remainingPath);


}
