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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

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
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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
	private JCheckBox chkCaSang,chkCaToi,chkThem;
	public DefaultTableModel modelDsNV;
    public JTable tblDsNV;
    private JButton btnTimKiemTen,btnCapNhat,btnHoanTat;
    private NhanVien_DAO nhanVienDao;
    private BoPhan_DAO boPhanDao;
    private ArrayList<BoPhan> dsBP;
    private ArrayList<NhanVien> dsNVBPCa;
    private BangChamCongNV_DAO chamCongNVDao;
    private ArrayList<BangChamCongNV> dsCongLamViec;
    private ArrayList<String> dsTen;
   
    
	
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
		dsCongLamViec = new ArrayList<BangChamCongNV>();
		
		lblNgayCC = new JLabel("Ngày chấm công");
		dpNgayChamCong = new kDatePicker(120);
		dpNgayChamCong.setPreferredSize(new Dimension(120, 40));
		lblBoPhan = new JLabel("Bộ Phận");
		modelBoPhan = new DefaultComboBoxModel<String>();
		dsNVBPCa = nhanVienDao.getListNVtheoBPCa(1, dsBP.get(0).getMaBoPhan());
		dsCongLamViec = chamCongNVDao.getBangCCTheoNgayBPCa(LocalDate.now(), "BPKT", 1);
		
		
		
	
		pnlCCNV.setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1000,40));;
		pnlCCNV.add(topPanel, BorderLayout.NORTH);
	
		
		Box b;
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		b =  Box.createHorizontalBox();
		topPanel.add(b);
		topPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		
		
		for(BoPhan bp: dsBP) {
			modelBoPhan.addElement(bp.getTenBoPhan());
			
		}
		
	    cboBoPhan = new JComboBox<String>(modelBoPhan);
	    cboBoPhan.setPreferredSize(new Dimension(100,40));
	    cboBoPhan.setSelectedItem(dsBP.get(0).getMaBoPhan());

	  
	    
	    
	    
	   
	    
	    cboBoPhan.addItemListener(new ItemListener() {
		
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int boPhanIndex = cboBoPhan.getSelectedIndex();
				try {
					LocalDate ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
					if(chkCaSang.isSelected()==true) {
						dsNVBPCa = nhanVienDao.getListNVtheoBPCa(1,dsBP.get(boPhanIndex).getMaBoPhan());
						dsCongLamViec = chamCongNVDao.getBangCCTheoNgayBPCa(ngayChamCong, dsBP.get(boPhanIndex).getMaBoPhan(), 1);
						dsTen = layDsTen(dsNVBPCa);
						AutoCompleteDecorator.decorate(txtTimKiemTen, dsTen , false);
						if(kiemTraChamCong(dsCongLamViec)) {
							clearTable();
							docDuLieuVaoTable();
							btnHoanTat.setEnabled(false);
							btnCapNhat.setEnabled(true);
						}
						else {
							clearTable();
							dsCongLamViec = taoDefaultDsChamCong(dsNVBPCa);
							docDuLieuVaoTable();
							btnHoanTat.setEnabled(true);
							btnCapNhat.setEnabled(false);
						}
						
					}
					else if(chkCaToi.isSelected()==true) {
						dsNVBPCa = nhanVienDao.getListNVtheoBPCa(2,dsBP.get(boPhanIndex).getMaBoPhan());
						dsCongLamViec = chamCongNVDao.getBangCCTheoNgayBPCa(ngayChamCong, dsBP.get(boPhanIndex).getMaBoPhan(), 2);
						dsTen = layDsTen(dsNVBPCa);
						AutoCompleteDecorator.decorate(txtTimKiemTen, dsTen , false);
						if(kiemTraChamCong(dsCongLamViec)) {
							clearTable();
							docDuLieuVaoTable();
							btnHoanTat.setEnabled(false);
							btnCapNhat.setEnabled(true);
							
						}
						
						else {
							clearTable();
							dsCongLamViec = taoDefaultDsChamCong(dsNVBPCa);
							docDuLieuVaoTable();
							btnHoanTat.setEnabled(true);
							btnCapNhat.setEnabled(false);
							
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
	                        dsNVBPCa = nhanVienDao.getListNVtheoBPCa(1,dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan());
	    					dsCongLamViec = chamCongNVDao.getBangCCTheoNgayBPCa(ngayChamCong, dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan(), 1);
	    					dsTen = layDsTen(dsNVBPCa);
	    					AutoCompleteDecorator.decorate(txtTimKiemTen, dsTen , false);
	    					if(kiemTraChamCong(dsCongLamViec)) {
	    						clearTable();
	    						docDuLieuVaoTable();
	    						btnHoanTat.setEnabled(false);
	    						btnCapNhat.setEnabled(true);
	    						
	    					}
	    					else{
	    						clearTable();
	    						dsCongLamViec = taoDefaultDsChamCong(dsNVBPCa);
	    						docDuLieuVaoTable();
	    						btnHoanTat.setEnabled(true);
	    						btnCapNhat.setEnabled(false);
	    					}
	  
	                    } else if (source == chkCaToi) {
	                        chkCaSang.setSelected(false);
	                        chkThem.setSelected(false);
	                        dsNVBPCa = nhanVienDao.getListNVtheoBPCa(2,dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan());
	                        dsCongLamViec = chamCongNVDao.getBangCCTheoNgayBPCa(ngayChamCong, dsBP.get(cboBoPhan.getSelectedIndex()).getMaBoPhan(), 2);
	                        dsTen = layDsTen(dsNVBPCa);
	                        AutoCompleteDecorator.decorate(txtTimKiemTen, dsTen , false);
	                        if(kiemTraChamCong(dsCongLamViec)) {
	    						clearTable();
	    						docDuLieuVaoTable();
	    						btnHoanTat.setEnabled(false);
	    						btnCapNhat.setEnabled(true);
	    						
	    					}
	    					else{
	    						clearTable();
	    						dsCongLamViec = taoDefaultDsChamCong(dsNVBPCa);
	    						docDuLieuVaoTable();
	    						btnHoanTat.setEnabled(true);
	    						btnCapNhat.setEnabled(false);
	    						
	    					}
	                    } 
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                    
                   
         
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
       tblDsNV.setRowHeight(26);
       tblDsNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
        	
        
      
        
        tblDsNV.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(tblDsNV.getRowCount()==dsCongLamViec.size()) {
                	if (e.getType() == TableModelEvent.UPDATE && (e.getColumn() == 4 || e.getColumn() == 5 || e.getColumn() == 6) || e.getColumn() == 7 || e.getColumn() == 8) {
                        int row = e.getFirstRow();
                        if(((JRadioButton) modelDsNV.getValueAt(row, 4)).isSelected()==true){
                        	dsCongLamViec.get(row).setCoMat(true);
                        	dsCongLamViec.get(row).setCoPhep(false);
                        	dsCongLamViec.get(row).setVangMat(false);
                        	
            			}else if(((JRadioButton) modelDsNV.getValueAt(row, 5)).isSelected()==true) {
            				dsCongLamViec.get(row).setCoMat(false);
                        	dsCongLamViec.get(row).setCoPhep(true);
                        	dsCongLamViec.get(row).setVangMat(false);
            			}else {
            				dsCongLamViec.get(row).setCoMat(false);
                        	dsCongLamViec.get(row).setCoPhep(false);
                        	dsCongLamViec.get(row).setVangMat(true);
            			}
                        if(!(modelDsNV.getValueAt(row, 7).toString().equals("") || modelDsNV.getValueAt(row, 7).toString().equals("0")) ) {
    						dsCongLamViec.get(row).setSoGioTangCa(Integer.parseInt(modelDsNV.getValueAt(row, 7).toString()));
    					}
    					
    					if(!(modelDsNV.getValueAt(row, 8).toString().equals(""))) {
    						dsCongLamViec.get(row).setGhiChu(modelDsNV.getValueAt(row, 8).toString());
    					}
                	}
                    
                    
                    
                }
            }
        });
        tblDsNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tblDsNV.setRowHeight(24);
        
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
        
        
        dsTen = new ArrayList<String>();
        dsTen = layDsTen(dsNVBPCa);
       
        AutoCompleteDecorator.decorate(txtTimKiemTen, dsTen , false);
        
        txtTimKiemTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTimKiemTen.setCaretPosition(txtTimKiemTen.getText().length());
            }
        });
        
        txtTimKiemTen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { 
                    if ((txtTimKiemTen.getSelectionStart() == 1 && txtTimKiemTen.getSelectionEnd() == txtTimKiemTen.getText().length())||(txtTimKiemTen.getSelectionStart() == 0 && txtTimKiemTen.getSelectionEnd() == txtTimKiemTen.getText().length())) {
                        txtTimKiemTen.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }
        });


        pnBottom.setBorder(new EmptyBorder(0, 0, 20,0));
        
        btnHoanTat.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnTimKiemTen.addActionListener(this);
        
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
						
						JOptionPane.showMessageDialog(frame, "Chưa đến ngày làm không thể chấm công","Chưa đến ngày làm",0);
						btnHoanTat.setEnabled(false);
						btnCapNhat.setEnabled(false);
					}
					else if(ngayChamCong.isBefore(LocalDate.now()) || ngayChamCong.equals(LocalDate.now())) {
						dsCongLamViec = chamCongNVDao.getBangCCTheoNgayBPCa(ngayChamCong,dsBP.get(0).getMaBoPhan(),1);
						if(kiemTraChamCong(dsCongLamViec)) {
    						clearTable();
    						docDuLieuVaoTable();
    						btnHoanTat.setEnabled(false);
    						btnCapNhat.setEnabled(true);
    					}
    					else{
    						dsCongLamViec = taoDefaultDsChamCong(dsNVBPCa);
    						clearTable();
    						docDuLieuVaoTable();
    						btnHoanTat.setEnabled(true);
    						btnCapNhat.setEnabled(false);
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
        
        if(kiemTraChamCong(dsCongLamViec)) {
        	btnHoanTat.setEnabled(false);
        	btnCapNhat.setEnabled(true);
        	
        	
        }else {
        	
        	btnHoanTat.setEnabled(true);
        	btnCapNhat.setEnabled(false);
        	
        }
        if(!kiemTraChamCong(dsCongLamViec)) {
			dsCongLamViec = taoDefaultDsChamCong(dsNVBPCa);
		}
        docDuLieuVaoTable();
        
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
	
	public ArrayList<String> layDsTen(ArrayList<NhanVien> dsNV) {
		ArrayList<String> dsTenNV = new ArrayList<String>();
		for(NhanVien nv: dsNV) {
			dsTenNV.add(nv.getTen());
		}
		return dsTenNV;		
		
	}
	
	public ArrayList<BangChamCongNV> taoDefaultDsChamCong(ArrayList<NhanVien> dsNVBPCa) {
		ArrayList<BangChamCongNV> dsCongLam = new ArrayList<BangChamCongNV>();
		for(NhanVien nv : dsNVBPCa) {
			LocalDate ngayChamCong;
			try {
				ngayChamCong = dpNgayChamCong.getFullDate().toLocalDate();
				String maCC = phatSinhMaCC(ngayChamCong, nv.getMaNV());
				int soGioTangCa = 0;
				int caLam=1;
				boolean coMat=true;
				boolean coPhep=false;
				boolean vangMat=false;
				String ghiChu ="";
				BangChamCongNV bangChamCongNV = new BangChamCongNV(maCC, nv, ngayChamCong, soGioTangCa, caLam, coMat, coPhep, vangMat, ghiChu);
				dsCongLam.add(bangChamCongNV);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		return dsCongLam;
	}
	
	

	
	
	
	public Boolean kiemTraChamCong(ArrayList<BangChamCongNV> dsCongLam) {
		if(dsCongLam.size()==0)
			return false;
		return true;
	}
	

	
	public void docDuLieuVaoTable() {
		for(int i=0 ; i< dsNVBPCa.size();i++) {
			for(BangChamCongNV bangCC: dsCongLamViec) {
				if(dsNVBPCa.get(i).getMaNV().equals(bangCC.getNv().getMaNV())) {
					Object[] rowData = {dsNVBPCa.get(i).getMaNV(), dsNVBPCa.get(i).getHo(),dsNVBPCa.get(i).getTen(), layCaLamViec(dsNVBPCa.get(i)), new JRadioButton(), new JRadioButton(), new JRadioButton(),bangCC.getSoGioTangCa(),bangCC.getGhiChu()};
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
			if(JOptionPane.showConfirmDialog(frame, "Bạn hãy xác nhận hoàn thành bảng chấm công","Xác Nhận",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				for(BangChamCongNV bangChamCong: dsCongLamViec) {
					 try {
						 chamCongNVDao.insertBangChamCongNV(bangChamCong);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					 
						
				}
				btnHoanTat.setEnabled(false);
				btnCapNhat.setEnabled(true);
			}else 
				return;
			
			
		}
		if(o==btnCapNhat) {
			if(JOptionPane.showConfirmDialog(frame, "Hãy xác nhận cập nhật bảng chấm công","Xác Nhận",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				for(BangChamCongNV bangChamCong: dsCongLamViec) {
					 try {
						 chamCongNVDao.updateBangChamCongNV(bangChamCong);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					 
						
				}
			}else 
				return;
		}
		if(o==btnTimKiemTen) {
			String ten = txtTimKiemTen.getText();
			ArrayList<BangChamCongNV> dsBangCCTimKiem  = new ArrayList<BangChamCongNV>();
			ArrayList<NhanVien> dsNVTimKiem = new ArrayList<NhanVien>();
			if(!ten.equals("")) {
				for(NhanVien nv: dsNVBPCa) {
					if(nv.getTen().equals(ten)) {
						clearTable();
						for(BangChamCongNV bangCC: dsCongLamViec) {
							if(bangCC.getNv().getMaNV().equals(nv.getMaNV())) {
								dsBangCCTimKiem.add(bangCC);
								dsNVTimKiem.add(nv);
							}
						}
					}
				}
				if(dsNVTimKiem.size()==0) {
					JOptionPane.showMessageDialog(frame, "Không tìm thấy nhân viên nào!","Không tìm thấy",0);
				}
				else {
					clearTable();
					for(int n=0 ; n< dsNVTimKiem.size();n++) {
						for(BangChamCongNV bangCC: dsBangCCTimKiem) {
							if(dsNVTimKiem.get(n).getMaNV().equals(bangCC.getNv().getMaNV())) {
								Object[] rowData = {dsNVTimKiem.get(n).getMaNV(), dsNVTimKiem.get(n).getHo(),dsNVTimKiem.get(n).getTen(), layCaLamViec(dsNVTimKiem.get(n)), new JRadioButton(), new JRadioButton(), new JRadioButton(),bangCC.getSoGioTangCa(),bangCC.getGhiChu()};
								modelDsNV.addRow(rowData);
								ButtonGroup bg = new ButtonGroup();
					        	bg.add((JRadioButton) modelDsNV.getValueAt(n, 4));
					        	bg.add((JRadioButton) modelDsNV.getValueAt(n, 5));
					        	bg.add((JRadioButton) modelDsNV.getValueAt(n, 6));
					        	if(bangCC.isCoMat()) {
					        		((JRadioButton) modelDsNV.getValueAt(n, 4)).setSelected(true);
					        	}else if(bangCC.isCoPhep()){
					        		((JRadioButton) modelDsNV.getValueAt(n, 5)).setSelected(true);
					        	}else
					        		((JRadioButton) modelDsNV.getValueAt(n, 6)).setSelected(true);
							}
						}
					}
					
					
					tblDsNV.getModel().addTableModelListener(new TableModelListener() {
			            @Override
			            public void tableChanged(TableModelEvent e) {
			                if(tblDsNV.getRowCount()< dsCongLamViec.size()) {
			                	if (e.getType() == TableModelEvent.UPDATE && (e.getColumn() == 4 || e.getColumn() == 5 || e.getColumn() == 6) || e.getColumn() == 7 || e.getColumn() == 8) {
				                    int row = e.getFirstRow();
				                    if(((JRadioButton) modelDsNV.getValueAt(row, 4)).isSelected()==true){
				                    	dsBangCCTimKiem.get(row).setCoMat(true);
				                    	dsBangCCTimKiem.get(row).setCoPhep(false);
				                    	dsBangCCTimKiem.get(row).setVangMat(false);
				                    	
				        			}else if(((JRadioButton) modelDsNV.getValueAt(row, 5)).isSelected()==true) {
				        				dsBangCCTimKiem.get(row).setCoMat(false);
				        				dsBangCCTimKiem.get(row).setCoPhep(true);
				        				dsBangCCTimKiem.get(row).setVangMat(false);
				        			}else {
				        				dsBangCCTimKiem.get(row).setCoMat(false);
				        				dsBangCCTimKiem.get(row).setCoPhep(false);
				        				dsBangCCTimKiem.get(row).setVangMat(true);
				        			}
				                    if(!(modelDsNV.getValueAt(row, 7).toString().equals("") || modelDsNV.getValueAt(row, 7).toString().equals("0")) ) {
				                    	dsBangCCTimKiem.get(row).setSoGioTangCa(Integer.parseInt(modelDsNV.getValueAt(row, 7).toString()));
									}
									
									if(!(modelDsNV.getValueAt(row, 8).toString().equals(""))) {
										dsBangCCTimKiem.get(row).setGhiChu(modelDsNV.getValueAt(row, 8).toString());
									}
				                    
									for(BangChamCongNV bangChamCong: dsCongLamViec) {
										if(bangChamCong.getMaCCNV().equals(dsBangCCTimKiem.get(row).getMaCCNV())) {
											bangChamCong.setCoMat(dsBangCCTimKiem.get(row).isCoMat());
											bangChamCong.setCoPhep(dsBangCCTimKiem.get(row).isCoPhep());
											bangChamCong.setVangMat(dsBangCCTimKiem.get(row).isVangMat());
											return;
										}
									}
			                    
			              }
			            }
			          }
			        });
					
				}
				
				
			}else {
				clearTable();
				docDuLieuVaoTable();
			}
	
				
			
				
		}
		
	}

}