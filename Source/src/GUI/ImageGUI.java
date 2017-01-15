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
package GUI;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import WaterMarker.*;
import WatermarkHelper.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.filechooser.FileFilter;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/*
 * @author Hitesh Shankarrao Jyoti Sathawane
 * Created on January 13, 2010
 * WaterMarker 3.0
 * ImageGUI.java
 * The Class is main class to Open Image Watermarker GUI
 */
public class ImageGUI extends javax.swing.JFrame {

    WaterMarkHelper imageHelper;
    File watermarkImage = null;
    boolean isUpdate = false;
    int updateIndex;

    /**
     *  GUI for Editing ImageGUI Watermarker.
     * @param  Watermark Helper
     * @param  boolean Update if data is to be edited
     * @param  index of the element to be Edited
     */
    public ImageGUI(WaterMarkHelper helper, boolean Update, int Index) {
        imageHelper = helper;
        imageHelper.getMainStatus().setText(imageHelper.getProperty("message.image.open"));
        isUpdate = Update;
        updateIndex = Index;
        initComponents();
    }

    /**
     *  GUI for Adding new ImageGUI Watermarker.
     * @param  Watermark Helper
     */
    public ImageGUI(WaterMarkHelper helper) {
        imageHelper = helper;
        imageHelper.getMainStatus().setText(imageHelper.getProperty("message.image.open"));
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jTextField2 = new ImageNumericTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jTextField3 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        HspaceLabel = new javax.swing.JLabel();
        VspaceLabel = new javax.swing.JLabel();
        jTextField4 = new ImageNumericTextField();
        jTextField5 = new ImageNumericTextField();
        HSpaceTextField = new ImageNumericTextField();
        VSpaceTextField = new ImageNumericTextField();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        ImagePanel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(imageHelper.getProperty("title.image.gui"));
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                frame.dispose();
                imageHelper.setEnable(imageHelper.getMainFrame());
                imageHelper.getMainStatus().setText(imageHelper.getProperty("message.image.close"));
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 18));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Image Watermarker");

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("[?]");
        jLabel2.setToolTipText(imageHelper.getProperty("help.image"));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true), "Select Image & Set Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Palatino Linotype", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
        jPanel7.setForeground(new java.awt.Color(51, 51, 51));

        ImagePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE));
        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Select Image");

        jButton1.setText("Brows");
        jButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browsImage(evt);
            }
        });
        jTextField1.setText("Select Image");
        jTextField1.setEditable(false);
        jTextField1.setToolTipText("Image to be put as watermark");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BrowsInputImage(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Fill");

        jLabel5.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Scale");

        jSlider1.setMinimum(5);
        jSlider1.setValue(100);
        jSlider1.setToolTipText("Set fill of image to be watermarked ");
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                imageFillChangeAction(evt);
            }
        });

        jSlider2.setMinimum(5);
        jSlider2.setValue(100);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                imageScaleChangeAction(evt);
            }
        });


        jTextField3.setText("100%");
        jTextField3.setToolTipText("Image Scale");
        jTextField3.setEditable(false);
        jTextField3.setToolTipText("Fill of image to be watermarked ");

        jTextField8.setText("100%");
        jTextField8.setToolTipText("Image Scale");
        jTextField8.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(6, 6, 6).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1)).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE).addGroup(jPanel2Layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)).addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jButton1)).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(12, 12, 12).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4)).addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel5).addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(10, Short.MAX_VALUE)));
        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true), "Set Position", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Palatino Linotype", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Choose Position");

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("X");

        jLabel10.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Y");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Top Left", "Top Center", "Top Right", "Middle Left", "Middle Center", "Middle Right", "Bottom Left", "Bottom Center", "Bottom Right", "Coustome"}));
        jComboBox1.setToolTipText("Select Position of image to be Watermarked");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkCoustomePositon(evt);
            }
        });

        HspaceLabel.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        HspaceLabel.setForeground(new java.awt.Color(204, 204, 204));
        HspaceLabel.setText("Space from Left Edge");

        VspaceLabel.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        VspaceLabel.setForeground(new java.awt.Color(204, 204, 204));
        VspaceLabel.setText("Space from Top Edge");

        jTextField4.setText("0");
        jTextField4.setEnabled(false);
        jTextField4.setToolTipText("Set X Positon of image to be watermarked");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                XcheckNumber(evt);
            }
        });

        jTextField5.setText("0");
        jTextField5.setEnabled(false);
        jTextField5.setToolTipText("Set Y Positon of image to be watermarked");
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                YcheckNumber(evt);
            }
        });


        HSpaceTextField.setToolTipText("Set Vertical spacing (pix) from image Edge");
        HSpaceTextField.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                HspaceNumberCheck(evt);
            }
        });

        VSpaceTextField.setToolTipText("Set Horizontal spacing (pix) from image Edge");
        VSpaceTextField.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                VspaceNumberCheck(evt);
            }
        });



        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel8).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel9)).addGap(33, 33, 33).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jTextField4, 0, 0, Short.MAX_VALUE).addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGap(96, 96, 96).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(VspaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(HspaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(VSpaceTextField).addComponent(HSpaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(16, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel9).addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel10))).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(HSpaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(VSpaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(HspaceLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(VspaceLabel))))).addContainerGap(10, Short.MAX_VALUE)));
        jButton4.setText("Cancel");
        jButton4.setToolTipText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel(evt);
            }
        });

        jButton3.setText("Add");
        jButton3.setToolTipText("Add image as a watermarker");
        jButton3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddImageWatermarker(evt);
            }
        });

        if (isUpdate) {
            ArrayList EditList = (ArrayList) imageHelper.getWaterMarkerList().get(updateIndex);
            File imagePath = (File) EditList.get(3);
            watermarkImage = imagePath;
            jTextField1.setText(watermarkImage.getPath());
            int imageScale = Integer.parseInt(EditList.get(4).toString());
            int Fill = Integer.parseInt(EditList.get(9).toString());
            String imagePosition = EditList.get(10).toString();
            String X = EditList.get(11).toString();
            String Y = EditList.get(12).toString();
            String hSpace = EditList.get(13).toString();
            String vSpace = EditList.get(14).toString();
            jSlider2.setValue(imageScale);
            jSlider1.setValue(Fill);
            jComboBox1.setSelectedItem(imagePosition);
            jTextField4.setText(X);
            jTextField5.setText(Y);
            HSpaceTextField.setText(hSpace);
            VSpaceTextField.setText(vSpace);
            jButton3.setText("Update");
            jTextField3.setText(EditList.get(9).toString() + "%");
            jTextField8.setText(EditList.get(4).toString() + "%");
            BufferedImage image = imageHelper.getPreview(watermarkImage);
            RescaleImage previewImage = new RescaleImage();
            BufferedImage result = previewImage.RescaleImage(imageHelper, image, 120, 160);
            ImageIcon i2i = new ImageIcon(result);
            ImagePanel.setIcon(i2i);
            jPanel7.repaint();
            jPanel7.revalidate();

        } else {
            jTextField4.setText("0");
            jTextField5.setText("0");
            HSpaceTextField.setText("0");
            VSpaceTextField.setText("0");
            jButton3.setText("Add");
            jTextField2.setText("100%");
            jTextField8.setText("100%");
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel1).addGap(132, 132, 132).addComponent(jLabel2))).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(87, 87, 87).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE).addComponent(jButton4).addGap(77, 77, 77)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton4).addComponent(jButton3)).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 495) / 2, (screenSize.height - 361) / 2, 495, 361);
    }// </editor-fold>

    /**
     * Brows ImageGUI for watermarking
     */
    private void BrowsInputImage(java.awt.event.MouseEvent evt) {
        browsImageInput();
    }

    /**
     * Brows ImageGUI for watermarking
     */
    private void browsImage(java.awt.event.ActionEvent evt) {
        browsImageInput();
    }

    /**
     * Brows ImageGUI for watermarking
     */
    public void browsImageInput() {
        jFileChooser1 = new javax.swing.JFileChooser(".");
        ImagePreviewPanel preview = new ImagePreviewPanel();
        jFileChooser1.setAccessory(preview);
        jFileChooser1.addPropertyChangeListener(preview);
        FileFilter filter1 = new ImageExtensionFileFilter(imageHelper.getProperty("constants.jfilechooser.filter"), imageHelper.getImageFormats());
        jFileChooser1.setFileFilter(filter1);
        jFileChooser1.setMultiSelectionEnabled(false);
        int option = jFileChooser1.showOpenDialog(ImageGUI.this);
        if (option == jFileChooser1.APPROVE_OPTION) {
            try {
                watermarkImage = jFileChooser1.getSelectedFile();
                jTextField1.setText(watermarkImage.getPath());
                BufferedImage image = imageHelper.getPreview(watermarkImage);
                RescaleImage previewImage = new RescaleImage();
                BufferedImage result = previewImage.RescaleImage(imageHelper, image, 90, 120);
                ImageIcon ii = new ImageIcon(result);
                ImagePanel.setIcon(ii);
                jPanel7.repaint();
                jPanel7.revalidate();
            } catch (Exception ex) {
                Logger.getLogger(ImageGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Sets value of ImageGUI Fill in respective Textfield for display of value
     * @param  Slider Change Even
     * 
     */
    private void imageFillChangeAction(javax.swing.event.ChangeEvent evt) {
        imageHelper.sliderChangeAction(jSlider1, jTextField3);
    }

    /**
     * Sets value of ImageGUI scale in respective Textfield for display of value
     * @param  Slider Change Even
     */
    private void imageScaleChangeAction(javax.swing.event.ChangeEvent evt) {
        imageHelper.sliderChangeAction(jSlider2, jTextField8);
    }

    /**
     * Check position ImageGUI watermark and change Display labels accordingly
     * enable and disable of textfield components
     * @param  Combobox change selected event
     * 
     */
    private void checkCoustomePositon(java.awt.event.ActionEvent evt) {
        if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Coustome")) {
            jTextField4.setEnabled(true);
            jTextField5.setEnabled(true);
            HSpaceTextField.setEnabled(false);
            HSpaceTextField.setText("0");
            VSpaceTextField.setEnabled(false);
            VSpaceTextField.setText("0");
            HspaceLabel.setText("Not Applicable");
            VspaceLabel.setText("Not Applicable");
        } else {
            jTextField4.setEnabled(false);
            jTextField4.setText("0");
            jTextField5.setEnabled(false);
            jTextField5.setText("0");
            if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Top Left")) {
                HspaceLabel.setText("Space from Left Edge");
                VspaceLabel.setText("Space from Top Edge");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
                HSpaceTextField.setEnabled(true);
                HSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Top Center")) {
                HspaceLabel.setText("Not Applicable");
                HSpaceTextField.setEnabled(false);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Space from Top Edge");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Top Right")) {
                HspaceLabel.setText("Space from Right Edge");
                HSpaceTextField.setEnabled(true);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Space from Top Edge");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Middle Left")) {
                HspaceLabel.setText("Space from Left Edge");
                HSpaceTextField.setEnabled(true);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Not Applicable");
                VSpaceTextField.setEnabled(false);
                VSpaceTextField.setText("0");

            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Middle Center")) {
                HspaceLabel.setText("Not Applicable");
                HSpaceTextField.setEnabled(false);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Not Applicable");
                VSpaceTextField.setEnabled(false);
                VSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Middle Right")) {
                HspaceLabel.setText("Space from Right Edge");
                HSpaceTextField.setEnabled(true);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Not Applicable");
                VSpaceTextField.setEnabled(false);
                VSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Bottom Left")) {
                HspaceLabel.setText("Space from Left Edge");
                HSpaceTextField.setEnabled(true);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Space from Bottom Edge");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Bottom Center")) {
                HspaceLabel.setText("Not Applicable");
                HSpaceTextField.setEnabled(false);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Space from Bottom Edge");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (jComboBox1.getSelectedItem().toString().equalsIgnoreCase("Bottom Right")) {
                HspaceLabel.setText("Space from Right Edge");
                HSpaceTextField.setEnabled(true);
                HSpaceTextField.setText("0");
                VspaceLabel.setText("Space from Bottom Edge");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            }
        }
    }

    /**
     * Cancel action for add or edit of ImageGUI Watermarker.
     *
     */
    private void Cancel(java.awt.event.ActionEvent evt) {
        this.dispose();
        imageHelper.setEnable(imageHelper.getMainFrame());
        imageHelper.getMainStatus().setText(imageHelper.getProperty("message.image.close"));
    }

    /**
     * Validate data input data and add/update ImageGUI Watermarker
     *
     * @param  Button click Evnet
     */
    private void AddImageWatermarker(java.awt.event.ActionEvent evt) {
        if (watermarkImage != null) {
            if (imageHelper.checkExtension(watermarkImage.getName())) {
                ArrayList imageData = new ArrayList();
                imageData.add("IMAGE");
                imageData.add(null);
                imageData.add(null);
                imageData.add(watermarkImage);
                imageData.add(jSlider2.getValue());
                imageData.add(null);
                imageData.add(null);
                imageData.add(null);
                imageData.add(null);
                imageData.add(jSlider1.getValue());
                imageData.add(jComboBox1.getSelectedItem().toString());
                imageData.add(jTextField4.getText());
                imageData.add(jTextField5.getText());
                imageData.add(HSpaceTextField.getText());
                imageData.add(VSpaceTextField.getText());
                if (isUpdate) {
                    imageHelper.getWaterMarkerList().set(updateIndex, imageData);
                    imageHelper.Info("Updating Image Watermarker at Index:" + updateIndex + " with Parameters List: " + imageData);
                } else {
                    imageHelper.getWaterMarkerList().add(imageData);
                    imageHelper.Info("Adding Image Watermarker with Parameters List: " + imageData);
                }
                this.dispose();
                imageHelper.setEnable(imageHelper.getMainFrame());
                imageHelper.getMainWaterMarksDetails().removeAll();
                imageHelper.setWaterMarkDetails();
                imageHelper.getMainStatus().setText(imageHelper.getProperty("message.image.success"));
            } else {
                javax.swing.JOptionPane.showMessageDialog(jPanel1, imageHelper.getProperty("message.warning.imageFilter"));
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(jPanel1, imageHelper.getProperty("message.warning.selectImage"));
        }
    }

    /**
     * X position validation .
     *
     * @param Focus loose event
     */
    private void XcheckNumber(java.awt.event.FocusEvent evt) {
        imageHelper.checkNumber(jTextField4);
    }

    /**
     * Y position validation .
     *
     * @param Focus loose event
     */
    private void YcheckNumber(java.awt.event.FocusEvent evt) {
        imageHelper.checkNumber(jTextField5);
    }

    /**
     * H Space Validation
     */
    private void HspaceNumberCheck(java.awt.event.FocusEvent evt) {
        imageHelper.checkNumber(HSpaceTextField);

    }

    /**
     *V Space Validation
     */
    private void VspaceNumberCheck(java.awt.event.FocusEvent evt) {
        imageHelper.checkNumber(VSpaceTextField);
    }
    // Variables declaration - do not modify
    private javax.swing.JLabel ImagePanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel HspaceLabel;
    private javax.swing.JLabel VspaceLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField HSpaceTextField;
    private javax.swing.JTextField VSpaceTextField;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration
}

class ImageBackgroundPanel extends JPanel {

    BufferedImage image;

    ImageBackgroundPanel(BufferedImage image) {
        this.image = image;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

class ImageExtensionFileFilter extends FileFilter {

    String description;
    String extensions[];

    public ImageExtensionFileFilter(String description, String extension) {
        this(description, new String[]{extension});
    }

    public ImageExtensionFileFilter(String description, String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String path = file.getAbsolutePath().toLowerCase();
            for (int i = 0, n = extensions.length; i < n; i++) {
                String extension = extensions[i];
                if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
                    return true;
                }
            }
        }
        return false;
    }
}

@SuppressWarnings("serial")
class ImageNumericTextField extends JTextField {

    @Override
    protected Document createDefaultModel() {
        return new NumericDocument();
    }

    private static class NumericDocument extends PlainDocument {
        // The regular expression to match input against (zero or more digits)

        private final static Pattern DIGITS = Pattern.compile("\\d*");

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // Only insert the text if it matches the regular expression
            if (str != null && DIGITS.matcher(str).matches()) {
                super.insertString(offs, str, a);
            }
        }
    }
}

