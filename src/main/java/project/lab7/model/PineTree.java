package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class PineTree extends AbstractElement {
    
    public PineTree() {
        super(new DirectionImgs(getImage(800, 0, 100, 100)));
    }
}
