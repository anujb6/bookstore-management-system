package javaCrud;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.JButton;

public class registration {

	private JFrame frame;
	private JTextField emailId;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration window = new registration();
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
	public registration() {
		initialize();
		Connect();
	}
	
	Login1 Login1;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
			
		}
		catch(ClassNotFoundException ex){
			
		}
		catch(SQLException ex){
			
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1047, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(371, 22, 307, 49);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Designation");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setBounds(253, 117, 191, 49);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email Id");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1_1.setBounds(253, 204, 191, 49);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1_2.setBounds(253, 282, 191, 49);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Password");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1_3.setBounds(253, 364, 191, 49);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		emailId = new JTextField();
		emailId.setFont(new Font("Tahoma", Font.BOLD, 15));
		emailId.setBounds(518, 215, 284, 34);
		frame.getContentPane().add(emailId);
		emailId.setColumns(10);
		
		user = new JTextField();
		user.setFont(new Font("Tahoma", Font.BOLD, 15));
		user.setColumns(10);
		user.setBounds(518, 293, 284, 34);
		frame.getContentPane().add(user);
		
		pass = new JPasswordField();
		pass.setBounds(518, 364, 284, 34);
		frame.getContentPane().add(pass);
		
		String [] items = { "Select Designation", "Admin", "Student"};
		final JButton btnRegister = new JButton("Register");
		final JComboBox comboBox = new JComboBox(items);
		
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			String string = (String) comboBox.getSelectedItem();
				if(string == "Admin") {
					
					btnRegister.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							String email,username, password;
							
							email = emailId.getText();
							username = user.getText();
							password = pass.getText();
							
							if(password.equals("") || username.equals("") || email.equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter required information"); 
								 return;
							}
							
							try {
								pst = con.prepareStatement("insert into adminlogin(email,username,password)values(?,?,?)");
								pst.setString(1, email);
								pst.setString(2, username);
								pst.setString(3, password);
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Registration is done");
								emailId.setText("");
								user.setText("");
								pass.setText("");
								emailId.requestFocus();
								
								Login1 = new Login1();
								Login1.setVisible(true);
								frame.dispose();
								
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							
							
						}
					});
					
				}else {
					
					btnRegister.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							String email,username, password;
							
							email = emailId.getText();
							username = user.getText();
							password = pass.getText();
							
							if(password.equals("") || username.equals("") || email.equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter required information"); 
								 return;
							}
							
							
							try {
								pst = con.prepareStatement("insert into login(email,username,password)values(?,?,?)");
								pst.setString(1, email);
								pst.setString(2, username);
								pst.setString(3, password);
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Registration is done");
								emailId.setText("");
								user.setText("");
								pass.setText("");
								emailId.requestFocus();
								
								Login1 = new Login1();
								Login1.setVisible(true);
								frame.dispose();
								
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							
							
						}
					});
					
				}
			}
		});
		comboBox.setBounds(518, 124, 284, 44);
		frame.getContentPane().add(comboBox);
		
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegister.setBounds(397, 445, 225, 44);
		frame.getContentPane().add(btnRegister);
		
	}
}
