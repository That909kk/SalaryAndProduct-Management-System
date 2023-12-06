package gUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entity.BangChamCongCN;
import entity.CongNhan;
import entity.Xuong;
import dao.BangChamCongCN_DAO;
import dao.CongNhan_DAO;
import dao.Xuong_DAO;
import connectDB.ConnectDB;
import entity.SanPham;

public class ChamCongCN_GUI extends JFrame implements ListSelectionListener, ActionListener {

	private JButton btnHoanTat, btnCapNhat;
	private JCheckBox chckbxSang, chckbxToi, chckbxThem;
	private JLabel lblNgayChamCong, lblSanPhamSx, lblCaLam, lblCongDoan, lblXuong;
	private JComboBox cboXuong, cboCongDoan, cboSP;
	private JDateChooser dcNgayChamCong;
	private JPanel contentPane;
	private JFrame frame;
	private JTextField txtGhiChu;
	private JTextField txtTimKiem;
	private JTextField txtNumCongNhan;
	private JTextField txtNumDilam;
	public JTable tblDsCN;
	public DefaultTableModel modelDsCN;
	private BangChamCongCN_DAO chamCongCNDao;
	private DefaultComboBoxModel<String> modelXuong;
	private ArrayList<Xuong> dsXuong;
	private ArrayList<CongNhan> dsCNXCa;
	private CongNhan_DAO congNhanDao;
	private ArrayList<BangChamCongCN> dsDaChamCong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChamCongCN_GUI frame = new ChamCongCN_GUI();
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
	public ChamCongCN_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(this.getPNLCCCongNhan());
	}

	public JPanel getPNLCCCongNhan() {
		 try {
	            ConnectDB.getInstance().connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		Xuong_DAO xuongDao = new Xuong_DAO();
		chamCongCNDao = new BangChamCongCN_DAO();
		
		
		dsXuong = xuongDao.getDSXuong();
		dsCNXCa = new ArrayList<CongNhan>();
		congNhanDao = new CongNhan_DAO();
		dsDaChamCong = new ArrayList<BangChamCongCN>();
		
		JPanel pnlCCCN = new JPanel();
		pnlCCCN.setLayout(null);
		pnlCCCN.setBounds(0, 50, 1268, 632);
		pnlCCCN.setBackground(new Color(240, 248, 255));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(240, 248, 255));
		pnlTop.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u1ED9 L\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTop.setBounds(10, 11, 1248, 120);
		pnlCCCN.add(pnlTop);
		pnlTop.setLayout(null);
		
		JLabel lblNgayChamCong = new JLabel("Ngày chấm công:");
		lblNgayChamCong.setBounds(10, 18, 141, 24);
		pnlTop.add(lblNgayChamCong);
		lblNgayChamCong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblSanPhamSx = new JLabel("Sản phẩm sản xuất:");
		lblSanPhamSx.setBounds(401, 18, 169, 24);
		pnlTop.add(lblSanPhamSx);
		lblSanPhamSx.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		String cb[] = {"Áo thun", "Áo phông", "Áo sơ mi"};
		cboSP = new JComboBox(cb);
		cboSP.setBounds(567, 13, 105, 34);
		pnlTop.add(cboSP);
		cboSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCongdoan = new JLabel("Công đoạn:");
		lblCongdoan.setBounds(711, 18, 99, 24);
		pnlTop.add(lblCongdoan);
		lblCongdoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblXuong = new JLabel("Xưởng:");
		lblXuong.setBounds(979, 18, 68, 24);
		pnlTop.add(lblXuong);
		lblXuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		String cb1[] = {"Nhuộm", "Gia Công", "Kiểm tra"};
		cboCongDoan = new JComboBox(cb1);
		cboCongDoan.setBounds(820, 11, 105, 34);
		pnlTop.add(cboCongDoan);
		cboCongDoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		String cb2[] = {"Xưởng nhuộm 1", "Xưởng nhuộm 2", "Xưởng nhuộm 3"};
		cboXuong = new JComboBox(cb2);
		cboXuong.setBounds(1057, 11, 169, 34);
		pnlTop.add(cboXuong);
		cboXuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblCaLam = new JLabel("Ca làm:");
		lblCaLam.setBounds(10, 68, 68, 24);
		pnlTop.add(lblCaLam);
		lblCaLam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JCheckBox chckbxSang = new JCheckBox("Sáng");
		chckbxSang.setBackground(new Color(240, 248, 255));
		chckbxSang.setBounds(84, 70, 70, 21);
		pnlTop.add(chckbxSang);
		chckbxSang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JCheckBox chckbxToi = new JCheckBox("Tối ");
		chckbxToi.setBackground(new Color(240, 248, 255));
		chckbxToi.setBounds(186, 70, 70, 21);
		pnlTop.add(chckbxToi);
		chckbxToi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JCheckBox chckbxThem = new JCheckBox("Thêm");
		chckbxThem.setBackground(new Color(240, 248, 255));
		chckbxThem.setBounds(276, 70, 84, 21);
		pnlTop.add(chckbxThem);
		chckbxThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		 chckbxSang.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                if (e.getStateChange() == ItemEvent.DESELECTED && !chckbxToi.isSelected()) {
	                	chckbxToi.setSelected(true);
	                }
	            }
	        });
	        
		 chckbxToi.addItemListener(new ItemListener() {
	            @Override
	            public void itemStateChanged(ItemEvent e) {
	                if (e.getStateChange() == ItemEvent.DESELECTED && !chckbxSang.isSelected()) {
	                	chckbxSang.setSelected(true);
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
							ngayChamCong = dcNgayChamCong.getFullDate().toLocalDate();
							if (source == chckbxSang) {
								chckbxToi.setSelected(false);
		                        chckbxThem.setSelected(false);
		                        dsCNXCa = congNhanDao.getListCNtheoXuong(dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong());
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
		  
		                    } else if (source == chckbxToi) {
		                        chckbxSang.setSelected(false);
		                        chckbxThem.setSelected(false);
		                        dsCNXCa = congNhanDao.getListCNtheoXuong(dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong());
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
	                    
//	                    else if (source == chkThem) {
//	                        chkCaSang.setSelected(false);
//	                        chkCaToi.setSelected(false);
//	                    }
	                   
	         
	                }
	            }
	        };
	        
	        chckbxSang.addItemListener(itemListener);
	        chckbxToi.addItemListener(itemListener);
	        chckbxThem.addItemListener(itemListener);
		
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setBounds(810, 68, 80, 24);
		pnlTop.add(lblGhiChu);
		lblGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtGhiChu = new JTextField();
		txtGhiChu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGhiChu.setBounds(893, 65, 333, 30);
		pnlTop.add(txtGhiChu);
		txtGhiChu.setColumns(10);
		
		dcNgayChamCong = new JDateChooser();
		dcNgayChamCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dcNgayChamCong.setBackground(new Color(240, 248, 255));
		dcNgayChamCong.setDateFormatString("dd/MM/yyyy");
		dcNgayChamCong.setDate(new Date());
		dcNgayChamCong.setBounds(161, 18, 199, 30);
		pnlTop.add(dcNgayChamCong);

		JLabel lblTimKiemTheoTen = new JLabel("Tìm kiếm theo tên:");
		lblTimKiemTheoTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimKiemTheoTen.setBounds(20, 584, 154, 24);
		pnlCCCN.add(lblTimKiemTheoTen);
		
		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTimKiem.setColumns(10);
		txtTimKiem.setBounds(178, 581, 169, 30);
		pnlCCCN.add(txtTimKiem);
		
		JButton btnTimKiem = new JButton("");
		btnTimKiem.setIcon(new ImageIcon("C:\\Users\\ADMIN\\OneDrive\\Documents\\PTUD\\SalaryAndProduct-Management-System-main\\SalaryAndProduct-Management-System-main\\img\\icons\\icons8-search-30.png"));
		btnTimKiem.setBounds(357, 581, 30, 30);
		pnlCCCN.add(btnTimKiem);
		
		JLabel lblTong = new JLabel("Tổng số công nhân:");
		lblTong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTong.setBounds(457, 584, 169, 24);
		pnlCCCN.add(lblTong);
		
		txtNumCongNhan = new JTextField();
		txtNumCongNhan.setEditable(false);
		txtNumCongNhan.setBackground(new Color(240, 248, 255));
		txtNumCongNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumCongNhan.setColumns(10);
		txtNumCongNhan.setBounds(623, 581, 60, 27);
		pnlCCCN.add(txtNumCongNhan);
		
		JLabel lblDangLam = new JLabel("Đang làm:");
		lblDangLam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDangLam.setBounds(689, 584, 84, 24);
		pnlCCCN.add(lblDangLam);
		
		txtNumDilam = new JTextField();
		txtNumDilam.setEditable(false);
		txtNumDilam.setBackground(new Color(240, 248, 255));
		txtNumDilam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumDilam.setColumns(10);
		txtNumDilam.setBounds(783, 581, 60, 27);
		pnlCCCN.add(txtNumDilam);
		
		JButton btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setBackground(new Color(255, 255, 255));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCapNhat.setBounds(895, 577, 154, 40);
		pnlCCCN.add(btnCapNhat);
		
		JButton btnHoanTat = new JButton("Hoàn tất");
		btnHoanTat.setBackground(new Color(255, 255, 255));
		btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHoanTat.setBounds(1081, 577, 154, 40);
		pnlCCCN.add(btnHoanTat);
	        
		tblDsCN = new JTable();
	   	modelDsCN = new DefaultTableModel();
	   	tblDsCN.setModel(modelDsCN);
	    	modelDsCN.addColumn("Mã công nhân");
	    	modelDsCN.addColumn("Họ đệm");
	    	modelDsCN.addColumn("Tên công nhân");
	    	modelDsCN.addColumn("Ca làm");
	    	modelDsCN.addColumn("Sản lượng");
	    	modelDsCN.addColumn("Số giờ tăng ca");
	    	modelDsCN.addColumn("Ghi chú");
		JScrollPane scrollPane = new JScrollPane(tblDsCN);
		scrollPane.setBounds(10, 140, 1248, 422);
		pnlCCCN.add(scrollPane);
		
		 dsCNXCa = congNhanDao.getListCNtheoXuong(dsXuong.get(cboXuong.getSelectedIndex()).getMaXuong());
	        if(boPhanDaChamCongChua(dsCNXCa)) {
	        	docDuLieuTableDaCC(dsCNXCa);
	        	btnHoanTat.setEnabled(false);
	        	
	        }else {
	        	docDuLieuVaoTable(dsCNXCa);
	        }
	        
		btnHoanTat.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnTimKiem.addActionListener(this);
        
		return pnlCCCN;
	}

	public void clearTable() {
		modelDsCN.getDataVector().removeAllElements();
	}
	
	public void docDuLieuVaoTable(ArrayList<CongNhan> dsCN) {
		
		for(CongNhan cn: dsCN) {
				 Object[] rowData = {cn.getMaCN(), cn.getHo(),cn.getTen(), layCaLamViec(cn),"","0",""};
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
	
	public void docDuLieuTableDaCC(ArrayList<CongNhan> dsCN){
		for(int i=0 ; i< dsCN.size();i++) {
			for(BangChamCongCN bangCC: dsDaChamCong) {
				if(dsCN.get(i).getMaCN().equals(bangCC.getCn().getMaCN())) {
					Object[] rowData = {dsCN.get(i).getMaCN(), dsCN.get(i).getHo(),dsCN.get(i).getTen(), layCaLamViec(dsCN.get(i)), bangCC.getSoGioTangCa(), bangCC.getGhiChu()};
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
        String ma  = dayFormat.format(Date.valueOf(ngayChamCong))+ monthFormat.format(Date.valueOf(ngayChamCong)) +yearFormat.format(Date.valueOf(ngayChamCong)) + maCN;
        return ma;
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		int i =0;
		if(o==btnHoanTat) {
			 if(chckbxSang.isSelected()==true || chckbxToi.isSelected()==true) {
				for(CongNhan cn: dsCNXCa) {
					try {
						LocalDate ngayChamCong = dcNgayChamCong.getFullDate().toLocalDate();
						String maCC = phatSinhMaCC(ngayChamCong, cn.getMaCN());
						int soGioTangCa;
						if(modelDsCN.getValueAt(i, 6).toString().equals("")) {
							soGioTangCa =0;
						}
						else {
							soGioTangCa = Integer.parseInt(modelDsCN.getValueAt(i, 7).toString()) ;
						}
						int caLam=0;
						caLam = cn.getCaLamViec();
						
						int sanLuong = 0;
						String ghiChu;
						if(modelDsCN.getValueAt(i, 7).toString().equals("")) {
							ghiChu="";
						}else {
							ghiChu = String.valueOf(modelDsCN.getValueAt(i, 7)) ;
						}
						BangChamCongCN bangChamCongCN = new BangChamCongCN(maCC, null, ngayChamCong, soGioTangCa, caLam, caLam, rootPaneCheckingEnabled, rootPaneCheckingEnabled, ghiChu);
						chamCongCNDao.insertBangChamCongCN(bangChamCongCN);
						i++;
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				String ca;
				if(chckbxSang.isSelected()) {
					ca = "Sáng";
				}
				else
					ca= "Tối";
				btnHoanTat.setEnabled(false);
				try {
					JOptionPane.showMessageDialog(frame, "Bạn đã chấm công thành công cho  " +String.valueOf(cboXuong.getSelectedItem()) +" ca " +ca +" ngày " +String.valueOf(lblNgayChamCong.getFullDate()) );
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
							LocalDate ngayChamCong = dcNgayChamCong.getFullDate().toLocalDate();
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
							BangChamCongCN bangChamCongCN = new BangChamCongCN(maCC, dsCNXCa.get(n), ngayChamCong, soGioTangCa, caLam, caLam, rootPaneCheckingEnabled, rootPaneCheckingEnabled, ghiChu);
							chamCongCNDao.updateBangChamCongCN(bangChamCongCN);
									
									
									
									
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
					}
				}
			}
			String ca;
			if(chckbxSang.isSelected()) {
				ca = "Sáng";
			}
			else
				ca= "Tối";

			try {
				JOptionPane.showMessageDialog(frame, "Bạn đã cập nhật thành công bảng chấm công cho  " +String.valueOf(cboXuong.getSelectedItem()) +" ca " +ca +" ngày " +String.valueOf(dcNgayChamCong.getFullDate()) );
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