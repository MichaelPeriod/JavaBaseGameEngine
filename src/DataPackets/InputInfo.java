package DataPackets;

import java.awt.event.KeyEvent;

/*
* Used for sending information of an input from one place to another
* */

public class InputInfo {
    //Store general information about an input
    public boolean inputHeld;
    public long inputTime;

    //Keeps the specific event that happens
    public KeyEvent keyEvent;

    //Builds the basic requirements for an input
    public InputInfo(KeyEvent event,  boolean _inputHeld){
        inputHeld = _inputHeld;
        keyEvent = event;

        inputTime = System.currentTimeMillis();
    }
}
