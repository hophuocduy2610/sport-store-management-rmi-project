package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import app.Client;
import entity.Address;
import entity.Staff;

public class QuanLyNhanVienGUI extends JFrame  implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1473211672150172396L;

	private JPanel khung;

	private JLabel lblMaNV;
	private JLabel lblTenNV;
	private JLabel lblNamSinh;
	private JLabel lblPhai;
	private JLabel lblChucVu;
	private JLabel lblDiaChi;
	private JLabel lblPhuong;
	private JLabel lblQuan;
	private JLabel lblThanhPho;
	private JLabel lblSdt;
	private JLabel lblTimKiem;

	@SuppressWarnings("rawtypes")
	private JComboBox cbGioiTinh, cbChucVu;
	private JDateChooser dpNamSinh;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtDiaChi;
	private JTextField txtPhuong;
	private JTextField txtQuan;
	private JTextField txtThanhPho;
	private JTextField txtSdt;
	private JTextField txtTimKiem;

	private JTable table;
	private DefaultTableModel tableModel;

	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;
	private JButton btnThoat;
//	private JButton btnTimKiem;

	String[] headers1 = { "Ma?? nh??n vi??n", "T??n nh??n vi??n", "Gi???i t??nh", "N??m sinh", "V??? tr??", "?????a ch???", "Ph?????ng",
			"Qu???n", "Th??nh ph???", "S??? ??i???n tho???i" };

	private Client client = new Client();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public QuanLyNhanVienGUI() throws RemoteException, MalformedURLException, NotBoundException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("H??? TH??NG QU???N L?? TH??NG TIN NH??N VI??N");
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		khung = new JPanel();
		khung.setBackground(new Color(255, 179, 0));
		khung.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(khung);
		khung.setLayout(null);

		JLabel lblQLNV = new JLabel("QU???N L?? NH??N VI??N");
		lblQLNV.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblQLNV.setBounds(500, 10, 600, 35);
		khung.add(lblQLNV);

		JPanel p1 = new JPanel();
		p1.setBackground(new Color(204, 204, 204));
		p1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th??ng tin nh??n vi??n:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		p1.setBounds(0, 80, 350, 684);
		khung.add(p1);
		p1.setLayout(null);

		// ----------------------------------------

		// B???ng Nh??n vi??n
		tableModel = new DefaultTableModel(headers1, 0);
		JPanel pnTable2 = new JPanel();
		pnTable2.setBounds(350, 80, 932, 620);
		pnTable2.setBackground(new Color(204, 204, 204));
		pnTable2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh S??ch Nh??n Vi??n",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pnTable2.setLayout(new GridLayout(1, 0, 0, 0));
		khung.add(pnTable2);
		JScrollPane scroll3;
		pnTable2.add(scroll3 = new JScrollPane(table = new JTable(tableModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		}));

		scroll3.setBackground(new Color(102, 0, 102));
		table.setBackground(new Color(204, 204, 204));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		scroll3.setViewportView(table);

		// ------------------------------------

		// add c??c Text v??o
		lblMaNV = new JLabel("M?? nh??n vi??n:");
		lblMaNV.setBounds(10, 30, 120, 14);
		p1.add(lblMaNV);
		txtMaNV = new JTextField();
		txtMaNV.setBounds(140, 27, 200, 20);
		txtMaNV.setEditable(false);
		p1.add(txtMaNV);
		txtMaNV.setColumns(10);

		lblTenNV = new JLabel("T??n Nh??n vi??n:");
		lblTenNV.setBounds(10, 70, 120, 14);
		p1.add(lblTenNV);
		txtTenNV = new JTextField();
		txtTenNV.setBounds(140, 67, 200, 20);
		p1.add(txtTenNV);
		txtTenNV.setColumns(10);

//		==========================
		lblPhai = new JLabel("Gi???i t??nh:");
		lblPhai.setBounds(10, 110, 120, 14);
		p1.add(lblPhai);
		cbGioiTinh = new JComboBox();
		cbGioiTinh.setEditable(false);
		cbGioiTinh.setBounds(140, 107, 200, 20);
		p1.add(cbGioiTinh);
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("N???");
		cbGioiTinh.addItem("Kh??c");

