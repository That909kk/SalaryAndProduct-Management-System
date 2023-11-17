package gUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDayChooser;

import connectDB.ConnectDB;
import dao.CongNhan_DAO;
import entity.CongNhan;

import com.toedter.calendar.JDateChooser;

public class CongNhan_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_ThongTin;
	private JTextField txtTimKiem;
	private JTextField txtChuyenMon;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtCCCD;
	private JTextField txtTen;
	private JTextField txtHoDem;
	
	private CongNhan_DAO cn_DAO = new CongNhan_DAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CongNhan_GUI frame = new CongNhan_GUI();
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
	public CongNhan_GUI() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(this.getPNLCongNhan());
	}

	public JPanel getPNLCongNhan() {
		JPanel pnlCN = new JPanel();
		pnlCN.setBackground(new Color(240, 248, 255));
		pnlCN.setLayout(null);
		pnlCN.setBounds(0, 50, 1268, 632);
		
		panel_ThongTin = new JPanel();
		panel_ThongTin.setBackground(new Color(240, 248, 255));
		panel_ThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ThongTin.setBounds(10, 0, 872, 239);
		pnlCN.add(panel_ThongTin);
		panel_ThongTin.setLayout(null);
		
		JLabel lblHodem = new JLabel("Họ đệm:");
		lblHodem.setBounds(21, 26, 70, 24);
		panel_ThongTin.add(lblHodem);
		lblHodem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTen = new JLabel("Tên:");
		lblTen.setBounds(21, 70, 70, 24);
		panel_ThongTin.add(lblTen);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setBounds(21, 104, 88, 33);
		panel_ThongTin.add(lblNgaysinh);
		lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setBounds(21, 147, 70, 24);
		panel_ThongTin.add(lblCCCD);
		lblCCCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtHoDem = new JTextField();
		txtHoDem.setBounds(111, 26, 168, 24);
		panel_ThongTin.add(txtHoDem);
		txtHoDem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoDem.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.setBounds(111, 70, 168, 24);
		panel_ThongTin.add(txtTen);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTen.setColumns(10);
		
		txtCCCD = new JTextField();
		txtCCCD.setBounds(111, 147, 168, 24);
		panel_ThongTin.add(txtCCCD);
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setBounds(297, 26, 54, 24);
		panel_ThongTin.add(lblSDT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtSDT = new JTextField();
		txtSDT.setBounds(403, 26, 249, 24);
		panel_ThongTin.add(txtSDT);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(297, 70, 70, 24);
		panel_ThongTin.add(lblDiaChi);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(403, 70, 249, 24);
		panel_ThongTin.add(txtDiaChi);
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		
		JLabel lblBatDauLam = new JLabel("Bắt đầu làm:");
		lblBatDauLam.setBounds(297, 108, 101, 24);
		panel_ThongTin.add(lblBatDauLam);
		lblBatDauLam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(297, 147, 70, 24);
		panel_ThongTin.add(lblCaLam);
		lblCaLam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkSang = new JCheckBox("Sáng");
		chkSang.setBounds(403, 147, 70, 21);
		panel_ThongTin.add(chkSang);
		chkSang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkToi = new JCheckBox("Tối");
		chkToi.setBounds(481, 147, 54, 21);
		panel_ThongTin.add(chkToi);
		chkToi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkThem = new JCheckBox("Thêm");
		chkThem.setBounds(564, 147, 88, 21);
		panel_ThongTin.add(chkThem);
		chkThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setBounds(21, 189, 88, 24);
		panel_ThongTin.add(lblGioiTinh);
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(111, 189, 70, 21);
		panel_ThongTin.add(rdbtnNam);
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(207, 189, 60, 21);
		panel_ThongTin.add(rdbtnNu);
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblChuyenMon = new JLabel("Chuyên môn:");
		lblChuyenMon.setBounds(297, 189, 110, 24);
		panel_ThongTin.add(lblChuyenMon);
		lblChuyenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtChuyenMon = new JTextField();
		txtChuyenMon.setBounds(414, 192, 223, 24);
		panel_ThongTin.add(txtChuyenMon);
		txtChuyenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtChuyenMon.setColumns(10);
		
		JLabel lblXuong = new JLabel("Xưởng:");
		lblXuong.setBounds(658, 147, 70, 24);
		panel_ThongTin.add(lblXuong);
		lblXuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		String cbXuong[] = {"May", "Gia công", "Nhuộm"};
		JComboBox cboXuong = new JComboBox(cbXuong);
		cboXuong.setBounds(744, 143, 101, 33);
		panel_ThongTin.add(cboXuong);
		cboXuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboXuong.setToolTipText("\r\n");
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setBounds(658, 189, 88, 24);
		panel_ThongTin.add(lblTrangThai);
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkTrangThai = new JCheckBox("Đang làm");
		chkTrangThai.setBounds(744, 189, 110, 21);
		panel_ThongTin.add(chkTrangThai);
		chkTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblImage = new JLabel("image");
		lblImage.setBounds(662, 26, 184, 111);
		panel_ThongTin.add(lblImage);
		lblImage.setBackground(new Color(0, 0, 0));
		lblImage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JDateChooser dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(111, 104, 168, 30);
		panel_ThongTin.add(dcNgaySinh);
		
		JDateChooser dcNgayBatDauLam = new JDateChooser();
		dcNgayBatDauLam.setBounds(403, 104, 168, 30);
		panel_ThongTin.add(dcNgayBatDauLam);
		
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
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 240, 1246, 380);
		pnlCN.add(scrollPane);
		
		JPanel panel_TacVu = new JPanel();
		panel_TacVu.setBackground(new Color(240, 248, 255));
		panel_TacVu.setBorder(new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TacVu.setBounds(885, 0, 381, 237);
		pnlCN.add(panel_TacVu);
		panel_TacVu.setLayout(null);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(10, 30, 110, 33);
		panel_TacVu.add(txtTimKiem);
		txtTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setText("Nhập tên");
		txtTimKiem.setToolTipText("");
		txtTimKiem.setColumns(10);
		
		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBounds(130, 30, 54, 33);
		panel_TacVu.add(btnTim);
		btnTim.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		String cbNam[] = {"2020", "2021", "2022"};
		JComboBox cboNam = new JComboBox(cbNam);
		cboNam.setBounds(205, 33, 164, 30);
		panel_TacVu.add(cboNam);
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setBounds(205, 81, 164, 50);
		panel_TacVu.add(btnThem);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setBackground(new Color(255, 255, 255));
		btnXemChiTiet.setBounds(10, 81, 174, 50);
		panel_TacVu.add(btnXemChiTiet);
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXemChiTiet.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setBounds(10, 149, 174, 50);
		panel_TacVu.add(btnSua);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setBounds(205, 149, 164, 50);
		panel_TacVu.add(btnXoa);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		cn_DAO = new CongNhan_DAO();
		model.setRowCount(0);
		ArrayList<CongNhan> listCN = cn_DAO.getDSCongNhan();
		for (CongNhan cn : listCN) {
			model.addRow(new Object[] {cn.getMaCN(), cn.getHo(), cn.getTen(), 18, cn.getNgaySinh(), cn.getcCCD(),
					cn.isGioiTinh() ? "Nam" : "Nữ", cn.getSoDienThoai(), cn.getDiaChi(), cn.getNgayBatDauLamViec(),
							cn.getCaLamViec(), cn.getChuyenMon(), cn.getXuong().getMaXuong()});
		}
		
		return pnlCN;
	}
}
