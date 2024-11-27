package DataPackets;

import View.GameView;

import java.awt.*;

public class WorldCoordinate {
    public static final int worldTileSize = 16;
    private Point worldPos;

    public WorldCoordinate(Point _worldPos){
        setWorldPos(_worldPos);
    }

    public WorldCoordinate(WorldCoordinate other) {
        setWorldPos(other.worldPos);
    }
    public WorldCoordinate(){
        //Do nothing, needs to be coupled with a setTilePos
    }

    public void moveWorldPos(Point delta){
        setWorldPos(new Point(getWorldPos().x + delta.x, getWorldPos().y + delta.y));
    }

    public void moveTilePos(Point delta){
        setTilePos(new Point(getTilePos().x + delta.x, getTilePos().y + delta.y));
    }

    public WorldCoordinate setWorldPos(Point worldPos) {
        this.worldPos = worldPos;
        return this;
    }

    public Point getWorldPos() {
        return worldPos;
    }

    public WorldCoordinate setTilePos(Point tilePos) {
        setWorldPos(new Point(tilePos.x * worldTileSize, tilePos.y * worldTileSize));
        return this;
    }

    public Point getTilePos(){
        return new Point(Math.floorDiv(getWorldPos().x, worldTileSize), Math.floorDiv(getWorldPos().y, worldTileSize));
    }

    public Point getCameraPos(GameView view){
        return getWorldPos();
    }
}
