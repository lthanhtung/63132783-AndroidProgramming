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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManHinhGiaiPT extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Soa;
	private JTextField textField_Sob;
	private JButton btnNewButton_GPT;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_Soc;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_LoaiPT;
	private JLabel lblNewLabel_5;
	private JTextField textField_Delta;
	private JLabel lblNewLabel_Nghiem1;
	private JTextField textField_Nghiem1;
	private JLabel lblNewLabel_Nghiem2;
	private JTextField textField_Nghiem2;
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	public ManHinhGiaiPT() {
		setTitle("Ứng dụng giải phương trình");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_Soa = new JTextField();
		textField_Soa.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Soa.setBounds(59, 59, 96, 19);
		contentPane.add(textField_Soa);
		textField_Soa.setColumns(10);
		
		textField_Sob = new JTextField();
		textField_Sob.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Sob.setBounds(272, 59, 96, 19);
		contentPane.add(textField_Sob);
		textField_Sob.setColumns(10);
		
		btnNewButton_GPT = new JButton("Giải Phương Trình");
		btnNewButton_GPT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiaiPT();
			}
		});
		btnNewButton_GPT.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_GPT.setBounds(216, 100, 199, 35);
		contentPane.add(btnNewButton_GPT);
		
		lblNewLabel = new JLabel("Ứng dụng giải phương trình");
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
		
		textField_Soc = new JTextField();
		textField_Soc.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Soc.setColumns(10);
		textField_Soc.setBounds(484, 59, 96, 19);
		contentPane.add(textField_Soc);
		
		lblNewLabel_4 = new JLabel("Kết Quả");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(20, 153, 73, 35);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_LoaiPT = new JLabel("");
		lblNewLabel_LoaiPT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_LoaiPT.setBounds(20, 186, 395, 28);
		contentPane.add(lblNewLabel_LoaiPT);
		
		lblNewLabel_5 = new JLabel("Delta=");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(20, 224, 53, 35);
		contentPane.add(lblNewLabel_5);
		
		textField_Delta = new JTextField();
		textField_Delta.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Delta.setBounds(71, 224, 429, 31);
		contentPane.add(textField_Delta);
		textField_Delta.setColumns(10);
		
		lblNewLabel_Nghiem1 = new JLabel("x1=");
		lblNewLabel_Nghiem1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_Nghiem1.setBounds(20, 282, 45, 13);
		contentPane.add(lblNewLabel_Nghiem1);
		
		textField_Nghiem1 = new JTextField();
		textField_Nghiem1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Nghiem1.setBounds(59, 278, 164, 26);
		contentPane.add(textField_Nghiem1);
		textField_Nghiem1.setColumns(10);
		
		lblNewLabel_Nghiem2 = new JLabel("x2=");
		lblNewLabel_Nghiem2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_Nghiem2.setBounds(446, 282, 45, 13);
		contentPane.add(lblNewLabel_Nghiem2);
		
		textField_Nghiem2 = new JTextField();
		textField_Nghiem2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Nghiem2.setColumns(10);
		textField_Nghiem2.setBounds(484, 278, 96, 26);
		contentPane.add(textField_Nghiem2);
		
		btnNewButton = new JButton("Trở về");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroVeManHinhChinh();
			}
		});
		btnNewButton.setBounds(526, 330, 110, 35);
		contentPane.add(btnNewButton);
	}
	void GiaiPT()
	{
		String Seta = textField_Soa.getText();
		String Setb = textField_Sob.getText();
		String Setc = textField_Soc.getText();
		
		//chuyển kiểu
		double a = Double.parseDouble(Seta);
		double b = Double.parseDouble(Setb);
		double c = Double.parseDouble(Setc);
		
		if(a == 0)
		{
			lblNewLabel_LoaiPT.setText("Đây là phương trình bậc nhất");
			textField_Delta.setText("Không thể tính delta vì đây là phương trình bậc nhất");
			double nghiem = -c/b;
			textField_Nghiem1.setText(String.valueOf(nghiem));
			textField_Nghiem2.setText("null");
		}
		else
		{
			lblNewLabel_LoaiPT.setText("Đây là phương trình bậc 2");
			double delta =b*b -(4*a*c);
			textField_Delta.setText(String.valueOf(delta));
			  if (delta <0)
	            {
	                textField_Nghiem1.setText("null (vì phương trình vô nghiệm)");
	               
	                textField_Nghiem2.setText("null (vì phương trình  vô nghiệm)");
	        
	            }
			  if(delta == 0)
	            {
	                double Nghiem = -b/(2*a);
	                textField_Nghiem1.setText(String.valueOf(Nghiem));
	                textField_Nghiem2.setText("=X1 vì phương trình có nghiệm khép");
	            }
			  else
	            {
	                double Nghiem1 = (-b + Math.sqrt(delta))/(2*a);
	                double Nghiem2 = (-b - Math.sqrt(delta))/(2*a);
	                
	                textField_Nghiem1.setText(String.valueOf(Nghiem1));
	                
	                textField_Nghiem2.setText(String.valueOf(Nghiem2));
	            }  
		}
		
	}
	void TroVeManHinhChinh()
	{
		ManHinhChinh ManHinhChinh = new ManHinhChinh();
		ManHinhChinh.setVisible(true);
		this.setVisible(false);
	}
	

}
