package gUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.event.MouseInputListener;

import connectDB.ConnectDB;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
import javax.swing.ImageIcon;

public class DangNhap_GUI extends JFrame implements ActionListener, MouseListener, MouseInputListener {

	private JPanel contentPane;
	private JTextField txtTK;
	private JPasswordField pwdMK;
	private JButton btnDN;
	private JButton btnDK;
	private JLabel lblBackGround;
	private JLabel lblLinkQMK;

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
		
		lblBackGround = new JLabel("");
		lblBackGround.setBounds(0, 0, 455, 461);
		lblBackGround.setIcon(new ImageIcon("img\\background\\coins_resize.jpg"));
		pnlBackGround.add(lblBackGround);
		
		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setBounds(555, 36, 240, 80);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		contentPane.add(lblTitle);
		
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTaiKhoan.setBounds(490, 120, 88, 20);
		contentPane.add(lblTaiKhoan);
		
		txtTK = new JTextField();
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setBounds(490, 149, 360, 40);
		contentPane.add(txtTK);
		txtTK.setColumns(10);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMatKhau.setBounds(490, 210, 88, 20);
		contentPane.add(lblMatKhau);
		
		pwdMK = new JPasswordField();
		pwdMK.setBounds(490, 241, 360, 40);
		contentPane.add(pwdMK);
		
		btnDK = new JButton("Đăng ký");
		btnDK.setBackground(new Color(255, 255, 255));
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDK.setBounds(700, 310, 150, 50);
		contentPane.add(btnDK);
		
		btnDN = new JButton("Đăng nhập");
		btnDN.setBackground(new Color(255, 255, 255));
		btnDN.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDN.setBounds(490, 310, 150, 50);
		contentPane.add(btnDN);
		
		lblLinkQMK = new JLabel("Quên mật khẩu?");
		lblLinkQMK.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLinkQMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLinkQMK.setBounds(730, 390, 120, 40);
		contentPane.add(lblLinkQMK);
		
		btnDK.addActionListener(this);
		btnDN.addActionListener(this);
		lblLinkQMK.addMouseMotionListener(this);
		lblLinkQMK.addMouseListener(this);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
		lblLinkQMK.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lblLinkQMK.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
