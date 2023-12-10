package gUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

import connectDB.ConnectDB;
import dao.HopDong_DAO;
import dao.NhanVien_DAO;
import dao.TaiKhoan_DAO;
import entity.TaiKhoan;
/**
 * Lớp này dùng để tạo giao diện chính
 * Tạo bởi: Huỳnh Kim Thành - 21086351
 * ngày: 25/10/2023
 */
public class GiaoDienChinh_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {	
	private JPanel contentPane;

	private JTextField txtDate;
	private JTextField txtNumberNV;
	private JTextField txtNumHD;
	private JTextField txtName;
	
	private JButton btnDangXuat;
	private JButton btnDoiMK;
	private JButton btnCaiDat;

	private JPanel pnlBackGround;
	private JMenuBar menuBar;
	private JMenu mnHome;
	private JMenu mnCongNhan;
	private JMenu mnNhanVien;
	private JMenu mnCongDoan;
	private JMenu mnLuong;
	private JMenu mnHopDong;
	private JMenu mnTroGiup;
	private JMenu mnGioiThieu;
	private JMenuItem mntmPCCD;
	private JMenuItem mntmPCCN;
	private JMenuItem mntmLuongCN;
	private JMenuItem mntmLuongNV;
	
	private TaiKhoan_DAO taiKhoan_DAO = new TaiKhoan_DAO();
	private TaiKhoan tkMain = null;
	private HopDong_DAO hd_DAO;
	private NhanVien_DAO nv_DAO;
	
	private NhanVien_GUI nv_GUI;
	private JMenuItem mniQuanLyCN;
	private JMenuItem mniCCCN;
	private JMenuItem mniQuanLyNV;
	private JMenuItem mniCCNV;
	private JMenuItem mniHopDong;
	private JMenuItem mniSanPham;
	private ArrayList<JMenuItem> listMenu = new ArrayList<JMenuItem>();
	/**
	 * Create the frame.
	 */
	public GiaoDienChinh_GUI(TaiKhoan tk) {		
		super("Màn hình chính");
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		hd_DAO = new HopDong_DAO();
		hd_DAO.getDSHopDong();
		nv_DAO = new NhanVien_DAO();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();

		tkMain = tk;
		createGUI(tk);
		
	}
	
	public void createGUI(TaiKhoan tk) {
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 1264, 50);
		contentPane.add(menuBar);
		
		mnHome = new JMenu("");
		mnHome.setForeground(new Color(240, 248, 255));
		mnHome.setBackground(new Color(0, 255, 127));
		mnHome.setHorizontalAlignment(SwingConstants.CENTER);
		mnHome.setIcon(new ImageIcon("img\\icons\\icons8-home-40.gif"));
		mnHome.setIconTextGap(20);
		menuBar.add(mnHome);
		
