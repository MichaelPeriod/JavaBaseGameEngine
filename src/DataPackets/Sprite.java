package DataPackets;

import java.awt.*;

public class Sprite {
    private Point spritePosition;
    //May later include sheet drawn on

    public Sprite(Point _spritePosition){
        setPosition(_spritePosition);
    }

    public Point getPosition() {
        return spritePosition;
    }

    public void setPosition(Point spritePosition) {
        this.spritePosition = spritePosition;
    }
}
