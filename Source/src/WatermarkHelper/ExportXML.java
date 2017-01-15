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
package WatermarkHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on January 13, 2010
 * WaterMarker 3.0
 * ExportXML.java
 * This class contains method for Export of property file in XML Format
 */
public class ExportXML {

    /**
     * The generates a XML file for current settings
     * 
     * @param Watermark helper 
     * @param output Folder path
     * @param Maximum Height
     * @param Maximum Width
     * @param quality of image
     */
    public void ExportXML(WaterMarkHelper helper,
            File outputFolder,
            String MaxHeight,
            String MaxWidth,
            String ImageQuality) throws IOException {
        String xmlOutput = "<?xml version=\"1.0\"?>\n <WaterMarker>\n";
        xmlOutput = xmlOutput + " <ImagesProperties>\n" +
                " <maxHeight>" + MaxHeight + "</maxHeight>\n" +
                " <maxWidth>" + MaxWidth + "</maxWidth>\n" +
                " <imageQuality>" + ImageQuality + "</imageQuality>\n" +
                " </ImagesProperties>\n";
        xmlOutput = xmlOutput + "<watermarkers>\n";
        for (Iterator it = helper.getWaterMarkerList().iterator(); it.hasNext();) {
            ArrayList list = (ArrayList) it.next();
            String WatermarkingText[];
            String textString = null;
            if (list.get(0).toString().equalsIgnoreCase("TEXT")) {
                WatermarkingText = (String[]) list.get(1);
                textString = "";
                for (int r = 0; r < WatermarkingText.length; r++) {
                    textString = textString + WatermarkingText[r] + "\n";
                }
                textString = textString.substring(0, textString.length() - 1);
            }
            xmlOutput = xmlOutput + "<watermarkerProperties>\n" +
                    "  <watermarkType>" + list.get(0) + "</watermarkType>\n" +
                    " <textString>" + textString + "</textString>\n" +
                    "  <shape>" + list.get(2) + "</shape>\n" +
                    " <imagePath>" + list.get(3) + "</imagePath>\n" +
                    " <imageScale>" + list.get(4) + "</imageScale>\n" +
                    " <shapeWidth>" + list.get(5) + "</shapeWidth>\n" +
                    " <shapeHeight>" + list.get(6) + "</shapeHeight>\n" +
                    " <font>" + list.get(7) + "</font>\n" +
                    " <color>" + list.get(8) + "</color>\n" +
                    " <fill>" + list.get(9) + "</fill>\n" +
                    " <positionSelected>" + list.get(10) + "</positionSelected>\n" +
                    " <Xposition>" + list.get(11) + "</Xposition>\n" +
                    " <Yposition>" + list.get(12) + "</Yposition>\n" +
                    " <Hspace>" + list.get(13) + "</Hspace>\n" +
                    "<Vspace>" + list.get(14) + "</Vspace>\n" +
                    "</watermarkerProperties>\n  ";
        }
        xmlOutput = xmlOutput + "</watermarkers>\n";
        xmlOutput = xmlOutput + "</WaterMarker>\n";
        helper.MakeOutputDirectory();
        Writer output = null;
        File file = new File(helper.getOutputImageFolderPath().getPath() + File.separator + helper.getOutputFolderName() + File.separator + "Watermark Export file.xml");
        output = new BufferedWriter(new FileWriter(file));
        output.write(xmlOutput);
        output.close();

    }
}
