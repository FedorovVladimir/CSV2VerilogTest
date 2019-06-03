import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CreateModule {

    @Test
    void createEmptyModule() throws FileNotFoundException {
        Module module = new Module("NAME");
        WorkWithFile.equals(new FileReader("src/test/resources/empty_module_NAME.sv"), module.getText());
    }
}
