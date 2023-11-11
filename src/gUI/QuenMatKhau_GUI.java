package gUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import connectDB.ConnectDB;

/**
 * Lớp này dùng để tạo giao diện quên mật khẩu
 * Tạo bởi: Huỳnh Kim Thành - 21086351
 * ngày: 25/10/2023
 */
public class QuenMatKhau_GUI extends JFrame implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JPanel pnlForm;
	private JLabel lblBackGround;

	private JTextField txtTK;
	private JButton btnDK;
	
	private JLabel lblLinkQMK;
	
	public QuenMatKhau_GUI() {
		super("Quên mật khẩu");
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
		pnlBackGround.add(lblBackGround);
		lblBackGround.setIcon(new ImageIcon("img\\background\\coins_resize.jpg"));
		
		pnlForm = new JPanel();
		pnlForm.setBounds(454, 0, 430, 461);
		contentPane.add(pnlForm);
		pnlForm.setBackground(new Color(240, 248, 255));
		pnlForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("QUÊN MẬT KHẨU");
		lblTitle.setBounds(75, 39, 300, 80);
		pnlForm.add(lblTitle);
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		JLabel lblTaiKhoan = new JLabel("Mail:");
		lblTaiKhoan.setBounds(28, 150, 88, 20);
		pnlForm.add(lblTaiKhoan);
		lblTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtTK = new JTextField();
		txtTK.setBounds(28, 181, 370, 40);
		pnlForm.add(txtTK);
		txtTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTK.setColumns(10);
		
		btnDK = new JButton("Gửi");
		btnDK.setBounds(136, 290, 160, 50);
		pnlForm.add(btnDK);
		btnDK.setBackground(new Color(255, 255, 255));
		btnDK.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblLinkQMK = new JLabel("<html><u>Trở lại Đăng Nhập</u></html>");
		lblLinkQMK.setForeground(new Color(30, 144, 255));
		lblLinkQMK.setBounds(270, 410, 160, 40);
		pnlForm.add(lblLinkQMK);
		lblLinkQMK.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinkQMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLinkQMK.addMouseListener(this);
		
		btnDK.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(lblLinkQMK)) {
			this.dispose();
			DangNhap_GUI dangNhap = new DangNhap_GUI();
			dangNhap.setVisible(true);
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
}
