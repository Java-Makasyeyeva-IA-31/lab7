package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class DilledRoad extends AbstractElement {
    
    public DilledRoad() {
        super(new DirectionImgs(getImage(950, 150, 50, 50)));
    }
}
