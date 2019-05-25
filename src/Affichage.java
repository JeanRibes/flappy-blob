import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Affichage extends JPanel implements KeyListener, MouseMotionListener {
    Point blob = new Point(100, 200);
    public static Color FOND = new Color(81, 245, 255);
    List<Rectangle> obstacles = new ArrayList();
    public boolean mort = true;

    public Affichage() {
        super();
        add(new JLabel("Jeu"));
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setBackground(FOND);
        setVisible(true);
        creerObstacle(200);
        creerObstacle(100);
        new Timer(32, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blob.y += 3;
                try {
                    for (Rectangle obstacle : obstacles) {
                        if (toucheObstacle(blob, obstacle))
                            setBackground(Color.RED); //marche pas
                        else
                            setBackground(FOND);
                        if (obstacle.x > 0)
                            obstacle.x -= 2;
                        else
                            obstacles.remove(obstacle);
                    }
                } catch (ConcurrentModificationException e1) {
                }
                repaint();
            }
        }).start();
        new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerObstacle(getWidth());
            }
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("up");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int t = 0; t < 5; t++) {
                        blob.y -= 5 * (5 - t);
                        System.out.println("thread");
                        repaint();
                        try {
                            Thread.sleep(16);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        System.out.println(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //System.out.println("repaint");
        g.setColor(Color.orange);
        g.fillOval(blob.x, blob.y, 15, 15);
        g.setColor(Color.GREEN);
        for (Rectangle obstacle : obstacles) {
            g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
            if (toucheObstacle(blob, obstacle)) {
                g.setColor(Color.RED);
                g.fillOval(getWidth()-30, 20, 10, 10);
                setBackground(Color.RED); //marche pas
            } else
                setBackground(FOND);
        }
    }

    public static boolean toucheObstacle(Point blob, Rectangle obstacle) {

        return (blob.y > obstacle.y && blob.y < obstacle.getMaxY()) && //position y
                (blob.x > obstacle.x && blob.x < obstacle.getMaxX()); //x
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void creerObstacle(int offset) {
        int hauteur = (int) (400 * Math.random());
        obstacles.add(new Rectangle(
                offset,
                getHeight() - hauteur,
                20,
                hauteur
        ));//bas

        obstacles.add(new Rectangle(
                offset,
                0,
                20,
                getHeight() - hauteur - 100
        )); // haut
    }
}