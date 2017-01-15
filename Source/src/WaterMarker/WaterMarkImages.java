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

import WatermarkHelper.WriteToPDF;
import WatermarkHelper.WaterMarkHelper;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.Iterator;
import javax.media.jai.JAI;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.sanselan.*;
import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.jpeg.exifRewrite.ExifRewriter;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.apache.sanselan.formats.tiff.constants.ExifTagConstants;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.constants.TiffFieldTypeConstants;
import org.apache.sanselan.formats.tiff.write.TiffOutputDirectory;
import org.apache.sanselan.formats.tiff.write.TiffOutputField;
import org.apache.sanselan.formats.tiff.write.TiffOutputSet;
import javax.media.jai.NullOpImage;
import javax.media.jai.OpImage;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.ImageCodec;

/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on January 13, 2010
 * WaterMarker 3.0
 * WaterMarkImages.java
 * This Class Contains method to watermark images
 */
public class WaterMarkImages implements Runnable {

    ArrayList ImageList;
    String outPutFilePath;
    float imageQuality;
    double MaxHeight;
    double MaxWidth;
    ArrayList watermarker;
    File OutputFile = null;
    int i;
    WaterMarkHelper helper;
    boolean isPreview;
    javax.swing.JLabel[] jFileListLabelStatus;

    public WaterMarkImages(WaterMarkHelper WMHelper, javax.swing.JLabel[] jFileListLabelstatus) {
        i = 0;
        helper = WMHelper;
        jFileListLabelStatus = jFileListLabelstatus;
    }

    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    /**
     * The generates image with extension .jpg
     *
     * @param Buffer image to be converted to jpeg image
     * @param quality of image
     * @param Watermark helper 
     * 
     */
    public void writeJPEG(BufferedImage input,
            String name,
            float quality, WaterMarkHelper helper, int Index) throws Exception {
        helper.MakeOutputDirectory();
        File inputFile = (File) helper.getInputFiles().get(Index);
        OutputFile = getOutputFile(name, ".jpg");
        addImageHistoryTag(inputFile, input, OutputFile, helper);
    }

