package tdhd;

import tdhd.file_system.WindowsFileSystem;
import tdhd.project.FileSystem;

public class FileSystemFactory {
    public static FileSystem getFileSystem() {
        return new WindowsFileSystem();
    }
}
