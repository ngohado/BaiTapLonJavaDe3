/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.add;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEFT;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import tdt.Object.Candidate;

/**
 *
 * @author admin
 */
public class PanelAdd {
    //declare the variables
    private String file_name = new String();
    
    //private JFrame fMain;
    private JPanel pAdd;
    private JPanel pEast;
    private JPanel pLogo;
    private JPanel pAddMain;
    private JPanel pImage;
        
    private JLabel lLogo;
    private JLabel lID;
    private JTextField tfID;
    private JLabel lName;
    private JTextField tfName;
    private JLabel lPlace;
    private JTextField tfPlace;
    private JLabel lGender;
    private JComboBox cbGender;
    private JLabel lUnit;
    private JComboBox cbUnit;
    private JLabel lMath;
    private JTextField tfMath;
    private JLabel lPhysics;
    private JTextField tfPhysics;
    private JLabel lChemistry;
    private JTextField tfChemistry;
    private JLabel lEnglish;
    private JTextField tfEnglish;
    private JLabel lDOB;
    private com.toedter.calendar.JDateChooser dcDOB;
    private JLabel lArea;
    private JTextField tfArea;
        
    private JLabel lImage;
    private JLabel ldes;
    private JButton bChoose;
    private JTextField tfPath;
    
    private ImageIcon iAvatar;
    private byte[] person_image;

    //constructor
    public PanelAdd(){
        initComponent();
    }
    
    private void initComponent(){
        //fMain = new JFrame();
        pAdd = new JPanel();
        pEast = new JPanel();
        pLogo = new JPanel();
        pAddMain = new JPanel();
        pImage = new JPanel();
        
        lLogo = new JLabel();
        lID = new JLabel("ID",LEFT);
        tfID = new JTextField("", 20);
        lName = new JLabel("Name",LEFT);
        tfName = new JTextField("",20);
        lPlace = new JLabel("Place",LEFT);
        tfPlace = new JTextField("",20);
        lGender = new JLabel("Gender",LEFT);
        cbGender = new JComboBox();
        lUnit = new JLabel("Unit",LEFT);
        cbUnit = new JComboBox();
        lMath = new JLabel("Math",LEFT);
        tfMath = new JTextField("",20);
        lPhysics = new JLabel("Physics",LEFT);
        tfPhysics = new JTextField("",20);
        lChemistry = new JLabel("Chemistry",LEFT);
        tfChemistry = new JTextField("",20);
        lEnglish = new JLabel("English",LEFT);
        tfEnglish = new JTextField("",20);
        lDOB = new JLabel("Date of birth",LEFT);
        dcDOB = new com.toedter.calendar.JDateChooser();
        lArea = new JLabel("Area",LEFT);
        tfArea = new JTextField("",20);
        
        lImage = new JLabel();
        ldes = new JLabel("Size 3x4");
        bChoose = new JButton("Choose image");
        tfPath = new JTextField("",17);
        
        person_image  = null; 

        //
//        fMain.setPreferredSize(new Dimension(500,400));
//        fMain.setSize(500, 400);
//        fMain.setLocationRelativeTo(null);
        
        //main panel
        pAdd.setLayout(new BorderLayout());

        
        
        //panel logo
        pLogo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pLogo.setBackground(Color.WHITE);
        lLogo.setText("Student information");
        pLogo.add(lLogo);
        pAdd.add(pLogo,BorderLayout.NORTH);
        
        
        
        //panel addMain
        pAddMain.setLayout(new GridBagLayout());
        pAddMain.setBackground(Color.WHITE);
        pAddMain.setBorder(new TitledBorder("Information"));
        //add labels to panel add main
        lID.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lID, 0, 0, 1, 1);
        lName.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lName, 1, 0, 1, 1);
        lGender.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lGender, 2, 0, 1, 1);
        lDOB.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lDOB, 3, 0, 1, 1);
        lPlace.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lPlace, 4, 0, 1, 1);
        lArea.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lArea, 5, 0, 1, 1);
        lUnit.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lUnit, 6, 0, 1, 1);
        lMath.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lMath, 7, 0, 1, 1);
        lPhysics.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lPhysics, 8, 0, 1, 1);
        lChemistry.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lChemistry, 9, 0, 1, 1);
        lEnglish.setFont(new Font("Tahoma",0,14));
        addComponent(pAddMain, lEnglish, 10, 0, 1, 1);
        
        //add text fields to panel addMain
        addComponent(pAddMain, tfID, 0, 1, 1, 2);
        addComponent(pAddMain, tfName, 1, 1, 1, 2);
        addComponent(pAddMain, tfPlace, 4, 1, 1, 2);
        addComponent(pAddMain, tfArea, 5, 1, 1, 2);
        addComponent(pAddMain, tfMath, 7, 1, 1, 2);
        addComponent(pAddMain, tfPhysics, 8, 1, 1, 2);
        addComponent(pAddMain, tfChemistry, 9, 1, 1, 2);
        addComponent(pAddMain, tfEnglish, 10, 1, 1, 2);
        //add combo boxes to panel addMain
        cbGender.setModel(new DefaultComboBoxModel(new String[]{"Male","Female","Not identified"}));
        cbGender.setFocusable(false);
        addComponent(pAddMain, cbGender, 2, 1, 1, 2);
        
        dcDOB.setDateFormatString("dd/MM/yyyy");
        addComponent(pAddMain, dcDOB, 3, 1, 1, 2);
        
        cbUnit.setModel(new DefaultComboBoxModel(new String[]{"A","A1"}));
        cbUnit.setFocusable(false);
        addComponent(pAddMain, cbUnit, 6, 1, 1, 2);
        
        pAdd.add(pAddMain,BorderLayout.CENTER);

        //panel east
        pEast.setLayout(new BorderLayout());
        pEast.setBackground(Color.WHITE);
        pAdd.add(pEast,BorderLayout.EAST);
        //panel image
        pImage.setLayout(new GridBagLayout());
        pImage.setBackground(Color.WHITE);
        pImage.setBorder(new TitledBorder("Image"));
        
        lImage.setIcon(new ImageIcon(getClass().getResource("/studentms/image/avatar.jpg")));
        addComponentNotFill(pImage, lImage, 0, 0, 1, 1);
        addComponentNotFill(pImage, ldes, 1, 0, 1, 1);
        
        bChoose.setIcon(new ImageIcon(getClass().getResource("/studentms/image/Actions-tab-new-icon.png")));
        bChoose.setFocusable(false);
        bChoose.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bChooseActionPerformed(e);
            }
        });
        addComponentNotFill(pImage, bChoose, 2, 0, 1, 1);
        
        addComponentNotFill(pImage, tfPath, 3, 0, 1, 1);
        
        pEast.add(pImage,BorderLayout.NORTH);

