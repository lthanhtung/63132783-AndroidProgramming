package ntu.lethanhtung63132783;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class ManHinhGiaiPT extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Soa;
	private JTextField textField_1_Sob;
	private JButton btnNewButton_GPT;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_2_Soc;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_LoaiPT;
	private JLabel lblNewLabel_5;
	private JTextField textField;
	private JLabel lblNewLabel_6;
	private JTextField textField_1;
	private JLabel lblNewLabel_7;
	private JTextField textField_2;
	/**
	 * Create the frame.
	 */
	public ManHinhGiaiPT() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Soa = new JTextField();
		textField_Soa.setBounds(59, 59, 96, 19);
		contentPane.add(textField_Soa);
		textField_Soa.setColumns(10);
		
		textField_1_Sob = new JTextField();
		textField_1_Sob.setBounds(272, 59, 96, 19);
		contentPane.add(textField_1_Sob);
		textField_1_Sob.setColumns(10);
		
		btnNewButton_GPT = new JButton("Giải Phương Trình");
		btnNewButton_GPT.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_GPT.setBounds(216, 100, 171, 35);
		contentPane.add(btnNewButton_GPT);
		
		lblNewLabel = new JLabel("Chương trình giải phương trình");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(148, 10, 281, 19);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Số a:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 60, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Số b:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(233, 60, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Số c:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(446, 60, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		textField_2_Soc = new JTextField();
		textField_2_Soc.setColumns(10);
		textField_2_Soc.setBounds(484, 59, 96, 19);
		contentPane.add(textField_2_Soc);
		
		lblNewLabel_4 = new JLabel("Kết Quả");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(20, 153, 73, 35);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_LoaiPT = new JLabel("loai");
		lblNewLabel_LoaiPT.setBounds(20, 186, 395, 28);
		contentPane.add(lblNewLabel_LoaiPT);
		
		lblNewLabel_5 = new JLabel("Delta=");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(20, 224, 53, 35);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(71, 224, 281, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_6 = new JLabel("x1=");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(20, 282, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		textField_1 = new JTextField();
		textField_1.setBounds(59, 278, 96, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_7 = new JLabel("x2=");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7.setBounds(446, 282, 45, 13);
		contentPane.add(lblNewLabel_7);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(484, 278, 96, 26);
		contentPane.add(textField_2);
	}

}
