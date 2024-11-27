package DataPackets;

import java.awt.*;

public class WorldArea {
    private WorldCoordinate topLeft;
    private WorldCoordinate bottomRight;

    public WorldArea(WorldCoordinate _topLeft, WorldCoordinate _bottomRight){
        setTopLeft(_topLeft);
        setBottomRight(_bottomRight);
    }

    public WorldArea(WorldArea other) {
        this(other.getTopLeft(), other.getBottomRight());
    }

    public void setTopLeft(WorldCoordinate topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(WorldCoordinate bottomRight) {
        this.bottomRight = bottomRight;
    }

    public WorldCoordinate getTopLeft() {
        return topLeft;
    }

    public WorldCoordinate getBottomRight() {
        return bottomRight;
    }

    public void moveWorldPos(Point delta){
        getTopLeft().moveWorldPos(delta);
        getBottomRight().moveWorldPos(delta);
    }
}
