import java.io.FileReader;
import java.io.IOException;

class WorkWithFile {

    static Boolean fileEquals(FileReader reader, FileReader reader2) {
        int c = 0;
        int c2 = 0;
        while(true){
            try {
                if (!((c = reader.read()) != -1 && (c2 = reader2.read()) != -1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (c != c2) {
                return false;
            }
        }
        return true;
    }
}
