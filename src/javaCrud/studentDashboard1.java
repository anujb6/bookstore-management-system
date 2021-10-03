package javaCrud;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class studentDashboard1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbname;
	private JTextField txtbid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentDashboard1 frame = new studentDashboard1();
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
	}	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public studentDashboard1() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 983, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Dashboard");
		lblNewLabel.setBounds(273, 21, 521, 49);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblNewLabel);
		
		JPanel box = new JPanel();
		box.setLayout(null);
		box.setBorder(new TitledBorder(null, "Searching", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		box.setBounds(27, 104, 429, 365);
		contentPane.add(box);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(25, 38, 146, 28);
		box.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Id");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(36, 249, 146, 28);
		box.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(49, 117, 146, 28);
		box.add(lblNewLabel_1_2);
		
		txtedition = new JTextField();
		txtedition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtedition.setColumns(10);
		txtedition.setBounds(156, 180, 225, 44);
		box.add(txtedition);
		
		txtbname = new JTextField();
		txtbname.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtbname.setColumns(10);
		txtbname.setBounds(156, 33, 225, 44);
		box.add(txtbname);
		
		txtbid = new JTextField();
		txtbid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtbid.setColumns(10);
		txtbid.setBounds(156, 244, 225, 44);
		box.add(txtbid);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			       
		            String id = txtbid.getText();

		                pst = con.prepareStatement("select name,edition,price from book where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		                
		                
		            }else {
		            	JOptionPane.showMessageDialog(null, "Please enter correct book id");

		            }

		        } 
			
			 catch (SQLException ex) {
		           
		        }
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(156, 312, 225, 44);
		box.add(btnNewButton);
		
		JLabel txted = new JLabel("edition");
		txted.setFont(new Font("Tahoma", Font.BOLD, 20));
		txted.setBounds(49, 185, 146, 28);
		box.add(txted);
		
		txtprice = new JTextField();
		txtprice.setBounds(156, 112, 225, 44);
		box.add(txtprice);
		txtprice.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtprice.setColumns(10);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "recommandations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(51, 483, 862, 92);
		contentPane.add(panel_1_1);
		
		final JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.insertItemAt("Select tags", 0);
        try {
        	 pst = con.prepareStatement("select distinct tag from book");
        	 ResultSet rs = pst.executeQuery();
        	 while (rs.next()) {  
        	     comboBox.addItem(rs.getString("tag"));  
        	 }
		}catch(Exception e){
			
		}
        
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tag = (String) comboBox.getSelectedItem();
					try {
						pst = con.prepareStatement("select * from book where tag=?");
						pst.setString(1, tag);
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		
		comboBox.setBounds(243, 28, 356, 28);
		panel_1_1.add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(466, 104, 493, 365);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table); 
		table_load();
	}
}
