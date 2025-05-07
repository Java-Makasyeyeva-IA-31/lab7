package project.lab7;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import static java.util.Objects.nonNull;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import project.lab7.common.GridElementPoint;
import project.lab7.model.Element;
import static project.lab7.model.Element.BLOCK_SIZE;
import project.lab7.model.Grass;
import project.lab7.transfer.ElementTransferable;
    
public class CityPanel extends javax.swing.JPanel {

    private static final String FILE_NAME = "elements.txt";

    private boolean dragOver = false;
    private boolean constructorMode = false;

    private Map<GridElementPoint, Element> elements;
    private final GridElementPoint[][] gridElementPoints;
    private final Element[][] backgroundGridElements;

    public CityPanel(int width, int height) {
        initComponents();
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));

        elements = new HashMap<>();
        gridElementPoints = new GridElementPoint[getWidth() / BLOCK_SIZE][getHeight() / BLOCK_SIZE];
        backgroundGridElements = new Element[getWidth() / BLOCK_SIZE][getHeight() / BLOCK_SIZE];

        fillBackground();
        fillGridElementPoints();

        new Timer(50, (e) -> repaint()).start();

        new DropTarget(this, new DropTargetAdapter() {
            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                dragOver = true;

                try {
                    ElementTransferable transferable = (ElementTransferable) dtde.getTransferable().getTransferData(ElementTransferable.ELEMENT_FLAVOR);
                    Element elementCopy = transferable.getElement().copy();

                    Point dropPoint = dtde.getLocation();
                    dropPoint.translate(transferable.getImageOffset().x, transferable.getImageOffset().y);

                    dragAboveGrideElementPoints(elementCopy.getXRange(), elementCopy.getYRange(), dropPoint.x / BLOCK_SIZE, dropPoint.y / BLOCK_SIZE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                dragOver = false;
                
                try {
                    if (dtde.isDataFlavorSupported(ElementTransferable.ELEMENT_FLAVOR)) {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);

                        ElementTransferable transferable = (ElementTransferable) dtde.getTransferable().getTransferData(ElementTransferable.ELEMENT_FLAVOR);
                        Element elementCopy = transferable.getElement().copy();
                        Point dropPoint = dtde.getLocation();
                        dropPoint.translate(transferable.getImageOffset().x, transferable.getImageOffset().y);
                        updateGridElements(elementCopy, dropPoint.x / BLOCK_SIZE, dropPoint.y / BLOCK_SIZE);

                        dtde.dropComplete(true);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    dtde.rejectDrop();
                    e.printStackTrace();
                }
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                dragOver = false;
                dragAboveGrideElementPoints(0, 0, 0, 0);
            }
        });
    }

    private void dragAboveGrideElementPoints(int xRange, int yRange, int pointX, int pointY) {
        for (int y = 0; y < getHeight() / BLOCK_SIZE; y++) {
            for (int x = 0; x < getWidth() / BLOCK_SIZE; x++) {
                GridElementPoint gridElementPoint = gridElementPoints[y][x];

                if (dragOver && x >= pointX && x < pointX + xRange && y >= pointY && y < pointY + yRange) {
                    gridElementPoint.setDragOver(true);
                } else {
                    gridElementPoint.setDragOver(false);
                }
            }
        }
    }

    private void updateGridElements(Element element, int pointX, int pointY) {
        if (canPutElementInGrid(pointX, pointY, element.getXRange(), element.getYRange())) {
            putInGridElement(element, pointX, pointY, element.getXRange(), element.getYRange());
        }
    }

    private boolean canPutElementInGrid(int pointX, int pointY, int xRange, int yRange) {
        for (int y = pointY; y < pointY + yRange; y++) {
            for (int x = pointX; x < pointX + xRange; x++) {
                if (gridElementPoints[y][x].isElementExists()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void putInGridElement(Element element, int pointX, int pointY, int xRange, int yRange) {
        GridElementPoint gridElementPoint = new GridElementPoint(pointX, pointY);

        element.setX(pointX * BLOCK_SIZE);
        element.setY(pointY * BLOCK_SIZE);
        elements.put(gridElementPoint, element);

        putInGridElementPoints(pointX, pointY, xRange, yRange);
    }

    private void putInGridElementPoints(int pointX, int pointY, int xRange, int yRange) {
        for (int y = pointY; y < pointY + yRange; y++) {
            for (int x = pointX; x < pointX + xRange; x++) {
                GridElementPoint gridElementPoint = new GridElementPoint(pointX, pointY);
                gridElementPoint.setElementExists(true);
                gridElementPoints[y][x] = gridElementPoint;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        drawBackground(g);
        drawGridLines(g);
        drawElements(g);
        drawGridElementPoints(g);
        drawGridFrame(g);
    }

    private void fillBackground() {
        for (int y = 0; y < getHeight() / BLOCK_SIZE; y++) {
            for (int x = 0; x < getWidth() / BLOCK_SIZE; x++) {
                Grass grass = new Grass();
                grass.setX(x * BLOCK_SIZE);
                grass.setY(y * BLOCK_SIZE);

                backgroundGridElements[x][y] = grass;
            }
        }
    }

    private void drawBackground(Graphics g) {
        for (Element[] elements : backgroundGridElements) {
            for (Element element : elements) {
                drawImage(g, element);
            }
        }
    }

    private void fillGridElementPoints() {
        for (int y = 0; y < getHeight() / BLOCK_SIZE; y++) {
            for (int x = 0; x < getWidth() / BLOCK_SIZE; x++) {
                gridElementPoints[y][x] = new GridElementPoint(x, y);
            }
        }
    }

    private void drawGridElementPoints(Graphics g) {
        if (dragOver) {
            for (int y = 0; y < getHeight() / BLOCK_SIZE; y++) {
                for (int x = 0; x < getWidth() / BLOCK_SIZE; x++) {
                    GridElementPoint gridElementPoint = gridElementPoints[y][x];

                    if (gridElementPoint.isDragOver()) {
                        if (gridElementPoint.isElementExists()) {
                            g.setColor(Color.RED);
                        } else {
                            g.setColor(Color.GREEN);
                        }
                        g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    }
                }
            }
        }
    }
    
    private void drawGridFrame(Graphics g) {
        if (dragOver) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0, 120, 215, 128));
            g2.setStroke(new BasicStroke(4));
            g2.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
        }
    }

    private void drawGridLines(Graphics g) {
        if (constructorMode) {
            g.setColor(Color.LIGHT_GRAY);

            for (int x = 0; x <= getWidth(); x += BLOCK_SIZE) {
                g.drawLine(x, 0, x, getHeight());
            }

            for (int y = 0; y <= getHeight(); y += BLOCK_SIZE) {
                g.drawLine(0, y, getWidth(), y);
            }
        }
    }

    private void drawElements(Graphics g) {
        for (Element element : elements.values()) {
            drawImage(g, element);
        }
    }

    private void drawImage(Graphics g, Element element) {
        ImageIcon imageIcon;

        if (constructorMode) {
            imageIcon = element.getBaseImageIcon();
        } else {
            imageIcon = element.getNextImageIcon();
        }
        g.drawImage(imageIcon.getImage(), element.getX(), element.getY(), null);
    }

    public void setConstructorMode(boolean constructorMode) {
        this.constructorMode = constructorMode;
    }

    public void saveToFile() {
        String message;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(elements);
            message = "Map has been saved.";
        } catch (IOException ex) {
            message = "An error occurred.";
        }

        JOptionPane.showMessageDialog(this, message, "Saving...", JOptionPane.INFORMATION_MESSAGE);
    }

    public void downloadFromFile() {
        String message;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object object = in.readObject();

            if (nonNull(object)) {
                this.elements = (Map<GridElementPoint, Element>) object;
                updateGripElementPoints();
                message = "Map has been downloaded.";
            } else {
                message = "Map is empty.";
            }
        } catch (FileNotFoundException ex) {
            message = "Map is empty.";
        } catch (IOException | ClassNotFoundException ex) {
            message = "An error occurred.";
        }

        JOptionPane.showMessageDialog(this, message, "Downloading...", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateGripElementPoints() {
        for (Element element : elements.values()) {
            putInGridElementPoints(element.getX() / BLOCK_SIZE, element.getY() / BLOCK_SIZE, element.getXRange(), element.getYRange());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
