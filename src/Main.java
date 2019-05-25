import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    public Main() {
        super();
        setLocationRelativeTo(null);
        setSize(500, 600);
        setTitle("Flappy Blob");
        Affichage a = new Affichage();
        setContentPane(a);
        //setBackground(Color.BLACK);
        /*addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                requestFocus();
            }
        });*/
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        new Main();
    }
}
