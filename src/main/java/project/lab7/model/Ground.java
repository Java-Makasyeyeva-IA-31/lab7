package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Ground extends AbstractElement {
    
    public Ground() {
        super(new DirectionImgs(getImage(900, 150, 50, 50)));
    }
}
