package gUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
/**
 * Lớp này dùng để tạo giao diện chính
 * Tạo bởi: Huỳnh Kim Thành - 21086351
 * ngày: 25/10/2023
 */
public class GiaoDienChinh_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {	
	private JPanel contentPane;
	private JMenu mnHome;
	private JTextField txtDate;
	private JTextField txtNumberNV;
	private JTextField txtNumHD;
	private JTextField txtName;
	
	private JButton btnDangXuat;
	private JButton btnDoiMK;
	private JButton btnCaiDat;
	private JMenu mnNhanVien;
	private JMenu mnCongDoan;
	private JMenu mnLuong;
	private JMenu mnHopDong;
	private JMenu mnTroGiup;
	private JMenu mnGioiThieu;
	private JMenuItem mntmPCCD;
	private JMenuItem mntmPCCN;
	private JPanel pnlBackGround;
	private JMenuBar menuBar;
	
	
	private TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();
	private TaiKhoan tkMain = null;
	/**
	 * Create the frame.
	 */
	public GiaoDienChinh_GUI(TaiKhoan tk) {		
		super("Màn hình chính");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		tkMain = tk;
		createGUI(tk);
		
		switch (taiKhoan_DAO.getBoPhanCuaNV(tk)) {
		case "BPNS" -> {
			String dateFormatted = chuyenKieuNgay(tk.getNgayDNCuoi());
			txtDate.setText(dateFormatted);
			
			mnLuong.setEnabled(false);
		}
		
		case "BPKT" -> {
			
		}
		
		case "QLXU" -> {
			mnNhanVien.setEnabled(false);
		}
		
		default ->
		throw new IllegalArgumentException("Unexpected value: ");
		}
		
		
		
	}
	
	public void createGUI(TaiKhoan tk) {
		menuBar = new JMenuBar();
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
		
		mnNhanVien = new JMenu("  NHÂN VIÊN  ");
		mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnNhanVien);
		
		mnCongDoan = new JMenu("  CÔNG ĐOẠN  ");
		mnCongDoan.setHorizontalAlignment(SwingConstants.CENTER);
		mnCongDoan.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnCongDoan);
		
		mntmPCCD = new JMenuItem("Phân Chia Công Đoạn");
		mntmPCCD.setBorder(new EmptyBorder(10, 5, 10, 0));
		mntmPCCD.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCongDoan.add(mntmPCCD);
		
		mntmPCCN = new JMenuItem("Phân Công Công Nhân");
		mntmPCCN.setBorder(new EmptyBorder(10, 5, 10, 0));
		mntmPCCN.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCongDoan.add(mntmPCCN);
		
		mnLuong = new JMenu("  LƯƠNG  ");
		mnLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnLuong);
		
		mnHopDong = new JMenu("  HỢP ĐỒNG  ");
		mnHopDong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnHopDong);
		
		mnTroGiup = new JMenu("  TRỢ GIÚP  ");
		mnTroGiup.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnTroGiup);
		
		mnGioiThieu = new JMenu("  GIỚI THIỆU  ");
		mnGioiThieu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnGioiThieu);
		
		pnlBackGround = new JPanel();
		pnlBackGround.setBackground(new Color(224, 255, 255));
		pnlBackGround.setBounds(0, 50, 1264, 631);
		contentPane.add(pnlBackGround);
		pnlBackGround.setLayout(null);
		
		JLabel lblDNCuoi = new JLabel("Lần đăng nhập cuối:");
		lblDNCuoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDNCuoi.setBounds(10, 590, 190, 30);
		pnlBackGround.add(lblDNCuoi);
		
		txtDate = new JTextField();
		txtDate.setBackground(new Color(224, 255, 255));
		txtDate.setEditable(false);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDate.setBounds(200, 590, 160, 30);
		pnlBackGround.add(txtDate);
		txtDate.setColumns(30);
		txtDate.setBorder(null);
		
		JLabel lblTongNhanVien = new JLabel("Tổng số nhân viên hiện có:");
		lblTongNhanVien.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTongNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongNhanVien.setBounds(10, 116, 241, 30);
		pnlBackGround.add(lblTongNhanVien);
		
		txtNumberNV = new JTextField();
		txtNumberNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumberNV.setEditable(false);
		txtNumberNV.setColumns(30);
		txtNumberNV.setBorder(null);
		txtNumberNV.setBackground(new Color(224, 255, 255));
		txtNumberNV.setBounds(261, 116, 160, 30);
		pnlBackGround.add(txtNumberNV);
		
		txtNumHD = new JTextField();
		txtNumHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumHD.setEditable(false);
		txtNumHD.setColumns(30);
		txtNumHD.setBorder(null);
		txtNumHD.setBackground(new Color(224, 255, 255));
		txtNumHD.setBounds(220, 157, 160, 30);
		pnlBackGround.add(txtNumHD);
		
		JLabel lblHopDong = new JLabel("Số hợp đồng hiện có:");
		lblHopDong.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHopDong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHopDong.setBounds(10, 158, 200, 30);
		pnlBackGround.add(lblHopDong);
		
		btnDangXuat = new JButton("Đăng Xuất");
		btnDangXuat.setBackground(new Color(255, 255, 255));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDangXuat.setBounds(1094, 560, 160, 60);
		btnDangXuat.setIcon(new ImageIcon("img\\icons\\icons8-logout-32.png"));
		pnlBackGround.add(btnDangXuat);
		
		btnDoiMK = new JButton("Đổi Mật Khẩu");
		btnDoiMK.setBackground(new Color(255, 255, 255));
		btnDoiMK.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDoiMK.setBounds(904, 560, 180, 60);
		btnDoiMK.setIcon(new ImageIcon("img\\icons\\icons8-change-password-32.png"));
		pnlBackGround.add(btnDoiMK);
		
		btnCaiDat = new JButton("Cài Đặt");
		btnCaiDat.setBackground(new Color(255, 255, 255));
		btnCaiDat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCaiDat.setBounds(734, 560, 160, 60);
		btnCaiDat.setIcon(new ImageIcon("img\\icons\\icons8-setting-24.png"));
		pnlBackGround.add(btnCaiDat);
		
		JLabel lblUser = new JLabel("Xin Chào! ");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(918, 11, 82, 30);
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

		//Lê Minh Thật chỉnh lại đường dẫn 
