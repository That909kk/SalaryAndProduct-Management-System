package gUI;
/**
 * Lớp này dùng để tạo giao diện phân chia công đoạn
 * Tạo bởi: Huỳnh Kim Thành - 21086351
 * ngày: 2/11/2023
 * Ngày cập nhật mới nhất: 3/11/2023
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.CongDoan_DAO;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.CongDoan;
import entity.HopDong;
import entity.SanPham;

public class PhanChiaCongDoan_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JPanel Menu;
	private JTable tableSanPham;
	private DefaultTableModel modelSanPham;
	private JTextField txtMaCD;
	private JLabel lblTenCD;
	private JTextField txtTenCD;
	private JTextField txtGiaTien;
	private JTextField txtSoCNDuKien;
	private JTextField txtNDTim;
	
	private JTable tableCongDoan;
	private DefaultTableModel modelCongDoan;
	
	private SanPham_DAO sp_DAO;
	private HopDong_DAO hd_DAO;
	private CongDoan_DAO cd_DAO;
	private DefaultComboBoxModel<String> modelCBOTienQuyet;
	private JComboBox<String> cboMaCDTienQuyet;
	private JButton btnXem;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JTextField txtSoLuongSP;
	private JDateChooser dcNgayBatDau;
	private JDateChooser dcNgayKetThuc;
	private JButton btnHoanTat;
	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		PhanChiaCongDoan_GUI pccd_GUI = new PhanChiaCongDoan_GUI();
		pccd_GUI.setVisible(true);
	}
	
	public PhanChiaCongDoan_GUI() {
		super("Phân chia công đoạn");
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sp_DAO = new SanPham_DAO();
		hd_DAO = new HopDong_DAO();
		cd_DAO = new CongDoan_DAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(this.getPCCDUI());
		
	}
	
	public JPanel getPCCDUI() {
		JPanel pnlPCCD = new JPanel();
		pnlPCCD.setBackground(new Color(240, 248, 255));
		pnlPCCD.setBounds(0, 50, 1264, 632);
		pnlPCCD.setLayout(null);
		
		
		
		String[] header = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Số lượng công đoạn hiện có", "Ngày kết thúc hợp đồng"};
		modelSanPham = new DefaultTableModel(header, 0);
		tableSanPham = new JTable(modelSanPham);
		
		tableSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableSanPham.setRowHeight(26);
		
		tableSanPham.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(140);
		tableSanPham.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableSanPham.getColumnModel().getColumn(3).setPreferredWidth(180);
		tableSanPham.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		layDSSanPhamTuDB();
		
		JScrollPane scrollPane = new JScrollPane(tableSanPham);
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
		lblMaCD.setBounds(15, 20, 110, 26);
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
		lblNgayBatDau.setBounds(15, 137, 110, 26);
		pnlThongTinCongDoan.add(lblNgayBatDau);
		
		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc:");
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayKetThuc.setBounds(300, 137, 110, 26);
		pnlThongTinCongDoan.add(lblNgayKetThuc);
		
		JLabel lblTienQuyet = new JLabel("Công đoạn tiên quyết:");
		lblTienQuyet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTienQuyet.setBounds(300, 20, 160, 26);
		pnlThongTinCongDoan.add(lblTienQuyet);
		
		modelCBOTienQuyet = new DefaultComboBoxModel<String>();
		cboMaCDTienQuyet = new JComboBox<String>(modelCBOTienQuyet);
		cboMaCDTienQuyet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboMaCDTienQuyet.setBounds(455, 20, 149, 26);
		cboMaCDTienQuyet.addItem(" ");
		
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
		
		txtSoCNDuKien = new JTextField();
		txtSoCNDuKien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoCNDuKien.setColumns(10);
		txtSoCNDuKien.setBounds(455, 100, 149, 26);
		pnlThongTinCongDoan.add(txtSoCNDuKien);
		
		btnXem = new JButton("Xem chi tiết");
		btnXem.setEnabled(false);
		btnXem.setBackground(new Color(255, 255, 255));
		btnXem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXem.setBounds(10, 177, 150, 50);
		btnXem.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		pnlThongTinCongDoan.add(btnXem);
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThem.setBounds(166, 177, 140, 50);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-20.png"));
		pnlThongTinCongDoan.add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setEnabled(false);
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSua.setBounds(314, 177, 140, 50);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		pnlThongTinCongDoan.add(btnSua);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnXoa.setBounds(464, 177, 140, 50);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		btnXoa.setIconTextGap(6);
		btnXoa.setEnabled(false);
		pnlThongTinCongDoan.add(btnXoa);
		
		dcNgayBatDau = new JDateChooser();
		dcNgayBatDau.getCalendarButton().setBackground(new Color(255, 255, 255));
		dcNgayBatDau.setDateFormatString("dd/MM/yyyy");
		dcNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcNgayBatDau.setBackground(new Color(255, 255, 255));
		dcNgayBatDau.setBounds(125, 137, 164, 26);
		dcNgayBatDau.setDate(new Date());
		pnlThongTinCongDoan.add(dcNgayBatDau);
		
		dcNgayKetThuc = new JDateChooser();
		dcNgayKetThuc.getCalendarButton().setBackground(new Color(255, 255, 255));
		dcNgayKetThuc.setDateFormatString("dd/MM/yyyy");
		dcNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcNgayKetThuc.setBackground(new Color(255, 255, 255));
		dcNgayKetThuc.setBounds(455, 137, 149, 26);
		
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DAY_OF_MONTH, 1);
		
		dcNgayKetThuc.setDate(currentDate.getTime());
		pnlThongTinCongDoan.add(dcNgayKetThuc);
		
		JLabel lblSoLuongCD = new JLabel("Số lượng:");
		lblSoLuongCD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoLuongCD.setBounds(15, 100, 100, 26);
		pnlThongTinCongDoan.add(lblSoLuongCD);
		
		txtSoLuongSP = new JTextField();
		txtSoLuongSP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuongSP.setColumns(10);
		txtSoLuongSP.setBounds(125, 100, 164, 26);
		pnlThongTinCongDoan.add(txtSoLuongSP);
		
		JPanel pnlCacCongDoan = new JPanel();
		pnlCacCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCacCongDoan.setBackground(new Color(240, 248, 255));
		pnlCacCongDoan.setBorder(new TitledBorder(null, "C\u00E1c c\u00F4ng \u0111o\u1EA1n hi\u1EC7n c\u00F3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCacCongDoan.setBounds(0, 240, 1264, 340);
		pnlPCCD.add(pnlCacCongDoan);
		pnlCacCongDoan.setLayout(null);
		
		String[] header_CongDoan = {"Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Số lượng sản phẩm", "Số lượng công nhân dự kiến"
				, "Giá tiền (VND)", "Trạng thái", "Ngày bắt đầu", "Ngày kết thúc", "Công đoạn tiên quyết"};
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
		lblTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTim.setBounds(10, 590, 220, 30);
		pnlPCCD.add(lblTim);
		
		txtNDTim = new JTextField();
		txtNDTim.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNDTim.setBounds(230, 590, 200, 30);
		pnlPCCD.add(txtNDTim);
		txtNDTim.setColumns(30);
		
		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBounds(432, 590, 30, 30);
		btnTim.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		pnlPCCD.add(btnTim);
		
		
		btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnHoanTat.setBounds(1114, 585, 140, 40);
		btnHoanTat.setIcon(new ImageIcon("img\\icons\\icons8-checked-checkbox-24.png"));
		btnHoanTat.setIconTextGap(6);
		btnHoanTat.setEnabled(false);
		pnlPCCD.add(btnHoanTat);
		
		btnXem.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnHoanTat.addActionListener(this);
		btnTim.addActionListener(this);
		
		tableSanPham.addMouseListener(this);
		tableCongDoan.addMouseListener(this);
		
		return pnlPCCD;
	}
	
	private void layDSSanPhamTuDB() {
		sp_DAO = new SanPham_DAO();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		modelSanPham.setRowCount(0);
		for (SanPham sp : sp_DAO.getDSSanPhamTheoTrangThai(true)) {
			HopDong hd = hd_DAO.getMotHopDong(sp.getHopDong().getMaHopDong());
			modelSanPham.addRow(new Object[] {sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getSoLuongCongDoan(),
					hd.getNgayThanhLyHopDong().format(dtf)});
		}
	}
	
	private void layDSCongDoanTuDBTheoMaSP(String maSP) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		modelCongDoan.setRowCount(0);
		for (CongDoan cd : cd_DAO.getDSCongDoanTheoMaSP(maSP)) {
			SanPham sp = sp_DAO.getMotSanPham(maSP);
			modelCongDoan.addRow(new Object[] {sp.getTenSP(), cd.getMaCongDoan(), cd.getTenCongDoan(),
					cd.getSoLuongSanPham(), cd.getSoLuongCongNhanDuKien(), cd.getGiaTien(), 
					cd.isTrangThai() ? "Đã xong" : "Chưa xong", cd.getNgayBatDau().format(dtf), 
					cd.getNgayKetThucDuKien().format(dtf), cd.getCongDoanTienQuyet()});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object object = e.getSource();
		
		if (object.equals(tableSanPham)) {
			int row = tableSanPham.getSelectedRow();
			String maSP = modelSanPham.getValueAt(row, 0).toString();
			
			if (sp_DAO.getMotSanPham(maSP).getSoLuongCongDoan() > 0) {
				capNhatCBOTienQuyet(maSP);
			}
			layDSCongDoanTuDBTheoMaSP(maSP);
			txtTenCD.setEnabled(true);
			txtGiaTien.setEnabled(true);
			txtSoCNDuKien.setEnabled(true);
			txtSoLuongSP.setEnabled(true);
			txtSoLuongSP.setText(sp_DAO.getMotSanPham(maSP).getSoLuong() + "");
			dcNgayBatDau.setEnabled(true);
			dcNgayKetThuc.setEnabled(true);
			btnSua.setEnabled(false);
			btnXoa.setEnabled(true);
		}
		
		if (object.equals(tableCongDoan)) {
			txtTenCD.setEnabled(false);
			txtGiaTien.setEnabled(false);
			txtSoCNDuKien.setEnabled(false);
			txtSoLuongSP.setEnabled(false);
			dcNgayBatDau.setEnabled(false);
			dcNgayKetThuc.setEnabled(false);
			btnSua.setEnabled(true);
		}
	}

	private void capNhatCBOTienQuyet(String maSP) {
		modelCBOTienQuyet.removeAllElements();
		modelCBOTienQuyet.addElement(" ");
		for (CongDoan cd : cd_DAO.getDSCongDoanTheoMaSP(maSP)) {
			modelCBOTienQuyet.addElement(cd.getMaCongDoan());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnThem)) {
			int row = tableSanPham.getSelectedRow();
			String tenCD = txtTenCD.getText().trim();
			String giaTien_String = txtGiaTien.getText().trim();
			String soLuongSP_String = txtSoLuongSP.getText().trim();
			String soLuongCN_String = txtSoCNDuKien.getText().trim();
			
			if (validation()) {
				String maSP = modelSanPham.getValueAt(row, 0).toString();
				int soLuongSP = Integer.parseInt(soLuongSP_String);
				int soLuongCN = Integer.parseInt(soLuongCN_String);
				double giaTien = Double.parseDouble(giaTien_String);
				LocalDate ngayBatDau = dcNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate ngayKetThuc = dcNgayKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String maCD = taoMaCongDoan(maSP);
				String tienQuyet = cboMaCDTienQuyet.getSelectedItem().toString();
				SanPham sp = sp_DAO.getMotSanPham(maSP);
				
				CongDoan cd = null;
				if (tienQuyet.equals(" ")) {
					cd = new CongDoan(maCD, tenCD, soLuongSP, soLuongCN, giaTien, ngayBatDau, ngayKetThuc, false, "", sp);
				} else {
					cd = new CongDoan(maCD, tenCD, soLuongSP, soLuongCN, giaTien, ngayBatDau, ngayKetThuc, false, tienQuyet, sp);
				}
				
				if (cd_DAO.insertCongDoan(cd)) {
					capNhatSoCongDoanChoSP(maSP);
					layDSCongDoanTuDBTheoMaSP(maSP);
					capNhatCBOTienQuyet(maSP);
				}
			}
		}
		
		if (o.equals(btnSua)) {
			btnSua.setEnabled(false);
			btnHoanTat.setEnabled(true);
		}
		
		if (o.equals(btnXoa)) {
			String maCD = modelCongDoan.getValueAt(tableCongDoan.getSelectedRow(), 1).toString();
			int luaChon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá công đoạn này", "Lưu ý", JOptionPane.YES_NO_OPTION);
			
			if (luaChon == JOptionPane.YES_OPTION) {
				if (cd_DAO.deleteCongDoan(maCD)) {
					JOptionPane.showMessageDialog(null, "Xoá thành công");
					layDSCongDoanTuDBTheoMaSP(modelSanPham.getValueAt(tableSanPham.getSelectedRow(), 0).toString());
					capNhatSoCongDoanChoSP(modelSanPham.getValueAt(tableSanPham.getSelectedRow(), 0).toString());
				}
			}
		}
		
		if (o.equals(btnHoanTat)) {
			btnSua.setEnabled(true);
			btnHoanTat.setEnabled(false);
		}
	}
	
	private void capNhatSoCongDoanChoSP(String maSP) {
		SanPham sp = sp_DAO.getMotSanPham(maSP);
		sp.setSoLuongCongDoan(cd_DAO.getDSCongDoanTheoMaSP(maSP).size());
		if (sp_DAO.updateSanPham(sp)) {
			layDSSanPhamTuDB();
		}
	}

	private String taoMaCongDoan(String maSP) {
		return maSP + (cd_DAO.getDSCongDoanTheoMaSP(maSP).size() + 1);
	}

	private boolean validation() {
		String maSP = modelSanPham.getValueAt(tableSanPham.getSelectedRow(), 0).toString();
		SanPham sp = sp_DAO.getMotSanPham(maSP);
		HopDong hd = hd_DAO.getMotHopDong(sp.getHopDong().getMaHopDong());
		String tenCD = txtTenCD.getText().trim();
		String giaTien_String = txtGiaTien.getText().trim();
		String soLuongSP_String = txtSoLuongSP.getText().trim();
		String soLuongCN_String = txtSoCNDuKien.getText().trim();
		
		if (tenCD.length() <= 0) {
			thongBaoLoi(txtTenCD, "Hãy nhập tên công đoạn!!!");
			return false;
		}
		
		try {
			int soLuongSP = Integer.parseInt(soLuongSP_String);
			if (soLuongSP <= 0 && soLuongSP > sp.getSoLuong()) {
				thongBaoLoi(txtSoLuongSP, "Số lượng sản phẩm của công đoạn phải là Số > 0 và không được quá số lượng sản phẩm ban đầu");
				return false;
			}
		} catch (NumberFormatException e) {
			thongBaoLoi(txtSoLuongSP, "Số lượng sản phẩm phải là Số");
			return false;
		}
		
		try {
			int soLuongCN = Integer.parseInt(soLuongCN_String);
			if (soLuongCN <= 0) {
				thongBaoLoi(txtSoCNDuKien, "Số lượng công nhân dự kiến phải là Số > 0");
				return false;
			}
		} catch (NumberFormatException e) {
			thongBaoLoi(txtSoCNDuKien, "Số lượng công nhân dự kiến phải là Số");
			return false;
		}
		
		try {
			double giaTien = Double.parseDouble(giaTien_String);
			if (giaTien < 0) {
				thongBaoLoi(txtGiaTien, "Hãy nhập SỐ không âm cho giá tiền!!!");
				return false;
			}
		} catch (NumberFormatException e) {
			thongBaoLoi(txtGiaTien, "Hãy nhập SỐ không âm cho giá tiền!!!");
			return false;
		}
		
		Date ngayBatDau = dcNgayBatDau.getDate();
		Date ngayKetThuc = dcNgayKetThuc.getDate();
		Date ngayThanhLiHD = Date.from(hd.getNgayThanhLyHopDong().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		
		if (ngayKetThuc.after(ngayThanhLiHD)) {
			JOptionPane.showMessageDialog(null, "Ngày kết thúc công đoạn phải từ ngày thanh lí hơp đồng trở về trước");
			dcNgayKetThuc.requestFocus();
			return false;
		}
		if (ngayBatDau.after(ngayKetThuc)) {
			JOptionPane.showMessageDialog(null, "Ngày bắt đầu công đoạn phải trước ngày kết thúc");
			dcNgayBatDau.requestFocus();
			return false;
		}
		
		return true;
	}
	
	private void thongBaoLoi(JTextField txt, String mess) {
		JOptionPane.showMessageDialog(null, mess);
		txt.requestFocus();
		txt.selectAll();
	}
}
