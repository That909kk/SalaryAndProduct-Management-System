package gUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.Xuong_DAO;
import dao.CongNhan_DAO;
import entity.Xuong;
import entity.CongNhan;

public class CongNhan_GUI implements MouseListener, ActionListener {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel pnTop, pnCenter;
	private JLabel lblHoDem, lblTen, lblNgaySinh, lblCCCD, lblGioiTinh, lblSDT, lblDiaChi, lblBatDauLam, lblCaLam, lblChuyenMon, lblAvt, lblXuong, lblTrangThai;
	private JTextField txtHoDem, txtTen, txtCCCD, txtSDT, txtDiaChi, txtChuyenMon,txtTimKiem;
	private kDatePicker dpNgaySinh,dpBatDauLam;
	private JCheckBox chkSang, chkToi, chkThem, chkTrangThai;
	private JRadioButton rdoNam, rdoNu; 
	private DefaultComboBoxModel<String> modelBacLuong, modelHeSoLuong;
	private JComboBox<String> cboBacLuong, cboHeSoLuong;
	private JButton btnTimKiem, btnXemChiTiet, btnThem, btnXoa, btnSua; 
	private DefaultComboBoxModel<String> modelNam;
	private JComboBox<String> cboNam;
	public DefaultTableModel modelDsCN;
    public JTable tblDsCN;
    private CongNhan_DAO congNhanDao;
    private static ArrayList<CongNhan> dsCN;
	private DefaultComboBoxModel<String> modelXuong;
	private JComboBox<String> cboXuong;
	private Xuong_DAO xuongDao;
	private ArrayList<Xuong> dsX;
	private ArrayList<CongNhan> dsCNX;
	private String ma;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CongNhan_GUI window = new CongNhan_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CongNhan_GUI() {
		frame = new JFrame("Nhân viên");
		frame.setSize(1280,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		
		frame.setContentPane(contentPane);
		
		contentPane.add(this.createGUI());
	}
	
	public JPanel createGUI() {
		
		try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		congNhanDao = new CongNhan_DAO();
		dsCN = congNhanDao.getListCN();
		xuongDao = new Xuong_DAO();
		dsX = xuongDao.getDSXuong();
		dsCNX = new ArrayList<CongNhan>();
		
		JPanel pnlCN = new JPanel();
		pnlCN.setBounds(0, 50, 1268, 632);;
		pnlCN.setLayout(new BorderLayout(0, 0));
		
		pnlCN.setLayout(new BorderLayout(0, 0));
		pnTop = new JPanel();
		pnlCN.add(pnTop, BorderLayout.NORTH);
	    pnTop.setBorder(new EmptyBorder(20, 20, 0, 20));
	    pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.X_AXIS));
	    
	    Box b,b1,b2,b3,b11,b12,b13,b14,b15,b21,b22,b23,b31,b32,b33;
	    
	    
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
	    b23 = Box.createHorizontalBox();
	    
	    b21.setPreferredSize(new Dimension(200, 30));
	    b22.setPreferredSize(new Dimension(200,33));
	    b23.setPreferredSize(new Dimension(200,33));
	    
	    b31 = Box.createHorizontalBox();
	    b32 = Box.createHorizontalBox();
	    b33 = Box.createHorizontalBox();
	    
	    b.add(b1);
	    b.add(Box.createHorizontalStrut(30));
	    
	    
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
	    lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblCaLam = new JLabel("Ca làm               ");
	    lblCaLam.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblHoDem = new JLabel("Họ đệm");
	    lblHoDem.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblHoDem.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b11.add(lblHoDem);
	    b11.add(Box.createHorizontalStrut(15));
	    
	    txtHoDem = new JTextField();
	    txtHoDem.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    txtHoDem.setPreferredSize(new Dimension(20,36));
	    b11.add(txtHoDem);
	    b11.add(Box.createHorizontalStrut(20));
	    