//		==========================
		lblChucVu = new JLabel("Ch???c v???:");
		lblChucVu.setBounds(10, 150, 120, 14);
		p1.add(lblChucVu);
		cbChucVu = new JComboBox();
		cbChucVu.setEditable(false);
		cbChucVu.setBounds(140, 147, 200, 20);
		p1.add(cbChucVu);
		cbChucVu.addItem("Qu???n L??");
		cbChucVu.addItem("Nh??n Vi??n");

		lblNamSinh = new JLabel("N??m sinh:");
		lblNamSinh.setBounds(10, 190, 120, 14);
		p1.add(lblNamSinh);
		dpNamSinh = new JDateChooser();
		dpNamSinh.setBounds(140, 187, 200, 20);
		dpNamSinh.setDateFormatString("dd-MM-yyyy");
		dpNamSinh.setLocale(Locale.US);
		p1.add(dpNamSinh);

//		============================
		lblDiaChi = new JLabel("?????a ch???:");
		lblDiaChi.setBounds(10, 230, 120, 14);
		p1.add(lblDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(140, 227, 200, 20);
		p1.add(txtDiaChi);
		txtDiaChi.setColumns(10);

//		=====================
		lblPhuong = new JLabel("Ph?????ng:");
		lblPhuong.setBounds(10, 270, 120, 14);
		p1.add(lblPhuong);
		txtPhuong = new JTextField();
		txtPhuong.setBounds(140, 267, 200, 20);
		p1.add(txtPhuong);
		txtPhuong.setColumns(10);

//		=====================
		lblQuan = new JLabel("Qu???n:");
		lblQuan.setBounds(10, 310, 120, 14);
		p1.add(lblQuan);
		txtQuan = new JTextField();
		txtQuan.setBounds(140, 307, 200, 20);
		p1.add(txtQuan);
		txtQuan.setColumns(10);

//		=====================
		lblThanhPho = new JLabel("Th??nh ph???:");
		lblThanhPho.setBounds(10, 350, 120, 14);
		p1.add(lblThanhPho);
		txtThanhPho = new JTextField();
		txtThanhPho.setBounds(140, 347, 200, 20);
		p1.add(txtThanhPho);
		txtThanhPho.setColumns(10);

//		============================
		lblSdt = new JLabel("S??? ??i???n tho???i:");
		lblSdt.setBounds(10, 390, 120, 14);
		p1.add(lblSdt);
		txtSdt = new JTextField();
		txtSdt.setBounds(140, 387, 200, 20);
		p1.add(txtSdt);
		txtSdt.setColumns(10);

		// Th??m
		btnThem = new JButton("Th??m ");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setBackground(new Color(226, 207, 72));
		btnThem.setBounds(40, 500, 120, 40);
		p1.add(btnThem);

		// S???a
		btnSua = new JButton("C???p Nh???t");
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSua.setBackground(new Color(226, 207, 72));
		btnSua.setBounds(180, 500, 120, 40);
		p1.add(btnSua);

		// X??a
		btnXoa = new JButton("X??a ");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setBackground(new Color(226, 207, 72));
		btnXoa.setBounds(40, 550, 120, 40);
		p1.add(btnXoa);

		// X??a r???ng
		btnXoaRong = new JButton("X??a Tr???ng");
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaRong.setBackground(new Color(226, 207, 72));
		btnXoaRong.setBounds(180, 550, 120, 40);
		p1.add(btnXoaRong);

//		======================================
		lblTimKiem = new JLabel("Nh???p TT Nh??n vi??n c???n t??m:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimKiem.setBounds(750, 45, 340, 35);
		khung.add(lblTimKiem);
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(980, 50, 200, 25);
		khung.add(txtTimKiem);

		btnThoat = new JButton();
		btnThoat.setBounds(0, 0, 60, 40);
		btnThoat.setIcon(new ImageIcon("assets/image/exit.png"));
		btnThoat.setBackground(new Color(51, 255, 51));
		khung.add(btnThoat);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
		
		renderDataToTable();

		btnThoat.addActionListener((QuanLyNhanVienGUI) -> {
			int confirmAlert = JOptionPane.showConfirmDialog(this, "Ba??n ch????c ch????n mu????n thoa??t !!!!", "Nh???c nh???",
					JOptionPane.YES_NO_OPTION);
			if (confirmAlert == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this, "Ca??m ??n ba??n ??a?? s???? du??ng di??ch vu??");
				MainGUI mainGUI;
				try {
					mainGUI = new MainGUI();
					mainGUI.setVisible(true);
					setVisible(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// -----------------------X??? L?? T??m Ki???m-------------------------------------//

		txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				TimKiemKeyRelease(evt);
			}

			private void TimKiemKeyRelease(KeyEvent evt) {
				// TODO Auto-generated method stub
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				String search = txtTimKiem.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModel);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		
		//----------------------X??? l?? ????a d??? li???u t??? b???ng v??o c??c text field-------------------------------------//
				table.addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {
						SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

						/*
						 * "Ma?? nh??n vi??n", "T??n nh??n vi??n", "Gi???i t??nh", "N??m sinh", "V??? tr??",
						 * "?????a ch???", "Ph?????ng", "Qu???n", "Th??nh ph???", "S??? ??i???n tho???i"
						 */
						// TODO Auto-generated method stub
						int rowclicked = table.getSelectedRow();
						txtMaNV.setText(tableModel.getValueAt(rowclicked, 0).toString());
						txtTenNV.setText(tableModel.getValueAt(rowclicked, 1).toString());
						cbGioiTinh.setSelectedItem(tableModel.getValueAt(rowclicked, 2).toString());
						try {
							dpNamSinh.setDate(formatDate.parse(tableModel.getValueAt(rowclicked, 3).toString()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						cbChucVu.setSelectedItem(tableModel.getValueAt(rowclicked, 4).toString());
						txtDiaChi.setText(tableModel.getValueAt(rowclicked, 5).toString());
						txtPhuong.setText(tableModel.getValueAt(rowclicked, 6).toString());
						txtQuan.setText(tableModel.getValueAt(rowclicked, 7).toString());
						txtThanhPho.setText(tableModel.getValueAt(rowclicked, 8).toString());
						txtSdt.setText(tableModel.getValueAt(rowclicked, 9).toString());
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});
	}

	// main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNhanVienGUI frame = new QuanLyNhanVienGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			try {
				add();
			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o.equals(btnXoa)) {
			try {
				delete();
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o.equals(btnSua)) {
			try {
				update();
			} catch (HeadlessException | RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (o.equals(btnXoaRong)) {
			clearTextField();
		}
	}

//----------------------X??? L?? ????a d??? li???u t??? Database l??n b???ng-------------------------------------//

	public void renderDataToTable() throws RemoteException, MalformedURLException, NotBoundException {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		List<Staff> listStaffs = client.getStaffDAO().getListStaffs();
		int columns = headers1.length;
		Object[] obj;
		int rows = listStaffs.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Staff staff = listStaffs.get(i);
				obj = new Object[columns];
				obj[0] = staff.getId();
				obj[1] = staff.getName();
				obj[2] = staff.getGender();
				obj[3] = formatDate.format(staff.getDateOfBirth());
				obj[4] = staff.getPosition();
				obj[5] = staff.getAddress().getAddress();
				obj[6] = staff.getAddress().getWard();
				obj[7] = staff.getAddress().getDistrict();
				obj[8] = staff.getAddress().getCity();
				obj[9] = staff.getPhone();
				tableModel.addRow(obj);
			}
		}
	}

// ----------------------X??? l?? s??? ki???n th??m th??ng tin nh??n vi??n-------------------------------------//

	private void add() throws RemoteException, MalformedURLException, NotBoundException {
		String maNV = client.getStaffDAO().generateStaffID();
		String tenNV = txtTenNV.getText();
		String gioiTinh = cbGioiTinh.getSelectedItem().toString();
		Date namSinh = dpNamSinh.getDate();
		String chucVu = cbChucVu.getSelectedItem().toString();
		String diaChi = txtDiaChi.getText();
		String phuong = txtPhuong.getText();
		String quan = txtQuan.getText();
		String thanhPho = txtThanhPho.getText();
		String sdt = txtSdt.getText();

		Address address = new Address(thanhPho, quan, phuong, diaChi);
		Staff staff = new Staff(maNV, tenNV, namSinh, gioiTinh, sdt, chucVu, address, ABORT);

		client.getStaffDAO().addStaff(staff);
		clearTable();
		updateStaffTable();
		clearTextField();
	}

// ----------------------X??? l?? s??? ki???n x??a th??ng tin nh??n vi??n-------------------------------------//

	private void delete() throws HeadlessException, RemoteException, MalformedURLException, NotBoundException {
		int row = table.getSelectedRow();
		if (row != -1) {
			int confirmAlert = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n xo?? Th??ng Tin Nh??n Vi??n n??y kh??ng?",
					"Ch?? ??", JOptionPane.YES_NO_OPTION);
			if (confirmAlert == JOptionPane.YES_OPTION) {
				String maNV = table.getValueAt(row, 0).toString();
				if (client.getStaffDAO().deleteStaff(maNV)) {
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(this, "???? xo??!");
					clearTextField();
				} else
					JOptionPane.showMessageDialog(this, "Xo?? th???t b???i!");
			}
		} else
			JOptionPane.showMessageDialog(this, "B???n ch??a ch???n Th??ng Tin Nh??n Vi??n C???n X??a!");
	}

// ----------------------X??? l?? s??? ki???n c???p nh???t th??ng tin nh??n vi??n-------------------------------------//

	private void update() throws RemoteException, HeadlessException, MalformedURLException, NotBoundException {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		String maNV = txtMaNV.getText();
		String tenNV = txtTenNV.getText();
		String gioiTinh = cbGioiTinh.getSelectedItem().toString();
		Date namSinh = dpNamSinh.getDate();
		String chucVu = cbChucVu.getSelectedItem().toString();
		String diaChi = txtDiaChi.getText();
		String phuong = txtPhuong.getText();
		String quan = txtQuan.getText();
		String thanhPho = txtThanhPho.getText();
		String sdt = txtSdt.getText();
		int row = table.getSelectedRow();
		if (row != -1) {

			Address address = new Address(thanhPho, quan, phuong, diaChi);
			Staff staff = new Staff(maNV, tenNV, namSinh, gioiTinh, sdt, chucVu, address, ABORT);

			if (client.getStaffDAO().updateStaff(staff)) {
				table.setValueAt(maNV, row, 0);
				table.setValueAt(tenNV, row, 1);
				table.setValueAt(gioiTinh, row, 2);
				table.setValueAt(formatDate.format(namSinh), row, 3);
				table.setValueAt(chucVu, row, 4);
				table.setValueAt(diaChi, row, 5);
				table.setValueAt(phuong, row, 6);
				table.setValueAt(quan, row, 7);
				table.setValueAt(thanhPho, row, 8);
				table.setValueAt(sdt, row, 9);
				JOptionPane.showMessageDialog(this, "???? c???p nh???t!");
				clearTextField();
				updateStaffTable();
			} else
				JOptionPane.showMessageDialog(this, "C???p nh???t th???t b???i!");
		} else
			JOptionPane.showMessageDialog(this, "B???n ch??a ch???n Th??ng Tin Nh??n Vi??n c???n c???p nh???t!");
	}

// ----------------------X??? l?? s??? ki???n c???p nh???t b???ng nh??n vi??n-------------------------------------//

	private void updateStaffTable() throws RemoteException, MalformedURLException, NotBoundException {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Ma?? nh??n vi??n");
		tableModel.addColumn("T??n nh??n vi??n");
		tableModel.addColumn("Gi???i t??nh");
		tableModel.addColumn("N??m sinh");
		tableModel.addColumn("Ch???c v???");
		tableModel.addColumn("?????a ch???");
		tableModel.addColumn("Ph?????ng");
		tableModel.addColumn("Qu???n");
		tableModel.addColumn("Th??nh ph???");
		tableModel.addColumn("S??? ??i???n tho???i");
		List<Staff> listStaffs = client.getStaffDAO().getListStaffs();
		for (Staff staff : listStaffs) {
			String[] rowData1 = { staff.getId(), staff.getName(), staff.getGender(), formatDate.format(staff.getDateOfBirth()).toString(),
					staff.getPosition(), staff.getAddress().getAddress(), staff.getAddress().getWard(),
					staff.getAddress().getDistrict(), staff.getAddress().getCity(), staff.getPhone() };
			tableModel.addRow(rowData1);
		}
		table.setModel(tableModel);
	}

// ------------------------------X??a s???ch b???ng----------------------------------------//

	private void clearTable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged();
	}

// ----------------------X??a tr???ng c??c text field-------------------------------------//

	private void clearTextField() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		cbGioiTinh.setSelectedIndex(0);
		dpNamSinh.setCalendar(null);
		cbChucVu.setSelectedIndex(0);
		txtDiaChi.setText("");
		txtPhuong.setText("");
		txtQuan.setText("");
		txtThanhPho.setText("");
		txtSdt.setText("");
	}
}
