package Model.Objects.Tiles;

import DataPackets.SpriteSet;
import DataPackets.WorldCoordinate;
import Model.Objects.Object;

import java.awt.*;

public class Tile extends Object {
    public Tile(SpriteSet sprites, Point _tilePos){
        super(sprites, new WorldCoordinate().setTilePos(_tilePos));
    }

    protected void updateTile() {

    }
}
