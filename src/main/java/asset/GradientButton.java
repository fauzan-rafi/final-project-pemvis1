package asset;

import javax.swing.*;
import java.awt.*;

class GradientButton extends JButton
{
    Color color1, color2;
  
    public GradientButton(String text, Color color1, Color color2)
    {
        super(text);
        this.color1 = color1;
        this.color2 = color2;
        setOpaque(false);
    }
  
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        Paint gradient = new GradientPaint(0, 0, color1, width, height, color2);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, width, height);
        super.paintComponent(g);
    }
}
