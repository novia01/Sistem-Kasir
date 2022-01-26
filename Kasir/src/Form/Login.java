package Form;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private JFrame frameLogin;
	private JTextField tf_Username;
	private JTextField tf_Password;
	private JLabel lbl_Alert_Login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frameLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.setBounds(100, 100, 1360, 768);
		frameLogin.getContentPane().setBackground(new Color(139,69,19));
		frameLogin.getContentPane().setLayout(null);
		
		tf_Username = new JTextField();
		tf_Username.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_Username.setBounds(495, 230, 378, 45);
		frameLogin.getContentPane().add(tf_Username);
		tf_Username.setColumns(10);
		
		tf_Password = new JPasswordField();
		tf_Password.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_Password.setBounds(495, 371, 378, 45);
		frameLogin.getContentPane().add(tf_Password);
		tf_Password.setColumns(10);
		
		JLabel lbl_Username = new JLabel("Username");
		lbl_Username.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Username.setBounds(495, 184, 180, 30);
		frameLogin.getContentPane().add(lbl_Username);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(495, 322, 180, 30);
		frameLogin.getContentPane().add(lblNewLabel);
		
		JLabel lbl_Toko = new JLabel("Toko Sumber Rejeki");
		lbl_Toko.setForeground(SystemColor.text);
		lbl_Toko.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Toko.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbl_Toko.setBounds(425, 104, 532, 55);
		frameLogin.getContentPane().add(lbl_Toko);
		
		JButton btn_Login = new JButton("Masuk");
		btn_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				authentication();
			}
		});
		btn_Login.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btn_Login.setBounds(632, 480, 112, 45);
		frameLogin.getContentPane().add(btn_Login);
		
		lbl_Alert_Login = new JLabel("");
		lbl_Alert_Login.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_Alert_Login.setBounds(495, 568, 462, 30);
		lbl_Alert_Login.setVisible(false);
		frameLogin.getContentPane().add(lbl_Alert_Login);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void authentication() {
		String username = tf_Username.getText().trim();
		String password = tf_Password.getText().trim();
		
		if (username.equals("admin") && password.equals("admin")) {
			Menu frameMenu = new Menu();
			frameLogin.setVisible(false);
			frameMenu.getFrame().setVisible(true);
		}
		else {
			lbl_Alert_Login.setForeground(Color.RED);
			lbl_Alert_Login.setText("Gagal Login");
			lbl_Alert_Login.setVisible(true);
		}
	}
}
