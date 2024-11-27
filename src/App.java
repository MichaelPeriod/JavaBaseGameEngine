import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        //Create window and set properties
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("ADD TITLE IN APP");
        window.setBackground(Color.BLACK);

        //Start application from controller
        new Controller(window);
    }
}
