package gUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.BangChamCongNV_DAO;
import dao.NhanVien_DAO;
import entity.BangChamCongNV;
import entity.BangLuongNV;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import dao.BangChamCongNV_DAO;
import dao.BangLuongNV_DAO;
import dao.NhanVien_DAO;

public class ChiTietNV_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlChiTietLuongNV;
	private JTable tblChiTietBangLuong;
	private JPanel contentPane;
	private DefaultTableModel modelTableChiTietBangLuong;
	private JLabel lblHoTen;
	private JLabel lblBoPhan;
	private JLabel lblThang;
	private JTextField txtThang;
	private JTextField txtNam;
	private JTextField txtBoPhan;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtLuongNhan;
	private JTextField txtBHXH;
	private JTextField txtSoNgayNghi;
	private JTextField txtTongSongGioTangCa;
	private JLabel lblTongSoGioTangCa;
	private JLabel lblTongLuong;
	private JTextField txtTongLuong;
	private BangLuongNV_DAO bl_DAO;
	private NhanVien_DAO nv_DAO;
	private BangChamCongNV_DAO bcc_DAO;
	private JLabel lblPhuCap;
	private JTextField txtPhuCap;
	private JTextField txtPhuCap_1;
	private JLabel lblPhuCap_1;



	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChiTietNV_GUI frame = new ChiTietNV_GUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	/**
	 * Create the frame.
	 */
	public ChiTietNV_GUI(String maNV,int Thang,int Nam,String boPhan) {
		super("Chi Tiết Lương Nhân Viên");
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		getChiTietNV_GUI();
		khoiTaoChiTietLuong(maNV, Thang, Nam,boPhan);
	
//		pnlChiTietLuongNV = new JPanel();
//		pnlChiTietLuongNV.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(pnlChiTietLuongNV);
//		pnlChiTietLuongNV.setLayout(null);
//		
//		JLabel lblChiTietBangLuong = new JLabel("Chi Tiết Bảng Lương");
//		lblChiTietBangLuong.setHorizontalAlignment(SwingConstants.CENTER);
//		lblChiTietBangLuong.setFont(new Font("Tahoma", Font.PLAIN, 28));
//		lblChiTietBangLuong.setBounds(420, 11, 500, 31);
//		pnlChiTietLuongNV.add(lblChiTietBangLuong);
//		
//		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
//		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblMaNV.setBounds(74, 66, 140, 25);
//		pnlChiTietLuongNV.add(lblMaNV);
//		
//		tblChiTietBangLuong = new JTable();
//		tblChiTietBangLuong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tblChiTietBangLuong.setBounds(0, 160, 1260, 450);
//		pnlChiTietLuongNV.add(tblChiTietBangLuong);
//		
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setBounds(553, 160, 17, 376);
//		pnlChiTietLuongNV.add(scrollBar);
//		
//		lblHoTen = new JLabel("Họ tên:");
//		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblHoTen.setBounds(74, 111, 80, 25);
//		pnlChiTietLuongNV.add(lblHoTen);
//		
//		lblBoPhan = new JLabel("Bộ Phận:");
//		lblBoPhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblBoPhan.setBounds(450, 66, 80, 25);
//		pnlChiTietLuongNV.add(lblBoPhan);
//		
//		lblThang = new JLabel("Tháng");
//		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblThang.setBounds(450, 111, 60, 25);
//		pnlChiTietLuongNV.add(lblThang);
//		
//		txtThang = new JTextField();
//		txtThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtThang.setBounds(520, 111, 50, 25);
//		pnlChiTietLuongNV.add(txtThang);
//		txtThang.setColumns(10);
//		
//		JLabel lblNam = new JLabel("Năm");
//		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblNam.setBounds(580, 111, 45, 25);
//		pnlChiTietLuongNV.add(lblNam);
//		
//		txtNam = new JTextField();
//		txtNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtNam.setColumns(10);
//		txtNam.setBounds(626, 111, 85, 25);
//		pnlChiTietLuongNV.add(txtNam);
//		
//		txtBoPhan = new JTextField();
//		txtBoPhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtBoPhan.setColumns(10);
//		txtBoPhan.setBounds(538, 66, 200, 25);
//		pnlChiTietLuongNV.add(txtBoPhan);
//		
//		txtMaNV = new JTextField();
//		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtMaNV.setColumns(10);
//		txtMaNV.setBounds(210, 66, 200, 25);
//		pnlChiTietLuongNV.add(txtMaNV);
//		
//		txtHoTen = new JTextField();
//		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtHoTen.setColumns(10);
//		txtHoTen.setBounds(210, 111, 200, 25);
//		pnlChiTietLuongNV.add(txtHoTen);
//		
//		JLabel lblLuongNhan = new JLabel("Lương Nhận:");
//		lblLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblLuongNhan.setBounds(793, 66, 160, 25);
//		pnlChiTietLuongNV.add(lblLuongNhan);
//		
//		JLabel lblBHXH = new JLabel("Bảo Hiểm Xã Hội:");
//		lblBHXH.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblBHXH.setBounds(793, 111, 160, 25);
//		pnlChiTietLuongNV.add(lblBHXH);
//		
//		txtLuongNhan = new JTextField();
//		txtLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtLuongNhan.setColumns(10);
//		txtLuongNhan.setBounds(957, 66, 220, 25);
//		pnlChiTietLuongNV.add(txtLuongNhan);
//		
//		txtBHXH = new JTextField();
//		txtBHXH.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtBHXH.setColumns(10);
//		txtBHXH.setBounds(957, 111, 220, 25);
//		pnlChiTietLuongNV.add(txtBHXH);
//		
//		JLabel lblSoNgayNghi = new JLabel("Số Ngày Nghỉ:");
//		lblSoNgayNghi.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblSoNgayNghi.setBounds(35, 631, 135, 25);
//		pnlChiTietLuongNV.add(lblSoNgayNghi);
//		
//		txtSoNgayNghi = new JTextField();
//		txtSoNgayNghi.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtSoNgayNghi.setColumns(10);
//		txtSoNgayNghi.setBounds(172, 631, 100, 25);
//		pnlChiTietLuongNV.add(txtSoNgayNghi);
//		
//		txtTongSongGioTangCa = new JTextField();
//		txtTongSongGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtTongSongGioTangCa.setColumns(10);
//		txtTongSongGioTangCa.setBounds(490, 631, 100, 25);
//		pnlChiTietLuongNV.add(txtTongSongGioTangCa);
//		
//		lblTongSoGioTangCa = new JLabel("Tổng Số Giờ Tăng Ca:");
//		lblTongSoGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblTongSoGioTangCa.setBounds(290, 631, 200, 25);
//		pnlChiTietLuongNV.add(lblTongSoGioTangCa);
//		
//		lblTongLuong = new JLabel("Tổng Lương:");
//		lblTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblTongLuong.setBounds(935, 631, 120, 25);
//		pnlChiTietLuongNV.add(lblTongLuong);
//		
//		txtTongLuong = new JTextField();
//		txtTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtTongLuong.setColumns(10);
//		txtTongLuong.setBounds(1050, 631, 200, 25);
//		pnlChiTietLuongNV.add(txtTongLuong);
//		
//		txtPhuCap_1 = new JTextField();
//		txtPhuCap_1.setEditable(false);
//		txtPhuCap_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		txtPhuCap_1.setColumns(10);
//		txtPhuCap_1.setBounds(705, 631, 200, 25);
//		pnlChiTietLuongNV.add(txtPhuCap_1);
//		
//		lblPhuCap_1 = new JLabel("Phụ Cấp:");
//		lblPhuCap_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblPhuCap_1.setBounds(620, 631, 80, 25);
//		pnlChiTietLuongNV.add(lblPhuCap_1);
	}
	public JPanel getChiTietNV_GUI() {
		pnlChiTietLuongNV = new JPanel();
		pnlChiTietLuongNV.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlChiTietLuongNV);
		pnlChiTietLuongNV.setLayout(null);
		
		JLabel lblChiTietBangLuong = new JLabel("Chi Tiết Bảng Lương");
		lblChiTietBangLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietBangLuong.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblChiTietBangLuong.setBounds(420, 11, 420, 31);
		pnlChiTietLuongNV.add(lblChiTietBangLuong);
		
		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNV.setBounds(74, 66, 140, 25);
		pnlChiTietLuongNV.add(lblMaNV);
		
		String headerChiTiet[] = {"Mã Chấm Công", "Ngày Chấm", "Số Giờ Tăng Ca","Ca Làm","Có Mặt","Vắng Mặt","Có Phép","Tiền Công","Ghi chú"};
		modelTableChiTietBangLuong = new DefaultTableModel(headerChiTiet, 0) {
			 @Override
		        public Class<?> getColumnClass(int columnIndex) {
		        return (columnIndex >= 4 && columnIndex <= 6) ? Boolean.class : Object.class;
		    }
		};
		
		tblChiTietBangLuong = new JTable(modelTableChiTietBangLuong);
		tblChiTietBangLuong.setFont(UIManager.getFont("TableHeader.font"));
		tblChiTietBangLuong.setFillsViewportHeight(true);
		tblChiTietBangLuong.setRowHeight(26);
		tblChiTietBangLuong.setRowSelectionAllowed(true);
		tblChiTietBangLuong.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblChiTietBangLuong.setBounds(20, 160, 550, 380);
		tblChiTietBangLuong.setEnabled(false);
		tblChiTietBangLuong.setAutoCreateRowSorter(true);
		tblChiTietBangLuong.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		JScrollPane scrollpane = new JScrollPane(tblChiTietBangLuong);
		scrollpane.setBounds(0, 160, 1260, 450);
		pnlChiTietLuongNV.add(scrollpane);
		
		
		lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHoTen.setBounds(74, 111, 80, 25);
		pnlChiTietLuongNV.add(lblHoTen);
		
		lblBoPhan = new JLabel("Bộ Phận:");
		lblBoPhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBoPhan.setBounds(450, 66, 80, 25);
		pnlChiTietLuongNV.add(lblBoPhan);
		
		lblThang = new JLabel("Tháng");
		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThang.setBounds(450, 111, 60, 25);
		pnlChiTietLuongNV.add(lblThang);
		
		txtThang = new JTextField();
		txtThang.setBounds(520, 111, 50, 25);
		pnlChiTietLuongNV.add(txtThang);
		txtThang.setColumns(10);
		
		JLabel lblNam = new JLabel("Năm");
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNam.setBounds(580, 111, 45, 25);
		pnlChiTietLuongNV.add(lblNam);
		
		txtNam = new JTextField();
		txtNam.setColumns(10);
		txtNam.setBounds(626, 111, 85, 25);
		pnlChiTietLuongNV.add(txtNam);
		
		txtBoPhan = new JTextField();
		txtBoPhan.setColumns(10);
		txtBoPhan.setBounds(538, 66, 200, 25);
		pnlChiTietLuongNV.add(txtBoPhan);
		
		txtMaNV = new JTextField();
		txtMaNV.setColumns(10);
		txtMaNV.setBounds(210, 66, 200, 25);
		pnlChiTietLuongNV.add(txtMaNV);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(210, 111, 200, 25);
		pnlChiTietLuongNV.add(txtHoTen);
		
		JLabel lblLuongNhan = new JLabel("Lương Nhận:");
		lblLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuongNhan.setBounds(793, 66, 160, 25);
		pnlChiTietLuongNV.add(lblLuongNhan);
		
		JLabel lblBHXH = new JLabel("Bảo Hiểm Xã Hội:");
		lblBHXH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBHXH.setBounds(793, 111, 160, 25);
		pnlChiTietLuongNV.add(lblBHXH);
		
		txtLuongNhan = new JTextField();
		txtLuongNhan.setColumns(10);
		txtLuongNhan.setBounds(957, 66, 220, 25);
		pnlChiTietLuongNV.add(txtLuongNhan);
		
		txtBHXH = new JTextField();
		txtBHXH.setColumns(10);
		txtBHXH.setBounds(957, 111, 220, 25);
		pnlChiTietLuongNV.add(txtBHXH);
		
		JLabel lblSoNgayNghi = new JLabel("Số Ngày Làm:");
		lblSoNgayNghi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSoNgayNghi.setBounds(35, 631, 135, 25);
		pnlChiTietLuongNV.add(lblSoNgayNghi);
		
		txtSoNgayNghi = new JTextField();
		txtSoNgayNghi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoNgayNghi.setColumns(10);
		txtSoNgayNghi.setBounds(172, 631, 100, 25);
		pnlChiTietLuongNV.add(txtSoNgayNghi);
		
		txtTongSongGioTangCa = new JTextField();
		txtTongSongGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongSongGioTangCa.setColumns(10);
		txtTongSongGioTangCa.setBounds(490, 631, 100, 25);
		pnlChiTietLuongNV.add(txtTongSongGioTangCa);
		
		lblTongSoGioTangCa = new JLabel("Tổng Số Giờ Tăng Ca:");
		lblTongSoGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongSoGioTangCa.setBounds(290, 631, 200, 25);
		pnlChiTietLuongNV.add(lblTongSoGioTangCa);
		
		lblTongLuong = new JLabel("Tổng Lương:");
		lblTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongLuong.setBounds(935, 631, 120, 25);
		pnlChiTietLuongNV.add(lblTongLuong);
		
		txtTongLuong = new JTextField();
		txtTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongLuong.setColumns(10);
		txtTongLuong.setBounds(1050, 631, 200, 25);
		pnlChiTietLuongNV.add(txtTongLuong);
		
		txtPhuCap_1 = new JTextField();
		txtPhuCap_1.setEditable(false);
		txtPhuCap_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPhuCap_1.setColumns(10);
		txtPhuCap_1.setBounds(705, 631, 200, 25);
		pnlChiTietLuongNV.add(txtPhuCap_1);
		
		lblPhuCap_1 = new JLabel("Phụ Cấp:");
		lblPhuCap_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhuCap_1.setBounds(620, 631, 80, 25);
		pnlChiTietLuongNV.add(lblPhuCap_1);
		
		txtBHXH.setEditable(false);
		txtBoPhan.setEditable(false);
		txtHoTen.setEditable(false);
		txtLuongNhan.setEditable(false);
		txtMaNV.setEditable(false);
		txtNam.setEditable(false);
		txtSoNgayNghi.setEditable(false);
		txtThang.setEditable(false);
		txtTongLuong.setEditable(false);
		txtTongSongGioTangCa.setEditable(false);
		txtTongSongGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSoNgayNghi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBHXH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBoPhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtThang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBHXH.setBorder(null);
		txtBoPhan.setBorder(null);
		txtHoTen.setBorder(null);
		txtLuongNhan.setBorder(null);
		txtMaNV.setBorder(null);
		txtNam.setBorder(null);
		txtSoNgayNghi.setBorder(null);
		txtThang.setBorder(null);
		txtTongLuong.setBorder(null);
		txtTongSongGioTangCa.setBorder(null);
		txtPhuCap_1.setBorder(null);
		return pnlChiTietLuongNV;
	}
	private void khoiTaoChiTietLuong(String maNV,int Thang,int Nam,String mabp) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.###");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        bl_DAO = new BangLuongNV_DAO();
        nv_DAO = new NhanVien_DAO();
        bcc_DAO = new BangChamCongNV_DAO();
        BangLuongNV bl= bl_DAO.lay1BangLuongTheoMaNVThangNam(maNV, Thang, Nam);
        
        ArrayList<BangChamCongNV> listBCC = bcc_DAO.dsBangCCtheomaNVthangnam(maNV, Thang, Nam);
        NhanVien nv = nv_DAO.getMotNVTuMaNV(maNV);
        txtMaNV.setText(nv.getMaNV());
        txtHoTen.setText(nv.getHo()+" "+nv.getTen());
        txtBoPhan.setText(mabp);
        txtThang.setText(Thang+"");
        txtNam.setText(Nam+"");
        txtLuongNhan.setText(decimalFormat.format(bl.getLuongTong())+" VNĐ");
        txtBHXH.setText(decimalFormat.format(bl.getBhxh())+" VNĐ");
        txtSoNgayNghi.setText(bcc_DAO.getSoNgayDiLamCua1NV(nv.getMaNV(),Thang,Nam)+"");
        txtTongSongGioTangCa.setText(bcc_DAO.getTongSoGioTangCaCua1NV(nv.getMaNV(),Thang,Nam)+"");
        txtPhuCap_1.setText(decimalFormat.format(nv.getPhuCap())+"");
        double tienCong1Ngay=nv.getLuongCoBan()*nv.getHeSoLuong()*nv.getThangBacLuong()/30;
        double tienCong1Gio=tienCong1Ngay/8;
        for(BangChamCongNV bcc : listBCC) {
        	double tienCong=0;
            if(bcc.isCoMat()) {
                tienCong=tienCong1Ngay+tienCong1Gio*2*bcc.getSoGioTangCa();
            }else if(bcc.isCoPhep()||bcc.isVangMat()) {
                tienCong=0;
            }
            
            modelTableChiTietBangLuong.addRow(new Object[] {bcc.getMaCCNV(),dtf.format(bcc.getNgayCham()),bcc.getSoGioTangCa(),bcc.getCaLam(),bcc.isCoMat(),bcc.isVangMat(),bcc.isCoPhep(),decimalFormat.format(tienCong),bcc.getGhiChu()});
        }
        int rowCount = tblChiTietBangLuong.getRowCount();
        double tongao=0;
        for (int i = 0; i < rowCount; i++) {

			
			tongao+= Double.parseDouble(modelTableChiTietBangLuong.getValueAt(i,7).toString().replace(",", ""));
		}
        txtTongLuong.setText(decimalFormat.format(tongao)+" VNĐ");
        
        }
}
