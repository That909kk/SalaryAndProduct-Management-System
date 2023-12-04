package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.CongDoan_DAO;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.CongDoan;
import entity.HopDong;
import entity.SanPham;

public class SanPham_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtTenSanPham;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXacNhan;
	private JTable tblDSSanPham;
	private DefaultTableModel modelDSSanPham;
	private DefaultComboBoxModel<String> modelCBOTrangThai;
	private DefaultTableModel modelTblHopDong;
	private JTable tblHopDong;
	private JComboBox<String> cboTrangThai;
	private JTextField txtSoLuong;
	
	private HopDong_DAO hd_DAO;
	private SanPham_DAO sp_DAO;
	private CongDoan_DAO cd_DAO;
	private DateTimeFormatter dtfVN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JTextField txtTenDoiTac;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SanPham_GUI frame = new SanPham_GUI();
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
	public SanPham_GUI() {
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		contentPane.add(this.createGUI());
		
		setContentPane(contentPane);
	}

	public JPanel createGUI() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		hd_DAO = new HopDong_DAO();
		sp_DAO = new SanPham_DAO();
		cd_DAO = new CongDoan_DAO();
		
		JPanel pnlSP = new JPanel();
		pnlSP.setBackground(new Color(240, 248, 255));
		pnlSP.setBounds(0, 50, 1268, 632);
		pnlSP.setLayout(null);
		
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng Tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTin.setBackground(new Color(240, 248, 255));
		pnlThongTin.setBounds(348, 10, 450, 150);
		pnlSP.add(pnlThongTin);
		pnlThongTin.setLayout(null);
		
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTenSanPham.setBounds(10, 60, 120, 30);
		pnlThongTin.add(lblTenSanPham);
		
		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenSanPham.setBounds(150, 60, 280, 30);
		pnlThongTin.add(txtTenSanPham);
		txtTenSanPham.setColumns(14);
		
		JLabel lblTenDoiTac = new JLabel("Tên đối tác:");
		lblTenDoiTac.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTenDoiTac.setBounds(10, 20, 140, 30);
		pnlThongTin.add(lblTenDoiTac);
		
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSoLuong.setBounds(10, 100, 120, 30);
		pnlThongTin.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtSoLuong.setColumns(14);
		txtSoLuong.setBounds(150, 100, 280, 30);
		pnlThongTin.add(txtSoLuong);
		
		txtTenDoiTac = new JTextField();
		txtTenDoiTac.setBackground(new Color(240, 248, 255));
		txtTenDoiTac.setEditable(false);
		txtTenDoiTac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTenDoiTac.setColumns(14);
		txtTenDoiTac.setBounds(150, 20, 280, 30);
		pnlThongTin.add(txtTenDoiTac);
		
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setLayout(null);
		pnlChucNang.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c N\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlChucNang.setBackground(new Color(240, 248, 255));
		pnlChucNang.setBounds(798, 10, 460, 150);
		pnlSP.add(pnlChucNang);
		
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThem.setBounds(30, 36, 170, 40);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-20.png"));
		pnlChucNang.add(btnThem);
		
		btnXoa = new JButton("Xoá");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBounds(250, 36, 170, 40);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		pnlChucNang.add(btnXoa);
		
		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBounds(250, 86, 170, 40);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		pnlChucNang.add(btnSua);
		
		btnXacNhan = new JButton("Đã xong");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXacNhan.setBackground(Color.WHITE);
		btnXacNhan.setBounds(30, 86, 170, 40);
		btnXacNhan.setIcon(new ImageIcon("img\\icons\\icons8-check-20.png"));
		pnlChucNang.add(btnXacNhan);
		
		String header[] = {"Mã hợp đồng", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Số lượng công đoạn", "Ngày thanh lí", "Trạng Thái"};
		modelDSSanPham = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0 && column != 1;
			}
		};
		tblDSSanPham = new JTable(modelDSSanPham);
		tblDSSanPham.setRowHeight(30);
		tblDSSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrDSSanPham = new JScrollPane(tblDSSanPham);
		scrDSSanPham.setBounds(10, 161, 1248, 460);
		pnlSP.add(scrDSSanPham);
		
		JPanel pnlHopDong = new JPanel();
		pnlHopDong.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "H\u1EE3p \u0110\u1ED3ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHopDong.setBackground(new Color(240, 248, 255));
		pnlHopDong.setBounds(10, 10, 340, 150);
		pnlSP.add(pnlHopDong);
		pnlHopDong.setLayout(null);
		
		String headerHopDong[] = {"Mã hợp đồng", "Ngày ký"};
		modelTblHopDong = new DefaultTableModel(headerHopDong, 0);
		tblHopDong = new JTable(modelTblHopDong);
		tblHopDong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblHopDong.setRowHeight(20);
		
		hienThiDSHopDongTheoTT(false);
		
		JScrollPane scrHopDong = new JScrollPane(tblHopDong);
		scrHopDong.setLocation(10, 50);
		scrHopDong.setSize(320, 90);
		pnlHopDong.add(scrHopDong);
		
		JLabel lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrangThai.setBounds(10, 14, 100, 26);
		pnlHopDong.add(lblTrangThai);
		
		modelCBOTrangThai = new DefaultComboBoxModel<String>();
		cboTrangThai = new JComboBox<String>(modelCBOTrangThai);
		cboTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboTrangThai.setBounds(110, 14, 220, 26);
		modelCBOTrangThai.addElement("Hiển thị tất cả");
		modelCBOTrangThai.addElement("Đã hoàn thành");
		modelCBOTrangThai.addElement("Chưa hoàn thành");
		cboTrangThai.setSelectedIndex(2);
		
		pnlHopDong.add(cboTrangThai);
		
		btnThem.addActionListener(this);
		btnXacNhan.addActionListener(this);
	    btnXoa.addActionListener(this);
	    btnSua.addActionListener(this);
	    tblDSSanPham.addMouseListener(this);
	    cboTrangThai.addActionListener(this);
	    tblHopDong.addMouseListener(this);
		
		return pnlSP;
	}
	/**
	 * Phương thức hiển thị danh sách hợp đồng trên model
	 */
	private void hienThiDSHopDong() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		hd_DAO = new HopDong_DAO();
		ArrayList<HopDong> listHD = hd_DAO.getDSHopDong();
		
		modelTblHopDong.setRowCount(0);
		for (HopDong hopDong : listHD) {
			modelTblHopDong.addRow(new Object[] {hopDong.getMaHopDong(), hopDong.getNgayKy().format(dtf)});
		}
	}
	/**
	 * Phương thức hiển thị danh sách các hợp đồng theo trạng thái trên modelTable
	 * @param trangThai
	 */
	private void hienThiDSHopDongTheoTT(boolean trangThai) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		hd_DAO = new HopDong_DAO();
		ArrayList<HopDong> listHD = hd_DAO.getListHDTheoTrangThai(trangThai);
		
		modelTblHopDong.setRowCount(0);
		for (HopDong hopDong : listHD) {
			modelTblHopDong.addRow(new Object[] {hopDong.getMaHopDong(), hopDong.getNgayKy().format(dtf)});
		}
	}

