import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CreateModule {

    @Test
    void createEmptyModule() throws FileNotFoundException {
        String begin = "module name();\n";
        String end = "endmodule\n";
        String module = begin + end;
        WorkWithFile.equals(new FileReader("src/test/resources/empty_module.sv"), module);
    }
}
