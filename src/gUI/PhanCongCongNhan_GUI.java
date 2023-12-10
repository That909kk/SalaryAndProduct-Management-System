package gUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connectDB.ConnectDB;
import dao.BangPhanCongCN_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import dao.Xuong_DAO;
import entity.BangPhanCongCN;
import entity.CongDoan;
import entity.CongNhan;
import entity.HopDong;
import entity.SanPham;
import entity.Xuong;

public class PhanCongCongNhan_GUI extends JFrame implements ActionListener, MouseListener, TableModelListener, ListSelectionListener{

	private JPanel contentPane;
	private JPanel Menu;
	private JPanel pnlPCCD;
	
	private DefaultTableModel modelCongDoan;
	private TableRowSorter<DefaultTableModel> congDoanSorter;
	private JTable tableCongDoan;
	private JTextField txtSoCN;
	private JTextField txtSoCD;
	private DefaultTableModel modelPCCN;
	private JTable tablePCCN;
	private JTextField txtNDTim;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnIn;
	
	private int rowCD = 0;
	private SanPham_DAO sp_DAO;
	private CongDoan_DAO cd_DAO;
	private CongNhan_DAO cn_DAO;
	private Xuong_DAO x_DAO;
	private BangPhanCongCN_DAO bPCCN_DAO;
	private ArrayList<BangPhanCongCN> listBPCCN = new ArrayList<BangPhanCongCN>();
	private ArrayList<Integer> listRowPCCN = new ArrayList<Integer>();
	private ArrayList<Integer> listRowUnchecked = new ArrayList<Integer>();
	private boolean modelPCCNIsChanging = false;
	private int minSelectedRow = -1;
	private int maxSelectedRow = -1;
	private boolean tableModelListenerIsChanging = false;
	
	private JButton btnTim;
	private JButton btnHoanTat;
	private JLabel lblThongBaoSoLuongPhanCong;
	
