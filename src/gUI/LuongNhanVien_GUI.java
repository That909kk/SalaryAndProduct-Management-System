package gUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connectDB.ConnectDB;
import dao.BangChamCongNV_DAO;
import dao.BangLuongNV_DAO;
import dao.BoPhan_DAO;
import dao.NhanVien_DAO;
import entity.BangChamCongNV;
import entity.BangLuongNV;
import entity.BoPhan;
import entity.NhanVien;

public class LuongNhanVien_GUI extends JFrame implements ActionListener ,MouseListener {

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
	private DefaultTableModel modelTableThangLuongNV;
	private DefaultTableModel modelTableDSLuongNV;
	private BangLuongNV_DAO bl_DAO;
	private BangChamCongNV_DAO bcc_DAO;
	private NhanVien_DAO nv_DAO;
	private BangChamCongNV_DAO bc_DAO;
	JButton btnTinhLuongNV;
	private Component frame;
	private JComboBox<String> cboBoPhanLuongNV;
	private JComboBox<String> cboNamLuongNV;
	private JComboBox<String> cboThangLuongNV;
	private BoPhan_DAO bp_DAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LuongNhanVien_GUI frame = new LuongNhanVien_GUI();
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
	public LuongNhanVien_GUI()  {
		super("Lương Nhân Viên");
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bl_DAO = new BangLuongNV_DAO();
		bc_DAO = new BangChamCongNV_DAO();
		nv_DAO = new NhanVien_DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(this.getLuongNVGUI());

	}

