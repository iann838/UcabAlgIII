package edu.ucab.cryptomonitor.deprecated;
import java.io.File;

public class Alert {
	private File file;
	private float price;
	private String crypto;
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	

	public String getCrypto() {
		return crypto;
	}

	public void setCrypto(String crypto) {
		this.crypto = crypto;
	}
	

	public Alert(File file, float price, String crypto) {
		this.file = file;
		this.price = price;
		this.crypto = crypto;
	}
	
	
	
}