	public static void main(String[] args) {
		PhanCongCongNhan_GUI pccn = new PhanCongCongNhan_GUI();
		pccn.setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public PhanCongCongNhan_GUI() {
		super("Phân công công nhân");
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sp_DAO = new SanPham_DAO();
		cd_DAO = new CongDoan_DAO();
		cn_DAO = new CongNhan_DAO();
		x_DAO = new Xuong_DAO();
		
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
		
		lblThongBaoSoLuongPhanCong = new JLabel("(*)");
		lblThongBaoSoLuongPhanCong.setForeground(Color.RED);
		lblThongBaoSoLuongPhanCong.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblThongBaoSoLuongPhanCong.setBounds(500, 590, 500, 30);
		pnlPCCD.add(lblThongBaoSoLuongPhanCong);
	}
	
	protected JPanel getPCCNGUI() {
		bPCCN_DAO = new BangPhanCongCN_DAO();
		
		pnlPCCD = new JPanel();
		pnlPCCD.setBackground(new Color(240, 248, 255));
		pnlPCCD.setBounds(0, 50, 1264, 632);
		contentPane.add(pnlPCCD);
		pnlPCCD.setLayout(null);
		
		String[] header = {"Tên sản phẩm", "Mã công đoạn", "Công đoạn", "Số lượng sản phẩm", "Số lượng công nhân", "Ngày bắt đầu", "Ngày kết thúc dự kiến"};
		modelCongDoan = new DefaultTableModel(header, 0);
		tableCongDoan = new JTable(modelCongDoan);
		
		tableCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableCongDoan.setRowHeight(26);
		
		congDoanSorter = new TableRowSorter<DefaultTableModel>(modelCongDoan);
		tableCongDoan.setRowSorter(congDoanSorter);
		
		tableCongDoan.getColumnModel().getColumn(0).setPreferredWidth(172);
		tableCongDoan.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableCongDoan.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableCongDoan.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableCongDoan.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableCongDoan.getColumnModel().getColumn(5).setPreferredWidth(95);
		tableCongDoan.getColumnModel().getColumn(6).setPreferredWidth(125);
		
		layDSCongDoanVaSanPhamTuDB();
		
		JScrollPane scrCongDoan = new JScrollPane(tableCongDoan);
		scrCongDoan.setBackground(new Color(255, 255, 255));
		scrCongDoan.setBounds(0, 0, 832, 230);
		scrCongDoan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pnlPCCD.add(scrCongDoan);
		
		JPanel pnlThongTinCongDoan = new JPanel();
		pnlThongTinCongDoan.setBackground(new Color(240, 248, 255));
		pnlThongTinCongDoan.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u00E1o c\u00E1o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinCongDoan.setBounds(836, 0, 428, 230);
		pnlPCCD.add(pnlThongTinCongDoan);
		pnlThongTinCongDoan.setLayout(null);
		
		JLabel lblSoCN = new JLabel("Số công nhân chưa phân công:");
		lblSoCN.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoCN.setBounds(30, 20, 290, 30);
		pnlThongTinCongDoan.add(lblSoCN);
		
		txtSoCN = new JTextField("");
		txtSoCN.setDisabledTextColor(new Color(255, 0, 0));
		txtSoCN.setForeground(new Color(255, 0, 0));
		txtSoCN.setBackground(new Color(240, 248, 255));
		txtSoCN.setBorder(null);
		txtSoCN.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSoCN.setEnabled(false);
		txtSoCN.setBounds(320, 20, 100, 30);
		txtSoCN.setColumns(10);
		txtSoCN.setText(cn_DAO.getDSCongNhan().size() - bPCCN_DAO.getDSCongNhanDuocPhanCong().size() + "");
		pnlThongTinCongDoan.add(txtSoCN);
		
		JLabel lblSoCD = new JLabel("Số công đoạn chưa phân công:");
		lblSoCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSoCD.setBounds(30, 60, 284, 30);
		pnlThongTinCongDoan.add(lblSoCD);
		
		txtSoCD = new JTextField("");
		txtSoCD.setDisabledTextColor(new Color(255, 0, 0));
		txtSoCD.setBackground(new Color(240, 248, 255));
		txtSoCD.setBorder(null);
		txtSoCD.setEnabled(false);
		txtSoCD.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtSoCD.setColumns(10);
		txtSoCD.setBounds(315, 60, 100, 30);
		txtSoCD.setText(cd_DAO.getDSCongDoanTheoTrangThai(false).size() + "");
		pnlThongTinCongDoan.add(txtSoCD);
		
		btnThem = new JButton("Thêm vào công đoạn");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnThem.setBounds(30, 100, 370, 50);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setIconTextGap(6);
		pnlThongTinCongDoan.add(btnThem);
		
		btnIn = new JButton("Xuất Excel");
		btnIn.setBackground(new Color(255, 255, 255));
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnIn.setBounds(230, 160, 170, 50);
		btnIn.setIcon(new ImageIcon("img\\icons\\icons8-excel-20.png"));
		btnIn.setIconTextGap(6);
		pnlThongTinCongDoan.add(btnIn);
		
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		
		JPanel pnlCacCongDoan = new JPanel();
		pnlCacCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCacCongDoan.setBackground(new Color(240, 248, 255));
		pnlCacCongDoan.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch c\u00E1c c\u00F4ng nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCacCongDoan.setBounds(0, 228, 1264, 352);
		pnlPCCD.add(pnlCacCongDoan);
		pnlCacCongDoan.setLayout(null);
		
		String[] header_CongDoan = {"Mã Công Nhân", "Họ", "Tên", "Chuyên môn", "Ca làm", "Xưởng", "Trạng thái giao việc", 
				"Số lượng được phân công"};
		modelPCCN = new DefaultTableModel(header_CongDoan, 0) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 6)
					return Boolean.class;
				return Object.class;
			}
		};
		
		tablePCCN = new JTable(modelPCCN);
		tablePCCN.setRowHeight(26);
		tablePCCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		tablePCCN.setColumnSelectionAllowed(true);
