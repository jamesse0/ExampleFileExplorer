import fileExplorer.FileExplorer;
import fileExplorer.TextFile;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class FileExplorerTests {
    private FileExplorer fileExplorer;
    @BeforeEach
    public void setup () {
        fileExplorer = new FileExplorer();
    }

    @Test
    public void validCreate () {
        fileExplorer.create("drive", "newDrive", "\\");
        assertTrue(fileExplorer.storage.containsKey("newDrive"));
    }

    @Test
    public void validMove () {
        fileExplorer.create("drive", "newDrive", "\\");
        fileExplorer.create("drive", "mainDrive","\\");
        fileExplorer.create("folder","newFolder","mainDrive");
        fileExplorer.move("mainDrive\\newFolder","newDrive");
        assertTrue(fileExplorer.storage.get("newDrive").storage.containsKey("newFolder"));
    }

    @Test
    public void validWrite () {
        fileExplorer.create("drive", "newDrive", "\\");
        fileExplorer.create("text","newText", "newDrive");
        fileExplorer.writeToFile("newDrive\\newText", "content");
        TextFile textFile = (TextFile) fileExplorer.storage.get("newDrive").storage.get("newText");
        assertEquals("content", textFile.content);
    }

    @Test
    public void validDelete () {
        fileExplorer.create("drive", "newDrive", "\\");
        fileExplorer.delete("newDrive");
        assertFalse(fileExplorer.storage.containsKey("newDrive"));
    }
}
