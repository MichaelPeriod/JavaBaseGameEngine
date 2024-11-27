package Model.Objects.Tiles;

import DataPackets.WorldArea;
import DataPackets.WorldCoordinate;

import java.awt.*;

public class Chunk {
    private static final int chunckSize = 4;
    private Tile[][] tiles = new Tile[chunckSize][chunckSize];
    private WorldArea chunkArea;

    public Chunk(WorldArea _chunkArea){
        chunkArea = _chunkArea;
    }

    public void setTile(Tile t){
        Point offset = getChunkOffset(t.getWorldCoordinate());
        tiles[offset.x][offset.y] = t;
    }

    public void setTiles(Tile[][] _tiles){
        if(tiles.length != _tiles.length || tiles[0].length != _tiles[0].length) return;
        tiles = _tiles;
    }

    public static Point getChunkNumber(WorldCoordinate coord) {
        return new Point(Math.floorDiv(coord.getTilePos().x, chunckSize),
                Math.floorDiv(coord.getTilePos().y, chunckSize));
    }

    public static Point getChunkOffset(WorldCoordinate coord) {
        return new Point(coord.getTilePos().x % chunckSize,
                coord.getTilePos().y % chunckSize);
    }
}