//        fMain.add(pAdd);
//        
//        fMain.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        fMain.setVisible(true);
    }
    
    private void addComponent(JPanel p,Component b,int r,int c,int nr,int nc){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = c;
        gbc.gridy = r;
        gbc.gridwidth = nc;
        gbc.gridheight = nr;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4,10,4,10);
        p.add(b,gbc);
    }
    
    private void addComponentNotFill(JPanel p,Component b,int r,int c,int nr,int nc){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = c;
        gbc.gridy = r;
        gbc.gridwidth = nc;
        gbc.gridheight = nr;
        gbc.insets = new Insets(4,10,4,10);
        p.add(b,gbc);
    }    
    
    private void bChooseActionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter fex = new FileNameExtensionFilter("Image file", "jpg","jpeg","bmp", "png");
        fc.setFileFilter(fex);
        int check = fc.showOpenDialog(null);
        if (check==1){
            File f = fc.getSelectedFile();
            try{
                file_name =f.getAbsolutePath();
            } catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Please choose the image...","Choose image", 2);
            }
        }
        
                tfPath.setText(file_name);

                
        try{
            File image = new File(file_name);
            FileInputStream fis = new FileInputStream(image);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for(int readNum; (readNum = fis.read(buf)) != -1;){
                bos.write(buf,0,readNum);
                
            }
        person_image =  bos.toByteArray();
        iAvatar = new ImageIcon(file_name);
        lImage.setIcon(iAvatar);
        }catch(Exception ex){   
            JOptionPane.showMessageDialog(null, "Please, input avatar !", "Please...", 2);
        }
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PanelAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PanelAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PanelAdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PanelAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setCandidate(Candidate c){
        tfID.setText(c.getId()+"");
        tfName.setText(c.getFullName());
//        if (c.getSex()) cbGender.setS
    }
    
    public JPanel getPanelAdd(){
        return pAdd;
    }
    
}
