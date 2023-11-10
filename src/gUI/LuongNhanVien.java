package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
import java.awt.GridLayout;
import javax.swing.UIManager;

public class LuongNhanVien extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LuongNhanVien frame = new LuongNhanVien();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LuongNhanVien() {
		super("Lương Nhân Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1264, 50);
		menuBar.setBackground(new Color(255, 255, 255));
		contentPane.add(menuBar);
		
		mnHome = new JMenu("");
		mnHome.setHorizontalAlignment(SwingConstants.CENTER);
		mnHome.setIcon(new ImageIcon("D:\\PTUD_Project\\SalaryAndProduct-Management-System\\img\\icons\\icons8-home-40.gif"));
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
		
		tblThangLuongNhanVien = new JTable();
		tblThangLuongNhanVien.setFont(UIManager.getFont("TableHeader.font"));
		tblThangLuongNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThangLuongNhanVien.setCellSelectionEnabled(true);
		tblThangLuongNhanVien.setModel(new DefaultTableModel(
			new Object[][] {
				{"Th\u00E1ng", "N\u0103m", "B\u1ED9 Ph\u1EADn"},
			},
			new String[] {
				"Th\u00E1ng", "N\u0103m", "B\u1ED9 Ph\u1EADn"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblThangLuongNhanVien.setBounds(0, 50, 604, 185);
		contentPane.add(tblThangLuongNhanVien);
		
		tblDSLuongNV = new JTable();
		tblDSLuongNV.setFillsViewportHeight(true);
		tblDSLuongNV.setCellSelectionEnabled(true);
		tblDSLuongNV.setModel(new DefaultTableModel(
			new Object[][] {
				{"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "L\u01B0\u01A1ng C\u01A1 b\u1EA3n", "B\u1EADc l\u01B0\u01A1ng", "H\u1EC7 s\u1ED1 l\u01B0\u01A1ng", "S\u1ED1 ng\u00E0y l\u00E0m", "Th\u01B0\u1EDFng", "Kh\u1EA5u tr\u1EEB", "B\u1EA3o hi\u1EC3m x\u00E3 h\u1ED9i", "T\u1ED5ng L\u01B0\u01A1ng", "\u0110\u00E3 t\u00EDnh L\u01B0\u01A1ng", "Ghi Ch\u00FA"},
			},
			new String[] {
				"M\u00E3 Nh\u00E2n Vi\u00EAn", "T\u00EAn Nh\u00E2n Vi\u00EAn", "L\u01B0\u01A1ng C\u01A1 B\u1EA3n", "B\u1EADc l\u01B0\u01A1ng", "H\u1EC7 s\u1ED1 l\u01B0\u01A1ng", "S\u1ED1 ng\u00E0y l\u00E0m", "Th\u01B0\u1EDFng ", "Kh\u1EA5u tr\u1EEB", "B\u1EA3o hi\u1EC3m x\u00E3 h\u1ED9i", "T\u1ED5ng L\u01B0\u01A1ng", "\u0110\u00E3 t\u00EDnh L\u01B0\u01A1ng", "Ghi Ch\u00FA"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDSLuongNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblDSLuongNV.setBounds(0, 240, 1264, 362);
		contentPane.add(tblDSLuongNV);
		
		txtGhiChuLuongNV = new JTextField();
		txtGhiChuLuongNV.setBounds(830, 61, 341, 20);
		contentPane.add(txtGhiChuLuongNV);
		txtGhiChuLuongNV.setColumns(10);
		
		JLabel lblGhiChuLuongNV = new JLabel("Ghi Chú:");
		lblGhiChuLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGhiChuLuongNV.setBounds(624, 59, 98, 20);
		contentPane.add(lblGhiChuLuongNV);
		
		JLabel lblSoNVChuaTinhLuong = new JLabel("Số Nhân Viên Chưa Tính Lương:");
		lblSoNVChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoNVChuaTinhLuong.setBounds(624, 97, 204, 20);
		contentPane.add(lblSoNVChuaTinhLuong);
		
		txtSoNVChuaTinhLuong = new JTextField();
		txtSoNVChuaTinhLuong.setColumns(10);
		txtSoNVChuaTinhLuong.setBounds(830, 99, 341, 20);
		contentPane.add(txtSoNVChuaTinhLuong);
		
		JLabel lblTongSoNV = new JLabel("Tổng Số Nhân Viên:");
		lblTongSoNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongSoNV.setBounds(624, 139, 196, 20);
		contentPane.add(lblTongSoNV);
		
		txtTongSoNV = new JTextField();
		txtTongSoNV.setColumns(10);
		txtTongSoNV.setBounds(830, 141, 341, 20);
		contentPane.add(txtTongSoNV);
		
		JLabel lblTongLuongCanTraNV = new JLabel("Tổng Tiền Lương Cần Trả:");
		lblTongLuongCanTraNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongLuongCanTraNV.setBounds(624, 185, 197, 20);
		contentPane.add(lblTongLuongCanTraNV);
		
		txtTongLuongCanTraNV = new JTextField();
		txtTongLuongCanTraNV.setColumns(10);
		txtTongLuongCanTraNV.setBounds(830, 187, 341, 20);
		contentPane.add(txtTongLuongCanTraNV);
		
		JScrollPane scrThangLuongNhanVien = new JScrollPane();
		scrThangLuongNhanVien.setBounds(590, 65, 14, 170);
		contentPane.add(scrThangLuongNhanVien);
		
		JLabel lblTimKiemTheoTenNV = new JLabel("Tìm Kiếm Theo Tên:");
		lblTimKiemTheoTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimKiemTheoTenNV.setBounds(10, 629, 159, 20);
		contentPane.add(lblTimKiemTheoTenNV);
		
		txtTimKiemTheoTenNV = new JTextField();
		txtTimKiemTheoTenNV.setColumns(10);
		txtTimKiemTheoTenNV.setBounds(161, 631, 221, 20);
		contentPane.add(txtTimKiemTheoTenNV);
		
		JButton btnTimKiemTheoTenNV = new JButton("New button");
		btnTimKiemTheoTenNV.setBounds(392, 630, 39, 31);
		contentPane.add(btnTimKiemTheoTenNV);
		
		JPanel pnlButtonLuongNV = new JPanel();
		pnlButtonLuongNV.setBorder(UIManager.getBorder("Button.border"));
		pnlButtonLuongNV.setBounds(453, 613, 787, 57);
		contentPane.add(pnlButtonLuongNV);
		GridLayout gl_pnlButtonLuongNV = new GridLayout(0, 5, 0, 0);
        gl_pnlButtonLuongNV.setHgap(10);
        gl_pnlButtonLuongNV.setVgap(10);
		pnlButtonLuongNV.setLayout(gl_pnlButtonLuongNV);
		
		JButton btnXemChiTietLuongNV = new JButton("Xem Chi Tiết");
		pnlButtonLuongNV.add(btnXemChiTietLuongNV);
		
		JButton btnTinhLuongNV = new JButton("Tính Lương");
		pnlButtonLuongNV.add(btnTinhLuongNV);
		
		JButton btnXoaKhoiDanhSachLuongNV = new JButton("Xóa");
		pnlButtonLuongNV.add(btnXoaKhoiDanhSachLuongNV);
		
		JButton btnHoanTatLuongNV = new JButton("Hoàn Tất");
		pnlButtonLuongNV.add(btnHoanTatLuongNV);
		
		JButton btnInBangLuongNV = new JButton("In");
		pnlButtonLuongNV.add(btnInBangLuongNV);
		
		JScrollPane scrDSLuongNV = new JScrollPane();
		scrDSLuongNV.setBounds(1252, 255, 12, 347);
		contentPane.add(scrDSLuongNV);
		btnInBangLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTinhLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
}
