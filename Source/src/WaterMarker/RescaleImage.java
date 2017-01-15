/*
 * Copyright (c) 2011 Hitesh Shankarrao Jyoti Sathawane, Dombivli, MH, 421201, India.
 * mailto:hiteshsathawane@gmail.com. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the
 *    distribution.
 * 3. Redistributions in any form must be accompanied by information on
 *    how to obtain complete source code for the WaterMarker software.
 *
 *
 * This software is provided "as is", without warranty of any kind, express or 
 * implied, including but not limited to the warranties of merchantability,   
 * fitness for a particular purpose and noninfringement.  In no event shall
 * Copyright holder be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise,  arising from,out of
 * or in connection with Watermarker or the use or other dealings in
 * Watermarker.
 */
package WaterMarker;

import WatermarkHelper.WaterMarkHelper;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on January 13, 2010
 * WaterMarker 3.0
 * RescaleImage.java
 * The Class contain method for rescailing of Images
 */
public class RescaleImage {

    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    /**
     * The mehtod rescales the image and returns bufferimage respectively
     *
     * @param Watermark Helper
     * @param Maximum Height
     * @param Maximum Width
     * 
     */
    public BufferedImage RescaleImage(WaterMarkHelper helper, BufferedImage source, double MaxHeight, double MaxWidth) {
        try {
            double height = source.getHeight();
            double width = source.getWidth();
            String imageSizeString = helper.imageSize(height, width, MaxHeight, MaxWidth);
            String[] imageSize = imageSizeString.split("##@@##");
            height = Double.parseDouble(imageSize[0]);
            width = Double.parseDouble(imageSize[1]);
            Object interpolation = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
            int sourceWidth = source.getWidth();
            int sourceHeight = source.getHeight();
            double xScale = ((double) width) / (double) sourceWidth;
            double yScale = ((double) height) / (double) sourceHeight;
            if (height <= 0) {
                xScale = yScale;
                width = (int) Math.rint(xScale * sourceWidth);
            }
            if (height <= 0) {
                yScale = xScale;
                width = (int) Math.rint(yScale * sourceHeight);
            }
            GraphicsConfiguration gc = getDefaultConfiguration();
            BufferedImage result = gc.createCompatibleImage((int) width, (int) height, source.getColorModel().getTransparency());
            Graphics2D g2d = null;
            try {
                g2d = result.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolation);
                AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
                g2d.drawRenderedImage(source, at);

            } finally {
                if (g2d != null) {
                    g2d.dispose();
                }
            }
            return result;
        } catch (Exception e) {
        }

        return null;

    }
}
