package Model;

public enum Layer {
    //Layer names and number
    Ground(0),
    GroundDetails(1),
    Midground(2),
    Foreground(3);

    //Stores a number to keep track of layer depth
    public final int tilemapNum;

    Layer(int _tilemapNum) {
        tilemapNum = _tilemapNum;
    }

    public static Layer getLayer(int l) {
        //Used to get the layer by tilemap number
        for (Layer possibleLayer : Layer.values()) {
            if (possibleLayer.tilemapNum == l)
                return possibleLayer;
        }
        return Ground; //If no layer found assume walls layer intended
    }

    public Layer getPreviousLayer() {
        //Get the layer before current unless at lowest layer
        if (tilemapNum == 0)
            return this;
        return getLayer(tilemapNum - 1);
    }
}