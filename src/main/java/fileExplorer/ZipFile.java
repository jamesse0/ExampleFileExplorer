package fileExplorer;


public class ZipFile extends Folder {
    public ZipFile(String name, String parentPath) {
        super(name, parentPath);
    }

    @Override
    public void calcSize () {
        size = 0;
        for (Entity file: storage.values()) {
            size+= file.getSize();
        }
        size = size/2;
    }
}
