package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class MapleTree extends AbstractElement {
    
    public MapleTree() {
        super(new DirectionImgs(getImage(800, 100, 100, 100)));
    }
}
