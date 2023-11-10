package gUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.GridLayout;
import javax.swing.UIManager;

public class LuongCongNhan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnHome;
	private JTable tblThangLuongCN;
	private JTable tbtDSLuongCN;
	private JTextField txtGhiChuLuongCN;
	private JTextField txtSoCNChuaTinhLuong;
	private JTextField txtTongSoCN;
	private JTextField txtTongLuongCanTraCN;
	private JTextField txtTimKiemTheoTenCN;

	/**
	 * Xoá phương thức main
	 * Huỳnh Kim Thành. Ngày 10/11/2023
	 */
	
	public LuongCongNhan() {
		super("Lương Cong Nhan");
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
		
		contentPane.add(this.getLuongCNGUI());
	}
	
	public JPanel getLuongCNGUI() {
		JPanel pnlLuongCN = new JPanel();
		pnlLuongCN.setBackground(new Color(240, 248, 255));
		pnlLuongCN.setBounds(0, 50, 1264, 632);
		pnlLuongCN.setLayout(null);
		
		tblThangLuongCN = new JTable();
		tblThangLuongCN.setFont(UIManager.getFont("TableHeader.font"));
		tblThangLuongCN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThangLuongCN.setCellSelectionEnabled(true);
		tblThangLuongCN.setModel(new DefaultTableModel(
			new Object[][] {
				{"Th\u00E1ng", "N\u0103m", "X\u01B0\u1EDFng", "T\u00EAn S\u1EA3n Ph\u1EA9m", "S\u1ED1 L\u01B0\u1EE3ng", "C\u00F4ng \u0110o\u1EA1n", "\u0110\u01A1n gi\u00E1"},
			},
			new String[] {
				"Th\u00E1ng", "N\u0103m", "X\u01B0\u1EDFng", "T\u00EAn S\u1EA3n Ph\u1EA9m", "S\u1ED1 l\u01B0\u1EE3ng", "C\u00F4ng \u0110o\u1EA1n", "\u0110\u01A1n gi\u00E1"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblThangLuongCN.setBounds(0, 0, 604, 185);
		pnlLuongCN.add(tblThangLuongCN);
		
		tbtDSLuongCN = new JTable();
		tbtDSLuongCN.setFillsViewportHeight(true);
		tbtDSLuongCN.setCellSelectionEnabled(true);
		tbtDSLuongCN.setModel(new DefaultTableModel(
			new Object[][] {
				{"M\u00E3 C\u00F4ng Nh\u00E2n", "T\u00EAn C\u00F4ng Nh\u00E2n", "L\u01B0\u01A1ng C\u01A1 b\u1EA3n", "L\u01B0\u01A1ng S\u1EA3n Ph\u1EA9m", "S\u1ED1 Ng\u00E0y l\u00E0m", "Th\u01B0\u1EDFng", "Kh\u1EA5u tr\u1EEB", "B\u1EA3o hi\u1EC3m x\u00E3 h\u1ED9i", "T\u1ED5ng L\u01B0\u01A1ng", "\u0110\u00E3 T\u00EDnh L\u01B0\u01A1ng", "Ghi Ch\u00FA"},
			},
			new String[] {
				"M\u00E3 C\u00F4ng Nh\u00E2n", "T\u00EAn C\u00F4ng Nh\u00E2n", "L\u01B0\u01A1ng C\u01A1 b\u1EA3n", "L\u01B0\u01A1ng S\u1EA3n Ph\u1EA9m", "S\u1ED1 Ng\u00E0y l\u00E0m", "Th\u01B0\u1EDFng ", "Kh\u1EA5u tr\u1EEB", "B\u1EA3o hi\u1EC3m x\u00E3 h\u1ED9i", "T\u1ED5ng L\u01B0\u01A1ng", "\u0110\u00E3 T\u00EDnh L\u01B0\u01A1ng", "Ghi Ch\u00FA"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbtDSLuongCN.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tbtDSLuongCN.setBounds(0, 190, 1264, 362);
		pnlLuongCN.add(tbtDSLuongCN);
		
		JScrollPane scrThangLuongCN = new JScrollPane();
		scrThangLuongCN.setBounds(590, 15, 14, 170);
		pnlLuongCN.add(scrThangLuongCN);
		
		JLabel lblTmKimTheoCN = new JLabel("Tìm Kiếm Theo Tên:");
		lblTmKimTheoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTmKimTheoCN.setBounds(10, 579, 159, 32);
		pnlLuongCN.add(lblTmKimTheoCN);
		
		txtTimKiemTheoTenCN = new JTextField();
		txtTimKiemTheoTenCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiemTheoTenCN.setColumns(10);
		txtTimKiemTheoTenCN.setBounds(161, 579, 221, 32);
		pnlLuongCN.add(txtTimKiemTheoTenCN);
		
		JButton btnTimKiemTenCN = new JButton();
		btnTimKiemTenCN.setBackground(new Color(255, 255, 255));
		btnTimKiemTenCN.setBounds(392, 580, 39, 31);
		btnTimKiemTenCN.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		pnlLuongCN.add(btnTimKiemTenCN);
		
		JPanel pnlButtonLuongCN = new JPanel();
		pnlButtonLuongCN.setBackground(new Color(240, 248, 255));
		pnlButtonLuongCN.setBorder(UIManager.getBorder("Button.border"));
		pnlButtonLuongCN.setBounds(453, 563, 811, 57);
		pnlLuongCN.add(pnlButtonLuongCN);
		GridLayout gl_pnlButtonLuongCN = new GridLayout(0, 5, 0, 0);
        gl_pnlButtonLuongCN.setHgap(10);
        gl_pnlButtonLuongCN.setVgap(10);
		pnlButtonLuongCN.setLayout(gl_pnlButtonLuongCN);
		
		JButton btnXemChiTietLuongCN = new JButton("Xem Chi Tiết");
		btnXemChiTietLuongCN.setBackground(new Color(255, 255, 255));
		btnXemChiTietLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnXemChiTietLuongCN);
		
		JButton btnTinhLuongCN = new JButton("Tính Lương");
		btnTinhLuongCN.setBackground(new Color(255, 255, 255));
		btnTinhLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnTinhLuongCN);
		
		JButton btnXoaKhoiDanhSachLuongCN = new JButton("Xóa");
		btnXoaKhoiDanhSachLuongCN.setBackground(new Color(255, 255, 255));
		btnXoaKhoiDanhSachLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaKhoiDanhSachLuongCN.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		pnlButtonLuongCN.add(btnXoaKhoiDanhSachLuongCN);
		
		JButton btnHoanTatLuongCN = new JButton("Hoàn Tất");
		btnHoanTatLuongCN.setBackground(new Color(255, 255, 255));
		btnHoanTatLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHoanTatLuongCN.setIcon(new ImageIcon("img\\icons\\icons8-checked-checkbox-24.png"));
		pnlButtonLuongCN.add(btnHoanTatLuongCN);
		
		JButton btnInBangLuongCN = new JButton("In");
		btnInBangLuongCN.setBackground(new Color(255, 255, 255));
		btnInBangLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnInBangLuongCN);
		
		JScrollPane scrDSLuongCN = new JScrollPane();
		scrDSLuongCN.setBounds(1252, 205, 12, 347);
		pnlLuongCN.add(scrDSLuongCN);
		
		JPanel pnlThongKeTinhLuong = new JPanel();
		pnlThongKeTinhLuong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongKeTinhLuong.setBackground(new Color(240, 248, 255));
		pnlThongKeTinhLuong.setBounds(604, 0, 660, 190);
		pnlLuongCN.add(pnlThongKeTinhLuong);
		pnlThongKeTinhLuong.setLayout(null);
		
		txtGhiChuLuongCN = new JTextField();
		txtGhiChuLuongCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGhiChuLuongCN.setBounds(268, 29, 369, 30);
		pnlThongKeTinhLuong.add(txtGhiChuLuongCN);
		txtGhiChuLuongCN.setColumns(10);
		
		txtSoCNChuaTinhLuong = new JTextField();
		txtSoCNChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoCNChuaTinhLuong.setBounds(268, 69, 369, 30);
		pnlThongKeTinhLuong.add(txtSoCNChuaTinhLuong);
		txtSoCNChuaTinhLuong.setColumns(10);
		
		txtTongSoCN = new JTextField();
		txtTongSoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongSoCN.setBounds(268, 109, 369, 30);
		pnlThongKeTinhLuong.add(txtTongSoCN);
		txtTongSoCN.setColumns(10);
		
		txtTongLuongCanTraCN = new JTextField();
		txtTongLuongCanTraCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongLuongCanTraCN.setBounds(268, 149, 369, 30);
		pnlThongKeTinhLuong.add(txtTongLuongCanTraCN);
		txtTongLuongCanTraCN.setColumns(10);
		
		JLabel lblGhiChuLuongCN = new JLabel("Ghi Chú:");
		lblGhiChuLuongCN.setBounds(21, 29, 98, 30);
		pnlThongKeTinhLuong.add(lblGhiChuLuongCN);
		lblGhiChuLuongCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblSoCNChuaTinhLuong = new JLabel("Số Công Nhân Chưa Tính Lương:");
		lblSoCNChuaTinhLuong.setBounds(21, 69, 243, 30);
		pnlThongKeTinhLuong.add(lblSoCNChuaTinhLuong);
		lblSoCNChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTongSoCN = new JLabel("Tổng Số Công Nhân:");
		lblTongSoCN.setBounds(21, 109, 196, 30);
		pnlThongKeTinhLuong.add(lblTongSoCN);
		lblTongSoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTongLuongCanTraCN = new JLabel("Tổng Tiền Lương Cần Trả:");
		lblTongLuongCanTraCN.setBounds(21, 149, 197, 30);
		pnlThongKeTinhLuong.add(lblTongLuongCanTraCN);
		lblTongLuongCanTraCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnInBangLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnTinhLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		return pnlLuongCN;
	}
}
