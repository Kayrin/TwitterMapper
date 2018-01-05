package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import twitter4j.Status;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMarkerImage extends MapMarkerCircle implements MapMarker {
    public static final double defaultMarkerSize = 10.0;
    public static final Color defaultColor = Color.white;

    private BufferedImage image;
    private Status status;

    public MapMarkerImage(Layer layer, Coordinate coord, String url, Status status) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        image = util.Util.imageFromURL(url);
        this.status = status;
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
    }

    public BufferedImage getImage() {
        return image;
    }

    public Status getStatus(){
        return status;
    }
}
