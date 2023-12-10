package gUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import connectDB.ConnectDB;
import dao.BangChamCongCN_DAO;
import dao.Xuong_DAO;
import dao.CongDoan_DAO;
import dao.CongNhan_DAO;
import entity.BangChamCongCN;
import entity.Xuong;
import entity.CongNhan;
import entity.SanPham;
import entity.CongDoan;

public class ChamCongCN_GUI implements ListSelectionListener, ActionListener {
	private JPanel contentPane;
	private JFrame frame;
	private kDatePicker dpNgayChamCong;
	private JLabel lblNgayCC,lblXuong,lblCaLam,lblGhiChu, lblSanPhamSanXuat, lblCongDoan;
	private JTextField txtGhiChu,txtTimKiemTen;
	private DefaultComboBoxModel<String> modelXuong, modelSanPhamSanXuat, modelCongDoan;
	private JComboBox<String> cboXuong, cboSanPhamSanXuat, cboCongDoan;
	private JTextField textField;
	private JCheckBox chkCaSang,chkCaToi,chkThem;
	public DefaultTableModel modelDsCN;
    public JTable tblDsCN;
    private JButton btnTimKiemTen,btnCapNhat,btnHoanTat;
    private CongNhan_DAO congNhanDao;
    private Xuong_DAO xuongDao;
    private ArrayList<Xuong> dsX;
    private ArrayList<CongNhan> dsCNXCa;
    private BangChamCongCN_DAO chamCongCNDao;
    private ArrayList<BangChamCongCN> dsDaChamCong;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChamCongCN_GUI window = new ChamCongCN_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ChamCongCN_GUI() {
		frame = new JFrame("Chấm công công nhân");
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



	protected JPanel createGUI() {
		 JPanel pnlCCCN = new JPanel();
		 pnlCCCN.setBackground(new Color(240, 248, 255));
		 pnlCCCN.setBounds(0, 50, 1268, 632);
		
		 try {
	            ConnectDB.getInstance().connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		Xuong_DAO xuongDao = new Xuong_DAO();
		chamCongCNDao = new BangChamCongCN_DAO();
		
		
		dsX = xuongDao.getDSXuong();
		dsCNXCa = new ArrayList<CongNhan>();
		congNhanDao = new CongNhan_DAO();
		dsDaChamCong = new ArrayList<BangChamCongCN>();
		
		 
		pnlCCCN.setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1000,40));;
		pnlCCCN.add(topPanel, BorderLayout.NORTH);
	
		
		Box b;
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		b =  Box.createHorizontalBox();
		topPanel.add(b);
		topPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
	
		
		lblNgayCC = new JLabel("Ngày chấm công");
		dpNgayChamCong = new kDatePicker(120);
		dpNgayChamCong.setPreferredSize(new Dimension(120, 40));
		//lblSanPhamSanXuat = new JLabel("Sản phẩm sản xuất");
		//lblCongDoan = new JLabel("Công đoạn");
		lblXuong = new JLabel("Xưởng");
		modelXuong = new DefaultComboBoxModel<String>();
		dpNgayChamCong.txt.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try {
					chkCaSang.setSelected(true);
					chkCaToi.setSelected(false);
					LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
					if(ngayChamCong.isAfter(LocalDate.now())) {
						
						JOptionPane.showMessageDialog(frame, "Chưa đến ngày làm không thể chấm công");
					}
					else if(ngayChamCong.isBefore(LocalDate.now())) {
						dsDaChamCong = chamCongCNDao.getBangCCTheoNgay(ngayChamCong);
						if(boPhanDaChamCongChua(dsCNXCa)) {
    						clearTable();
    						docDuLieuTableDaCC(dsCNXCa);
    						btnHoanTat.setEnabled(false);
    					}
    					else{
    						clearTable();
    						docDuLieuVaoTable(dsCNXCa);
    						btnHoanTat.setEnabled(true);
    					}	
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				
			}
		});
		
		//String sp[] = {"Áo sơ mi trắng","Áo thun đen thời thượng","Quần jeans màu xanh dương","Quần short thể thao màu xanh lam"};
		//cboSanPhamSanXuat = new JComboBox(sp);
		
		//String cd[] = {"May vá","Đóng gói","Nhuộm"};
		//cboCongDoan = new JComboBox(cd);
		
		for(Xuong x: dsX) {
			modelXuong.addElement(x.getTenXuong());
			
		}
		
