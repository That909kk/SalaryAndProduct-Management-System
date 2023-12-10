package gUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.CongNhan_DAO;
import dao.Xuong_DAO;
import entity.CongNhan;
import entity.Xuong;

public class CongNhan_GUI extends JFrame  implements MouseListener, ActionListener  {

	private JFrame frame;
	private JPanel contentPane;
	private JButton btnThem, btnXoa, btnSua;
	private JCheckBox chkSang, chkToi;
	private JPanel panel_ThongTin;
	private JDateChooser dcNgaySinh, dcNgayBatDauLam;
	private JRadioButton rdbtnNam, rdbtnNu;
	private JComboBox cboXuong;
	private JTextField txtTimKiem;
	private JTextField txtChuyenMon;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtCCCD;
	private JTextField txtTen;
	private JTextField txtHoDem;
	public JTable tblDsCN;
	public DefaultTableModel modelDsCN;
	private CongNhan_DAO congNhanDao;
	private DefaultComboBoxModel<String> modelNam;
	private JComboBox<String> cboNam;
	private static ArrayList<CongNhan> dsCN;
	private Xuong_DAO xuongDao;
	private ArrayList<Xuong> dsXuong;
	private ArrayList<CongNhan> dsCNX;
	private String ma;
	
	private CongNhan_DAO cn_DAO = new CongNhan_DAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CongNhan_GUI frame = new CongNhan_GUI();
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
	public CongNhan_GUI() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(this.getPNLCongNhan());
	}

	public JPanel getPNLCongNhan() {
		
		try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		congNhanDao = new CongNhan_DAO();
		dsCN = congNhanDao.getListCN();
		xuongDao = new Xuong_DAO();
		dsXuong = xuongDao.getDSXuong();
		dsCNX = new ArrayList<CongNhan>();
		
		JPanel pnlCN = new JPanel();
		pnlCN.setBackground(new Color(240, 248, 255));
		pnlCN.setLayout(null);
		pnlCN.setBounds(0, 50, 1268, 632);
		
		panel_ThongTin = new JPanel();
		panel_ThongTin.setBackground(new Color(240, 248, 255));
		panel_ThongTin.setBorder(new TitledBorder(null, "Th\u00F4ng tin c\u00E1 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_ThongTin.setBounds(10, 0, 872, 239);
		pnlCN.add(panel_ThongTin);
		panel_ThongTin.setLayout(null);
		
		JLabel lblHodem = new JLabel("Họ đệm:");
		lblHodem.setBounds(21, 26, 70, 24);
		panel_ThongTin.add(lblHodem);
		lblHodem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTen = new JLabel("Tên:");
		lblTen.setBounds(21, 70, 70, 24);
		panel_ThongTin.add(lblTen);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setBounds(21, 104, 88, 33);
		panel_ThongTin.add(lblNgaysinh);
		lblNgaysinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setBounds(21, 147, 70, 24);
		panel_ThongTin.add(lblCCCD);
		lblCCCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtHoDem = new JTextField();
		txtHoDem.setBounds(111, 26, 168, 24);
		panel_ThongTin.add(txtHoDem);
		txtHoDem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHoDem.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.setBounds(111, 70, 168, 24);
		panel_ThongTin.add(txtTen);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTen.setColumns(10);
		
		txtCCCD = new JTextField();
		txtCCCD.setBounds(111, 147, 168, 24);
		panel_ThongTin.add(txtCCCD);
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		
		JLabel lblSDT = new JLabel("SDT:");
		lblSDT.setBounds(297, 26, 54, 24);
		panel_ThongTin.add(lblSDT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtSDT = new JTextField();
		txtSDT.setBounds(403, 26, 249, 24);
		panel_ThongTin.add(txtSDT);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setColumns(10);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(297, 70, 70, 24);
		panel_ThongTin.add(lblDiaChi);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(403, 70, 249, 24);
		panel_ThongTin.add(txtDiaChi);
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		
		JLabel lblBatDauLam = new JLabel("Bắt đầu làm:");
		lblBatDauLam.setBounds(297, 108, 101, 24);
		panel_ThongTin.add(lblBatDauLam);
		lblBatDauLam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(297, 147, 70, 24);
		panel_ThongTin.add(lblCaLam);
		lblCaLam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkSang = new JCheckBox("Sáng");
		chkSang.setBounds(403, 147, 70, 21);
		panel_ThongTin.add(chkSang);
		chkSang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkToi = new JCheckBox("Tối");
		chkToi.setBounds(481, 147, 54, 21);
		panel_ThongTin.add(chkToi);
		chkToi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkThem = new JCheckBox("Thêm");
		chkThem.setBounds(564, 147, 88, 21);
		panel_ThongTin.add(chkThem);
		chkThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setBounds(21, 189, 88, 24);
		panel_ThongTin.add(lblGioiTinh);
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(111, 189, 70, 21);
		panel_ThongTin.add(rdbtnNam);
		rdbtnNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(207, 189, 60, 21);
		panel_ThongTin.add(rdbtnNu);
		rdbtnNu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblChuyenMon = new JLabel("Chuyên môn:");
		lblChuyenMon.setBounds(297, 189, 110, 24);
		panel_ThongTin.add(lblChuyenMon);
		lblChuyenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtChuyenMon = new JTextField();
		txtChuyenMon.setBounds(414, 192, 223, 24);
		panel_ThongTin.add(txtChuyenMon);
		txtChuyenMon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtChuyenMon.setColumns(10);
		
		JLabel lblXuong = new JLabel("Xưởng:");
		lblXuong.setBounds(658, 147, 70, 24);
		panel_ThongTin.add(lblXuong);
		lblXuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		String cbXuong[] = {"May", "Gia công", "Nhuộm", "Tất cả"};
		cboXuong = new JComboBox(cbXuong);
		cboXuong.setBounds(744, 143, 101, 33);
		panel_ThongTin.add(cboXuong);
		cboXuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboXuong.setToolTipText("\r\n");
	
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setBounds(658, 189, 88, 24);
		panel_ThongTin.add(lblTrangThai);
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JCheckBox chkTrangThai = new JCheckBox("Đang làm");
		chkTrangThai.setBounds(744, 189, 110, 21);
		panel_ThongTin.add(chkTrangThai);
		chkTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblImage = new JLabel("image");
		lblImage.setBounds(662, 26, 184, 111);
		panel_ThongTin.add(lblImage);
		lblImage.setBackground(new Color(0, 0, 0));
		lblImage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		dcNgaySinh = new JDateChooser();
		dcNgaySinh.setBounds(111, 104, 168, 30);
		panel_ThongTin.add(dcNgaySinh);
		
		dcNgayBatDauLam = new JDateChooser();
		dcNgayBatDauLam.setBounds(403, 104, 168, 30);
		panel_ThongTin.add(dcNgayBatDauLam);
		
		tblDsCN = new JTable();
	    modelDsCN = new DefaultTableModel();
	    tblDsCN.setModel(modelDsCN);
	    modelDsCN.addColumn("Mã công nhân");
	    modelDsCN.addColumn("Họ đệm");
	    modelDsCN.addColumn("Tên");
	    modelDsCN.addColumn("Tuổi");
	    modelDsCN.addColumn("Ngày sinh");
	    modelDsCN.addColumn("CCCD");
	    modelDsCN.addColumn("Giới tính");
	    modelDsCN.addColumn("SDT");
	    modelDsCN.addColumn("Địa chỉ");
	    modelDsCN.addColumn("Ngày bắt đầu làm");
	    modelDsCN.addColumn("Ca làm việc");
	    modelDsCN.addColumn("Chuyên môn");
	    modelDsCN.addColumn("Xưởng");
	    tblDsCN.setRowHeight(26);
		JScrollPane scrollPane = new JScrollPane(tblDsCN);
		scrollPane.setBounds(10, 240, 1246, 380);
		pnlCN.add(scrollPane);
		
		JPanel panel_TacVu = new JPanel();
		panel_TacVu.setBackground(new Color(240, 248, 255));
		panel_TacVu.setBorder(new TitledBorder(null, "T\u00E1c v\u1EE5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TacVu.setBounds(885, 0, 381, 237);
		pnlCN.add(panel_TacVu);
		panel_TacVu.setLayout(null);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(10, 30, 110, 33);
		panel_TacVu.add(txtTimKiem);
		txtTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setText("Nhập tên");
		txtTimKiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa placeholder text khi TextField nhận focus
                if (txtTimKiem.getText().equals("Nhập tên")) {
                	txtTimKiem.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtTimKiem.getText().isEmpty()) {
                	txtTimKiem.setText("Nhập tên");
                }
            }
        });
		txtTimKiem.setToolTipText("");
		txtTimKiem.setColumns(10);
		
		JButton btnTim = new JButton("");
		btnTim.setBackground(new Color(255, 255, 255));
		btnTim.setBounds(130, 30, 54, 33);
		panel_TacVu.add(btnTim);
		btnTim.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		btnTim.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		String cbNam[] = {"2020", "2021", "2022"};
		JComboBox cboNam = new JComboBox(cbNam);
		cboNam.setBounds(205, 33, 164, 30);
		panel_TacVu.add(cboNam);
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(255, 255, 255));
		btnThem.setBounds(205, 81, 164, 50);
		panel_TacVu.add(btnThem);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setBackground(new Color(255, 255, 255));
		btnXemChiTiet.setBounds(10, 81, 174, 50);
		panel_TacVu.add(btnXemChiTiet);
		btnXemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnXemChiTiet.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(255, 255, 255));
		btnSua.setBounds(10, 149, 174, 50);
		panel_TacVu.add(btnSua);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 255, 255));
		btnXoa.setBounds(205, 149, 164, 50);
		panel_TacVu.add(btnXoa);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 16));
	
		dsCNX = congNhanDao.getListCNtheoXuong(dsXuong.get(0).getMaXuong());
		docDuLieuVaoTable(dsCNX);
		tblDsCN.addMouseListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
		return pnlCN;
	}
	
	public void docDuLieuVaoTable(ArrayList<CongNhan> dsCN) {
		 for(CongNhan cn: dsCN) {
			 String tenXuong="" ;
				for(Xuong x: dsXuong) {
				if(x.getMaXuong().equals(cn.getXuong().getMaXuong())) {
					tenXuong = x.getTenXuong();
				}
			}
	        	Object[] rowData = {cn.getMaCN(),cn.getHo(),cn.getTen(),tinhTuoi(cn),cn.getNgaySinh(),cn.getcCCD(),
	        			laGioiTinh(cn),cn.getSoDienThoai(),cn.getDiaChi(),
	        			cn.getNgayBatDauLamViec(),layCaLamViec(cn), cn.getChuyenMon(),tenXuong};
	        	modelDsCN.addRow(rowData);
	        }
	}
	public int tinhTuoi(CongNhan cn) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(cn.getNgaySinh(), currentDate).getYears();  
    }
	
	public int tinhTuoiTheoNS(LocalDate ns) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(ns, currentDate).getYears();  
    }
	
	public String laGioiTinh(CongNhan cn) {
		if(cn.isGioiTinh()==true) {
			return "Nam";
		}
		return "Nữ";
	}

	public String layCaLamViec(CongNhan cn) {
		if(cn.getCaLamViec()==1) {
			return "Sáng";
		}
		return "Tối";
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	public static String phatSinhMaCN(LocalDate today) {
        // Lấy 2 số cuối của năm hiện tại
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        String maMax="";
        String year = yearFormat.format(Date.valueOf(today));
        int count =0;
        for(CongNhan nv: dsCN) {
        	if(nv.getNgayBatDauLamViec().getYear()== today.getYear()) {
        		count++;
        		maMax = nv.getMaCN();
        	}
        }
        if(count == 0) {
        	// Định dạng thứ tự người làm vào công ty trong năm
            String formattedSequence = String.format("%04d", count+1 );

            // Tạo mã nhân viên
            String employeeId = "CN" + year + formattedSequence; 	
            return employeeId;
        }
        else {
        	count = Integer.parseInt(maMax.substring(maMax.length() - 4));
        	String formattedSequence = String.format("%04d", count+1 );

            // Tạo mã nhân viên
            String employeeId = "CN" + year + formattedSequence; 
            return employeeId;
        }
      
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==btnThem) {
			cboNam.setSelectedItem("Tất cả");
			String ten = txtTen.getText();
			String regexTen = "^[A-Z]";
			if(ten.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống tên");
				return;
			}
			if(Pattern.matches(regexTen, ten)) {
				JOptionPane.showMessageDialog( frame, "Chữ cái đầu của tên phải viết hoa");
				return;	
				
			}
			
			String hoDem = txtHoDem.getText();
			String regexHoDem = "^(\\p{Lu}\\p{L}*(\\s|$))+";
	        Pattern patternHoDem = Pattern.compile(regexHoDem);
	        if(hoDem.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống họ đệm");
				return;
			}
	        if(patternHoDem.matches(regexHoDem, hoDem)==false) {
	        	JOptionPane.showMessageDialog( frame, "Các từ trong họ đệm phải có chữ cái đầu viết hoa");
				return;	
	        }
	        
			String diaChi = txtDiaChi.getText();
			if(diaChi.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống địa chỉ");
				return;
			}
			
			String sdt = txtSDT.getText();
			String regexSDT = "0[15789]\\d{8}";
			Pattern patternSDT = Pattern.compile(regexSDT);
			if(sdt.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống số điện thoại");
				return;
			}
			if(patternSDT.matches(regexSDT, sdt)==false) {
				JOptionPane.showMessageDialog( frame, "Số điện thoại phải có đầu số là 01,03,05,07,08,09 và gồm 10 chữ số");
				return;
			}
			
			
			String cccd = txtCCCD.getText();
			String regexCCCD = "^[0-9]{12}$";
			if(cccd.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống căn cước công dân");
				return;
			}
			if(Pattern.matches(regexCCCD, cccd)==false) {
				JOptionPane.showMessageDialog( frame, "Căn cước công dân bao gồm 12 số");
				return;
			}
			
			LocalDate ngaySinh=null;
			ngaySinh = dcNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(tinhTuoiTheoNS(ngaySinh) < 18) {
				JOptionPane.showMessageDialog(frame, "Nhân viên chưa đủ 18 tuổi");
				return;
			}

			
			
			if(String.valueOf(cboXuong.getSelectedItem()).equals("Tất cả")) {
				JOptionPane.showMessageDialog(frame, "Bạn chưa chọn bộ phận cho nhân viên");
			}
			
			LocalDate ngayBatDauLam = null;
			ngayBatDauLam = dcNgayBatDauLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if((rdbtnNam.isSelected() == false)&&(rdbtnNu.isSelected()==false)) {
				JOptionPane.showMessageDialog( frame, "Bạn chưa chọn giới tính");
				return;	
			}
			
			String maNV= phatSinhMaCN(ngayBatDauLam);
			Boolean gioiTinh=null;
			if(rdbtnNam.isSelected() == true) {
				gioiTinh = true;
			}else if(rdbtnNu.isSelected()==true)
				gioiTinh = false;
			
			int caLam=1;
			if((chkSang.isSelected() == false)&&(chkToi.isSelected()==false)) {
				JOptionPane.showMessageDialog( frame, "Bạn chưa chọn ca làm");
				return;	
			}
			if(chkSang.isSelected()==true) {
				caLam = 1;	
			}else if(chkToi.isSelected()==true) {
				caLam = 2;
			}
			
			double phuCap = 500000;
			String chuyenMon = txtChuyenMon.getText().trim();
			String maXuong = dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong();
			Xuong xuong = new Xuong(maXuong);
			
//			CongNhan cn = new CongNhan(maNV, null, hoDem, ten, gioiTinh, sdt, diaChi, cccd, ngaySinh, ngayBatDauLam, caLam, phuCap, xuong);
			CongNhan cn = new CongNhan(maNV, null, hoDem, ten, gioiTinh, ngaySinh, cccd, diaChi, sdt, "", caLam, phuCap, 2000000f, ngayBatDauLam, xuong);
			congNhanDao.insertCN(cn);
			dsCN = congNhanDao.getListCN();
			clearTable();
			dsCNX = congNhanDao.getListCNtheoXuong(dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong());
			docDuLieuVaoTable(dsCNX);
				
				
		}
		if(o==btnXoa) {
			int row = tblDsCN.getSelectedRow();
			int xacNhan;
			String xuong = String.valueOf(cboXuong.getSelectedItem());
			String nam = String.valueOf(cboNam.getSelectedItem());
			if(xuong.equals("Tất cả")){
				if(nam.equals("Tất cả")) {
					xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn xóa công nhân có mã là " +dsCN.get(row).getMaCN()+" và có tên là "+dsCN.get(row).getHo()+" "+ dsCN.get(row).getTen()+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION) {
						congNhanDao.deleteCN(ma);
						clearTable();
						dsCN = congNhanDao.getListCN();
						if(dsCN.isEmpty()) {
							modelDsCN.setRowCount(0);
							return;
						}
						docDuLieuVaoTable(dsCN);
						return;
					}
					return;
				}
				else {
					xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn xóa công nhân có mã là " +dsCN.get(row).getMaCN()+" và có tên là "+dsCN.get(row).getHo()+" "+ dsCN.get(row).getTen()+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION) {
						congNhanDao.deleteCN(ma);
						
						dsCN = congNhanDao.getListCNtheoNamVaoLam(Integer.valueOf(nam));
						if(dsCN.isEmpty()) {
							modelDsCN.setRowCount(0);
							return;
						}
						clearTable();
						docDuLieuVaoTable(dsCN);
						return;
					}
					return;

				}
			}
			else {
				if(nam.equals("Tất cả")) {
					xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn xóa nhân viên có mã là " +dsCNX.get(row).getMaCN()+" và có tên là "+dsCNX.get(row).getHo()+" "+ dsCNX.get(row).getTen()+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION) {
						congNhanDao.deleteCN(ma);
						dsCNX = congNhanDao.getListCNtheoXuong(dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong());
						dsCNX = congNhanDao.getListCNtheoNamVaoLam(Integer.valueOf(nam));
						if(dsCNX.isEmpty()) {
							modelDsCN.setRowCount(0);
							return;
						}
						clearTable();
						docDuLieuVaoTable(dsCNX);
						return;
					}
					return;
				}else {
					xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn xóa nhân viên có mã là " +dsCNX.get(row).getMaCN()+" và có tên là "+dsCNX.get(row).getHo()+" "+ dsCNX.get(row).getTen()+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION) {
						congNhanDao.deleteCN(ma);
						dsCNX = congNhanDao.getListNVtheoNamXuong(Integer.valueOf(nam),dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong());
						if(dsCNX.isEmpty()) {
							modelDsCN.setRowCount(0);
							return;
						}
						clearTable();
						docDuLieuVaoTable(dsCNX);
						return;
					}
					return;
					
				}
			}
		}
		if(o==btnSua) {
			
			String ten = txtTen.getText();
			String regexTen = "^[A-Z]";
			if(ten.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống tên");
				return;
			}
			if(Pattern.matches(regexTen, ten)) {
				JOptionPane.showMessageDialog( frame, "Chữ cái đầu của tên phải viết hoa");
				return;	
				
			}
			
			String hoDem = txtHoDem.getText();
			String regexHoDem = "^(\\p{Lu}\\p{L}*(\\s|$))+";
	        Pattern patternHoDem = Pattern.compile(regexHoDem);
	        if(hoDem.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống họ đệm");
				return;
			}
	        if(patternHoDem.matches(regexHoDem, hoDem)==false) {
	        	JOptionPane.showMessageDialog( frame, "Các từ trong họ đệm phải có chữ cái đầu viết hoa");
				return;	
	        }
	        
			String diaChi = txtDiaChi.getText();
			if(diaChi.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống địa chỉ");
				return;
			}
			
			String sdt = txtSDT.getText();
			String regexSDT = "0[15789]\\d{8}";
			Pattern patternSDT = Pattern.compile(regexSDT);
			if(sdt.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống số điện thoại");
				return;
			}
			if(patternSDT.matches(regexSDT, sdt)==false) {
				JOptionPane.showMessageDialog( frame, "Số điện thoại phải có đầu số là 01,03,05,07,08,09 và gồm 10 chữ số");
				return;
			}
			
			
			String cccd = txtCCCD.getText();
			String regexCCCD = "^[0-9]{12}$";
			if(cccd.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống căn cước công dân");
				return;
			}
			if(Pattern.matches(regexCCCD, cccd)==false) {
				JOptionPane.showMessageDialog( frame, "Căn cước công dân bao gồm 12 số");
				return;
			}
			
			LocalDate ngaySinh=null;
			ngaySinh = dcNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(tinhTuoiTheoNS(ngaySinh) < 18) {
				JOptionPane.showMessageDialog(frame, "Nhân viên chưa đủ 18 tuổi");
				return;
			}

			
			
			if(String.valueOf(cboXuong.getSelectedItem()).equals("Tất cả")) {
				JOptionPane.showMessageDialog(frame, "Bạn chưa chọn bộ phận cho nhân viên");
			}
			
			LocalDate ngayBatDauLam = null;
			ngayBatDauLam = dcNgayBatDauLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if((rdbtnNam.isSelected() == false)&&(rdbtnNu.isSelected()==false)) {
				JOptionPane.showMessageDialog( frame, "Bạn chưa chọn giới tính");
				return;	
			}
			
			Boolean gioiTinh=null;
			if(rdbtnNam.isSelected() == true) {
				gioiTinh = true;
			}else if(rdbtnNu.isSelected()==true)
				gioiTinh = false;
			
			int caLam=1;
			if((chkSang.isSelected() == false)&&(chkToi.isSelected()==false)) {
				JOptionPane.showMessageDialog( frame, "Bạn chưa chọn ca làm");
				return;	
			}
			if(chkSang.isSelected()==true) {
				caLam = 1;	
			}else if(chkToi.isSelected()==true) {
				caLam = 2;
			}
			
			String chuyenMon = txtChuyenMon.getText().trim();
			String maXuong = dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong();
			Xuong xuong = new Xuong(maXuong);
//			CongNhan cn = new CongNhan(ma, null, hoDem, ten, gioiTinh, sdt, diaChi, cccd, ngaySinh, ngayBatDauLam, caLam, 500000, xuong);
			CongNhan cn = new CongNhan(ma, null, hoDem, ten, gioiTinh, ngaySinh, cccd, diaChi, sdt, chuyenMon, caLam, 500000, 2000000, ngayBatDauLam, xuong);
			
			int xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn sửa nhân viên có mã là " +ma+" và có tên là "+hoDem+" "+ten+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
			if(xacNhan == JOptionPane.YES_OPTION) {
				congNhanDao.updateCN(cn);
				clearTable();
				dsCN = congNhanDao.getListCN();
				dsCNX = congNhanDao.getListCNtheoXuong(maXuong);
				docDuLieuVaoTable(dsCNX);
				
				return;
			}
			return;	
		}
		}
	
	public void clearTable() {
		modelDsCN.getDataVector().removeAllElements();
	}
	
}
