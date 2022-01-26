package Form;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Pembelian {

	private JFrame frame;
	private JTextField textField_Kode_Barang;
	private JTextField textField_Jumlah;
	private JTextField textField_Total;
	private Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pembelian window = new Pembelian();
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
	public Pembelian() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1360, 768);
		frame.getContentPane().setBackground(new Color(139,69,19));
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lbl_Pembelian = new JLabel("Pembelian");
		lbl_Pembelian.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Pembelian.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Pembelian.setBounds(518, 29, 339, 44);
		frame.getContentPane().add(lbl_Pembelian);
		
		JLabel lblNewLabel = new JLabel("Kode Barang");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(387, 126, 180, 29);
		frame.getContentPane().add(lblNewLabel);
		
		textField_Kode_Barang = new JTextField();
		textField_Kode_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Kode_Barang.setBounds(387, 197, 255, 44);
		frame.getContentPane().add(textField_Kode_Barang);
		textField_Kode_Barang.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Jumlah");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(767, 126, 180, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_Jumlah = new JTextField();
		textField_Jumlah.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Jumlah.setBounds(767, 197, 255, 44);
		frame.getContentPane().add(textField_Jumlah);
		textField_Jumlah.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Total");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(767, 295, 180, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_Total = new JTextField();
		textField_Total.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Total.setBounds(767, 366, 255, 44);
		frame.getContentPane().add(textField_Total);
		textField_Total.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertPembelian();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(629, 491, 143, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Print");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					printNota();
				} catch (JRException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(629, 576, 143, 44);
		frame.getContentPane().add(btnNewButton_1);
		
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
	}
	
	private void insertPembelian() {
		String sql = "INSERT INTO pembelian (kode_barang, jumlah, total, tanggal) VALUES (?, ?, ?, CURRENT_DATE());";
		DBConnection db = new DBConnection();
		conn = db.open();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, textField_Kode_Barang.getText().trim());
			preparedStatement.setInt(2, Integer.parseInt(textField_Jumlah.getText().trim()));
			preparedStatement.setInt(3, Integer.parseInt(textField_Total.getText().trim()));
			preparedStatement.executeUpdate();
			System.out.println("Sukses");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void printNota() throws JRException, IOException {
		String SrcFileNota = "C:\\Users\\USER\\Documents\\Java Projects\\Kasir\\laporan\\Nota Pembelian.jrxml";
        
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
                "C:\\Users\\USER\\Documents\\Java Projects\\Kasir\\laporan\\Nota Pembelian.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
 
        System.out.print("Done!");
        
        Desktop.getDesktop().open(new File("C:\\\\Users\\\\USER\\\\Documents\\\\Java Projects\\\\Kasir\\\\laporan\\\\Nota Pembelian.pdf"));
	}
}
