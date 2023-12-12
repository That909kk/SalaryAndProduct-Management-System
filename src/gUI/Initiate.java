package gUI;

import java.awt.EventQueue;

public class Initiate {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap_GUI dangNhap = new DangNhap_GUI();
					dangNhap.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
