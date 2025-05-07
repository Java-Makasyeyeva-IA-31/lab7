package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class RedHouse extends AbstractElement {
    
    public RedHouse() {
        super(new DirectionImgs(getImage(900, 0, 100, 150)));
    }
}
