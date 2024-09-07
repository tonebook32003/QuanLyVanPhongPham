/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import GUI.Component.InputForm;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import static com.formdev.flatlaf.extras.components.FlatTabbedPane.TabAlignment.center;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.HelperFunction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class Login extends JFrame implements KeyListener{
    JPanel pnlMain, pnlLogIn;
    JLabel lblImage, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    InputForm txtUsername, txtPassword;

    Color FontColor = new Color(96, 125, 139);

    public Login() {
        initComponent();
        txtUsername.setText("vanloi@gmail.com");
        txtPassword.setPass("12345");
    }

    private void initComponent() {       
        
        this.setSize(new Dimension(1000, 500));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Đăng nhập");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JFrame jf = this;

        imgIntro();

        pnlMain = new JPanel();
        pnlMain.setBackground(Color.white);
        pnlMain.setBorder(new EmptyBorder(20, 0, 0, 0));

        pnlMain.setPreferredSize(new Dimension(500, 740));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));

        lbl3 = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lbl3.setFont(new Font(FlatRobotoFont.FAMILY_SEMIBOLD, Font.BOLD, 25));
        pnlMain.add(lbl3);

        JPanel paneldn = new JPanel();
        paneldn.setBackground(Color.BLACK);
        paneldn.setPreferredSize(new Dimension(400, 200));
        paneldn.setLayout(new GridLayout(2, 1));

        txtUsername = new InputForm("Tên đăng nhập");
        paneldn.add(txtUsername);
        txtPassword = new InputForm("Mật khẩu", "password");
        paneldn.add(txtPassword);

        txtUsername.getTxtForm().addKeyListener(this);
        txtPassword.getTxtPass().addKeyListener(this);

        pnlMain.add(paneldn);

        lbl6 = new JLabel("ĐĂNG NHẬP");
        lbl6.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 16));
        lbl6.setForeground(Color.white);        

        pnlLogIn = new JPanel();
        pnlLogIn.putClientProperty( FlatClientProperties.STYLE, "arc: 90" );
        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setPreferredSize(new Dimension(380, 45));
        pnlLogIn.setLayout(new FlowLayout(1, 0, 15));

        pnlLogIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                pnlLogInMouseEntered(evt);
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                try {
                    pnlLogInMousePressed(evt);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                pnlLogInMouseExited(evt);
            }
        });
        pnlLogIn.add(lbl6);       

        pnlMain.add(pnlLogIn);

        this.add(pnlMain, BorderLayout.EAST);

    }

    public void checkLogin() throws UnsupportedLookAndFeelException {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getPass();
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        } else {
            NhanVienDTO nv = NhanVienDAO.getInstance().selectByEmail(usernameCheck);
            if (nv == null) {
                JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            } else {
                if (nv.getTrangThai() == 0) {
                    JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (HelperFunction.checkLoginUserPass(usernameCheck, passwordCheck)) {
                        try {
                            //JOptionPane.showMessageDialog(this, "Đăng nhập thành công", "Thông báo!", JOptionPane.OK_OPTION);                            
                            this.dispose();
                            //Main main = new Main();
                            Main main = new Main(nv);
                            main.setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật khẩu không khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }

    private void pnlLogInMousePressed(java.awt.event.MouseEvent evt) throws UnsupportedLookAndFeelException {
        checkLogin();
    }

    private void pnlLogInMouseEntered(java.awt.event.MouseEvent evt) {
        pnlLogIn.setBackground(FontColor);
        pnlLogIn.setForeground(Color.black);
    }

    private void pnlLogInMouseExited(java.awt.event.MouseEvent evt) {

        pnlLogIn.setBackground(Color.BLACK);
        pnlLogIn.setForeground(Color.white);
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("PasswordField.showRevealButton", true);
        Login login = new Login();
        login.setVisible(true);
    }

    public void imgIntro() {
        JPanel bo = new JPanel();
        bo.setBorder(new EmptyBorder(-5, 10, 5, 5));
        bo.setPreferredSize(new Dimension(500, 760));
        bo.setBackground(Color.white);
        this.add(bo, BorderLayout.WEST);

        lblImage = new JLabel();
        FlatSVGIcon icon = new FlatSVGIcon("./img/login-image.svg");
        lblImage.setPreferredSize(new Dimension(550, 472));
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login-image.png")));
        bo.add(lblImage);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                checkLogin();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
