package Model;

public class modelBarang {
	private String kode;
	private String nama;
	private int stok;
	private double harga_pokok;
	private double harga_jual;
	private int ppn;
	private int diskon;
	
	
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}
	public double getHarga_pokok() {
		return harga_pokok;
	}
	public void setHarga_pokok(double harga_pokok) {
		this.harga_pokok = harga_pokok;
	}
	public double getHarga_jual() {
		return harga_jual;
	}
	public void setHarga_jual(double harga_jual) {
		this.harga_jual = harga_jual;
	}
	public int getPpn() {
		return ppn;
	}
	public void setPpn(int ppn) {
		this.ppn = ppn;
	}
	public int getDiskon() {
		return diskon;
	}
	public void setDiskon(int diskon) {
		this.diskon = diskon;
	}
	
	
}
