package View;

import DataPackets.InputInfo;
import DataPackets.RenderInfo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Queue;

/*
* Class for the View / Window
* Inherits most boilerplate from Client.java
* */
public class GameView extends Client {
    //Sprite sheet reader for all RenderInfos to be interpreted
    private final SpriteSheetReader spriteSheetReader;

    //Queues for transportation of render and input information
    //Processed at following tick
    private final Queue<RenderInfo> renderQueue = new LinkedList<>();
    private final Queue<InputInfo> inputQueue = new LinkedList<>();

    //Constructor
    public GameView(Point _tileMapSize) {
        super(_tileMapSize);

        //Make reader for sprite sheet
        //May in future make many to handle different sheets
        spriteSheetReader = new SpriteSheetReader(tileSize);
    }

    public GameView() {
        super(new Point(1280, 720));

        spriteSheetReader = new SpriteSheetReader(tileSize);
    }

    //Frame drawing
    @Override
    public void paint(Graphics g) {
        //Create 2d drawing plane
        Graphics2D g2d = (Graphics2D) g;

        //Get every render in queue and display in order
        //Placed in order such that all higher sprites render after
        while(!renderQueue.isEmpty()){
            RenderInfo r = renderQueue.poll();
            Point startPos = new Point(
                     r.getCoords().getCameraPos(this).x * pixelSize,
                     r.getCoords().getCameraPos(this).y * pixelSize);
            //Draw to screen
            //Passed reading to SpriteSheetReader.java
//            g2d.setColor(Color.green);
//            g2d.fillOval(startPos.x - 5, startPos.y - 5, 10, 10);

            draw(g2d, startPos, spriteSheetReader.retrievePixels(r));
        }
    }

    //Draw on screen
    private void draw(Graphics2D g, Point startPos, int[][] pixels){
        //Loop through all pixels
        for(int i = 0; i < tileSize; i++){
            for(int j = 0; j < tileSize; j++){
                //Ignore all pixels which are transparent
                int alpha = pixels[i][j] >> 24;
                if(alpha == 0) continue;

                //Draw all others
                Color color = new Color(pixels[i][j]);
                g.setColor(color);
                g.fillRect(startPos.x + i * pixelSize,
                           startPos.y + j * pixelSize,
                              pixelSize, pixelSize);
            }
        }
    }

    //Input render information
    public void addToRenderQueue(RenderInfo _render){
        renderQueue.add(_render);
    }

    //Export input information
    private void addToInputQueue(InputInfo input){
        inputQueue.add(input);
    }
    public Queue<InputInfo> getInputQueue() {
        return inputQueue;
    }

    //Handle all inputs and outputs
    @Override
    public void keyPressed(KeyEvent e) {
        addToInputQueue(new InputInfo(e, true));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        addToInputQueue(new InputInfo(e, false));
    }
}
