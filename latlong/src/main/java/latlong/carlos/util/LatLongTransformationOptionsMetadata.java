package latlong.carlos.util;

import org.springframework.xd.module.options.spi.ModuleOption;

public class LatLongTransformationOptionsMetadata {
    private int xCellSize = 500;
    private int yCellSize = 500;

    public int getxCellSize() {
        return xCellSize;
    }

    @ModuleOption("The cell size for x axis")
    public void setxCellSize(int xCellSize) {
        this.xCellSize = xCellSize;
    }

    public int getyCellSize() {
        return yCellSize;
    }

    @ModuleOption("The cell size for y axis")
    public void setyCellSize(int yCellSize) {
        this.yCellSize = yCellSize;
    }
}
