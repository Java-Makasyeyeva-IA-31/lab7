package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Shop extends AbstractElement {
    
    public Shop() {
        super(new DirectionImgs(getImage(1100, 0, 150, 150)));
    }
}
