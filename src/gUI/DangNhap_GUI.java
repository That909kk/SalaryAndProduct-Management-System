package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import javax.swing.ImageIcon;

public class DangNhap_GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtTK;
	private JPasswordField pwdMK;
	private JButton btnDN;
	private JButton btnDK;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		DangNhap_GUI dangNhap = new DangNhap_GUI();
		dangNhap.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public DangNhap_GUI() {
		super("Đăng nhập");
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
		pnlBackGround.setBounds(0, 0, 455, 461);
		pnlBackGround.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 455, 461);
		lblNewLabel.setIcon(new ImageIcon("img\\background\\coins_resize.jpg"));
		pnlBackGround.add(lblNewLabel);
		
		JLabel lblTitle = new JLabel("Đăng nhập");
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setBounds(555, 36, 240, 80);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		contentPane.add(lblTitle);
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTaiKhoan.setBounds(490, 127, 80, 20);
		contentPane.add(lblTaiKhoan);
		
		txtTK = new JTextField();
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setBounds(490, 156, 360, 40);
		contentPane.add(txtTK);
		txtTK.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMatKhau.setBounds(490, 219, 80, 20);
		contentPane.add(lblMatKhau);
		
		pwdMK = new JPasswordField();
		pwdMK.setBounds(490, 250, 360, 40);
		contentPane.add(pwdMK);
		
		btnDK = new JButton("Đăng kí");
		btnDK.setBackground(new Color(255, 255, 255));
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDK.setBounds(700, 335, 150, 50);
		contentPane.add(btnDK);
		
		btnDN = new JButton("Đăng nhập");
		btnDN.setBackground(new Color(255, 255, 255));
		btnDN.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDN.setBounds(490, 335, 150, 50);
		contentPane.add(btnDN);
		
		JLabel lblLinkQMK = new JLabel("Quên mật khẩu?");
		lblLinkQMK.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLinkQMK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLinkQMK.setBounds(730, 400, 120, 30);
		contentPane.add(lblLinkQMK);
		
		btnDK.addActionListener(this);
		btnDN.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
Object obj = e.getSource();
		
		if (obj.equals(btnDN)) {
			TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();
			
			String tk = txtTK.getText();
			String mk = String.valueOf(pwdMK.getPassword());
			
			TaiKhoan tonTai = taiKhoan_DAO.soSanhPWD(tk, mk);
			
			if (tonTai != null) {
				JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Đăng nhập thất bại! Tài khoản hoặc mật khẩu sai");
				txtTK.requestFocus();
				txtTK.selectAll();
				pwdMK.setText("");
			}
		}
		
		if (obj.equals(btnDK)) {
			this.dispose();
			DangKy_GUI dangKy = new DangKy_GUI();
			dangKy.setVisible(true);
		}
	}

}
