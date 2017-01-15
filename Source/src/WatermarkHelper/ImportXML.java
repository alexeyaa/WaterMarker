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

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.ArrayList;

/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on January 13, 2010
 * WaterMarker 3.0
 * ImportXML.java
 * This Class contains method for importing and validating properties from external XML file
 */
public class ImportXML {

    /**
     * Validates input XML File and extrates the settings from it 
     *
     * @param Watermark Helper
     * @param XML File Input
     * @return Settings in form of arraylist
     *
     */
    public ArrayList importXML(WaterMarkHelper helper, File inputXML) {
        ArrayList returnList = new ArrayList();
        boolean hasNoError = true;
        returnList.add(hasNoError);
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputXML);
            doc.getDocumentElement().normalize();
            NodeList ImageProperties = doc.getElementsByTagName("ImagesProperties");
            Node fstNode = ImageProperties.item(0);
            Element fstElmnt = (Element) fstNode;
            NodeList maxHeight = fstElmnt.getElementsByTagName("maxHeight");
            Element maxHeightElmnt = (Element) maxHeight.item(0);
            NodeList maxHeightNm = maxHeightElmnt.getChildNodes();
            int MaxHeight = Integer.parseInt(((Node) maxHeightNm.item(0)).getNodeValue());
            NodeList maxWidth = fstElmnt.getElementsByTagName("maxWidth");
            Element maxWidthElmnt = (Element) maxWidth.item(0);
            NodeList maxWidthNm = maxWidthElmnt.getChildNodes();
            int MaxWidth = Integer.parseInt(((Node) maxWidthNm.item(0)).getNodeValue());
            NodeList imageQualityElmntLst = fstElmnt.getElementsByTagName("imageQuality");
            Element imageQualityElmnt = (Element) imageQualityElmntLst.item(0);
            NodeList imageQualityNm = imageQualityElmnt.getChildNodes();
            String imageQuality = ((Node) imageQualityNm.item(0)).getNodeValue().trim();

