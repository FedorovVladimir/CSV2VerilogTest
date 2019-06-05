import model.ArrayToCVS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Array2CVS {

    @Test
    void createHeadAndOneRow() {
        String[] headers = {
                "a",
        };

        int[][] rows = {
                {0},
        };

        ArrayToCVS arrayToCVS = new ArrayToCVS();
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("a\n" +
                "0\n", arrayToCVS.getText());
    }

    @Test
    void createHeadAndTwoRow() {
        String[] headers = {
                "a",
                "b",
        };

        int[][] rows = {
                {0,1},
                {0,1},
        };

        ArrayToCVS arrayToCVS = new ArrayToCVS();
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("a, b\n" +
                "0, 1\n" +
                "0, 1\n",
                arrayToCVS.getText());
    }
}
