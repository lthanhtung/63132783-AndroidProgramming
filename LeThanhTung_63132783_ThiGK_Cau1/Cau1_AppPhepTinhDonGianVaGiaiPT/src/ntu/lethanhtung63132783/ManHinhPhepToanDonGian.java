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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManHinhPhepToanDonGian extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textSoa;
	private JButton btnCong;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField textSob;
	private JButton btnTru;
	private JButton btnNhan;
	private JButton btnChia;
	private JLabel lblNewLabel_3;
	private JTextField textKetQua;
	private JButton btnTroVe;


	/**
	 * Create the frame.
	 */
	public ManHinhPhepToanDonGian() {
		setTitle("Ứng dụng phép tính đơn giản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textSoa = new JTextField();
		textSoa.setBounds(67, 78, 96, 19);
		contentPane.add(textSoa);
		textSoa.setColumns(10);
		
		btnCong = new JButton("Cộng");
		btnCong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XuLyCong();
			}
		});
		btnCong.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCong.setBounds(226, 73, 145, 29);
		contentPane.add(btnCong);
		
		lblNewLabel = new JLabel("Ứng dụng phép toán đơn giản");
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
		
		textSob = new JTextField();
		textSob.setColumns(10);
		textSob.setBounds(67, 145, 96, 19);
		contentPane.add(textSob);
		
		btnTru = new JButton("Trừ");
		btnTru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XuLyTru();
			}
		});
		btnTru.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTru.setBounds(394, 73, 145, 29);
		contentPane.add(btnTru);
		
		btnNhan = new JButton("Nhân");
		btnNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XuLyNhan();
			}
		});
		btnNhan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNhan.setBounds(226, 144, 145, 29);
		contentPane.add(btnNhan);
		
		btnChia = new JButton("Chia");
		btnChia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XuLyChia();
			}
		});
		btnChia.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChia.setBounds(394, 144, 145, 29);
		contentPane.add(btnChia);
		
		lblNewLabel_3 = new JLabel("Kết Quả:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(25, 220, 76, 37);
		contentPane.add(lblNewLabel_3);
		
		textKetQua = new JTextField();
		textKetQua.setFont(new Font("Tahoma", Font.BOLD, 15));
		textKetQua.setBounds(97, 226, 178, 29);
		contentPane.add(textKetQua);
		textKetQua.setColumns(10);
		
		btnTroVe = new JButton("Trở về");
		btnTroVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TroVeManHinhChinh();
			}
		});
		btnTroVe.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTroVe.setBounds(513, 276, 104, 54);
		contentPane.add(btnTroVe);
	}
	void XuLyCong()
	{
	//Lấy dữ liệu từ điều khiển	
		String SoA = textSoa.getText();
		String SoB = textSob.getText();
		//Chuyển kiểu
		double A = Double.parseDouble(SoA);
		double B = Double.parseDouble(SoB);
		//Tính tổng
		double Tong = A + B;
		
		String KQ = String.valueOf(Tong);
		textKetQua.setText(KQ);		
	}
	void XuLyTru()
	{
		//Lấy dữ liệu từ điều khiển	
				String SoA = textSoa.getText();
				String SoB = textSob.getText();
				//Chuyển kiểu
				double A = Double.parseDouble(SoA);
				double B = Double.parseDouble(SoB);
				//Tính Trừ
				double Tru = A - B;
				
				String KQ = String.valueOf(Tru);
				textKetQua.setText(KQ);	
	}
	void XuLyNhan()
	{
		//Lấy dữ liệu từ điều khiển	
			String SoA = textSoa.getText();
			String SoB = textSob.getText();
			//Chuyển kiểu
			double A = Double.parseDouble(SoA);
			double B = Double.parseDouble(SoB);
			//Tính Nhan
			double Nhan = A * B;			
			String KQ = String.valueOf(Nhan);
			textKetQua.setText(KQ);
	}
	
	void XuLyChia()
	{
		//Lấy dữ liệu từ điều khiển	
			String SoA = textSoa.getText();
			String SoB = textSob.getText();
			//Chuyển kiểu
			double A = Double.parseDouble(SoA);
			double B = Double.parseDouble(SoB);
			if(B == 0)
			{
				textKetQua.setText("Không thể chia cho 0");
			}
			else {
				//Tính Chia
				double Chia = A / B;
				String KQ = String.valueOf(Chia);
				textKetQua.setText(KQ);
			}			
			
	}
	void TroVeManHinhChinh()
	{
		ManHinhChinh ManHinhChinh = new ManHinhChinh();
		ManHinhChinh.setVisible(true);
		this.setVisible(false);
	}
	
	
}
