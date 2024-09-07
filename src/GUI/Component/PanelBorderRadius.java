/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class PanelBorderRadius extends JPanel {
    int shadowSize = 3;
    Color HowerBackgroundColor = new Color(187, 222, 251);

    public PanelBorderRadius () {
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        createShadow(grphcs);
    }

    private void createShadow(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize *2;
        int x = 0;
        int y=0;
        int width = getWidth() - size;
        int height = getHeight() - size;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g2.setBackground(HowerBackgroundColor);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width,  height, 15, 15);
        

        g2.drawImage(img, x, y, null);
    }
}
