package freiman.fallingsand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SandComponent extends JComponent {

    private final Sand sand;

    public SandComponent(Sand sand) {
        this.sand = sand;
        // repaint using timer
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sand.fall();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the sand
        Color beige = new Color(194, 178, 128);
        g.setColor(beige);
        int[][] field = sand.getField();
        for (int y = 0; y < field.length; y++) {
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 1) {
                    g.fillRect(x * 2, y * 2, 2, 2); // Using fillRect to draw filled rectangles
                }
            }
        }
    }
}