//		tablePCCN.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrPCCN = new JScrollPane(tablePCCN);
		scrPCCN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrPCCN.setBackground(new Color(255, 255, 255));
		scrPCCN.setLocation(10, 20);
		scrPCCN.setSize(1244, 322);
		pnlCacCongDoan.add(scrPCCN);
		
		JLabel lblTim = new JLabel("Tìm kiếm theo tên sản phẩm:");
		lblTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTim.setBounds(10, 590, 240, 30);
		pnlPCCD.add(lblTim);
		
		txtNDTim = new JTextField();
		txtNDTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNDTim.setBounds(255, 590, 200, 30);
		pnlPCCD.add(txtNDTim);
		txtNDTim.setColumns(30);
		
		btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBounds(457, 590, 30, 30);
		btnTim.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		pnlPCCD.add(btnTim);
		
		btnHoanTat = new JButton("Hoàn Tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHoanTat.setBounds(1095, 585, 160, 40);
		btnHoanTat.setIcon(new ImageIcon("img\\icons\\icons8-checked-checkbox-24.png"));
		pnlPCCD.add(btnHoanTat);
		
		btnThem.setEnabled(false);
		btnIn.setEnabled(false);
		
		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setBounds(30, 160, 170, 50);
		pnlThongTinCongDoan.add(btnCapNhat);
		btnCapNhat.setBackground(new Color(255, 255, 255));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCapNhat.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnCapNhat.setIconTextGap(6);
		btnCapNhat.setEnabled(false);
		btnCapNhat.addActionListener(this);
		btnHoanTat.setEnabled(false);
		
		btnThem.addActionListener(this);
		btnIn.addActionListener(this);
		btnTim.addActionListener(this);
		btnHoanTat.addActionListener(this);
		
		tablePCCN.addMouseListener(this);
		tableCongDoan.addMouseListener(this);
		modelPCCN.addTableModelListener(this);
		tablePCCN.getSelectionModel().addListSelectionListener(this);
		return pnlPCCD;
	}
	/**
	 * Phương thức hiển thị danh sách công đoạn từ database
	 */
	private void layDSCongDoanVaSanPhamTuDB() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		sp_DAO = new SanPham_DAO();
		cd_DAO = new CongDoan_DAO();
		
		modelCongDoan.setRowCount(0);
		
		for (CongDoan cd : cd_DAO.getDSCongDoan()) {
			SanPham sp = sp_DAO.getMotSanPham(cd.getSanPham().getMaSP());
			ArrayList<BangPhanCongCN> bPCCN = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(cd.getMaCongDoan());
			
			if (!cd.isTrangThai()) {
				modelCongDoan.addRow(new Object[] {sp.getTenSP(), cd.getMaCongDoan(), cd.getTenCongDoan(), 
						cd.getSoLuongSanPham(), bPCCN.size() + " / " + cd.getSoLuongCongNhanDuKien(),
						cd.getNgayBatDau().format(dtf), cd.getNgayKetThucDuKien().format(dtf)});
			}
		}
	}
	/**
	 * Phương thức hiển thị danh sách phân công công nhân từ database theo tên xưởng
	 * @param xuong
	 * @param maCD
	 */
	private void layDSPCCCongNhanTuDBTheoXuong(String xuong, String maCD) {
		cd_DAO = new CongDoan_DAO();
		cn_DAO = new CongNhan_DAO();
		x_DAO = new Xuong_DAO();
		bPCCN_DAO = new BangPhanCongCN_DAO();
		ArrayList<BangPhanCongCN> listPCCN = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(maCD);
		modelPCCN.setRowCount(0);
		
		if (listPCCN.size() == 0) {
			for (CongNhan cn : cn_DAO.getDSCongNhanTheoXuongVaChuaDuocPhanCong(xuong)) {
				Xuong x = x_DAO.getMotXuong(cn.getXuong().getMaXuong());
				modelPCCN.addRow(new Object[] {cn.getMaCN(), cn.getHo(), cn.getTen(), cn.getChuyenMon(), cn.getCaLamViec(),
						x.getTenXuong(), false, 0});
			}
		} else {
			for (BangPhanCongCN bangPhanCongCN : listPCCN) {
				CongNhan cn = cn_DAO.getCongNhanTheoMaCN(bangPhanCongCN.getCongNhan().getMaCN());
				Xuong x = x_DAO.getMotXuong(cn.getXuong().getMaXuong());
				
				modelPCCN.addRow(new Object[] {cn.getMaCN(), cn.getHo(), cn.getTen(), cn.getChuyenMon(), cn.getCaLamViec(),
						x.getTenXuong(), bangPhanCongCN.isTrangThai(), bangPhanCongCN.getSoLuongSanPham()});
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnThem)) {
			int countRowUnchecked = listRowUnchecked.size();
			listBPCCN.clear();
			CongDoan cd = cd_DAO.getMotCongDoanTheoMaCD(tableCongDoan.getValueAt(rowCD, 1).toString());
			
			ArrayList<BangPhanCongCN> listTheoCD = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(tableCongDoan.getValueAt(rowCD, 1).toString());
			
			for (Integer rowIndex : listRowPCCN) {
				if (!listRowUnchecked.contains(rowIndex)) {
					modelPCCN.setValueAt(true, rowIndex, 6);
				} else {
					modelPCCN.setValueAt(false, rowIndex, 6);
					modelPCCN.setValueAt(0, rowIndex, 7);
				}
			}
			
			if (listTheoCD.size() == 0) {
				for (Integer rowIndex : listRowPCCN) {
					int soLuongRowPCCNDaChon = listRowPCCN.size();
					int soLuongSP = cd.getSoLuongSanPham() % soLuongRowPCCNDaChon == 0 
							? (cd.getSoLuongSanPham() / soLuongRowPCCNDaChon) 
							: ((cd.getSoLuongSanPham() + (cd.getSoLuongSanPham() % soLuongRowPCCNDaChon) + 1) / soLuongRowPCCNDaChon);
					
					if (!listRowUnchecked.contains(rowIndex)) {
						modelPCCN.setValueAt(true, rowIndex, 6);
						modelPCCN.setValueAt(soLuongSP, rowIndex, 7);
						listBPCCN.add(getCongNhanDuocChon(rowIndex));
					} else {
						modelPCCN.setValueAt(false, rowIndex, 6);
						modelPCCN.setValueAt(0, rowIndex, 7);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Hiện tại công đoạn này đã phân công đủ số sản phẩm. Hãy chọn công đoạn khác hoặc"
						+ " chọn nút cập nhật để phân công lại công đoạn này");
			}
		}
		
		if (o.equals(btnHoanTat)) {
			if (Integer.parseInt(modelPCCN.getValueAt(listRowPCCN.get(0), 7).toString()) == 0) {
				JOptionPane.showMessageDialog(null, "Công đoạn này chưa phân công số lượng");
			} else {
				listBPCCN = new ArrayList<BangPhanCongCN>();
				cd_DAO = new CongDoan_DAO();
				bPCCN_DAO = new BangPhanCongCN_DAO();
				
				for (Integer rowCheckedIndex : listRowPCCN) {
					listBPCCN.add(getCongNhanDuocChon(rowCheckedIndex));
				}
				
				for (BangPhanCongCN bangPhanCongCN : listBPCCN) {
					bangPhanCongCN.setSoLuongSanPham(Integer.parseInt(tablePCCN.getValueAt(listRowPCCN.get(0), 7).toString()));
					bPCCN_DAO.insertPhanCongCongNhan(bangPhanCongCN);
				}
				
				modelPCCNIsChanging = true;
				JOptionPane.showMessageDialog(null, "Phân công công đoạn thành công");
				
				txtSoCN.setText(cn_DAO.getDSCongNhan().size() - bPCCN_DAO.getDSCongNhanDuocPhanCong().size() + "");
				
				layDSCongDoanVaSanPhamTuDB();
				
				if (modelPCCNIsChanging) {
					btnHoanTat.setEnabled(false);
					btnCapNhat.setEnabled(true);
				}
			}
		}
		
		if (o.equals(btnCapNhat)) {
			btnCapNhat.setEnabled(false);
			btnHoanTat.setEnabled(true);
			modelPCCNIsChanging = false;
			
			rowCD = tableCongDoan.getSelectedRow();
			String maCD = modelCongDoan.getValueAt(rowCD, 1).toString();
			String tenXuong = "%" + layChuoiTruocKyTuTrang(modelCongDoan.getValueAt(rowCD, 2).toString()) + "%";
			
			bPCCN_DAO.deleteALLPCCuaCongDoan(maCD);
			layDSPCCCongNhanTuDBTheoXuong(tenXuong, maCD);
			txtSoCN.setText(cn_DAO.getDSCongNhan().size() - bPCCN_DAO.getDSCongNhanDuocPhanCong().size() + "");
			
			layDSCongDoanVaSanPhamTuDB();
		}
		
		if (o.equals(btnTim)) {
			String tenSP = txtNDTim.getText().trim();
			
			if (tenSP.length() == 0) {
				congDoanSorter.setRowFilter(null);
			} else {
				try {
					congDoanSorter.setRowFilter(RowFilter.regexFilter(tenSP, 0));
				} catch (PatternSyntaxException pse) {
					System.out.println("Bad regex pattern");
				}
			}
		}
		
		if (o.equals(btnIn)) {
			String maCongDoan = tableCongDoan.getValueAt(rowCD, 1).toString();
			xuatFileExcel(maCongDoan);
		}
	}
	/**
	 * Phương thức dùng để xuất file excel bảng phân công công nhân theo công đoạn đã chọn
	 * @param maCongDoan
	 */
	private void xuatFileExcel(String maCongDoan) {
		int firstSheetRow = 0;
		HopDong_DAO hd_DAO = new HopDong_DAO();
		
		CongDoan cd = cd_DAO.getMotCongDoanTheoMaCD(maCongDoan);
		SanPham sp = sp_DAO.getMotSanPham(cd.getSanPham().getMaSP());
		HopDong hd = hd_DAO.getMotHopDong(sp.getHopDong().getMaHopDong());
		
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet(sp.getTenSP() + " - " + cd.getTenCongDoan());
		XSSFRow row = null;
		XSSFCell cell = null;
		
		row = sheet.createRow(3);
		
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Mã nhân viên");
		
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Họ");
		
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Tên");
		
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("Xưởng");
		
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("Số lượng sản phẩm");
		
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("Ca làm");
		
		sheet.autoSizeColumn(0);
		ArrayList<BangPhanCongCN> listPC = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(maCongDoan);
		
		for (BangPhanCongCN phanCongCN : listPC) {
			row = sheet.createRow(4 + firstSheetRow);
			
			CongNhan cn = cn_DAO.getCongNhanTheoMaCN(phanCongCN.getCongNhan().getMaCN());
			Xuong x = x_DAO.getMotXuong(cn.getXuong().getMaXuong());
			
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(phanCongCN.getCongNhan().getMaCN());
			
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(cn.getHo());
			
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(cn.getTen());
			
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(x.getTenXuong());
			
			cell = row.createCell(4, CellType.NUMERIC);
			cell.setCellValue(phanCongCN.getSoLuongSanPham());
			
			cell = row.createCell(5, CellType.STRING);
			if (cn.getCaLamViec() == 1)
				cell.setCellValue("Sáng");
			else
				cell.setCellValue("Tối");
			
			firstSheetRow += 1;
		}
		
		String filePath = "";
		
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = fc.showSaveDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			if (fc.getSelectedFile().isDirectory()) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy");

				filePath += fc.getSelectedFile();
				File file = new File(filePath + "//" + sp.getTenSP() + "_" + cd.getTenCongDoan() 
				+ "_" + hd.getNgayKy().format(dtf) + ".xlsx");
				
				try {
					FileOutputStream fos = new FileOutputStream(file);
					workBook.write(fos);
					fos.close();
					
					JOptionPane.showMessageDialog(null, "Xuất file excel thành công");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(tableCongDoan)) {
			btnThem.setEnabled(true);
			btnTim.setEnabled(true);
			
			hienThiDSPCCNTrenModel();
			
			String maCD = tableCongDoan.getValueAt(rowCD, 1).toString();
			ArrayList<BangPhanCongCN> listBPCCNTheoCD = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(maCD);
			
			if (listBPCCNTheoCD.size() != 0) {
				lblThongBaoSoLuongPhanCong.setText("(*) Hiện tại công đoạn này đã phân công đủ số sản phẩm.");
				btnHoanTat.setEnabled(false);
				btnCapNhat.setEnabled(true);
				btnIn.setEnabled(true);
				btnThem.setEnabled(false);
			} else {
				lblThongBaoSoLuongPhanCong.setText("(*)");
				btnHoanTat.setEnabled(true);
				btnCapNhat.setEnabled(false);
				btnIn.setEnabled(false);
				btnThem.setEnabled(true);
			}
		}
		
		if (o.equals(tablePCCN)) {
			int rowPCCN = tablePCCN.getSelectedRow();
			
			String maCD = tableCongDoan.getValueAt(rowCD, 1).toString();
			ArrayList<BangPhanCongCN> listBPCCNTheoCD = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(maCD);
			
			if (listBPCCNTheoCD.size() != 0) {
				JOptionPane.showMessageDialog(null, "Công đoạn này đã được phân công. Nếu muốn phân công lại, bạn hãy nhấn cập nhật.");
			} else {
				if (!((Boolean) modelPCCN.getValueAt(rowPCCN, 6)).booleanValue()) {
					modelPCCN.setValueAt(0, rowPCCN, 7);
					modelPCCN.setValueAt(false, rowPCCN, 6);
					listRowUnchecked.add(rowPCCN);
					listRowPCCN.remove(listRowPCCN.indexOf(rowPCCN));
				} else {
					modelPCCN.setValueAt(true, rowPCCN, 6);
					listRowPCCN.add(rowPCCN);
					listRowUnchecked.remove(listRowUnchecked.indexOf(rowPCCN));
				}
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Phương thức lấy chuỗi ký tự trước ký tự trắng đầu tiên
	 * @param input
	 * @return String firstPart
	 */
	private static String layChuoiTruocKyTuTrang(String input) {
        String[] parts = input.split(" ");
        if (parts.length > 0) {
            return parts[0].toLowerCase();
        } else {
            return input.toLowerCase();
        }
    }
	
	private BangPhanCongCN getCongNhanDuocChon(int rowPCCN) {
		cn_DAO = new CongNhan_DAO();
		cd_DAO = new CongDoan_DAO();
		
		String maCN = modelPCCN.getValueAt(rowPCCN, 0).toString();
		String maCD = modelCongDoan.getValueAt(rowCD, 1).toString();
		
		String maPCCN = maCN.substring(2) + maCD;
		
		CongNhan cn = cn_DAO.getCongNhanTheoMaCN(maCN);
		CongDoan cd = cd_DAO.getMotCongDoanTheoMaCD(maCD);
		
		BangPhanCongCN bPCCN = new BangPhanCongCN(maPCCN, true, LocalDate.now(), 0, cn, cd);
		return bPCCN;
	}
	/**
	 * Phương thức hiển thị danh sách phân công công nhân trên modelTable
	 */
	private void hienThiDSPCCNTrenModel() {
		listRowPCCN.clear();
		listRowUnchecked.clear();
		
		cn_DAO = new CongNhan_DAO();
		cd_DAO = new CongDoan_DAO();
		
		int row = tableCongDoan.getSelectedRow();
		rowCD = row;
		
		String maCD = modelCongDoan.getValueAt(row, 1).toString();
		String tenCD = modelCongDoan.getValueAt(row, 2).toString();
		String tuDauTienCuaTenCongDoan = "%" + layChuoiTruocKyTuTrang(tenCD) + "%";
		
		layDSPCCCongNhanTuDBTheoXuong(tuDauTienCuaTenCongDoan, maCD);
		
		if (bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(maCD).size() > 0)
			btnCapNhat.setEnabled(true);
		else
			btnCapNhat.setEnabled(false);
		
		int soLuongCNTheoXuong = modelPCCN.getRowCount();
		int soLuongCNDuKien = cd_DAO.getMotCongDoanTheoMaCD(maCD).getSoLuongCongNhanDuKien();
		
		if (soLuongCNTheoXuong == 0)
			modelPCCN.setRowCount(0);
		else {
			if (soLuongCNTheoXuong < soLuongCNDuKien) {
				for (int rowIndex = 0; rowIndex < soLuongCNTheoXuong; rowIndex++) {
					modelPCCN.setValueAt(true, rowIndex, 6);
					listRowPCCN.add(rowIndex);
				}
			} else if (soLuongCNTheoXuong > soLuongCNDuKien) {
				for (int rowIndex = 0; rowIndex < soLuongCNDuKien; rowIndex++) {
					modelPCCN.setValueAt(true, rowIndex, 6);
					listRowPCCN.add(rowIndex);
				}
			} else {
				for (int rowIndex = 0; rowIndex < soLuongCNTheoXuong; rowIndex++) {
					modelPCCN.setValueAt(true, rowIndex, 6);
					listRowPCCN.add(rowIndex);
				}
			} 
		}
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		Object o = e.getSource();
		if (o.equals(modelPCCN)) {
			if (tableModelListenerIsChanging)
				return;
			
			int firstRow = e.getFirstRow();
			int column = e.getColumn();
			
			if (column != 1 || maxSelectedRow == -1 || minSelectedRow == -1) {
				return;
			}
			
			tableModelListenerIsChanging = true;
			
			boolean value = ((Boolean) modelPCCN.getValueAt(firstRow, column)).booleanValue();
			
			for (int i = minSelectedRow; i <= maxSelectedRow; i++) {
				modelPCCN.setValueAt(0, i, column + 1);
				modelPCCN.setValueAt(Boolean.valueOf(value), i, column);
			} 
			
			minSelectedRow = -1;
			maxSelectedRow = -1;
			
			tableModelListenerIsChanging = false;
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object o = e.getSource();
		if (o.equals(tablePCCN)) {
			if (e.getValueIsAdjusting())
				return;
			minSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMinSelectionIndex();
			maxSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMaxSelectionIndex();
		}
	}
}
