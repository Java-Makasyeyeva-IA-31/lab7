package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Grass extends AbstractElement {
    
    public Grass() {
        super(new DirectionImgs(getImage(1200, 150, 50, 50)));
    }
}
