package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class ChamCongCN_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtGhiChu;
	private JTextField txtTimKiem;
	private JTextField txtNumCongNhan;
	private JTextField txtNumDilam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChamCongCN_GUI frame = new ChamCongCN_GUI();
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
	public ChamCongCN_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(this.getPNLCCCongNhan());
	}

	public JPanel getPNLCCCongNhan() {
		JPanel pnlCCCN = new JPanel();
		pnlCCCN.setLayout(null);
		pnlCCCN.setBounds(0, 50, 1268, 632);
		pnlCCCN.setBackground(new Color(240, 248, 255));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(240, 248, 255));
		pnlTop.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u1ED9 L\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTop.setBounds(10, 11, 1248, 120);
		pnlCCCN.add(pnlTop);
		pnlTop.setLayout(null);
		
		JLabel lblNgayChamCong = new JLabel("Ngày chấm công:");
		lblNgayChamCong.setBounds(10, 18, 141, 24);
		pnlTop.add(lblNgayChamCong);
		lblNgayChamCong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblSanPhamSx = new JLabel("Sản phẩm sản xuất:");
		lblSanPhamSx.setBounds(401, 18, 169, 24);
		pnlTop.add(lblSanPhamSx);
		lblSanPhamSx.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		String cb[] = {"Áo thun", "Áo phông", "Áo sơ mi"};
		JComboBox cboSP = new JComboBox(cb);
		cboSP.setBounds(567, 13, 105, 34);
		pnlTop.add(cboSP);
		cboSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCongdoan = new JLabel("Công đoạn:");
		lblCongdoan.setBounds(711, 18, 99, 24);
		pnlTop.add(lblCongdoan);
		lblCongdoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblXuong = new JLabel("Xưởng:");
		lblXuong.setBounds(979, 18, 68, 24);
		pnlTop.add(lblXuong);
		lblXuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		String cb1[] = {"Nhuộm", "Gia Công", "Kiểm tra"};
		JComboBox cboCongDoan = new JComboBox(cb1);
		cboCongDoan.setBounds(820, 11, 105, 34);
		pnlTop.add(cboCongDoan);
		cboCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		String cb2[] = {"Xưởng nhuộm 1", "Xưởng nhuộm 2", "Xưởng nhuộm 3"};
		JComboBox cboXuong = new JComboBox(cb2);
		cboXuong.setBounds(1057, 11, 169, 34);
		pnlTop.add(cboXuong);
		cboXuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(10, 68, 68, 24);
		pnlTop.add(lblCaLam);
		lblCaLam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JCheckBox chckbxSang = new JCheckBox("Sáng");
		chckbxSang.setBackground(new Color(240, 248, 255));
		chckbxSang.setBounds(84, 70, 70, 21);
		pnlTop.add(chckbxSang);
		chckbxSang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JCheckBox chckbxToi = new JCheckBox("Tối ");
		chckbxToi.setBackground(new Color(240, 248, 255));
		chckbxToi.setBounds(186, 70, 70, 21);
		pnlTop.add(chckbxToi);
		chckbxToi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JCheckBox chckbxThem = new JCheckBox("Thêm");
		chckbxThem.setBackground(new Color(240, 248, 255));
		chckbxThem.setBounds(276, 70, 84, 21);
		pnlTop.add(chckbxThem);
		chckbxThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setBounds(810, 68, 80, 24);
		pnlTop.add(lblGhiChu);
		lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGhiChu.setBounds(893, 65, 333, 30);
		pnlTop.add(txtGhiChu);
		txtGhiChu.setColumns(10);
		
		JDateChooser dcNgayChamCong = new JDateChooser();
		dcNgayChamCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcNgayChamCong.setBackground(new Color(240, 248, 255));
		dcNgayChamCong.setDateFormatString("dd/MM/yyyy");
		dcNgayChamCong.setDate(new Date());
		dcNgayChamCong.setBounds(161, 18, 199, 30);
		pnlTop.add(dcNgayChamCong);

		JLabel lblTimKiemTheoTen = new JLabel("Tìm kiếm theo tên:");
		lblTimKiemTheoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimKiemTheoTen.setBounds(20, 584, 154, 24);
		pnlCCCN.add(lblTimKiemTheoTen);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(178, 581, 169, 30);
		pnlCCCN.add(txtTimKiem);
		
		JButton btnTimKiem = new JButton("");
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-search-30.png"));
		btnTimKiem.setBounds(357, 581, 30, 30);
		pnlCCCN.add(btnTimKiem);
		
		JLabel lblTong = new JLabel("Tổng số công nhân:");
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTong.setBounds(457, 584, 169, 24);
		pnlCCCN.add(lblTong);
		
		txtNumCongNhan = new JTextField();
		txtNumCongNhan.setEditable(false);
		txtNumCongNhan.setBackground(new Color(240, 248, 255));
		txtNumCongNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumCongNhan.setColumns(10);
		txtNumCongNhan.setBounds(623, 581, 60, 27);
		pnlCCCN.add(txtNumCongNhan);
		
		JLabel lblDangLam = new JLabel("Đang làm:");
		lblDangLam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDangLam.setBounds(689, 584, 84, 24);
		pnlCCCN.add(lblDangLam);
		
		txtNumDilam = new JTextField();
		txtNumDilam.setEditable(false);
		txtNumDilam.setBackground(new Color(240, 248, 255));
		txtNumDilam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumDilam.setColumns(10);
		txtNumDilam.setBounds(783, 581, 60, 27);
		pnlCCCN.add(txtNumDilam);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(255, 255, 255));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhat.setBounds(895, 577, 154, 40);
		pnlCCCN.add(btnCapNhat);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHoanTat.setBounds(1081, 577, 154, 40);
		pnlCCCN.add(btnHoanTat);
		
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
		scrollPane.setBounds(10, 140, 1248, 422);
		pnlCCCN.add(scrollPane);
		return pnlCCCN;
	}
}
