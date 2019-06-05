import model.ArrayToCVS;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Array2CVS {

    @Test
    void createHeadAndOneRow() {
        String[] marks = {
                "i",
        };

        String[] headers = {
                "a",
        };

        int[][] rows = {
                {0},
        };

        ArrayToCVS arrayToCVS = new ArrayToCVS();
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("i\n" +
                "a\n" +
                "0\n", arrayToCVS.getText());
    }

    @Disabled
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

    @Disabled
    @Test
    void createHeadAndManyRow() {
        String[] headers = {
                "a",
                "b",
                "cin",
                "s",
                "cout",
        };

        int[][] rows = {
                {0,0,0,0,0},
                {0,1,0,1,0},
        };

        ArrayToCVS arrayToCVS = new ArrayToCVS();
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("a, b, cin, s, cout\n" +
                        "0, 0, 0, 0, 0\n" +
                        "0, 1, 0, 1, 0\n",
                arrayToCVS.getText());
    }
}
