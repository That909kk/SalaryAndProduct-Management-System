package gUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import connectDB.ConnectDB;
import dao.BoPhan_DAO;
import dao.NhanVien_DAO;
import entity.BoPhan;
import entity.NhanVien;

public class ChamCongNV_GUI implements ListSelectionListener {

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
    private ArrayList<NhanVien> dsNV;
    private ArrayList<NhanVien> dsNVBP;
	
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
		createMenuGUI();
		createGUI();
	}



	private void createGUI() {
		 try {
	            ConnectDB.getInstance().connect();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		BoPhan_DAO boPhanDao = new BoPhan_DAO();
		dsBP = new ArrayList<BoPhan>();
		
		dsBP = boPhanDao.getdsBoPhan();
		
		nhanVienDao = new NhanVien_DAO();
		dsNV = new ArrayList<NhanVien>();
		dsNV = nhanVienDao.getListNV();
		
		dsNVBP = new ArrayList<NhanVien>();
		
		 
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1000,40));;
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
	
		
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
		for(BoPhan bp: dsBP) {
			modelBoPhan.addElement(bp.getTenBoPhan());
			
		}
		
	    cboBoPhan = new JComboBox<String>(modelBoPhan);
	    cboBoPhan.setPreferredSize(new Dimension(100,40));
	    String selectedValue = (String) cboBoPhan.getSelectedItem();
        for(BoPhan bp: dsBP) {
        	if(bp.getTenBoPhan().equals(selectedValue)) {
        		selectedValue = bp.getMaBoPhan();
        	}
        	
        }
        for(NhanVien nv: dsNV) {
        	if(nv.getBoPhan().getMaBoPhan().equals(selectedValue)) {
        		dsNVBP.add(nv);
        	}
        }
        
        cboBoPhan.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedValue = (String) combo.getSelectedItem();
                dsNVBP.clear();
                for(BoPhan bp: dsBP) {
                	if(bp.getTenBoPhan().equals(selectedValue)) {
                		selectedValue = bp.getMaBoPhan();
                	}
                	
                }
                for(NhanVien nv: dsNV) {
                	if(nv.getBoPhan().getMaBoPhan().equals(selectedValue)) {
                		dsNVBP.add(nv);
                	}
                }
                clearTable();
                for(NhanVien nv: dsNVBP) {
                	Object[] rowData = {nv.getMaNV(), nv.getHo(),nv.getTen(), nv.getCaLamViec(), new JRadioButton(), new JRadioButton(), new JRadioButton(),"0",""};
                	modelDsNV.addRow(rowData);
                }
      
            }
        });
	    
	    lblCaLam = new JLabel("Ca Làm:");
        chkCaSang = new JCheckBox("Ca sáng"); 
        chkCaToi = new JCheckBox("Ca Tối");
        chkThem = new JCheckBox("Thêm"); 
        
        
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
        b.add(chkThem);
        b.add(Box.createHorizontalStrut(30));
        
        
        JPanel pnTable = new JPanel();
        frame.getContentPane().add(pnTable, BorderLayout.CENTER);
        String[] cols_nhanVien = {"Mã nhân viên", "Họ đệm", "Tên nhân viên", "Ca làm", "Đi làm", "Vắng phép", "Vắng không phép", "Số giờ tăng ca","Ghi chú"};
        modelDsNV = new DefaultTableModel(cols_nhanVien, 0);
        tblDsNV = new JTable(modelDsNV) {
        	public void tableChanged(TableModelEvent e) {
        		super.tableChanged(e);
        		
        		repaint();
        	}
        };
        
        for(NhanVien nv: dsNVBP) {
        	Object[] rowData = {nv.getMaNV(), nv.getHo(),nv.getTen(), nv.getCaLamViec(), new JRadioButton(), new JRadioButton(), new JRadioButton(),"0",""};
        	modelDsNV.addRow(rowData);
        }
        
        int n=0;
        for(NhanVien nv: dsNVBP) {	
        	ButtonGroup bg = new ButtonGroup();
        	bg.add((JRadioButton) modelDsNV.getValueAt(n, 4));
        	bg.add((JRadioButton) modelDsNV.getValueAt(n, 5));
        	bg.add((JRadioButton) modelDsNV.getValueAt(n, 6));
        	n++;
        	
        }
        
        
        tblDsNV.getColumn("Đi làm").setCellRenderer(new Radiorenderer());
        tblDsNV.getColumn("Đi làm").setCellEditor(new RadioEditor(new JCheckBox()));
        
        tblDsNV.getColumn("Vắng phép").setCellRenderer(new Radiorenderer());
        tblDsNV.getColumn("Vắng phép").setCellEditor(new RadioEditor(new JCheckBox()));
        
        tblDsNV.getColumn("Vắng không phép").setCellRenderer(new Radiorenderer());
        tblDsNV.getColumn("Vắng không phép").setCellEditor(new RadioEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(tblDsNV);
        pnTable.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(1220,540));
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
        scrollPane.setColumnHeaderView(rdbtnNewRadioButton);
        tblDsNV.getSelectionModel().addListSelectionListener(this);
        
        
        JPanel pnBottom = new JPanel();
        frame.getContentPane().add(pnBottom, BorderLayout.SOUTH);
        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.X_AXIS));
        Box b2 = Box.createHorizontalBox();
        pnBottom.add(b2);
        
        JLabel lblTimKiemTen = new JLabel("Tìm kiếm theo tên");
        txtTimKiemTen = new JTextField(5);
        btnTimKiemTen = new JButton("Tìm");
        btnCapNhat = new JButton("Cập nhật");
        btnHoanTat = new JButton("Hoàn tất");
        
        b2.add(Box.createHorizontalStrut(15));
        b2.add(lblTimKiemTen);
        b2.add(Box.createHorizontalStrut(10));
        b2.add(txtTimKiemTen);
        b2.add(Box.createHorizontalStrut(10));
        b2.add(btnTimKiemTen);
        b2.add(Box.createHorizontalStrut(400));
        b2.add(btnCapNhat);
        b2.add(Box.createHorizontalStrut(40));
        b2.add(btnHoanTat);
        b2.add(Box.createHorizontalStrut(10));
        pnBottom.setBorder(new EmptyBorder(0, 0, 20,0));
        
        
        
        
        
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
	


	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
