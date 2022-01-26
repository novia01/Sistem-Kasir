package Form;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
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
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(139,69,19));
		frame.setForeground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setTitle("Kasir");
		frame.setBounds(100, 100, 1360, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_Kasir = new JPanel();
		panel_Kasir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Tambah_Barang frameTambahBarang = new  Tambah_Barang();
				frame.setVisible(false);
				frameTambahBarang.getFrame().setVisible(true);
			}
		});
		panel_Kasir.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Kasir.setBackground(SystemColor.menu);
		panel_Kasir.setBounds(300, 100, 144, 139);
		
		frame.getContentPane().add(panel_Kasir);
		panel_Kasir.setLayout(null);
		
		JLabel lbl_Kasir = new JLabel("Tambah");
		lbl_Kasir.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Kasir.setBounds(10, 104, 124, 24);
		lbl_Kasir.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_Kasir.add(lbl_Kasir);
		
		JLabel image_Add = new JLabel("");
		image_Add.setBounds(48, 48, 48, 48);
		image_Add.setIcon(new ImageIcon("Images/add.png"));
		panel_Kasir.add(image_Add);
		
		JPanel panel_Nota_Pembelian = new JPanel();
		panel_Nota_Pembelian.setBackground(SystemColor.menu);
		panel_Nota_Pembelian.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Nota_Pembelian.setBounds(520, 100, 144, 139);
		frame.getContentPane().add(panel_Nota_Pembelian);
		panel_Nota_Pembelian.setLayout(null);
		
		JLabel lbl_Nota_1 = new JLabel("Pembelian");
		lbl_Nota_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nota_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Nota_1.setBounds(10, 104, 124, 24);
		panel_Nota_Pembelian.add(lbl_Nota_1);
		
		JLabel lbl_Nota_Pembelian = new JLabel("Laporan");
		lbl_Nota_Pembelian.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nota_Pembelian.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Nota_Pembelian.setBounds(10, 79, 124, 24);
		panel_Nota_Pembelian.add(lbl_Nota_Pembelian);
		
		JLabel image_Pembelian = new JLabel("");
		image_Pembelian.setBounds(49, 27, 48, 48);
		image_Pembelian.setIcon(new ImageIcon("Images/Laporan_Pembelian.png"));
		panel_Nota_Pembelian.add(image_Pembelian);
		
		JPanel panel_Nota_Penjualan = new JPanel();
		panel_Nota_Penjualan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					printNotaPenjualan();
				} catch (JRException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_Nota_Penjualan.setBackground(SystemColor.menu);
		panel_Nota_Penjualan.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Nota_Penjualan.setBounds(740, 100, 144, 139);
		frame.getContentPane().add(panel_Nota_Penjualan);
		panel_Nota_Penjualan.setLayout(null);
		
		JLabel lbl_Nota_Penjualan = new JLabel("Penjualan");
		lbl_Nota_Penjualan.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nota_Penjualan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Nota_Penjualan.setBounds(10, 104, 124, 24);
		panel_Nota_Penjualan.add(lbl_Nota_Penjualan);
		
		JLabel lbl_Nota_2 = new JLabel("Laporan");
		lbl_Nota_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nota_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Nota_2.setBounds(10, 79, 124, 24);
		panel_Nota_Penjualan.add(lbl_Nota_2);
		
		JLabel image_Penjualan = new JLabel("");
		image_Penjualan.setBounds(49, 27, 48, 48);
		image_Penjualan.setIcon(new ImageIcon("Images/Laporan.png"));
		panel_Nota_Penjualan.add(image_Penjualan);
		
		JPanel panel_Pembelian = new JPanel();
		panel_Pembelian.setBackground(SystemColor.menu);
		panel_Pembelian.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Pembelian.setBounds(300, 304, 144, 139);
		frame.getContentPane().add(panel_Pembelian);
		panel_Pembelian.setLayout(null);
		
		JLabel lbl_Edit = new JLabel("Edit");
		lbl_Edit.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Edit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Edit.setBounds(10, 104, 124, 24);
		panel_Pembelian.add(lbl_Edit);
		
		JLabel image_Edit = new JLabel("");
		image_Edit.setBounds(48, 48, 48, 48);
		image_Edit.setIcon(new ImageIcon("Images/edit.png"));
		panel_Pembelian.add(image_Edit);
		
		JPanel panel_Laba = new JPanel();
		panel_Laba.setBackground(SystemColor.menu);
		panel_Laba.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Laba.setBounds(520, 304, 144, 139);
		frame.getContentPane().add(panel_Laba);
		panel_Laba.setLayout(null);
		
		JLabel lbl_Laba = new JLabel("Laba-Rugi");
		lbl_Laba.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Laba.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Laba.setBounds(10, 104, 124, 24);
		panel_Laba.add(lbl_Laba);
		
		JLabel image_laba = new JLabel("");
		image_laba.setBounds(48, 48, 48, 48);
		image_laba.setIcon(new ImageIcon("Images/Laporan_Laba.png"));
		panel_Laba.add(image_laba);
		
		JPanel panel_Side = new JPanel();
		panel_Side.setBackground(new Color(178,190,181));
		panel_Side.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Side.setBounds(1127, 0, 243, 729);
		frame.getContentPane().add(panel_Side);
		panel_Side.setLayout(null);
		
		JPanel panel_Pembelian_1 = new JPanel();
		panel_Pembelian_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					printNotaPenjualan();
				} catch (JRException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_Pembelian_1.setLayout(null);
		panel_Pembelian_1.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Pembelian_1.setBackground(SystemColor.menu);
		panel_Pembelian_1.setBounds(47, 100, 144, 139);
		panel_Side.add(panel_Pembelian_1);
		
		JLabel lbl_Pembelian = new JLabel("Pembelian");
		lbl_Pembelian.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Pembelian.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Pembelian.setBounds(10, 104, 124, 24);
		panel_Pembelian_1.add(lbl_Pembelian);
		
		JLabel image_Pembelian_1 = new JLabel("");
		image_Pembelian_1.setBounds(49, 27, 48, 48);
		image_Pembelian_1.setIcon(new ImageIcon("Images/cart.png"));
		panel_Pembelian_1.add(image_Pembelian_1);
		
		JPanel panel_Penjualan_1 = new JPanel();
		panel_Penjualan_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Penjualan formPembelian = new Penjualan();
				frame.setVisible(false);
				formPembelian.getFrame().setVisible(true);
			}
		});
		panel_Penjualan_1.setLayout(null);
		panel_Penjualan_1.setBorder(new EmptyBorder(20, 20, 20, 20));
		panel_Penjualan_1.setBackground(SystemColor.menu);
		panel_Penjualan_1.setBounds(47, 304, 144, 139);
		panel_Side.add(panel_Penjualan_1);
		
		JLabel lbl_Pembelian_1 = new JLabel("Penjualan");
		lbl_Pembelian_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Pembelian_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Pembelian_1.setBounds(10, 104, 124, 24);
		panel_Penjualan_1.add(lbl_Pembelian_1);
		
		JLabel image_Penjualan_1 = new JLabel("");
		image_Penjualan_1.setBounds(49, 27, 48, 48);
		image_Penjualan_1.setIcon(new ImageIcon("Images/checkout.png"));
		panel_Penjualan_1.add(image_Penjualan_1);
	}

	public JFrame getFrame() {
		return frame;
	}
	
	private void printNotaPenjualan() throws JRException, IOException {
		String SrcFileNota = "C:\\Users\\USER\\Documents\\Java Projects\\Kasir\\laporan\\Laporan Penjualan.jrxml";
        
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
                "C:\\Users\\USER\\Documents\\Java Projects\\Kasir\\laporan\\Laporan Penjualan.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
 
        System.out.print("Done!");
        
        Desktop.getDesktop().open(new File("C:\\\\Users\\\\USER\\\\Documents\\\\Java Projects\\\\Kasir\\\\laporan\\\\Laporan Penjualan.pdf"));
	}
	
	
	
}

