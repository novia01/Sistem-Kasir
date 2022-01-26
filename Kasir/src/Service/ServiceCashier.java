package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Connection.DBConnection;
import Model.modelBarang;

public abstract class ServiceCashier {
	private ArrayList<modelBarang> listBarang = new ArrayList<modelBarang>();
	
	public DefaultTableModel getBarang() {
		JTable table = new JTable();
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
		DefaultTableModel model = new DefaultTableModel();
		DBConnection db = new DBConnection();
		Connection conn = db.open();
		try {
			String sql = "SELECT * FROM barang";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				modelBarang barang = new modelBarang();
				model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						rs.getString(1), rs.getString(2), rs.getInt(5)
				});
				barang.setKode(rs.getString(1));
				barang.setNama(rs.getString(2));
				barang.setStok(rs.getInt(3));
				barang.setHarga_pokok(rs.getDouble(4));
				barang.setHarga_jual(rs.getDouble(5));
				barang.setPpn(rs.getInt(6));
				barang.setDiskon(rs.getInt(7));
				listBarang.add(barang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn = db.close();
		return model;
	}

	public ArrayList<modelBarang> getListBarang() {
		return listBarang;
	}
	
	
}
