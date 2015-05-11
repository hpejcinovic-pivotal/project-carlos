package latlong.carlos.util;

public class Cell {

    int cellHeight;
    int cellWidth;

    public Cell(int cellHeight, int cellWidth) {
        super();
        this.cellHeight = cellHeight;
        this.cellWidth = cellWidth;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    @Override
    public String toString() {
        return cellWidth + "-" + cellHeight;
    }
}
