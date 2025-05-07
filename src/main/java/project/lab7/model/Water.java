package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Water extends AbstractElement {
    
    public Water() {
        super(new DirectionImgs(getImage(1000, 150, 50, 50), getImage(1050, 150, 50, 50)));
    }
}
