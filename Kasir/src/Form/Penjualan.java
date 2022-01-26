package Form;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import Connection.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class Penjualan {

	private JFrame frame;
	private JTable table_Barang;
	private JTable table_Kasir;
	DefaultTableModel model_Barang, model_Pembelian_Konsumen;
	private JLabel lbl_Total;
	private JLabel Kembalian;
	private Connection conn = null;
	int total = 0;
	private JTextField textField_Bayar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Penjualan window = new Penjualan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Penjualan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Kasir");
		frame.setBounds(100, 100, 1360, 768);
		frame.getContentPane().setBackground(new Color(139,69,19));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_Toko = new JLabel("Toko Sumber Rejeki");
		lbl_Toko.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Toko.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Toko.setBounds(518, 29, 339, 44);
		frame.getContentPane().add(lbl_Toko);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 104, 618, 553);
		frame.getContentPane().add(scrollPane);
		
		table_Barang = new JTable();
		table_Barang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addItem();
			}
		});
		table_Barang.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Kode Produk", "Nama Barang", "Harga"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(table_Barang);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(770, 104, 521, 279);
		frame.getContentPane().add(scrollPane_1);
		
		table_Kasir = new JTable();
		table_Kasir.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Kode Barang", "Nama Barang", "Harga"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_Kasir);
		
		lbl_Total = new JLabel("0");
		lbl_Total.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Total.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Total.setBounds(995, 424, 296, 44);
		frame.getContentPane().add(lbl_Total);
		
		JLabel Total = new JLabel("Total");
		Total.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Total.setBounds(770, 424, 98, 44);
		frame.getContentPane().add(Total);
		
		JLabel lbl_Bayar = new JLabel("Bayar");
		lbl_Bayar.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Bayar.setBounds(770, 494, 98, 44);
		frame.getContentPane().add(lbl_Bayar);
		
		JLabel lbl_Kembalian = new JLabel("Kembalian");
		lbl_Kembalian.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Kembalian.setBounds(770, 549, 155, 44);
		frame.getContentPane().add(lbl_Kembalian);
		
		Kembalian = new JLabel("0");
		Kembalian.setHorizontalAlignment(SwingConstants.RIGHT);
		Kembalian.setFont(new Font("Times New Roman", Font.BOLD, 20));
		Kembalian.setBounds(995, 549, 296, 44);
		frame.getContentPane().add(Kembalian);
		
		NumberFormat numformat = NumberFormat.getInstance();
	    NumberFormatter numformatter;
	    numformat.setMaximumFractionDigits(0);
        
        //  Deklarasikan NumberFormatter
        numformatter = new NumberFormatter(numformat);
        numformatter.setAllowsInvalid(false);
		
		textField_Bayar = new JFormattedTextField(numformatter);
		textField_Bayar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
			        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

			        formatRp.setCurrencySymbol("Rp. ");
			        formatRp.setMonetaryDecimalSeparator(',');
			        formatRp.setGroupingSeparator('.');

			        kursIndonesia.setDecimalFormatSymbols(formatRp);
			        String s = textField_Bayar.getText().trim().replace(",", "");
					int bayar = Integer.parseInt(s);
					int kembalian = bayar - total;
					Kembalian.setText(String.valueOf(kursIndonesia.format(kembalian)));
				}
			}
		});
		textField_Bayar.setBounds(995, 494, 296, 44);
		frame.getContentPane().add(textField_Bayar);
		textField_Bayar.setColumns(10);
		
		JButton btnNewButton = new JButton("Print");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					printNota();
					String sql = "TRUNCATE TABLE nota_konsumen;";
					DBConnection db = new DBConnection();
					conn = db.open();
					Statement statement = conn.prepareStatement(sql);
					statement.executeUpdate(sql);
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(1148, 643, 143, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btn_Kembali = new JButton("Kembali");
		btn_Kembali.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu frameMenu = new Menu();
				frame.setVisible(false);
				frameMenu.getFrame().setVisible(true);
			}
		});
		btn_Kembali.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btn_Kembali.setBounds(10, 70, 143, 29);
		frame.getContentPane().add(btn_Kembali);
		getBarang();
	}
	
	
	private void getBarang() {
		DBConnection db = new DBConnection();
		conn = db.open();
		try {
			String sql = "SELECT * FROM barang";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				model_Barang = (DefaultTableModel) table_Barang.getModel();
				model_Barang.addRow(new Object[] {
						rs.getString(1), rs.getString(2), rs.getInt(5)
				});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = db.close();
	}
	
	private void printNota() throws JRException, IOException {
		String SrcFileNota = "C:\\Users\\USER\\Documents\\Java Projects\\Kasir\\laporan\\Nota.jrxml";
        
        // First, compile jrxml file.
        JasperReport jasperReport =    JasperCompileManager.compileReport(SrcFileNota);
        DBConnection db = new DBConnection();
        Connection conn = db.open();
 
        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
 
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, conn);
 
        // Make sure the output directory exists.
        java.io.File outDir = new java.io.File("C:/laporan");
        outDir.mkdirs();
 
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "C:\\Users\\USER\\Documents\\Java Projects\\Kasir\\laporan\\Nota Konsumen.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
 
        System.out.print("Done!");
        
        Desktop.getDesktop().open(new File("C:\\\\Users\\\\USER\\\\Documents\\\\Java Projects\\\\Kasir\\\\laporan\\\\Nota Konsumen.pdf"));
	}
	
	
	public JFrame getFrame() {
		return frame;
	}
	
	private void addItem() {
		String kode_Barang = (String)table_Barang.getValueAt(table_Barang.getSelectedRow(), 0);
		String nama_Barang = (String)table_Barang.getValueAt(table_Barang.getSelectedRow(), 1);
		int harga_Barang = (Integer)table_Barang.getValueAt(table_Barang.getSelectedRow(), 2);
		String sql = "INSERT INTO nota_konsumen (kode_barang, nama_barang, harga_barang) VALUES (?, ?, ?);";
		String sqlLaporanPenjualan = "INSERT INTO penjualan (id_barang, jumlah, total, tanggal) VALUES (?, 1, ?, CURRENT_DATE());";
		DBConnection db = new DBConnection();
		conn = db.open();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			PreparedStatement statementLaporanPenjualan = conn.prepareStatement(sqlLaporanPenjualan);
			preparedStatement.setString(1, kode_Barang);
			preparedStatement.setString(2, nama_Barang);
			preparedStatement.setDouble(3, harga_Barang);
			preparedStatement.executeUpdate();
			statementLaporanPenjualan.setString(1, kode_Barang);
			statementLaporanPenjualan.setDouble(2, harga_Barang);
			statementLaporanPenjualan.executeUpdate();
			System.out.println("Berhasil Menambah Barang");
			//getBarangKonsumen();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conn = db.close();
		model_Pembelian_Konsumen = (DefaultTableModel) table_Kasir.getModel();
		model_Pembelian_Konsumen.addRow(new Object[] {
				kode_Barang, nama_Barang, harga_Barang
		});
		total += harga_Barang;
		lbl_Total.setText(String.valueOf(total));
	}
}
