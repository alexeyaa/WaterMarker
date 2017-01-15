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

import GUI.CaptionGUI;
import GUI.ImageGUI;
import GUI.ShapeGUI;
import GUI.TextGUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on January 13, 2010
 * WaterMarker 3.0
 * WaterMarkHelper.java
 * This Class Contains helper methods and setter and getters for important parameters
 */
public class WaterMarkHelper {

    private String imageOrImagesSelectBox = null;
    private File outputImageFolderPath = null;
    private File inputParentFolderPath = null;
    private ArrayList inputFiles = null;
    private ArrayList inputFilesCaption = null;
    private int completeImageList = 0;
    private double maxImageHeight = 0.0;
    private double maxImageWidth = 0.0;
    private double imageQuality = 1;
    private ArrayList waterMarkerList = new ArrayList();
    private javax.swing.JFrame mainFrame;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainStatus;
    private javax.swing.JPanel mainWaterMarksDetails;
    private javax.swing.JProgressBar mainProgressBar;
    private Thread Worker;
    private boolean pause;
    private boolean stop;
    private boolean losseless;
    private javax.swing.JTextField inPutImageTextField;
    private javax.swing.JTextField outPutImageTextField;
    private javax.swing.JTextField imageQualityTextField;
    private String outputFolderName;
    private String ReportFileName = "";
    String extensions;
    private String[] imageFormats;
    Properties prop;
    private static Logger logger = Logger.getLogger("WatermarkLogger");

    public void setExtensions() {
        extensions = getProperty("constants.supported.extensions");
    }

    public void setImageFormats() {
        imageFormats = extensions.split(getProperty("constants.supported.extensions.seperator"));
    }