            if (MaxHeight > 9 && MaxWidth > 9 && (imageQuality != "Loss less" || imageQuality != "Lossy")) {

                returnList.add(MaxHeight);
                returnList.add(MaxWidth);
                returnList.add(imageQuality);

                NodeList listOfWaterMarkers = doc.getElementsByTagName("watermarkerProperties");
                ArrayList watermarkingList = new ArrayList();
                for (int s = 0; s < listOfWaterMarkers.getLength(); s++) {
                    ArrayList watermarker = new ArrayList();
                    Node firstWaterMarkerNode = listOfWaterMarkers.item(s);
                    if (firstWaterMarkerNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element WaterMarkerPropertiesElement = (Element) firstWaterMarkerNode;
                        NodeList watermarkTypeList = WaterMarkerPropertiesElement.getElementsByTagName("watermarkType");
                        Element watermarkTypeElement = (Element) watermarkTypeList.item(0);
                        NodeList textWatermarkTypeList = watermarkTypeElement.getChildNodes();
                        String watermarkType = ((Node) textWatermarkTypeList.item(0)).getNodeValue().trim();

                        if (watermarkType.equalsIgnoreCase("TEXT")) {
                            NodeList textStringList = WaterMarkerPropertiesElement.getElementsByTagName("textString");
                            Element textStringElement = (Element) textStringList.item(0);
                            NodeList textTextStringList = textStringElement.getChildNodes();
                            String textString = ((Node) textTextStringList.item(0)).getNodeValue().trim();
                            String textStringArray[] = textString.split("\n");
                            for (int i = 0; i < textStringArray.length; i++) {
                                textStringArray[i] = textStringArray[i].replace("\\", "\\\\");

                            }
                            NodeList fontList = WaterMarkerPropertiesElement.getElementsByTagName("font");
                            Element fontElement = (Element) fontList.item(0);
                            NodeList textFontList = fontElement.getChildNodes();
                            String fontString = ((Node) textFontList.item(0)).getNodeValue().trim();
                            String FontName = fontString.substring(fontString.indexOf("name=") + 5, fontString.indexOf(",", fontString.indexOf("name=") + 5));
                            String FontStyle = fontString.substring(fontString.indexOf("style=") + 6, fontString.indexOf(",", fontString.indexOf("style=") + 5));
                            int FontSize = Integer.parseInt(fontString.substring(fontString.indexOf("size=") + 5, fontString.indexOf("]")));
                            Font font = null;
                            if (FontStyle.equalsIgnoreCase("regular")) {
                                font = new Font(FontName, Font.PLAIN, FontSize);
                            } else if (FontStyle.equalsIgnoreCase("bold")) {
                                font = new Font(FontName, Font.BOLD, FontSize);
                            } else if (FontStyle.equalsIgnoreCase("italic")) {
                                font = new Font(FontName, Font.ITALIC, FontSize);
                            } else if (FontStyle.equalsIgnoreCase("bolditalics")) {
                                font = new Font(FontName, Font.BOLD + Font.ITALIC, FontSize);
                            } else {
                                hasNoError = hasNoError && false;
                            }

                            NodeList colorList = WaterMarkerPropertiesElement.getElementsByTagName("color");
                            Element colorElement = (Element) colorList.item(0);
                            NodeList textColorList = colorElement.getChildNodes();
                            String[] colorString = ((Node) textColorList.item(0)).getNodeValue().trim().split(",");
                            int r = Integer.parseInt(colorString[0].substring(colorString[0].indexOf("=") + 1, colorString[0].length()));
                            int g = Integer.parseInt(colorString[1].substring(colorString[1].indexOf("=") + 1, colorString[1].length()));
                            int b = Integer.parseInt(colorString[2].substring(colorString[2].indexOf("=") + 1, colorString[2].indexOf("]")));
                            Color color = new Color(r, g, b);

                            NodeList fillList = WaterMarkerPropertiesElement.getElementsByTagName("fill");
                            Element fillElement = (Element) fillList.item(0);
                            NodeList textFillList = fillElement.getChildNodes();
                            int fill = Integer.parseInt(((Node) textFillList.item(0)).getNodeValue().trim());

                            NodeList positionSelectedList = WaterMarkerPropertiesElement.getElementsByTagName("positionSelected");
                            Element positionSelectedElement = (Element) positionSelectedList.item(0);
                            NodeList textPositionSelectedList = positionSelectedElement.getChildNodes();
                            String positionSelected = ((Node) textPositionSelectedList.item(0)).getNodeValue().trim();


                            NodeList HSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Hspace");
                            Element HSpaceElement = (Element) HSpaceList.item(0);
                            NodeList textHSpaceList = HSpaceElement.getChildNodes();
                            int Hspace = Integer.parseInt(((Node) textHSpaceList.item(0)).getNodeValue().trim());

                            NodeList VSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Vspace");
                            Element VSpaceElement = (Element) VSpaceList.item(0);
                            NodeList textVSpaceList = VSpaceElement.getChildNodes();
                            int VSpace = Integer.parseInt(((Node) textVSpaceList.item(0)).getNodeValue().trim());
                            int Xposition = 0;
                            int Yposition = 0;

                            if ((positionSelected.equalsIgnoreCase("Horizontal Top Left")
                                    || positionSelected.equalsIgnoreCase("Horizontal Top Center")
                                    || positionSelected.equalsIgnoreCase("Horizontal Top Right")
                                    || positionSelected.equalsIgnoreCase("Horizontal Middle Left")
                                    || positionSelected.equalsIgnoreCase("Horizontal Middle Center")
                                    || positionSelected.equalsIgnoreCase("Horizontal Middle Right")
                                    || positionSelected.equalsIgnoreCase("Horizontal Bottom Left")
                                    || positionSelected.equalsIgnoreCase("Horizontal Bottom Center")
                                    || positionSelected.equalsIgnoreCase("Horizontal Bottom Right")
                                    || positionSelected.equalsIgnoreCase("Horizontal Coustome")
                                    || positionSelected.equalsIgnoreCase("Top-Bottom Diognal")
                                    || positionSelected.equalsIgnoreCase("Bottom-Top Diognal")
                                    || positionSelected.equalsIgnoreCase("Vertical Top Left")
                                    || positionSelected.equalsIgnoreCase("Vertical Top Center")
                                    || positionSelected.equalsIgnoreCase("Vertical Top Right")
                                    || positionSelected.equalsIgnoreCase("Vertical Middle Left")
                                    || positionSelected.equalsIgnoreCase("Vertical Middle Center")
                                    || positionSelected.equalsIgnoreCase("Vertical Middle Right")
                                    || positionSelected.equalsIgnoreCase("Vertical Bottom Left")
                                    || positionSelected.equalsIgnoreCase("Vertical Bottom Center")
                                    || positionSelected.equalsIgnoreCase("Vertical Bottom Right")
                                    || positionSelected.equalsIgnoreCase("Vertical Coustome")) && (fill > 4 && fill < 101)) {

                                if (positionSelected.equalsIgnoreCase("Horizontal Coustome") || positionSelected.equalsIgnoreCase("Vertical Coustome")) {
                                    NodeList XpositionList = WaterMarkerPropertiesElement.getElementsByTagName("Xposition");
                                    Element XpositionElement = (Element) XpositionList.item(0);
                                    NodeList textXpositionList = XpositionElement.getChildNodes();
                                    Xposition = Integer.parseInt(((Node) textXpositionList.item(0)).getNodeValue().trim());

                                    NodeList YpositionList = WaterMarkerPropertiesElement.getElementsByTagName("Yposition");
                                    Element YpositionElement = (Element) YpositionList.item(0);
                                    NodeList textYpositionList = YpositionElement.getChildNodes();
                                    Yposition = Integer.parseInt(((Node) textYpositionList.item(0)).getNodeValue().trim());
                                }
                            } else {
                                hasNoError = hasNoError && false;
                            }
                            watermarker.add(watermarkType);
                            watermarker.add(textStringArray);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(font);
                            watermarker.add(color);
                            watermarker.add(fill);
                            watermarker.add(positionSelected);
                            watermarker.add(Xposition);
                            watermarker.add(Yposition);
                            watermarker.add(Hspace);
                            watermarker.add(VSpace);
                        } else if (watermarkType.equalsIgnoreCase("IMAGE")) {

                            NodeList imagePathList = WaterMarkerPropertiesElement.getElementsByTagName("imagePath");
                            Element imagePathEm = (Element) imagePathList.item(0);
                            NodeList textImagePathList = imagePathEm.getChildNodes();
                            File imagePath = new File(((Node) textImagePathList.item(0)).getNodeValue().trim());

                            NodeList imageScaleList = WaterMarkerPropertiesElement.getElementsByTagName("imageScale");
                            Element imageScaleEm = (Element) imageScaleList.item(0);
                            NodeList textImageScaleList = imageScaleEm.getChildNodes();
                            int imageScale = Integer.parseInt(((Node) textImageScaleList.item(0)).getNodeValue().trim());

                            NodeList fillList = WaterMarkerPropertiesElement.getElementsByTagName("fill");
                            Element fillElement = (Element) fillList.item(0);
                            NodeList textFillList = fillElement.getChildNodes();
                            int fill = Integer.parseInt(((Node) textFillList.item(0)).getNodeValue().trim());

                            NodeList positionSelectedList = WaterMarkerPropertiesElement.getElementsByTagName("positionSelected");
                            Element positionSelectedElement = (Element) positionSelectedList.item(0);
                            NodeList textPositionSelectedList = positionSelectedElement.getChildNodes();
                            String positionSelected = ((Node) textPositionSelectedList.item(0)).getNodeValue().trim();

                            NodeList HSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Hspace");
                            Element HSpaceElement = (Element) HSpaceList.item(0);
                            NodeList textHSpaceList = HSpaceElement.getChildNodes();
                            int Hspace = Integer.parseInt(((Node) textHSpaceList.item(0)).getNodeValue().trim());

                            NodeList VSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Vspace");
                            Element VSpaceElement = (Element) VSpaceList.item(0);
                            NodeList textVSpaceList = VSpaceElement.getChildNodes();
                            int VSpace = Integer.parseInt(((Node) textVSpaceList.item(0)).getNodeValue().trim());

                            int Xposition = 0;
                            int Yposition = 0;

                            if ((positionSelected.equalsIgnoreCase("Top Left")
                                    || positionSelected.equalsIgnoreCase("Top Center")
                                    || positionSelected.equalsIgnoreCase("Top Right")
                                    || positionSelected.equalsIgnoreCase("Middle Left")
                                    || positionSelected.equalsIgnoreCase("Middle Center")
                                    || positionSelected.equalsIgnoreCase("Middle Right")
                                    || positionSelected.equalsIgnoreCase("Bottom Left")
                                    || positionSelected.equalsIgnoreCase("Bottom Center")
                                    || positionSelected.equalsIgnoreCase("Bottom Right")
                                    || positionSelected.equalsIgnoreCase("Coustome"))
                                    && (imagePath.isFile() && fill > 4 && fill < 101 && imageScale > 0 && imageScale < 101)) {
                                if (positionSelected.equalsIgnoreCase("Coustome")) {
                                    NodeList XpositionList = WaterMarkerPropertiesElement.getElementsByTagName("Xposition");
                                    Element XpositionElement = (Element) XpositionList.item(0);
                                    NodeList textXpositionList = XpositionElement.getChildNodes();
                                    Xposition = Integer.parseInt(((Node) textXpositionList.item(0)).getNodeValue().trim());
                                    NodeList YpositionList = WaterMarkerPropertiesElement.getElementsByTagName("Yposition");
                                    Element YpositionElement = (Element) YpositionList.item(0);
                                    NodeList textYpositionList = YpositionElement.getChildNodes();
                                    Yposition = Integer.parseInt(((Node) textYpositionList.item(0)).getNodeValue().trim());
                                }
                            } else {
                                hasNoError = hasNoError && false;
                            }
                            watermarker.add(watermarkType);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(imagePath);
                            watermarker.add(imageScale);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(fill);
                            watermarker.add(positionSelected);
                            watermarker.add(Xposition);
                            watermarker.add(Yposition);
                            watermarker.add(Hspace);
                            watermarker.add(VSpace);
                        } else if (watermarkType.equalsIgnoreCase("SHAPE")) {

                            NodeList shapeList = WaterMarkerPropertiesElement.getElementsByTagName("shape");
                            Element shapeElement = (Element) shapeList.item(0);
                            NodeList textShapeList = shapeElement.getChildNodes();
                            String shape = ((Node) textShapeList.item(0)).getNodeValue().trim();

                            NodeList shapeHeightList = WaterMarkerPropertiesElement.getElementsByTagName("shapeHeight");
                            Element shapeHeightElement = (Element) shapeHeightList.item(0);
                            NodeList textShapeHeightList = shapeHeightElement.getChildNodes();
                            int shapeHeight = Integer.parseInt(((Node) textShapeHeightList.item(0)).getNodeValue());

                            NodeList shapeWidthList = WaterMarkerPropertiesElement.getElementsByTagName("shapeWidth");
                            Element shapeWidthElement = (Element) shapeWidthList.item(0);
                            NodeList textShapeWidthList = shapeWidthElement.getChildNodes();
                            int shapeWidth = Integer.parseInt(((Node) textShapeWidthList.item(0)).getNodeValue());

                            NodeList colorList = WaterMarkerPropertiesElement.getElementsByTagName("color");
                            Element colorElement = (Element) colorList.item(0);
                            NodeList textColorList = colorElement.getChildNodes();
                            String[] colorString = ((Node) textColorList.item(0)).getNodeValue().trim().split(",");
                            int r = Integer.parseInt(colorString[0].substring(colorString[0].indexOf("=") + 1, colorString[0].length()));
                            int g = Integer.parseInt(colorString[1].substring(colorString[1].indexOf("=") + 1, colorString[1].length()));
                            int b = Integer.parseInt(colorString[2].substring(colorString[2].indexOf("=") + 1, colorString[2].indexOf("]")));
                            Color color = new Color(r, g, b);

                            NodeList fillList = WaterMarkerPropertiesElement.getElementsByTagName("fill");
                            Element fillElement = (Element) fillList.item(0);
                            NodeList textFillList = fillElement.getChildNodes();
                            int fill = Integer.parseInt(((Node) textFillList.item(0)).getNodeValue().trim());

                            NodeList positionSelectedList = WaterMarkerPropertiesElement.getElementsByTagName("positionSelected");
                            Element positionSelectedElement = (Element) positionSelectedList.item(0);
                            NodeList textPositionSelectedList = positionSelectedElement.getChildNodes();
                            String positionSelected = ((Node) textPositionSelectedList.item(0)).getNodeValue().trim();

                            NodeList HSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Hspace");
                            Element HSpaceElement = (Element) HSpaceList.item(0);
                            NodeList textHSpaceList = HSpaceElement.getChildNodes();
                            int Hspace = Integer.parseInt(((Node) textHSpaceList.item(0)).getNodeValue().trim());

                            NodeList VSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Vspace");
                            Element VSpaceElement = (Element) VSpaceList.item(0);
                            NodeList textVSpaceList = VSpaceElement.getChildNodes();
                            int VSpace = Integer.parseInt(((Node) textVSpaceList.item(0)).getNodeValue().trim());

                            int Xposition = 0;
                            int Yposition = 0;

                            if ((positionSelected.equalsIgnoreCase("Top Left")
                                    || positionSelected.equalsIgnoreCase("Top Center")
                                    || positionSelected.equalsIgnoreCase("Top Right")
                                    || positionSelected.equalsIgnoreCase("Middle Left")
                                    || positionSelected.equalsIgnoreCase("Middle Center")
                                    || positionSelected.equalsIgnoreCase("Middle Right")
                                    || positionSelected.equalsIgnoreCase("Bottom Left")
                                    || positionSelected.equalsIgnoreCase("Bottom Center")
                                    || positionSelected.equalsIgnoreCase("Bottom Right")
                                    || positionSelected.equalsIgnoreCase("Coustome"))
                                    && ((shapeWidth > 2 && shapeHeight > 2 && fill > 4 && fill < 101)
                                    && (shape.equalsIgnoreCase("RECTRANGLE") || shape.equalsIgnoreCase("OVEL")))) {

                                if (positionSelected.equalsIgnoreCase("Coustome")) {
                                    NodeList XpositionList = WaterMarkerPropertiesElement.getElementsByTagName("Xposition");
                                    Element XpositionElement = (Element) XpositionList.item(0);
                                    NodeList textXpositionList = XpositionElement.getChildNodes();
                                    //validate integer
                                    Xposition = Integer.parseInt(((Node) textXpositionList.item(0)).getNodeValue().trim());

                                    //----
                                    NodeList YpositionList = WaterMarkerPropertiesElement.getElementsByTagName("Yposition");
                                    Element YpositionElement = (Element) YpositionList.item(0);
                                    NodeList textYpositionList = YpositionElement.getChildNodes();
                                    //validate integer
                                    Yposition = Integer.parseInt(((Node) textYpositionList.item(0)).getNodeValue().trim());
                                }
                            } else {
                                hasNoError = hasNoError && false;
                            }

                            watermarker.add(watermarkType);
                            watermarker.add(null);
                            watermarker.add(shape);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(shapeWidth);
                            watermarker.add(shapeHeight);
                            watermarker.add(null);
                            watermarker.add(color);
                            watermarker.add(fill);
                            watermarker.add(positionSelected);
                            watermarker.add(Xposition);
                            watermarker.add(Yposition);
                            watermarker.add(Hspace);
                            watermarker.add(VSpace);
                        } else if (watermarkType.equalsIgnoreCase("CAPTION")) {
                            NodeList fontList = WaterMarkerPropertiesElement.getElementsByTagName("font");
                            Element fontElement = (Element) fontList.item(0);
                            NodeList textFontList = fontElement.getChildNodes();
                            String fontString = ((Node) textFontList.item(0)).getNodeValue().trim();
                            String FontName = fontString.substring(fontString.indexOf("name=") + 5, fontString.indexOf(",", fontString.indexOf("name=") + 5));
                            String FontStyle = fontString.substring(fontString.indexOf("style=") + 6, fontString.indexOf(",", fontString.indexOf("style=") + 5));
                            int FontSize = Integer.parseInt(fontString.substring(fontString.indexOf("size=") + 5, fontString.indexOf("]")));
                            Font font = null;
                            if (FontStyle.equalsIgnoreCase("regular")) {
                                font = new Font(FontName, Font.PLAIN, FontSize);
                            } else if (FontStyle.equalsIgnoreCase("bold")) {
                                font = new Font(FontName, Font.BOLD, FontSize);
                            } else if (FontStyle.equalsIgnoreCase("italic")) {
                                font = new Font(FontName, Font.ITALIC, FontSize);
                            } else if (FontStyle.equalsIgnoreCase("bolditalics")) {
                                font = new Font(FontName, Font.BOLD + Font.ITALIC, FontSize);
                            } else {
                                hasNoError = hasNoError && false;
                            }


                            NodeList colorList = WaterMarkerPropertiesElement.getElementsByTagName("color");
                            Element colorElement = (Element) colorList.item(0);
                            NodeList textColorList = colorElement.getChildNodes();
                            String[] colorString = ((Node) textColorList.item(0)).getNodeValue().trim().split(",");
                            int r = Integer.parseInt(colorString[0].substring(colorString[0].indexOf("=") + 1, colorString[0].length()));
                            int g = Integer.parseInt(colorString[1].substring(colorString[1].indexOf("=") + 1, colorString[1].length()));
                            int b = Integer.parseInt(colorString[2].substring(colorString[2].indexOf("=") + 1, colorString[2].indexOf("]")));
                            Color color = new Color(r, g, b);//**

                            NodeList fillList = WaterMarkerPropertiesElement.getElementsByTagName("fill");
                            Element fillElement = (Element) fillList.item(0);
                            NodeList textFillList = fillElement.getChildNodes();
                            int fill = Integer.parseInt(((Node) textFillList.item(0)).getNodeValue().trim());

                            NodeList positionSelectedList = WaterMarkerPropertiesElement.getElementsByTagName("positionSelected");
                            Element positionSelectedElement = (Element) positionSelectedList.item(0);
                            NodeList textPositionSelectedList = positionSelectedElement.getChildNodes();
                            String positionSelected = ((Node) textPositionSelectedList.item(0)).getNodeValue().trim();


                            NodeList HSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Hspace");
                            Element HSpaceElement = (Element) HSpaceList.item(0);
                            NodeList textHSpaceList = HSpaceElement.getChildNodes();
                            int Hspace = Integer.parseInt(((Node) textHSpaceList.item(0)).getNodeValue().trim());

                            NodeList VSpaceList = WaterMarkerPropertiesElement.getElementsByTagName("Vspace");
                            Element VSpaceElement = (Element) VSpaceList.item(0);
                            NodeList textVSpaceList = VSpaceElement.getChildNodes();
                            int VSpace = Integer.parseInt(((Node) textVSpaceList.item(0)).getNodeValue().trim());
                            if ((positionSelected.equalsIgnoreCase("Top Left")
                                    || positionSelected.equalsIgnoreCase("Top Center")
                                    || positionSelected.equalsIgnoreCase("Top Right")
                                    || positionSelected.equalsIgnoreCase("Middle Left")
                                    || positionSelected.equalsIgnoreCase("Middle Center")
                                    || positionSelected.equalsIgnoreCase("Middle Right")
                                    || positionSelected.equalsIgnoreCase("Bottom Left")
                                    || positionSelected.equalsIgnoreCase("Bottom Center")
                                    || positionSelected.equalsIgnoreCase("Bottom Right")
                                    || positionSelected.equalsIgnoreCase("Coustome"))
                                    && (fill > 4 && fill < 101)) {
                            } else {
                                hasNoError = hasNoError && false;
                            }
                            watermarker.add(watermarkType);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(font);
                            watermarker.add(color);
                            watermarker.add(fill);
                            watermarker.add(positionSelected);
                            watermarker.add(null);
                            watermarker.add(null);
                            watermarker.add(Hspace);
                            watermarker.add(VSpace);
                        } else {// not shape text or image
                            hasNoError = hasNoError && false;
                        }
                    } //end of if clause
                    watermarkingList.add(watermarker);
                }//end of for loop with s var
                returnList.add(watermarkingList);
            } else {// end of if looop of max height , width and Image quality check
                hasNoError = hasNoError && false;
            }

        } catch (Exception e) {
            hasNoError = hasNoError && false;
        }

        returnList.set(0, hasNoError);
        return returnList;
    }
}
