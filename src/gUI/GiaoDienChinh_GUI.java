package gUI;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class GiaoDienChinh_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JMenu mnHome;
	private JTextField txtDate;
	private JTextField txtNumberNV;
	private JTextField txtNumHD;
	private JTextField txtName;
	
	private JButton btnDangXuat;
	private JButton btnDoiMK;
	private JButton btnCaiDat;
	
	/**
	 * Create the frame.
	 */
	public GiaoDienChinh_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 1264, 50);
		contentPane.add(menuBar);
		
		mnHome = new JMenu("");
		mnHome.setHorizontalAlignment(SwingConstants.CENTER);
		mnHome.setIcon(new ImageIcon("img\\icons\\icons8-home-40.gif"));
		mnHome.setIconTextGap(20);
		menuBar.add(mnHome);
		
		JMenu mnCongNhan = new JMenu("  CÔNG NHÂN  ");
		mnCongNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCongNhan.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnCongNhan);
		
		JMenu mnNhanVien = new JMenu("  NHÂN VIÊN  ");
		mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnNhanVien);
		
		JMenu mnCongDoan = new JMenu("  CÔNG ĐOẠN  ");
		mnCongDoan.setHorizontalAlignment(SwingConstants.CENTER);
		mnCongDoan.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnCongDoan);
		
		JMenu mnLuong = new JMenu("  LƯƠNG  ");
		mnLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnLuong);
		
		JMenu mnHopDong = new JMenu("  HỢP ĐỒNG  ");
		mnHopDong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnHopDong);
		
		JMenu mnTroGiup = new JMenu("  TRỢ GIÚP  ");
		mnTroGiup.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnTroGiup);
		
		JMenu mnGioiThieu = new JMenu("  GIỚI THIỆU  ");
		mnGioiThieu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnGioiThieu);
		
		JPanel pnlBackGround = new JPanel();
		pnlBackGround.setBackground(new Color(224, 255, 255));
		pnlBackGround.setBounds(0, 50, 1264, 631);
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JLabel lblDNCuoi = new JLabel("Lần đăng nhập cuối:");
		lblDNCuoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDNCuoi.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDNCuoi.setBounds(10, 590, 154, 30);
		pnlBackGround.add(lblDNCuoi);
		
		txtDate = new JTextField();
		txtDate.setBackground(new Color(224, 255, 255));
		txtDate.setEditable(false);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDate.setBounds(161, 590, 160, 30);
		pnlBackGround.add(txtDate);
		txtDate.setColumns(30);
		txtDate.setBorder(null);
		
		JLabel lblTongNhanVien = new JLabel("Tổng số nhân viên hiện có:");
		lblTongNhanVien.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTongNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongNhanVien.setBounds(40, 117, 200, 30);
		pnlBackGround.add(lblTongNhanVien);
		
		txtNumberNV = new JTextField();
		txtNumberNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumberNV.setEditable(false);
		txtNumberNV.setColumns(30);
		txtNumberNV.setBorder(null);
		txtNumberNV.setBackground(new Color(224, 255, 255));
		txtNumberNV.setBounds(239, 117, 160, 30);
		pnlBackGround.add(txtNumberNV);
		
		txtNumHD = new JTextField();
		txtNumHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumHD.setEditable(false);
		txtNumHD.setColumns(30);
		txtNumHD.setBorder(null);
		txtNumHD.setBackground(new Color(224, 255, 255));
		txtNumHD.setBounds(203, 158, 160, 30);
		pnlBackGround.add(txtNumHD);
		
		JLabel lblHopDong = new JLabel("Số hợp đồng hiện có:");
		lblHopDong.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHopDong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHopDong.setBounds(40, 158, 170, 30);
		pnlBackGround.add(lblHopDong);
		
		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setBackground(new Color(255, 255, 255));
		btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDangXuat.setBounds(1094, 560, 160, 60);
		pnlBackGround.add(btnDangXuat);
		
		btnDoiMK = new JButton("Đổi Mật Khẩu");
		btnDoiMK.setBackground(new Color(255, 255, 255));
		btnDoiMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDoiMK.setBounds(924, 560, 160, 60);
		pnlBackGround.add(btnDoiMK);
		
		btnCaiDat = new JButton("Cài Đặt");
		btnCaiDat.setBackground(new Color(255, 255, 255));
		btnCaiDat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCaiDat.setBounds(754, 560, 160, 60);
		pnlBackGround.add(btnCaiDat);
		
		JLabel lblUser = new JLabel("Xin Chào! ");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUser.setBounds(920, 11, 80, 30);
		pnlBackGround.add(lblUser);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(224, 255, 255));
		txtName.setEditable(false);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setBounds(1000, 11, 200, 30);
		pnlBackGround.add(txtName);
		txtName.setColumns(10);
		txtName.setBorder(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setIcon(new ImageIcon("T:\\SalaryProductManagementSystem\\SalaryProductsManagementSystem\\img\\icons\\icons8-user-30.png"));
		lblNewLabel.setBounds(1205, 11, 30, 30);
		pnlBackGround.add(lblNewLabel);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setBackground(new Color(124, 252, 0));
		lblBackGround.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackGround.setIcon(new ImageIcon("img\\background\\Refund-bro_resize.png"));
		lblBackGround.setBounds(0, 0, 1264, 631);
		pnlBackGround.add(lblBackGround);
		
		mnHome.addMouseListener(this);
		btnDangXuat.addActionListener(this);
		btnCaiDat.addActionListener(this);
		btnDoiMK.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnDangXuat)) {
			DangNhap_GUI dangNhap = new DangNhap_GUI();
			this.dispose();
			dangNhap.setVisible(true);
		}
		
		if (o.equals(btnDoiMK)) {
			DoiMatKhau_GUI doiMK = new DoiMatKhau_GUI();
			doiMK.setVisible(true);
		}
		
		if (o.equals(btnCaiDat)) {
			
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(mnHome)) {
			QuenMatKhau_GUI quen = new QuenMatKhau_GUI();
			quen.setVisible(true);
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