    /**
     * Check if the value is Integer or not
     * @param String S
     * @return boolean if value is integer or not
     */
    public boolean checkInteger(String s) {
        try {
            Integer.parseInt(s.trim());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Check extensions of file
     * @param String extension
     * @return boolean if extens are ok
     */
    public boolean checkExtension(String FilePath) {
        String extension = FilePath.substring(FilePath.lastIndexOf(".") + 1).toUpperCase();
        ArrayList ExtensionList = new ArrayList(Arrays.asList(extensions.split(getProperty("constants.supported.extensions.seperator"))));
        if (ExtensionList.contains(extension)) {
            return true;
        } else {
            return false;
        }
    }

    public javax.swing.border.TitledBorder panelBorder(String Title) {
        javax.swing.border.LineBorder b = new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true);
        return new javax.swing.border.TitledBorder(b, Title, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, new java.awt.Font("Palatino Linotype", 0, 11), new java.awt.Color(0, 153, 255));
    }

    public void createReportFile() {
        try {
            MakeOutputDirectory();
            ReportFileName = getOutputImageFolderPath().getPath() + File.separator + getOutputFolderName() + File.separator + "Report_" + getDate() + ".txt";
            File f = new File(ReportFileName);
            Writer output = null;
            output = new BufferedWriter(new FileWriter(f));
            output.write("\t\t\t\t\tWatermarking Reort\n\nNumber of Files To Watermark:\t" + inputFiles.size() + "\n");
            output.close();
        } catch (IOException ex) {
        }

    }

    public void setLogParam() {
        try {
            logger.setUseParentHandlers(false);
            File F = new File("Log");
            if (!F.isDirectory()) {
                F.mkdir();
            }
            FileHandler fh = new FileHandler("Log" + File.separator + "Watermarker_" + getDate() + "%g.log", Integer.parseInt(getProperty("constants.log.limit")), Integer.parseInt(getProperty("constants.log.count")));
            ConsoleHandler ch = new ConsoleHandler();
            ch.setFormatter(new Formatter() {

                public String format(LogRecord record) {
                    return getDate() + "\t" + record.getLevel() + "     \t:\t" + record.getMessage() + "\n";
                }
            });
            fh.setFormatter(new Formatter() {

                public String format(LogRecord record) {
                    return getDate() + "\t" + record.getLevel() + "     \t:\t" + record.getMessage() + "\n";
                }
            });

            logger.addHandler(ch);
            logger.addHandler(fh);
        } catch (Exception ex) {
        }
    }

    public void Info(String log) {
        logger.info(log);
    }

    public void warning(String log) {
        logger.warning(log);
    }

    public void severe(String log) {
        logger.severe(log);
    }

    public void appendReport(String appendText) {
        File f = new File(ReportFileName);
        if (f.exists()) {
            try {
                long fileLength = f.length();
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                raf.seek(fileLength);
                raf.writeBytes(appendText);
                raf.close();
            } catch (Exception ex) {
            }
        }
    }

    public BufferedImage getPreview(File file) {
        String inputFileName = file.getPath();
        String extension = inputFileName.substring(inputFileName.lastIndexOf("."));
        BufferedImage image = null;
        if (extension.equalsIgnoreCase(".tiff") || extension.equalsIgnoreCase(".tif") || extension.equalsIgnoreCase(".dng") || extension.equalsIgnoreCase(".nef")) {
            RenderedImage images = JAI.create("fileload", file.getPath());
            image = convertRenderedImage(images);
        } else if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".jpe") || extension.equalsIgnoreCase(".jfif") || extension.equalsIgnoreCase(".bmp") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".gif")) {
            try {
                image = javax.imageio.ImageIO.read(new File(file.getPath()));
            } catch (IOException ex) {
            }
        } else {
            try {
                URL is = getClass().getResource("/image/blank.png");
                image = ImageIO.read(is);
            } catch (IOException ex) {
            }

        }
        return image;
    }

    public void readPropertyFile() {
        try {
            InputStream confFile = getClass().getResourceAsStream("/ApplicationResource/ApplicationResource.properties");
            prop = new Properties();
            prop.load(confFile);

        } catch (Exception ex) {
        }
    }

    public String getProperty(String key) {
        return (String) prop.get(key);
    }

    public void checkNumber(JTextField textField) {
        String XVal = textField.getText();
        if (!checkInteger(XVal)) {
            textField.setText("0");
        }
    }

    /**
     * convert rendering image to bufferimage
     * @param RenderedImage
     * @return bufferimage
     */
    public BufferedImage convertRenderedImage(RenderedImage img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        ColorModel cm = img.getColorModel();
        int width = img.getWidth();
        int height = img.getHeight();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        Hashtable properties = new Hashtable();
        String[] keys = img.getPropertyNames();
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                properties.put(keys[i], img.getProperty(keys[i]));
            }
        }
        BufferedImage result = new BufferedImage(cm, raster, isAlphaPremultiplied, properties);
        img.copyData(raster);
        return result;
    }

    /**
     * scaling of image
     * @param height
     * @param width
     * @param MaxHeight
     * @param MaxWidth
     * @return String containging saciled height and width
     */
    public String imageSize(double height, double width, double MaxHeight, double MaxWidth) {
        double x = 0.00000000;
        double y = 0.00000000;
        double widthreturn = 0;
        double hightreturn = 0;

        if (width > height) {
            x = (MaxWidth / width);
            height = height * x;
            hightreturn = (int) Math.ceil(height);
            widthreturn = MaxWidth;
            if (hightreturn > MaxHeight) {
                y = MaxHeight / height;
                width = MaxWidth * y;
                widthreturn = (int) Math.ceil(width);
                hightreturn = MaxHeight;
            }

        } else {
            y = MaxHeight / height;
            width = width * y;
            widthreturn = (int) Math.ceil(width);
            hightreturn = MaxHeight;
            if (widthreturn > MaxWidth) {
                x = MaxWidth / width;
                height = MaxHeight * x;
                hightreturn = (int) Math.ceil(height);
                widthreturn = MaxWidth;
            }
        }
        return hightreturn + "##@@##" + widthreturn;
    }

    /**
     * get current date
     * 
     * @return String containging current date time
     */
    public String getDate() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(getProperty("constants.dataFormat"));
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }

    /**
     * make output directory
     * 
     */
    public void MakeOutputDirectory() {
        String outputPath = outputImageFolderPath.getPath() + File.separator + outputFolderName;
        File OutputFile = new File(outputPath);
        if (!OutputFile.isDirectory()) {
            OutputFile.mkdir();
        }
    }

    /**
     * make output folder structure
     * 
     */
    public void MakeFolderStructure(String path) {
        String outputPath = outputImageFolderPath.getPath() + File.separator + outputFolderName;
        File OutputFile = new File(outputPath);
        if (!OutputFile.isDirectory()) {
            OutputFile.mkdir();
        }
        String outPutPath = path.replace(File.separator, "@@##@");
        String folderNames[] = outPutPath.split("@@##@");
        String SplitOutput = outputPath.replace(File.separator, "@@##@");
        String outPutFolderName = outputPath;
        File file;
        for (int i = SplitOutput.split("@@##@").length; i < folderNames.length - 1; i++) {
            outPutFolderName = outPutFolderName + File.separator + folderNames[i];
            file = new File(outPutFolderName);
            if (!file.isDirectory()) {
                file.mkdir();
            }
        }

    }

    /**
     * enable required component 
     * 
     * @param panel
     */
    public void setEnable(javax.swing.JPanel panel) {
        Component[] com = panel.getComponents();
        for (int a = 0; a < com.length; a++) {
            com[a].setEnabled(true);
        }
    }

    public void setDisable(JFrame f) {
        f.setEnabled(false);
    }

    public void setEnable(JFrame f) {
        f.setEnabled(true);
        f.show();
    }

    /**
     * Set the content of panel with current settings for easy display
     * Edit and delete
     *
     */
    public void setWaterMarkDetails() {
        int i = 0;
        javax.swing.JTextArea WatermarkerTextArea[] = new JTextArea[waterMarkerList.size()];
        javax.swing.JButton Delete[] = new JButton[waterMarkerList.size()];
        javax.swing.JButton Edite[] = new JButton[waterMarkerList.size()];
        JPanel jChangePanel[] = new JPanel[waterMarkerList.size()];
        JPanel jpanel[] = new JPanel[waterMarkerList.size()];

        mainWaterMarksDetails.setLayout(new BoxLayout(mainWaterMarksDetails, BoxLayout.Y_AXIS));
        for (Iterator it = waterMarkerList.iterator(); it.hasNext();) {
            ArrayList list = (ArrayList) it.next();
            int count = i + 1;
            String waterMarkDetails = "";
            waterMarkDetails = waterMarkDetails + "\n" + count + "] \n";
            if (list.get(0).toString().equalsIgnoreCase("TEXT")) {
                waterMarkDetails = waterMarkDetails + "Type: " + list.get(0) + " \n";
                String WatermarkingText[] = (String[]) list.get(1);
                String textString = "";
                for (int r = 0; r < WatermarkingText.length; r++) {
                    textString = textString + WatermarkingText[r] + "\n";
                }
                waterMarkDetails = waterMarkDetails + "Text to Watermark: \n" + textString;
                Font font = (Font) list.get(7);
                waterMarkDetails = waterMarkDetails + "Font Name : " + font.getFontName() + " Font Style :" + font.getStyle() + " Font Size : " + font.getSize() + " \n";
                Color color = (Color) list.get(8);
                waterMarkDetails = waterMarkDetails + "Colour: Red : " + color.getRed() + " Green : " + color.getGreen() + " Blue : " + color.getBlue() + " \n";
            } else if (list.get(0).toString().equalsIgnoreCase("SHAPE")) {
                waterMarkDetails = waterMarkDetails + "Type: " + list.get(0) + " \n";
                waterMarkDetails = waterMarkDetails + "Shape: " + list.get(2) + " \n";
                waterMarkDetails = waterMarkDetails + "Shape Height: " + list.get(6) + " \n";
                waterMarkDetails = waterMarkDetails + "Shape Width: " + list.get(5) + " \n";
                Color color = (Color) list.get(8);
                waterMarkDetails = waterMarkDetails + "Colour: R " + color.getRed() + " G " + color.getGreen() + " B " + color.getBlue() + " \n";
            } else if (list.get(0).toString().equalsIgnoreCase("CAPTION")) {
                waterMarkDetails = waterMarkDetails + "Type: " + list.get(0) + " \n";
                Font font = (Font) list.get(7);
                waterMarkDetails = waterMarkDetails + "Font Name : " + font.getFontName() + " Font Style :" + font.getStyle() + " Font Size : " + font.getSize() + " \n";
                Color color = (Color) list.get(8);
                waterMarkDetails = waterMarkDetails + "Colour: Red : " + color.getRed() + " Green : " + color.getGreen() + " Blue : " + color.getBlue() + " \n";

            } else if (list.get(0).toString().equalsIgnoreCase("IMAGE")) {
                waterMarkDetails = waterMarkDetails + "Type: " + list.get(0) + " \n";
                waterMarkDetails = waterMarkDetails + "Image Path: " + list.get(3) + " \n";
                waterMarkDetails = waterMarkDetails + "Image Scale: " + list.get(4) + " \n";
            }
            waterMarkDetails = waterMarkDetails + "Fill: " + list.get(9) + " \n";
            waterMarkDetails = waterMarkDetails + "Position: " + list.get(10) + " \n";
            if (list.get(10).toString().equalsIgnoreCase("COUSTOME")) {
                waterMarkDetails = waterMarkDetails + "X position: " + list.get(11) + " \n";
                waterMarkDetails = waterMarkDetails + "Y position: " + list.get(12) + " \n";
            } else {
                if (Integer.parseInt(list.get(13).toString()) != 0) {
                    waterMarkDetails = waterMarkDetails + "H Spacing: " + list.get(13) + " \n";
                }
                if (Integer.parseInt(list.get(14).toString()) != 0) {
                    waterMarkDetails = waterMarkDetails + "V Spacing: " + list.get(14) + " \n";
                }
            }

            String lineCounter[] = waterMarkDetails.split("\n");

            Delete[i] = new JButton();
            Edite[i] = new JButton();
            WatermarkerTextArea[i] = new JTextArea();

            jChangePanel[i] = new JPanel();
            jChangePanel[i].setBackground(new java.awt.Color(51, 51, 51));
            jChangePanel[i].setLayout(new BoxLayout(jChangePanel[i], BoxLayout.Y_AXIS));

            jpanel[i] = new JPanel();
            jpanel[i].setBackground(new java.awt.Color(51, 51, 51));
            jpanel[i].setLayout(new BoxLayout(jpanel[i], BoxLayout.X_AXIS));

            final int index = i;

            Delete[i].setText("Delete");
            Delete[i].addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    waterMarkerList.remove(index);
                    mainWaterMarksDetails.removeAll();
                    mainWaterMarksDetails.revalidate();
                    mainWaterMarksDetails.repaint();
                    setWaterMarkDetails();
                    Info("Watermarker at Index: " + index + " removed");
                }
            });

            Edite[i].setText(" Edite ");
            Edite[i].addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Component[] compo = mainPanel.getComponents();
                    for (int a = 0; a < compo.length; a++) {
                        compo[a].setEnabled(false);
                    }
                    ArrayList EditList = (ArrayList) waterMarkerList.get(index);
                    if (EditList.get(0).toString().equalsIgnoreCase("TEXT")) {
                        java.awt.EventQueue.invokeLater(new Runnable() {

                            public void run() {
                                new TextGUI(WaterMarkHelper.this, true, index).setVisible(true);
                            }
                        });

                    } else if (EditList.get(0).toString().equalsIgnoreCase("IMAGE")) {
                        java.awt.EventQueue.invokeLater(new Runnable() {

                            public void run() {
                                new ImageGUI(WaterMarkHelper.this, true, index).setVisible(true);
                            }
                        });
                    } else if (EditList.get(0).toString().equalsIgnoreCase("SHAPE")) {
                        java.awt.EventQueue.invokeLater(new Runnable() {

                            public void run() {
                                new ShapeGUI(WaterMarkHelper.this, true, index).setVisible(true);
                            }
                        });
                    } else if (EditList.get(0).toString().equalsIgnoreCase("CAPTION")) {
                        java.awt.EventQueue.invokeLater(new Runnable() {

                            public void run() {
                                new CaptionGUI(WaterMarkHelper.this, true, index).setVisible(true);
                            }
                        });
                    }
                }
            });



            WatermarkerTextArea[i].setForeground(new java.awt.Color(255, 153, 0));
            WatermarkerTextArea[i].setBackground(new java.awt.Color(51, 51, 51));
            WatermarkerTextArea[i].setDisabledTextColor(new java.awt.Color(255, 153, 0));
            WatermarkerTextArea[i].setEnabled(false);
            WatermarkerTextArea[i].setRows(lineCounter.length);
            WatermarkerTextArea[i].setText(waterMarkDetails);


            JSeparator jSeparator1 = new JSeparator(1);
            jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

            JSeparator jSeparator3 = new JSeparator(0);
            jSeparator3.setForeground(new java.awt.Color(51, 51, 51));

            jChangePanel[i].add(Edite[i]);
            jChangePanel[i].add(Box.createRigidArea(new Dimension(0, 10)));
            jChangePanel[i].add(Delete[i]);

            jpanel[i].add(Box.createRigidArea(new Dimension(10, 0)));
            jpanel[i].add(jChangePanel[i]);
            jpanel[i].add(Box.createRigidArea(new Dimension(10, 0)));
            jpanel[i].add(jSeparator1);
            jpanel[i].add(Box.createRigidArea(new Dimension(10, 0)));
            jpanel[i].add(WatermarkerTextArea[i]);
            jpanel[i].add(Box.createRigidArea(new Dimension(10, 0)));

            JSeparator jSeparator2 = new JSeparator(0);
            jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

            mainWaterMarksDetails.add(Box.createRigidArea(new Dimension(10, 0)));
            mainWaterMarksDetails.add(jpanel[i]);
            mainWaterMarksDetails.validate();
            mainWaterMarksDetails.add(jSeparator2);
            i++;
        }
    }

    public void sliderChangeAction(JSlider jSlider, JTextField jTextField) {
        jSlider.setToolTipText(jSlider.getValue() + "%");
        jTextField.setText(String.valueOf(jSlider.getValue()) + "%");
    }

    public File getInputParentFolderPath() {
        return inputParentFolderPath;
    }

    public void setInputParentFolderPath(File inputParentFolderPath) {
        this.inputParentFolderPath = inputParentFolderPath;
    }

    public int getCompleteImageList() {
        return completeImageList;
    }

    public String[] getImageFormats() {
        return imageFormats;
    }

    public void setCompleteImageList(int completeImageList) {
        this.completeImageList = completeImageList;
    }

    public void setInputFiles(ArrayList inputFiles) {
        this.inputFiles = inputFiles;
    }

    public ArrayList getInputFiles() {
        return inputFiles;
    }

    public void setOutputFolderName(String outputFolderName) {
        this.outputFolderName = outputFolderName;
    }

    public String getOutputFolderName() {
        return outputFolderName;
    }

    public void setImageQualityTextField(JTextField imageQualityTextField) {
        this.imageQualityTextField = imageQualityTextField;
    }

    public void setInPutImageTextField(JTextField inPutImageTextField) {
        this.inPutImageTextField = inPutImageTextField;
    }

    public void setOutPutImageTextField(JTextField outPutImageTextField) {
        this.outPutImageTextField = outPutImageTextField;
    }

    public JTextField getImageQualityTextField() {
        return imageQualityTextField;
    }

    public JTextField getInPutImageTextField() {
        return inPutImageTextField;
    }

    public JTextField getOutPutImageTextField() {
        return outPutImageTextField;
    }

    public boolean isPause() {
        return pause;
    }

    public boolean isStop() {
        return stop;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Thread getWorker() {
        return Worker;
    }

    public void setWorker(Thread Worker) {
        this.Worker = Worker;
    }

    public String getImageOrImagesSelectBox() {
        return imageOrImagesSelectBox;
    }

    public double getImageQuality() {
        return imageQuality;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JProgressBar getMainProgressBar() {
        return mainProgressBar;
    }

    public JLabel getMainStatus() {
        return mainStatus;
    }

    public JPanel getMainWaterMarksDetails() {
        return mainWaterMarksDetails;
    }

    public double getMaxImageHeight() {
        return maxImageHeight;
    }

    public double getMaxImageWidth() {
        return maxImageWidth;
    }

    public File getOutputImageFolderPath() {
        return outputImageFolderPath;
    }

    public ArrayList getWaterMarkerList() {
        return waterMarkerList;
    }

    public void setImageOrImagesSelectBox(String imageOrImagesSelectBox) {
        this.imageOrImagesSelectBox = imageOrImagesSelectBox;
    }

    public void setImageQuality(double imageQuality) {
        this.imageQuality = imageQuality;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setMainProgressBar(JProgressBar mainProgressBar) {
        this.mainProgressBar = mainProgressBar;
    }

    public void setMainStatus(JLabel mainStatus) {
        this.mainStatus = mainStatus;
    }

    public void setMainWaterMarksDetails(JPanel mainWaterMarksDetails) {
        this.mainWaterMarksDetails = mainWaterMarksDetails;
    }

    public void setMaxImageHeight(double maxImageHeight) {
        this.maxImageHeight = maxImageHeight;
    }

    public void setMaxImageWidth(double maxImageWidth) {
        this.maxImageWidth = maxImageWidth;
    }

    public void setOutputImageFolderPath(File outputImageFolderPath) {
        this.outputImageFolderPath = outputImageFolderPath;
    }

    public void setWaterMarkerList(ArrayList waterMarkerList) {
        this.waterMarkerList = waterMarkerList;
    }

    public ArrayList getInputFilesCaption() {
        return inputFilesCaption;
    }

    public void setInputFilesCaption(ArrayList inputFilesCaption) {
        this.inputFilesCaption = inputFilesCaption;
    }

    public boolean isLosseless() {
        return losseless;
    }

    public void setLosseless(boolean losseless) {
        this.losseless = losseless;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
