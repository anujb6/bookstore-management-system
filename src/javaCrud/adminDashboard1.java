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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class adminDashboard1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminDashboard1 frame = new adminDashboard1();
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
	private JTextField txttag;
	
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
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public adminDashboard1() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1096, 647);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(353, 10, 617, 92);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 126, 466, 245);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(34, 35, 146, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(34, 107, 146, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(34, 177, 146, 28);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(210, 39, 208, 27);
		panel.add(txtbname);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(210, 111, 208, 27);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(210, 181, 208, 27);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String bname,edition, price, tag;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				tag = txttag.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name,edition,price,tag)values(?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, tag);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addeddd!!!!");
					table_load();
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txttag.setText("");
					txtbname.requestFocus();
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(41, 393, 120, 47);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price,bid, tag;
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid  = txtbid.getText();
				tag = txttag.getText();
				
				 try {
						pst = con.prepareStatement("update book set name= ?,edition=?,price=?,tag=? where id =?");
						pst.setString(1, bname);
			            pst.setString(2, edition);
			            pst.setString(3, price);
			            pst.setString(4, tag);
			            pst.setString(5, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txttag.setText("");
			            txtbname.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(195, 393, 120, 47);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid  = txtbid.getText();
				
				 try {
						pst = con.prepareStatement("delete from book where id =?");
				
			            pst.setString(1, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
			            table_load();
			           
			            txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txttag.setText("");
			            txtbname.requestFocus();
					}
	 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(353, 393, 120, 47);
		contentPane.add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 482, 466, 92);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(56, 33, 146, 28);
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
			          
			            String id = txtbid.getText();

			                pst = con.prepareStatement("select name,edition,price,tag from book where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                String tag = rs.getString(4);
			                
			                txtbname.setText(name);
			                txtedition.setText(edition);
			                txtprice.setText(price);
			                txttag.setText(tag);
			                
			            }   
			            else
			            {
			            	txtbname.setText("");
			            	txtedition.setText("");
			                txtprice.setText("");
			                txttag.setText("");
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(145, 37, 248, 27);
		panel_1.add(txtbid);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "recommandations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(528, 482, 497, 92);
		contentPane.add(panel_1_1);
		
		txttag = new JTextField();
		txttag.setFont(new Font("Tahoma", Font.BOLD, 15));
		txttag.setBounds(84, 40, 335, 31);
		panel_1_1.add(txttag);
		txttag.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("tag");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(235, 22, 45, 20);
		panel_1_1.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(528, 126, 495, 319);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table_load();
	}
}
