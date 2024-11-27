package Model.Objects.Tiles;

import DataPackets.Sprite;
import DataPackets.SpriteSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileFactory {
    public enum Tiles {
        Ground(new Point[]{new Point(0, 3), new Point(1, 3)}, new Double[]{0.05, 0.95}),
        Wall(new Point(1, 2));

        private final SpriteSet spriteSet;

        Tiles(Point point){
            spriteSet = new SpriteSet(new Sprite(point));
        }

        Tiles(Point[] points){
            spriteSet = new SpriteSet();
            for(Point p : points){
                spriteSet.addSprite(new Sprite(p));
            }
        }

        Tiles(Point[] points, Double[] _weights){
            if(points.length != _weights.length){
                spriteSet = new SpriteSet(new Sprite(points[0]));
                System.out.println("Weights != points");
                return;
            }

            List<Sprite> sprites = new ArrayList<>();
            for(Point p : points){
                sprites.add(new Sprite(p));
            }

            List<Double> weights = Arrays.stream(_weights).toList();

            spriteSet = new SpriteSet(sprites, weights);
        }

        public SpriteSet getSpriteSet() {
            return spriteSet;
        }
    }
    public static Tile generate(Tiles type, Point pos){
        return new Tile(new SpriteSet(type.getSpriteSet()), pos);
    }
}
