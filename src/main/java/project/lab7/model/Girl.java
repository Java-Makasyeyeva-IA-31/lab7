package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Girl extends AbstractElement {
    
    public Girl() {
        super(new DirectionImgs(getImage(650, 150, 50, 50), getImage(700, 150, 50, 50), getImage(750, 150, 50, 50), getImage(700, 150, 50, 50)),
                new DirectionImgs(getImage(650, 0, 50, 50), getImage(700, 0, 50, 50), getImage(750, 0, 50, 50), getImage(700, 0, 50, 50)),
                new DirectionImgs(getImage(650, 50, 50, 50), getImage(700, 50, 50, 50), getImage(750, 50, 50, 50), getImage(700, 50, 50, 50)),
                new DirectionImgs(getImage(650, 100, 50, 50), getImage(700, 100, 50, 50), getImage(750, 100, 50, 50), getImage(700, 100, 50, 50)));
    }
}
