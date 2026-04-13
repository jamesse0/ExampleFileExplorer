import fileExplorer.FileExplorer;

void main() {
    FileExplorer fileExplorer = new FileExplorer();
    fileExplorer.create("drive", "mainDrive","\\");
    fileExplorer.create("folder","photos","mainDrive");
    fileExplorer.create("text", "captions", "mainDrive\\photos");
    fileExplorer.writeToFile("mainDrive\\photos\\captions", "This is a caption!");
    fileExplorer.move("mainDrive\\photos\\captions","mainDrive");
    fileExplorer.delete("mainDrive\\photos");
}
