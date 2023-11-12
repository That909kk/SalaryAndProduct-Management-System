package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class LuongNhanVien_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnHome;
	private JTable tblThangLuongNhanVien;
	private JTable tblDSLuongNV;
	private JTextField txtGhiChuLuongNV;
	private JTextField txtSoNVChuaTinhLuong;
	private JTextField txtTongSoNV;
	private JTextField txtTongLuongCanTraNV;
	private JTextField txtTimKiemTheoTenNV;
	private DefaultTableModel modelTableThangLuongNV;
	private DefaultTableModel modelTableDSLuongNV;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LuongNhanVien frame = new LuongNhanVien();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LuongNhanVien_GUI() {
		super("Lương Nhân Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
//		JMenuBar menuBar = new JMenuBar();
//		menuBar.setBounds(0, 0, 1264, 50);
//		menuBar.setBackground(new Color(255, 255, 255));
//		contentPane.add(menuBar);
//		
//		mnHome = new JMenu("");
//		mnHome.setHorizontalAlignment(SwingConstants.CENTER);
//		mnHome.setIcon(new ImageIcon("D:\\PTUD_Project\\SalaryAndProduct-Management-System\\img\\icons\\icons8-home-40.gif"));
//		mnHome.setIconTextGap(20);
//		menuBar.add(mnHome);
//		
//		JMenu mnCongNhan = new JMenu("  CÔNG NHÂN  ");
//		mnCongNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		mnCongNhan.setHorizontalAlignment(SwingConstants.CENTER);
//		menuBar.add(mnCongNhan);
//		
//		JMenu mnNhanVien = new JMenu("  NHÂN VIÊN  ");
//		mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		menuBar.add(mnNhanVien);
//		
//		JMenu mnCongDoan = new JMenu("  CÔNG ĐOẠN  ");
//		mnCongDoan.setHorizontalAlignment(SwingConstants.CENTER);
//		mnCongDoan.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		menuBar.add(mnCongDoan);
//		
//		JMenu mnLuong = new JMenu("  LƯƠNG  ");
//		mnLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		menuBar.add(mnLuong);
//		
//		JMenu mnHopDong = new JMenu("  HỢP ĐỒNG  ");
//		mnHopDong.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		menuBar.add(mnHopDong);
//		
//		JMenu mnTroGiup = new JMenu("  TRỢ GIÚP  ");
//		mnTroGiup.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		menuBar.add(mnTroGiup);
//		
//		JMenu mnGioiThieu = new JMenu("  GIỚI THIỆU  ");
//		mnGioiThieu.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		menuBar.add(mnGioiThieu);
		
		contentPane.add(this.getLuongNVGUI());
		
	}
	
	public JPanel getLuongNVGUI() {
		JPanel pnlLuongNV = new JPanel();
		pnlLuongNV.setBackground(new Color(240, 248, 255));
		pnlLuongNV.setBounds(0, 50, 1264, 632);
		pnlLuongNV.setLayout(null);
		
		String headerThangLuong[] = {"Tháng", "Năm", "Bộ Phận"};
		modelTableThangLuongNV = new DefaultTableModel(headerThangLuong, 0);
		
		tblThangLuongNhanVien = new JTable(modelTableThangLuongNV);
		tblThangLuongNhanVien.setFont(UIManager.getFont("TableHeader.font"));
		tblThangLuongNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThangLuongNhanVien.setCellSelectionEnabled(true);
		tblThangLuongNhanVien.setBackground(new Color(255, 255, 255));
		
		JScrollPane scrThangLuong = new JScrollPane(tblThangLuongNhanVien);
		scrThangLuong.setBackground(new Color(255, 255, 255));
		scrThangLuong.setBounds(0, 0, 604, 190);
		pnlLuongNV.add(scrThangLuong);
		
		String headerDSLuong[] = {"Mã nhân viên", "Họ tên", "Lương cơ bản", "Bậc lương", "Hệ số lương", "Số ngày làm",
				"Thưởng ", "Khấu trừ", "Bảo hiểm xã hội", "Tổng lương", "Đã tính lương", "Ghi chú"};
		modelTableDSLuongNV = new DefaultTableModel(headerDSLuong, 0);
		tblDSLuongNV = new JTable(modelTableDSLuongNV);
		tblDSLuongNV.setFillsViewportHeight(true);
		tblDSLuongNV.setCellSelectionEnabled(true);
		tblDSLuongNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pnlLuongNV.add(tblDSLuongNV);
		
		txtGhiChuLuongNV = new JTextField();
		txtGhiChuLuongNV.setBounds(268, 29, 369, 30);
		txtGhiChuLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(txtGhiChuLuongNV);
		txtGhiChuLuongNV.setColumns(10);
		
		JLabel lblGhiChuLuongNV = new JLabel("Ghi Chú:");
		lblGhiChuLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiChuLuongNV.setBounds(21, 29, 98, 30);
		pnlLuongNV.add(lblGhiChuLuongNV);
		
		JLabel lblSoNVChuaTinhLuong = new JLabel("Số Nhân Viên Chưa Tính Lương:");
		lblSoNVChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoNVChuaTinhLuong.setBounds(21, 69, 243, 30);
		pnlLuongNV.add(lblSoNVChuaTinhLuong);
		
		txtSoNVChuaTinhLuong = new JTextField();
		txtSoNVChuaTinhLuong.setColumns(10);
		txtSoNVChuaTinhLuong.setBounds(268, 69, 369, 30);
		txtSoNVChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(txtSoNVChuaTinhLuong);
		
		JLabel lblTongSoNV = new JLabel("Tổng Số Nhân Viên:");
		lblTongSoNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongSoNV.setBounds(21, 109, 196, 30);
		pnlLuongNV.add(lblTongSoNV);
		
		txtTongSoNV = new JTextField();
		txtTongSoNV.setColumns(10);
		txtTongSoNV.setBounds(268, 109, 369, 30);
		txtTongSoNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(txtTongSoNV);
		
		JLabel lblTongLuongCanTraNV = new JLabel("Tổng Tiền Lương Cần Trả:");
		lblTongLuongCanTraNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongLuongCanTraNV.setBounds(21, 149, 197, 30);
		pnlLuongNV.add(lblTongLuongCanTraNV);
		
		txtTongLuongCanTraNV = new JTextField();
		txtTongLuongCanTraNV.setColumns(10);
		txtTongLuongCanTraNV.setBounds(268, 149, 369, 30);
		txtTongLuongCanTraNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(txtTongLuongCanTraNV);
		
		JScrollPane scrThangLuongNhanVien = new JScrollPane(tblDSLuongNV);
		scrThangLuongNhanVien.setBounds(0, 15, 14, 170);
		pnlLuongNV.add(scrThangLuongNhanVien);
		
		JLabel lblTimKiemTheoTenNV = new JLabel("Tìm Kiếm Theo Tên:");
		lblTimKiemTheoTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimKiemTheoTenNV.setBounds(10, 579, 159, 30);
		pnlLuongNV.add(lblTimKiemTheoTenNV);
		
		txtTimKiemTheoTenNV = new JTextField();
		txtTimKiemTheoTenNV.setColumns(10);
		txtTimKiemTheoTenNV.setBounds(161, 581, 221, 30);
		txtTimKiemTheoTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(txtTimKiemTheoTenNV);
		
		JButton btnTimKiemTheoTenNV = new JButton("");
		btnTimKiemTheoTenNV.setBounds(392, 580, 39, 30);
		btnTimKiemTheoTenNV.setBackground(new Color(255, 255, 255));
		btnTimKiemTheoTenNV.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));	
		pnlLuongNV.add(btnTimKiemTheoTenNV);
		
		JPanel pnlButtonLuongNV = new JPanel();
		pnlButtonLuongNV.setBorder(UIManager.getBorder("Button.border"));
		pnlButtonLuongNV.setBounds(453, 563, 787, 57);
		pnlLuongNV.add(pnlButtonLuongNV);
		
		GridLayout gl_pnlButtonLuongNV = new GridLayout(0, 5, 0, 0);
        gl_pnlButtonLuongNV.setHgap(10);
        gl_pnlButtonLuongNV.setVgap(10);
		pnlButtonLuongNV.setLayout(gl_pnlButtonLuongNV);
		
		JButton btnXemChiTietLuongNV = new JButton("Xem Chi Tiết");
		btnXemChiTietLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTietLuongNV.setBackground(new Color(255, 255, 255));
		pnlButtonLuongNV.add(btnXemChiTietLuongNV);
		
		JButton btnTinhLuongNV = new JButton("Tính Lương");
		btnTinhLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTinhLuongNV.setBackground(new Color(255, 255, 255));
		pnlButtonLuongNV.add(btnTinhLuongNV);
		
		JButton btnXoaKhoiDanhSachLuongNV = new JButton("Xóa");
		btnXoaKhoiDanhSachLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaKhoiDanhSachLuongNV.setBackground(new Color(255, 255 ,255));
		pnlButtonLuongNV.add(btnXoaKhoiDanhSachLuongNV);
		
		JButton btnHoanTatLuongNV = new JButton("Hoàn Tất");
		btnHoanTatLuongNV.setBackground(new Color(255, 255, 255));
		btnHoanTatLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongNV.add(btnHoanTatLuongNV);
		
		JButton btnInBangLuongNV = new JButton("In");
		btnInBangLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInBangLuongNV.setBackground(new Color(255, 255, 255));
		pnlButtonLuongNV.add(btnInBangLuongNV);
		
		JScrollPane scrDSLuongNV = new JScrollPane(tblDSLuongNV);
		scrDSLuongNV.setBounds(0, 205, 1268, 347);
		pnlLuongNV.add(scrDSLuongNV);
		
		JPanel pnlThongKeTinhLuong = new JPanel();
		pnlThongKeTinhLuong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongKeTinhLuong.setBackground(new Color(240, 248, 255));
		pnlThongKeTinhLuong.setBounds(604, 0, 660, 190);
		pnlLuongNV.add(pnlThongKeTinhLuong);
		pnlThongKeTinhLuong.setLayout(null);
		
		pnlThongKeTinhLuong.add(lblGhiChuLuongNV);
		pnlThongKeTinhLuong.add(lblSoNVChuaTinhLuong);
		pnlThongKeTinhLuong.add(lblTongLuongCanTraNV);
		pnlThongKeTinhLuong.add(lblTongSoNV);
		
		pnlThongKeTinhLuong.add(txtGhiChuLuongNV);
		pnlThongKeTinhLuong.add(txtSoNVChuaTinhLuong);
		pnlThongKeTinhLuong.add(txtTongLuongCanTraNV);
		pnlThongKeTinhLuong.add(txtTongSoNV);
		
		btnInBangLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnTinhLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		return pnlLuongNV;
	}
}
