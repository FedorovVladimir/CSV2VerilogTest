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
            for (int i : row) {
                str.append(i);
            }
            str.append("\n");
        }

        return str.toString();
    }
}
