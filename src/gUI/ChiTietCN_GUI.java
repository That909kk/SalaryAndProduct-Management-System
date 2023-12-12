package gUI;

import java.awt.Font;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.BangChamCongCN_DAO;
import dao.BangLuongCN_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import dao.SanPham_DAO;
import entity.BangChamCongCN;
import entity.BangLuongCN;
import entity.CongNhan;

public class ChiTietCN_GUI extends JFrame {
    private static final long serialVersionUID = 1L;
	private JPanel pnlChiTietLuongCN;
	private JTable tblChiTietBangLuong;
	private JPanel contentPane;
	private DefaultTableModel modelTableChiTietBangLuong;
	private JLabel lblHoTen;
	private JLabel lblXuong;
	private JLabel lblThang;
	private JTextField txtThang;
	private JTextField txtNam;
	private JTextField txtXuong;
	private JTextField txtMaCN;
	private JTextField txtHoTen;
	private JTextField txtLuongNhan;
	private JTextField txtBHXH;
	private JTextField txtSoNgayLam;
	private JTextField txtTongSongGioTangCa;
	private JLabel lblTongSoGioTangCa;
	private JLabel lblTongLuong;
	private JTextField txtTongLuong;
	private BangLuongCN_DAO bl_DAO;
	private BangChamCongCN_DAO bcc_DAO;
	private JLabel lblPhuCap;
	private JTextField txtPhuCap_1;
	private JLabel lblPhuCap_1;
	private JTextField txtTongSanLuong;
    private JTextField txtKhauTru;
    private CongNhan_DAO cn_DAO;
    private CongDoan_DAO cd_DAO;
    private SanPham_DAO sp_DAO;

