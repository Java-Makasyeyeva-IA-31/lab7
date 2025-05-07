package project.lab7.common;

import java.io.Serial;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class DirectionImgs implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int IMAGE_DELAY = 300;
    private long startTime = System.currentTimeMillis();

    private int imgIdx = 0;
    private final ImageIcon[] imgIcons;

    public DirectionImgs(ImageIcon... imgIcons) {
        this.imgIcons = imgIcons;
    }

    public ImageIcon getBase() {
        return imgIcons[0];
    }

    public ImageIcon getNext() {
        long elapsed = System.currentTimeMillis() - startTime;
        int idx = (int) ((elapsed / IMAGE_DELAY) % imgIcons.length);

        return imgIcons[idx];
    }

    public ImageIcon getCurrent() {
        return imgIcons[imgIdx];
    }
}
