package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Gunflower extends AbstractElement {

    public Gunflower() {
        super(new DirectionImgs(getImage(1100, 150, 50, 50), getImage(1150, 150, 50, 50)));
    }
}
