package gUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.BangPhanCongCN_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.SanPham_DAO;
import dao.Xuong_DAO;
import entity.BangPhanCongCN;
import entity.CongDoan;
import entity.CongNhan;
import entity.SanPham;
import entity.Xuong;

public class PhanCongCongNhan_GUI extends JFrame implements ActionListener, MouseListener, TableModelListener, ListSelectionListener{

	private JPanel contentPane;
	private JPanel Menu;
	private JPanel pnlPCCD;
	
	private DefaultTableModel modelCongDoan;
	private JTable tableCongDoan;
	private JTextField txtSoCN;
	private JTextField txtSoCD;
	private DefaultTableModel modelPCCN;
	private JTable tablePCCN;
	private JTextField txtNDTim;
	private JButton btnXem;
	private JButton btnThem;
	private JButton btnSua;
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
		
		tableCongDoan.getColumnModel().getColumn(0).setPreferredWidth(135);
		tableCongDoan.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableCongDoan.getColumnModel().getColumn(2).setPreferredWidth(130);
		tableCongDoan.getColumnModel().getColumn(3).setPreferredWidth(130);
		tableCongDoan.getColumnModel().getColumn(4).setPreferredWidth(115);
		tableCongDoan.getColumnModel().getColumn(5).setPreferredWidth(145);
		
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
		
		JLabel lblSoCD = new JLabel("Số công đoạn chưa đủ người:");
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
		txtSoCD.setText("");
		pnlThongTinCongDoan.add(txtSoCD);
		
		btnXem = new JButton("Xem chi tiết");
		btnXem.setBackground(new Color(255, 255, 255));
		btnXem.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnXem.setBounds(30, 160, 170, 50);
		btnXem.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		pnlThongTinCongDoan.add(btnXem);
		
		btnThem = new JButton("Thêm vào công đoạn");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnThem.setBounds(30, 100, 370, 50);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setIconTextGap(6);
		pnlThongTinCongDoan.add(btnThem);
		
		btnIn = new JButton("In");
		btnIn.setBackground(new Color(255, 255, 255));
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnIn.setBounds(230, 160, 170, 50);
		btnIn.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
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
		
		btnSua = new JButton("Cập Nhật");
		btnSua.setBounds(964, 585, 140, 40);
		pnlPCCD.add(btnSua);
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnSua.setIconTextGap(6);
		
