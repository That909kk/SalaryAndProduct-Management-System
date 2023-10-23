package gUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;

public class DangKy_GUI extends JFrame implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JPanel pnlForm;
	private JLabel lblBackGround;

	private JTextField txtTK;
	private JPasswordField pwdMK;
	
	private JButton btnDN;
	private JButton btnDK;
	
	private JLabel lblLinkQMK;
	
	public DangKy_GUI() {
		
		super("Đăng Ký");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		
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
		pnlBackGround.setBounds(429, 0, 455, 461);
		pnlBackGround.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		lblBackGround = new JLabel("");
		lblBackGround.setBounds(0, 0, 455, 461);
		pnlBackGround.add(lblBackGround);
		lblBackGround.setIcon(new ImageIcon("img\\background\\coins_resize.jpg"));
		
		pnlForm = new JPanel();
		pnlForm.setBounds(0, 0, 430, 461);
		contentPane.add(pnlForm);
		pnlForm.setBackground(new Color(240, 248, 255));
		pnlForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("ĐĂNG KÝ");
		lblTitle.setBounds(100, 39, 240, 80);
		pnlForm.add(lblTitle);
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setBounds(29, 130, 88, 20);
		pnlForm.add(lblTaiKhoan);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtTK = new JTextField();
		txtTK.setBounds(29, 158, 370, 40);
		pnlForm.add(txtTK);
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setBounds(29, 220, 88, 20);
		pnlForm.add(lblMatKhau);
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		pwdMK = new JPasswordField();
		pwdMK.setBounds(29, 251, 370, 40);
		pnlForm.add(pwdMK);
		
		btnDK = new JButton("Đăng ký");
		btnDK.setBounds(250, 327, 150, 50);
		pnlForm.add(btnDK);
		btnDK.setBackground(new Color(255, 255, 255));
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnDN = new JButton("Đăng nhập");
		btnDN.setBounds(29, 327, 150, 50);
		pnlForm.add(btnDN);
		btnDN.setBackground(new Color(255, 255, 255));
		btnDN.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblLinkQMK = new JLabel("Quên mật khẩu?");
		lblLinkQMK.setBounds(280, 388, 120, 40);
		pnlForm.add(lblLinkQMK);
		lblLinkQMK.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLinkQMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLinkQMK.addMouseListener(this);
		btnDN.addActionListener(this);
		
		btnDK.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		String pwdHash = null;
		
		if (object.equals(btnDK)) {
			if (txtTK.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Hãy nhập tài khoản");
				txtTK.requestFocus();
			} else if (pwdMK.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Hãy nhập mật khẩu");
				pwdMK.requestFocus();
			} else {
				TaiKhoan_DAO listTK = new TaiKhoan_DAO();
				String tk = txtTK.getText();
				String mk = new String(pwdMK.getPassword());
				
				try {
					MessageDigest md = MessageDigest.getInstance("MD5");
					md.update(mk.getBytes());
					byte[] digest = md.digest();
					pwdHash = new String(digest).toUpperCase();
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}

				TaiKhoan taiKhoan = new TaiKhoan();
				listTK.insert(taiKhoan);
				
				JOptionPane.showMessageDialog(this, "Đăng ký thành công");
				this.dispose();
				
				DangNhap_GUI dangNhap = new DangNhap_GUI();
				dangNhap.setVisible(true);
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