//	private void layDSSanPhamTuDB() {
//		sp_DAO = new SanPham_DAO();
//		hd_DAO = new HopDong_DAO();
//		ArrayList<SanPham> listSP = sp_DAO.getDSSanPham();
//		modelDSSanPham.setRowCount(0);
//		
//		for (SanPham sanPham : listSP) {
//			HopDong hd = hd_DAO.getMotHopDong(sanPham.getHopDong().getMaHopDong());
//			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
//					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
//		}
//	}
	/**
	 * Hiển thị danh sách sản phẩm theo mã hợp đồng trên modelTable
	 * @param maHD
	 */
	private void layDSSanPhamTheoHopDongTuDB(String maHD) {
		sp_DAO = new SanPham_DAO();
		hd_DAO = new HopDong_DAO();
		ArrayList<SanPham> listSP = sp_DAO.getDSSanPhamTheoHopDong(maHD);
		modelDSSanPham.setRowCount(0);
		
		for (SanPham sanPham : listSP) {
			HopDong hd = hd_DAO.getMotHopDong(maHD);
			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
		}
	}
	
//	private void layDSSanPhamTheoTrangThaiTuDB(boolean trangThai) {
//		sp_DAO = new SanPham_DAO();
//		hd_DAO = new HopDong_DAO();
//		ArrayList<SanPham> listSP = sp_DAO.getDSSanPhamTheoTrangThai(trangThai);
//		modelDSSanPham.setRowCount(0);
//		
//		for (SanPham sanPham : listSP) {
//			HopDong hd = hd_DAO.getMotHopDong(sanPham.getHopDong().getMaHopDong());
//			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
//					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
//		}
//	}
//	
//	private void layDSSanPhamTheoHDvaTTTuDB(String maHD, boolean trangThai) {
//		sp_DAO = new SanPham_DAO();
//		hd_DAO = new HopDong_DAO();
//		ArrayList<SanPham> listSP = sp_DAO.getDSSanPhamTheoHDvaTT(maHD, trangThai);
//		modelDSSanPham.setRowCount(0);
//		
//		for (SanPham sanPham : listSP) {
//			HopDong hd = hd_DAO.getMotHopDong(maHD);
//			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
//					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
//		}
//	}
	/**
	 * Phương thức kiểm tra dữ liệu nhập vào các JTextField
	 * @return true nếu toàn bộ đều hợp lệ, ngược lại trả về false
	 */
	private boolean validation() {
		String tenSanPham = txtTenSanPham.getText().trim();
		String soLuong_String = txtSoLuong.getText().trim();
		
		if (tenSanPham.length() == 0 || !tenSanPham.matches("^[\\p{L}0-9&,-]+(\\s?[\\p{L}0-9&,-]*)*")) {
			thongBaoLoiNhapDuLieu(txtTenSanPham, "Tên đối tác không được để trống");
			return false;
		}
		
		try {
			int soLuong = Integer.parseInt(soLuong_String);
			if (soLuong <= 0) {
				thongBaoLoiNhapDuLieu(txtSoLuong, "Số lượng phải là SỐ lớn hơn 0!!!");
				return false;
			}
		} catch (NumberFormatException e) {
			thongBaoLoiNhapDuLieu(txtSoLuong, "Số lượng phải là SỐ lớn hơn 0!!!");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/**
	 * Hàm thông báo lỗi, khi nhập dữ liệu sai thì trỏ vào txt chứa chuỗi không hợp lệ
	 * @param txt
	 * @param mess
	 */
	private void thongBaoLoiNhapDuLieu(JTextField txt, String mess) {
		JOptionPane.showMessageDialog(null, mess);
		txt.selectAll();
		txt.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(tblDSSanPham)) {
			int row = tblDSSanPham.getSelectedRow();
			
			txtTenSanPham.setText(modelDSSanPham.getValueAt(row, 2).toString());
			txtSoLuong.setText(tblDSSanPham.getValueAt(row, 3).toString());
		}
		
		if (o.equals(tblHopDong)) {
			int rowHopDong = tblHopDong.getSelectedRow();
			txtTenDoiTac.setText(hd_DAO.getMotHopDong(tblHopDong.getValueAt(rowHopDong, 0).toString()).getTenDoiTac());
			String maHD = modelTblHopDong.getValueAt(rowHopDong, 0).toString();
			
			ArrayList<SanPham> listSPTheoHD = sp_DAO.getDSSanPhamTheoHopDong(maHD);

			modelDSSanPham.setRowCount(0);
			
			for (SanPham sp : listSPTheoHD) {
				modelDSSanPham.addRow(new Object[] {sp.getHopDong().getMaHopDong(), sp.getMaSP(), sp.getTenSP(),
						sp.getSoLuong(), sp.getSoLuongCongDoan(), sp.getHopDong().getNgayThanhLyHopDong(),
						sp.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnThem)) {
			hd_DAO = new HopDong_DAO();
			String tenSP = txtTenSanPham.getText().trim();
			String soLuong_String = txtSoLuong.getText().trim();
			
			if (validation()) {
				int soLuong = Integer.parseInt(soLuong_String);
				int rowHopDong = tblHopDong.getSelectedRow();
				
				String maHD = modelTblHopDong.getValueAt(rowHopDong, 0).toString();
				String maSP = taoMaSP(maHD);
				HopDong hd = hd_DAO.getMotHopDong(maHD);
				
				try {
					SanPham sp = new SanPham(maSP, tenSP, soLuong, 0, false, hd);
					sp_DAO.insertSanPham(sp);
					layDSSanPhamTheoHopDongTuDB(maHD);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		if (o.equals(btnXacNhan)) {
			int row = tblDSSanPham.getSelectedRow();
			boolean trangThaiCuaCongDoan = true;
			
			if (row == -1) 
				JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cần xác nhận đã hoàn thành");
			else {
				if (tblDSSanPham.getValueAt(row, 6).toString().equals("Đã hoàn thành")) {
					JOptionPane.showMessageDialog(null, "Sản phẩm này đã hoàn thành");
				} else {
					String maSP = tblDSSanPham.getValueAt(row, 0).toString();
					SanPham sp = sp_DAO.getMotSanPham(maSP);
					
					ArrayList<CongDoan> listCD = cd_DAO.getDSCongDoanTheoMaSP(maSP);
					
					if (listCD.size() == 0) {
						JOptionPane.showMessageDialog(null, "Sản phẩm này chưa có công đoạn");
					} else {
						for (CongDoan cd : listCD) {
							if (!cd.isTrangThai()) {
								trangThaiCuaCongDoan = false;
							}
						}
						
						if (trangThaiCuaCongDoan) {
							if (sp != null) {
								sp.setTrangThai(true);
								sp_DAO.updateSanPham(sp);
								tblDSSanPham.setValueAt("Đã hoàn thành", row, 4);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Sản phẩm này có công đoạn chưa hoàn thành");
						}
					}
				}
			}
		}
		
		if (o.equals(btnSua)) {
			int row = tblDSSanPham.getSelectedRow();
			int luaChon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn sửa sản phẩm này không?", "Lưu Ý", JOptionPane.YES_NO_OPTION);
			
			if (row == -1)
				JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cần sửa!!!");
			else {
				if (luaChon == JOptionPane.YES_OPTION) {
					String maSP = tblDSSanPham.getValueAt(row, 1).toString();
					String tenSP = txtTenSanPham.getText().trim();
					String soLuong_String = txtSoLuong.getText().trim();
					
					if (validation()) {
						SanPham spSua = sp_DAO.getMotSanPham(maSP);
						try {
							spSua.setTenSP(tenSP);
							spSua.setSoLuong(Integer.parseInt(soLuong_String));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (sp_DAO.updateSanPham(spSua)) {
							JOptionPane.showMessageDialog(null, "Sản phẩm đã sửa thành công");
							layDSSanPhamTheoHopDongTuDB(spSua.getHopDong().getMaHopDong());
						}
					}
				}
			}
		}
		
		if (o.equals(btnXoa)) {
			int row = tblDSSanPham.getSelectedRow();
			int luaChon = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xoá cột này?", "Lưu Ý", JOptionPane.YES_NO_OPTION);
			
			if (row == -1)
				JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cần xoá!!!");
			else {
				if (luaChon == JOptionPane.YES_OPTION) {
					if (sp_DAO.deleteSanPham((String) tblDSSanPham.getValueAt(row, 1))) {
						modelDSSanPham.removeRow(row);
						JOptionPane.showMessageDialog(null, "Xoá thành công!");
					} else {
						JOptionPane.showMessageDialog(null, "Xoá thất bại! Không tìm thấy hợp đồng cần xoá");
					}
				}
			}
		}
		
		if (o.equals(cboTrangThai)) {
			switch (cboTrangThai.getSelectedItem().toString()) {
			
			case "Hiển thị tất cả" -> hienThiDSHopDong();
			case "Đã hoàn thành" -> hienThiDSHopDongTheoTT(true);
			case "Chưa hoàn thành" -> hienThiDSHopDongTheoTT(false);
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + cboTrangThai.getSelectedItem());
			}
		}
	}
	/**
	 * Phương thức tạo mã sản phẩm dựa trên mã hợp đồng
	 * @param maHD
	 * @return String maSP
	 */
	private String taoMaSP(String maHD) {
		HopDong hd = hd_DAO.getMotHopDong(maHD);
		String maCanTao = "00";
	
		return hd.getMaHopDong() + maCanTao.substring(0, String.valueOf(sp_DAO.getSizeCuaDSTheoHopDong(maHD)).length()) 
		+ sp_DAO.getSizeCuaDSTheoHopDong(maHD);
	}
}
