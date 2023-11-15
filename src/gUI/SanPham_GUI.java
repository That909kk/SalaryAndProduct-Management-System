package gUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HopDong_DAO;
import dao.SanPham_DAO;
import entity.HopDong;
import entity.SanPham;

public class SanPham_GUI extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtTenSanPham;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXacNhan;
	private JCheckBox chkHDVaTT;
	private JTable tblDSSanPham;
	private DefaultTableModel modelDSSanPham;
	private DefaultComboBoxModel<String> modelCBOHopDong;
	private DefaultComboBoxModel<String> modelCBOTrangThai;
	private JComboBox<String> cboTrangThai;
	private JComboBox<String> cboHopDong;
	private JTextField txtSoLuong;
	
	private HopDong_DAO hd_DAO;
	private SanPham_DAO sp_DAO;
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
		
		JPanel pnlSP = new JPanel();
		pnlSP.setBackground(new Color(240, 248, 255));
		pnlSP.setBounds(0, 50, 1268, 632);
		pnlSP.setLayout(null);
		
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng Tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTin.setBackground(new Color(240, 248, 255));
		pnlThongTin.setBounds(10, 10, 450, 150);
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
		btnThem.setBounds(30, 50, 170, 40);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-20.png"));
		pnlChucNang.add(btnThem);
		
		btnXoa = new JButton("Xoá");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXoa.setBounds(250, 50, 170, 40);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		pnlChucNang.add(btnXoa);
		
		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSua.setBounds(250, 100, 170, 40);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		pnlChucNang.add(btnSua);
		
		chkHDVaTT = new JCheckBox("Lọc theo hợp đồng và trạng thái");
		chkHDVaTT.setBackground(new Color(240, 248, 255));
		chkHDVaTT.setBounds(30, 20, 250, 23);
		pnlChucNang.add(chkHDVaTT);
		chkHDVaTT.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnXacNhan = new JButton("Đã xong");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnXacNhan.setBackground(Color.WHITE);
		btnXacNhan.setBounds(30, 100, 170, 40);
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
		
		JPanel pnlLoc = new JPanel();
		pnlLoc.setBorder(new TitledBorder(null, "B\u1ED9 L\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLoc.setBackground(new Color(240, 248, 255));
		pnlLoc.setBounds(459, 10, 340, 149);
		pnlSP.add(pnlLoc);
		pnlLoc.setLayout(null);
		
		modelCBOHopDong = new DefaultComboBoxModel<String>();
		cboHopDong = new JComboBox<String>(modelCBOHopDong);
		cboHopDong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboHopDong.setBounds(110, 20, 220, 30);
		
		for (HopDong hd : hd_DAO.getDSHopDong()) {
			modelCBOHopDong.addElement(hd.getMaHopDong());
		}
		
		pnlLoc.add(cboHopDong);
		
		JLabel lblHopDong = new JLabel("Hợp đồng:");
		lblHopDong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHopDong.setBounds(10, 20, 100, 30);
		pnlLoc.add(lblHopDong);
		
		JLabel lblTrangThai = new JLabel("Trạng Thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrangThai.setBounds(10, 60, 100, 30);
		pnlLoc.add(lblTrangThai);
		
		modelCBOTrangThai = new DefaultComboBoxModel<String>();
		cboTrangThai = new JComboBox<String>(modelCBOTrangThai);
		cboTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTrangThai.setBounds(110, 60, 220, 30);
		modelCBOTrangThai.addElement("Hiển thị tất cả");
		modelCBOTrangThai.addElement("Đã hoàn thành");
		modelCBOTrangThai.addElement("Chưa hoàn thành");
		pnlLoc.add(cboTrangThai);
		
		txtTimKiem = new JTextField("Nhập tên sản phẩm");
		txtTimKiem.setForeground(new Color(119, 136, 153));
		txtTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTimKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTimKiem.getText().equals("Nhập tên đối tác")) {
					txtTimKiem.setForeground(Color.black);
					txtTimKiem.setText("");
				}
			}
		});
		txtTimKiem.setBounds(10, 100, 290, 30);
		pnlLoc.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		
		btnTimKiem = new JButton("");
		btnTimKiem.setBackground(new Color(255, 255, 255));
		btnTimKiem.setBounds(300, 100, 30, 30);
		btnTimKiem.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		pnlLoc.add(btnTimKiem);
		
		btnThem.addActionListener(this);
		btnXacNhan.addActionListener(this);
	    btnXoa.addActionListener(this);
	    btnSua.addActionListener(this);
	    btnTimKiem.addActionListener(this);
	    tblDSSanPham.addMouseListener(this);
	    cboTrangThai.addActionListener(this);
	    cboHopDong.addActionListener(this);
		chkHDVaTT.addActionListener(this);
		
		return pnlSP;
	}
	
	private void layDSSanPhamTuDB() {
		sp_DAO = new SanPham_DAO();
		hd_DAO = new HopDong_DAO();
		ArrayList<SanPham> listSP = sp_DAO.getDSSanPham();
		modelDSSanPham.setRowCount(0);
		
		for (SanPham sanPham : listSP) {
			HopDong hd = hd_DAO.getMotHopDong(sanPham.getHopDong().getMaHopDong());
			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
		}
	}
	
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
	
	private void layDSSanPhamTheoTrangThaiTuDB(boolean trangThai) {
		sp_DAO = new SanPham_DAO();
		hd_DAO = new HopDong_DAO();
		ArrayList<SanPham> listSP = sp_DAO.getDSSanPhamTheoTrangThai(trangThai);
		modelDSSanPham.setRowCount(0);
		
		for (SanPham sanPham : listSP) {
			HopDong hd = hd_DAO.getMotHopDong(sanPham.getHopDong().getMaHopDong());
			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
		}
	}
	
	private void layDSSanPhamTheoHDvaTTTuDB(String maHD, boolean trangThai) {
		sp_DAO = new SanPham_DAO();
		hd_DAO = new HopDong_DAO();
		ArrayList<SanPham> listSP = sp_DAO.getDSSanPhamTheoHDvaTT(maHD, trangThai);
		modelDSSanPham.setRowCount(0);
		
		for (SanPham sanPham : listSP) {
			HopDong hd = hd_DAO.getMotHopDong(maHD);
			modelDSSanPham.addRow(new Object[] {hd.getMaHopDong(), sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getSoLuong(),
					sanPham.getSoLuongCongDoan(), hd.getNgayThanhLyHopDong().format(dtfVN), sanPham.isTrangThai() ? "Đã hoàn thành" : "Chưa hoàn thành"});
		}
	}
	
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
	
	private void thongBaoLoiNhapDuLieu(JTextField txt, String mess) {
		JOptionPane.showMessageDialog(null, mess);
		txt.selectAll();
		txt.requestFocus();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblDSSanPham)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			int row = tblDSSanPham.getSelectedRow();
			
			txtTenDoiTac.setText(hd_DAO.getMotHopDong(tblDSSanPham.getValueAt(row, 0).toString()).getTenDoiTac());
			txtTenSanPham.setText(modelDSSanPham.getValueAt(row, 2).toString());
			txtSoLuong.setText(tblDSSanPham.getValueAt(row, 3).toString());
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
				String maHD = cboHopDong.getSelectedItem().toString();
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
			
			if (row == -1) 
				JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm cần xác nhận đã hoàn thành");
			else {
				if (tblDSSanPham.getValueAt(row, 6).toString().equals("Đã hoàn thành")) {
					JOptionPane.showMessageDialog(null, "Sản phẩm này đã hoàn thành");
				} else {
					String maSP = tblDSSanPham.getValueAt(row, 0).toString();
					SanPham sp = sp_DAO.getMotSanPham(maSP);
					
					if (sp != null) {
						sp.setTrangThai(true);
						sp_DAO.updateSanPham(sp);
						tblDSSanPham.setValueAt("Đã hoàn thành", row, 4);
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
			
			case "Hiển thị tất cả" -> layDSSanPhamTuDB();
			case "Chưa thanh lí" -> layDSSanPhamTheoTrangThaiTuDB(false);
			case "Đã thanh lí" -> layDSSanPhamTheoTrangThaiTuDB(true);
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + cboTrangThai.getSelectedItem());
			}
		}
		
		if (o.equals(cboHopDong)) {
			hd_DAO = new HopDong_DAO();
			String maHD = cboHopDong.getSelectedItem().toString();
			HopDong hd = hd_DAO.getMotHopDong(maHD);
			txtTenDoiTac.setText(hd.getTenDoiTac());
			layDSSanPhamTheoHopDongTuDB(maHD);
		}
		
		if (o.equals(chkHDVaTT)) {
			String maHD = cboHopDong.getSelectedItem().toString();
			String trangThai = cboTrangThai.getSelectedItem().toString();
			
			switch (trangThai) {
			
			case "Hiển thị tất cả" -> layDSSanPhamTheoHopDongTuDB(maHD);
			case "Chưa thanh lí" -> layDSSanPhamTheoHDvaTTTuDB(maHD, false);
			case "Đã thanh lí" -> layDSSanPhamTheoHDvaTTTuDB(maHD, true);
			
			default ->
			throw new IllegalArgumentException("Unexpected value: " + trangThai);
			}
		}
	}

	private String taoMaSP(String maHD) {
		HopDong hd = hd_DAO.getMotHopDong(maHD);
		String maCanTao = "00";
	
		return hd.getMaHopDong() + maCanTao.substring(0, String.valueOf(sp_DAO.getSizeCuaDSTheoHopDong(maHD)).length()) 
		+ sp_DAO.getSizeCuaDSTheoHopDong(maHD);
	}
}
