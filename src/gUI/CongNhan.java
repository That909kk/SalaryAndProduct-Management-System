package gUI;

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
import java.awt.TextField;
import java.awt.Panel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class CongNhan extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField txtNhpTn;
	private DefaultTableModel tableModel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CongNhan frame = new CongNhan();
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
	public CongNhan() {
		setFont(new Font("Times New Roman", Font.PLAIN, 20));
		setTitle("Công nhân");
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
		
		JLabel lblHodem = new JLabel("Họ đệm:");
		lblHodem.setBounds(30, 82, 70, 24);
		contentPane.add(lblHodem);
		lblHodem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblTen = new JLabel("Tên:");
		lblTen.setBounds(30, 126, 70, 24);
		contentPane.add(lblTen);
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setBounds(30, 160, 88, 33);
		contentPane.add(lblNgaysinh);
		lblNgaysinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setBounds(30, 203, 70, 24);
		contentPane.add(lblCCCD);
		lblCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		textField_2 = new JTextField();
		textField_2.setBounds(120, 82, 168, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(120, 126, 168, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField = new JTextField();
		textField.setText("       /       /");
		textField.setBounds(120, 160, 122, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 203, 168, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSDT.setBounds(306, 82, 54, 24);
		contentPane.add(lblSDT);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(412, 82, 249, 24);
		contentPane.add(textField_4);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDiaChi.setBounds(306, 126, 70, 24);
		contentPane.add(lblDiaChi);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(412, 126, 249, 24);
		contentPane.add(textField_5);
		
		JLabel lblBatDauLam = new JLabel("Bắt đầu làm:");
		lblBatDauLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBatDauLam.setBounds(306, 164, 101, 24);
		contentPane.add(lblBatDauLam);
		
		textField_6 = new JTextField();
		textField_6.setText("           /         /");
		textField_6.setColumns(10);
		textField_6.setBounds(412, 164, 184, 24);
		contentPane.add(textField_6);
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCaLam.setBounds(306, 203, 70, 24);
		contentPane.add(lblCaLam);
		
		JCheckBox chckbxSang = new JCheckBox("Sáng");
		chckbxSang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxSang.setBounds(412, 203, 70, 21);
		contentPane.add(chckbxSang);
		
		JCheckBox chckbxToi = new JCheckBox("Tối");
		chckbxToi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxToi.setBounds(490, 203, 54, 21);
		contentPane.add(chckbxToi);
		
		JCheckBox chckbxThem = new JCheckBox("Thêm");
		chckbxThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxThem.setBounds(573, 203, 88, 21);
		contentPane.add(chckbxThem);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGioiTinh.setBounds(30, 245, 88, 24);
		contentPane.add(lblGioiTinh);
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnNam.setBounds(120, 245, 70, 21);
		contentPane.add(rdbtnNam);
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		rdbtnNu.setBounds(216, 245, 60, 21);
		contentPane.add(rdbtnNu);
		
		JLabel lblChuyenMon = new JLabel("Chuyên môn:");
		lblChuyenMon.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblChuyenMon.setBounds(306, 245, 110, 24);
		contentPane.add(lblChuyenMon);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(423, 248, 223, 24);
		contentPane.add(textField_7);
		
		JLabel lblXuong = new JLabel("Xưởng:");
		lblXuong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblXuong.setBounds(667, 203, 70, 24);
		contentPane.add(lblXuong);
		
		String cb[] = {"May", "Gia công", "Nhuộm"};
		JComboBox comboBox = new JComboBox(cb);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox.setToolTipText("\r\n");
		comboBox.setBounds(753, 199, 101, 33);
		contentPane.add(comboBox);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTrangThai.setBounds(667, 245, 88, 24);
		contentPane.add(lblTrangThai);
		
		JCheckBox chckbxDangLam = new JCheckBox("Đang làm");
		chckbxDangLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		chckbxDangLam.setBounds(753, 245, 110, 21);
		contentPane.add(chckbxDangLam);
		
		txtNhpTn = new JTextField();
		txtNhpTn.setHorizontalAlignment(SwingConstants.CENTER);
		txtNhpTn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtNhpTn.setText("Nhập tên");
		txtNhpTn.setToolTipText("");
		txtNhpTn.setColumns(10);
		txtNhpTn.setBounds(897, 97, 110, 33);
		contentPane.add(txtNhpTn);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-search-30.png"));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(1017, 97, 54, 33);
		contentPane.add(btnNewButton);
		
		String cb1[] = {"2020", "2021", "2022"};
		JComboBox comboBox_1 = new JComboBox(cb1);
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox_1.setBounds(1091, 100, 135, 30);
		contentPane.add(comboBox_1);
		
		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXemChiTiet.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-info-25.png"));
		btnXemChiTiet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXemChiTiet.setBounds(897, 148, 174, 43);
		contentPane.add(btnXemChiTiet);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Desktop\\Thư mục mới\\SalaryAndProduct-Management-System\\img\\icons\\icons8-pencil-20.png"));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSua.setBounds(897, 216, 174, 43);
		contentPane.add(btnSua);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-add-friend-25.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBounds(1092, 148, 134, 43);
		contentPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-delete-30.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBounds(1092, 216, 135, 43);
		contentPane.add(btnXoa);
		
		JTable table = new JTable();
	    DefaultTableModel model = new DefaultTableModel();
	    table.setModel(model);
	    model.addColumn("Mã công nhân");
	    model.addColumn("Họ đệm");
	    model.addColumn("Tên");
	    model.addColumn("Tuổi");
	    model.addColumn("Ngày sinh");
	    model.addColumn("CCCD");
	    model.addColumn("Giới tính");
	    model.addColumn("SDT");
	    model.addColumn("Địa chỉ");
	    model.addColumn("Ngày bắt đầu làm");
	    model.addColumn("Ca làm việc");
	    model.addColumn("Chuyên môn");
	    model.addColumn("Bộ phận");
	    model.addColumn("Trạng thái làm việc");
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 301, 1246, 372);
		contentPane.add(scrollPane);
		
		JButton btnNgaySinh = new JButton("");
		btnNgaySinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNgaySinh.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-calendar-30.png"));
		btnNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNgaySinh.setBounds(252, 160, 36, 33);
		contentPane.add(btnNgaySinh);
		
		JButton btnBatDauLam = new JButton("");
		btnBatDauLam.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-calendar-30.png"));
		btnBatDauLam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnBatDauLam.setBounds(618, 160, 43, 33);
		contentPane.add(btnBatDauLam);
		
		JPanel panel_TacVu = new JPanel();
		panel_TacVu.setBorder(new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TacVu.setBounds(885, 54, 381, 237);
		contentPane.add(panel_TacVu);
		
		JLabel lblImage = new JLabel("image");
		lblImage.setBackground(new Color(0, 0, 0));
		lblImage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblImage.setBounds(671, 82, 184, 111);
		contentPane.add(lblImage);
		
		JPanel panel_ThongTin = new JPanel();
		panel_ThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ThongTin.setBounds(0, 52, 872, 239);
		contentPane.add(panel_ThongTin);
	}
}
