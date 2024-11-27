package Model;

import DataPackets.*;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public Model() {

    }

    public void update() {
        //Update the game state once every tick
    }

    public void updateSprites(int updateNumber) {

    }

    //Retrieve all tile updates
    //Called by controller
    public List<RenderInfo> getAllUpdates(){
        List<RenderInfo> allUpdates = new ArrayList<>();

        return allUpdates;
    }
}