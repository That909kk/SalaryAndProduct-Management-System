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
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		
		table = new JTable();
		table.setFont(UIManager.getFont("TableHeader.font"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
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
		table.setBounds(0, 50, 604, 185);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"M\u00E3 C\u00F4ng Nh\u00E2n", "T\u00EAn C\u00F4ng Nh\u00E2n", "L\u01B0\u01A1ng C\u01A1 b\u1EA3n", "L\u01B0\u01A1ng S\u1EA3n Ph\u1EA9m", "S\u1ED1 Ng\u00E0y l\u00E0m", "Th\u01B0\u1EDFng", "Kh\u1EA5u tr\u1EEB", "B\u1EA3o hi\u1EC3m x\u00E3 h\u1ED9i", "T\u1ED5ng L\u01B0\u01A1ng", "\u0110\u00E3 T\u00EDnh L\u01B0\u01A1ng", ""},
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
		table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_1.setBounds(0, 240, 1264, 362);
		contentPane.add(table_1);
		
		textField = new JTextField();
		textField.setBounds(840, 61, 341, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ghi Chú:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(624, 59, 98, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblSCngNhn = new JLabel("Số Công Nhân Chưa Tính Lương:");
		lblSCngNhn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSCngNhn.setBounds(624, 97, 221, 20);
		contentPane.add(lblSCngNhn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(840, 99, 341, 20);
		contentPane.add(textField_1);
		
		JLabel lblTngSCng = new JLabel("Tổng Số Công Nhân:");
		lblTngSCng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngSCng.setBounds(624, 139, 196, 20);
		contentPane.add(lblTngSCng);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(840, 141, 341, 20);
		contentPane.add(textField_2);
		
		JLabel lblTngSCng_1 = new JLabel("Tổng Cộng:");
		lblTngSCng_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngSCng_1.setBounds(624, 185, 197, 20);
		contentPane.add(lblTngSCng_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(840, 187, 341, 20);
		contentPane.add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(590, 65, 14, 170);
		contentPane.add(scrollPane);
		
		JLabel lblTmKimTheo = new JLabel("Tìm Kiếm Theo Tên:");
		lblTmKimTheo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTmKimTheo.setBounds(10, 629, 159, 20);
		contentPane.add(lblTmKimTheo);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(161, 631, 221, 20);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(392, 630, 39, 31);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("Button.border"));
		panel.setBounds(453, 613, 787, 57);
		contentPane.add(panel);
		GridLayout layout = new GridLayout(0, 5, 0, 0);
        layout.setHgap(10);
        layout.setVgap(10);
		panel.setLayout(layout);
		
		JButton btnNewButton_1 = new JButton("Xem Chi Tiết");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Tính Lương");
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Xóa");
		panel.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Hoàn Tất");
		panel.add(btnNewButton_1_2_1);
		
		JButton btnNewButton_1_2_2 = new JButton("In");
		panel.add(btnNewButton_1_2_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1252, 255, 12, 347);
		contentPane.add(scrollPane_1);
		btnNewButton_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
	}
}
