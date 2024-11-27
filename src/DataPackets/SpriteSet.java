package DataPackets;

import java.util.ArrayList;
import java.util.List;

public class SpriteSet {
    /*
     * What can cause a sprite to be updated:
     * Time / Game ticks -
     * State machine actions
     * Adjacent tiles X
     * Randomly chosen at start -
     * */

    private int currentSprite;
    private List<Sprite> sprites;
    private List<Double> spriteWeights;
    private long tickLastChanged;
    private long spriteupdateFrequency = -1;
    private boolean needsSelected = false;

    public SpriteSet(SpriteSet other) {
        this();
        setSprites(other.sprites);
        setSpriteupdateFrequency(other.spriteupdateFrequency);
        needsSelected = other.needsSelected;
        setSpriteWeights(other.spriteWeights);
    }

    public SpriteSet(){
        sprites = new ArrayList<>();
        spriteWeights = new ArrayList<>();
        updateSpriteTimer(0);
    }

    public SpriteSet(Sprite sprite) { //Singular basic sprite
        this();
        addSprite(sprite);
    }

    public SpriteSet(List<Sprite> _sprites, long _frequency) { //Periodically updating sprites
        this();
        setSprites(_sprites);
        setSpriteupdateFrequency(_frequency);
    }

    public SpriteSet(List<Sprite> _sprites, List<Double> _weights) { //Randomized sprites
        this();
        setSprites(_sprites);
        setSpriteWeights(_weights);
        needsSelected = true;
    }

    public void initSprite(){
        if (needsSelected)
            selectRandomSprite();
    }

    public Sprite getSprite() {
        return sprites.get(currentSprite);
    }

    public void addSprite(Sprite _sprite) {
        sprites.add(_sprite);
    }

    public void updateSprites(long currentTick){
        if(spriteupdateFrequency != -1 && tickLastChanged + spriteupdateFrequency <= currentTick){
            updateSpriteTimer(currentTick);
            updateSprite();
        }
    }

    private void updateSprite(){ //Handles sprites that update on frequency
        if(sprites.isEmpty()) return;
        currentSprite = (currentSprite + 1) % sprites.size();
    }

    public void selectRandomSprite(){
        if(sprites.isEmpty() || spriteWeights.isEmpty() || sprites.size() != spriteWeights.size()) return;

        double totalWeights = 0;
        for(Double weight : spriteWeights)
            totalWeights += weight;

        double rand = Math.random();
        rand *= totalWeights;

        for(int i = 0; i < sprites.size(); i++){
            rand -= spriteWeights.get(i);

            if(rand <= 0){
                currentSprite = i;
                return;
            }
        }
        currentSprite = spriteWeights.size() - 1;
    }

    private void updateSpriteTimer(long currentTick){
        tickLastChanged = currentTick;
    }

    public void setSpriteupdateFrequency(long spriteupdateFrequency) {
        this.spriteupdateFrequency = spriteupdateFrequency;
    }

    public void setSprites(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    public void setSpriteWeights(List<Double> spriteWeights) {
        this.spriteWeights.addAll(spriteWeights);
    }
}
