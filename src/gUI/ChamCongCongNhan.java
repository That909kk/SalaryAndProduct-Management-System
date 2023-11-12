package gUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class ChamCongCongNhan extends JFrame {

	private JPanel contentPane;
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
					ChamCongCongNhan frame = new ChamCongCongNhan();
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
	public ChamCongCongNhan() {
		setTitle("Chấm công công nhân");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1266, 50);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-home-40.gif"));
		mnNewMenu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Công nhân");
		mnNewMenu_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Nhân viên");
		mnNewMenu_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Công đoạn");
		mnNewMenu_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Lương");
		mnNewMenu_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_4);
		
		JMenu mnNewMenu_5 = new JMenu("Hợp đồng");
		mnNewMenu_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_5);
		
		JMenu mnNewMenu_6 = new JMenu("Trợ giúp");
		mnNewMenu_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_6);
		
		JMenu mnNewMenu_7 = new JMenu("Giới thiệu");
		mnNewMenu_7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_7);
		
		JLabel lblNgayChamCong = new JLabel("Ngày chấm công:");
		lblNgayChamCong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgayChamCong.setBounds(24, 77, 141, 24);
		contentPane.add(lblNgayChamCong);
		
		textField = new JTextField();
		textField.setText("         /       /");
		textField.setBounds(175, 77, 154, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNgayChamCong = new JButton("");
		btnNgayChamCong.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-calendar-30.png"));
		btnNgayChamCong.setBounds(339, 69, 49, 39);
		contentPane.add(btnNgayChamCong);
		
		JLabel lblSanPhamSx = new JLabel("Sản phẩm sản xuất:");
		lblSanPhamSx.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSanPhamSx.setBounds(415, 77, 169, 24);
		contentPane.add(lblSanPhamSx);
		
		String cb[] = {"Áo thun", "Áo phông", "Áo sơ mi"};
		JComboBox comboBox = new JComboBox(cb);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox.setBounds(581, 72, 105, 34);
		contentPane.add(comboBox);
		
		JLabel lblCongdoan = new JLabel("Công đoạn:");
		lblCongdoan.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCongdoan.setBounds(725, 77, 99, 24);
		contentPane.add(lblCongdoan);
		
		JLabel lblXuong = new JLabel("Xưởng:");
		lblXuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblXuong.setBounds(993, 77, 68, 24);
		contentPane.add(lblXuong);
		
		String cb1[] = {"Nhuộm", "Gia Công", "Kiểm tra"};
		JComboBox comboBox_2 = new JComboBox(cb1);
		comboBox_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox_2.setBounds(834, 70, 105, 34);
		contentPane.add(comboBox_2);
		
		String cb2[] = {"Xưởng nhuộm 1", "Xưởng nhuộm 2", "Xưởng nhuộm 3"};
		JComboBox comboBox_1 = new JComboBox(cb2);
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox_1.setBounds(1071, 70, 169, 34);
		contentPane.add(comboBox_1);
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCaLam.setBounds(24, 127, 68, 24);
		contentPane.add(lblCaLam);
		
		JCheckBox chckbxSang = new JCheckBox("Sáng");
		chckbxSang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxSang.setBounds(98, 129, 68, 21);
		contentPane.add(chckbxSang);
		
		JCheckBox chckbxToi = new JCheckBox("Tối ");
		chckbxToi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxToi.setBounds(200, 129, 57, 21);
		contentPane.add(chckbxToi);
		
		JCheckBox chckbxThem = new JCheckBox("Thêm");
		chckbxThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxThem.setBounds(290, 129, 84, 21);
		contentPane.add(chckbxThem);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGhiChu.setBounds(823, 127, 99, 24);
		contentPane.add(lblGhiChu);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(907, 124, 333, 27);
		contentPane.add(textField_1);
		
		JLabel lblTimKiemTheoTen = new JLabel("Tìm kiếm theo tên:");
		lblTimKiemTheoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTimKiemTheoTen.setBounds(24, 637, 154, 24);
		contentPane.add(lblTimKiemTheoTen);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(182, 634, 169, 27);
		contentPane.add(textField_2);
		
		JButton btnTimKiem = new JButton("");
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-search-30.png"));
		btnTimKiem.setBounds(361, 630, 49, 35);
		contentPane.add(btnTimKiem);
		
		JLabel lblTong = new JLabel("Tổng số công nhân:");
		lblTong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTong.setBounds(461, 637, 169, 24);
		contentPane.add(lblTong);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(627, 634, 40, 27);
		contentPane.add(textField_3);
		
		JLabel lblDangLam = new JLabel("Đang làm:");
		lblDangLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDangLam.setBounds(693, 637, 84, 24);
		contentPane.add(lblDangLam);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(787, 634, 40, 27);
		contentPane.add(textField_4);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnCapNhat.setBounds(907, 626, 154, 39);
		contentPane.add(btnCapNhat);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnHoanTat.setBounds(1085, 626, 154, 39);
		contentPane.add(btnHoanTat);
		
		JTable table = new JTable();
	   	DefaultTableModel model = new DefaultTableModel();
	    	table.setModel(model);
	    	model.addColumn("Mã công nhân");
	   	model.addColumn("Tên công nhân");
	    	model.addColumn("Ca làm");
	    	model.addColumn("Sản lượng");
	    	model.addColumn("Số giờ tăng ca");
	    	model.addColumn("Ghi chú");
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(24, 161, 1216, 456);
		contentPane.add(scrollPane);
	}
}
