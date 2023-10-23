package gUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

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

public class DangKy_GUI extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnDK;
	private JPasswordField pwdMK;
	private JTextField txtTK;
	
	public DangKy_GUI() {
		
		super("Đăng nhập");
		setTitle("Đăng kí");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		};

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 404, 461);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		
		JLabel lblTitle = new JLabel("Đăng kí");
		lblTitle.setBounds(478, 52, 240, 80);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		contentPane.add(lblTitle);
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTaiKhoan.setBounds(438, 140, 80, 20);
		contentPane.add(lblTaiKhoan);
		
		txtTK = new JTextField();
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setBounds(438, 170, 312, 35);
		contentPane.add(txtTK);
		txtTK.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMatKhau.setBounds(438, 220, 80, 20);
		contentPane.add(lblMatKhau);
		
		pwdMK = new JPasswordField();
		pwdMK.setBounds(438, 250, 312, 35);
		contentPane.add(pwdMK);
		
		btnDK = new JButton("Đăng kí");
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDK.setBounds(537, 326, 120, 40);
		contentPane.add(btnDK);
		
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
}
