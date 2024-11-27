import DataPackets.*;
import Model.Model;
import View.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Controller extends Thread {
    //Get final variables
    private final int tickRate = 60;
//    private final Point tileMapSize = new Point(15, 9);

    //Store model and view
    private final Thread tickThread;
    private final GameView view;
    private final Model model;

    //Constructor
    public Controller(JFrame window){
        //Add a window to console
//        view = new GameView(tileMapSize);
        view = new GameView();
        window.add(view);

        //Display window
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //Build Model
        model = new Model();

        //Start as new thread
        tickThread = new Thread(this);
        tickThread.start(); //Calls run on new thread
    }

    @Override
    public void run() {
        //Function that runs every tick
        gameTick();
    }

    private void gameTick() { //Runs every tick
        //Declare variables
        final double tickInterval = 1000000000/(float)tickRate;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer;
        int ticksRan = 0;

        //Loop for entire duration of game
        while(true){
            //Get time
            currentTime = System.nanoTime();

            //Figure out if time is past new frame time
            timer = currentTime - lastTime;
            delta += timer / tickInterval;
            lastTime = currentTime;
            if(delta <= 1) continue;

            //Run game functions
            handleInputs();
            model.update();
            updateScreen(ticksRan);

            //Draw frame and restart time till next frame
            delta--;
            ticksRan++;
        }
    }

    private List<Character> buttonsDown = new ArrayList<>();
    private void handleInputs(){
        Queue<InputInfo> inputQueue = view.getInputQueue();
        while(!inputQueue.isEmpty()){
            InputInfo input = inputQueue.poll();
            if(input.inputHeld) {
                if(!buttonsDown.contains(input.keyEvent.getKeyChar()))
                    buttonsDown.add(input.keyEvent.getKeyChar());
            } else {
                if (buttonsDown.contains(input.keyEvent.getKeyChar()))
                    buttonsDown.remove(Character.valueOf(input.keyEvent.getKeyChar()));
            }
        }

        for(Character c : buttonsDown){
            switch (c) {
//                case 'value' -> functionCall();
            }
        }
    }

    private void updateScreen(int totalTicks){
        //Prompt sprites to see if update is wanted
        model.updateSprites(totalTicks);

        //Transfer all render information to the view
        for(RenderInfo renderInfo : model.getAllUpdates()){
            view.addToRenderQueue(renderInfo);
        }

        //Draw the updates
        view.repaint();
    }
}
