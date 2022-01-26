package Form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

import Connection.DBConnection;
import Service.Service_Kasir;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tambah_Barang implements Service_Kasir{

	private JFrame frame;
	private JTextField textField_Kode_Barang;
	private JTextField textField_Nama_Barang;
	private JTextField textField_Stok_Barang;
	private JTextField textField_Harga_Pokok;
	private JTextField textField_PPN;
	private JTextField textField_Diskon;
	private Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tambah_Barang window = new Tambah_Barang();
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
	public Tambah_Barang() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Kasir");
		frame.setBounds(100, 100, 1360, 768);
		frame.getContentPane().setBackground(new Color(139,69,19));
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_Tambah_Barang = new JLabel("Tambah Barang");
		lbl_Tambah_Barang.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Tambah_Barang.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Tambah_Barang.setBounds(518, 29, 339, 44);
		frame.getContentPane().add(lbl_Tambah_Barang);
		
		textField_Kode_Barang = new JTextField();
		textField_Kode_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Kode_Barang.setBounds(268, 158, 325, 44);
		frame.getContentPane().add(textField_Kode_Barang);
		textField_Kode_Barang.setColumns(10);
		
		JLabel lbl_Kode_Barang = new JLabel("Kode Barang");
		lbl_Kode_Barang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Kode_Barang.setLabelFor(textField_Kode_Barang);
		lbl_Kode_Barang.setBounds(268, 107, 180, 29);
		frame.getContentPane().add(lbl_Kode_Barang);
		
		JLabel lbl_Nama_Barang = new JLabel("Nama Barang");
		lbl_Nama_Barang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Nama_Barang.setBounds(268, 234, 180, 29);
		frame.getContentPane().add(lbl_Nama_Barang);
		
		textField_Nama_Barang = new JTextField();
		lbl_Nama_Barang.setLabelFor(textField_Nama_Barang);
		textField_Nama_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Nama_Barang.setBounds(268, 294, 325, 44);
		frame.getContentPane().add(textField_Nama_Barang);
		textField_Nama_Barang.setColumns(10);
		
		JLabel lbl_Stok_Barang = new JLabel("Stok Barang");
		lbl_Stok_Barang.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Stok_Barang.setBounds(268, 374, 180, 29);
		frame.getContentPane().add(lbl_Stok_Barang);
		
		textField_Stok_Barang = new JTextField();
		lbl_Stok_Barang.setLabelFor(textField_Stok_Barang);
		textField_Stok_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Stok_Barang.setBounds(268, 437, 325, 44);
		frame.getContentPane().add(textField_Stok_Barang);
		textField_Stok_Barang.setColumns(10);
		
		JLabel lbl_Harga_Pokok = new JLabel("Harga Pokok");
		lbl_Harga_Pokok.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Harga_Pokok.setBounds(794, 107, 180, 29);
		frame.getContentPane().add(lbl_Harga_Pokok);
		
		textField_Harga_Pokok = new JTextField();
		lbl_Harga_Pokok.setLabelFor(textField_Harga_Pokok);
		textField_Harga_Pokok.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Harga_Pokok.setBounds(794, 158, 325, 44);
		frame.getContentPane().add(textField_Harga_Pokok);
		textField_Harga_Pokok.setColumns(10);
		
		JLabel lbl_PPN = new JLabel("PPN (%)");
		lbl_PPN.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_PPN.setBounds(794, 234, 180, 29);
		frame.getContentPane().add(lbl_PPN);
		
		textField_PPN = new JTextField();
		lbl_PPN.setLabelFor(textField_PPN);
		textField_PPN.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_PPN.setBounds(794, 294, 325, 44);
		frame.getContentPane().add(textField_PPN);
		textField_PPN.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Diskon (%)");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(794, 374, 180, 29);
		frame.getContentPane().add(lblNewLabel);
		
		textField_Diskon = new JTextField();
		lblNewLabel.setLabelFor(textField_Diskon);
		textField_Diskon.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		textField_Diskon.setBounds(794, 437, 325, 44);
		frame.getContentPane().add(textField_Diskon);
		textField_Diskon.setColumns(10);
		
		JButton btn_Tambah_Barang = new JButton("Tambah");
		btn_Tambah_Barang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tambahBarang();
			}
		});
		btn_Tambah_Barang.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btn_Tambah_Barang.setBounds(616, 534, 143, 44);
		frame.getContentPane().add(btn_Tambah_Barang);
		
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void tambahBarang() {
		DBConnection db = new DBConnection();
		String sql = "INSERT INTO barang (id_barang, nama_barang, stok, harga_pokok, ppn, diskon, harga_jual) VALUES (?, ?, ?, ?, ?, ?, ?);";
		conn = db.open();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			double ppn = Double.parseDouble(textField_PPN.getText());
			double harga_Pokok = Double.parseDouble(textField_Harga_Pokok.getText());
			double harga_Jual = harga_Pokok  + (harga_Pokok * (ppn / 100));
			preparedStatement.setString(1, textField_Kode_Barang.getText());
			preparedStatement.setString(2, textField_Nama_Barang.getText());
			preparedStatement.setInt(3, Integer.parseInt(textField_Stok_Barang.getText()));
			preparedStatement.setDouble(4, Double.parseDouble(textField_Harga_Pokok.getText()));
			preparedStatement.setInt(5, Integer.parseInt(textField_PPN.getText()));
			preparedStatement.setInt(6, Integer.parseInt(textField_Diskon.getText()));
			preparedStatement.setDouble(7, harga_Jual);
			preparedStatement.executeUpdate();
			System.out.println("Berhasil Menambah Barang");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getBarang() {
		// TODO Auto-generated method stub
		
	}

	public JFrame getFrame() {
		return frame;
	}
	
	
}
