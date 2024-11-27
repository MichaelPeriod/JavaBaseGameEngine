package Model.Objects.Entities;

import DataPackets.SpriteSet;
import DataPackets.WorldArea;
import DataPackets.WorldCoordinate;
import Model.Objects.Object;

import java.awt.*;

public abstract class Entity extends Object {
    protected WorldArea trailingWorldArea;

    public Entity(SpriteSet sprites, Point _worldCoord) {
        super(sprites, new WorldCoordinate(_worldCoord));
        setTrailingWorldArea(new WorldArea(getWorldArea()));
    }

    public void move(Point dir){
        Point delta = new Point(dir);

        //Assumes is scaled by subclass or needs no scaling

        setTrailingWorldArea(new WorldArea(getWorldArea()));
        getWorldArea().moveWorldPos(delta);
        setNeedsRendered();
    }

    public WorldArea getTrailingWorldArea() {
        return trailingWorldArea;
    }

    private void setTrailingWorldArea(WorldArea trailingWorldArea) {
        this.trailingWorldArea = trailingWorldArea;
    }
}