    public byte[] bufferedImageToByteArray(BufferedImage img) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        ImageIO.write(img, "jpeg", baos);
        baos.flush();
        baos.close();
        return baos.toByteArray();
    }

    public File getOutputFile(String name, String format) {
        String nameWithoutExtension = name.substring(0, name.lastIndexOf("."));
        File f = new File(name);
        if (f.exists()) {
            boolean isNotFile = true;
            int i = 1;
            String nameWithoutExt = "";
            while (isNotFile) {
                if (f.exists()) {
                    nameWithoutExt = nameWithoutExtension + "_" + i;
                    isNotFile = true;
                } else {
                    isNotFile = false;
                }

                name = nameWithoutExt + format;
                f = new File(name);
                i++;
            }

        }
        return f;
    }

    public void addImageHistoryTag(File file, BufferedImage image, File outputFile, WaterMarkHelper helper) throws Exception {
        File dst = null;
        IImageMetadata metadata = null;
        JpegImageMetadata jpegMetadata = null;
        TiffImageMetadata exif = null;
        OutputStream os = null;
        TiffOutputSet outputSet = new TiffOutputSet();
        String extension = file.getPath().substring(file.getPath().lastIndexOf(".") + 1, file.getPath().length());
        // establish metadata
        try {
            metadata = Sanselan.getMetadata(file);
        } catch (Exception e) {
        }
        if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jfif")) {
            if (metadata != null) {
                jpegMetadata = (JpegImageMetadata) metadata;
            }
            if (jpegMetadata != null) {
                exif = jpegMetadata.getExif();
            }
        } else {
            exif = null;
        }
        if (exif != null) {
            try {
                outputSet = exif.getOutputSet();
            } catch (ImageWriteException e) {
            }
        }

        if (outputSet != null) {
            TiffOutputField creationSoftwareTag = outputSet.findField(TiffConstants.EXIF_TAG_SOFTWARE);
            if (creationSoftwareTag != null) {
                outputSet.removeField(TiffConstants.EXIF_TAG_SOFTWARE);
            }
            TiffOutputField commentTag = outputSet.findField(TiffConstants.EXIF_TAG_XPCOMMENT);
            if (commentTag != null) {
                outputSet.removeField(TiffConstants.EXIF_TAG_XPCOMMENT);
            }
            try {
                String fieldData = "Hitesh S J Sathawane Watermarker";
                TiffOutputField creationSoftware = new TiffOutputField(
                        ExifTagConstants.EXIF_TAG_SOFTWARE,
                        TiffFieldTypeConstants.FIELD_TYPE_ASCII,
                        fieldData.length(),
                        fieldData.getBytes());
                TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
                exifDirectory.add(creationSoftware);
                String comments = helper.getProperty("constants.metaTag.Comments");
                TiffOutputField comment = new TiffOutputField(
                        ExifTagConstants.EXIF_TAG_XPCOMMENT,
                        TiffFieldTypeConstants.FIELD_TYPE_ASCII,
                        comments.length(),
                        comments.getBytes());
                exifDirectory.add(comment);
            } catch (ImageWriteException e) {
            }
        }
        try {
            dst = outputFile;
            os = new FileOutputStream(dst);
            os = new BufferedOutputStream(os);

            if (helper.isLosseless()) {
                new ExifRewriter().updateExifMetadataLossless(bufferedImageToByteArray(image), os, outputSet);
            } else {
                new ExifRewriter().updateExifMetadataLossy(bufferedImageToByteArray(image), os, outputSet);
            }
        } catch (Exception e) {
            outputSet = null;
            String fieldData = "Hitesh S J Sathawane Watermarker";
            TiffOutputField creationSoftware = new TiffOutputField(
                    ExifTagConstants.EXIF_TAG_SOFTWARE,
                    TiffFieldTypeConstants.FIELD_TYPE_ASCII,
                    fieldData.length(),
                    fieldData.getBytes());
            TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
            exifDirectory.add(creationSoftware);
            String comments = helper.getProperty("constants.metaTag.Comments");
            TiffOutputField comment = new TiffOutputField(
                    ExifTagConstants.EXIF_TAG_XPCOMMENT,
                    TiffFieldTypeConstants.FIELD_TYPE_ASCII,
                    comments.length(),
                    comments.getBytes());

            exifDirectory.add(comment);
            if (helper.isLosseless()) {
                new ExifRewriter().updateExifMetadataLossless(bufferedImageToByteArray(image), os, outputSet);
            } else {
                new ExifRewriter().updateExifMetadataLossy(bufferedImageToByteArray(image), os, outputSet);
            }
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }

    }

    public void run() {
        ImageList = helper.getInputFiles();
        outPutFilePath = helper.getOutputImageFolderPath().getPath() + File.separator + helper.getOutputFolderName();
        imageQuality = (float) helper.getImageQuality() / 100;
        MaxHeight = helper.getMaxImageHeight();
        MaxWidth = helper.getMaxImageWidth();
        watermarker = helper.getWaterMarkerList();
        RescaleImage previewImage = new RescaleImage();
        Iterator its = ImageList.iterator();
        for (int x = 1; x < helper.getCompleteImageList(); x++) {
            its.next();
        }
        while (its.hasNext()) {
            File inputImage = (File) its.next();
            try {
                String inputFileName = inputImage.getPath();
                String inputFileExtension = inputFileName.substring(inputFileName.lastIndexOf("."));
                BufferedImage source = null;
                ArrayList sources = new ArrayList();
                if (inputFileExtension.equalsIgnoreCase(".tiff") || inputFileExtension.equalsIgnoreCase(".tif")) {
                    SeekableStream s = new FileSeekableStream(inputImage.getPath());
                    TIFFDecodeParam param = null;
                    ImageDecoder dec = ImageCodec.createImageDecoder("tiff", s, param);
                    RenderedImage op = null;
                    for (int i = 0; i < dec.getNumPages(); i++) {
                        op =
                                new NullOpImage(dec.decodeAsRenderedImage(i),
                                null,
                                OpImage.OP_IO_BOUND,
                                null);
                        source = helper.convertRenderedImage(op);
                        sources.add(source);
                    }
                } else {
                    source = ImageIO.read(new File(inputImage.getPath()));
                    sources.add(source);
                }
                ArrayList results = multiSourceMarker(sources);
                if (!helper.isLosseless()) {
                    writePDF(results, inputImage);
                } else {
                    weriteJPEG(results, inputImage);
                }
                if (OutputFile.isFile()) {

                    BufferedImage correct = null;
                    URL is = getClass().getResource("/image/correct.png");
                    try {
                        correct = ImageIO.read(is);
                    } catch (IOException ex) {
                    }

                    correct = previewImage.RescaleImage(helper, correct, 15, 15);
                    ImageIcon correctIcon = new ImageIcon(correct);
                    jFileListLabelStatus[i].setIcon(correctIcon);
                } else {
                    helper.appendReport(i + 1 + "]\tError\t\t" + helper.getInputFiles().get(i) + "\n");
                    helper.severe("\tError\t\t" + helper.getInputFiles().get(i) + "\n");
                    BufferedImage error = null;
                    URL is = getClass().getResource("/image/error.png");
                    try {
                        error = ImageIO.read(is);
                    } catch (IOException ex) {
                    }
                    error = previewImage.RescaleImage(helper, error, 15, 15);
                    ImageIcon errorIcon = new ImageIcon(error);
                    jFileListLabelStatus[i].setIcon(errorIcon);
                }

            } catch (Exception e) {
                e.printStackTrace();
                helper.appendReport(i + 1 + "]\tError\t\t" + helper.getInputFiles().get(i) + "\n");
                helper.severe("\tError\t\t" + helper.getInputFiles().get(i) + "\n");
                BufferedImage error = null;
                URL is = getClass().getResource("/image/error.png");
                try {
                    error = ImageIO.read(is);
                } catch (IOException ex) {
                }
                error = previewImage.RescaleImage(helper, error, 15, 15);
                ImageIcon errorIcon = new ImageIcon(error);
                jFileListLabelStatus[i].setIcon(errorIcon);
            }
            i = ImageList.indexOf(inputImage) + 1;

            helper.getMainProgressBar().setValue(i);

            if (helper.isPause() && helper.getWorker().isAlive()) {
                helper.setCompleteImageList(i);
                helper.getMainStatus().setText(helper.getProperty("message.watermarking.pause"));
                helper.getWorker().stop();
                helper.warning("Pause");

            }
            if (helper.isStop() && helper.getWorker().isAlive()) {
                helper.getMainProgressBar().setValue(0);
                helper.getMainStatus().setText(helper.getProperty("message.watermarking.stop"));
                helper.setInputFiles(new ArrayList());
                helper.setCompleteImageList(0);
                helper.getWorker().stop();
                helper.warning("Stop");
            }
        }

        if (!(helper.isStop() && helper.isPause())) {
            helper.getMainStatus().setText(helper.getProperty("message.watermarking.complete"));
        }
    }

    /**
     * processes the buffer image of img document and multipage tiff
     *
     * @param Arraylist of bufferimages
     *
     */
    public ArrayList multiSourceMarker(ArrayList sources) throws Exception {
        ArrayList results = new ArrayList();
        int imgNo = 0;
        for (Iterator itIMG = sources.iterator(); itIMG.hasNext();) {
            BufferedImage source = (BufferedImage) itIMG.next();

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
                for (Iterator it = watermarker.iterator(); it.hasNext();) {
                    ArrayList waterMarkerList = (ArrayList) it.next();
                    String positionSelect = waterMarkerList.get(10).toString();
                    int hSpace = Integer.parseInt(waterMarkerList.get(13).toString());
                    int vSpace = Integer.parseInt(waterMarkerList.get(14).toString());
                    int x = 0;
                    int y = 0;
                    float fill = (float) (Double.valueOf(waterMarkerList.get(9).toString()) / 100);
                    if (waterMarkerList.get(0).toString().equalsIgnoreCase("TEXT")) {
                        Font font = (Font) waterMarkerList.get(7);
                        FontMetrics outMetrics = g2d.getFontMetrics(font);
                        g2d.setFont(font);
                        g2d.setColor((Color) waterMarkerList.get(8));
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fill));
                        String WatermarkingText[] = (String[]) waterMarkerList.get(1);
                        int blockHeight = 0;
                        int blockWidth = 0;
                        if (positionSelect.equalsIgnoreCase("Horizontal Top Left")
                                || positionSelect.equalsIgnoreCase("Horizontal Top Center")
                                || positionSelect.equalsIgnoreCase("Horizontal Top Right")
                                || positionSelect.equalsIgnoreCase("Horizontal Middle Left")
                                || positionSelect.equalsIgnoreCase("Horizontal Middle Center")
                                || positionSelect.equalsIgnoreCase("Horizontal Middle Right")
                                || positionSelect.equalsIgnoreCase("Horizontal Bottom Left")
                                || positionSelect.equalsIgnoreCase("Horizontal Bottom Center")
                                || positionSelect.equalsIgnoreCase("Horizontal Bottom Right")
                                || positionSelect.equalsIgnoreCase("Horizontal Coustome")) {
                            // For Horizontal Text
                            for (int r = 0; r < WatermarkingText.length; r++) {
                                java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(WatermarkingText[r], g2d);
                                int textHeight = (int) (rect.getHeight());
                                int textWidth = (int) (rect.getWidth());
                                blockHeight = blockHeight + textHeight;
                                if (blockWidth < textWidth) {
                                    blockWidth = textWidth;
                                }
                            }

                            java.awt.geom.Rectangle2D rect0Posn = outMetrics.getStringBounds(WatermarkingText[0], g2d);
                            int textHeight0Posn = (int) (rect0Posn.getHeight());
                            int topYposition = vSpace - textHeight0Posn;
                            int middleYPosition = (result.getHeight() / 2) - (blockHeight / 2) - textHeight0Posn + outMetrics.getAscent();
                            int bottomYPosition = result.getHeight() - blockHeight - vSpace - textHeight0Posn + outMetrics.getAscent();
                            int coustomYPosition = Integer.parseInt(waterMarkerList.get(12).toString()) - textHeight0Posn;
                            for (int i = 0; i < WatermarkingText.length; i++) {
                                java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(WatermarkingText[i], g2d);
                                int textHeight = (int) (rect.getHeight());
                                int textWidth = (int) (rect.getWidth());
                                if (positionSelect.equalsIgnoreCase("Horizontal Top Left")) {
                                    topYposition = topYposition + textHeight;
                                    x = hSpace;
                                    y = topYposition + outMetrics.getAscent();
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Top Center")) {
                                    topYposition = topYposition + textHeight;
                                    x = (result.getWidth() / 2) - (textWidth / 2);
                                    y = topYposition + outMetrics.getAscent();
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Top Right")) {
                                    topYposition = topYposition + textHeight;
                                    x = (result.getWidth()) - (blockWidth) - hSpace;
                                    y = topYposition + outMetrics.getAscent();
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Middle Left")) {
                                    middleYPosition = middleYPosition + textHeight;
                                    x = hSpace;
                                    y = middleYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Middle Center")) {
                                    middleYPosition = middleYPosition + textHeight;
                                    x = (result.getWidth() / 2) - (textWidth / 2);
                                    y = middleYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Middle Right")) {
                                    middleYPosition = middleYPosition + textHeight;
                                    x = x = (result.getWidth()) - (blockWidth) - hSpace;
                                    y = middleYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Bottom Left")) {
                                    bottomYPosition = bottomYPosition + textHeight;
                                    x = hSpace;
                                    y = bottomYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Bottom Center")) {
                                    bottomYPosition = bottomYPosition + textHeight;
                                    x = (result.getWidth() / 2) - (textWidth / 2);
                                    y = bottomYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Bottom Right")) {
                                    bottomYPosition = bottomYPosition + textHeight;
                                    x = x = (result.getWidth()) - (blockWidth) - hSpace;
                                    y = bottomYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Horizontal Coustome")) {
                                    coustomYPosition = coustomYPosition + textHeight;
                                    x = Integer.parseInt(waterMarkerList.get(11).toString());
                                    y = coustomYPosition;
                                }

                                g2d.drawString(WatermarkingText[i], x, y);
                            }
                        } else if (positionSelect.equalsIgnoreCase("Vertical Top Left")
                                || positionSelect.equalsIgnoreCase("Vertical Top Center")
                                || positionSelect.equalsIgnoreCase("Vertical Top Right")
                                || positionSelect.equalsIgnoreCase("Vertical Middle Left")
                                || positionSelect.equalsIgnoreCase("Vertical Middle Center")
                                || positionSelect.equalsIgnoreCase("Vertical Middle Right")
                                || positionSelect.equalsIgnoreCase("Vertical Bottom Left")
                                || positionSelect.equalsIgnoreCase("Vertical Bottom Center")
                                || positionSelect.equalsIgnoreCase("Vertical Bottom Right")
                                || positionSelect.equalsIgnoreCase("Vertical Coustome")) {

                            //get block height and width
                            ArrayList lineWidthList = new ArrayList();
                            ArrayList lineHeightList = new ArrayList();
                            for (int r = 0; r < WatermarkingText.length; r++) {
                                char[] CurrentLine = WatermarkingText[r].toString().toCharArray();
                                int lineHeight = 0;
                                int lineWidth = 0;

                                for (int i = 0; i < CurrentLine.length; i++) {
                                    String Character = String.valueOf(CurrentLine[i]);

                                    java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(Character, g2d);
                                    int charHeight = (int) (rect.getHeight());
                                    int charWidth = (int) (rect.getWidth());

                                    lineHeight = lineHeight + charHeight;
                                    if (lineWidth < charWidth) {
                                        lineWidth = charWidth;
                                    }
                                }

                                blockWidth = blockWidth + lineWidth;
                                if (blockHeight < lineHeight) {
                                    blockHeight = lineHeight;
                                }
                                lineWidthList.add(lineWidth);
                                lineHeightList.add(lineHeight);
                            }
                            //get Top Left X & Y Positions
                            int TopLeftXPosition = 0;
                            int TopLeftYPosition = 0;
                            if (positionSelect.equalsIgnoreCase("Vertical Top Left")) {
                                TopLeftXPosition = hSpace;
                                TopLeftYPosition = vSpace;
                            } else if (positionSelect.equalsIgnoreCase("Vertical Top Center")) {
                                TopLeftXPosition = (result.getWidth() / 2) - (blockWidth / 2);
                                TopLeftYPosition = vSpace;
                            } else if (positionSelect.equalsIgnoreCase("Vertical Top Right")) {
                                TopLeftXPosition = (result.getWidth()) - (blockWidth) - hSpace;
                                TopLeftYPosition = vSpace;
                            } else if (positionSelect.equalsIgnoreCase("Vertical Middle Left")) {

                                TopLeftXPosition = hSpace;
                                TopLeftYPosition = (result.getHeight() / 2) - (blockHeight / 2);
                            } else if (positionSelect.equalsIgnoreCase("Vertical Middle Center")) {

                                TopLeftXPosition = (result.getWidth() / 2) - (blockWidth / 2);
                                TopLeftYPosition = (result.getHeight() / 2) - (blockHeight / 2);
                            } else if (positionSelect.equalsIgnoreCase("Vertical Middle Right")) {

                                TopLeftXPosition = x = (result.getWidth()) - (blockWidth) - hSpace;
                                TopLeftYPosition = (result.getHeight() / 2) - (blockHeight / 2);
                            } else if (positionSelect.equalsIgnoreCase("Vertical Bottom Left")) {

                                TopLeftXPosition = hSpace;
                                TopLeftYPosition = (result.getHeight()) - (blockHeight) - vSpace;
                            } else if (positionSelect.equalsIgnoreCase("Vertical Bottom Center")) {

                                TopLeftXPosition = (result.getWidth() / 2) - (blockWidth / 2);
                                TopLeftYPosition = (result.getHeight()) - (blockHeight) - vSpace;
                            } else if (positionSelect.equalsIgnoreCase("Vertical Bottom Right")) {

                                TopLeftXPosition = x = (result.getWidth()) - (blockWidth) - hSpace;
                                TopLeftYPosition = (result.getHeight()) - (blockHeight) - vSpace;
                            } else if (positionSelect.equalsIgnoreCase("Vertical Coustome")) {
                                TopLeftXPosition = Integer.parseInt(waterMarkerList.get(11).toString());
                                TopLeftYPosition = Integer.parseInt(waterMarkerList.get(12).toString());
                            }

                            int currentXPosition = TopLeftXPosition - (Integer.parseInt(lineWidthList.get(0).toString()));
                            for (int r1 = 0; r1 < WatermarkingText.length; r1++) {

                                int lineHeight = Integer.parseInt(lineHeightList.get(r1).toString());
                                int lineWidth = Integer.parseInt(lineWidthList.get(r1).toString());
                                currentXPosition = currentXPosition + lineWidth;
                                char[] CurrentLine = WatermarkingText[r1].toString().toCharArray();
                                String firstChar = WatermarkingText[r1].toString().substring(0, 1);

                                java.awt.geom.Rectangle2D rect0Posn = outMetrics.getStringBounds(firstChar, g2d);
                                int charHeight0Posn = (int) (rect0Posn.getHeight());
                                int currentYPosition = TopLeftYPosition;
                                int modifiedYposition = currentYPosition + ((blockHeight - lineHeight) / 2);
                                for (int i1 = 0; i1 < CurrentLine.length; i1++) {
                                    String Character = String.valueOf(CurrentLine[i1]);
                                    java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(Character, g2d);
                                    int charHeight = (int) (rect.getHeight());
                                    int charWidth = (int) (rect.getWidth());
                                    modifiedYposition = modifiedYposition + charHeight;
                                    int modifiedXPosition = currentXPosition + ((lineWidth - charWidth) / 2);
                                    g2d.drawString(Character, modifiedXPosition, modifiedYposition);
                                }

                            }

                        } else if (positionSelect.equalsIgnoreCase("Bottom-Top Diognal")
                                || positionSelect.equalsIgnoreCase("Top-Bottom Diognal")) {


                            double resHeight = result.getHeight();
                            double resWidth = result.getWidth();

                            double angle = Math.atan2(resHeight, resWidth);
                            if (positionSelect.equalsIgnoreCase("Bottom-Top Diognal")) {
                                angle = angle * (-1);
                            }
                            for (int r = 0; r < WatermarkingText.length; r++) {
                                java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(WatermarkingText[r], g2d);
                                int textHeight = (int) (rect.getHeight());
                                int textWidth = (int) (rect.getWidth());
                                blockHeight = blockHeight + textHeight;
                                if (blockWidth < textWidth) {
                                    blockWidth = textWidth;
                                }
                            }

                            java.awt.geom.Rectangle2D rect0Posn = outMetrics.getStringBounds(WatermarkingText[0], g2d);
                            int textHeight0Posn = (int) (rect0Posn.getHeight());
                            int middleYPosition = (result.getHeight() / 2) - (blockHeight / 2) - textHeight0Posn + outMetrics.getAscent();

                            for (int i = 0; i < WatermarkingText.length; i++) {
                                java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(WatermarkingText[i], g2d);
                                int textHeight = (int) (rect.getHeight());
                                int textWidth = (int) (rect.getWidth());
                                middleYPosition = middleYPosition + textHeight;
                                x = (result.getWidth() / 2) - (textWidth / 2);
                                y = middleYPosition;
                                AffineTransform affineTransform = new AffineTransform();
                                affineTransform.rotate(angle, (result.getWidth() / 2), (result.getHeight() / 2));
                                g2d.transform(affineTransform);
                                g2d.drawString(WatermarkingText[i], x, y);
                                AffineTransform affineTransformRev = new AffineTransform();
                                affineTransformRev.rotate(-angle, (result.getWidth() / 2), (result.getHeight() / 2));
                                g2d.transform(affineTransformRev);

                            }
                        } else {
                            // error
                            Integer.parseInt("s");
                        }


                    } else if (waterMarkerList.get(0).toString().equalsIgnoreCase("SHAPE")) {
                        int ShapeHeight = Integer.parseInt(waterMarkerList.get(6).toString());
                        int ShapeWidth = Integer.parseInt(waterMarkerList.get(5).toString());
                        if (positionSelect.equalsIgnoreCase("Top Left")) {
                            x = hSpace;
                            y = vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Top Center")) {
                            x = (result.getWidth() / 2) - (ShapeWidth / 2);
                            y = vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Top Right")) {
                            x = (result.getWidth()) - (ShapeWidth) - hSpace;
                            y = vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Middle Left")) {
                            x = hSpace;
                            y = (result.getHeight() / 2) - (ShapeHeight / 2);
                        } else if (positionSelect.equalsIgnoreCase("Middle Center")) {
                            x = (result.getWidth() / 2) - (ShapeWidth / 2);
                            y = (result.getHeight() / 2) - (ShapeHeight / 2);
                        } else if (positionSelect.equalsIgnoreCase("Middle Right")) {
                            x = x = (result.getWidth()) - (ShapeWidth) - hSpace;
                            y = (result.getHeight() / 2) - (ShapeHeight / 2);
                        } else if (positionSelect.equalsIgnoreCase("Bottom Left")) {
                            x = hSpace;
                            y = (result.getHeight()) - (ShapeHeight) - vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Bottom Center")) {
                            x = (result.getWidth() / 2) - (ShapeWidth / 2);
                            y = (result.getHeight()) - (ShapeHeight) - vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Bottom Right")) {
                            x = x = (result.getWidth()) - (ShapeWidth) - hSpace;
                            y = (result.getHeight()) - (ShapeHeight) - vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Coustome")) {
                            x = Integer.parseInt(waterMarkerList.get(11).toString());
                            y = Integer.parseInt(waterMarkerList.get(12).toString());
                        }
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fill));
                        g2d.setColor((Color) waterMarkerList.get(8));


                        if (waterMarkerList.get(2).toString().equalsIgnoreCase("RECTRANGLE")) {
                            g2d.fillRect(x, y, ShapeWidth, ShapeHeight);
                        } else if (waterMarkerList.get(2).toString().equalsIgnoreCase("OVEL")) {
                            g2d.fillOval(x, y, ShapeWidth, ShapeHeight);
                        } else {
                            Integer.parseInt("s");
                        }
                    } else if (waterMarkerList.get(0).toString().equalsIgnoreCase("IMAGE")) {
                        File f = (File) waterMarkerList.get(3);
                        RenderedImage image2 = JAI.create("fileload", f.getPath());

                        BufferedImage waterMarkImage = helper.convertRenderedImage(image2);

                        double imageScale = Double.parseDouble(waterMarkerList.get(4).toString()) / 100;
                        RescaleImage RescaleImage = new RescaleImage();

                        BufferedImage im2 = RescaleImage.RescaleImage(helper, waterMarkImage, waterMarkImage.getHeight() * imageScale, waterMarkImage.getWidth() * imageScale);
                        if (positionSelect.equalsIgnoreCase("Top Left")) {
                            x = hSpace;
                            y = vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Top Center")) {
                            x = (result.getWidth() / 2) - (im2.getWidth() / 2);
                            y = vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Top Right")) {
                            x = (result.getWidth()) - (im2.getWidth()) - hSpace;
                            y = vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Middle Left")) {
                            x = hSpace;
                            y = (result.getHeight() / 2) - (im2.getHeight() / 2);
                        } else if (positionSelect.equalsIgnoreCase("Middle Center")) {
                            x = (result.getWidth() / 2) - (im2.getWidth() / 2);
                            y = (result.getHeight() / 2) - (im2.getHeight() / 2);
                        } else if (positionSelect.equalsIgnoreCase("Middle Right")) {
                            x = x = (result.getWidth()) - (im2.getWidth()) - hSpace;
                            y = (result.getHeight() / 2) - (im2.getHeight() / 2);
                        } else if (positionSelect.equalsIgnoreCase("Bottom Left")) {
                            x = hSpace;
                            y = (result.getHeight()) - (im2.getHeight()) - vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Bottom Center")) {
                            x = (result.getWidth() / 2) - (im2.getWidth() / 2);
                            y = (result.getHeight()) - (im2.getHeight()) - vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Bottom Right")) {
                            x = x = (result.getWidth()) - (im2.getWidth()) - hSpace;
                            y = (result.getHeight()) - (im2.getHeight()) - vSpace;
                        } else if (positionSelect.equalsIgnoreCase("Coustome")) {
                            x = Integer.parseInt(waterMarkerList.get(11).toString());
                            y = Integer.parseInt(waterMarkerList.get(12).toString());
                        }
                        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fill));
                        g2d.drawImage(im2, x, y, null);

                    } else if (waterMarkerList.get(0).toString().equalsIgnoreCase("CAPTION")) {
                        String WatermarkingText[] = (String[]) helper.getInputFilesCaption().get(i);
                        String checkNull = WatermarkingText[0];
                        if (WatermarkingText.length < 2 && checkNull == null) {
                        } else {
                            Font font = (Font) waterMarkerList.get(7);
                            FontMetrics outMetrics = g2d.getFontMetrics(font);
                            g2d.setFont(font);
                            g2d.setColor((Color) waterMarkerList.get(8));
                            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fill));
                            int blockHeight = 0;
                            int blockWidth = 0;
                            // For Horizontal Text
                            for (int r = 0; r < WatermarkingText.length; r++) {
                                java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(WatermarkingText[r], g2d);
                                int textHeight = (int) (rect.getHeight());
                                int textWidth = (int) (rect.getWidth());
                                blockHeight = blockHeight + textHeight;
                                if (blockWidth < textWidth) {
                                    blockWidth = textWidth;
                                }
                            }

                            java.awt.geom.Rectangle2D rect0Posn = outMetrics.getStringBounds(WatermarkingText[0], g2d);
                            int textHeight0Posn = (int) (rect0Posn.getHeight());
                            int topYposition = vSpace - textHeight0Posn;
                            int middleYPosition = (result.getHeight() / 2) - (blockHeight / 2) - textHeight0Posn + outMetrics.getAscent();
                            int bottomYPosition = result.getHeight() - blockHeight - vSpace - textHeight0Posn + outMetrics.getAscent();
                            for (int i = 0; i < WatermarkingText.length; i++) {
                                java.awt.geom.Rectangle2D rect = outMetrics.getStringBounds(WatermarkingText[i], g2d);
                                int textHeight = (int) (rect.getHeight());
                                int textWidth = (int) (rect.getWidth());
                                if (positionSelect.equalsIgnoreCase("Top Left")) {
                                    topYposition = topYposition + textHeight;
                                    x = hSpace;
                                    y = topYposition + outMetrics.getAscent();
                                } else if (positionSelect.equalsIgnoreCase("Top Center")) {
                                    topYposition = topYposition + textHeight;
                                    x = (result.getWidth() / 2) - (textWidth / 2);
                                    y = topYposition + outMetrics.getAscent();
                                } else if (positionSelect.equalsIgnoreCase("Top Right")) {
                                    topYposition = topYposition + textHeight;
                                    x = (result.getWidth()) - (blockWidth) - hSpace;
                                    y = topYposition + outMetrics.getAscent();
                                } else if (positionSelect.equalsIgnoreCase("Middle Left")) {
                                    middleYPosition = middleYPosition + textHeight;
                                    x = hSpace;
                                    y = middleYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Middle Center")) {
                                    middleYPosition = middleYPosition + textHeight;
                                    x = (result.getWidth() / 2) - (textWidth / 2);
                                    y = middleYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Middle Right")) {
                                    middleYPosition = middleYPosition + textHeight;
                                    x = x = (result.getWidth()) - (blockWidth) - hSpace;
                                    y = middleYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Bottom Left")) {
                                    bottomYPosition = bottomYPosition + textHeight;
                                    x = hSpace;
                                    y = bottomYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Bottom Center")) {
                                    bottomYPosition = bottomYPosition + textHeight;
                                    x = (result.getWidth() / 2) - (textWidth / 2);
                                    y = bottomYPosition;
                                } else if (positionSelect.equalsIgnoreCase("Bottom Right")) {
                                    bottomYPosition = bottomYPosition + textHeight;
                                    x = x = (result.getWidth()) - (blockWidth) - hSpace;
                                    y = bottomYPosition;
                                }
                                g2d.drawString(WatermarkingText[i], x, y);
                            }
                        }

                    } else {
                        Integer.parseInt("s");
                    }
                }
            } finally {
                if (g2d != null) {
                    g2d.dispose();
                }
            }
            results.add(result);
            imgNo++;
        }
        return results;
    }

    /**
     * converts arraylist of buffer images to a pdf document based on the document size
     *
     * @param Arraylist of bufferimages
     * @param InputImage File
     *
     */
    public void writePDF(ArrayList results, File inputImage) {
        File inputFolderParent = helper.getInputParentFolderPath();
        String inputParentFoldrName = "";
        String ExtendedFolderPath = "";
        String outputFolder = "";
        String tiffPageNo = "";
        String ext = ".pdf";

        if (inputFolderParent != null) {
            inputParentFoldrName = inputFolderParent.getPath();
            ExtendedFolderPath = inputImage.getPath().substring(inputParentFoldrName.length(), inputImage.getPath().lastIndexOf(".")) + tiffPageNo + ext;
            outputFolder = outPutFilePath + File.separator + inputFolderParent.getName() + ExtendedFolderPath;
        } else {
            ExtendedFolderPath = inputImage.getPath().substring(0, 1) + File.separator + inputImage.getPath().substring(3, inputImage.getPath().lastIndexOf(".")) + tiffPageNo + ext;
            outputFolder = outPutFilePath + File.separator + ExtendedFolderPath;
        }
        helper.MakeFolderStructure(outputFolder);
        String outputPath = outputFolder;

        WriteToPDF writer = new WriteToPDF();
        OutputFile = getOutputFile(outputPath, ".pdf");
        writer.writeToPDF(OutputFile.getPath(), results, getMaxDocSize(results), helper);
        helper.appendReport(i + 1 + "]\tComplete\t" + helper.getInputFiles().get(i) + "\n");
        helper.Info("\tComplete\t\t" + helper.getInputFiles().get(i) + "\n");

    }

    public float[] getMaxDocSize(ArrayList resulList) {
        int maxHeight = 0, maxWidth = 0;
        float dimentions[] = new float[2];
        for (Iterator it = resulList.iterator(); it.hasNext();) {
            BufferedImage result = (BufferedImage) it.next();
            if (maxHeight < result.getHeight()) {
                maxHeight = result.getHeight();
            }
            if (maxWidth < result.getWidth()) {
                maxWidth = result.getWidth();
            }
        }
        dimentions[0] = maxHeight;
        dimentions[1] = maxWidth;
        return dimentions;
    }

    /**
     * converts arraylist of buffer images to a number of jpg document based on the document size
     *
     * @param Arraylist of bufferimages
     * @param InputImage File
     *
     */
    public void weriteJPEG(ArrayList results, File inputImage) throws Exception {
        int imgNo = 0;
        for (Iterator imgRes = results.iterator(); imgRes.hasNext();) {
            BufferedImage result = (BufferedImage) imgRes.next();
            File inputFolderParent = helper.getInputParentFolderPath();
            String inputParentFoldrName = "";
            String ExtendedFolderPath = "";
            String outputFolder = "";
            String tiffPageNo = "";
            String ext = ".jpg";
            if (inputFolderParent != null) {
                inputParentFoldrName = inputFolderParent.getPath();
                ExtendedFolderPath = inputImage.getPath().substring(inputParentFoldrName.length(), inputImage.getPath().lastIndexOf(".")) + tiffPageNo + ext;
                outputFolder = outPutFilePath + File.separator + inputFolderParent.getName() + ExtendedFolderPath;
            } else {
                ExtendedFolderPath = inputImage.getPath().substring(0, 1) + File.separator + inputImage.getPath().substring(3, inputImage.getPath().lastIndexOf(".")) + tiffPageNo + ext;
                outputFolder = outPutFilePath + File.separator + ExtendedFolderPath;
            }
            helper.MakeFolderStructure(outputFolder);
            String outputPath = outputFolder;
            writeJPEG(result, outputPath, imageQuality, helper, i);
            helper.appendReport(i + 1 + "]\tComplete\t" + helper.getInputFiles().get(i) + "\n");
            helper.Info("\tComplete\t\t" + helper.getInputFiles().get(i) + "\n");
        }
    }
}
