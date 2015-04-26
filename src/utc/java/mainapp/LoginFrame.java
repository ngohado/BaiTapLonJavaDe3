/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.mainapp;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import utc.java.database.DatabaseConnection;

/**
 *
 * @author admin
 */
public class LoginFrame {

    private JTextField tfUser;
    private JFrame fLogin;
    private JPanel pLogin;
    private JLabel lTDT;
    private JPasswordField pfPass;
    private JComboBox cbALevel;
    private JLabel lForgot;
    private JLabel lLogin;
    
    private Connection connect;
    

    public LoginFrame() throws SQLException {
        initComponent();
        connect = DatabaseConnection.getDatabaseConnection();
    }

    private void initComponent() {
        fLogin = new JFrame("Login");
        pLogin = new JPanel();

        fLogin.setSize(450, 360);
        fLogin.setLocationRelativeTo(null);
        fLogin.setResizable(false);
        pLogin.setLayout(new GridBagLayout());

        pLogin.setBackground(Color.WHITE);
        lTDT = new JLabel();
        ImageIcon icon = new ImageIcon("logo.png");
        lTDT.setIcon(icon);
        lTDT.setHorizontalAlignment(JTextField.CENTER);
        addComponent(pLogin, lTDT, 0, 0, 1, 2);

        tfUser = new JTextField("Your ID", 20);
        tfUser.setHorizontalAlignment(JTextField.CENTER);
        tfUser.setForeground(Color.GRAY);
        tfUser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tfUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    tfUserKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tfUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfUserMousePressed(evt);
            }
        });
        tfUser.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                tfUserFocusLost(e);
            }
        });

        addComponent(pLogin, tfUser, 1, 0, 1, 2);

        pfPass = new JPasswordField("password");
        pfPass.setHorizontalAlignment(JTextField.CENTER);
        pfPass.setForeground(Color.GRAY);
        addComponent(pLogin, pfPass, 2, 0, 1, 2);
        pfPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pfPassMousePressed(e);
            }
        });
        pfPass.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                pfPassMousePressed(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                pfPassFocusLost(e);
            }
        });
        pfPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    pfPassKeyPressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        cbALevel = new JComboBox();
        cbALevel.setModel(new DefaultComboBoxModel(new String[]{"Administrator", "Member", "Guest"}));
        //addComponent(fLogin, cbALevel, 3, 0, 1, 2);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
//        gbc.fill = GridBagConstraints.BOTH;
        Insets s = new Insets(8, 8, 8, 8);
        gbc.insets = s;
        pLogin.add(cbALevel, gbc);

        lForgot = new JLabel("Forgot password?");
        lForgot.setForeground(Color.GRAY);
        lForgot.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lForgot.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lForgotMouseMoved(e);
            }
        });
        lForgot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lForgotMouseExited(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lForgotMousePressed(e);
            }
        });
        addComponent(pLogin, lForgot, 4, 0, 1, 1);

        lLogin = new JLabel();
        lLogin.setIcon(new ImageIcon("login.png"));
        lLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    lLoginMousePressed(e);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        addComponent(pLogin, lLogin, 4, 1, 1, 1);

        fLogin.add(pLogin);
        fLogin.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        fLogin.setVisible(true);
    }

    private void tfUserMousePressed(MouseEvent evt) {
        if (tfUser.getText().equals("Your ID")) {
            tfUser.setText("");
        }
        tfUser.setForeground(Color.BLACK);
        if (pfPass.getText().equals("")) {
            pfPass.setText("password");
            pfPass.setForeground(Color.GRAY);
        }
    }

    private void tfUserKeyPressed(KeyEvent e) throws SQLException {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            lLoginMousePressed(null);
        }
        if (tfUser.getText().equals("Your ID")) {
            tfUser.setText("");
            tfUser.setForeground(Color.BLACK);
        }

    }

    private void tfUserFocusLost(FocusEvent e) {
        if (tfUser.getText().equals("")) {
            tfUser.setText("Your ID");
            tfUser.setForeground(Color.GRAY);
        }
    }

    private void pfPassMousePressed(MouseEvent e) {
        pfPass.setForeground(Color.BLACK);
        if (pfPass.getText().equals("password")) {
            pfPass.setText("");
        }
        if (tfUser.getText().equals("")) {
            tfUser.setText("Your ID");
            tfUser.setForeground(Color.GRAY);
        }
    }

    private void pfPassFocusLost(FocusEvent e) {
        if (pfPass.getText().equals("")) {
            pfPass.setText("password");
            pfPass.setForeground(Color.GRAY);
        }
    }

    private void pfPassKeyPressed(KeyEvent e) throws SQLException {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            lLoginMousePressed(null);
        }
    }

    private void lForgotMouseMoved(MouseEvent e) {
        lForgot.setForeground(Color.BLUE);
    }

    private void lForgotMouseExited(MouseEvent e) {
        lForgot.setForeground(Color.GRAY);
    }

    private void lForgotMousePressed(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Please contact with adminstrator to recover your password ", "Forgot Password", 2);
    }

    private void lLoginMousePressed(MouseEvent e) throws SQLException {
        //connect to the database and confirm the login 
        String id = tfUser.getText();
        String password = new String(pfPass.getPassword());
        String level = cbALevel.getSelectedItem().toString();
        if(id.equals("Your ID")){
            JOptionPane.showMessageDialog(null, "Your id is empty", "Empty Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        confirmAccess(id, password,level);
    }

    private void addComponent(JPanel f, Component b, int r, int c, int nr, int nc) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = c;
        gbc.gridy = r;
        gbc.gridheight = nr;
        gbc.gridwidth = nc;
        gbc.fill = GridBagConstraints.BOTH;
        Insets s = new Insets(8, 8, 8, 8);
        gbc.insets = s;
        f.add(b, gbc);
    }

    private void confirmAccess(String idUser, String password,String level) throws SQLException {
        Statement statement = connect.createStatement();
        int lv = 1;
        switch(level){
            case "Administrator":
                lv = 1;
                break;
                
            case "Member":
                lv = 2;
                break;
                
            case "Guess":
                lv = 3;
                break;
        }
        
        String confirmSQL = "select *\n"
                + "from Account\n"
                + "where (userName like '"+idUser+"') and (passWord like '"+password+"') and (accessLevell="+lv+")";
        
        ResultSet resultSet = statement.executeQuery(confirmSQL);
        resultSet.next();
        try {
            if(resultSet.getString("userName").equalsIgnoreCase(idUser)){
                System.out.println("Correct");
                connect.close();
                startMainFrame(lv);
                fLogin.setVisible(false);
               
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Your id or password is not correct .Please inspect again !", "Warning",JOptionPane.WARNING_MESSAGE);
            pfPass.setText("");
            fLogin.setVisible(true);
        }
         
    }

    private void startMainFrame(int accessLevel) throws SQLException{
        FrameMain mainFrame = new FrameMain(accessLevel);
    }
  
    
    //set Icon for frame
    private void setIcon() {
        ImageIcon icon = new ImageIcon("favicon.png");
        Image image = icon.getImage();
        fLogin.setIconImage(image);
    }

    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoginFrame a = new LoginFrame();
    }
}
