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

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import WatermarkHelper.*;
import java.util.ArrayList;
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
 * TextGUI.java
 *  The Class is main class to Open TextWatermarker GUI
 */
public class TextGUI extends javax.swing.JFrame {

    WaterMarkHelper textHelper;
    Font textFont = null;
    Color textColor = new java.awt.Color(0, 0, 0);
    boolean isUpdate = false;
    int updateIndex;

    /** Creates new form TextGUI */
    public TextGUI(WaterMarkHelper helper, boolean Update, int Index) {
        textHelper = helper;
        textHelper.getMainStatus().setText(textHelper.getProperty("message.text.open"));
        isUpdate = Update;
        updateIndex = Index;
        initComponents();
    }

    public TextGUI(WaterMarkHelper helper) {
        textHelper = helper;
        textHelper.getMainStatus().setText(textHelper.getProperty("message.text.open"));
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jSlider1 = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        HspaceLabel = new javax.swing.JLabel();
        VspaceLabel = new javax.swing.JLabel();
        jTextField2 = new TextNumericTextField();
        jTextField3 = new TextNumericTextField();
        HspaceTextField = new TextNumericTextField();
        VSpaceTextField = new TextNumericTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        textFont = new Font("Arial", Font.BOLD, 12);

        setTitle(textHelper.getProperty("title.text.gui"));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setResizable(false);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent evt) {
                Frame frame = (Frame) evt.getSource();
                frame.dispose();
                textHelper.setEnable(textHelper.getMainFrame());
                textHelper.getMainStatus().setText(textHelper.getProperty("message.text.close"));
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 18));
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Text Watermarker");

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("[?]");
        jLabel2.setToolTipText(textHelper.getProperty("help.text"));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true), "Set details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Palatino Linotype", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Set Font");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Choose Color");

        jLabel6.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("Set Fill");


        jButton1.setText("Select Font");
        jButton1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFontAction(evt);
            }
        });

        jButton2.setText("Choose Colour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseColourAction(evt);
            }
        });

        jTextField1.setText("100%");
        jTextField1.setEditable(false);
        jTextField1.setToolTipText("Fill/Opacity");

        jSlider1.setMinimum(5);
        jSlider1.setValue(100);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                FillChangeAction(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3).addComponent(jLabel6)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jButton1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton2)).addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jButton1).addComponent(jLabel4).addComponent(jButton2)).addGap(11, 11, 11).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel6).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true), "Position Select", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Palatino Linotype", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N
        jLabel7.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Choose Position");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("X");

        jLabel9.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("Y");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Horizontal Top Left", "Horizontal Top Center", "Horizontal Top Right", "Horizontal Middle Left", "Horizontal Middle Center", "Horizontal Middle Right", "Horizontal Bottom Left", "Horizontal Bottom Center", "Horizontal Bottom Right", "Horizontal Coustome", "Top-Bottom Diognal", "Bottom-Top Diognal", "Vertical Top Left", "Vertical Top Center", "Vertical Top Right", "Vertical Middle Left", "Vertical Middle Center", "Vertical Middle Right", "Vertical Bottom Left", "Vertical Bottom Center", "Vertical Bottom Right", "Vertical Coustome"}));
        jComboBox1.setToolTipText("Select Position to Watermark");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCustomePosition(evt);
            }
        });

        HspaceLabel.setFont(new java.awt.Font("Palatino Linotype", 0, 11)); // NOI18N
        HspaceLabel.setForeground(new java.awt.Color(204, 204, 204));
        HspaceLabel.setText("Space from Left Edge");

        VspaceLabel.setFont(new java.awt.Font("Palatino Linotype", 0, 11));
        VspaceLabel.setForeground(new java.awt.Color(204, 204, 204));
        VspaceLabel.setText("Space from Top Edge");

        jTextField2.setText("0");
        jTextField2.setToolTipText("X position");
        jTextField2.setEnabled(false);
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                XcheckNumber(evt);
            }
        });

        jTextField3.setText("0");
        jTextField3.setEnabled(false);
        jTextField3.setToolTipText("Y position");
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                YcheckNumber(evt);
            }
        });


        HspaceTextField.setText("0");
        HspaceTextField.setToolTipText("Set Horizontal spacing (pix) from image Edge");
        HspaceTextField.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                HspaceNumberCheck(evt);
            }
        });

        VSpaceTextField.setText("0");
        VSpaceTextField.setToolTipText("Set Vertical spacing (pix) from image Edge");
        VSpaceTextField.addFocusListener(new java.awt.event.FocusAdapter() {

            public void focusLost(java.awt.event.FocusEvent evt) {
                VspaceNumberCheck(evt);
            }
        });


        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel8)).addGap(33, 33, 33).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jTextField2, 0, 0, Short.MAX_VALUE).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(jPanel3Layout.createSequentialGroup().addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(VspaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(HspaceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(HspaceTextField).addComponent(VSpaceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))).addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(14, 14, 14)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(HspaceLabel).addComponent(HspaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(VSpaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(VspaceLabel)).addContainerGap(10, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel9)).addContainerGap()));
        jButton3.setText("Add");
        jButton3.setToolTipText("Add Text Watermarker");
        jButton3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTextWatermarker(evt);
            }
        });

        jButton4.setText("Cancel");
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelFont(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 255), 1, true), "Set Text for Watermarking", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Palatino Linotype", 0, 11), new java.awt.Color(0, 153, 255))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);


        if (isUpdate) {
            ArrayList EditList = (ArrayList) textHelper.getWaterMarkerList().get(updateIndex);
            String text[] = (String[]) EditList.get(1);
            Font f = (Font) EditList.get(7);
            Color c = (Color) EditList.get(8);
            int Fill = Integer.parseInt(EditList.get(9).toString());
            String textPosition = EditList.get(10).toString();
            String X = EditList.get(11).toString();
            String Y = EditList.get(12).toString();
            String hSpace = EditList.get(13).toString();
            String vSpace = EditList.get(14).toString();
            String textValue = "";
            for (int i = 0; i < text.length; i++) {
                textValue = textValue + text[i] + "\n";

            }
            jTextArea1.setText(textValue);
            textFont = f;
            textColor = c;
            jTextArea1.setFont(textFont);
            jTextArea1.setForeground(c);
            jSlider1.setValue(Fill);
            jTextField1.setText(EditList.get(9).toString() + "%");
            jComboBox1.setSelectedItem(textPosition);
            jTextField2.setText(X);
            jTextField3.setText(Y);
            HspaceTextField.setText(hSpace);
            VSpaceTextField.setText(vSpace);
            jButton3.setText("Update");

        } else {
            jTextArea1.setFont(textFont);
            jTextArea1.setForeground(java.awt.Color.BLACK);
            jTextField2.setText("0");
            jTextField3.setText("0");
            HspaceTextField.setText("0");
            VSpaceTextField.setText("0");
            jButton3.setText("Add");
            jTextField1.setText("100%");
        }


        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE).addContainerGap()));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE).addGap(10, 10, 10)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(53, 53, 53).addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE).addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(53, 53, 53)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(118, Short.MAX_VALUE).addComponent(jLabel1).addGap(98, 98, 98).addComponent(jLabel2).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton4).addComponent(jButton3)).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(34, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 425) / 2, (screenSize.height - 440) / 2, 425, 440);
    }// </editor-fold>                        

    /**
     * Sets value of Image Fill in respective Textfield for display of value
     * @param  Slider Change Even
     * 
     */
    private void FillChangeAction(javax.swing.event.ChangeEvent evt) {
        textHelper.sliderChangeAction(jSlider1, jTextField1);
    }

    /**
     * X position validation .
     *
     * @param Focus loose event
     */
    private void XcheckNumber(java.awt.event.FocusEvent evt) {
        textHelper.checkNumber(jTextField2);
    }

    /**
     * Y position validation .
     *
     * @param Focus loose event
     */
    private void YcheckNumber(java.awt.event.FocusEvent evt) {
        textHelper.checkNumber(jTextField3);
    }

    /**
     * H Space Validation
     */
    private void HspaceNumberCheck(java.awt.event.FocusEvent evt) {
        textHelper.checkNumber(HspaceTextField);

    }

    /**
     *V Space Validation
     */
    private void VspaceNumberCheck(java.awt.event.FocusEvent evt) {
        textHelper.checkNumber(VSpaceTextField);
    }

    /**
     * Validate data input data and add/update TextGUI Watermarker
     *
     * @param  Button click Evnet
     */
    private void AddTextWatermarker(java.awt.event.ActionEvent evt) {
        if (!(jTextArea1.getText().length() < 1)) {
            ArrayList textData = new ArrayList();
            String text = jTextArea1.getText();
            String textString[] = text.split("\n");
            textData.add("TEXT");
            textData.add(textString);
            textData.add(null);
            textData.add(null);
            textData.add(null);
            textData.add(null);
            textData.add(null);
            textData.add(textFont);
            textData.add(textColor);
            textData.add(jSlider1.getValue());
            textData.add(jComboBox1.getSelectedItem().toString());
            textData.add(jTextField2.getText());
            textData.add(jTextField3.getText());
            textData.add(HspaceTextField.getText());
            textData.add(VSpaceTextField.getText());

            if (isUpdate) {
                textHelper.getWaterMarkerList().set(updateIndex, textData);
                textHelper.Info("Updating Text Watermarker at Index:" + updateIndex + " with Parameters List: " + textData);
            } else {
                textHelper.getWaterMarkerList().add(textData);
                textHelper.Info("Adding Text Watermarker with Parameters List: " + textData);
            }

            this.dispose();
            textHelper.setEnable(textHelper.getMainFrame());
            textHelper.getMainWaterMarksDetails().removeAll();
            textHelper.setWaterMarkDetails();
            textHelper.getMainStatus().setText(textHelper.getProperty("message.text.success"));

        } else {
            javax.swing.JOptionPane.showMessageDialog(jPanel1, textHelper.getProperty("message.text.warning.text"));
        }

    }

    /**
     * Check position TextGUI watermark and change Display labels accordingly
     * enable and disable of textfield components
     * @param  Combobox change selected event
     *
     */
    private void CheckCustomePosition(java.awt.event.ActionEvent evt) {
        String position = jComboBox1.getSelectedItem().toString();
        if (position.equalsIgnoreCase("Horizontal Coustome")
                || position.equalsIgnoreCase("Vertical Coustome")) {
            jTextField2.setEnabled(true);
            jTextField3.setEnabled(true);
            VSpaceTextField.setEnabled(false);
            VSpaceTextField.setText("0");
            HspaceTextField.setEnabled(false);
            HspaceTextField.setText("0");
            HspaceLabel.setText("Not Applicable");
            VspaceLabel.setText("Not Applicable");
        } else {
            jTextField2.setEnabled(false);
            jTextField2.setText("0");
            jTextField3.setEnabled(false);
            jTextField3.setText("0");
            if (position.equalsIgnoreCase("Horizontal Top Left") || position.equalsIgnoreCase("Vertical Top Left")) {
                HspaceLabel.setText("Space from Left Edge");
                VspaceLabel.setText("Space from Top Edge");
                HspaceTextField.setEnabled(true);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Top Center") || position.equalsIgnoreCase("Vertical Top Center")) {
                HspaceLabel.setText("Not Applicable");
                VspaceLabel.setText("Space from Top Edge");
                HspaceTextField.setEnabled(false);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Top Right") || position.equalsIgnoreCase("Vertical Top Right")) {
                HspaceLabel.setText("Space from Right Edge");
                VspaceLabel.setText("Space from Top Edge");
                HspaceTextField.setEnabled(true);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Middle Left") || position.equalsIgnoreCase("Vertical Middle Left")) {
                HspaceLabel.setText("Space from Left Edge");
                VspaceLabel.setText("Not Applicable");
                HspaceTextField.setEnabled(true);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(false);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Middle Center") || position.equalsIgnoreCase("Vertical Middle Center")
                    || position.equalsIgnoreCase("Top-Bottom Diognal")
                    || position.equalsIgnoreCase("Bottom-Top Diognal")) {
                HspaceLabel.setText("Not Applicable");
                VspaceLabel.setText("Not Applicable");
                VSpaceTextField.setEnabled(false);
                VSpaceTextField.setText("0");
                HspaceTextField.setEnabled(false);
                HspaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Middle Right") || position.equalsIgnoreCase("Vertical Middle Right")) {
                HspaceLabel.setText("Space from Right Edge");
                VspaceLabel.setText("Not Applicable");
                HspaceTextField.setEnabled(true);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(false);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Bottom Left") || position.equalsIgnoreCase("Vertical Bottom Left")) {
                HspaceLabel.setText("Space from Left Edge");
                VspaceLabel.setText("Space from Bottom Edge");
                HspaceTextField.setEnabled(true);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Bottom Center") || position.equalsIgnoreCase("Vertical Bottom Center")) {
                HspaceLabel.setText("Not Applicable");
                VspaceLabel.setText("Space from Bottom Edge");
                HspaceTextField.setEnabled(false);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            } else if (position.equalsIgnoreCase("Horizontal Bottom Right") || position.equalsIgnoreCase("Vertical Bottom Right")) {
                HspaceLabel.setText("Space from Right Edge");
                VspaceLabel.setText("Space from Bottom Edge");
                HspaceTextField.setEnabled(true);
                HspaceTextField.setText("0");
                VSpaceTextField.setEnabled(true);
                VSpaceTextField.setText("0");
            }
        }
    }

    /**
     * Opens Color Chooser for choosing color
     *
     * @param Button click Evnet
     */
    private void chooseColourAction(java.awt.event.ActionEvent evt) {
        jFColorChooser1 = new javax.swing.JColorChooser();
        textColor = javax.swing.JColorChooser.showDialog(
                TextGUI.this,
                "Choose Color",
                jTextArea1.getBackground());

        if (textColor != null) {
            jTextArea1.setForeground(textColor);
        } else {
            textColor = jTextArea1.getBackground();
        }

    }

    /**
     * Opens Font Chooser for choosing font
     *
     * @param Button click Evnet
     */
    private void selectFontAction(java.awt.event.ActionEvent evt) {
        JFontChooser chooser = new JFontChooser(jTextArea1.getFont());
        if (chooser.showDialog(this, "Choose a font...") == JFontChooser.ACCEPT_OPTION) {
            if (textFont != null) {
                textFont = chooser.getSelectedFont();
                jTextArea1.setFont(textFont);
            }
        }
    }

    /**
     * Cancel action for add or edit of TextGUI Watermarker.
     *
     */
    private void CancelFont(java.awt.event.ActionEvent evt) {
        this.dispose();
        textHelper.setEnable(textHelper.getMainFrame());
        textHelper.getMainStatus().setText(textHelper.getProperty("message.text.close"));
    }
    // Variables declaration - do not modify
    private javax.swing.JColorChooser jFColorChooser1;
    private javax.swing.JLabel HspaceLabel;
    private javax.swing.JTextField HspaceTextField;
    private javax.swing.JTextField VSpaceTextField;
    private javax.swing.JLabel VspaceLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration                   
}

@SuppressWarnings("serial")
class TextNumericTextField extends JTextField {

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
