package gUI;
/**
 * Lớp này dùng để tạo giao diện phân chia công đoạn
 * Tạo bởi: Huỳnh Kim Thành - 21086351
 * ngày: 2/11/2023
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.ScrollPane;

public class PhanChiaCongDoan_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel Menu;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtMaCD;
	private JLabel lblTenCD;
	private JTextField txtTenCD;
	private JTextField txtGiaTien;
	private JTextField txtGiaCongNan;
	private JTextField txtNDTim;
	
	private JTable tableCongDoan;
	private DefaultTableModel modelCongDoan;
	/**
	 * Create the frame.
	 */
	public PhanChiaCongDoan_GUI() {
		super("Phân chia công đoạn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(this.PCCD());
		
	}
	
	public JPanel PCCD() {
		JPanel pnlPCCD = new JPanel();
		pnlPCCD.setBackground(new Color(240, 248, 255));
		pnlPCCD.setBounds(0, 49, 1264, 632);
		pnlPCCD.setLayout(null);
		
		
		
		String[] header = {"Mã hợp đồng", "Tên sản phẩm", "Số lượng", "Số lượng công đoạn hiện có", "Ngày kết thúc hợp đồng"};
		model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(26);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		model.addRow(new Object[] {"Mã hợp đồng", "Tên sản phẩm", 1000, 0, "13/11/2023"});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(0, 0, 640, 240);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlPCCD.add(scrollPane);
		
		JPanel pnlThongTinCongDoan = new JPanel();
		pnlThongTinCongDoan.setBackground(new Color(240, 248, 255));
		pnlThongTinCongDoan.setBorder(new TitledBorder(null, "C\u00F4ng \u0110o\u1EA1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinCongDoan.setBounds(644, 0, 618, 240);
		pnlPCCD.add(pnlThongTinCongDoan);
		pnlThongTinCongDoan.setLayout(null);
		
		JLabel lblMaCD = new JLabel("Mã công đoạn:");
		lblMaCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMaCD.setBounds(15, 20, 100, 26);
		pnlThongTinCongDoan.add(lblMaCD);
		
		txtMaCD = new JTextField();
		txtMaCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMaCD.setEnabled(false);
		txtMaCD.setBounds(125, 20, 164, 26);
		pnlThongTinCongDoan.add(txtMaCD);
		txtMaCD.setColumns(10);
		
		lblTenCD = new JLabel("Tên công đoạn:");
		lblTenCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenCD.setBounds(15, 60, 110, 26);
		pnlThongTinCongDoan.add(lblTenCD);
		
		txtTenCD = new JTextField();
		txtTenCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenCD.setColumns(10);
		txtTenCD.setBounds(125, 60, 164, 26);
		pnlThongTinCongDoan.add(txtTenCD);
		
		JLabel lblNgayBatDau = new JLabel("Ngày bắt đầu:");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayBatDau.setBounds(15, 100, 110, 26);
		pnlThongTinCongDoan.add(lblNgayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayKetThuc.setBounds(15, 140, 110, 26);
		pnlThongTinCongDoan.add(lblNgayKetThuc);
		
		JLabel lblTienQuyet = new JLabel("Công đoạn tiên quyết:");
		lblTienQuyet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTienQuyet.setBounds(300, 20, 160, 26);
		pnlThongTinCongDoan.add(lblTienQuyet);
		
		JComboBox cboMaCDTienQuyet = new JComboBox();
		cboMaCDTienQuyet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboMaCDTienQuyet.setBounds(455, 20, 149, 26);
		pnlThongTinCongDoan.add(cboMaCDTienQuyet);
		
		JLabel lblGiaTien = new JLabel("Giá tiền: ");
		lblGiaTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGiaTien.setBounds(300, 60, 160, 26);
		pnlThongTinCongDoan.add(lblGiaTien);
		
		txtGiaTien = new JTextField();
		txtGiaTien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiaTien.setColumns(10);
		txtGiaTien.setBounds(455, 60, 149, 26);
		pnlThongTinCongDoan.add(txtGiaTien);
		
		JLabel lblSoCongNhan = new JLabel("Số công nhân dự kiến:");
		lblSoCongNhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoCongNhan.setBounds(300, 100, 160, 26);
		pnlThongTinCongDoan.add(lblSoCongNhan);
		
		txtGiaCongNan = new JTextField();
		txtGiaCongNan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtGiaCongNan.setColumns(10);
		txtGiaCongNan.setBounds(455, 100, 149, 26);
		pnlThongTinCongDoan.add(txtGiaCongNan);
		
		JButton btnXem = new JButton("Xem chi tiết");
		btnXem.setBackground(new Color(255, 255, 255));
		btnXem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXem.setBounds(10, 177, 140, 50);
		pnlThongTinCongDoan.add(btnXem);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBounds(160, 177, 140, 50);
		pnlThongTinCongDoan.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSua.setBounds(314, 177, 140, 50);
		pnlThongTinCongDoan.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBounds(464, 177, 140, 50);
		pnlThongTinCongDoan.add(btnXoa);
		
		JDateChooser dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.getCalendarButton().setBackground(new Color(255, 255, 255));
		dcNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dcNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcNgayBatDau.setBackground(new Color(255, 255, 255));
		dcNgayBatDau.setBounds(125, 100, 164, 26);
		dcNgayBatDau.setDate(new Date());
		pnlThongTinCongDoan.add(dcNgayBatDau);
		
		JDateChooser dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.getCalendarButton().setBackground(new Color(255, 255, 255));
		dcNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		dcNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcNgayKetThuc.setBackground(new Color(255, 255, 255));
		dcNgayKetThuc.setBounds(125, 140, 164, 26);
		
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		
		dcNgayKetThuc.setDate(currentDate.getTime());
		pnlThongTinCongDoan.add(dcNgayKetThuc);
		
		JPanel pnlCacCongDoan = new JPanel();
		pnlCacCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCacCongDoan.setBackground(new Color(240, 248, 255));
		pnlCacCongDoan.setBorder(new TitledBorder(null, "C\u00E1c c\u00F4ng \u0111o\u1EA1n hi\u1EC7n c\u00F3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCacCongDoan.setBounds(0, 240, 1264, 340);
		pnlPCCD.add(pnlCacCongDoan);
		pnlCacCongDoan.setLayout(null);
		
		String[] header_CongDoan = {"Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Số lượng công nhân dự kiến", "Giá tiền (VND)"
				, "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú", "Công đoạn tiên quyết"};
		modelCongDoan = new DefaultTableModel(header_CongDoan, 0);
		tableCongDoan = new JTable(modelCongDoan);
		tableCongDoan.setRowHeight(26);
		tableCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JScrollPane scrollPane_CD = new JScrollPane(tableCongDoan);
		scrollPane_CD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane_CD.setBackground(new Color(255, 255, 255));
		scrollPane_CD.setLocation(10, 20);
		scrollPane_CD.setSize(1244, 310);
		pnlCacCongDoan.add(scrollPane_CD);
		
		modelCongDoan.addRow(new Object[] {"xxxxxx0123", "Mã công đoạn", "Tên công đoạn", 300, "32,000"
				, "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú", "Công đoạn tiên quyết"});
		
		JLabel lblTim = new JLabel("Tìm kiếm theo tên sản phẩm:");
		lblTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTim.setBounds(10, 590, 210, 30);
		pnlPCCD.add(lblTim);
		
		txtNDTim = new JTextField();
		txtNDTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNDTim.setBounds(230, 590, 200, 30);
		pnlPCCD.add(txtNDTim);
		txtNDTim.setColumns(30);
		
		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBounds(432, 590, 30, 30);
		pnlPCCD.add(btnTim);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHoanTat.setBounds(1114, 585, 140, 40);
		pnlPCCD.add(btnHoanTat);
		
		return pnlPCCD;
	}
}
