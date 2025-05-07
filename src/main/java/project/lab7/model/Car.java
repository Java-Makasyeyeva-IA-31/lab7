package project.lab7.model;

import project.lab7.common.DirectionImgs;
import static project.lab7.model.Element.getImage;

public class Car extends AbstractElement {
    
    public Car() {
        super(new DirectionImgs(getImage(100, 0, 100, 100), getImage(100, 100, 100, 100)),
                new DirectionImgs(getImage(0, 0, 100, 100), getImage(0, 100, 100, 100)),
                new DirectionImgs(getImage(200, 0, 150, 100), getImage(200, 100, 150, 100)),
                new DirectionImgs(getImage(350, 0, 150, 100), getImage(350, 100, 150, 100)));
    }
}
