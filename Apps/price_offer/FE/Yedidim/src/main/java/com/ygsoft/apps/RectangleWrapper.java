package com.ygsoft.apps;

import java.awt.*;


public class RectangleWrapper {

    private final BoundsWrapper boundsWrapper = new BoundsWrapper();


    public RectangleWrapper() {
        boundsWrapper.parse();
    }


    public Rectangle get(String dimensionsString) {

        String rectangleSize = boundsWrapper.getValue(dimensionsString);

        String[] values = rectangleSize.split(",");

        return new Rectangle(
                Integer.parseInt(values[0]),
                Integer.parseInt(values[1]),
                Integer.parseInt(values[2]),
                Integer.parseInt(values[3])
        );
    }
}