//		lblNewLabel.setIcon(new ImageIcon("T:\\SalaryProductManagementSystem\\SalaryProductsManagementSystem\\img\\icons\\icons8-user-30.png"));
		//Lê Minh Thật chỉnh lại đường dẫn tương đối
		lblNewLabel.setIcon(new ImageIcon("\\img\\icons\\icons8-user-30.png"));

		lblNewLabel.setBounds(1205, 11, 30, 30);
		pnlBackGround.add(lblNewLabel);
		
		JLabel lblBackGround = new JLabel("");
		lblBackGround.setBackground(new Color(124, 252, 0));
		lblBackGround.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackGround.setIcon(new ImageIcon("img\\background\\Refund-bro_resize.png"));
		lblBackGround.setBounds(0, 0, 1264, 631);
		pnlBackGround.add(lblBackGround);
		
		mnHome.addMouseListener(this);
		mntmPCCD.addActionListener(this);
		mntmPCCN.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnCaiDat.addActionListener(this);
		btnDoiMK.addActionListener(this);
	}

	private String chuyenKieuNgay(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dtf.format(date);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnDangXuat)) {
			int chon = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất", "Lưu Ý", JOptionPane.YES_NO_OPTION);
			
			if (chon == JOptionPane.YES_OPTION) {
				taiKhoan_DAO.updateNgayCNCuoi(LocalDate.now(), tkMain);
				DangNhap_GUI dangNhap = new DangNhap_GUI();
				this.dispose();
				dangNhap.setVisible(true);
			}	
		}
		
		if (o.equals(btnDoiMK)) {
			DoiMatKhau_GUI doiMK = new DoiMatKhau_GUI();
			doiMK.setVisible(true);
			doiMK.setAlwaysOnTop(true);
		
		}
		
		if (o.equals(btnCaiDat)) {
			
		}
		
		if (o.equals(mntmPCCD)) {
			PhanChiaCongDoan_GUI pccd = new PhanChiaCongDoan_GUI();
			this.getContentPane().removeAll();
			getContentPane().add(menuBar);
			getContentPane().add(pccd.getPCCDUI());
			this.revalidate();
			this.repaint();
			this.setTitle("Phân chia công đoạn");
		}
		
		if (o.equals(mntmPCCN)) {
			PhanCongCongNhan_GUI pccn = new PhanCongCongNhan_GUI();
			this.getContentPane().removeAll();
			this.getContentPane().add(menuBar);
			this.getContentPane().add(pccn.getPCCNGUI());
			this.revalidate();
			this.repaint();
			this.setTitle("Phân chia công nhân");
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(mnHome)) {
			this.getContentPane().removeAll();
			getContentPane().add(menuBar);
			getContentPane().add(pnlBackGround);
			this.revalidate();
			this.repaint();
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

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
