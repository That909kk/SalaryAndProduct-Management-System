package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.border.EtchedBorder;

public class PhanCongCongNhan_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel Menu;
	private JPanel pnlPCCD;
	
	private DefaultTableModel modelSanPham;
	private JTable tableSanPham;
	private JTextField txtSoCN;
	private JTextField txtSoCD;
	private DefaultTableModel modelCongDoan;
	private JTable tableCongDoan;
	private JTextField txtNDTim;
	/**
	 * Create the frame.
	 */
	public PhanCongCongNhan_GUI() {
		super("Phân công công nhân");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Menu = new JPanel();
		Menu.setBounds(0, 0, 1264, 50);
		contentPane.add(Menu);
		
		contentPane.add(this.getPCCNGUI());
		
	}
	
	protected JPanel getPCCNGUI() {
		pnlPCCD = new JPanel();
		pnlPCCD.setBackground(new Color(240, 248, 255));
		pnlPCCD.setBounds(0, 50, 1264, 632);
		contentPane.add(pnlPCCD);
		pnlPCCD.setLayout(null);
		
		
		
		String[] header = {"Tên sản phẩm", "Công đoạn", "Số lượng sản phẩm", "Số lượng công nhân", "Ngày bắt đầu", "Ngày kết thúc dự kiến"};
		modelSanPham = new DefaultTableModel(header, 0);
		tableSanPham = new JTable(modelSanPham);
		
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSanPham.setRowHeight(26);
		
		tableSanPham.getColumnModel().getColumn(0).setPreferredWidth(135);
		tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableSanPham.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableSanPham.getColumnModel().getColumn(3).setPreferredWidth(130);
		tableSanPham.getColumnModel().getColumn(4).setPreferredWidth(115);
		tableSanPham.getColumnModel().getColumn(5).setPreferredWidth(145);
		
		JScrollPane scrollPane = new JScrollPane(tableSanPham);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(0, 0, 750, 240);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlPCCD.add(scrollPane);
		
		JPanel pnlThongTinCongDoan = new JPanel();
		pnlThongTinCongDoan.setBackground(new Color(240, 248, 255));
		pnlThongTinCongDoan.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u00E1o c\u00E1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinCongDoan.setBounds(754, 0, 500, 240);
		pnlPCCD.add(pnlThongTinCongDoan);
		pnlThongTinCongDoan.setLayout(null);
		
		JLabel lblSoCN = new JLabel("Số công nhân chưa phân công:");
		lblSoCN.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoCN.setBounds(15, 20, 284, 30);
		pnlThongTinCongDoan.add(lblSoCN);
		
		txtSoCN = new JTextField("800");
		txtSoCN.setDisabledTextColor(new Color(255, 0, 0));
		txtSoCN.setForeground(new Color(255, 0, 0));
		txtSoCN.setBackground(new Color(240, 248, 255));
		txtSoCN.setBorder(null);
		txtSoCN.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSoCN.setEnabled(false);
		txtSoCN.setBounds(300, 20, 100, 30);
		pnlThongTinCongDoan.add(txtSoCN);
		txtSoCN.setColumns(10);
		
		JLabel lblSoCD = new JLabel("Số công đoạn chưa đủ người:");
		lblSoCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoCD.setBounds(15, 60, 284, 30);
		pnlThongTinCongDoan.add(lblSoCD);
		
		txtSoCD = new JTextField("12");
		txtSoCD.setDisabledTextColor(new Color(255, 0, 0));
		txtSoCD.setBackground(new Color(240, 248, 255));
		txtSoCD.setBorder(null);
		txtSoCD.setEnabled(false);
		txtSoCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSoCD.setColumns(10);
		txtSoCD.setBounds(300, 60, 100, 30);
		pnlThongTinCongDoan.add(txtSoCD);
		
		JButton btnXem = new JButton("Xem chi tiết");
		btnXem.setBackground(new Color(255, 255, 255));
		btnXem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXem.setBounds(48, 111, 180, 50);
		btnXem.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		pnlThongTinCongDoan.add(btnXem);
		
		JButton btnThem = new JButton("<html>Thêm vào<br>công đoạn</html>");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(48, 172, 180, 50);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setIconTextGap(6);
		pnlThongTinCongDoan.add(btnThem);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBounds(273, 111, 180, 50);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnSua.setIconTextGap(6);
		pnlThongTinCongDoan.add(btnSua);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBounds(273, 172, 180, 50);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		btnSua.setIconTextGap(6);
		pnlThongTinCongDoan.add(btnXoa);
		
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		
		JPanel pnlCacCongDoan = new JPanel();
		pnlCacCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCacCongDoan.setBackground(new Color(240, 248, 255));
		pnlCacCongDoan.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch c\u00E1c c\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCacCongDoan.setBounds(0, 240, 1264, 340);
		pnlPCCD.add(pnlCacCongDoan);
		pnlCacCongDoan.setLayout(null);
		
		String[] header_CongDoan = {"Mã Công Nhân", "Họ", "Tên", "Chuyên môn", "Ca làm", "Xưởng", "Trạng thái giao việc", "ghi chú"};
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
		
		JLabel lblTim = new JLabel("Tìm kiếm theo tên sản phẩm:");
		lblTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTim.setBounds(10, 590, 240, 30);
		pnlPCCD.add(lblTim);
		
		txtNDTim = new JTextField();
		txtNDTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNDTim.setBounds(255, 590, 200, 30);
		pnlPCCD.add(txtNDTim);
		txtNDTim.setColumns(30);
		
		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBounds(457, 590, 30, 30);
		btnTim.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		pnlPCCD.add(btnTim);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHoanTat.setBounds(1114, 585, 140, 40);
		btnHoanTat.setIcon(new ImageIcon("img\\icons\\icons8-checked-checkbox-24.png"));
		pnlPCCD.add(btnHoanTat);
		
		return pnlPCCD;
	}
}
