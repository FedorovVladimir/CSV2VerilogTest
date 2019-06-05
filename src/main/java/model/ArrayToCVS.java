package model;

public class ArrayToCVS implements TextMaker {

    private String[] headers;
    private int[][] rows;

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public void setRows(int[][] rows) {
        this.rows = rows;
    }

    @Override
    public String getText() {
        StringBuilder str = new StringBuilder();
        str.append(String.join(", ", headers)).append("\n");
        for (int[] row : rows) {
            for (int j = 0; j < row.length; j++) {
                if (j > 0) {
                    str.append(", ");
                }
                str.append(row[j]);
            }
            str.append("\n");
        }

        return str.toString();
    }
}