	    lblSDT = new JLabel("Số điện thoại");
	    lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblSDT.setPreferredSize(lblCaLam.getPreferredSize());
	    b11.add(lblSDT);
	    
	    txtSDT = new JTextField();
	    txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    txtSDT.setPreferredSize(txtHoDem.getPreferredSize());
	    b11.add(txtSDT);
	    
	    lblTen = new JLabel("Tên");
	    lblTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTen.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b12.add(lblTen);
	    b12.add(Box.createHorizontalStrut(15));
	    
	    txtTen = new JTextField();
	    txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    txtTen.setPreferredSize(txtHoDem.getPreferredSize());
	    b12.add(txtTen);
	    b12.add(Box.createHorizontalStrut(20));
	    
	    lblDiaChi = new JLabel("Địa chỉ");
	    lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblDiaChi.setPreferredSize(lblCaLam.getPreferredSize());
	    b12.add(lblDiaChi);
	    
	    txtDiaChi = new JTextField();
	    txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    txtDiaChi.setPreferredSize(txtHoDem.getPreferredSize());
	    b12.add(txtDiaChi);
	    
	    
	    b13.add(lblNgaySinh);
	    b13.add(Box.createHorizontalStrut(15));
	    
	    dpNgaySinh = new kDatePicker(192);
	    dpNgaySinh.setPreferredSize(new Dimension(193,35));
	    b13.add(dpNgaySinh);
	    
	    b13.add(Box.createHorizontalStrut(20));
	    lblBatDauLam = new JLabel("Bắt đầu làm");
	    lblBatDauLam.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblBatDauLam.setPreferredSize(lblCaLam.getPreferredSize());
	    b13.add(lblBatDauLam);
	    
	    dpBatDauLam = new kDatePicker(193);
	    dpBatDauLam.setPreferredSize(new Dimension(193,35));
	    b13.add(dpBatDauLam);
	    
	    lblCCCD = new JLabel("CCCD");
	    lblCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblCCCD.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b14.add(lblCCCD);
	    b14.add(Box.createHorizontalStrut(15));
	    
	    txtCCCD = new JTextField();
	    txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    b14.add(txtCCCD);
	    
	    b14.add(Box.createHorizontalStrut(45));
	    b14.add(lblCaLam);
	    b14.add(Box.createHorizontalStrut(5));

	    
	    chkSang = new JCheckBox("Sáng");
	    chkSang.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    b14.add(chkSang);
	    b14.add(Box.createHorizontalStrut(30));
	    
	    chkToi = new JCheckBox("Tối");
	    chkToi.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    b14.add(chkToi);
	    
	    chkThem = new JCheckBox("Thêm");
	    chkThem.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    b14.add(chkThem);
	    
	    lblGioiTinh = new JLabel("Giới tính");
	    lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblGioiTinh.setPreferredSize(lblNgaySinh.getPreferredSize());
	    b15.add(lblGioiTinh);
	    b15.add(Box.createHorizontalStrut(40));
	    
	    rdoNam = new JRadioButton("Nam");
	    rdoNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    rdoNu = new JRadioButton("Nữ");
	    rdoNu.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    ButtonGroup buttonGroup = new ButtonGroup();
	    buttonGroup.add(rdoNam);
	    buttonGroup.add(rdoNu);
	    b15.add(rdoNam);
	    b15.add(Box.createHorizontalStrut(60));
	    b15.add(rdoNu);
	    b15.add(Box.createHorizontalStrut(35));
	    
	    lblChuyenMon = new JLabel("Chuyên môn");
	    lblChuyenMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblChuyenMon.setPreferredSize(lblCaLam.getPreferredSize());
	    b15.add(lblChuyenMon);
	    
	    txtChuyenMon = new JTextField();
	    txtChuyenMon.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    b15.add(txtChuyenMon);
	    
