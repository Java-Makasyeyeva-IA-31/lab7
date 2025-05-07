package project.lab7.common;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class GridElementPoint implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    private final int x;
    private final int y;
    private boolean dragOver;
    private boolean elementExists;

    public GridElementPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDragOver() {
        return dragOver;
    }

    public void setDragOver(boolean dragOver) {
        this.dragOver = dragOver;
    }

    public boolean isElementExists() {
        return elementExists;
    }

    public void setElementExists(boolean elementExists) {
        this.elementExists = elementExists;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GridElementPoint that = (GridElementPoint) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
