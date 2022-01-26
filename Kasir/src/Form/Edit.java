package Form;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Connection.DBConnection;
import Model.modelBarang;
import Service.ServiceCashier;
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

public class Edit extends ServiceCashier{

	private JFrame frame;
	private JTable table;
	private JTextField textField_Kode_Barang;
	private JTextField textField_Nama_Barang;
	private JTextField textField_Stok;
	private JTextField textField_Harga;
	private JTextField textField_PPN;
	private JTextField textField_Diskon;
	private ArrayList<modelBarang> listBarang;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit window = new Edit();
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
	public Edit() {
		initialize();
		listBarang = getListBarang();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1360, 768);
		frame.getContentPane().setBackground(new Color(139,69,19));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_Edit_Barang = new JLabel("Edit Barang");
		lbl_Edit_Barang.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Edit_Barang.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Edit_Barang.setBounds(518, 29, 339, 44);
		frame.getContentPane().add(lbl_Edit_Barang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 156, 562, 503);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelBarang model = listBarang.get(table.getSelectedRow());
				textField_Kode_Barang.setText(model.getKode());
				textField_Nama_Barang.setText(model.getNama());
				textField_Stok.setText(String.valueOf(model.getStok()));
				textField_Harga.setText(String.valueOf(model.getHarga_pokok()));
				textField_PPN.setText(String.valueOf(model.getPpn()));
				textField_Diskon.setText(String.valueOf(model.getDiskon()));
			}
		});
		table.setModel(new DefaultTableModel(
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
		table.setModel(getBarang());
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Kode Barang");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(677, 154, 180, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Barang");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(677, 290, 180, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Stok Barang");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(677, 426, 180, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Harga Pokok");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setBounds(1065, 154, 180, 29);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PPN (%)");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_4.setBounds(1065, 290, 180, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Diskon (%)");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_5.setBounds(1065, 426, 180, 29);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_Kode_Barang = new JTextField();
		textField_Kode_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Kode_Barang.setBounds(677, 215, 232, 44);
		frame.getContentPane().add(textField_Kode_Barang);
		textField_Kode_Barang.setColumns(10);
		
		textField_Nama_Barang = new JTextField();
		textField_Nama_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Nama_Barang.setBounds(677, 351, 232, 44);
		frame.getContentPane().add(textField_Nama_Barang);
		textField_Nama_Barang.setColumns(10);
		
		textField_Stok = new JTextField();
		textField_Stok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Stok.setBounds(677, 487, 232, 44);
		frame.getContentPane().add(textField_Stok);
		textField_Stok.setColumns(10);
		
		textField_Harga = new JTextField();
		textField_Harga.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Harga.setBounds(1065, 215, 232, 44);
		frame.getContentPane().add(textField_Harga);
		textField_Harga.setColumns(10);
		
		textField_PPN = new JTextField();
		textField_PPN.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField_PPN.setBounds(1065, 351, 232, 44);
		frame.getContentPane().add(textField_PPN);
		textField_PPN.setColumns(10);
		
		textField_Diskon = new JTextField();
		textField_Diskon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Diskon.setBounds(1065, 487, 232, 44);
		frame.getContentPane().add(textField_Diskon);
		textField_Diskon.setColumns(10);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editBarang();
				table.setModel(getBarang());
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(923, 600, 143, 44);
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
	}
	
	private void editBarang() {
		String sql = "UPDATE barang SET nama_barang = ?, stok = ?, harga_pokok = ?, "
				+ "harga_jual = ?, ppn = ?, diskon = ? WHERE id_barang = ?;";
		DBConnection db = new DBConnection();
		conn = db.open();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			double ppn = Double.parseDouble(textField_PPN.getText());
			double harga_Pokok = Double.parseDouble(textField_Harga.getText());
			double harga_Jual = harga_Pokok  + (harga_Pokok * (ppn / 100));
			preparedStatement.setString(1, textField_Nama_Barang.getText().trim());
			preparedStatement.setInt(2, Integer.parseInt(textField_Stok.getText().trim()));
			preparedStatement.setDouble(3, Double.parseDouble(textField_Harga.getText().trim()));
			preparedStatement.setDouble(4, harga_Jual);
			preparedStatement.setInt(5, Integer.parseInt(textField_PPN.getText().trim()));
			preparedStatement.setInt(6, Integer.parseInt(textField_Diskon.getText().trim()));
			preparedStatement.setString(7, textField_Kode_Barang.getText().trim());
			preparedStatement.executeUpdate();
			System.out.println("Berhasil Edit Barang");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = db.close();
	}
}
