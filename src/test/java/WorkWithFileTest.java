import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class WorkWithFileTest {

    @Test
    void openEqualsFileTest() {
        FileReader reader = null;
        FileReader reader2 = null;
        try {
            reader = new FileReader("src/test/resources/test.txt");
            reader2 = new FileReader("src/test/resources/test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(reader);
        assertNotNull(reader2);
        assertTrue(WorkWithFile.fileEquals(reader, reader2));
    }

    @Test
    void openNotEqualsFileTest() {
        FileReader reader = null;
        FileReader reader2 = null;
        try {
            reader = new FileReader("src/test/resources/test.txt");
            reader2 = new FileReader("src/test/resources/test2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(reader);
        assertNotNull(reader2);
        assertFalse(WorkWithFile.fileEquals(reader, reader2));
    }


}
