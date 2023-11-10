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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LuongCongNhan frame = new LuongCongNhan();
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
	public LuongCongNhan() {
		super("Lương Cong Nhan");
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
		tblThangLuongCN.setBounds(0, 50, 604, 185);
		contentPane.add(tblThangLuongCN);
		
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
		tbtDSLuongCN.setBounds(0, 240, 1264, 362);
		contentPane.add(tbtDSLuongCN);
		
		txtGhiChuLuongCN = new JTextField();
		txtGhiChuLuongCN.setBounds(840, 61, 341, 20);
		contentPane.add(txtGhiChuLuongCN);
		txtGhiChuLuongCN.setColumns(10);
		
		JLabel lblGhiChuLuongCN = new JLabel("Ghi Chú:");
		lblGhiChuLuongCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGhiChuLuongCN.setBounds(624, 59, 98, 20);
		contentPane.add(lblGhiChuLuongCN);
		
		JLabel lblSoCNChuaTinhLuong = new JLabel("Số Công Nhân Chưa Tính Lương:");
		lblSoCNChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoCNChuaTinhLuong.setBounds(624, 97, 221, 20);
		contentPane.add(lblSoCNChuaTinhLuong);
		
		txtSoCNChuaTinhLuong = new JTextField();
		txtSoCNChuaTinhLuong.setColumns(10);
		txtSoCNChuaTinhLuong.setBounds(840, 99, 341, 20);
		contentPane.add(txtSoCNChuaTinhLuong);
		
		JLabel lblTongSoCN = new JLabel("Tổng Số Công Nhân:");
		lblTongSoCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongSoCN.setBounds(624, 139, 196, 20);
		contentPane.add(lblTongSoCN);
		
		txtTongSoCN = new JTextField();
		txtTongSoCN.setColumns(10);
		txtTongSoCN.setBounds(840, 141, 341, 20);
		contentPane.add(txtTongSoCN);
		
		JLabel lblTongLuongCanTraCN = new JLabel("Tổng Tiền Lương Cần Trả:");
		lblTongLuongCanTraCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongLuongCanTraCN.setBounds(624, 185, 197, 20);
		contentPane.add(lblTongLuongCanTraCN);
		
		txtTongLuongCanTraCN = new JTextField();
		txtTongLuongCanTraCN.setColumns(10);
		txtTongLuongCanTraCN.setBounds(840, 187, 341, 20);
		contentPane.add(txtTongLuongCanTraCN);
		
		JScrollPane scrThangLuongCN = new JScrollPane();
		scrThangLuongCN.setBounds(590, 65, 14, 170);
		contentPane.add(scrThangLuongCN);
		
		JLabel lblTmKimTheoCN = new JLabel("Tìm Kiếm Theo Tên:");
		lblTmKimTheoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTmKimTheoCN.setBounds(10, 629, 159, 20);
		contentPane.add(lblTmKimTheoCN);
		
		txtTimKiemTheoTenCN = new JTextField();
		txtTimKiemTheoTenCN.setColumns(10);
		txtTimKiemTheoTenCN.setBounds(161, 631, 221, 20);
		contentPane.add(txtTimKiemTheoTenCN);
		
		JButton btnTimKiemTenCN = new JButton("New button");
		btnTimKiemTenCN.setBounds(392, 630, 39, 31);
		contentPane.add(btnTimKiemTenCN);
		
		JPanel pnlButtonLuongCN = new JPanel();
		pnlButtonLuongCN.setBorder(UIManager.getBorder("Button.border"));
		pnlButtonLuongCN.setBounds(453, 613, 787, 57);
		contentPane.add(pnlButtonLuongCN);
		GridLayout gl_pnlButtonLuongCN = new GridLayout(0, 5, 0, 0);
        gl_pnlButtonLuongCN.setHgap(10);
        gl_pnlButtonLuongCN.setVgap(10);
		pnlButtonLuongCN.setLayout(gl_pnlButtonLuongCN);
		
		JButton btnXemChiTietLuongCN = new JButton("Xem Chi Tiết");
		pnlButtonLuongCN.add(btnXemChiTietLuongCN);
		
		JButton btnTinhLuongCN = new JButton("Tính Lương");
		pnlButtonLuongCN.add(btnTinhLuongCN);
		
		JButton btnXoaKhoiDanhSachLuongCN = new JButton("Xóa");
		pnlButtonLuongCN.add(btnXoaKhoiDanhSachLuongCN);
		
		JButton btnHoanTatLuongCN = new JButton("Hoàn Tất");
		pnlButtonLuongCN.add(btnHoanTatLuongCN);
		
		JButton btnInBangLuongCN = new JButton("In");
		pnlButtonLuongCN.add(btnInBangLuongCN);
		
		JScrollPane scrDSLuongCN = new JScrollPane();
		scrDSLuongCN.setBounds(1252, 255, 12, 347);
		contentPane.add(scrDSLuongCN);
		btnInBangLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTinhLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
}