	    cboXuong = new JComboBox<String>(modelXuong);
	    cboXuong.setPreferredSize(new Dimension(100,40));
	    cboXuong.setSelectedItem(dsX.get(0).getMaXuong());

	    try {
			dsDaChamCong = chamCongCNDao.getBangCCTheoNgay(dpNgayChamCong.getFullDate().toLocalDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    cboXuong.addItemListener(new ItemListener() {
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int xuongIndex = cboXuong.getSelectedIndex();
				try {
					LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
					if(chkCaSang.isSelected()==true) {
						dsCNXCa = congNhanDao.getListCntheoXuongCa(1,dsX.get(xuongIndex).getMaXuong());
						dsDaChamCong = chamCongCNDao.getBangCCTheoNgay(ngayChamCong);
						if(boPhanDaChamCongChua(dsCNXCa)) {
							clearTable();
							docDuLieuTableDaCC(dsCNXCa);
							btnHoanTat.setEnabled(false);
						}
						else {
							clearTable();
							docDuLieuVaoTable(dsCNXCa);
							btnHoanTat.setEnabled(true);
						}
						
					}
					else if(chkCaToi.isSelected()==true) {
						dsCNXCa = congNhanDao.getListCntheoXuongCa(2,dsX.get(xuongIndex).getMaXuong());
						dsDaChamCong = chamCongCNDao.getBangCCTheoNgay(ngayChamCong);
						if(boPhanDaChamCongChua(dsCNXCa)) {
							clearTable();
							docDuLieuTableDaCC(dsCNXCa);
							btnHoanTat.setEnabled(false);
						}
						
						else {
							clearTable();
							docDuLieuVaoTable(dsCNXCa);
							btnHoanTat.setEnabled(true);
						}
						
					}
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
			}
		});
	    
	    lblCaLam = new JLabel("Ca Làm:");
        chkCaSang = new JCheckBox("Ca sáng"); 
        chkCaToi = new JCheckBox("Ca Tối");
        chkThem = new JCheckBox("Thêm");
        chkCaSang.setSelected(true);
        chkCaToi.setSelected(false);
        
        chkCaSang.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED && !chkCaToi.isSelected()) {
                	chkCaToi.setSelected(true);
                }
            }
        });
        
        chkCaToi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED && !chkCaSang.isSelected()) {
                	chkCaSang.setSelected(true);
                }
            }
        });
        
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();

                if (e.getStateChange() == ItemEvent.SELECTED) {
                	LocalDate ngayChamCong;
					try {
						ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
						if (source == chkCaSang) {
	                        chkCaToi.setSelected(false);
	                        chkThem.setSelected(false);
	                        dsCNXCa = congNhanDao.getListCntheoXuongCa(1,dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
	    					dsDaChamCong = chamCongCNDao.getBangCCTheoNgay(ngayChamCong);
	    					if(boPhanDaChamCongChua(dsCNXCa)) {
	    						clearTable();
	    						docDuLieuTableDaCC(dsCNXCa);
	    						btnHoanTat.setEnabled(false);
	    					}
	    					else{
	    						clearTable();
	    						docDuLieuVaoTable(dsCNXCa);
	    						btnHoanTat.setEnabled(true);
	    					}
	  
	                    } else if (source == chkCaToi) {
	                        chkCaSang.setSelected(false);
	                        chkThem.setSelected(false);
	                        dsCNXCa = congNhanDao.getListCntheoXuongCa(2,dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
	                        if(boPhanDaChamCongChua(dsCNXCa)) {
	    						clearTable();
	    						docDuLieuTableDaCC(dsCNXCa);
	    						btnHoanTat.setEnabled(false);
	    					}
	    					else{
	    						clearTable();
	    						docDuLieuVaoTable(dsCNXCa);
	    						btnHoanTat.setEnabled(true);
	    					}
	                    } 
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
//                    else if (source == chkThem) {
//                        chkCaSang.setSelected(false);
//                        chkCaToi.setSelected(false);
//                    }
                   
         
                }
            }
        };
        
        chkCaSang.addItemListener(itemListener);
        chkCaToi.addItemListener(itemListener);
        chkThem.addItemListener(itemListener);
        
        lblGhiChu = new JLabel("Ghi chú");
        txtGhiChu = new JTextField();
        
        b.add(Box.createHorizontalStrut(30));
        b.add(lblNgayCC);
        b.add(Box.createHorizontalStrut(50));
        b.add(dpNgayChamCong);
        //b.add(Box.createHorizontalStrut(30));
        //b.add(lblSanPhamSanXuat);
        //b.add(Box.createHorizontalStrut(30));
        //b.add(cboSanPhamSanXuat);
        //b.add(Box.createHorizontalStrut(30));
        //b.add(lblCongDoan);
        //b.add(Box.createHorizontalStrut(30));
        //b.add(cboCongDoan);
        b.add(Box.createHorizontalStrut(50));
        b.add(lblXuong);
        b.add(Box.createHorizontalStrut(30));
        b.add(cboXuong);
        b.add(Box.createHorizontalStrut(150));
        b.add(lblCaLam);
        b.add(Box.createHorizontalStrut(50));
        b.add(chkCaSang);
        b.add(Box.createHorizontalStrut(50));
        b.add(chkCaToi);
        b.add(Box.createHorizontalStrut(50));
//        b.add(chkThem);
        b.add(Box.createHorizontalStrut(30));
        b.add(lblGhiChu);
        b.add(Box.createHorizontalStrut(30));
        b.add(txtGhiChu);
        b.add(Box.createHorizontalStrut(25));
        
        JPanel pnTable = new JPanel();
        pnlCCCN.add(pnTable, BorderLayout.CENTER);
        String[] cols_congnhan = {"Mã nhân viên", "Họ đệm", "Tên nhân viên", "Ca làm","Sản lượng","Số giờ tăng ca","Ghi chú"};
        modelDsCN = new DefaultTableModel(cols_congnhan, 0);
        tblDsCN = new JTable(modelDsCN) {
        	public void tableChanged(TableModelEvent e) {
        		super.tableChanged(e);
        		
        		repaint();
        	}
        }; 
        tblDsCN.setRowHeight(26);
        tblDsCN.setFont(new Font("Tahoma", Font.PLAIN, 16));
        
        JScrollPane scrollPane = new JScrollPane(tblDsCN);
        pnTable.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1220,530));
        
  
        JPanel pnBottom = new JPanel();
        pnlCCCN.add(pnBottom, BorderLayout.SOUTH);
        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        Box b3 = Box.createHorizontalBox();
        pnBottom.add(b3);
        
        JLabel lblTimKiemTen = new JLabel("Tìm kiếm theo tên");
        txtTimKiemTen = new JTextField(5);
        btnTimKiemTen = new JButton("Tìm");
        btnCapNhat = new JButton("Cập nhật");
        btnHoanTat = new JButton("Hoàn tất");
        
        b3.add(Box.createHorizontalStrut(20));
        b3.add(lblTimKiemTen);
        b3.add(Box.createHorizontalStrut(10));
        b3.add(txtTimKiemTen);
        b3.add(Box.createHorizontalStrut(10));
        b3.add(btnTimKiemTen);
        b3.add(Box.createHorizontalStrut(400));
        b3.add(btnCapNhat);
        b3.add(Box.createHorizontalStrut(40));
        b3.add(btnHoanTat);
        b3.add(Box.createHorizontalStrut(25));
        pnBottom.setBorder(new EmptyBorder(0, 0, 20,0));
        
        dsCNXCa = congNhanDao.getListCntheoXuongCa(1, dsX.get(cboXuong.getSelectedIndex()).getMaXuong());
        if(boPhanDaChamCongChua(dsCNXCa)) {
        	docDuLieuTableDaCC(dsCNXCa);
        	btnHoanTat.setEnabled(false);
        	
        }else {
        	docDuLieuVaoTable(dsCNXCa);
        }
        
        btnHoanTat.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnTimKiemTen.addActionListener(this);
        
        
        
		return pnlCCCN;
	}
	
	public void clearTable() {
		modelDsCN.getDataVector().removeAllElements();
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
	
	public void docDuLieuVaoTable(ArrayList<CongNhan> dsCN) {
	
		for(CongNhan cn: dsCN) {
				 SanPham sp = new SanPham();
				 Object[] rowData = {cn.getMaCN(), cn.getHo(),cn.getTen(), layCaLamViec(cn),sp.getSoLuong(),"0",""};
				 modelDsCN.addRow(rowData);
            }
	}
	
	public Boolean boPhanDaChamCongChua(ArrayList<CongNhan> dsCNX) {
		
		for(int i=0 ; i< dsCNX.size();i++) {
			for(BangChamCongCN bangCC: dsDaChamCong) {
				if(dsCNX.get(i).getMaCN().equals(bangCC.getCn().getMaCN())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void docDuLieuTableDaCC(ArrayList<CongNhan> dsNV){
		for(int i=0 ; i< dsNV.size();i++) {
			for(BangChamCongCN bangCC: dsDaChamCong) {
				if(dsNV.get(i).getMaCN().equals(bangCC.getCn().getMaCN())) {
					Object[] rowData = {dsNV.get(i).getMaCN(), dsNV.get(i).getHo(),dsNV.get(i).getTen(), layCaLamViec(dsNV.get(i)),bangCC.getSoGioTangCa(),bangCC.getGhiChu()};
					modelDsCN.addRow(rowData);
				}
			}
		}
	}
	
	public String layCaLamViec(CongNhan cn) {
		if(cn.getCaLamViec()==1) {
			return "Sáng";
		}
		return "Tối";
		
	}
	
	public String phatSinhMaCC(LocalDate ngayChamCong, String maCN) {
		
		SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String ma  = dayFormat.format(Date.valueOf(ngayChamCong))+ monthFormat.format(Date.valueOf(ngayChamCong)) +yearFormat.format(Date.valueOf(ngayChamCong)) +maCN;
        return ma;
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		int i =0;
		if(o==btnHoanTat) {
			 if(chkCaSang.isSelected()==true || chkCaToi.isSelected()==true) {
				for(CongNhan cn: dsCNXCa) {
					try {
						LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
						String maCC = phatSinhMaCC(ngayChamCong, cn.getMaCN());
						int soGioTangCa;
						if(modelDsCN.getValueAt(i, 7).toString().equals("")) {
							soGioTangCa =0;
						}
						else {
							soGioTangCa = Integer.parseInt(modelDsCN.getValueAt(i, 7).toString()) ;
						}
						int caLam=0;
						
						
						caLam = cn.getCaLamViec();
					
						String ghiChu;
						if(modelDsCN.getValueAt(i, 8).toString().equals("")) {
							ghiChu="";
						}else {
							ghiChu = String.valueOf(modelDsCN.getValueAt(i, 8)) ;
						}
						BangChamCongCN bangChamCongCn = new BangChamCongCN(maCC);
						chamCongCNDao.insertBangChamCongCN(bangChamCongCn);
						i++;
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				String ca;
				if(chkCaSang.isSelected()) {
					ca = "Sáng";
				}
				else
					ca= "Tối";
				btnHoanTat.setEnabled(false);
				try {
					JOptionPane.showMessageDialog(frame, "Bạn đã chấm công thành công cho  " +String.valueOf(cboXuong.getSelectedItem()) +" ca " +ca +" ngày " +String.valueOf(dpNgayChamCong.getFullDate()) );
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
			}
			
		}
		if(o==btnCapNhat) {
			for(int n=0 ; n< dsCNXCa.size();n++) {
				for(BangChamCongCN bangCC: dsDaChamCong) {
					if(dsCNXCa.get(n).getMaCN().equals(bangCC.getCn().getMaCN())) {
						try {
							LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
							String maCC = bangCC.getMaCCCN();
							int soGioTangCa;
							if(modelDsCN.getValueAt(n, 7).toString().equals("")) {
								soGioTangCa =0;
							}
							else {
								soGioTangCa = Integer.parseInt(modelDsCN.getValueAt(n, 7).toString()) ;
							}
							int caLam=0;
							
							
							caLam = bangCC.getCaLam();
							
							String ghiChu;
							if(modelDsCN.getValueAt(n, 8).toString().equals("")) {
								ghiChu="";
							}else {
								ghiChu = String.valueOf(modelDsCN.getValueAt(n, 8)) ;
							}
							BangChamCongCN bangChamCongCN = new BangChamCongCN(maCC);
							chamCongCNDao.updateBangChamCongCN(bangChamCongCN);
									
									
									
									
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					}
				}
			}
			String ca;
			if(chkCaSang.isSelected()) {
				ca = "Sáng";
			}
			else
				ca= "Tối";

			try {
				JOptionPane.showMessageDialog(frame, "Bạn đã cập nhật thành công bảng chấm công cho  " +String.valueOf(cboXuong.getSelectedItem()) +" ca " +ca +" ngày " +String.valueOf(dpNgayChamCong.getFullDate()) );
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}