	    lblAvt = new JLabel("\\Hình ảnh\\");
	    lblAvt.setPreferredSize(new Dimension(50, 120));
	    b2.add(lblAvt);
	    b2.add(Box.createVerticalStrut(5));
	
	    ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (source == chkSang) {
                    	chkToi.setSelected(false);
                        
                    } else if (source == chkToi) {
                    	chkSang.setSelected(false);
                    } 
                    
                }
            }
        };
        
        chkSang.addItemListener(itemListener);
        chkToi.addItemListener(itemListener);
	    
	    lblTrangThai = new JLabel("Trạng thái");
	    lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblTrangThai.setPreferredSize(lblTrangThai.getPreferredSize());
	    chkTrangThai = new JCheckBox("Đang làm");
	    chkTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    chkTrangThai.setPreferredSize(chkTrangThai.getPreferredSize());
	    b22.add(lblTrangThai);
	    b22.add(Box.createHorizontalStrut(30));
	    b22.add(chkTrangThai);
	    b2.add(b22);
	    b22.add(Box.createHorizontalStrut(85));
        
	    modelXuong = new DefaultComboBoxModel<String>();
	    cboXuong = new JComboBox<String>(modelXuong);
	    
	    lblXuong = new JLabel("Xưởng");
	    lblXuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblXuong.setPreferredSize(lblXuong.getPreferredSize());