    public ChiTietCN_GUI(String maNV,int Thang,int Nam,String Xuong,double khauTru) {
		super("Chi Tiết Lương Công Nhân");
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
		getChiTietCN_GUI();
		khoiTaoChiTietLuong(maNV,Thang,Nam,Xuong,khauTru);
    }
    public JPanel getChiTietCN_GUI() {
        pnlChiTietLuongCN = new JPanel();
  
        pnlChiTietLuongCN.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(pnlChiTietLuongCN);
		pnlChiTietLuongCN.setLayout(null);
        JLabel lblChiTietBangLuong = new JLabel("Chi Tiết Bảng Lương");
		lblChiTietBangLuong.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietBangLuong.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblChiTietBangLuong.setBounds(420, 11, 420, 31);
		pnlChiTietLuongCN.add(lblChiTietBangLuong);
		
		JLabel lblMaNV = new JLabel("Mã Nhân Viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMaNV.setBounds(10, 66, 140, 25);
		pnlChiTietLuongCN.add(lblMaNV);
		
		String headerChiTiet[] = {"Mã Chấm Công", "Ngày Chấm", "Số Giờ Tăng Ca","Ca Làm","Có Mặt","Có Phép","Sản Lượng","Công Đoạn","Sản Phẩm","Đơn giá","Tiền Công","Ghi chú"};
		modelTableChiTietBangLuong = new DefaultTableModel(headerChiTiet, 0) {
			 @Override
		        public Class<?> getColumnClass(int columnIndex) {
		        return (columnIndex >= 4 && columnIndex <= 5) ? Boolean.class : Object.class;
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
		pnlChiTietLuongCN.add(scrollpane);
        lblHoTen = new JLabel("Họ tên:");
		lblHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHoTen.setBounds(10, 111, 80, 25);
		pnlChiTietLuongCN.add(lblHoTen);

        lblXuong = new JLabel("Xưởng:");
		lblXuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblXuong.setBounds(300, 66, 80, 25);
		pnlChiTietLuongCN.add(lblXuong);
		
		lblThang = new JLabel("Tháng");
		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThang.setBounds(300, 111, 60, 25);
		pnlChiTietLuongCN.add(lblThang);

        txtThang = new JTextField();
		txtThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtThang.setBounds(360, 111, 30, 25);
		pnlChiTietLuongCN.add(txtThang);
		txtThang.setColumns(10);
		
		JLabel lblNam = new JLabel("Năm");
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNam.setBounds(400, 111, 45, 25);
		pnlChiTietLuongCN.add(lblNam);

        txtNam = new JTextField();
		txtNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNam.setColumns(10);
		txtNam.setBounds(450, 111, 85, 25);
		pnlChiTietLuongCN.add(txtNam);
		
		txtXuong = new JTextField();
		txtXuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtXuong.setColumns(10);
		txtXuong.setBounds(382, 66, 165, 25);
		pnlChiTietLuongCN.add(txtXuong);
		
		txtMaCN = new JTextField();
		txtMaCN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaCN.setColumns(10);
		txtMaCN.setBounds(143, 66, 142, 25);
		pnlChiTietLuongCN.add(txtMaCN);


        txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(85, 111, 200, 25);
		pnlChiTietLuongCN.add(txtHoTen);
		
		JLabel lblLuongNhan = new JLabel("Lương Nhận:");
		lblLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuongNhan.setBounds(560, 66, 160, 25);
		pnlChiTietLuongCN.add(lblLuongNhan);
		
		JLabel lblBHXH = new JLabel("Bảo Hiểm Xã Hội:");
		lblBHXH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBHXH.setBounds(560, 111, 160, 25);
		pnlChiTietLuongCN.add(lblBHXH);
		
		txtLuongNhan = new JTextField();
		txtLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLuongNhan.setColumns(10);
		txtLuongNhan.setBounds(680, 66, 220, 25);
		pnlChiTietLuongCN.add(txtLuongNhan);

        txtBHXH = new JTextField();
		txtBHXH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBHXH.setColumns(10);
		txtBHXH.setBounds(720, 111, 200, 25);
		pnlChiTietLuongCN.add(txtBHXH);
		
		JLabel lblSoNgayNghi = new JLabel("Số Ngày Làm:");
		lblSoNgayNghi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSoNgayNghi.setBounds(35, 631, 135, 25);
		pnlChiTietLuongCN.add(lblSoNgayNghi);
		
		txtSoNgayLam = new JTextField();
		txtSoNgayLam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoNgayLam.setColumns(10);
		txtSoNgayLam.setBounds(172, 631, 100, 25);
		pnlChiTietLuongCN.add(txtSoNgayLam);
		
		txtTongSongGioTangCa = new JTextField();
		txtTongSongGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongSongGioTangCa.setColumns(10);
		txtTongSongGioTangCa.setBounds(490, 631, 100, 25);
		pnlChiTietLuongCN.add(txtTongSongGioTangCa);
		
		lblTongSoGioTangCa = new JLabel("Tổng Số Giờ Tăng Ca:");
		lblTongSoGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongSoGioTangCa.setBounds(290, 631, 200, 25);
		pnlChiTietLuongCN.add(lblTongSoGioTangCa);
		
		lblTongLuong = new JLabel("Tổng Lương:");
		lblTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTongLuong.setBounds(935, 631, 120, 25);
		pnlChiTietLuongCN.add(lblTongLuong);
		
		txtTongLuong = new JTextField();
		txtTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongLuong.setColumns(10);
		txtTongLuong.setBounds(1050, 631, 200, 25);
		pnlChiTietLuongCN.add(txtTongLuong);
		
		txtPhuCap_1 = new JTextField();
		txtPhuCap_1.setEditable(false);
		txtPhuCap_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPhuCap_1.setColumns(10);
//		txtPhuCap_1.setBounds(705, 631, 200, 25);
		txtPhuCap_1.setBounds(1040, 66, 200, 25);
		pnlChiTietLuongCN.add(txtPhuCap_1);
		
		lblPhuCap_1 = new JLabel("Phụ Cấp:");
		lblPhuCap_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblPhuCap_1.setBounds(620, 631, 80, 25);
		lblPhuCap_1.setBounds(934, 66, 160, 25);
		pnlChiTietLuongCN.add(lblPhuCap_1);
		
		JLabel lblKhauTru = new JLabel("Khấu Trừ:");
		lblKhauTru.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblKhauTru.setBounds(934, 66, 160, 25);
		lblKhauTru.setBounds(934, 111, 160, 25);
		pnlChiTietLuongCN.add(lblKhauTru);
		
		txtKhauTru = new JTextField();
		txtKhauTru.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKhauTru.setColumns(10);
//		txtKhauTru.setBounds(1040, 66, 200, 25);
		txtKhauTru.setBounds(1040, 111, 170, 25);
		pnlChiTietLuongCN.add(txtKhauTru);
		
		JLabel lblTongSanLuong = new JLabel("Tổng Sản Lượng:");
		lblTongSanLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblThuong.setBounds(934, 111, 160, 25);
		lblTongSanLuong.setBounds(600, 631, 200, 25);
		pnlChiTietLuongCN.add(lblTongSanLuong);
//		
		txtTongSanLuong = new JTextField();
		txtTongSanLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongSanLuong.setColumns(10);
//		txtTongSanLuong.setBounds(1090, 111, 170, 25);
		txtTongSanLuong.setBounds(760, 631, 170, 25);
		pnlChiTietLuongCN.add(txtTongSanLuong);
		
		txtTongSanLuong.setEditable(false);
		txtBHXH.setEditable(false);
		txtXuong.setEditable(false);
		txtHoTen.setEditable(false);
		txtLuongNhan.setEditable(false);
		txtMaCN.setEditable(false);
		txtNam.setEditable(false);
		txtSoNgayLam.setEditable(false);
		txtThang.setEditable(false);
		txtTongLuong.setEditable(false);
		txtKhauTru.setEditable(false);
		txtTongSongGioTangCa.setEditable(false);
		txtTongSongGioTangCa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTongLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSoNgayLam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBHXH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtXuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtLuongNhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaCN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtThang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtBHXH.setBorder(null);
		txtXuong.setBorder(null);
		txtHoTen.setBorder(null);
		txtLuongNhan.setBorder(null);
		txtMaCN.setBorder(null);
		txtNam.setBorder(null);
		txtSoNgayLam.setBorder(null);
		txtThang.setBorder(null);
		txtTongLuong.setBorder(null);
		txtTongSongGioTangCa.setBorder(null);
		txtTongSanLuong.setBorder(null);
		txtPhuCap_1.setBorder(null);
		txtKhauTru.setBorder(null);
		return pnlChiTietLuongCN;
    }
    public void khoiTaoChiTietLuong( String maCN, int thang, int nam,String Xuong,double khauTru) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	bl_DAO = new BangLuongCN_DAO();
        cd_DAO = new CongDoan_DAO();
        bcc_DAO = new BangChamCongCN_DAO();
        cn_DAO = new CongNhan_DAO();
        sp_DAO = new SanPham_DAO();
        BangLuongCN bl = bl_DAO.getMotBangLuongCNTheoThangNam(maCN, thang, nam);
        ArrayList<BangChamCongCN> dsBCC = bcc_DAO.getBangCCCNTheomaCNThangNam(maCN, thang, nam);
        CongNhan cn = cn_DAO.getCongNhanTheoMaCN(maCN);
        txtMaCN.setText(maCN);
        txtHoTen.setText(cn.getHo()+" "+cn.getTen());
        txtXuong.setText(Xuong);
        txtThang.setText(thang+"");
        txtNam.setText(nam+"");
        txtLuongNhan.setText(decimalFormat.format(bl.getLuongTong())+" VNĐ");
        txtBHXH.setText(decimalFormat.format(bl.getBhxh())+" VNĐ");
        txtKhauTru.setText(decimalFormat.format(khauTru)+" VNĐ");
        txtPhuCap_1.setText(decimalFormat.format(cn.getPhuCap())+" VNĐ");
        txtSoNgayLam.setText(bcc_DAO.laySoNgayDiLamCuaCNTheoThangNam(maCN, thang, nam)+"");
        txtTongSongGioTangCa.setText(bcc_DAO.layTongSoGioTangCaCuaCNTheoThangNam(maCN, thang, nam)+"");
        txtTongSanLuong.setText(bcc_DAO.laySoSanLuongCuaCNTheoThangNam(maCN, thang, nam)+"");
        double tienCong1Ngay = cn.getLuongCoBan()/30;
//        for (BangChamCongCN bcc : dsBCC) {
//            double tienCong = 0;
//            if (bcc.isVangMat()==true||bcc.isCoPhep()==true) {
//                tienCong = 0;
//                
//            }else{
//            tienCong= tienCong1Ngay+bcc.getSanLuong()*bcc_DAO.layGiaTienSanPhamTheoBCC(bcc);}
//            modelTableChiTietBangLuong.addRow(new Object[] {bcc.getMaCCCN(),dtf.format(bcc.getNgayCham()),bcc.getSoGioTangCa(),bcc.getCaLam()==1?"Sáng":"Tối",bcc.isVangMat()?false:true,bcc.isCoPhep()?true:false,bcc.getSanLuong(),cd_DAO.getMotCongDoanTheoMaCD(cd_DAO.getMaCDtheomaCC(bcc)).getTenCongDoan(),sp_DAO.getMotSanPham(bcc_DAO.layMaSPtheoMaCC(bcc)),decimalFormat.format(bcc_DAO.layGiaTienSanPhamTheoBCC(bcc)),decimalFormat.format(tienCong),""});
//        }
        for (BangChamCongCN bcc : dsBCC) {
            double tienCong = 0;
            if (bcc.isVangMat() || bcc.isCoPhep()) {
                tienCong = 0;
            } else {
                tienCong = tienCong1Ngay + bcc.getSanLuong() * bcc_DAO.layGiaTienSanPhamTheoBCC(bcc);
            }

            String ngayChamFormatted = bcc.getNgayCham() != null ? dtf.format(bcc.getNgayCham()) : "";
            modelTableChiTietBangLuong.addRow(new Object[] {
                bcc.getMaCCCN(),
                ngayChamFormatted,
                bcc.getSoGioTangCa(),
                bcc.getCaLam() == 1 ? "Sáng" : "Tối",
                bcc.isVangMat() ? false : true,
                bcc.isCoPhep()&&bcc.isVangMat() ? true : false,
                bcc.getSanLuong(),
                cd_DAO.getMotCongDoanTheoMaCD(cd_DAO.getMaCDtheomaCC(bcc)).getTenCongDoan(),
                sp_DAO.getMotSanPham(bcc_DAO.layMaSPtheoMaCC(bcc)).getTenSP(),
                decimalFormat.format(bcc_DAO.layGiaTienSanPhamTheoBCC(bcc)),
                decimalFormat.format(tienCong),
                ""
            });
        }

        int rowCount = tblChiTietBangLuong.getRowCount();
        double tongao=0;
        for (int i = 0; i < rowCount; i++) {
            tongao+=Double.parseDouble(tblChiTietBangLuong.getValueAt(i, 10).toString().replace(",", ""));

        }
        txtTongLuong.setText(decimalFormat.format(tongao)+" VNĐ");
}
}