		btnHoanTat = new JButton("Hoàn Tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHoanTat.setBounds(1114, 585, 140, 40);
		btnHoanTat.setIcon(new ImageIcon("img\\icons\\icons8-checked-checkbox-24.png"));
		pnlPCCD.add(btnHoanTat);
		
		btnThem.addActionListener(this);
		btnIn.addActionListener(this);
		btnXem.addActionListener(this);
		btnTim.addActionListener(this);
		btnSua.addActionListener(this);
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
			if (!cd.isTrangThai()) {
				modelCongDoan.addRow(new Object[] {sp.getTenSP(), cd.getMaCongDoan(), cd.getTenCongDoan(), 
						cd.getSoLuongCongNhanDuKien(), cd.getSoLuongSanPham(), 
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
			int sumOfProducts = 0;
			listBPCCN.clear();
			CongDoan cd = cd_DAO.getMotCongDoanTheoMaCD(tableCongDoan.getValueAt(rowCD, 1).toString());
			
			ArrayList<BangPhanCongCN> listTheoCD = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(tableCongDoan.getValueAt(rowCD, 1).toString());
			
			for (BangPhanCongCN bangPhanCongCN : listTheoCD) {
				sumOfProducts += bangPhanCongCN.getSoLuongSanPham();
			}
			
			for (Integer rowIndex : listRowPCCN) {
				if (!listRowUnchecked.contains(rowIndex)) {
					modelPCCN.setValueAt(true, rowIndex, 6);
				} else {
					modelPCCN.setValueAt(false, rowIndex, 6);
					modelPCCN.setValueAt(0, rowIndex, 7);
				}
			}
			if (sumOfProducts == 0) {
				for (Integer rowIndex : listRowPCCN) {
					int soLuongRowPCCNDaChon = listRowPCCN.size() - countRowUnchecked;
					int soLuongSP = (soLuongRowPCCNDaChon) % 2 == 0 
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
			listBPCCN = new ArrayList<BangPhanCongCN>();
			cd_DAO = new CongDoan_DAO();
			bPCCN_DAO = new BangPhanCongCN_DAO();
			
			for (Integer rowCheckedIndex : listRowPCCN) {
				listBPCCN.add(getCongNhanDuocChon(rowCheckedIndex));
			}
			
			for (BangPhanCongCN bangPhanCongCN : listBPCCN) {
				bangPhanCongCN.setSoLuongSanPham(Integer.parseInt(tablePCCN.getValueAt(0, 7).toString()));
				bPCCN_DAO.insertPhanCongCongNhan(bangPhanCongCN);
			}
			
			modelPCCNIsChanging = true;
			JOptionPane.showMessageDialog(null, "Phân công công đoạn thành công");
			
			txtSoCN.setText(cn_DAO.getDSCongNhan().size() - bPCCN_DAO.getDSCongNhanDuocPhanCong().size() + "");
			
			
			if (modelPCCNIsChanging) {
				btnHoanTat.setEnabled(false);
				btnSua.setEnabled(true);
			}
		}
		
		if (o.equals(btnSua)) {
			btnSua.setEnabled(false);
			modelPCCNIsChanging = false;
			
			rowCD = tableCongDoan.getSelectedRow();
			String maCD = modelCongDoan.getValueAt(rowCD, 1).toString();
			
			bPCCN_DAO.deleteALLPCCuaCongDoan(maCD);
			
			txtSoCN.setText(cn_DAO.getDSCongNhan().size() - bPCCN_DAO.getDSCongNhanDuocPhanCong().size() + "");
			
			hienThiDSPCCNTrenModel();
		}
		
		if (o.equals(btnXem)) {
			bPCCN_DAO = new BangPhanCongCN_DAO();
			cn_DAO = new CongNhan_DAO();
			x_DAO = new Xuong_DAO();
			
			rowCD = tableCongDoan.getSelectedRow();
			String maCD = modelCongDoan.getValueAt(rowCD, 1).toString();
			
			modelPCCN.setRowCount(0);
			
			for (BangPhanCongCN bpccn : bPCCN_DAO.getDSCongNhanTheoXuongVaDuocPhanCong(maCD)) {
				CongNhan cn = cn_DAO.getCongNhanTheoMaCN(bpccn.getCongNhan().getMaCN());
				Xuong x = x_DAO.getMotXuong(cn.getXuong().getMaXuong());
				
				modelPCCN.addRow(new Object[] {cn.getMaCN(), cn.getHo(), cn.getTen(), cn.getChuyenMon(), cn.getCaLamViec(),
						x.getTenXuong(), bpccn.isTrangThai(), bpccn.getSoLuongSanPham()});
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		
		if (o.equals(tableCongDoan)) {
			hienThiDSPCCNTrenModel();
			String maCD = tableCongDoan.getValueAt(rowCD, 1).toString();
			ArrayList<BangPhanCongCN> listBPCCNTheoCD = bPCCN_DAO.getDSPhanCongCongDoanTheoMaCD(maCD);
			
			if (listBPCCNTheoCD.size() != 0) {
				btnHoanTat.setEnabled(false);
				btnSua.setEnabled(true);
			} else {
				btnHoanTat.setEnabled(true);
				btnSua.setEnabled(false);
			}
		}
		
		if (o.equals(tablePCCN)) {
			int rowPCCN = tablePCCN.getSelectedRow();
			
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
			btnSua.setEnabled(true);
		else
			btnSua.setEnabled(false);
		
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
