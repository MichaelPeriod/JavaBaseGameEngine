package DataPackets;

/*
* Used to send information of an object's position and visual
* */
public class RenderInfo {
    //The location of the sprite within the sprite sheet
    //(May need to expand to handle multiple sprite-sheets in the future)
        //Use enum of sheets written as SheetAlias(String location)
    private SpriteSet sprites;

    //In world specific position
    private WorldCoordinate coords;

    //Defines the information to transfer
    public RenderInfo(SpriteSet _sprites, WorldCoordinate _coords){
        setSprites(_sprites);
        setCoords(_coords);
    }

    //Getters and setters
    public Sprite getSprite() {
        return sprites.getSprite();
    }

    public void setSprites(SpriteSet sprites) {
        this.sprites = sprites;
    }

    public WorldCoordinate getCoords() {
        return coords;
    }
    public void setCoords(WorldCoordinate coords) {
        this.coords = coords;
    }
}
