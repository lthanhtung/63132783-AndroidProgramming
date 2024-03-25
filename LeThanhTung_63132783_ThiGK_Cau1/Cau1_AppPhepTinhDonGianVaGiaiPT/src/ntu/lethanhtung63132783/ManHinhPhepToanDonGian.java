package ntu.lethanhtung63132783;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;

public class ManHinhPhepToanDonGian extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;
	private JButton btnTr;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_3;
	private JTextField textField_2;
	private JButton btnNewButton_1;


	/**
	 * Create the frame.
	 */
	public ManHinhPhepToanDonGian() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(67, 78, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Cộng");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(226, 73, 145, 29);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Chương trình phép toán đơn giản");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(158, 22, 254, 21);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Số a");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(25, 79, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Số b:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(25, 146, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(67, 145, 96, 19);
		contentPane.add(textField_1);
		
		btnTr = new JButton("Trừ");
		btnTr.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTr.setBounds(394, 73, 145, 29);
		contentPane.add(btnTr);
		
		btnNewButton_2 = new JButton("Nhân");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(226, 144, 145, 29);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Chia");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBounds(394, 144, 145, 29);
		contentPane.add(btnNewButton_3);
		
		lblNewLabel_3 = new JLabel("Kết Quả:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(25, 220, 76, 37);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(97, 226, 76, 29);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton_1 = new JButton("Trở về");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(513, 276, 104, 54);
		contentPane.add(btnNewButton_1);
	}
}
