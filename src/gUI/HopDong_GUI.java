package gUI;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class HopDong_GUI implements ListSelectionListener {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtTenDoiTac, txtSanPham, txtSoLuong,txtTimKiem;
	private kDatePicker dpNgayKi,dpNgayThanhLi;
	private JButton btnTimKiem, btnXemChiTiet, btnThem, btnXoa, btnSua; 
	private DefaultComboBoxModel<String> modelNam;
	private JComboBox<String> cboNam;	
	private JPanel pnTop, pnCenter; 
	public DefaultTableModel modelDsHD;
    public JTable tblDsHD;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HopDong_GUI window = new HopDong_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public HopDong_GUI()  {
		frame = new JFrame("Hợp đồng");
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		contentPane.add(this.createGUI());
		
		frame.setContentPane(contentPane);
//		createMenuGUI();
	}


//	public void createMenuGUI(){
//		JMenuBar menuBar = new JMenuBar();
//		frame.setJMenuBar(menuBar);
//		
//		JMenu mnNewMenu = new JMenu("New menu");
//		menuBar.add(mnNewMenu);
//		
//		JMenu menuCN = new JMenu("Công nhân");
//		menuBar.add(menuCN);
//		
//		JMenu menuNV = new JMenu("Nhân viên");
//		menuBar.add(menuNV);
//		
//		JMenu menuCongDoan = new JMenu("Công đoạn");
//		menuBar.add(menuCongDoan);
//		
//		JMenu menuLuong = new JMenu("Lương");
//		menuBar.add(menuLuong);
//		
//		JMenu menuHopDong = new JMenu("Hợp đồng");
//		menuBar.add(menuHopDong);
//		
//		JMenu menuTroGiup = new JMenu("Trợ giúp");
//		menuBar.add(menuTroGiup);
//		
//		JMenu menuGioiThieu = new JMenu("Giới thiệu");
//		menuBar.add(menuGioiThieu);
//	}
	
	public JPanel createGUI() {
		JPanel pnlHD = new JPanel();
		pnlHD.setBounds(0, 50, 1268, 632);
		
		pnlHD.setLayout(new BorderLayout(0, 0));
	    pnTop = new JPanel();
	    pnlHD.add(pnTop, BorderLayout.NORTH);
	    pnTop.setBorder(new EmptyBorder(20, 20, 0, 20));
	    pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.X_AXIS));   
	    
	    Box b,b1,b2,b11,b12,b13,b21,b22,b23;
	    
	    b = Box.createHorizontalBox();
	    
	    b1 = Box.createVerticalBox();
	    b11 = Box.createHorizontalBox();
	    b12 = Box.createHorizontalBox();
	    b13 = Box.createHorizontalBox();
  
	    b2 = Box.createVerticalBox();
	    b21 = Box.createHorizontalBox();
	    b22 = Box.createHorizontalBox();
	    b23 = Box.createHorizontalBox();
	    
	    pnTop.add(b);
	    b.add(b1);
	    b.add(Box.createHorizontalStrut(10));
	    b.add(b2);
	    b1.add(b11);
	    b1.add(Box.createVerticalStrut(5));
	    b1.add(b12);
	    b1.add(Box.createVerticalStrut(5));
	    b1.add(b13);
	    b2.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
	    b1.setBorder(BorderFactory.createTitledBorder("Thông tin"));
	    b1.setPreferredSize(new Dimension(650,90));
	    
	    b11.setPreferredSize(new Dimension(200,33));
	    b12.setPreferredSize(new Dimension(200,33));
	    b13.setPreferredSize(new Dimension(200,33));
    
	    b2.add(b21);
	    b2.add(Box.createVerticalStrut(5));
	    b2.add(b22);
	    b2.add(Box.createVerticalStrut(5));
	    b2.add(b23);
	    b2.add(Box.createVerticalStrut(5));

	    b21.setPreferredSize(new Dimension(200,33));
	    b22.setPreferredSize(new Dimension(200,33));
	    b23.setPreferredSize(new Dimension(200,33));
	    
	    b11.add(Box.createHorizontalStrut(20));
	    JLabel lblTenDoiTac = new JLabel("Tên đối tác");
	    lblTenDoiTac.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    b11.add(lblTenDoiTac);
	    b11.add(Box.createHorizontalStrut(30));
	    
	    
	    txtTenDoiTac = new JTextField(3);
	    txtTenDoiTac.setPreferredSize(new Dimension(50,33));
	    txtTenDoiTac.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    b11.add(txtTenDoiTac);
	    b11.add(Box.createHorizontalStrut(40));
	    
	    JLabel lblNgayKi = new JLabel("Ngày kí");
	    b11.add(lblNgayKi);
	    b11.add(Box.createHorizontalStrut(40));
	    lblNgayKi.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    
	    dpNgayKi = new kDatePicker(100);
		dpNgayKi.setPreferredSize(new Dimension(100, 10));
		b11.add(dpNgayKi);
		b11.add(Box.createHorizontalStrut(20));

		b12.add(Box.createHorizontalStrut(20));
	    JLabel lblSanPham = new JLabel("Sản phẩm");
	    b12.add(lblSanPham);
	    lblSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    b12.add(Box.createHorizontalStrut(40));

	    txtSanPham = new JTextField(3);
	    b12.add(txtSanPham);
	    txtSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    txtSanPham.setPreferredSize(txtTenDoiTac.getPreferredSize());
	    b12.add(Box.createHorizontalStrut(37));
	    
		JLabel lblNgayThanhLi = new JLabel("Ngày thanh lí hợp đồng:");
		lblNgayThanhLi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		b12.add(lblNgayThanhLi);
		b12.add(Box.createHorizontalStrut(55));

		b13.add(Box.createHorizontalStrut(20));
	    JLabel lblSoLuong = new JLabel("Số lượng");
	    lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    b13.add(lblSoLuong);
	    b13.add(Box.createHorizontalStrut(50));
	    
	    txtSoLuong = new JTextField(3);
	    b13.add(txtSoLuong);
	    txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    b13.add(Box.createHorizontalStrut(40));
	    
		dpNgayThanhLi = new kDatePicker(190);
		dpNgayThanhLi.setPreferredSize(new Dimension(190, 40));
		b13.add(dpNgayThanhLi);
		b13.add(Box.createHorizontalStrut(20));
		
		b21.add(Box.createHorizontalStrut(20));
		txtTimKiem = new JTextField("Nhập tên");
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		b21.add(txtTimKiem);
				
		btnTimKiem = new JButton("");
		btnTimKiem.setPreferredSize(new Dimension(35,50));
		b21.add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		b21.add(Box.createHorizontalStrut(50));
		
		modelNam = new DefaultComboBoxModel<String>();
		cboNam = new JComboBox<String>(modelNam);
		cboNam.setPreferredSize(new Dimension(142,35));
		b21.add(cboNam);
		b21.add(Box.createHorizontalStrut(20));
		
		b22.add(Box.createHorizontalStrut(20));
		btnXemChiTiet = new JButton("Xem chi tiết");
		b22.add(btnXemChiTiet);
		btnXemChiTiet.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		btnXemChiTiet.setMaximumSize(new Dimension(148,45));
		b22.add(Box.createHorizontalStrut(50));
		
		btnThem = new JButton("Thêm");
		b22.add(btnThem);
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setMaximumSize(new Dimension(148,45));
		b22.add(Box.createHorizontalStrut(20));
		
		b23.add(Box.createHorizontalStrut(20));
		btnSua = new JButton("Sửa");
		b23.add(btnSua);
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnSua.setMaximumSize(new Dimension(265,45));
		b23.add(Box.createHorizontalStrut(50));
		
		btnXoa = new JButton("Xóa");
		btnXoa.setMaximumSize(new Dimension(265,45));
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		b23.add(btnXoa);
		b23.add(Box.createHorizontalStrut(20));

		
		pnCenter = new JPanel();
		pnlHD.add(pnCenter, BorderLayout.CENTER);
		String[] cols_datphong = {"Mã hợp đồng", "Tên đối tác", "Tên sản phẩm", "Ngày kí", "Ngày thanh lí"};
        modelDsHD = new DefaultTableModel(cols_datphong, 0);
        tblDsHD = new JTable(modelDsHD);
        JScrollPane scrollPane = new JScrollPane(tblDsHD);
        pnCenter.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1220,450));
        tblDsHD.getSelectionModel().addListSelectionListener(this);

        return pnlHD;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