//	    cboXuong.addItemListener(new ItemListener() {
//			
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//            	String tatCa = String.valueOf(cboXuong.getSelectedItem()); 
//            	if(tatCa.equals("Tất cả")) {
//            		if(String.valueOf(cboNam.getSelectedItem())!= "Tất cả") {
//            			dsCN = congNhanDao.getListCNtheoNamVaoLam(Integer.valueOf((String)cboNam.getSelectedItem()));
//            			if(dsCNX.isEmpty() == true) {						
////							cboBoPhan.setSelectedItem("Tất cả");
////							JOptionPane.showMessageDialog(frame, "Không tìm thấy nhân viên nào");
//							modelDsCN.setRowCount(0);
//            				return;
//            			}
//            			clearTable();
//            			docDuLieuVaoTable(dsCN);
//            			return;
//            		}
//            		else if(String.valueOf(cboNam.getSelectedItem())== "Tất cả"){
//            			dsCN = congNhanDao.getListCN();
//            			if(dsCNX.isEmpty() == true) {						
////							cboBoPhan.setSelectedItem("Tất cả");
////							JOptionPane.showMessageDialog(frame, "Không tìm thấy nhân viên nào");
//            				modelDsCN.setRowCount(0);
//            				return;
//            			}
//            			clearTable();
//                		docDuLieuVaoTable(dsCN);
//                		return;
//            		}
//            		
//            	}
//            	else {
//            		if(String.valueOf(cboNam.getSelectedItem())!= "Tất cả") {
//            			dsCNX = congNhanDao.getListNVtheoNamXuong(Integer.valueOf((String)cboNam.getSelectedItem()), dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
//            			if(dsCNX.isEmpty() == true) {
////							cboNam.setSelectedItem("Tất cả");
////							JOptionPane.showMessageDialog(frame, "Không tìm thấy nhân viên nào");
//            				modelDsCN.setRowCount(0);
//            				return;
//            			}
//            			else {
//            				clearTable();
//                			docDuLieuVaoTable(dsCNX); 		
//                			return;
//            			}
//            
//            			
//            		}
//            		else {
//            			
//            			dsCNX = congNhanDao.getListCNtheoXuong(dsX.get(cboXuong.getSelectedIndex()).getMaXuong());	
//                    	if(dsCNX.isEmpty() == true) {						
////							cboBoPhan.setSelectedItem("Tất cả");
////							JOptionPane.showMessageDialog(frame, "Không tìm thấy nhân viên nào");
//                    		modelDsCN.setRowCount(0);
//            				return;
//            			}
//                    	clearTable();
//                    	docDuLieuVaoTable(dsCNX);
//                		return;
//            		}
//            		
//            	}
//            	  		
//            }	
//				
//		});

	    
	    b23.add(lblXuong);
	    b23.add(Box.createHorizontalStrut(15));
	    b23.add(cboXuong);
	    b2.add(b23);
	    
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
                if (txtTimKiem.getText().isEmpty()) {
                	txtTimKiem.setText("Nhập tên");
                }
            }
        });
		b31.add(txtTimKiem);
				
		btnTimKiem = new JButton("");
		btnTimKiem.setPreferredSize(new Dimension(35,50));
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setIcon(new ImageIcon("img\\icons\\icons8-magnifying-glass-20.png"));
		btnTimKiem.setBackground(new Color(255, 255, 255));
		b31.add(btnTimKiem);
		b31.add(Box.createHorizontalStrut(50));
		
		modelNam = new DefaultComboBoxModel<String>();
		cboNam = new JComboBox<String>(modelNam);
		cboNam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboNam.setPreferredSize(new Dimension(120, 35));
		
		modelNam.addElement("Tất cả");
		modelNam.setSelectedItem("Tất cả");
		
		xuongDao = new Xuong_DAO();
        modelXuong.removeAllElements();
	    modelXuong.addElement("Tất cả");
	    modelXuong.setSelectedItem("Tất cả");
        for (Xuong x : xuongDao.getDSXuong()) {
			modelXuong.addElement(x.getMaXuong());
		}
		
		cboNam.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(String.valueOf(cboNam.getSelectedItem())!= "Tất cả" ) {
					if(String.valueOf(cboXuong.getSelectedItem()).equals("Tất cả")) {
						dsCN= congNhanDao.getListCNtheoNamVaoLam(Integer.valueOf((String) cboNam.getSelectedItem()));
						if(dsCN.isEmpty() == true) {						
							modelDsCN.setRowCount(0);
            				return;
            			}
						clearTable();
						docDuLieuVaoTable(dsCN);
						return;
						
					}
					else {
						dsCNX = congNhanDao.getListNVtheoNamXuong(Integer.valueOf((String) cboNam.getSelectedItem()), dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
						if(dsCNX.isEmpty() == true) {						
							modelDsCN.setRowCount(0);
            				return;
            			}
						else {
							clearTable();
							docDuLieuVaoTable(dsCNX);
							return;
						}
						
					}
				}
				else {
					if(String.valueOf(cboNam.getSelectedItem()) == "Tất cả" ) {
						dsCN = congNhanDao.getListCN();
						if(dsCN.isEmpty() == true) {						
							modelDsCN.setRowCount(0);
            				return;
            			}
						clearTable();
						docDuLieuVaoTable(dsCN);
						return;
					}
					else {
						int viTri = cboXuong.getSelectedIndex();
		            	dsCNX = congNhanDao.getListCNtheoXuong(dsX.get(viTri).getMaXuong());
		            	if(dsCNX.isEmpty() == true) {						
//							cboBoPhan.setSelectedItem("Tất cả");
//							JOptionPane.showMessageDialog(frame, "Không tìm thấy nhân viên nào");
							modelDsCN.setRowCount(0);
            				return;
            			}
		            	clearTable();
		            	docDuLieuVaoTable(dsCNX);
		            	return;
					}
					
					
				}
				
			}
		});
			
		b31.add(cboNam);
		b31.add(Box.createHorizontalStrut(20));
		
		b32.add(Box.createHorizontalStrut(20));
		btnXemChiTiet = new JButton("Xem chi tiết");
		b32.add(btnXemChiTiet);
		btnXemChiTiet.setMaximumSize(new Dimension(140, 45));
		btnXemChiTiet.setIcon(new ImageIcon("img\\icons\\icons8-info-20.png"));
		btnXemChiTiet.setBackground(new Color(255, 255, 255));
		b32.add(Box.createHorizontalStrut(50));
		btnXemChiTiet.setEnabled(false);
		
		btnThem = new JButton("Thêm");
		b32.add(btnThem);
		btnThem.setMaximumSize(new Dimension(140, 45));
		btnThem.setIcon(new ImageIcon("img\\icons\\icons8-add-user-20.png"));
		btnThem.setBackground(new Color(255, 255, 255));
		b32.add(Box.createHorizontalStrut(20));
		
		b33.add(Box.createHorizontalStrut(20));
		btnSua = new JButton("Sửa");
		b33.add(btnSua);
		btnSua.setMaximumSize(new Dimension(160, 45));
		btnSua.setIcon(new ImageIcon("img\\icons\\icons8-pencil-20.png"));
		btnSua.setBackground(new Color(255, 255, 255));
		b33.add(Box.createHorizontalStrut(50));
		
		btnXoa = new JButton("Xóa");
		btnXoa.setMaximumSize(new Dimension(140, 45));
		b33.add(btnXoa);
		btnXoa.setIcon(new ImageIcon("img\\icons\\icons8-delete-20.png"));
		btnXoa.setBackground(new Color(255, 255, 255));
		b33.add(Box.createHorizontalStrut(20));
		
		pnCenter = new JPanel();
		pnlCN.add(pnCenter, BorderLayout.CENTER);
		String[] cols_datphong = {"Mã công nhân", "Họ đệm", "Tên", "Tuổi", "Ngày sinh", "CCCD", "Giới tính", "SĐT", "Địa chỉ", "Ngày bắt đầu làm","Ca làm việc", "Chuyên môn", "Xưởng", "Trạng thái"};
        modelDsCN = new DefaultTableModel(cols_datphong, 0);
        tblDsCN = new JTable(modelDsCN);
        tblDsCN.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblDsCN.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(tblDsCN);
        pnCenter.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1220,400));
        TableColumn column = tblDsCN.getColumnModel().getColumn(13);
        column.setPreferredWidth(100);
        dsCNX = congNhanDao.getListCNtheoXuong(dsX.get(0).getMaXuong());
        docDuLieuVaoTable(dsCNX);
        
        tblDsCN.addMouseListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnTimKiem.addActionListener(this);
        
        
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Làm mới");


        
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		txtTen.setText("");
        		txtHoDem.setText("");
        		txtSDT.setText("");
        		txtDiaChi.setText("");
        		txtCCCD.setText("");
        		txtChuyenMon.setText("");
        	
        		rdoNam.setSelected(false);
        		rdoNu.setSelected(false);
        		
        		chkSang.setSelected(false);
        		chkToi.setSelected(false);
        		
        	
        		dpNgaySinh.setValueToDay();
        		dpBatDauLam.setValueToDay();
            }
        });
        
        popupMenu.add(menuItem1);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        
        
        return pnlCN;
        
       
	}
	
	public void docDuLieuVaoTable(ArrayList<CongNhan> dsCN) {
		 for(CongNhan cn: dsCN) {
			 String tenXuong="" ;
				for(Xuong x: dsX) {
					if(x.getMaXuong().equals(cn.getXuong().getMaXuong())) {
						tenXuong = x.getTenXuong();
				}
			}
	        	Object[] rowData = {cn.getMaCN(),cn.getHo(),cn.getTen(),tinhTuoi(cn),cn.getNgaySinh(),cn.getcCCD(),laGioiTinh(cn),cn.getSoDienThoai(),cn.getDiaChi(),cn.getNgayBatDauLamViec(),layCaLamViec(cn),cn.getChuyenMon(),tenXuong};
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
	
	public static String phatSinhMaCN(LocalDate today) {
        // Lấy 2 số cuối của năm hiện tại
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        String maMax="";
        String year = yearFormat.format(Date.valueOf(today));
        int count =0;
        for(CongNhan cn: dsCN) {
        	if(cn.getNgayBatDauLamViec().getYear()== today.getYear()) {
        		count++;
        		maMax = cn.getMaCN();
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
	public void mouseClicked(MouseEvent e) {
		int row= tblDsCN.getSelectedRow();
		
		String boPhan = String.valueOf(cboXuong.getSelectedItem());
	

		 if(boPhan.equals("Bộ phận kế toán")||boPhan.equals("Bộ phận nhân sự")||boPhan.equals("Quản lý Xưởng")) {
			 {
					 if(row!= -1) {
							CongNhan cn = dsCNX.get(row);
							ma = cn.getMaCN();
							txtTen.setText(cn.getTen());
							txtHoDem.setText(cn.getHo());
							txtSDT.setText(cn.getSoDienThoai());
							txtDiaChi.setText(cn.getDiaChi());
							txtCCCD.setText(cn.getcCCD());
							txtChuyenMon.setText(String.valueOf(cn.getLuongCoBan()));
							if(cn.isGioiTinh()==true) {
								rdoNam.setSelected(true);
							}
							else
								rdoNu.setSelected(true);
							if(cn.getCaLamViec()==1) {
								chkSang.setSelected(true);
								chkToi.setSelected(false);
							}else {
								chkSang.setSelected(false);
								chkToi.setSelected(true);
								
							}
							dpNgaySinh.setValue(Date.valueOf(cn.getNgaySinh()));
							dpBatDauLam.setValue(Date.valueOf(cn.getNgayBatDauLamViec()));
							
						}
					 
				 
			 }
			
		}

		if(boPhan.equals("Tất cả")) {
			if(row!= -1) {
				CongNhan cn = dsCN.get(row);
				ma = cn.getMaCN();
				txtTen.setText(cn.getTen());
				txtHoDem.setText(cn.getHo());
				txtSDT.setText(cn.getSoDienThoai());
				txtDiaChi.setText(cn.getDiaChi());
				txtCCCD.setText(cn.getcCCD());
				txtChuyenMon.setText(String.valueOf(cn.getLuongCoBan()));
				if(cn.isGioiTinh()==true) {
					rdoNam.setSelected(true);
				}
				else
					rdoNu.setSelected(true);
				if(cn.getCaLamViec()==1) {
					chkSang.setSelected(true);
					chkToi.setSelected(false);
				}else {
					chkSang.setSelected(false);
					chkToi.setSelected(true);
					
				}
				dpNgaySinh.setValue(Date.valueOf(cn.getNgaySinh()));
				dpBatDauLam.setValue(Date.valueOf(cn.getNgayBatDauLamViec()));		
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
			
			String regexLuongCB = "^[-+]?\\d*\\.?\\d+$";
			if(txtChuyenMon.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống lương");
				return;
			}
			if(Pattern.matches(regexLuongCB, txtChuyenMon.getText())==false) {
				JOptionPane.showMessageDialog( frame, "Lương phải là một số tiền");
				return;	
			}
			double  luongCB = Double.valueOf(txtChuyenMon.getText());
			
			LocalDate ngaySinh=null;
			try {
				ngaySinh = dpNgaySinh.getFullDate().toLocalDate();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(tinhTuoiTheoNS(ngaySinh) < 18) {
				JOptionPane.showMessageDialog(frame, "Công nhân chưa đủ 18 tuổi");
				return;
			}

			
			
			if(String.valueOf(cboXuong.getSelectedItem()).equals("Tất cả")) {
				JOptionPane.showMessageDialog(frame, "Bạn chưa chọn xưởng cho công nhân");
			}
			
			LocalDate ngayBatDauLam = null;
			try {
				ngayBatDauLam = dpBatDauLam.getFullDate().toLocalDate();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if((rdoNam.isSelected() == false)&&(rdoNu.isSelected()==false)) {
				JOptionPane.showMessageDialog( frame, "Bạn chưa chọn giới tính");
				return;	
			}
			
			String maNV= phatSinhMaCN(ngayBatDauLam);
			Boolean gioiTinh=null;
			if(rdoNam.isSelected() == true) {
				gioiTinh = true;
			}else if(rdoNu.isSelected()==true)
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
			String maXuong = dsX.get(cboXuong.getSelectedIndex()).getMaXuong();
			Xuong xuong = new Xuong(maXuong);
			CongNhan cn = new CongNhan(maNV, null, hoDem, ten, gioiTinh, ngaySinh, cccd, diaChi, sdt,
					txtChuyenMon.getText().trim(), caLam, 500000, luongCB, ngayBatDauLam, xuong);
			congNhanDao.insertCN(cn);
			dsCN = congNhanDao.getListCN();
			clearTable();
			dsCNX = congNhanDao.getListCNtheoXuong(dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
			docDuLieuVaoTable(dsCNX);
				
				
		}
		if(o==btnXoa) {
			int row = tblDsCN.getSelectedRow();
			ma = modelDsCN.getValueAt(row, 0).toString();
			int xacNhan;
			
			String boPhan = String.valueOf(cboXuong.getSelectedItem());
			String nam = String.valueOf(cboNam.getSelectedItem());
			if(boPhan.equals("Tất cả")){
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
					xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn xóa công nhân có mã là " +dsCNX.get(row).getMaCN()+" và có tên là "+dsCNX.get(row).getHo()+" "+ dsCNX.get(row).getTen()+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION) {
						congNhanDao.deleteCN(ma);
						dsCNX = congNhanDao.getListCNtheoXuong(dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
						dsCN = congNhanDao.getListCNtheoNamVaoLam(Integer.valueOf(nam));
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
					xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn xóa công nhân có mã là " +dsCNX.get(row).getMaCN()+" và có tên là "+dsCNX.get(row).getHo()+" "+ dsCNX.get(row).getTen()+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
					if(xacNhan == JOptionPane.YES_OPTION) {
						congNhanDao.deleteCN(ma);
						dsCNX = congNhanDao.getListNVtheoNamXuong(Integer.valueOf(nam),dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
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
			
			String regexLuongCB = "^[-+]?\\d*\\.?\\d+$";
			if(txtChuyenMon.equals("")) {
				JOptionPane.showMessageDialog( frame, "Không được để trống lương");
				return;
			}
			if(Pattern.matches(regexLuongCB, txtChuyenMon.getText())==false) {
				JOptionPane.showMessageDialog( frame, "Lương phải là một số tiền");
				return;	
			}
			double  luongCB = Double.valueOf(txtChuyenMon.getText());
			
			LocalDate ngaySinh=null;
			try {
				ngaySinh = dpNgaySinh.getFullDate().toLocalDate();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(tinhTuoiTheoNS(ngaySinh) < 18) {
				JOptionPane.showMessageDialog(frame, "Công nhân chưa đủ 18 tuổi");
				return;
			}

			
			
			if(String.valueOf(cboXuong.getSelectedItem()).equals("Tất cả")) {
				JOptionPane.showMessageDialog(frame, "Bạn chưa chọn bộ phận cho nhân viên");
			}
			
			LocalDate ngayBatDauLam = null;
			try {
				ngayBatDauLam = dpBatDauLam.getFullDate().toLocalDate();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if((rdoNam.isSelected() == false)&&(rdoNu.isSelected()==false)) {
				JOptionPane.showMessageDialog( frame, "Bạn chưa chọn giới tính");
				return;	
			}
			
			Boolean gioiTinh=null;
			if(rdoNam.isSelected() == true) {
				gioiTinh = true;
			}else if(rdoNu.isSelected()==true)
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
			
			String maXuong = dsX.get(cboXuong.getSelectedIndex()).getMaXuong();
			Xuong xuong = new Xuong(maXuong);
			CongNhan cn = new CongNhan(ma);
			
			int xacNhan = JOptionPane.showConfirmDialog(frame, "Bạn có muốn sửa công nhân có mã là " +ma+" và có tên là "+hoDem+" "+ten+" không?","Xác nhận",JOptionPane.YES_NO_OPTION);
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
