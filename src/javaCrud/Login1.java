package javaCrud;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login1 extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;
	private JTextField user1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login1 frame = new Login1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	adminDashboard1 adminDashboard1;
	studentDashboard1 studentDashboard1;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JPasswordField pass1;
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
	public Login1() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1055, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(461, 10, 237, 49);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblNewLabel);
		
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblStudent.setBounds(213, 106, 189, 52);
		contentPane.add(lblStudent);
		
		user = new JTextField();
		user.setFont(new Font("Tahoma", Font.BOLD, 17));
		user.setColumns(10);
		user.setBounds(130, 212, 287, 52);
		contentPane.add(user);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(239, 168, 121, 52);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(239, 278, 121, 52);
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(130, 316, 287, 52);
		contentPane.add(pass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = user.getText(); 
					String password = pass.getText();
					pst  = con.prepareStatement("select username, password from login where username=? and password=?"); 
					pst.setString(1, username);
	                pst.setString(2, password);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true){
						studentDashboard1 = new studentDashboard1();
						studentDashboard1.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
						user.setText("");
						pass.setText("");
					}
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(213, 390, 121, 40);
		contentPane.add(btnNewButton);
		
		JLabel lblAdmin = new JLabel("Admin ");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblAdmin.setBounds(683, 106, 189, 52);
		contentPane.add(lblAdmin);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername_1.setBounds(706, 168, 121, 52);
		contentPane.add(lblUsername_1);
		
		user1 = new JTextField();
		user1.setFont(new Font("Tahoma", Font.BOLD, 17));
		user1.setColumns(10);
		user1.setBounds(603, 212, 274, 52);
		contentPane.add(user1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword_1.setBounds(706, 278, 121, 52);
		contentPane.add(lblPassword_1);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = user1.getText(); 
					String password = pass1.getText();
					pst  = con.prepareStatement("select username, password from adminlogin where username=? and password=?"); 
					pst.setString(1, username);
	                pst.setString(2, password);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true){
						adminDashboard1 = new adminDashboard1();
						adminDashboard1.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Incorrect username or password");
						user1.setText("");
						pass1.setText("");
					}
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(683, 390, 121, 40);
		contentPane.add(btnNewButton_1);
		
		pass1 = new JPasswordField();
		pass1.setBounds(601, 316, 287, 52);
		contentPane.add(pass1);
	}
}
