package gUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

/**
 * Lớp này dùng để tạo giao diện đăng nhập
 * Tạo bởi: Huỳnh Kim Thành - 21086351
 * ngày: 25/10/2023
 */
public class DangNhap_GUI extends JFrame implements ActionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private JTextField txtTK;
	private JPasswordField pwdMK;
	private JButton btnDN;
	private JLabel lblBackGround;
	private JLabel lblLinkQMK;
	private JPanel pnlForm;
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
		
		pnlForm = new JPanel();
		pnlForm.setBackground(new Color(240, 248, 255));
		pnlForm.setBounds(454, 0, 430, 461);
		contentPane.add(pnlForm);
		pnlForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
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
		
		btnDN = new JButton("Đăng nhập");
		btnDN.setBounds(130, 330, 180, 60);
		pnlForm.add(btnDN);
		btnDN.setBackground(new Color(255, 255, 255));
		btnDN.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblLinkQMK = new JLabel("<html><u>Quên mật khẩu?</u></html>");
		lblLinkQMK.setForeground(new Color(30, 144, 255));
		lblLinkQMK.setBounds(130, 401, 180, 40);
		pnlForm.add(lblLinkQMK);
		lblLinkQMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinkQMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLinkQMK.addMouseListener(this);
		btnDN.addActionListener(this);
		
		txtTK.addKeyListener(this);
		pwdMK.addKeyListener(this);
		
		txtTK.setText("230010");
		pwdMK.setText("230010");
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
				GiaoDienChinh_GUI main = new GiaoDienChinh_GUI(tonTai);
				main.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Đăng nhập thất bại! Tài khoản hoặc mật khẩu sai");
				txtTK.requestFocus();
				txtTK.selectAll();
				pwdMK.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(lblLinkQMK)) {
			QuenMatKhau_GUI quenMK = new QuenMatKhau_GUI();
			this.dispose();
			quenMK.setVisible(true);
		}
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnDN.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
