package Model.Objects;

import DataPackets.RenderInfo;
import DataPackets.SpriteSet;
import DataPackets.WorldArea;
import DataPackets.WorldCoordinate;

import java.awt.*;

public class Object {
    protected boolean visible = true;
    protected SpriteSet sprites;
    protected WorldArea worldArea;
    private RenderInfo currentRenderPacket;
    protected boolean willRegenerateRenderPacket = true;
    public boolean needsRendered = true;

    public Object(SpriteSet _sprites, WorldCoordinate _worldCoordinate){
        setSprites(_sprites);
        setWorldCoordinate(_worldCoordinate);
    }

    public void setSprites(SpriteSet sprites) {
        this.sprites = sprites;
        this.sprites.initSprite();
    }

    public SpriteSet getSprites() {
        return sprites;
    }

    public void setWorldCoordinate(WorldCoordinate worldCoordinate) {
        WorldCoordinate br = new WorldCoordinate(worldCoordinate);
        br.moveWorldPos(new Point(WorldCoordinate.worldTileSize - 1, WorldCoordinate.worldTileSize - 1));

        worldArea = new WorldArea(worldCoordinate, br);
    }

    public void setWorldArea(WorldArea worldArea) {
        this.worldArea = new WorldArea(worldArea);
    }

    public WorldArea getWorldArea() {
        return worldArea;
    }

    public WorldCoordinate getWorldCoordinate() {
        return worldArea.getTopLeft();
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }

    public void toggleVisible() {
        this.visible = !this.visible;
    }

    public void setNeedsRendered() {
        this.needsRendered = true;
    }

    public RenderInfo generateRenderPacket(){
        if (!visible) return null;

        if (willRegenerateRenderPacket) {
            currentRenderPacket = new RenderInfo(getSprites(), worldArea.getTopLeft());
        }

        needsRendered = false;

        return currentRenderPacket;
    }
}