		mnCongNhan = new JMenu("  CÔNG NHÂN  ");
		mnCongNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCongNhan.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnCongNhan);
		
		mniQuanLyCN = new JMenuItem("Quản Lý Công Nhân");
		mniQuanLyCN.setBorder(new EmptyBorder(10, 6, 10, 0));
		mniQuanLyCN.setBackground(new Color(255, 255, 255));
		mniQuanLyCN.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCongNhan.add(mniQuanLyCN);
		
		mniCCCN = new JMenuItem("Chấm Công Công Nhân");
		mniCCCN.setBorder(new EmptyBorder(10, 6, 10, 0));
		mniCCCN.setBackground(new Color(255, 255, 255));
		mniCCCN.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCongNhan.add(mniCCCN);
		
		mnNhanVien = new JMenu("  NHÂN VIÊN  ");
		mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnNhanVien);
		
		mniQuanLyNV = new JMenuItem("Quản Lý Nhân Viên");
		mniQuanLyNV.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mniQuanLyNV.setBorder(new EmptyBorder(10, 6, 10, 0));
		mniQuanLyNV.setBackground(new Color(255, 255, 255));
		mnNhanVien.add(mniQuanLyNV);
		
		mniCCNV = new JMenuItem("Chấm Công Nhân Viên");
		mniCCNV.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mniCCNV.setBorder(new EmptyBorder(10, 6, 10, 0));
		mniCCNV.setBackground(new Color(255, 255, 255));
		mnNhanVien.add(mniCCNV);
		
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
		
		mntmLuongCN = new JMenuItem("Lương Công Nhân");
		mntmLuongCN.setBorder(new EmptyBorder(10, 5, 10, 0));
		mntmLuongCN.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnLuong.add(mntmLuongCN);
		
		mntmLuongNV = new JMenuItem("Lương Nhân Viên");
		mntmLuongNV.setBorder(new EmptyBorder(10, 5, 10, 0));
		mntmLuongNV.setMargin(new Insets(6, 2, 6, 2));
		mntmLuongNV.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnLuong.add(mntmLuongNV);
		
		mnHopDong = new JMenu("  HỢP ĐỒNG  ");
		mnHopDong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(mnHopDong);
		
		mniHopDong = new JMenuItem("Quản Lý Hợp Đồng");
		mniHopDong.setBorder(new EmptyBorder(10, 6, 10, 0));
		mniHopDong.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mniHopDong.setBackground(new Color(255, 255, 255));
		mnHopDong.add(mniHopDong);
		
		mniSanPham = new JMenuItem("Quản Lý Sản Phẩm");
		mniSanPham.setBorder(new EmptyBorder(10, 6, 10, 0));
		mniSanPham.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mniSanPham.setBackground(new Color(255, 255, 255));
		mnHopDong.add(mniSanPham);
		
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
		lblTongNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongNhanVien.setBounds(10, 50, 250, 30);
		pnlBackGround.add(lblTongNhanVien);
		
		txtNumberNV = new JTextField();
		txtNumberNV.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtNumberNV.setEditable(false);
		txtNumberNV.setColumns(30);
		txtNumberNV.setBorder(null);
		txtNumberNV.setBackground(new Color(224, 255, 255));
		txtNumberNV.setBounds(261, 50, 100, 30);
		pnlBackGround.add(txtNumberNV);
		
		txtNumHD = new JTextField();
		txtNumHD.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtNumHD.setEditable(false);
		txtNumHD.setColumns(30);
		txtNumHD.setBorder(null);
		txtNumHD.setBackground(new Color(224, 255, 255));
		txtNumHD.setBounds(210, 90, 70, 30);
		pnlBackGround.add(txtNumHD);
		
		JLabel lblHopDong = new JLabel("Số hợp đồng hiện có:");
		lblHopDong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHopDong.setBounds(10, 90, 200, 30);
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
		lblUser.setBounds(918, 11, 90, 30);
		pnlBackGround.add(lblUser);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(224, 255, 255));
		txtName.setEditable(false);
		txtName.setFont(new Font("Tahoma", Font.BOLD, 18));
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
		

		String dateFormatted = chuyenKieuNgay(tk.getNgayDNCuoi());
		String hoTen = nv_DAO.getMotNVTuMaNV(tk.getNv().getMaNV()).getHo() + " " 
						+ nv_DAO.getMotNVTuMaNV(tk.getNv().getMaNV()).getTen();
		
		switch (taiKhoan_DAO.getBoPhanCuaNV(tk)) {
		case "BPNS" -> {
			txtNumberNV.setText(nv_DAO.getListNV().size() + "");
			txtNumHD.setText(hd_DAO.getSize() + "");
			txtDate.setText(dateFormatted);
			txtName.setText(hoTen);
			mnLuong.setVisible(false);
			mniCCCN.setVisible(false);
		}
		
		case "BPKT" -> {
			txtNumHD.setText(hd_DAO.getSize() + "");
			txtName.setText(hoTen);
			txtDate.setText(dateFormatted);
			txtNumberNV.setText(nv_DAO.getListNV().size() + "");
			mniCCCN.setVisible(false);
			mniCCNV.setVisible(false);
			mnCongDoan.setVisible(false);
		}
		
		case "QLXU" -> {
			txtDate.setText(dateFormatted);
			txtName.setText(hoTen);
			mnNhanVien.setVisible(false);
			mniHopDong.setVisible(false);
		}
		
		default ->
		throw new IllegalArgumentException("Unexpected value: ");
		}
		
		listMenu.add(mnHome);
		listMenu.add(mnCongNhan);
		listMenu.add(mnNhanVien);
		listMenu.add(mnHopDong);
		listMenu.add(mnLuong);
		listMenu.add(mnGioiThieu);
		listMenu.add(mnTroGiup);
		listMenu.add(mniQuanLyCN);
		listMenu.add(mniQuanLyNV);
		listMenu.add(mniHopDong);
		listMenu.add(mniCCCN);
		listMenu.add(mniCCNV);
		listMenu.add(mniSanPham);
		listMenu.add(mntmLuongCN);
		listMenu.add(mntmLuongNV);
		
		mnHome.addMouseListener(this);
		mniQuanLyCN.addActionListener(this);
		mniQuanLyNV.addActionListener(this);
		mniCCCN.addActionListener(this);
		mniCCNV.addActionListener(this);
		mntmPCCD.addActionListener(this);
		mntmPCCN.addActionListener(this);
		mniHopDong.addActionListener(this);
		mniSanPham.addActionListener(this);
		mntmLuongCN.addActionListener(this);
		mntmLuongNV.addActionListener(this);
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
		
		if (o.equals(mniQuanLyCN)) {
			CongNhan_GUI cn_GUI = new CongNhan_GUI();
			chuyenGUI(cn_GUI.createGUI());
			chuyenMauKhiAnMenu(mnCongNhan, mniQuanLyCN);
			this.setTitle("Quản Lý Công Nhân");
		}
		
		if (o.equals(mniQuanLyNV)) {
			nv_GUI = new NhanVien_GUI();
			chuyenGUI(nv_GUI.createGUI());
			chuyenMauKhiAnMenu(mnNhanVien, mniQuanLyNV);
			this.setTitle("Quản Lý Nhân Viên");
		}
		
		if (o.equals(mniCCCN)) {
			ChamCongCN_GUI cccn_GUI = new ChamCongCN_GUI();
			chuyenGUI(cccn_GUI.createGUI());
			chuyenMauKhiAnMenu(mnCongNhan, mniCCCN);
			this.setTitle("Chấm Công Công Nhân");
		}
		
		if (o.equals(mniCCNV)) {
			ChamCongNV_GUI ccnv_GUI = new ChamCongNV_GUI();
			chuyenGUI(ccnv_GUI.createGUI());
			chuyenMauKhiAnMenu(mnNhanVien, mniCCNV);
			this.setTitle("Chấm Công Nhân Viên");
		}
		
		if (o.equals(mntmPCCD)) {
			PhanChiaCongDoan_GUI pccd = new PhanChiaCongDoan_GUI();
			chuyenGUI(pccd.getPCCDUI());
			chuyenMauKhiAnMenu(mnCongDoan, mntmPCCD);
			this.setTitle("Phân chia công đoạn");
		}
		
		if (o.equals(mntmPCCN)) {
			PhanCongCongNhan_GUI pccn = new PhanCongCongNhan_GUI();
			chuyenGUI(pccn.getPCCNGUI());
			chuyenMauKhiAnMenu(mnCongDoan, mntmPCCN);
			this.setTitle("Phân công công nhân");
		}
		
		if (o.equals(mntmLuongCN)) {
			LuongCongNhan_GUI lcn = new LuongCongNhan_GUI();
			chuyenGUI(lcn.getLuongCNGUI());
			chuyenMauKhiAnMenu(mnLuong, mntmLuongCN);
			this.setTitle("Bảng Lương Công Nhân");
		}
		
		if (o.equals(mntmLuongNV)) {
			LuongNhanVien_GUI lnv = new LuongNhanVien_GUI();
			chuyenGUI(lnv.getLuongNVGUI());
			chuyenMauKhiAnMenu(mnLuong, mntmLuongNV);
			this.setTitle("Bảng Lương Nhân Viên");
		}
		
		if (o.equals(mniHopDong)) {
			HopDong_GUI hd_GUI = new HopDong_GUI();
			chuyenGUI(hd_GUI.createGUI());
			chuyenMauKhiAnMenu(mnHopDong, mniHopDong);
			this.setTitle("Quản Lý Hợp Đồng");
		}
		
		if (o.equals(mniSanPham)) {
			SanPham_GUI sp_GUI = new SanPham_GUI();
			chuyenGUI(sp_GUI.createGUI());
			chuyenMauKhiAnMenu(mnHopDong, mniSanPham);
			this.setTitle("Quản Lý Sản Phẩm");
		}
	}
	
	/**
	 * Huỳnh Kim Thành - 11/11/2023
	 * chuyenGUI cho phép truyền vào môt Jpanel để thực hiện việc chuyển đổi giữa các giao diện
	 * @param panelGUI
	 */
	private void chuyenGUI(JPanel pnlGUI) {
		this.getContentPane().removeAll();
		getContentPane().add(menuBar);
		getContentPane().add(pnlGUI);
		this.revalidate();
		this.repaint();
	}
	/**
	 * Phương thức chuyển màu Jmenu và JmenuItem khi nhấn
	 * @param menu
	 * @param menuItem
	 */
	private void chuyenMauKhiAnMenu(JMenu menu, JMenuItem menuItem) {
		for (JMenuItem item : listMenu) {
			item.setBackground(Color.WHITE);
			item.setForeground(Color.BLACK);
		}
		
		menu.setForeground(new Color(95, 189, 255));
		menuItem.setForeground(Color.WHITE);
		menuItem.setBackground(new Color(0, 169, 255));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(mnHome)) {
			chuyenGUI(pnlBackGround);
			this.setTitle("Màn Hình Chính");
			mnHome.setBackground(new Color(0, 169, 255));
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
