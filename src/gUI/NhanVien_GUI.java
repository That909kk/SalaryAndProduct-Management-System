package gUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class NhanVien_GUI implements ListSelectionListener {

	private JFrame frame;
	private JPanel pnTop, pnCenter;
	private JLabel lblHoDem, lblTen, lblNgaySinh, lblCCCD, lblGioiTinh, lblSDT, lblDiaChi, lblBatDauLam, lblCaLam, lblLuongCB, lblAvt, lblBacLuong, lblHeSoLuong;
	private JTextField txtHoDem, txtTen, txtCCCD, txtSDT, txtDiaChi, txtLuongCB,txtTimKiem;
	private kDatePicker dpNgaySinh,dpBatDauLam;
	private JCheckBox chkSang, chkToi;
	private JRadioButton rdoNam, rdoNu; 
	private DefaultComboBoxModel<String> modelBacLuong, modelHeSoLuong;
	private JComboBox<String> cboBacLuong, cboHeSoLuong;
	private JButton btnTimKiem, btnXemChiTiet, btnThem, btnXoa, btnSua; 
	private DefaultComboBoxModel<String> modelNam;
	private JComboBox<String> cboNam;
	public DefaultTableModel modelDsNV;
    public JTable tblDsNV;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien_GUI window = new NhanVien_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NhanVien_GUI() {
		frame = new JFrame("Nhân viên");
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		createMenuGUI();
		createGUI();
	}

	public void createMenuGUI(){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenu menuCN = new JMenu("Công nhân");
		menuBar.add(menuCN);
		
		JMenu menuNV = new JMenu("Nhân viên");
		menuBar.add(menuNV);
		
		JMenu menuCongDoan = new JMenu("Công đoạn");
		menuBar.add(menuCongDoan);
		
		JMenu menuLuong = new JMenu("Lương");
		menuBar.add(menuLuong);
		
		JMenu menuHopDong = new JMenu("Hợp đồng");
		menuBar.add(menuHopDong);
		
		JMenu menuTroGiup = new JMenu("Trợ giúp");
		menuBar.add(menuTroGiup);
		
		JMenu menuGioiThieu = new JMenu("Giới thiệu");
		menuBar.add(menuGioiThieu);	
	}
	
	public void createGUI() {
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		pnTop = new JPanel();
	    frame.getContentPane().add(pnTop, BorderLayout.NORTH);
	    pnTop.setBorder(new EmptyBorder(20, 20, 0, 20));
	    pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.X_AXIS));
	    
	    Box b,b1,b2,b3,b11,b12,b13,b14,b15,b21,b22,b31,b32,b33;
	    
	    
	    b = Box.createHorizontalBox();
	    pnTop.add(b);
	    
	    b1 = Box.createVerticalBox();
	    b2 = Box.createVerticalBox();
	    b3 = Box.createVerticalBox();
	    
	    b11 = Box.createHorizontalBox();
	    b12 = Box.createHorizontalBox();
	    b13 = Box.createHorizontalBox();
	    b14 = Box.createHorizontalBox();
	    b15 = Box.createHorizontalBox();
	    
	    b11.setPreferredSize(new Dimension(200,33));
	    b12.setPreferredSize(new Dimension(200,33));
	    b13.setPreferredSize(new Dimension(200,33));
	    b14.setPreferredSize(new Dimension(200,33));
	    b15.setPreferredSize(new Dimension(200,33));
	    
	    b21 = Box.createHorizontalBox();
	    b22 = Box.createHorizontalBox();
	    
	    b21.setPreferredSize(new Dimension(200, 30));
	    b22.setPreferredSize(new Dimension(200,33));
	    
	    b31 = Box.createHorizontalBox();
	    b32 = Box.createHorizontalBox();
	    b33 = Box.createHorizontalBox();
	    
	    b.add(b1);
	    b.add(Box.createHorizontalStrut(50));
	    
	    
	    b.add(b2);
	    b.add(Box.createHorizontalStrut(50));
	    b.add(b3);

	    b1.add(b11);
	    b1.add(Box.createVerticalStrut(5));
	    b1.add(b12);
	    b1.add(Box.createVerticalStrut(5));
	    b1.add(b13);
	    b1.add(Box.createVerticalStrut(5));
	    b1.add(b14);
	    b1.add(Box.createVerticalStrut(5));
	    b1.add(b15);
	    
	    
	    b3.add(Box.createVerticalStrut(15));
	    b3.add(b31);
	    b3.add(Box.createVerticalStrut(25));
	    b3.add(b32);
	    b3.add(Box.createVerticalStrut(25));
	    b3.add(b33);
	    b3.add(Box.createVerticalStrut(15));
	    
	    b3.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
	    
	    b31.setPreferredSize(new Dimension(200,33));
	    b32.setPreferredSize(new Dimension(200,33));
	    b33.setPreferredSize(new Dimension(200,33));
	    
	    b21.setPreferredSize(new Dimension(200,33));
	    b22.setPreferredSize(new Dimension(200,33));
	    
	    b1.setPreferredSize(new Dimension(432,180));
	    b2.setPreferredSize(new Dimension(216,180));
	    
	    lblNgaySinh = new JLabel("Ngày Sinh");
	    lblCaLam = new JLabel("Ca làm cố định");
	    lblHoDem = new JLabel("Họ đệm");
	    lblHoDem.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b11.add(lblHoDem);
	    b11.add(Box.createHorizontalStrut(15));
	    
	    txtHoDem = new JTextField();
	    b11.add(txtHoDem);
	    b11.add(Box.createHorizontalStrut(20));
	    
	    lblSDT = new JLabel("Số điện thoại");
	    lblSDT.setPreferredSize(lblCaLam.getPreferredSize());
	    b11.add(lblSDT);
	    
	    txtSDT = new JTextField();
	    b11.add(txtSDT);
	    
	    lblTen = new JLabel("Tên");
	    lblTen.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b12.add(lblTen);
	    b12.add(Box.createHorizontalStrut(15));
	    
	    txtTen = new JTextField();
	    b12.add(txtTen);
	    b12.add(Box.createHorizontalStrut(20));
	    
	    lblDiaChi = new JLabel("Địa chỉ");
	    lblDiaChi.setPreferredSize(lblCaLam.getPreferredSize());
	    b12.add(lblDiaChi);
	    
	    txtDiaChi = new JTextField();
	    b12.add(txtDiaChi);
	    
	    
	    b13.add(lblNgaySinh);
	    b13.add(Box.createHorizontalStrut(15));
	    
	    dpNgaySinh = new kDatePicker(193);
	    dpNgaySinh.setPreferredSize(new Dimension(193,35));
	    b13.add(dpNgaySinh);
	    
	    b13.add(Box.createHorizontalStrut(20));
	    lblBatDauLam = new JLabel("Bắt đầu làm");
	    lblBatDauLam.setPreferredSize(lblCaLam.getPreferredSize());
	    b13.add(lblBatDauLam);
	    
	    dpBatDauLam = new kDatePicker(193);
	    dpBatDauLam.setPreferredSize(new Dimension(193,35));
	    b13.add(dpBatDauLam);
	    
	    lblCCCD = new JLabel("CCCD");
	    lblCCCD.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b14.add(lblCCCD);
	    b14.add(Box.createHorizontalStrut(15));
	    
	    txtCCCD = new JTextField();
	    b14.add(txtCCCD);
	    
	    b14.add(Box.createHorizontalStrut(20));
	    b14.add(lblCaLam);
	    b14.add(Box.createHorizontalStrut(53));

	    
	    chkSang = new JCheckBox("Sáng");
	    b14.add(chkSang);
	    b14.add(Box.createHorizontalStrut(45));
	    
	    chkToi = new JCheckBox("Tối");
	    b14.add(chkToi);
	    
	    lblGioiTinh = new JLabel("Giới tính");
	    lblGioiTinh.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b15.add(lblGioiTinh);
	    b15.add(Box.createHorizontalStrut(40));
	    
	    rdoNam = new JRadioButton("Nam");
	    rdoNu = new JRadioButton("Nữ");
	    ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(rdoNam);
	    buttonGroup.add(rdoNu);
	    b15.add(rdoNam);
	    b15.add(Box.createHorizontalStrut(80));
	    b15.add(rdoNu);
	    b15.add(Box.createHorizontalStrut(20));
	    
	    lblLuongCB = new JLabel("Lương cơ bản");
	    lblLuongCB.setPreferredSize(lblCaLam.getPreferredSize());
	    b15.add(lblLuongCB);
	    
	    txtLuongCB = new JTextField();
	    b15.add(txtLuongCB);
	    
	    lblHeSoLuong = new JLabel("Hệ số lương");
	    lblAvt = new JLabel("\\Hình ảnh\\");
	    lblAvt.setPreferredSize(new Dimension(50, 110));
	    b2.add(lblAvt);
	    b2.add(Box.createVerticalStrut(5));
	    
	    lblBacLuong = new JLabel("Bậc lương");
	    lblBacLuong.setPreferredSize(lblHeSoLuong.getPreferredSize());
	    b21.add(lblBacLuong);
	    b21.add(Box.createHorizontalStrut(15));
	    
	    modelBacLuong = new DefaultComboBoxModel<String>();
	    cboBacLuong = new JComboBox<String>(modelBacLuong);
	    cboBacLuong.setPreferredSize(new Dimension(120,35));
	    b21.add(cboBacLuong);
	    b2.add(b21);
	    b2.add(Box.createVerticalStrut(10));
	    
	    
	    b22.add(lblHeSoLuong);
	    b22.add(Box.createHorizontalStrut(15));
	    
	    modelHeSoLuong = new DefaultComboBoxModel<String>();
	    cboHeSoLuong = new JComboBox<String>(modelHeSoLuong);
	    cboHeSoLuong.setPreferredSize(new Dimension(120, 35));
	    b22.add(cboHeSoLuong);
	    b2.add(b21);
	    b2.add(Box.createVerticalStrut(7));
	    b2.add(b22);
	    
	    
	    b31.add(Box.createHorizontalStrut(20));
		txtTimKiem = new JTextField("Nhập tên");	
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
                // Hiển thị lại placeholder text nếu TextField trống
                if (txtTimKiem.getText().isEmpty()) {
                	txtTimKiem.setText("Nhập tên");
                }
            }
        });
		b31.add(txtTimKiem);
				
		btnTimKiem = new JButton("Tìm");
		btnTimKiem.setPreferredSize(new Dimension(35,50));
		b31.add(btnTimKiem);
		b31.add(Box.createHorizontalStrut(50));
		
		modelNam = new DefaultComboBoxModel<String>();
		cboNam = new JComboBox<String>(modelNam);
		cboNam.setPreferredSize(new Dimension(120, 35));
		b31.add(cboNam);
		b31.add(Box.createHorizontalStrut(20));
		
		b32.add(Box.createHorizontalStrut(20));
		btnXemChiTiet = new JButton("Xem chi tiết");
		b32.add(btnXemChiTiet);
		btnXemChiTiet.setMaximumSize(new Dimension(140, 45));
		b32.add(Box.createHorizontalStrut(50));
		
		btnThem = new JButton("Thêm");
		b32.add(btnThem);
		btnThem.setMaximumSize(new Dimension(163, 45));
		b32.add(Box.createHorizontalStrut(20));
		
		b33.add(Box.createHorizontalStrut(20));
		btnSua = new JButton("Sửa");
		b33.add(btnSua);
		btnSua.setMaximumSize(new Dimension(160, 45));
		b33.add(Box.createHorizontalStrut(50));
		
		btnXoa = new JButton("Xóa");
		btnXoa.setMaximumSize(new Dimension(160, 45));
		b33.add(btnXoa);
		b33.add(Box.createHorizontalStrut(20));
		
		pnCenter = new JPanel();
		frame.getContentPane().add(pnCenter, BorderLayout.CENTER);
		String[] cols_datphong = {"Mã nhân viên", "Họ đệm", "Tên", "Tuổi", "Ngày sinh", "CCCD", "Giới tính", "SĐT", "Địa chỉ", "Ngày bắt đầu làm","Ca làm việc", "Bậc lương", "Lương cơ bản","Hệ số lương"};
        modelDsNV = new DefaultTableModel(cols_datphong, 0);
        tblDsNV = new JTable(modelDsNV);
        JScrollPane scrollPane = new JScrollPane(tblDsNV);
        pnCenter.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1220,420));
        tblDsNV.getSelectionModel().addListSelectionListener(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
