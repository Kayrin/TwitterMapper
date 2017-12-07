package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;

import java.awt.*;

public class MapMarkerColored extends MapMarkerSimple {
    public static Color color = defaultColor;

    public MapMarkerColored(Layer layer, Coordinate coord, Color color){
        super(layer, coord);
        this.color = color;
    }
}