	public JPanel getLuongNVGUI() {
		JPanel pnlLuongNV = new JPanel();
		pnlLuongNV.setBackground(new Color(240, 248, 255));
		pnlLuongNV.setBounds(0, 50, 1264, 632);
		pnlLuongNV.setLayout(null);

		String headerThangLuong[] = {"Tháng", "Năm", "Bộ Phận"};
		modelTableThangLuongNV = new DefaultTableModel(headerThangLuong, 0);


		tblThangLuongNhanVien = new JTable(modelTableThangLuongNV);
		tblThangLuongNhanVien.setFont(UIManager.getFont("TableHeader.font"));

		tblThangLuongNhanVien.setRowHeight(26);
		tblThangLuongNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblThangLuongNhanVien.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblThangLuongNhanVien.getColumnModel().getColumn(2).setPreferredWidth(404);
		tblThangLuongNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThangLuongNhanVien.setRowSelectionAllowed(true);
		tblThangLuongNhanVien.setBackground(new Color(255, 255, 255));

		layDSBangLuongtuDB();
		JScrollPane scrThangLuong = new JScrollPane(tblThangLuongNhanVien);
		scrThangLuong.setBackground(new Color(255, 255, 255));
		scrThangLuong.setBounds(0, 0, 604, 190);
		pnlLuongNV.add(scrThangLuong);

		String headerDSLuong[] = {"Mã nhân viên", "Họ tên", "Lương cơ bản", "Bậc lương", "Hệ số lương", "Số ngày làm",
				"Thưởng và Phụ Cấp ", "Khấu trừ", "Bảo hiểm xã hội", "Tổng lương", "Đã tính lương", "Ghi chú"};
		modelTableDSLuongNV = new DefaultTableModel(headerDSLuong, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Cho phép chỉnh sửa nếu cột không nằm trong khoảng 0 đến 5
				return column > 6;
			}
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 10)
					return Boolean.class;
				return Object.class;
			}
		};
		tblDSLuongNV = new JTable(modelTableDSLuongNV);
		tblDSLuongNV.setRowHeight(26);
		tblDSLuongNV.getColumnModel().getColumn(0).setPreferredWidth(85);
		tblDSLuongNV.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblDSLuongNV.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblDSLuongNV.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblDSLuongNV.getColumnModel().getColumn(4).setPreferredWidth(90);
		tblDSLuongNV.getColumnModel().getColumn(5).setPreferredWidth(85);
		tblDSLuongNV.getColumnModel().getColumn(6).setPreferredWidth(150);
		tblDSLuongNV.getColumnModel().getColumn(7).setPreferredWidth(150);
		tblDSLuongNV.getColumnModel().getColumn(8).setPreferredWidth(150);
		tblDSLuongNV.getColumnModel().getColumn(9).setPreferredWidth(256);
		tblDSLuongNV.getColumnModel().getColumn(10).setPreferredWidth(90);
		tblDSLuongNV.getColumnModel().getColumn(11).setPreferredWidth(120);
		tblDSLuongNV.setFillsViewportHeight(true);
		tblDSLuongNV.setRowSelectionAllowed(true);
		tblDSLuongNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pnlLuongNV.add(tblDSLuongNV);
		cboThangLuongNV = new JComboBox<String>();
		cboThangLuongNV.setBounds(101, 29, 50, 30);
		cboThangLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		for (int i = 1;i <= 12; i++){
			cboThangLuongNV.addItem(i+"");
		}
		cboThangLuongNV.setSelectedItem(LocalDate.now().getMonthValue()+"");
		pnlLuongNV.add(cboThangLuongNV);


		JLabel Thangcbo = new JLabel("Tháng:");
		Thangcbo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Thangcbo.setBounds(21, 29, 80, 30);
		pnlLuongNV.add(Thangcbo);

		JButton btnLoc = new JButton("Lọc");
		btnLoc.setBounds(590, 29, 60, 30);
		btnLoc.setBackground(new Color(255, 255, 255));

		
		cboNamLuongNV = new JComboBox<String>();
		cboNamLuongNV.setBounds(230, 29, 100, 30);
		cboNamLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 15));

		bcc_DAO = new BangChamCongNV_DAO();
		ArrayList<Integer> listNam = bcc_DAO.layDSNamKhacnhauCCNV();
		for(Integer nam2 : listNam){

			cboNamLuongNV.addItem(nam2.toString());

		}
		cboNamLuongNV.setSelectedItem(LocalDate.now().getYear()+"");
		pnlLuongNV.add(cboNamLuongNV);

		JLabel lblNamcbo = new JLabel("Năm:");
		lblNamcbo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNamcbo.setBounds(170, 29, 50, 30);
		pnlLuongNV.add(lblNamcbo);

		cboBoPhanLuongNV = new JComboBox<String>();
		cboBoPhanLuongNV.setBounds(430, 29, 155, 30);
		cboBoPhanLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(cboBoPhanLuongNV);
		
		bp_DAO=new BoPhan_DAO();
		ArrayList<BoPhan> dsBP = bp_DAO.layTatCaBoPhanKhacNhau() ;
		for(BoPhan boPhan : dsBP){
			String tenBP = hienThiTenBoPhan(boPhan.getMaBoPhan());
			cboBoPhanLuongNV.addItem(tenBP);
		}
		cboThangLuongNV.setSelectedItem(" ");
		cboNamLuongNV.setSelectedItem(" ");
		cboBoPhanLuongNV.setSelectedItem(" ");

		JLabel lblBoPhanLuongNV = new JLabel("Bộ Phận:");
		lblBoPhanLuongNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBoPhanLuongNV.setBounds(350, 29, 80, 30);
		pnlLuongNV.add(lblBoPhanLuongNV);


		JLabel lblSoNVChuaTinhLuong = new JLabel("Số Nhân Viên Chưa Tính Lương:");
		lblSoNVChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoNVChuaTinhLuong.setBounds(21, 69, 243, 30);
		pnlLuongNV.add(lblSoNVChuaTinhLuong);

		txtSoNVChuaTinhLuong = new JTextField();
		txtSoNVChuaTinhLuong.setColumns(10);
		txtSoNVChuaTinhLuong.setBounds(268, 69, 369, 30);
		txtSoNVChuaTinhLuong.setEditable(false);
		txtSoNVChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoNVChuaTinhLuong.setBackground(new Color(240, 248, 255));
		pnlLuongNV.add(txtSoNVChuaTinhLuong);

		JLabel lblTongSoNV = new JLabel("Tổng Số Nhân Viên:");
		lblTongSoNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongSoNV.setBounds(21, 109, 196, 30);
		pnlLuongNV.add(lblTongSoNV);

		txtTongSoNV = new JTextField();
		txtTongSoNV.setColumns(10);
		txtTongSoNV.setBounds(268, 109, 369, 30);
		txtTongSoNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTongSoNV.setEditable(false);
		txtTongSoNV.setBackground(new Color(240, 248, 255));
		pnlLuongNV.add(txtTongSoNV);



		JLabel lblTongLuongCanTraNV = new JLabel("Tổng Tiền Lương Cần Trả:");
		lblTongLuongCanTraNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongLuongCanTraNV.setBounds(21, 149, 197, 30);
		pnlLuongNV.add(lblTongLuongCanTraNV);

		txtTongLuongCanTraNV = new JTextField();
		txtTongLuongCanTraNV.setColumns(10);
		txtTongLuongCanTraNV.setBounds(268, 149, 369, 30);
		txtTongLuongCanTraNV.setEditable(false);
		txtTongLuongCanTraNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTongLuongCanTraNV.setBackground(new Color(240, 248, 255));
		pnlLuongNV.add(txtTongLuongCanTraNV);

		JScrollPane scrThangLuongNhanVien = new JScrollPane(tblDSLuongNV);
		scrThangLuongNhanVien.setBounds(0, 15, 14, 170);
		pnlLuongNV.add(scrThangLuongNhanVien);

		JLabel lblTimKiemTheoTenNV = new JLabel("Tìm Kiếm Theo Tên:");
		lblTimKiemTheoTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTimKiemTheoTenNV.setBounds(10, 579, 159, 30);
		pnlLuongNV.add(lblTimKiemTheoTenNV);

		txtTimKiemTheoTenNV = new JTextField();
		txtTimKiemTheoTenNV.setColumns(10);
		txtTimKiemTheoTenNV.setBounds(161, 581, 221, 30);
		txtTimKiemTheoTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongNV.add(txtTimKiemTheoTenNV);

		JButton btnTimKiemTheoTenNV = new JButton("");
		btnTimKiemTheoTenNV.setBounds(392, 580, 39, 30);
		btnTimKiemTheoTenNV.setBackground(new Color(255, 255, 255));
		btnTimKiemTheoTenNV.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));	
		pnlLuongNV.add(btnTimKiemTheoTenNV);

		JPanel pnlButtonLuongNV = new JPanel();
		pnlButtonLuongNV.setBorder(UIManager.getBorder("Button.border"));
		pnlButtonLuongNV.setBounds(453, 563, 787, 57);
		pnlLuongNV.add(pnlButtonLuongNV);

		GridLayout gl_pnlButtonLuongNV = new GridLayout(0, 5, 0, 0);
		gl_pnlButtonLuongNV.setHgap(10);
		gl_pnlButtonLuongNV.setVgap(10);
		pnlButtonLuongNV.setLayout(gl_pnlButtonLuongNV);

		JButton btnXemChiTietLuongNV = new JButton("Xem Chi Tiết");
		btnXemChiTietLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXemChiTietLuongNV.setBackground(new Color(255, 255, 255));
		pnlButtonLuongNV.add(btnXemChiTietLuongNV);

		JButton btnChonNhanh = new JButton("Chọn Nhanh");
		btnChonNhanh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChonNhanh.setBackground(new Color(255, 255 ,255));
		pnlButtonLuongNV.add(btnChonNhanh);

		btnTinhLuongNV = new JButton("Tính Lương");
		btnTinhLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTinhLuongNV.setBackground(new Color(255, 255, 255));
		pnlButtonLuongNV.add(btnTinhLuongNV);



		JButton btnHoanTatLuongNV = new JButton("Hoàn Tất");
		btnHoanTatLuongNV.setBackground(new Color(255, 255, 255));
		btnHoanTatLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongNV.add(btnHoanTatLuongNV);

		JButton btnInBangLuongNV = new JButton("In");
		btnInBangLuongNV.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInBangLuongNV.setBackground(new Color(255, 255, 255));
		pnlButtonLuongNV.add(btnInBangLuongNV);

		JScrollPane scrDSLuongNV = new JScrollPane(tblDSLuongNV);
		scrDSLuongNV.setBounds(0, 205, 1268, 347);
		pnlLuongNV.add(scrDSLuongNV);

		JPanel pnlThongKeTinhLuong = new JPanel();
		pnlThongKeTinhLuong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongKeTinhLuong.setBackground(new Color(240, 248, 255));
		pnlThongKeTinhLuong.setBounds(604, 0, 660, 190);
		pnlLuongNV.add(pnlThongKeTinhLuong);
		pnlThongKeTinhLuong.setLayout(null);


		pnlThongKeTinhLuong.add(lblSoNVChuaTinhLuong);
		pnlThongKeTinhLuong.add(lblTongLuongCanTraNV);
		pnlThongKeTinhLuong.add(lblTongSoNV);
		pnlThongKeTinhLuong.add(cboThangLuongNV);
		pnlThongKeTinhLuong.add(cboNamLuongNV);
		pnlThongKeTinhLuong.add(cboBoPhanLuongNV);
		pnlThongKeTinhLuong.add(Thangcbo);
		pnlThongKeTinhLuong.add(lblNamcbo);
		pnlThongKeTinhLuong.add(lblBoPhanLuongNV);


		pnlThongKeTinhLuong.add(txtSoNVChuaTinhLuong);
		pnlThongKeTinhLuong.add(txtTongLuongCanTraNV);
		pnlThongKeTinhLuong.add(txtTongSoNV);
		pnlThongKeTinhLuong.add(btnLoc);
		tblThangLuongNhanVien.addMouseListener(this);
		btnTinhLuongNV.addActionListener(this);
		txtSoNVChuaTinhLuong.setBorder(null);
		txtTongSoNV.setBorder(null);
		txtTongLuongCanTraNV.setBorder(null);
		btnHoanTatLuongNV.addActionListener(this);

		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String thang = cboThangLuongNV.getSelectedItem().toString().trim();
				String nam = cboNamLuongNV.getSelectedItem().toString().trim();
				String tenBP = cboBoPhanLuongNV.getSelectedItem().toString().trim();
				bcc_DAO= new BangChamCongNV_DAO();
				ArrayList<LocalDate> listkt = bcc_DAO.layTatCaThangvaNamkhacNhau();

				int temp=0;

				if(thang.equals("")||nam.equals("")||tenBP.equals("")){
					JOptionPane.showMessageDialog(null, "Hãy chọn đầy đủ thông tin để lọc!");
				}
				else{
					for (LocalDate kt : listkt) {
						if((kt.getMonthValue()+"").equals(thang))
							temp=1;

					}
					if(temp==0)
						JOptionPane.showMessageDialog(null, "Hiện Không Có Danh Sách Tháng Này!");
					else {
						int rowCount = modelTableThangLuongNV.getRowCount();
						System.out.println(rowCount);
						for (int i = rowCount - 1; i >= 0; i--) {
							String thangLoc = modelTableThangLuongNV.getValueAt(i, 0).toString();
							String namLoc = modelTableThangLuongNV.getValueAt(i, 1).toString();
							String tenBPLoc = modelTableThangLuongNV.getValueAt(i, 2).toString();
							if(thangLoc.equals(thang)&&namLoc.equals(nam)&&tenBPLoc.equals(tenBP)){
								tblThangLuongNhanVien.setRowSelectionInterval(i, i);

								int row = tblThangLuongNhanVien.getSelectedRow();
								int thang1 = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 0).toString());
								int nam1 = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 1).toString());
								String mabp = modelTableThangLuongNV.getValueAt(row, 2).toString();
								taoDSBangLuongtuDBtheoDK(thang1,nam1,mahoaTenBoPhan(mabp));
							}
						}
					}
				}}
		});

		btnInBangLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblThangLuongNhanVien.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn danh sách tháng lương để in!");
				}
				else {
					int thang = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 0).toString());
					int nam = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 1).toString());
					String mabp = modelTableThangLuongNV.getValueAt(row, 2).toString();
					String tongTra= txtTongLuongCanTraNV.getText();
					exportDsToExcel(thang,nam,mabp,tongTra);}
			}
		});
		btnTinhLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat decimalFormat = new DecimalFormat("#,###");
				decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				int rowdk = tblThangLuongNhanVien.getSelectedRow();
				int[] rows = tblDSLuongNV.getSelectedRows();

				if (rows.length <= 0 || rowdk < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn ít nhất 1 nhân viên để tính lương");
				}else {

					if (rows.length > 0) {
						// Kiểm tra xem bảng có đang sử dụng sorter hay không
						if (tblDSLuongNV.getRowSorter() != null) {
							TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) tblDSLuongNV.getRowSorter();

							for (int i = 0; i < rows.length; i++) {
								int row = rows[i];
								
				                
								// Kiểm tra xem selectedRow có hợp lệ trong bộ lọc hay không
								if (row < tblDSLuongNV.getRowCount()) {
									int modelRow = sorter.convertRowIndexToModel(row);
									double khauTru = 0;

					                try {
					                    khauTru = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 7).toString().replace(",", ""));
					                } catch (NumberFormatException ex) {
					                    JOptionPane.showMessageDialog(null, "Khấu trừ phải là một số.");
					                    return; // Dừng việc tính lương nếu có lỗi
					                }

					                if (khauTru < 0) {
					                    JOptionPane.showMessageDialog(null, "Khấu trừ không thể là số âm.");
					                    return; // Dừng việc tính lương nếu có lỗi
					                }
									boolean daTinhLuong = (boolean) modelTableDSLuongNV.getValueAt(modelRow, 10);
									boolean coLuongRoi = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 9).toString().replace(",", ""))>0?true:false;
									if(daTinhLuong==true&&coLuongRoi==true){
										int luaChon = JOptionPane.showConfirmDialog(null, "Nhân viên "+modelTableDSLuongNV.getValueAt(row, 1).toString()+" có mã số "+modelTableDSLuongNV.getValueAt(row, 0).toString()+" đã có lương. Bạn có muốn tính lại lương cho nhân viên này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
										if(luaChon==JOptionPane.YES_OPTION){
											modelTableDSLuongNV.setValueAt(false, modelRow, 10);
										}
									}
									if (daTinhLuong == false) {
										String maNV = modelTableDSLuongNV.getValueAt(modelRow, 0).toString();
										String tenNV = modelTableDSLuongNV.getValueAt(modelRow, 1).toString();
										int rowas = tblThangLuongNhanVien.getSelectedRow();
										int thang = Integer.parseInt(modelTableThangLuongNV.getValueAt(rowas, 0).toString());
										int nam = Integer.parseInt(modelTableThangLuongNV.getValueAt(rowas, 1).toString());
										double luongCoBan = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 2).toString().replace(",", ""));
										double bacLuong = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 3).toString().replace(",", ""));
										double heSoLuong = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 4).toString().replace(",", ""));
										double soNgayDiLam = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 5).toString().replace(",", ""));
										double thuong = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 6).toString().replace(",", ""));
										double tienPhat = Double.parseDouble(modelTableDSLuongNV.getValueAt(modelRow, 7).toString().replace(",", ""));
										if(kiemSoBangChamCongCua1NV(maNV, thang, nam)){
											double luongTinh = ((luongCoBan*bacLuong*heSoLuong)/30)*soNgayDiLam + thuong - tienPhat;
											double baoHiemXH = luongTinh*0.105;
											double tongLuong = luongTinh - baoHiemXH;

											modelTableDSLuongNV.setValueAt(decimalFormat.format(baoHiemXH), modelRow, 8);
											modelTableDSLuongNV.setValueAt(decimalFormat.format(tongLuong), modelRow, 9);
											modelTableDSLuongNV.setValueAt(true, modelRow, 10);
											LocalDate ngayhientai = LocalDate.now();
											modelTableDSLuongNV.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), modelRow, 11);
											bc_DAO = new BangChamCongNV_DAO();
											ArrayList<BangChamCongNV> list = bc_DAO. dsBangCCtheomaNVthangnam(maNV, thang, nam);
											if(list.size()>0)
												for (BangChamCongNV bcc : list) {
													bcc.setGhiChu("");
													bc_DAO.updateGhiChiBangChamCongNV(bcc);
												}
										}
										else{
											int chon =JOptionPane.showConfirmDialog(null, "Nhân viên: "+tenNV+" Mã Số: "+maNV+" chưa có đủ bảng chấm công tháng "+thang+" năm "+nam+". Bạn có muốn tiếp tục tính lương cho nhân viên này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
											if (chon ==JOptionPane.YES_OPTION) {
												double luongTinh = ((luongCoBan*bacLuong*heSoLuong)/30)*soNgayDiLam + thuong - tienPhat;
												double baoHiemXH = luongTinh*0.105;
												double tongLuong = luongTinh - baoHiemXH;
												modelTableDSLuongNV.setValueAt(decimalFormat.format(baoHiemXH),modelRow, 8);
												modelTableDSLuongNV.setValueAt(decimalFormat.format(tongLuong),modelRow, 9);
												modelTableDSLuongNV.setValueAt(true, modelRow, 10);
												LocalDate ngayhientai = LocalDate.now();
												modelTableDSLuongNV.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), modelRow, 11);
												bc_DAO = new BangChamCongNV_DAO();
												ArrayList<BangChamCongNV> list = bc_DAO. dsBangCCtheomaNVthangnam(maNV, thang, nam);
												if(list.size()>0)
													for (BangChamCongNV bcc : list) {
														bcc.setGhiChu("");
														bc_DAO.updateGhiChiBangChamCongNV(bcc);
													}
											}
										}


									}

								}
							}
						} else {
							for (int i=0;i<rows.length;i++){
								
								int row = rows[i];
								double khauTru = 0;

				                try {
				                    khauTru = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 7).toString().replace(",", ""));
				                } catch (NumberFormatException ex) {
				                    JOptionPane.showMessageDialog(null, "Khấu trừ phải là một số.");
				                    return; // Dừng việc tính lương nếu có lỗi
				                }

				                if (khauTru < 0) {
				                    JOptionPane.showMessageDialog(null, "Khấu trừ không thể là số âm.");
				                    return; // Dừng việc tính lương nếu có lỗi
				                }
								boolean daTinhLuong = (boolean) modelTableDSLuongNV.getValueAt(row, 10);
								boolean coLuongRoi = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 9).toString().replace(",", ""))>0?true:false;
								if(daTinhLuong==true&&coLuongRoi==true){
									int luaChon = JOptionPane.showConfirmDialog(null, "Nhân viên "+modelTableDSLuongNV.getValueAt(row, 1).toString()+" có mã số "+modelTableDSLuongNV.getValueAt(row, 0).toString()+" đã có lương. Bạn có muốn tính lại lương cho nhân viên này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
									if(luaChon==JOptionPane.YES_OPTION){
										modelTableDSLuongNV.setValueAt(false, row, 10);
									}
								}
								if( daTinhLuong==false){
									String maNV = modelTableDSLuongNV.getValueAt(row, 0).toString();
									String tenNV = modelTableDSLuongNV.getValueAt(row, 1).toString();
									int rowas = tblThangLuongNhanVien.getSelectedRow();
									int thang = Integer.parseInt(modelTableThangLuongNV.getValueAt(rowas, 0).toString());
									int nam = Integer.parseInt(modelTableThangLuongNV.getValueAt(rowas, 1).toString());
									double luongCoBan = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 2).toString().replace(",", ""));
									double bacLuong = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 3).toString().replace(",", ""));
									double heSoLuong = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 4).toString().replace(",", ""));
									double soNgayDiLam = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 5).toString().replace(",", ""));
									double thuong = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 6).toString().replace(",", ""));
									double tienPhat = Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 7).toString().replace(",", ""));
									if(kiemSoBangChamCongCua1NV(maNV, thang, nam)){
										double luongTinh = ((luongCoBan*bacLuong*heSoLuong)/30)*soNgayDiLam + thuong - tienPhat;
										double baoHiemXH = luongTinh*0.105;
										double tongLuong = luongTinh - baoHiemXH;
										modelTableDSLuongNV.setValueAt(decimalFormat.format(baoHiemXH), row, 8);
										modelTableDSLuongNV.setValueAt(decimalFormat.format(tongLuong), row, 9);
										modelTableDSLuongNV.setValueAt(true, row, 10);
										LocalDate ngayhientai = LocalDate.now();
										modelTableDSLuongNV.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), row, 11);
										bc_DAO = new BangChamCongNV_DAO();
										ArrayList<BangChamCongNV> list = bc_DAO. dsBangCCtheomaNVthangnam(maNV, thang, nam);
										if(list.size()>0)
											for (BangChamCongNV bcc : list) {
												bcc.setGhiChu("");
												bc_DAO.updateGhiChiBangChamCongNV(bcc);
											}
									}

									else{
										int chon =JOptionPane.showConfirmDialog(null, "Nhân viên: "+tenNV+" Mã Số: "+maNV+" chưa có đủ bảng chấm công tháng "+thang+" năm "+nam+". Bạn có muốn tiếp tục tính lương cho nhân viên này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
										if (chon ==JOptionPane.YES_OPTION) {
											double luongTinh = ((luongCoBan*bacLuong*heSoLuong)/30)*soNgayDiLam + thuong - tienPhat;
											double baoHiemXH = luongTinh*0.105;
											double tongLuong = luongTinh - baoHiemXH;
											modelTableDSLuongNV.setValueAt(decimalFormat.format(baoHiemXH), row, 8);
											modelTableDSLuongNV.setValueAt(decimalFormat.format(tongLuong), row, 9);
											modelTableDSLuongNV.setValueAt(true, row, 10);
											LocalDate ngayhientai = LocalDate.now();
											modelTableDSLuongNV.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), row, 11);
											bc_DAO = new BangChamCongNV_DAO();
											ArrayList<BangChamCongNV> list = bc_DAO. dsBangCCtheomaNVthangnam(maNV, thang, nam);
											if(list.size()>0)
												for (BangChamCongNV bcc : list) {
													bcc.setGhiChu("");
													bc_DAO.updateGhiChiBangChamCongNV(bcc);
												}
										}
									}
								}
							}
						}
					}
				}}
		});
		btnTimKiemTheoTenNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Lấy nội dung nhập từ ô tìm kiếm
				String dkCanTim = txtTimKiemTheoTenNV.getText().trim().toLowerCase();

				// Kiểm tra nếu ô nhập rỗng
				if (dkCanTim.isEmpty()) {
					// Hiển thị lại toàn bộ danh sách nếu ô tìm kiếm trống
					tblDSLuongNV.setRowSorter(null);
				} else {
					// Tạo một RowFilter để lọc các dòng theo điều kiện tìm kiếm
					RowFilter<Object, Object> rowFilter = new RowFilter<Object, Object>() {
						@Override
						public boolean include(Entry<? extends Object, ? extends Object> entry) {
							String tenNhanVien = entry.getStringValue(1).toLowerCase();
							String maNhanVien = entry.getStringValue(0).toLowerCase();

							// Kiểm tra nếu tên hoặc mã chứa từ khóa tìm kiếm
							return tenNhanVien.contains(dkCanTim) || maNhanVien.contains(dkCanTim);
						}
					};

					// Tạo một TableRowSorter với RowFilter đã tạo
					TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelTableDSLuongNV);
					sorter.setRowFilter(rowFilter);

					// Đặt TableRowSorter vào bảng để áp dụng bộ lọc
					tblDSLuongNV.setRowSorter(sorter);
				}

				txtTimKiemTheoTenNV.selectAll();
				txtTimKiemTheoTenNV.requestFocusInWindow();
			}

		});
		btnChonNhanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowdk = tblThangLuongNhanVien.getSelectedRow();
				if (rowdk < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn ít nhất 1 điều kiện để chọn nhanh danh sách nhân viến chưa tính lương!");
				}else
				{
					if(kiemtraCoFalseKhong()){
						chonTatCaFalse();
					} else {
						int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn chọn tất cả nhân viên không?", "Thông báo", JOptionPane.YES_NO_OPTION);
						if (chon == JOptionPane.YES_OPTION) {
							ChonTatCaTrue();

						}
					}
				}
			}
		});
		btnHoanTatLuongNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowdk = tblThangLuongNhanVien.getSelectedRow();
				int[] rows = tblDSLuongNV.getSelectedRows();

				if (rows.length <= 0 || rowdk < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn ít nhất 1 nhân viên để hoàn tất tính lương");
				}else {

					if (rows.length > 0) {
						int rowas = tblThangLuongNhanVien.getSelectedRow();
						int thang = Integer.parseInt(modelTableThangLuongNV.getValueAt(rowas, 0).toString());
						int nam = Integer.parseInt(modelTableThangLuongNV.getValueAt(rowas, 1).toString());

						if (tblDSLuongNV.getRowSorter() != null) {
							int chon = JOptionPane.showConfirmDialog(null, "Bạn đang trong danh sách tìm kiếm quay lại để hoàn tất việc chấm lương?", "Thông báo", JOptionPane.YES_NO_OPTION);

							if (chon == JOptionPane.YES_OPTION) {
								tblDSLuongNV.setRowSorter(null);
							}
						}else {  int thongBao=0; int dk=0;
						for (int i = 0; i < rows.length; i++) {
							int row = rows[i];
							boolean daTinhLuong = (boolean) modelTableDSLuongNV.getValueAt(row, 10);
							if (daTinhLuong == true) {
								BangLuongNV bl = new BangLuongNV();
								NhanVien_DAO nv_DAO = new NhanVien_DAO();
								bc_DAO = new BangChamCongNV_DAO();
								bl.setMaLuongNV(modelTableDSLuongNV.getValueAt(row, 0).toString() + dinhDangThang(thang) + lay2kitucuoicuaNam(nam));
								bl.setNv(nv_DAO.getMotNVTuMaNV(modelTableDSLuongNV.getValueAt(row, 0).toString()));
								bl.setThang(thang);
								bl.setNam(nam);
								bl.setSoNgayDiLam(Integer.parseInt(modelTableDSLuongNV.getValueAt(row, 5).toString().replace(",", "")));

								int soNgayNghiKhongPhep =(int) bc_DAO.getSoBangChamCongCua1NV(modelTableDSLuongNV.getValueAt(row, 0).toString(), thang, nam) - bl.getSoNgayDiLam();
								bl.setSoNgayNghiKhongPhep(soNgayNghiKhongPhep);

								bl.setTienPhat(Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 7).toString().replace(",", "")));
								bl.setBhxh(Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 8).toString().replace(",", "")));
								bl.setLuongTong(Double.parseDouble(modelTableDSLuongNV.getValueAt(row, 9).toString().replace(",", "")));

								BangLuongNV_DAO bl_DAO = new BangLuongNV_DAO();
								BangLuongNV bl2=bl_DAO.lay1BangLuongTheoMaNVThangNam(modelTableDSLuongNV.getValueAt(row, 0).toString(), thang, nam);
								if(bl2==null) {
									bl_DAO.insertBangLuongNV(bl);
									thongBao=1;
									dk=1;
								}
								else {
									bl_DAO.updateBangLuongNV(bl);
									thongBao=1;
									dk=1;

								}
							}
							else 
							{	thongBao=1;
							dk=0;
							}
						}
						if(thongBao==1&&dk==1) {
							JOptionPane.showMessageDialog(null, "Hoàn tất tính lương thành công!");
							int row = tblThangLuongNhanVien.getSelectedRow();
							int thangs = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 0).toString());
							int nams = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 1).toString());
							String mabp = modelTableThangLuongNV.getValueAt(row, 2).toString();

							taoDSBangLuongtuDBtheoDK(thangs,nams,mahoaTenBoPhan(mabp));
							bc_DAO = new BangChamCongNV_DAO();
							BangChamCongNV bc = bc_DAO.layBangCCCuoiCungThangCua1NV(modelTableDSLuongNV.getValueAt(row, 0).toString(), thang, nam);
							if(bc!=null) {
								bc.setGhiChu("");
								bc_DAO.updateGhiChiBangChamCongNV(bc);
							}}

						else {
							if(thongBao==1&&dk==0)
								JOptionPane.showMessageDialog(null, "Hoàn tất tính lương thất bại!Vui Lòng tính lương trước!");
							else 
								JOptionPane.showMessageDialog(null, "Hoàn tất tính lương thất bại");
						}
						}


					}

				}}
		});

		btnXemChiTietLuongNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblThangLuongNhanVien.getSelectedRow();
				int[] rows = tblDSLuongNV.getSelectedRows();

				if (rows.length <= 0 || row < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn 1 nhân viên để xem chi tiết lương");
				} else if (rows.length > 1) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng chỉ chọn 1 nhân viên để xem chi tiết lương!");
				} else {
					int thang = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 0).toString());
					int nam = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 1).toString());
					String mabp = modelTableThangLuongNV.getValueAt(row, 2).toString();
					bl_DAO = new BangLuongNV_DAO();

					// Kiểm tra xem có dòng được chọn trong tblDSLuongNV không
					if (rows[0] < modelTableDSLuongNV.getRowCount()) {
						String maNV = modelTableDSLuongNV.getValueAt(rows[0], 0).toString();
						double khauTru = Double.parseDouble(modelTableDSLuongNV.getValueAt(rows[0], 7).toString().replace(",","")); 
						BangLuongNV bl= bl_DAO.lay1BangLuongTheoMaNVThangNam(maNV, thang, nam);
						if(bl==null) {
							JOptionPane.showMessageDialog(null, "Nhân Viên Chưa Có Bảng Lương Không Thể Xem Chi Tiết Được!");
						}else
							new ChiTietNV_GUI(maNV, thang, nam,mabp,khauTru);
					} else {
						JOptionPane.showMessageDialog(null, "Không thể truy cập dữ liệu nhân viên.");
					}
				}
			}
		});

		return pnlLuongNV;
	}
	public String dinhDangThang(int mau) {
		if(mau<10&&mau>0)
			return "0"+mau;
		else
			return mau+"";
	}
	private String lay2kitucuoicuaNam(int nam){

		String nam2kitucuoi = String.valueOf(nam);
		nam2kitucuoi = nam2kitucuoi.substring(2, 4);
		return nam2kitucuoi;
	}
	private void layDSBangLuongtuDB(){
		nv_DAO = new NhanVien_DAO();
		bc_DAO = new BangChamCongNV_DAO();
		modelTableThangLuongNV.setRowCount(0);
		ArrayList<LocalDate> dsNgayCham = bc_DAO.layTatCaThangvaNamkhacNhau();
		for (LocalDate ngayCham : dsNgayCham) {
			ArrayList<String> dsMaBP = bc_DAO.listTatCaBoPhan(bc_DAO.dsBangChamCongNhanVienTheoTungThang(ngayCham.getMonthValue(), ngayCham.getYear()));
			for (String maBP : dsMaBP) {
				modelTableThangLuongNV.addRow(new Object[] { dinhDangThang(ngayCham.getMonthValue()),ngayCham.getYear(), hienThiTenBoPhan(maBP) });
			}
		}
	}
	public String hienThiTenBoPhan(String maBP) {
		String maBoPhanChu="";
		if(maBP.trim().equals("BPKT")) {
			maBoPhanChu="Bộ Phận Kế Toán";
		}
		if(maBP.trim().equals("BPNS")) {
			maBoPhanChu="Bộ Phận Nhân Sự";
		}
		if(maBP.trim().equals("QLXU")) {
			maBoPhanChu="Quản Lí Xưởng";
		}
		return maBoPhanChu;
	}
	public String mahoaTenBoPhan(String maBP) {
		String maBoPhanChu="";
		if(maBP.trim().equals("Bộ Phận Kế Toán")) {
			maBoPhanChu="BPKT";
		}
		if(maBP.trim().equals("Bộ Phận Nhân Sự")) {
			maBoPhanChu="BPNS";
		}
		if(maBP.trim().equals("Quản Lí Xưởng")) {
			maBoPhanChu="QLXU";
		}
		return maBoPhanChu;
	}
	private void taoDSBangLuongtuDBtheoDK(int thang, int nam, String mabp){
		nv_DAO = new NhanVien_DAO();

		DecimalFormat decimalFormat = new DecimalFormat("#,###");decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		modelTableDSLuongNV.setRowCount(0);
		for(NhanVien nv : nv_DAO.getListNVtheoBP(mabp)){

			bl_DAO = new BangLuongNV_DAO();
			BangLuongNV bl=bl_DAO.lay1BangLuongTheoMaNVThangNam(nv.getMaNV(), thang, nam);
			if (bl == null) {
				double thuong = (((nv.getLuongCoBan()*nv.getThangBacLuong()*nv.getHeSoLuong())/30)/8)*2*bc_DAO.getTongSoGioTangCaCua1NV(nv.getMaNV(), thang, nam);
				modelTableDSLuongNV.addRow(new Object[] {nv.getMaNV(), nv.getHo()+" "+nv.getTen(), decimalFormat.format(nv.getLuongCoBan()), decimalFormat.format(nv.getThangBacLuong()), nv.getHeSoLuong(), decimalFormat.format(bc_DAO.getSoNgayDiLamCua1NV(nv.getMaNV(), thang, nam)), decimalFormat.format(thuong+nv.getPhuCap()),0, 0, 0, false, "Chưa được tính lương"});

			} else {
				double thuong = (((nv.getLuongCoBan()*nv.getThangBacLuong()*nv.getHeSoLuong())/30)/8)*2*bc_DAO.getTongSoGioTangCaCua1NV(nv.getMaNV(), thang, nam);
				modelTableDSLuongNV.addRow(new Object[] { nv.getMaNV(), nv.getHo()+" "+nv.getTen(), decimalFormat.format(nv.getLuongCoBan()), decimalFormat.format(nv.getThangBacLuong()), nv.getHeSoLuong(), bl.getSoNgayDiLam(), decimalFormat.format(thuong+nv.getPhuCap()), decimalFormat.format(bl.getTienPhat()), decimalFormat.format(bl.getBhxh()), decimalFormat.format(bl.getLuongTong()),bl.getLuongTong()>0?true:false,"Đã có bảng lương" });
			}

		}
		setTxtSoNVChuaTinhLuong();
		setTxtTongSoNV();
		setTxtTongLuongCanTraNV();
	}
	private boolean kiemSoBangChamCongCua1NV(String maNV,int thang, int nam){
		bc_DAO = new BangChamCongNV_DAO();
		double soBangChamCong = bc_DAO.getSoBangChamCongCua1NV(maNV, thang, nam);
		if(soBangChamCong <25 ){
			return false;
		}
		return true;
	}
	private void chonTatCaFalse() {
		int rowCount = tblDSLuongNV.getRowCount();

		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {
			// Lấy giá trị tại cột 10 của dòng hiện tại
			int rowIndex = tblDSLuongNV.convertRowIndexToModel(i);
			boolean giaTriCot10 = (boolean) modelTableDSLuongNV.getValueAt(i, 10);

			// Kiểm tra nếu giá trị cột 10 là false
			if (!giaTriCot10) {
				// Chọn dòng hiện tại
				tblDSLuongNV.addRowSelectionInterval(i, i);
			}
		}
	}
	private void ChonTatCaTrue() {
		int rowCount = tblDSLuongNV.getRowCount();

		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {
			int rowIndex = tblDSLuongNV.convertRowIndexToModel(i);
			// Lấy giá trị tại cột 10 của dòng hiện tại
			boolean giaTriCot10 = (boolean) modelTableDSLuongNV.getValueAt(i, 10);

			// Kiểm tra nếu giá trị cột 10 là false
			if (giaTriCot10) {
				// Chọn dòng hiện tại
				tblDSLuongNV.addRowSelectionInterval(i, i);
			}
		}
	}
	private boolean kiemtraCoFalseKhong(){
		int rowCount = tblDSLuongNV.getRowCount();

		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {
			// Lấy giá trị tại cột 10 của dòng hiện tại
			boolean giaTriCot10 = (boolean) modelTableDSLuongNV.getValueAt(i, 10);

			// Kiểm tra nếu giá trị cột 10 là false
			if (!giaTriCot10) {
				return true;
			}
		}
		return false;
	}

	private int soNhanVienChuaTinhLuong() {
		int rowCount = tblDSLuongNV.getRowCount();
		int dem=0;
		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {
			// Lấy giá trị tại cột 10 của dòng hiện tại
			boolean giaTriCot10 = (boolean) modelTableDSLuongNV.getValueAt(i, 10);

			// Kiểm tra nếu giá trị cột 10 là false
			if (!giaTriCot10) {

				dem++;
			}
		}
		return dem;
	}
	private int soNhanVien() {
		int rowCount = tblDSLuongNV.getRowCount();
		return rowCount;
	}
	private double tongLuongTra() {
		int rowCount = tblDSLuongNV.getRowCount();
		double tong=0;
		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {


			tong+= Double.parseDouble(modelTableDSLuongNV.getValueAt(i,9).toString().replace(",", ""));
		}
		return tong;
	}
	private void setTxtSoNVChuaTinhLuong() {
		txtSoNVChuaTinhLuong.setText(soNhanVienChuaTinhLuong()+"");

	}
	private void setTxtTongSoNV() {
		txtTongSoNV.setText(soNhanVien()+"");

	}
	private void setTxtTongLuongCanTraNV() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		txtTongLuongCanTraNV.setText(decimalFormat.format(tongLuongTra())+" VNĐ");

	}

	private void exportDsToExcel(int thang, int nam , String boPhan, String tongLuong) {


		if(kiemtraCoFalseKhong()){
			JOptionPane.showMessageDialog(this, "Vui Lòng Tính Lương Trước Khi In!");
		} else {
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				XSSFWorkbook workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("Danh sách");

				int columnCount = tblDSLuongNV.getColumnCount() - 2; // Exclude the last two columns

				// Merge the first two rows
				sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, columnCount - 1));
				XSSFRow mergedHeaderRow = sheet.createRow(0);
				mergedHeaderRow.createCell(0, CellType.STRING).setCellValue("Danh Sách Bảng Lương Tháng " + dinhDangThang(thang)+" Năm "+nam );
				XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
				XSSFFont font = sheet.getWorkbook().createFont();
				font.setBold(true);
				style.setFont(font);
				style.setAlignment(HorizontalAlignment.CENTER);
				style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);

				XSSFCell cell = mergedHeaderRow.createCell(0, CellType.STRING);
				cell.setCellValue("Danh Sách Bảng Lương Tháng " + dinhDangThang(thang) + " Năm " + nam);
				cell.setCellStyle(style);

				// Create the second row
				// Tạo dòng subHeaderRow
				XSSFRow subHeaderRow = sheet.createRow(2);

				// Ô Tháng chiếm 2 cột bên phải
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
				subHeaderRow.createCell(0, CellType.STRING).setCellValue("Tháng: " + dinhDangThang(thang));

				// Ô Năm chiếm 2 cột bên phải
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 3));
				subHeaderRow.createCell(2, CellType.STRING).setCellValue("Năm: " + nam);

				// Ô Bộ phận chiếm 3 cột bên phải
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 6));
				subHeaderRow.createCell(4, CellType.STRING).setCellValue("" + boPhan);

				sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 10));
				subHeaderRow.createCell(7, CellType.STRING).setCellValue("Ngày In: " + dtf.format(LocalDate.now()));

				// Create the header row
				XSSFRow headerRow = sheet.createRow(3);
				for (int i = 0; i < columnCount; i++) {
					headerRow.createCell(i, CellType.STRING).setCellValue(tblDSLuongNV.getColumnName(i));
				}

				// Populate the data rows
				for (int i = 0; i < tblDSLuongNV.getRowCount(); i++) {
					XSSFRow row = sheet.createRow(i + 4); // Start from row 5
					for (int j = 0; j < columnCount; j++) {
						Object value = tblDSLuongNV.getValueAt(i, j);
						if (value != null) {
							if (value instanceof Number) {
								row.createCell(j, CellType.NUMERIC).setCellValue(((Number) value).doubleValue());
							} else {
								row.createCell(j, CellType.STRING).setCellValue(value.toString());
							}
						}
					}
				}

				// Create the last row
				XSSFRow lastRow = sheet.createRow(sheet.getLastRowNum() + 1);
				lastRow.createCell(0, CellType.STRING).setCellValue("Tổng Cộng: " + tongLuong);

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Chọn vị trí lưu file Excel");
				fileChooser.setFileFilter(new FileNameExtensionFilter("Tệp Excel (*.xlsx)", "xlsx"));
				int returnValue = fileChooser.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String filePath = selectedFile.getAbsolutePath();

					try (FileOutputStream fos = new FileOutputStream(filePath + ".xlsx")) {
						workBook.write(fos);
						JOptionPane.showMessageDialog(frame, "In thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object object = e.getSource();
		if(object.equals(tblThangLuongNhanVien)){
			int row = tblThangLuongNhanVien.getSelectedRow();
			int thang = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 0).toString());
			int nam = Integer.parseInt(modelTableThangLuongNV.getValueAt(row, 1).toString());
			String mabp = modelTableThangLuongNV.getValueAt(row, 2).toString();
			cboThangLuongNV.setSelectedItem(modelTableThangLuongNV.getValueAt(row, 0).toString());
			cboNamLuongNV.setSelectedItem(modelTableThangLuongNV.getValueAt(row, 1).toString());
			cboBoPhanLuongNV.setSelectedItem(modelTableThangLuongNV.getValueAt(row, 2).toString());
			taoDSBangLuongtuDBtheoDK(thang,nam,mahoaTenBoPhan(mabp));

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//		throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//		throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//		throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//		throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
	}
}