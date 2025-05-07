package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Boy extends AbstractElement {
    
    public Boy() {
        super(new DirectionImgs(getImage(500, 150, 50, 50), getImage(550, 150, 50, 50), getImage(600, 150, 50, 50), getImage(550, 150, 50, 50)),
                new DirectionImgs(getImage(500, 0, 50, 50), getImage(550, 0, 50, 50), getImage(600, 0, 50, 50), getImage(550, 0, 50, 50)),
                new DirectionImgs(getImage(500, 50, 50, 50), getImage(550, 50, 50, 50), getImage(600, 50, 50, 50), getImage(550, 50, 50, 50)),
                new DirectionImgs(getImage(500, 100, 50, 50), getImage(550, 100, 50, 50), getImage(600, 100, 50, 50), getImage(550, 100, 50, 50)));
    }
}
