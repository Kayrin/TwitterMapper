package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapRectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMarkerImage extends MapMarkerCircle implements MapMarker {
    public static final double defaultMarkerSize = 10.0;
    public static final Color defaultColor = Color.white;

    private BufferedImage image;
    private String text;
    private MapRectangle rectangle;

    public MapMarkerImage(Layer layer, Coordinate coord, String url, String text) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        image = util.Util.imageFromURL(url);
        this.text = text;
        setColor(Color.BLACK);
        setBackColor(defaultColor);
    }

    @Override
    public void paint(Graphics g, Point position, int radio) {
        double r = this.getRadius();
        int width = (int) (this.image.getWidth(null)) / 2;
        int height = (int) (this.image.getHeight(null)) / 2;
        int w2 = width / 2;
        int h2 = height / 2;
        g.drawImage(this.image, position.x - w2, position.y - h2, width, height, null);
        this.paintText(g, position);

        int x1 = (int)(rectangle.getTopLeft().getLat());
        int y1 = (int)(rectangle.getTopLeft().getLon());
        int x2 = (int)(rectangle.getBottomRight().getLat());
        int y2 = (int)(rectangle.getBottomRight().getLon());
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getText(){
        return text;
    }
}
