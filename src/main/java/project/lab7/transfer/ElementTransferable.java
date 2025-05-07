package project.lab7.transfer;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import project.lab7.model.Element;

public class ElementTransferable implements Transferable {

    private final Element element;
    private final Point imageOffset;
    public static final DataFlavor ELEMENT_FLAVOR = new DataFlavor(ElementTransferable.class, "Element");

    public ElementTransferable(Element element, Point imageOffset) {
        this.element = element;
        this.imageOffset = imageOffset;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{ELEMENT_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return ELEMENT_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return this;
    }

    public Element getElement() {
        return element;
    }

    public Point getImageOffset() {
        return imageOffset;
    }
}
