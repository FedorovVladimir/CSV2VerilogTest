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
        arrayToCVS.setMarks(marks);
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("i\n" +
                "a\n" +
                "0\n", arrayToCVS.getText());
    }

    @Test
    void createHeadAndTwoRow() {
        String[] marks = {
                "i",
                "i",
        };

        String[] headers = {
                "a",
                "b",
        };

        int[][] rows = {
                {0,1},
                {0,1},
        };

        ArrayToCVS arrayToCVS = new ArrayToCVS();
        arrayToCVS.setMarks(marks);
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("i, i\n" +
                "a, b\n" +
                "0, 1\n" +
                "0, 1\n",
                arrayToCVS.getText());
    }

    @Test
    void createHeadAndManyRow() {
        String[] marks = {
                "i",
                "i",
                "i",
                "o",
                "o",
        };

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
        arrayToCVS.setMarks(marks);
        arrayToCVS.setHeaders(headers);
        arrayToCVS.setRows(rows);

        assertEquals("i, i, i, o, o\n" +
                        "a, b, cin, s, cout\n" +
                        "0, 0, 0, 0, 0\n" +
                        "0, 1, 0, 1, 0\n",
                arrayToCVS.getText());
    }
}
