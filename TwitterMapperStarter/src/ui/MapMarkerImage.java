package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;

import java.awt.image.BufferedImage;

public class MapMarkerImage extends MapMarkerSimple{
    private BufferedImage image;

    public MapMarkerImage(Layer layer, Coordinate coord, String url) {
        super(layer, coord);
        image = util.Util.imageFromURL(url);
    }


}
