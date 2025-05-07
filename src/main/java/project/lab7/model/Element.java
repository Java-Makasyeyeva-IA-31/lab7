package project.lab7.model;

import java.awt.Graphics;
import java.io.Serial;
import java.io.Serializable;
import javax.swing.ImageIcon;
import static project.lab7.Application.IMAGE;
import project.lab7.common.Direction;

public interface Element extends Cloneable, Serializable {
    
    @Serial
    static final long serialVersionUID = 1L;

    int BLOCK_SIZE = 50;

    void draw(Graphics g);
    
    Element copy();

    void setDirection(Direction direction);

    boolean isRotetable();

    ImageIcon getBaseImageIcon();
    
    ImageIcon getNextImageIcon();
    
    ImageIcon getCurrentImageIcon();

    static ImageIcon getImage(int x, int y, int w, int h) {
        return new ImageIcon(IMAGE.getSubimage(x, y, w, h));
    }
    
    int getX();
    
    int getY();
    
    void setX(int x);
    
    void setY(int y);
    
    int getWidth();
    
    int getHeight();
    
    int getXRange();
    
    int getYRange();
}
