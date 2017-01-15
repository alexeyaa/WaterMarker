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

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.util.ArrayList;
/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on Feb 06, 2011
 * WaterMarker 3.0
 * WriteToPDF.java
 * The Class converts multiple buffer images to PDF document
 */

public class WriteToPDF {

    public void writeToPDF(String outputPath, ArrayList results, float[] dimentions, WaterMarkHelper helper) {

        try {
            Rectangle pageSize = new Rectangle(dimentions[1] + 20, dimentions[0] + 20);
            // Document document = new Document(PageSize.LETTER);
            Document document = new Document(pageSize);
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            for (Iterator it = results.iterator(); it.hasNext();) {
                BufferedImage result = (BufferedImage) it.next();
                Image img = Image.getInstance(result, null);
                if (img != null) {
                    img.scalePercent(95);
                    img.setAlignment(Image.MIDDLE);
                    document.add(img);
                    document.newPage();
                }
            }

            document.addSubject(helper.getProperty("constants.metaTag.Comments"));
            document.addKeywords("Hitesh S J Sathawane Watermarker");
            document.addCreator("Hitesh S J Sathawane Watermarker");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
