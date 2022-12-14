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
import entity.Product;
import entity.ProductType;
import entity.Supplier;

public class QuanLySPGUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1473211672150172396L;
	private JPanel khung;

	private JLabel lblMaSanPham;
	private JLabel lblTenSanPham;
	private JLabel lblLoaiSP;
	private JLabel lblNgayNhap;
	private JLabel lblNhaCC;
	private JLabel lblSoLuong;
	private JLabel lblDonGia;
	private JLabel lblTimKiem;

	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JTextField txtLoaiSanPham;
	private JTextField txtNhaCungCap;
	private JDateChooser dpNgayNhap;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;
	private JTextField txtTimKiem;

	private JTable tblDSSP;
	private DefaultTableModel tableModelDSSP;
	private JTable tblNCC;
	private DefaultTableModel tableModelNCC;
	private JTable tblLoaiSP;
	private DefaultTableModel tableModelLoaiSP;

	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;
	private JButton btnThoat;

	private Client client = new Client();

	String[] headersNCC = { "Ma?? Nh?? Cung C???p", "T??n Nh?? Cung C???p" };
	String[] headersDSSP = { "Ma?? S???n Ph???m", "T??n S???n Ph???m", "Nh?? Cung C???p", "Lo???i S???n Ph???m", "Ng??y Nh???p", "S??? L?????ng",
			"????n Gi??" };
	String[] headersLoaiSP = { "Ma?? Lo???i S???n Ph???m", "T??n Lo???i S???n Ph???m" };

	SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

	public QuanLySPGUI() throws RemoteException, MalformedURLException, NotBoundException {

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("H??? TH??NG QU???N L?? TH??NG TIN S???N PH???M");
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		khung = new JPanel();
		khung.setBackground(new Color(255, 179, 0));
		khung.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(khung);
		khung.setLayout(null);

		JLabel lblQLSP = new JLabel("QU???N L?? S???N PH???M");
		lblQLSP.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblQLSP.setBounds(480, 10, 600, 35);
		khung.add(lblQLSP);

		JPanel p1 = new JPanel();
		p1.setBackground(new Color(229, 228, 223));
		p1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th??ng tin s???n ph???m:",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		p1.setBounds(0, 80, 380, 700);
		khung.add(p1);
		p1.setLayout(null);

//		============================Danh s??ch s???n ph???m=========================================

		tableModelDSSP = new DefaultTableModel(headersDSSP, 0);
		JPanel pnTable = new JPanel();
		pnTable.setBounds(380, 80, 900, 220);
		pnTable.setBackground(new Color(204, 204, 204));
		pnTable.setForeground(new Color(204, 204, 204));
		pnTable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh S??ch S???n Ph???m",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pnTable.setLayout(new GridLayout(1, 0, 0, 0));
		khung.add(pnTable);
		JScrollPane scroll;
		pnTable.add(scroll = new JScrollPane(tblDSSP = new JTable(tableModelDSSP) {
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
		scroll.setBackground(new Color(102, 0, 102));
		tblDSSP.setForeground(new Color(0, 0, 0));
		tblDSSP.setBackground(new Color(204, 204, 204));
		tblDSSP.setRowHeight(25);
		tblDSSP.setAutoCreateRowSorter(true);
		scroll.setViewportView(tblDSSP);

//		==========================Danh s??ch Nh?? Cung C???p=====================================

		tableModelNCC = new DefaultTableModel(headersNCC, 0);
		JPanel pnTableNCC = new JPanel();
		pnTableNCC.setBounds(380, 500, 900, 200);
		pnTableNCC.setBackground(new Color(204, 204, 204));
		pnTableNCC.setForeground(new Color(204, 204, 204));
		pnTableNCC.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh S??ch Nh?? Cung C???p",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pnTableNCC.setLayout(new GridLayout(1, 0, 0, 0));
		khung.add(pnTableNCC);
		JScrollPane scrollNCC;
		pnTableNCC.add(scrollNCC = new JScrollPane(tblNCC = new JTable(tableModelNCC) {
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
		scrollNCC.setBackground(new Color(102, 0, 102));
		tblNCC.setForeground(new Color(0, 0, 0));
		tblNCC.setBackground(new Color(204, 204, 204));
		tblNCC.setRowHeight(25);
		tblNCC.setAutoCreateRowSorter(true);
		scrollNCC.setViewportView(tblNCC);

//		===================================

		// Danh s??ch lo???i s???n ph???m
		tableModelLoaiSP = new DefaultTableModel(headersLoaiSP, 0);
		JPanel pnTableLoaiSP = new JPanel();
		pnTableLoaiSP.setBounds(380, 300, 900, 200);
		pnTableLoaiSP.setBackground(new Color(204, 204, 204));
		pnTableLoaiSP.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh S??ch Lo???i S???n Ph???m",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pnTableLoaiSP.setLayout(new GridLayout(1, 0, 0, 0));
		khung.add(pnTableLoaiSP);
		JScrollPane scrollLoaiSP;
		pnTableLoaiSP.add(scrollLoaiSP = new JScrollPane(tblLoaiSP = new JTable(tableModelLoaiSP) {
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
		scrollLoaiSP.setBackground(new Color(102, 0, 102));
		tblLoaiSP.setForeground(new Color(0, 0, 0));
		tblLoaiSP.setBackground(new Color(204, 204, 204));
		tblLoaiSP.setRowHeight(25);
		tblLoaiSP.setAutoCreateRowSorter(true);
		scrollLoaiSP.setViewportView(tblLoaiSP);

		// ------------------------------------

		// add c??c Text v??o

		lblMaSanPham = new JLabel("M?? S???n Ph???m:");
		lblMaSanPham.setBounds(20, 50, 120, 14);
		p1.add(lblMaSanPham);
		txtMaSanPham = new JTextField();
		txtMaSanPham.setBounds(140, 47, 200, 20);
		txtMaSanPham.setEditable(false);
		p1.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);

		lblTenSanPham = new JLabel("T??n S???n Ph???m:");
		lblTenSanPham.setBounds(20, 100, 120, 14);
		p1.add(lblTenSanPham);
		txtTenSanPham = new JTextField();
		txtTenSanPham.setBounds(140, 97, 200, 20);
		p1.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);

		lblNgayNhap = new JLabel("Ng??y nh???p:");
		lblNgayNhap.setBounds(20, 150, 120, 14);
		p1.add(lblNgayNhap);
		dpNgayNhap = new JDateChooser();
		dpNgayNhap.setBounds(140, 147, 200, 20);
		dpNgayNhap.setDateFormatString("dd-MM-yyyy");
		dpNgayNhap.setLocale(Locale.US);
		p1.add(dpNgayNhap);

//		==========================

		lblLoaiSP = new JLabel("Lo???i S???n Ph???m:");
		lblLoaiSP.setBounds(20, 200, 120, 14);
		p1.add(lblLoaiSP);
		txtLoaiSanPham = new JTextField();
		txtLoaiSanPham.setEditable(false);
		txtLoaiSanPham.setBounds(140, 197, 200, 20);
		p1.add(txtLoaiSanPham);
		txtLoaiSanPham.setColumns(10);

//		==========================
		lblNhaCC = new JLabel("Nh?? cung c???p:");
		lblNhaCC.setBounds(20, 250, 120, 14);
		p1.add(lblNhaCC);
		txtNhaCungCap = new JTextField();
		txtNhaCungCap.setEditable(false);
		txtNhaCungCap.setBounds(140, 247, 200, 20);
		p1.add(txtNhaCungCap);

//		============================
		lblSoLuong = new JLabel("S??? L?????ng:");
		lblSoLuong.setBounds(20, 300, 120, 14);
		p1.add(lblSoLuong);
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(140, 297, 200, 20);
		p1.add(txtSoLuong);
		txtSoLuong.setColumns(10);

//		============================
		lblDonGia = new JLabel("????n Gi??:");
		lblDonGia.setBounds(20, 350, 120, 14);
		p1.add(lblDonGia);
		txtDonGia = new JTextField();
		txtDonGia.setBounds(140, 347, 200, 20);
		p1.add(txtDonGia);
		txtDonGia.setColumns(10);

		btnThoat = new JButton();
		btnThoat.setBounds(0, 0, 60, 40);
		btnThoat.setIcon(new ImageIcon("assets/image/exit.png"));
		btnThoat.setBackground(new Color(51, 255, 51));
		khung.add(btnThoat);

		// Th??m
		btnThem = new JButton("Th??m");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setBackground(new Color(226, 207, 72));
		btnThem.setBounds(60, 400, 120, 40);
		p1.add(btnThem);

		// C???p nh???t
		btnSua = new JButton("C???p Nh???t");
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnSua.setForeground(new Color(0, 0, 0));
		btnSua.setBackground(new Color(226, 207, 72));
		btnSua.setBounds(200, 400, 120, 40);
		p1.add(btnSua);

		// X??a
		btnXoa = new JButton("X??a");
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoa.setForeground(new Color(0, 0, 0));
		btnXoa.setBackground(new Color(226, 207, 72));
		btnXoa.setBounds(60, 470, 120, 40);
		p1.add(btnXoa);

		// X??a r???ng
		btnXoaRong = new JButton("X??a R???ng");
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnXoaRong.setForeground(new Color(0, 0, 0));
		btnXoaRong.setBackground(new Color(226, 207, 72));
		btnXoaRong.setBounds(200, 470, 120, 40);
		p1.add(btnXoaRong);

//		======================================
		lblTimKiem = new JLabel("Nh???p TT S???n Ph???m c???n t??m:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTimKiem.setBounds(820, 45, 340, 35);
		khung.add(lblTimKiem);
		txtTimKiem = new JTextField();
		txtTimKiem.setBounds(1050, 50, 200, 25);
		khung.add(txtTimKiem);

////------------------------------------------------------------------------------//
//		//Dky sk
//		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);

		renderDataProductToTable();
		renderDataProductTypeToTable();
		renderDataSupplierToTable();

		// -----------------------X??? L?? tho??t-------------------------------------//

		btnThoat.addActionListener((QuanLyKhachHangGUI) -> {
			int loinhac = JOptionPane.showConfirmDialog(this, "Ba??n ch????c ch????n mu????n thoa??t !!!!", "Nh???c nh???",
					JOptionPane.YES_NO_OPTION);
			if (loinhac == JOptionPane.YES_OPTION) {
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
				SearchByKeyRelease(evt);
			}

			private void SearchByKeyRelease(KeyEvent evt) {
				// TODO Auto-generated method stub
				tableModelDSSP = (DefaultTableModel) tblDSSP.getModel();
				String search = txtTimKiem.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tableModelDSSP);
				tblDSSP.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});

		// ------ X??? l?? ????a d??? li???u t??? b???ng s???n ph???m v??o c??c text field ------//
		tblDSSP.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowclicked = tblDSSP.getSelectedRow();
				try {
					txtMaSanPham.setText(tableModelDSSP.getValueAt(rowclicked, 0).toString());
					txtTenSanPham.setText(tableModelDSSP.getValueAt(rowclicked, 1).toString());
					txtNhaCungCap.setText(client.getSupplierDAO().getIDSupplierByName(tableModelDSSP.getValueAt(rowclicked, 2).toString()));
					txtLoaiSanPham.setText(client.getProductTypeDAO().getIDProductTypeByName(tableModelDSSP.getValueAt(rowclicked, 3).toString()));
				} catch (RemoteException | MalformedURLException | NotBoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					dpNgayNhap.setDate(formatDate.parse(tableModelDSSP.getValueAt(rowclicked, 4).toString()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtSoLuong.setText(tableModelDSSP.getValueAt(rowclicked, 5).toString());
				txtDonGia.setText(tableModelDSSP.getValueAt(rowclicked, 6).toString());
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
		// ------ X??? l?? ????a d??? li???u t??? b???ng Nh?? cung c???p v??o c??c text field ----------//
		tblNCC.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowclicked = tblNCC.getSelectedRow();
				txtNhaCungCap.setText(tableModelNCC.getValueAt(rowclicked, 0).toString());
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
		// ----- X??? l?? ????a d??? li???u t??? b???ng lo???i s???n ph???m v??o c??c text fiel ------//
		tblLoaiSP.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int rowclicked = tblLoaiSP.getSelectedRow();
				txtLoaiSanPham.setText(tableModelLoaiSP.getValueAt(rowclicked, 0).toString());
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

//	//main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLySPGUI frame = new QuanLySPGUI();
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

	// ---------X??? L?? ????a d??? li???u S???n ph???m t??? Database l??n b???ng --------------//
	public void renderDataProductToTable() throws RemoteException, MalformedURLException, NotBoundException {
		List<Product> listProducts = client.getProductDAO().getListProducts();
		int columns = headersDSSP.length;
		Object[] obj;
		int rows = listProducts.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Product product = listProducts.get(i);
				obj = new Object[columns];
				obj[0] = product.getId();
				obj[1] = product.getName();
				obj[2] = client.getSupplierDAO().getNameSupplierById(product.getSupplier().getId());
				obj[3] = client.getProductTypeDAO().getNameProductTypeByID(product.getProductType().getId());
				obj[4] = formatDate.format(product.getImportDate());
				obj[5] = product.getQuantity();
				obj[6] = product.getPrice();
				tableModelDSSP.addRow(obj);
			}
		}
	}

	// -----X??? L?? ????a d??? li???u Nh?? Cung C???p t??? Database l??n b???ng-----//
	public void renderDataSupplierToTable() throws RemoteException, MalformedURLException, NotBoundException {
		List<Supplier> listSuppliers = client.getSupplierDAO().getListSuppliers();
		int columns = headersNCC.length;
		Object[] obj;
		int rows = listSuppliers.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				Supplier supplier = listSuppliers.get(i);
				obj = new Object[columns];
				obj[0] = supplier.getId();
				obj[1] = supplier.getName();
				tableModelNCC.addRow(obj);
			}
		}
	}

	// --------- X??? L?? ????a d??? li???u Lo???i S???n Ph???m t??? Database l??n b???ng -----------//
	public void renderDataProductTypeToTable() throws RemoteException, MalformedURLException, NotBoundException {
		List<ProductType> listProductTypes = client.getProductTypeDAO().getListProductTypes();
		int columns = headersLoaiSP.length;
		Object[] obj;
		int rows = listProductTypes.size();
		if (rows > 0) {
			for (int i = 0; i < rows; i++) {
				ProductType productType = listProductTypes.get(i);
				obj = new Object[columns];
				obj[0] = productType.getId();
				obj[1] = productType.getName();
				tableModelLoaiSP.addRow(obj);
			}
		}
	}

	// ----------- X??? l?? s??? ki???n th??m th??ng tin s???n ph???m ----------//

	private void add() throws RemoteException, MalformedURLException, NotBoundException {
		String maSP = client.getProductDAO().generateProductID();
		String tenSP = txtTenSanPham.getText();
		String maNCC = txtNhaCungCap.getText();
		String maLoaiSP = txtLoaiSanPham.getText();
		Date ngayNhap = dpNgayNhap.getDate();
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		Double donGia = Double.parseDouble(txtDonGia.getText());

		ProductType productType = new ProductType(maLoaiSP);
		Supplier supplier = new Supplier(maNCC);
		Product product = new Product(maSP, tenSP, soLuong, donGia, ngayNhap, productType, supplier, ABORT);

		client.getProductDAO().addProduct(product);
		clearTable();
		updateProductTable();
		clearTextField();
	}

	// ------- X??? l?? s??? ki???n x??a th??ng tin s???n ph???m ---------//

	private void delete() throws HeadlessException, RemoteException, MalformedURLException, NotBoundException {
		int row = tblDSSP.getSelectedRow();
		if (row != -1) {
			int confirmAlert = JOptionPane.showConfirmDialog(this, "B???n c?? mu???n xo?? Th??ng Tin S???n Ph???m n??y kh??ng?",
					"Ch?? ??", JOptionPane.YES_NO_OPTION);
			if (confirmAlert == JOptionPane.YES_OPTION) {
				String maSP = tblDSSP.getValueAt(row, 0).toString();
				if (client.getProductDAO().deleteProduct(maSP)) {
					tableModelDSSP.removeRow(row);
					JOptionPane.showMessageDialog(this, "???? xo??!");
					clearTextField();
				} else
					JOptionPane.showMessageDialog(this, "Xo?? th???t b???i!");
			}
		} else
			JOptionPane.showMessageDialog(this, "B???n ch??a ch???n Th??ng Tin S???n Ph???m C???n X??a!");
	}

	// ------- X??? l?? s??? ki???n c???p nh???t th??ng tin s???n ph???m ---------//

	private void update() throws RemoteException, HeadlessException, MalformedURLException, NotBoundException {
		String maSP = txtMaSanPham.getText();
		String tenSP = txtTenSanPham.getText();
		String maNCC = txtNhaCungCap.getText();
		String maLoaiSP = txtLoaiSanPham.getText();
		Date ngayNhap = dpNgayNhap.getDate();
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		Double donGia = Double.parseDouble(txtDonGia.getText());

		int row = tblDSSP.getSelectedRow();

		if (row != -1) {
			ProductType productType = new ProductType(maLoaiSP);
			Supplier supplier = new Supplier(maNCC);
			Product product = new Product(maSP, tenSP, soLuong, donGia, ngayNhap, productType, supplier, ABORT);
			if (client.getProductDAO().udateProduct(product)) {
				tblDSSP.setValueAt(maSP, row, 0);
				tblDSSP.setValueAt(tenSP, row, 1);
				tblDSSP.setValueAt(client.getSupplierDAO().getNameSupplierById(maNCC), row, 2);
				tblDSSP.setValueAt(client.getProductTypeDAO().getNameProductTypeByID(maLoaiSP), row, 3);
				tblDSSP.setValueAt(ngayNhap, row, 4);
				tblDSSP.setValueAt(soLuong, row, 5);
				tblDSSP.setValueAt(donGia, row, 6);
				JOptionPane.showMessageDialog(this, "???? c???p nh???t!");
				clearTextField();
				updateProductTable();
			} else
				JOptionPane.showMessageDialog(this, "C???p nh???t th???t b???i!");
		} else
			JOptionPane.showMessageDialog(this, "B???n ch??a ch???n Th??ng Tin S???n Ph???m c???n c???p nh???t!");
	}

	// -------- X??? l?? s??? ki???n c???p nh???t b???ng s???n ph???m ---------//
	private void updateProductTable() throws RemoteException, MalformedURLException, NotBoundException {
		tableModelDSSP = new DefaultTableModel();
//			tableModelDSSP.addColumn(headersDSSP);
		tableModelDSSP.addColumn("Ma?? s???n ph???m");
		tableModelDSSP.addColumn("T??n s???n ph???m");
		tableModelDSSP.addColumn("Nh?? cung c???p");
		tableModelDSSP.addColumn("Lo???i s???n ph???m");
		tableModelDSSP.addColumn("Ng??y nh???p");
		tableModelDSSP.addColumn("S??? l?????ng");
		tableModelDSSP.addColumn("????n gi??");
		List<Product> listProducts = client.getProductDAO().getListProducts();
		for (Product product : listProducts) {
			String[] rowData1 = { product.getId(), product.getName(),
					client.getSupplierDAO().getNameSupplierById(product.getSupplier().getId()),
					client.getProductTypeDAO().getNameProductTypeByID(product.getProductType().getId()),
					formatDate.format(product.getImportDate()), String.valueOf(product.getQuantity()),
					String.valueOf(product.getPrice()) };
			tableModelDSSP.addRow(rowData1);
		}
		tblDSSP.setModel(tableModelDSSP);
	}

	// ------------------ X??a s???ch b???ng --------------------//

	private void clearTable() {
		DefaultTableModel dm = (DefaultTableModel) tblDSSP.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged();
	}

	// ------------- X??a tr???ng c??c text field --------------//

	private void clearTextField() {
		txtMaSanPham.setText("");
		txtTenSanPham.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		txtLoaiSanPham.setText("");
		txtNhaCungCap.setText("");
		dpNgayNhap.setDate(null);
	}
}
