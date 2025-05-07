package project.lab7.model;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import project.lab7.common.Direction;
import project.lab7.common.DirectionImgs;

public abstract class AbstractElement implements Element {

    private int x;
    private int y;

    private Direction currentDirection = Direction.DOWN;
    private final Map<Direction, DirectionImgs> imgsByDirection = new HashMap<>();

    private AbstractElement() {
    }

    public AbstractElement(DirectionImgs downImgDimension) {
        imgsByDirection.put(Direction.DOWN, downImgDimension);
    }

    public AbstractElement(DirectionImgs upImgDimension, DirectionImgs downImgDimension,
            DirectionImgs leftImgDimension, DirectionImgs rightImgDimension) {
        imgsByDirection.put(Direction.UP, upImgDimension);
        imgsByDirection.put(Direction.DOWN, downImgDimension);
        imgsByDirection.put(Direction.LEFT, leftImgDimension);
        imgsByDirection.put(Direction.RIGHT, rightImgDimension);
    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public Element copy() {
        try {
            return (Element) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new AssertionError("Clone failed", ex);
        }
    }

    @Override
    public void setDirection(Direction direction) {
        if (isRotetable()) {
            currentDirection = direction;
        }
    }

    @Override
    public boolean isRotetable() {
        return imgsByDirection.size() > 1;
    }

    @Override
    public ImageIcon getBaseImageIcon() {
        return imgsByDirection.get(currentDirection).getBase();
    }

    @Override
    public ImageIcon getNextImageIcon() {
        return imgsByDirection.get(currentDirection).getNext();
    }

    @Override
    public ImageIcon getCurrentImageIcon() {
        return imgsByDirection.get(currentDirection).getCurrent();
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return getBaseImageIcon().getIconWidth();
    }

    @Override
    public int getHeight() {
        return getBaseImageIcon().getIconHeight();
    }

    @Override
    public int getXRange() {
        return getWidth() / BLOCK_SIZE;
    }

    @Override
    public int getYRange() {
        return getHeight() / BLOCK_SIZE;
    }
}
