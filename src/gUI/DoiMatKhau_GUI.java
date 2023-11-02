package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import connectDB.ConnectDB;

public class DoiMatKhau_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel pnlForm;
	private JLabel lblBackGround;

	private JTextField txtTK;
	private JPasswordField pwdMK;
	private JButton btnDK;
	
	private JLabel lblLinkBack;
	private JPasswordField passwordField;
	/**
	 * Create the frame.
	 */
	public DoiMatKhau_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		this.setAlwaysOnTop(true);
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlBackGround = new JPanel();
		pnlBackGround.setBounds(0, 0, 455, 461);
		pnlBackGround.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JLabel lblBackGround_1 = new JLabel("");
		lblBackGround_1.setBounds(0, 0, 455, 461);
		pnlBackGround.add(lblBackGround_1);
		lblBackGround_1.setIcon(new ImageIcon("img\\background\\coins_resize.jpg"));
		
		JPanel pnlForm = new JPanel();
		pnlForm.setBounds(454, 0, 430, 461);
		contentPane.add(pnlForm);
		pnlForm.setBackground(new Color(240, 248, 255));
		pnlForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("Đổi Mật Khẩu");
		lblTitle.setBounds(100, 11, 240, 80);
		pnlForm.add(lblTitle);
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lblTaiKhoan = new JLabel("Mật khẩu cũ:");
		lblTaiKhoan.setBounds(29, 100, 120, 20);
		pnlForm.add(lblTaiKhoan);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtTK = new JTextField();
		txtTK.setBounds(29, 129, 370, 40);
		pnlForm.add(txtTK);
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu mới:");
		lblMatKhau.setBounds(29, 180, 120, 20);
		pnlForm.add(lblMatKhau);
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		pwdMK = new JPasswordField();
		pwdMK.setBounds(29, 209, 370, 40);
		pnlForm.add(pwdMK);
		
		btnDK = new JButton("Xác Nhận");
		btnDK.setBounds(144, 349, 160, 50);
		pnlForm.add(btnDK);
		btnDK.setBackground(new Color(255, 255, 255));
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblLinkBack = new JLabel("Trở lại Trang Chính");
		lblLinkBack.setForeground(new Color(0, 255, 255));
		lblLinkBack.setBounds(144, 410, 160, 40);
		pnlForm.add(lblLinkBack);
		lblLinkBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinkBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNhpLiMt = new JLabel("Nhập lại mật khẩu mới:");
		lblNhpLiMt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNhpLiMt.setBounds(29, 260, 200, 20);
		pnlForm.add(lblNhpLiMt);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(29, 289, 370, 40);
		pnlForm.add(passwordField);
	}
}
