import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WorkWithFileTest {

    @Test
    void EqualsFileTest() {
        try {
            FileReader reader = new FileReader("src/test/resources/test.txt");
            FileReader reader2 = new FileReader("src/test/resources/test.txt");
            assertTrue(WorkWithFile.equals(reader, reader2));
            reader.close();
            reader2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void NotEqualsFileTest() {
        try {
            FileReader reader = new FileReader("src/test/resources/test.txt");
            FileReader reader2 = new FileReader("src/test/resources/test2.txt");
            assertFalse(WorkWithFile.equals(reader, reader2));
            reader.close();
            reader2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void EqualsFileAndStringTest() {
        try {
            FileReader reader = new FileReader("src/test/resources/test.txt");
            assertNotNull(reader);
            assertTrue(WorkWithFile.equals(reader, "Hello, Verilog!"));
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void NotEqualsFileAndStringTest() {
        try {
            FileReader reader = new FileReader("src/test/resources/test.txt");
            assertNotNull(reader);
            assertFalse(WorkWithFile.equals(reader, "Hello, verilog!"));
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
