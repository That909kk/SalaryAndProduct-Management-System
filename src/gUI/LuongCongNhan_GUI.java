package gUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.events.MouseEvent;

import dao.BangChamCongCN_DAO;
import dao.BangChamCongNV_DAO;
import dao.BangLuongCN_DAO;
import dao.BangLuongNV_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import dao.Xuong_DAO;
import entity.BangChamCongCN;
import entity.BangLuongCN;
import entity.BangLuongNV;
import entity.CongNhan;
import entity.Xuong;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.GridLayout;
import javax.swing.UIManager;

public class LuongCongNhan_GUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnHome;
	private JTable tblThangLuongCN;
	private JTable tblDSLuongCN;
	private JTextField txtGhiChuLuongCN;
	private JTextField txtSoCNChuaTinhLuong;
	private JTextField txtTongSoCN;
	private JTextField txtTongLuongCanTraCN;
	private JTextField txtTimKiemTheoTenCN;
	private DefaultTableModel modelTableThangLuongCN;
	private DefaultTableModel modelTableDSLuongCN;
	private BangChamCongCN_DAO bc_DAO;
	private SanPham_DAO sp_DAO;
	private CongDoan_DAO cd_DAO;
	private Xuong_DAO x_DAO;
	private CongNhan_DAO cn_DAO;
	private BangLuongCN_DAO bl_DAO;
	private Component frame;
	private JComboBox<String> cboThangLuongCN;
	private JComboBox<String> cboNamLuongCN;
	private BangChamCongCN_DAO bcc_DAO;
	private JComboBox<String> cboXuongCN;

	public LuongCongNhan_GUI() {
		super("Lương Cong Nhan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(this.getLuongCNGUI());
		
	}
	
	public JPanel getLuongCNGUI() {
		JPanel pnlLuongCN = new JPanel();
		pnlLuongCN.setBackground(new Color(240, 248, 255));
		pnlLuongCN.setBounds(0, 50, 1264, 632);
		pnlLuongCN.setLayout(null);
		
		String headerThangLuong[] = {"Tháng", "Năm", "Xưởng"};
		modelTableThangLuongCN = new DefaultTableModel(headerThangLuong, 0);
		tblThangLuongCN = new JTable(modelTableThangLuongCN);
		tblThangLuongCN.setFont(UIManager.getFont("TableHeader.font"));
		tblThangLuongCN.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblThangLuongCN.setRowSelectionAllowed(true);
		tblThangLuongCN.setRowHeight(26);
		tblThangLuongCN.setBounds(0, 0, 604, 185);
		pnlLuongCN.add(tblThangLuongCN);
		layDSThangLuongCNtuDB();
		String headerDSLuongCN[] = {"Mã Công Nhân", "Họ Tên", "Lương cơ bản", "Lương sản phẩm", "Số ngày làm", "Phụ Cấp", "Khấu trừ",
				"Bảo hiểm xã hội", "Tổng lương", "Đã Tính Lương", "Ghi chú"};
		modelTableDSLuongCN = new DefaultTableModel(headerDSLuongCN, 0){
			 @Override
			    public boolean isCellEditable(int row, int column) {
			        // Cho phép chỉnh sửa nếu cột không nằm trong khoảng 0 đến 5
			        return column >= 6;
			    }
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 9)
					return Boolean.class;
				return Object.class;
			}
		};
		
		tblDSLuongCN = new JTable(modelTableDSLuongCN);
		tblDSLuongCN.setFillsViewportHeight(true);
		tblDSLuongCN.setRowSelectionAllowed(true);
		tblDSLuongCN.setRowHeight(26);
		tblDSLuongCN.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblDSLuongCN.setBounds(0, 190, 1264, 362);
		pnlLuongCN.add(tblDSLuongCN);
		
		JScrollPane scrThangLuongCN = new JScrollPane(tblThangLuongCN);
		scrThangLuongCN.setBounds(0, 0, 604, 190);
		pnlLuongCN.add(scrThangLuongCN);
		
		JLabel lblTmKimTheoCN = new JLabel("Tìm Kiếm Theo Tên:");
		lblTmKimTheoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTmKimTheoCN.setBounds(10, 579, 159, 32);
		pnlLuongCN.add(lblTmKimTheoCN);
		
		txtTimKiemTheoTenCN = new JTextField();
		txtTimKiemTheoTenCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTimKiemTheoTenCN.setColumns(10);
		txtTimKiemTheoTenCN.setBounds(161, 579, 221, 32);
		pnlLuongCN.add(txtTimKiemTheoTenCN);
		
		JButton btnTimKiemTenCN = new JButton();
		btnTimKiemTenCN.setBackground(new Color(255, 255, 255));
		btnTimKiemTenCN.setBounds(392, 580, 39, 31);
		btnTimKiemTenCN.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		pnlLuongCN.add(btnTimKiemTenCN);
		
		JPanel pnlButtonLuongCN = new JPanel();
		pnlButtonLuongCN.setBackground(new Color(240, 248, 255));
		pnlButtonLuongCN.setBorder(UIManager.getBorder("Button.border"));
		pnlButtonLuongCN.setBounds(453, 563, 787, 57);
		pnlLuongCN.add(pnlButtonLuongCN);
		
		GridLayout gl_pnlButtonLuongCN = new GridLayout(0, 5, 0, 0);
        gl_pnlButtonLuongCN.setHgap(10);
        gl_pnlButtonLuongCN.setVgap(10);
		pnlButtonLuongCN.setLayout(gl_pnlButtonLuongCN);
		
		JButton btnXemChiTietLuongCN = new JButton("Xem Chi Tiết");
		btnXemChiTietLuongCN.setBackground(new Color(255, 255, 255));
		btnXemChiTietLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnXemChiTietLuongCN);
		
		JButton btnChonNhan = new JButton("Chọn Nhanh");
		btnChonNhan.setBackground(new Color(255, 255, 255));
		btnChonNhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnChonNhan);

		JButton btnTinhLuongCN = new JButton("Tính Lương");
		btnTinhLuongCN.setBackground(new Color(255, 255, 255));
		btnTinhLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnTinhLuongCN);
		
		JButton btnHoanTatLuongCN = new JButton("Hoàn Tất");
		btnHoanTatLuongCN.setBackground(new Color(255, 255, 255));
		btnHoanTatLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHoanTatLuongCN.setIcon(new ImageIcon("img\\icons\\icons8-checked-checkbox-24.png"));
		pnlButtonLuongCN.add(btnHoanTatLuongCN);
		
		JButton btnInBangLuongCN = new JButton("In");
		btnInBangLuongCN.setBackground(new Color(255, 255, 255));
		btnInBangLuongCN.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlButtonLuongCN.add(btnInBangLuongCN);
		
		JScrollPane scrDSLuongCN = new JScrollPane(tblDSLuongCN);
		scrDSLuongCN.setBounds(0, 200, 1268, 347);
		pnlLuongCN.add(scrDSLuongCN);
		
		JPanel pnlThongKeTinhLuong = new JPanel();
		pnlThongKeTinhLuong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongKeTinhLuong.setBackground(new Color(240, 248, 255));
		pnlThongKeTinhLuong.setBounds(604, 0, 660, 190);
		pnlLuongCN.add(pnlThongKeTinhLuong);
		pnlThongKeTinhLuong.setLayout(null);
		//~~~~~~~~~~
		cboThangLuongCN = new JComboBox<String>();
		cboThangLuongCN.setBounds(101, 29, 50, 30);
		cboThangLuongCN.setFont(new Font("Tahoma", Font.PLAIN, 15));
			for (int i = 1;i <= 12; i++){
			cboThangLuongCN.addItem(i+"");
		}
		cboThangLuongCN.setSelectedItem(LocalDate.now().getMonthValue()+"");
		pnlLuongCN.add(cboThangLuongCN);

		
		JLabel Thangcbo = new JLabel("Tháng:");
		Thangcbo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Thangcbo.setBounds(21, 29, 80, 30);
		pnlLuongCN.add(Thangcbo);
		
		JButton btnLoc = new JButton("Lọc");
		btnLoc.setBounds(590, 29, 60, 30);
		btnLoc.setBackground(new Color(255, 255, 255));
	
		cboNamLuongCN = new JComboBox<String>();
		cboNamLuongCN.setBounds(230, 29, 100, 30);
		cboNamLuongCN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		bcc_DAO = new BangChamCongCN_DAO();
		ArrayList<Integer> listNam = bcc_DAO.layDSNamKhacnhauCCCN();
		for(Integer nam2 : listNam){
			
			cboNamLuongCN.addItem(nam2.toString());
			
		}
		cboNamLuongCN.setSelectedItem(LocalDate.now().getYear()+"");
		pnlLuongCN.add(cboNamLuongCN);
		
		JLabel lblNamcbo = new JLabel("Năm:");
		lblNamcbo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNamcbo.setBounds(170, 29, 50, 30);
		pnlLuongCN.add(lblNamcbo);

		cboXuongCN = new JComboBox<String>();
		cboXuongCN.setBounds(430, 29, 155, 30);
		cboXuongCN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlLuongCN.add(cboXuongCN);
		
		ArrayList<Xuong> listXuong = x_DAO.layTatCaXuongKhacNhau();
		for(Xuong x : listXuong){
			String tenBP = x.getTenXuong();
			cboXuongCN.addItem(tenBP);
		}
		cboXuongCN.setSelectedItem(null);
		

		JLabel lblXuongCN = new JLabel("Xưởng:");
		lblXuongCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXuongCN.setBounds(350, 29, 80, 30);
		pnlLuongCN.add(lblXuongCN);		
		txtSoCNChuaTinhLuong = new JTextField();
		txtSoCNChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoCNChuaTinhLuong.setBounds(268, 69, 369, 30);
		pnlThongKeTinhLuong.add(txtSoCNChuaTinhLuong);
		txtSoCNChuaTinhLuong.setColumns(10);
		
		txtTongSoCN = new JTextField();
		txtTongSoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongSoCN.setBounds(268, 109, 369, 30);
		pnlThongKeTinhLuong.add(txtTongSoCN);
		txtTongSoCN.setColumns(10);
		
		txtTongLuongCanTraCN = new JTextField();
		txtTongLuongCanTraCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTongLuongCanTraCN.setBounds(268, 149, 369, 30);
		pnlThongKeTinhLuong.add(txtTongLuongCanTraCN);
		txtTongLuongCanTraCN.setColumns(10);

		JLabel lblSoCNChuaTinhLuong = new JLabel("Số Công Nhân Chưa Tính Lương:");
		lblSoCNChuaTinhLuong.setBounds(21, 69, 243, 30);
		pnlThongKeTinhLuong.add(lblSoCNChuaTinhLuong);
		lblSoCNChuaTinhLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTongSoCN = new JLabel("Tổng Số Công Nhân:");
		lblTongSoCN.setBounds(21, 109, 196, 30);
		pnlThongKeTinhLuong.add(lblTongSoCN);
		lblTongSoCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTongLuongCanTraCN = new JLabel("Tổng Tiền Lương Cần Trả:");
		lblTongLuongCanTraCN.setBounds(21, 149, 197, 30);
		pnlThongKeTinhLuong.add(lblTongLuongCanTraCN);
		lblTongLuongCanTraCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtSoCNChuaTinhLuong.setBorder(null);
		txtTongSoCN.setBorder(null);
		txtTongLuongCanTraCN.setBorder(null);
		txtSoCNChuaTinhLuong.setBackground(new Color(240, 248, 255));
		txtTongSoCN.setBackground(new Color(240, 248, 255));
		txtTongLuongCanTraCN.setBackground(new Color(240, 248, 255));
		
		pnlThongKeTinhLuong.add(cboThangLuongCN);
		pnlThongKeTinhLuong.add(cboNamLuongCN);
		pnlThongKeTinhLuong.add(cboXuongCN);
		pnlThongKeTinhLuong.add(Thangcbo);
		pnlThongKeTinhLuong.add(lblNamcbo);
		pnlThongKeTinhLuong.add(lblXuongCN);
		pnlThongKeTinhLuong.add(btnLoc);
		tblThangLuongCN.addMouseListener(this);
		btnChonNhan.addActionListener(this);
		btnHoanTatLuongCN.addActionListener(this);
		btnTimKiemTenCN.addActionListener(this);
		btnXemChiTietLuongCN.addActionListener(this);
		btnInBangLuongCN.addActionListener(this);
		btnTinhLuongCN.addActionListener(this);
		
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String thang = cboThangLuongCN.getSelectedItem().toString().trim();
				String nam = cboNamLuongCN.getSelectedItem().toString().trim();
				String Xuong = cboXuongCN.getSelectedItem().toString().trim();
				bcc_DAO= new BangChamCongCN_DAO();
				ArrayList<LocalDate> listkt = bcc_DAO.layTatCaThangvaNamkhacNhauCN();
				int temp=0;
				
				if(thang.equals("")||nam.equals("")||Xuong.equals("")){
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
					int rowCount = modelTableThangLuongCN.getRowCount();
					System.out.println(rowCount);
					for (int i = rowCount - 1; i >= 0; i--) {
						String thangLoc = modelTableThangLuongCN.getValueAt(i, 0).toString();
						String namLoc = modelTableThangLuongCN.getValueAt(i, 1).toString();
						String tenBPLoc = modelTableThangLuongCN.getValueAt(i, 2).toString();
						if(thangLoc.equals(thang)&&namLoc.equals(nam)&&tenBPLoc.equals(Xuong)){
							tblThangLuongCN.setRowSelectionInterval(i, i);

							int row = tblThangLuongCN.getSelectedRow();
							int thang1 = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 0).toString());
							int nam1 = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 1).toString());
							String xuong = modelTableThangLuongCN.getValueAt(row, 2).toString();
							taoDSBangLuongtuDBtheoDK(thang1,nam1,xuong);
						}
					}
				}
			}}
		});
		
		btnInBangLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblThangLuongCN.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn danh sách tháng lương để in!");
				}
				else {
				int thang = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 0).toString());
				int nam = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 1).toString());
				String xuong = modelTableThangLuongCN.getValueAt(row, 2).toString();
				String tongTra= txtTongLuongCanTraCN.getText();
				exportDsToExcel(thang,nam,xuong,tongTra);}
			}
		});
		
		btnChonNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowdk = tblThangLuongCN.getSelectedRow();
				if (rowdk < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn ít nhất 1 điều kiện để chọn nhanh danh sách nhân viến chưa tính lương!");
				}else
					{
						if(kiemtraCoFalseKhong()){
							chonTatCaFalse();
						} else {
							int chon = JOptionPane.showConfirmDialog(null, "Bạn có muốn chọn tất cả công nhân không?", "Thông báo", JOptionPane.YES_NO_OPTION);
							if (chon == JOptionPane.YES_OPTION) {
								chonTatCaTrue();
								
							}
						}
					}}
		});
		btnHoanTatLuongCN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Thực hiện tương tự như ở LuongNhanVien_GUI
				int rowdk = tblThangLuongCN.getSelectedRow();
				int[] rows = tblDSLuongCN.getSelectedRows();
				if (rows.length <= 0 || rowdk < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 Công Nhân cần tính lương");
				}else{
					if(rows.length > 0){
						int rowas =tblThangLuongCN.getSelectedRow();
						int thang = Integer.parseInt(modelTableThangLuongCN.getValueAt(rowas, 0).toString());
						int nam = Integer.parseInt(modelTableThangLuongCN.getValueAt(rowas, 1).toString());
						if(tblDSLuongCN.getRowSorter()!=null){
							int chon = JOptionPane.showConfirmDialog(null, "Bạn đang trong danh sách tìm kiếm quay lại để hoàn tất việc chấm lương?", "Thông báo", JOptionPane.YES_NO_OPTION);
							if(chon==JOptionPane.YES_OPTION){
								tblDSLuongCN.setRowSorter(null);
							}}
							else{
								int thongBao =0;
								int dk=0;
								for(int i=0;i<rows.length;i++){
									int row=rows[i];
									boolean daTinhLuong = (boolean) modelTableDSLuongCN.getValueAt(row, 9);
									if(daTinhLuong==true){
										BangLuongCN bl = new BangLuongCN();
										CongDoan_DAO cd_DAO = new CongDoan_DAO();
										bc_DAO = new BangChamCongCN_DAO();
										bl.setMaLuongCN(modelTableDSLuongCN.getValueAt(row, 0).toString()+dinhDangThang(thang)+ lay2kitucuoicuaNam(nam));
										bl.setCn(cn_DAO.getCongNhanTheoMaCN(modelTableDSLuongCN.getValueAt(row, 0).toString()));
										bl.setThang(thang);
										bl.setNam(nam);
										bl.setBhxh(Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 7).toString().replace(",", "")));
										bl.setTongSanLuong(bc_DAO.laySoSanLuongCuaCNTheoThangNam(modelTableDSLuongCN.getValueAt(row, 0).toString(), thang, nam));
										bl.setSoNgayNghiKhongPhep(bc_DAO.laySoNgayNghiKhongPhepCua1CNTheoThangNam(modelTableDSLuongCN.getValueAt(row, 0).toString(), thang, nam));
										bl.setTienPhat(Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 6).toString().replace(",", "")));
										bl.setLuongTong(Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 8).toString().replace(",", "")));

										BangLuongCN_DAO bl_DAO = new BangLuongCN_DAO();
										BangLuongCN bl1 = bl_DAO.getMotBangLuongCNTheoThangNam(bl.getCn().getMaCN(), thang, nam);
										if(bl1==null)
										{
											bl_DAO.insertBangLuongCN(bl);
											dk=1;
											thongBao=1;
											
										}else{
											bl_DAO.updateBangLuongCN(bl);
											dk=1;
											thongBao=1;
											
										}
									}else{
										thongBao=1;
										dk=0;
									}
								} 
								if(thongBao==1&&dk==1){
									JOptionPane.showMessageDialog(null, "Hoàn tất tính lương cho các công nhân được chọn");
									int row = tblThangLuongCN.getSelectedRow();
									int thang1 = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 0).toString());
									int nam1 = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 1).toString());
									String xuong = modelTableThangLuongCN.getValueAt(row, 2).toString();
									taoDSBangLuongtuDBtheoDK(thang1, nam1, xuong);
									bc_DAO = new BangChamCongCN_DAO();
									BangChamCongCN bc = bc_DAO.layBangChamCongCuoiCungCuaThang(modelTableDSLuongCN.getValueAt(row, 0).toString(), thang, nam);
									if(bc!=null) {
									bc.setGhiChu(bc.getGhiChu()+"Đã Lưu Vào Ngày:"+LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
									
									bc_DAO.updateGhiChuBCCCN(bc);}

							}
							else{
								if(thongBao==1&&dk==0)
								JOptionPane.showMessageDialog(null, "Hoàn tất tính lương thất bại!Vui Lòng tính lương trước!");
							else 
								JOptionPane.showMessageDialog(null, "Hoàn tất tính lương thất bại");
							}
							}
						}
					}
					

			}
		});
		btnTimKiemTenCN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dkCanTim = txtTimKiemTheoTenCN.getText().trim().toLowerCase();
				
				// Kiểm tra nếu ô nhập rỗng
				if (dkCanTim.isEmpty()) {
					// Hiển thị lại toàn bộ danh sách nếu ô tìm kiếm trống
					tblDSLuongCN.setRowSorter(null);
				} else {
					// Tạo một RowFilter để lọc các dòng theo điều kiện tìm kiếm
					RowFilter<Object, Object> rowFilter = new RowFilter<Object, Object>() {
						@Override
						public boolean include(Entry<? extends Object, ? extends Object> entry) {
							String tenCongNhan = entry.getStringValue(1).toLowerCase();
							String maCongNhan = entry.getStringValue(0).toLowerCase();

							// Kiểm tra nếu tên hoặc mã chứa từ khóa tìm kiếm
							return tenCongNhan.contains(dkCanTim) || maCongNhan.contains(dkCanTim);
						}
					};

					// Tạo một TableRowSorter với RowFilter đã tạo
					TableRowSorter<TableModel> sorter = new TableRowSorter<>(modelTableDSLuongCN);
					sorter.setRowFilter(rowFilter);

					// Đặt TableRowSorter vào bảng để áp dụng bộ lọc
					tblDSLuongCN.setRowSorter(sorter);
				}

				txtTimKiemTheoTenCN.selectAll();
				txtTimKiemTheoTenCN.requestFocusInWindow();
			}

		});
		btnXemChiTietLuongCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tblThangLuongCN.getSelectedRow();
				int[] rows = tblDSLuongCN.getSelectedRows();

				if (rows.length <= 0 || row < 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn 1 công nhân để xem chi tiết lương");
				} else if (rows.length > 1) {
					JOptionPane.showMessageDialog(null, "Xin vui lòng chỉ chọn 1 công nhân để xem chi tiết lương!");
				} else {
					int thang = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 0).toString());
					int nam = Integer.parseInt(modelTableThangLuongCN.getValueAt(row, 1).toString());
					String xu = modelTableThangLuongCN.getValueAt(row, 2).toString();
					bl_DAO = new BangLuongCN_DAO();

					// Kiểm tra xem có dòng được chọn trong tblDSLuongNV không
					if (rows[0] < modelTableDSLuongCN.getRowCount()) {
						String maCN = modelTableDSLuongCN.getValueAt(rows[0], 0).toString();
						double khauTru =Double.parseDouble(modelTableDSLuongCN.getValueAt(rows[0], 6).toString().replace(",", "")); 
						BangLuongCN bl= bl_DAO.getMotBangLuongCNTheoThangNam(maCN, thang, nam);
						if(bl==null) {
							JOptionPane.showMessageDialog(null, "Công Nhân Chưa Có Bảng Lương Không Thể Xem Chi Tiết Được!");
						}else
							new ChiTietCN_GUI(maCN,thang,nam,xu,khauTru);
					} else {
						JOptionPane.showMessageDialog(null, "Không thể truy cập dữ liệu công nhân.");
					}
				}
			}
		});
		

		btnTinhLuongCN.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DecimalFormat decimalFormat = new DecimalFormat("#,###");
				decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				int rowdk = tblThangLuongCN.getSelectedRow();
				int[] rows = tblDSLuongCN.getSelectedRows();
				if (rows.length <= 0 || rowdk < 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn Công Nhân cần tính lương");
				}else{
					if (rows.length > 0){
						if (tblDSLuongCN.getRowSorter() != null){
							TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) tblDSLuongCN.getRowSorter();
							for (int i = 0; i < rows.length; i++){
								int row = rows[i];
								if (row < tblDSLuongCN.getRowCount()){
									int modelRow = tblDSLuongCN.convertRowIndexToModel(row);
									double khauTrudk = 0;

					                try {
					                    khauTrudk = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 6).toString().replace(",", ""));
					                } catch (NumberFormatException ex) {
					                    JOptionPane.showMessageDialog(null, "Khấu trừ phải là một số.");
					                    return; // Dừng việc tính lương nếu có lỗi
					                }

					                if (khauTrudk < 0) {
					                    JOptionPane.showMessageDialog(null, "Khấu trừ không thể là số âm.");
					                    return; // Dừng việc tính lương nếu có lỗi
					                }
									boolean daTinhLuong = (boolean) tblDSLuongCN.getModel().getValueAt(modelRow, 9);
									boolean coLuongRoi = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 8).toString().replace(",", ""))>0?true:false;
									if(daTinhLuong==true&&coLuongRoi==true){
										int luaChon = JOptionPane.showConfirmDialog(null, "Công Nhân "+modelTableDSLuongCN.getValueAt(row, 1).toString()+" có mã số "+modelTableDSLuongCN.getValueAt(row, 0).toString()+" đã có lương. Bạn có muốn tính lại lương cho công nhân này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
										if(luaChon==JOptionPane.YES_OPTION){
											modelTableDSLuongCN.setValueAt(false, modelRow, 9);
										}
									}
									if (daTinhLuong==false) {
										String maCN = modelTableDSLuongCN.getValueAt(modelRow, 0).toString();
										String hoTen = modelTableDSLuongCN.getValueAt(modelRow, 1).toString();
										double luongCoBan = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 2).toString().replace(",", ""));
										double luongSanPham = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 3).toString().replace(",", ""));
										double soNgayLam = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 4).toString().replace(",", ""));
										double phuCap = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 5).toString().replace(",", ""));
										double khauTru = Double.parseDouble(modelTableDSLuongCN.getValueAt(modelRow, 6).toString().replace(",", ""));
									
										int rowas = tblThangLuongCN.getSelectedRow();
										int thang = Integer.parseInt(modelTableThangLuongCN.getValueAt(rowas, 0).toString());
										int nam = Integer.parseInt(modelTableThangLuongCN.getValueAt(rowas, 1).toString());
										if(kiemSoBangChamCongCua1CN(maCN, thang, nam)){
											double Luong = (luongCoBan/30)*soNgayLam + luongSanPham + phuCap - khauTru;
											double bhxh = (Luong)*0.105;
											double tongLuong = Luong - bhxh;
											modelTableDSLuongCN.setValueAt(decimalFormat.format(tongLuong), modelRow, 8);
											modelTableDSLuongCN.setValueAt(decimalFormat.format(bhxh), modelRow, 7);
											modelTableDSLuongCN.setValueAt(true, modelRow, 9);
											LocalDate ngayhientai = LocalDate.now();
											modelTableDSLuongCN.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), modelRow, 10);
											//Cập Nhật ghi chú tất cả các bảng chấm công của công nhân đó
											bc_DAO = new BangChamCongCN_DAO();
											ArrayList<BangChamCongCN> list = bc_DAO.getBangCCCNTheomaCNThangNam(maCN, thang, nam);
											for(BangChamCongCN bc : list){
												bc.setGhiChu("Đã tính lương ở ngày:"+dtf.format(ngayhientai));
												bc_DAO.updateGhiChuBCCCN(bc);
											} 

										}else{
											int chon = JOptionPane.showConfirmDialog(null, "Công Nhân "+modelTableDSLuongCN.getValueAt(row, 1).toString()+" có mã số "+modelTableDSLuongCN.getValueAt(row, 0).toString()+" chưa đủ số bảng chấm công. Bạn có muốn tính lương cho công nhân này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
											if (chon==JOptionPane.YES_OPTION){
												double Luong = (luongCoBan/30)*soNgayLam + luongSanPham + phuCap - khauTru;
												double bhxh = (Luong)*0.105;
												double tongLuong = Luong - bhxh;
												modelTableDSLuongCN.setValueAt(decimalFormat.format(tongLuong), modelRow, 8);
												modelTableDSLuongCN.setValueAt(decimalFormat.format(bhxh), modelRow, 7);
												modelTableDSLuongCN.setValueAt(true, modelRow, 9);
												LocalDate ngayhientai = LocalDate.now();
												modelTableDSLuongCN.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), modelRow, 10);
											//Cập Nhật ghi chú tất cả các bảng chấm công của công nhân đó
											bc_DAO = new BangChamCongCN_DAO();
											ArrayList<BangChamCongCN> list = bc_DAO.getBangCCCNTheomaCNThangNam(maCN, thang, nam);
											for(BangChamCongCN bc : list){
												bc.setGhiChu("Đã tính lương ở ngày:"+dtf.format(ngayhientai));
												bc_DAO.updateGhiChuBCCCN(bc);
											} 
											}
												
											

										}

										
									}
								}
							}

						}else{
							for (int i=0;i<rows.length;i++){
								int row = rows[i];
								double khauTrudk = 0;

				                try {
				                    khauTrudk = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 6).toString().replace(",", ""));
				                } catch (NumberFormatException ex) {
				                    JOptionPane.showMessageDialog(null, "Khấu trừ phải là một số.");
				                    return; // Dừng việc tính lương nếu có lỗi
				                }

				                if (khauTrudk < 0) {
				                    JOptionPane.showMessageDialog(null, "Khấu trừ không thể là số âm.");
				                    return; // Dừng việc tính lương nếu có lỗi
				                }
								boolean daTinhLuong = (boolean) modelTableDSLuongCN.getValueAt(row, 9);
								boolean coLuongRoi = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 8).toString().replace(",", ""))>0?true:false;
								if (daTinhLuong==true&&coLuongRoi==true){
									int luaChon = JOptionPane.showConfirmDialog(null, "Công Nhân "+modelTableDSLuongCN.getValueAt(row, 1).toString()+" có mã số "+modelTableDSLuongCN.getValueAt(row, 0).toString()+" đã có lương. Bạn có muốn tính lại lương cho công nhân này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
									if(luaChon==JOptionPane.YES_OPTION){
										modelTableDSLuongCN.setValueAt(false, row, 9);
									}
								}
								if (daTinhLuong==false) {
									String maCN = modelTableDSLuongCN.getValueAt(row, 0).toString();
									String hoTen = modelTableDSLuongCN.getValueAt(row, 1).toString();
									double luongCoBan = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 2).toString().replace(",", ""));
									double luongSanPham = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 3).toString().replace(",", ""));
									double soNgayLam = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 4).toString().replace(",", ""));
									double phuCap = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 5).toString().replace(",", ""));
									double khauTru = Double.parseDouble(modelTableDSLuongCN.getValueAt(row, 6).toString().replace(",", ""));
									int rowas = tblThangLuongCN.getSelectedRow();
									int thang = Integer.parseInt(modelTableThangLuongCN.getValueAt(rowas, 0).toString());
									int nam = Integer.parseInt(modelTableThangLuongCN.getValueAt(rowas, 1).toString());
									if(kiemSoBangChamCongCua1CN(maCN, thang, nam)){
										double Luong = (luongCoBan/30)*soNgayLam + luongSanPham + phuCap - khauTru;
										double bhxh = (Luong)*0.105;
										double tongLuong = Luong - bhxh;
										modelTableDSLuongCN.setValueAt(decimalFormat.format(tongLuong), row, 8);
										modelTableDSLuongCN.setValueAt(decimalFormat.format(bhxh), row, 7);
										modelTableDSLuongCN.setValueAt(true, row, 9);
										LocalDate ngayhientai = LocalDate.now();
										modelTableDSLuongCN.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), row, 10);
										//Cập Nhật ghi chú tất cả các bảng chấm công của công nhân đó
											bc_DAO = new BangChamCongCN_DAO();
											ArrayList<BangChamCongCN> list = bc_DAO.getBangCCCNTheomaCNThangNam(maCN, thang, nam);
											for(BangChamCongCN bc : list){
												bc.setGhiChu("Đã tính lương ở ngày:"+dtf.format(ngayhientai));
												bc_DAO.updateGhiChuBCCCN(bc);
											} 
									}else{
										int chon = JOptionPane.showConfirmDialog(null, "Công Nhân "+modelTableDSLuongCN.getValueAt(row, 1).toString()+" có mã số "+modelTableDSLuongCN.getValueAt(row, 0).toString()+" chưa đủ số bảng chấm công. Bạn có muốn tính lương cho công nhân này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
										if (chon==JOptionPane.YES_OPTION){
											double Luong = (luongCoBan/30)*soNgayLam + luongSanPham + phuCap - khauTru;
											double bhxh = (Luong)*0.105;
											double tongLuong = Luong - bhxh;
											modelTableDSLuongCN.setValueAt(decimalFormat.format(tongLuong), row, 8);
											modelTableDSLuongCN.setValueAt(decimalFormat.format(bhxh), row, 7);
											modelTableDSLuongCN.setValueAt(true, row, 9);
											LocalDate ngayhientai = LocalDate.now();
											modelTableDSLuongCN.setValueAt("Đã tính lương ở ngày:"+dtf.format(ngayhientai), row, 10);
											//Cập Nhật ghi chú tất cả các bảng chấm công của công nhân đó
											bc_DAO = new BangChamCongCN_DAO();
											ArrayList<BangChamCongCN> list = bc_DAO.getBangCCCNTheomaCNThangNam(maCN, thang, nam);
											for(BangChamCongCN bc : list){
												bc.setGhiChu("Đã tính lương ở ngày:"+dtf.format(ngayhientai));
												bc_DAO.updateGhiChuBCCCN(bc);
											} 
										}
									}


							}
						}

					}

				}
			}}
		});
		
		return pnlLuongCN;
	}
	private void layDSThangLuongCNtuDB() {
		bc_DAO = new BangChamCongCN_DAO();
		x_DAO = new Xuong_DAO();
		ArrayList<LocalDate> list = bc_DAO.layTatCaThangvaNamkhacNhauCN();
		for(LocalDate date : list) {
			ArrayList<Xuong> listXuong = x_DAO.layTatCaXuongKhacNhau();
			
			for(Xuong xuong : listXuong) {
				
	
						Object[] row = {date.getMonthValue(), date.getYear(), xuong.getTenXuong()};
						modelTableThangLuongCN.addRow(row);
					
				
			}
			
		}
		

	}
		private void taoDSBangLuongtuDBtheoDK(int thang, int nam,String Xuong) {
		
		bc_DAO = new BangChamCongCN_DAO();
		cn_DAO = new CongNhan_DAO();
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		modelTableDSLuongCN.setRowCount(0);
		
		for(CongNhan cn : cn_DAO.layDanhSachCNTheoDK(Xuong)){
			bl_DAO = new BangLuongCN_DAO();
			BangLuongCN bLuongCN = bl_DAO.getMotBangLuongCNTheoThangNam(cn.getMaCN(), thang, nam);
			if(bLuongCN == null) {
				double lsp=0;
			ArrayList <BangChamCongCN> list = bc_DAO.getBangCCCNTheomaCNThangNam(cn.getMaCN(), thang, nam);
			for (BangChamCongCN bc : list) {
				lsp += bc_DAO.layGiaTienSanPhamTheoBCC(bc)*bc.getSanLuong();
			}
			
			Object[] row = {cn.getMaCN(), cn.getHo()+" "+ cn.getTen(), decimalFormat.format(cn.getLuongCoBan()), decimalFormat.format(lsp), bc_DAO.laySoNgayDiLamCuaCNTheoThangNam(cn.getMaCN(), thang, nam),decimalFormat.format(cn.getPhuCap()), 0, 0, 0, false, ""};
				modelTableDSLuongCN.addRow(row);
			}
			else {
				double lsp=0;
			ArrayList <BangChamCongCN> list = bc_DAO.getBangCCCNTheomaCNThangNam(cn.getMaCN(), thang, nam);
			for (BangChamCongCN bc : list) {
				lsp += bc_DAO.layGiaTienSanPhamTheoBCC(bc)*bc.getSanLuong();
			}
				Object[] row = {bLuongCN.getCn().getMaCN(),bLuongCN.getCn().getHo()+" "+bLuongCN.getCn().getTen(), decimalFormat.format(cn.getLuongCoBan()), decimalFormat.format(lsp), bc_DAO.laySoNgayDiLamCuaCNTheoThangNam(cn.getMaCN(), thang, nam),decimalFormat.format(cn.getPhuCap()), decimalFormat.format(bLuongCN.getTienPhat()),decimalFormat.format(bLuongCN.getBhxh()), decimalFormat.format(bLuongCN.getLuongTong()), true, "Đã có bảng lương"};				
				
				modelTableDSLuongCN.addRow(row);
		}
	}
		setTxtSoCNChuaTinhLuong();
		setTxtTongSoCN();
		setTxtTongLuongCanTra();
}
		private boolean kiemSoBangChamCongCua1CN(String maCN,int thang, int nam){
		bc_DAO = new BangChamCongCN_DAO();
		double soBangChamCong = bc_DAO.getBangCCCNTheomaCNThangNam(maCN, thang, nam).size();
		if(soBangChamCong <25 ){
			return false;
		}
		return true;
	}
	private void chonTatCaFalse() {
		int rowCount = tblDSLuongCN.getRowCount();

		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {
			// Lấy giá trị tại cột 10 của dòng hiện tại
			boolean giaTriCot9 = (boolean) modelTableDSLuongCN.getValueAt(i, 9);

			// Kiểm tra nếu giá trị cột 10 là false
			if (!giaTriCot9) {
				// Chọn dòng hiện tại
				tblDSLuongCN.addRowSelectionInterval(i, i);
			}
		}
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
	private int soCongNhanChuaTinhLuong(){
		int soCNChuaTinhLuong = 0;
		for(int i=0;i<tblDSLuongCN.getRowCount();i++){
			boolean daTinhLuong = (boolean) modelTableDSLuongCN.getValueAt(i, 9);
			if(daTinhLuong==false){
				soCNChuaTinhLuong++;
			}
		}
		return soCNChuaTinhLuong;
	}
	private int tongSoCongNhan(){
		int tongSoCN = 0;
		for(int i=0;i<tblDSLuongCN.getRowCount();i++){
			tongSoCN++;
		}
		return tongSoCN;
	}
	private double tongLuongCanTra(){
		double tongLuongCanTra = 0;
		for(int i=0;i<tblDSLuongCN.getRowCount();i++){
			boolean daTinhLuong = (boolean) modelTableDSLuongCN.getValueAt(i, 9);
			if(daTinhLuong==true){
				tongLuongCanTra += Double.parseDouble(modelTableDSLuongCN.getValueAt(i, 8).toString().replace(",", ""));
			}
		}
		return tongLuongCanTra;
	}
	private void setTxtSoCNChuaTinhLuong(){
		txtSoCNChuaTinhLuong.setText(soCongNhanChuaTinhLuong()+"");
	}
	private void setTxtTongSoCN(){
		txtTongSoCN.setText(tongSoCongNhan()+"");
	}
	private void setTxtTongLuongCanTra(){
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		txtTongLuongCanTraCN.setText(decimalFormat.format(tongLuongCanTra())+" VNĐ");
	}
	private void chonTatCaTrue() {
		int rowCount = tblDSLuongCN.getRowCount();

		// Duyệt qua tất cả các dòng trong bảng
		for (int i = 0; i < rowCount; i++) {
			// Lấy giá trị tại cột 10 của dòng hiện tại
			int rowIndex = tblDSLuongCN.convertRowIndexToModel(i);
			boolean giaTriCot9 = (boolean) modelTableDSLuongCN.getValueAt(i, 9);

			// Kiểm tra nếu giá trị cột 10 là false
			if (giaTriCot9) {
				// Chọn dòng hiện tại
				tblDSLuongCN.addRowSelectionInterval(i, i);
			}
		}
	}
	private boolean kiemtraCoFalseKhong(){
		int rowCount = tblDSLuongCN.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			// Lấy giá trị tại cột 10 của dòng hiện tại
			int rowIndex = tblDSLuongCN.convertRowIndexToModel(i);
			boolean giaTriCot9 = (boolean) modelTableDSLuongCN.getValueAt(i, 9);

			// Kiểm tra nếu giá trị cột 10 là false
			if (!giaTriCot9) {
				return true;
			}
		}
		return false;
	}
	private void exportDsToExcel(int thang, int nam , String Xuong, String tongLuong) {

		
		if(kiemtraCoFalseKhong()){
			JOptionPane.showMessageDialog(this, "Vui Lòng Tính Lương Trước Khi In!");
		} else {
			DecimalFormat decimalFormat = new DecimalFormat("#,###");
			decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				XSSFWorkbook workBook = new XSSFWorkbook();
				XSSFSheet sheet = workBook.createSheet("Danh sách");

				int columnCount = tblDSLuongCN.getColumnCount() - 2; // Exclude the last two columns

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
				subHeaderRow.createCell(4, CellType.STRING).setCellValue("" + Xuong);

				sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 10));
				subHeaderRow.createCell(7, CellType.STRING).setCellValue("Ngày In: " + dtf.format(LocalDate.now()));

				// Create the header row
				XSSFRow headerRow = sheet.createRow(3);
				for (int i = 0; i < columnCount; i++) {
					headerRow.createCell(i, CellType.STRING).setCellValue(tblDSLuongCN.getColumnName(i));
				}

				// Populate the data rows
				for (int i = 0; i < tblDSLuongCN.getRowCount(); i++) {
					XSSFRow row = sheet.createRow(i + 4); // Start from row 5
					for (int j = 0; j < columnCount; j++) {
						Object value = tblDSLuongCN.getValueAt(i, j);
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
	public void mouseClicked(java.awt.event.MouseEvent e) {
		Object o = e.getSource();
		if(o.equals(tblThangLuongCN)) {
			int row = tblThangLuongCN.getSelectedRow();
			int thang = Integer.parseInt(tblThangLuongCN.getValueAt(row, 0).toString());
			int nam = Integer.parseInt(tblThangLuongCN.getValueAt(row, 1).toString());
			String xuong = tblThangLuongCN.getValueAt(row, 2).toString();
			cboThangLuongCN.setSelectedItem(modelTableThangLuongCN.getValueAt(row, 0).toString());
			cboNamLuongCN.setSelectedItem(modelTableThangLuongCN.getValueAt(row, 1).toString());
			cboXuongCN.setSelectedItem(modelTableThangLuongCN.getValueAt(row, 2).toString());
			taoDSBangLuongtuDBtheoDK(thang, nam, xuong);
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
	}
	
}
