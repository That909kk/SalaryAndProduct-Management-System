package gUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import dao.BangChamCongNV_DAO;
import dao.BoPhan_DAO;
import dao.NhanVien_DAO;
import entity.BangChamCongNV;
import entity.BoPhan;
import entity.NhanVien;

public class ChamCongNV_GUI implements ListSelectionListener, ActionListener {
	private JPanel contentPane;
	private JFrame frame;
	private kDatePicker dpNgayChamCong;
	private JLabel lblNgayCC,lblBoPhan,lblCaLam,lblGhiChu;
	private JTextField txtGhiChu,txtTimKiemTen;
	private DefaultComboBoxModel<String> modelBoPhan;
	private JComboBox<String> cboBoPhan;
	private JTextField textField;
	private JCheckBox chkCaSang,chkCaToi,chkThem;
	public DefaultTableModel modelDsNV;
    public JTable tblDsNV;
    private JButton btnTimKiemTen,btnCapNhat,btnHoanTat;
    private NhanVien_DAO nhanVienDao;
    private BoPhan_DAO boPhanDao;
    private ArrayList<BoPhan> dsBP;
    private ArrayList<NhanVien> dsNVBPCa;
    private BangChamCongNV_DAO chamCongNVDao;
    private ArrayList<BangChamCongNV> dsDaChamCong;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChamCongNV_GUI window = new ChamCongNV_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public ChamCongNV_GUI() {
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
		 JPanel pnlCCNV = new JPanel();
		 pnlCCNV.setBackground(new Color(240, 248, 255));
		 pnlCCNV.setBounds(0, 50, 1268, 632);
		
		 try {
	            ConnectDB.getInstance().connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		BoPhan_DAO boPhanDao = new BoPhan_DAO();
		chamCongNVDao = new BangChamCongNV_DAO();
		
		
		dsBP = boPhanDao.getdsBoPhan();
		dsNVBPCa = new ArrayList<NhanVien>();
		nhanVienDao = new NhanVien_DAO();
		dsDaChamCong = new ArrayList<BangChamCongNV>();
		

		
		
		
		 
		pnlCCNV.setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1000,40));;
		pnlCCNV.add(topPanel, BorderLayout.NORTH);
	
		
		Box b;
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		b =  Box.createHorizontalBox();
		topPanel.add(b);
		topPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
	
		
		lblNgayCC = new JLabel("Ngày chấm công");
		dpNgayChamCong = new kDatePicker(120);
		dpNgayChamCong.setPreferredSize(new Dimension(120, 40));
		lblBoPhan = new JLabel("Bộ Phận");
		modelBoPhan = new DefaultComboBoxModel<String>();
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
						dsDaChamCong = chamCongNVDao.getBangCCTheoNgay(ngayChamCong);
						if(boPhanDaChamCongChua(dsNVBPCa)) {
    						clearTable();
    						docDuLieuTableDaCC(dsNVBPCa);
    						btnHoanTat.setEnabled(false);
    					}
    					else{
    						clearTable();
    						docDuLieuVaoTable(dsNVBPCa);
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
		
		
		
		for(BoPhan bp: dsBP) {
			modelBoPhan.addElement(bp.getTenBoPhan());
			
		}
		
	    cboBoPhan = new JComboBox<String>(modelBoPhan);
	    cboBoPhan.setPreferredSize(new Dimension(100,40));
	    cboBoPhan.setSelectedItem(dsBP.get(0).getMaBoPhan());
//	    dsNVBP = nhanVienDao.getListNVtheoBP(dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan());
	    try {
			dsDaChamCong = chamCongNVDao.getBangCCTheoNgay(dpNgayChamCong.getFullDate().toLocalDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    
	    
	    
	    
	    cboBoPhan.addItemListener(new ItemListener() {
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int boPhanIndex = cboBoPhan.getSelectedIndex();
				try {
					LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
					if(chkCaSang.isSelected()==true) {
						dsNVBPCa = nhanVienDao.getListNVtheoBPCa(1,dsBP.get(boPhanIndex).getMaBoPhan());
						dsDaChamCong = chamCongNVDao.getBangCCTheoNgay(ngayChamCong);
						if(boPhanDaChamCongChua(dsNVBPCa)) {
							clearTable();
							docDuLieuTableDaCC(dsNVBPCa);
							btnHoanTat.setEnabled(false);
						}
						else {
							clearTable();
							docDuLieuVaoTable(dsNVBPCa);
							btnHoanTat.setEnabled(true);
						}
						
					}
					else if(chkCaToi.isSelected()==true) {
						dsNVBPCa = nhanVienDao.getListNVtheoBPCa(2,dsBP.get(boPhanIndex).getMaBoPhan());
						dsDaChamCong = chamCongNVDao.getBangCCTheoNgay(ngayChamCong);
						if(boPhanDaChamCongChua(dsNVBPCa)) {
							clearTable();
							docDuLieuTableDaCC(dsNVBPCa);
							btnHoanTat.setEnabled(false);
						}
						
						else {
							clearTable();
							docDuLieuVaoTable(dsNVBPCa);
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
	                        dsNVBPCa = nhanVienDao.getListNVtheoBPCa(1,dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan());
	    					dsDaChamCong = chamCongNVDao.getBangCCTheoNgay(ngayChamCong);
	    					if(boPhanDaChamCongChua(dsNVBPCa)) {
	    						clearTable();
	    						docDuLieuTableDaCC(dsNVBPCa);
	    						btnHoanTat.setEnabled(false);
	    					}
	    					else{
	    						clearTable();
	    						docDuLieuVaoTable(dsNVBPCa);
	    						btnHoanTat.setEnabled(true);
	    					}
	  
	                    } else if (source == chkCaToi) {
	                        chkCaSang.setSelected(false);
	                        chkThem.setSelected(false);
	                        dsNVBPCa = nhanVienDao.getListNVtheoBPCa(2,dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan());
	                        if(boPhanDaChamCongChua(dsNVBPCa)) {
	    						clearTable();
	    						docDuLieuTableDaCC(dsNVBPCa);
	    						btnHoanTat.setEnabled(false);
	    					}
	    					else{
	    						clearTable();
	    						docDuLieuVaoTable(dsNVBPCa);
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
        
        b.add(Box.createHorizontalStrut(30));
        b.add(lblNgayCC);
        b.add(Box.createHorizontalStrut(60));
        b.add(dpNgayChamCong);
        b.add(Box.createHorizontalStrut(30));
        b.add(lblBoPhan);
        b.add(Box.createHorizontalStrut(30));
        b.add(cboBoPhan);
        b.add(Box.createHorizontalStrut(100));
        b.add(lblCaLam);
        b.add(Box.createHorizontalStrut(50));
        b.add(chkCaSang);
        b.add(Box.createHorizontalStrut(50));
        b.add(chkCaToi);
        b.add(Box.createHorizontalStrut(50));
//        b.add(chkThem);
        b.add(Box.createHorizontalStrut(30));
        
        JPanel pnTable = new JPanel();
        pnlCCNV.add(pnTable, BorderLayout.CENTER);
        String[] cols_nhanVien = {"Mã nhân viên", "Họ đệm", "Tên nhân viên", "Ca làm", "Đi làm", "Vắng phép", "Vắng không phép", "Số giờ tăng ca","Ghi chú"};
        modelDsNV = new DefaultTableModel(cols_nhanVien, 0);
        tblDsNV = new JTable(modelDsNV) {
        	public void tableChanged(TableModelEvent e) {
        		super.tableChanged(e);
        		
        		repaint();
        	}
        }; 
       
        	
        
       
        
        
        tblDsNV.getColumn("Đi làm").setCellRenderer(new Radiorenderer());
        tblDsNV.getColumn("Đi làm").setCellEditor(new RadioEditor(new JCheckBox()));
        
        tblDsNV.getColumn("Vắng phép").setCellRenderer(new Radiorenderer());
        tblDsNV.getColumn("Vắng phép").setCellEditor(new RadioEditor(new JCheckBox()));
        
        tblDsNV.getColumn("Vắng không phép").setCellRenderer(new Radiorenderer());
        tblDsNV.getColumn("Vắng không phép").setCellEditor(new RadioEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(tblDsNV);
        pnTable.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1220,530));
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
        scrollPane.setColumnHeaderView(rdbtnNewRadioButton);
        tblDsNV.getSelectionModel().addListSelectionListener(this);
        
        
        JPanel pnBottom = new JPanel();
        pnlCCNV.add(pnBottom, BorderLayout.SOUTH);
        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        Box b2 = Box.createHorizontalBox();
        pnBottom.add(b2);
        
        JLabel lblTimKiemTen = new JLabel("Tìm kiếm theo tên");
        txtTimKiemTen = new JTextField(5);
        btnTimKiemTen = new JButton("Tìm");
        btnCapNhat = new JButton("Cập nhật");
        btnHoanTat = new JButton("Hoàn tất");
        
        b2.add(Box.createHorizontalStrut(20));
        b2.add(lblTimKiemTen);
        b2.add(Box.createHorizontalStrut(10));
        b2.add(txtTimKiemTen);
        b2.add(Box.createHorizontalStrut(10));
        b2.add(btnTimKiemTen);
        b2.add(Box.createHorizontalStrut(400));
        b2.add(btnCapNhat);
        b2.add(Box.createHorizontalStrut(40));
        b2.add(btnHoanTat);
        b2.add(Box.createHorizontalStrut(25));
        pnBottom.setBorder(new EmptyBorder(0, 0, 20,0));
        
        dsNVBPCa = nhanVienDao.getListNVtheoBPCa(1, dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan());
        if(boPhanDaChamCongChua(dsNVBPCa)) {
        	docDuLieuTableDaCC(dsNVBPCa);
        	btnHoanTat.setEnabled(false);
        	
        }else {
        	docDuLieuVaoTable(dsNVBPCa);
        }
        
        btnHoanTat.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnTimKiemTen.addActionListener(this);
        
        
        
		return pnlCCNV;
	}
	
	public void clearTable() {
		modelDsNV.getDataVector().removeAllElements();
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
	
	class Radiorenderer implements TableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			if(value == null)
				return null;
			return (Component) value;
		}
		
	}
	
	class RadioEditor extends DefaultCellEditor implements ItemListener{
		
		JRadioButton radio;
		public RadioEditor(JCheckBox chk) {
			super(chk);
		}
		
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value,boolean selected, int row, int col) {
			
			if(value ==null)
				return null;
			radio = (JRadioButton) value;
			radio.addItemListener(this);
			return (Component) value;
			
			
		}
		
		@Override
		public Object getCellEditorValue() {
			radio.removeItemListener(this);
			return radio;
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			super.fireEditingStopped();
			
			
		}
		
	}
	
	public void docDuLieuVaoTable(ArrayList<NhanVien> dsNV) {
	
		for(NhanVien nv: dsNV) {
				 Object[] rowData = {nv.getMaNV(), nv.getHo(),nv.getTen(), layCaLamViec(nv), new JRadioButton(), new JRadioButton(), new JRadioButton(),"0",""};
				 modelDsNV.addRow(rowData);
            }
		 for(int i=0; i < dsNV.size();i++) {	
	        	ButtonGroup bg = new ButtonGroup();
	        	bg.add((JRadioButton) modelDsNV.getValueAt(i, 4));
	        	((JRadioButton) modelDsNV.getValueAt(i, 4)).setSelected(true);
	        	bg.add((JRadioButton) modelDsNV.getValueAt(i, 5));
	        	bg.add((JRadioButton) modelDsNV.getValueAt(i, 6));
	        }

	}
	
	public Boolean boPhanDaChamCongChua(ArrayList<NhanVien> dsNVBP) {
		
		for(int i=0 ; i< dsNVBP.size();i++) {
			for(BangChamCongNV bangCC: dsDaChamCong) {
				if(dsNVBP.get(i).getMaNV().equals(bangCC.getNv().getMaNV())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void docDuLieuTableDaCC(ArrayList<NhanVien> dsNV){
		for(int i=0 ; i< dsNV.size();i++) {
			for(BangChamCongNV bangCC: dsDaChamCong) {
				if(dsNV.get(i).getMaNV().equals(bangCC.getNv().getMaNV())) {
					Object[] rowData = {dsNV.get(i).getMaNV(), dsNV.get(i).getHo(),dsNV.get(i).getTen(), layCaLamViec(dsNV.get(i)), new JRadioButton(), new JRadioButton(), new JRadioButton(),bangCC.getSoGioTangCa(),bangCC.getGhiChu()};
					modelDsNV.addRow(rowData);
					ButtonGroup bg = new ButtonGroup();
		        	bg.add((JRadioButton) modelDsNV.getValueAt(i, 4));
		        	
		        	bg.add((JRadioButton) modelDsNV.getValueAt(i, 5));
		        	bg.add((JRadioButton) modelDsNV.getValueAt(i, 6));
		        	if(bangCC.isCoMat()) {
		        		((JRadioButton) modelDsNV.getValueAt(i, 4)).setSelected(true);
		        	}else if(bangCC.isCoPhep()){
		        		((JRadioButton) modelDsNV.getValueAt(i, 5)).setSelected(true);
		        	}else
		        		((JRadioButton) modelDsNV.getValueAt(i, 6)).setSelected(true);
				}
			}
		}
	}
	
	public String layCaLamViec(NhanVien nv) {
		if(nv.getCaLamViec()==1) {
			return "Sáng";
		}
		return "Tối";
		
	}
	
	public String phatSinhMaCC(LocalDate ngayChamCong, String maNV) {
		
		SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String ma  = dayFormat.format(Date.valueOf(ngayChamCong))+ monthFormat.format(Date.valueOf(ngayChamCong)) +yearFormat.format(Date.valueOf(ngayChamCong)) + maNV;
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
				for(NhanVien nv: dsNVBPCa) {
					try {
						LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
						String maCC = phatSinhMaCC(ngayChamCong, nv.getMaNV());
						int soGioTangCa;
						if(modelDsNV.getValueAt(i, 7).toString().equals("")) {
							soGioTangCa =0;
						}
						else {
							soGioTangCa = Integer.parseInt(modelDsNV.getValueAt(i, 7).toString()) ;
						}
						int caLam=0;
						
						
						caLam = nv.getCaLamViec();
						
						boolean coMat=true;
						boolean coPhep=true;
						boolean vangMat=true;
						if(((JRadioButton) modelDsNV.getValueAt(i, 4)).isSelected()==true) {
							coMat = true;
							coPhep= false;
							vangMat =false;
						}
						else if(((JRadioButton) modelDsNV.getValueAt(i, 5)).isSelected()==true) {
							coMat = false;
							coPhep= true;
							vangMat =false;
						}
						else if(((JRadioButton) modelDsNV.getValueAt(i, 6)).isSelected()==true) {
							coMat = false;
							coPhep= false;
							vangMat =true;
						}
						
						String ghiChu;
						if(modelDsNV.getValueAt(i, 8).toString().equals("")) {
							ghiChu="";
						}else {
							ghiChu = String.valueOf(modelDsNV.getValueAt(i, 8)) ;
						}
						BangChamCongNV bangChamCongNV = new BangChamCongNV(maCC, nv, ngayChamCong, soGioTangCa, caLam, coMat, coPhep, vangMat, ghiChu);
						chamCongNVDao.insertBangChamCongNV(bangChamCongNV);
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
					JOptionPane.showMessageDialog(frame, "Bạn đã chấm công thành công cho  " +String.valueOf(cboBoPhan.getSelectedItem()) +" ca " +ca +" ngày " +String.valueOf(dpNgayChamCong.getFullDate()) );
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
			for(int n=0 ; n< dsNVBPCa.size();n++) {
				for(BangChamCongNV bangCC: dsDaChamCong) {
					if(dsNVBPCa.get(n).getMaNV().equals(bangCC.getNv().getMaNV())) {
						try {
							LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
							String maCC = bangCC.getMaCCNV();
							int soGioTangCa;
							if(modelDsNV.getValueAt(n, 7).toString().equals("")) {
								soGioTangCa =0;
							}
							else {
								soGioTangCa = Integer.parseInt(modelDsNV.getValueAt(n, 7).toString()) ;
							}
							int caLam=0;
							
							
							caLam = bangCC.getCaLam();
							
							boolean coMat=true;
							boolean coPhep=true;
							boolean vangMat=true;
							if(((JRadioButton) modelDsNV.getValueAt(n, 4)).isSelected()==true) {
								coMat = true;
								coPhep= false;
								vangMat =false;
							}
							else if(((JRadioButton) modelDsNV.getValueAt(n, 5)).isSelected()==true) {
								coMat = false;
								coPhep= true;
								vangMat =false;
							}
							else if(((JRadioButton) modelDsNV.getValueAt(n, 6)).isSelected()==true) {
								coMat = false;
								coPhep= false;
								vangMat =true;
							}
							
							String ghiChu;
							if(modelDsNV.getValueAt(n, 8).toString().equals("")) {
								ghiChu="";
							}else {
								ghiChu = String.valueOf(modelDsNV.getValueAt(n, 8)) ;
							}
							BangChamCongNV bangChamCongNV = new BangChamCongNV(maCC, dsNVBPCa.get(n), ngayChamCong, soGioTangCa, caLam, coMat, coPhep, vangMat, ghiChu);
							chamCongNVDao.updateBangChamCongNV(bangChamCongNV);
									
									
									
									
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
				JOptionPane.showMessageDialog(frame, "Bạn đã cập nhật thành công bảng chấm công cho  " +String.valueOf(cboBoPhan.getSelectedItem()) +" ca " +ca +" ngày " +String.valueOf(dpNgayChamCong.getFullDate()) );
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