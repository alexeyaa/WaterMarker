
package WatermarkHelper;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;

/**
 * A simple panel that renders a font preview for {@link com.lamatek.swingextras.JFontChooser JFontChooser} component.
 */
public class JFontPreviewPanel extends JPanel {

    private Font font;

    /**
     * Constructs a font preview panel initialized to the specified font.
     *
     * @param f The font used to render the preview
     */
    public JFontPreviewPanel(Font f) {
        super();
        setFont(f);
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Preview"));
    }

    /**
     * Sets the font used to render the preview text.
     *
     * @param f The font used to render the preview
     */
    public void setFont(Font f) {
        this.font = f;
        repaint();
    }

    public void update(Graphics g) {
        paintComponent(g);
        paintBorder(g);
    }

    public void paintComponent(Graphics g) {
        Image osi = createImage(getSize().width, getSize().height);
        Graphics osg = osi.getGraphics();
        osg.setFont(this.font);
        Rectangle2D bounds = font.getStringBounds(font.getFontName(), 0, font.getFontName().length(), new FontRenderContext(null, true, false));
        int width = (new Double(bounds.getWidth())).intValue();
        int height = (new Double(bounds.getHeight())).intValue();
        osg.drawString(font.getFontName(), 5, (((getSize().height - height) / 2) + height));

        g.drawImage(osi, 0, 0, this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(getSize().width, 75);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
