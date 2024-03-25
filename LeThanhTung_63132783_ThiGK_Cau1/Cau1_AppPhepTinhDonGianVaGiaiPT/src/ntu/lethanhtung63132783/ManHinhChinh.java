package ntu.lethanhtung63132783;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManHinhChinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPhepToanDonGian;
	private JLabel lblNewLabel;
	private JButton btnGiaiPT;
	
	/**
	 * Create the frame.
	 */
	public ManHinhChinh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnPhepToanDonGian = new JButton("Phép toán đơn giản");
		btnPhepToanDonGian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppPhepToanDonGian();
			}
		});
		btnPhepToanDonGian.setBackground(new Color(192, 192, 192));
		btnPhepToanDonGian.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPhepToanDonGian.setBounds(45, 115, 165, 39);
		contentPane.add(btnPhepToanDonGian);
		
		lblNewLabel = new JLabel("Chương trình phép tính đơn giản và giải phương trình");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(63, 10, 410, 21);
		contentPane.add(lblNewLabel);
		
		btnGiaiPT = new JButton("Giải phương trình");
		btnGiaiPT.setBackground(new Color(0, 255, 255));
		btnGiaiPT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGiaiPT.setBounds(315, 115, 176, 39);
		contentPane.add(btnGiaiPT);
	}
	void AppPhepToanDonGian()
	{
		boolean ktraSuKien = btnPhepToanDonGian.isEnabled();
		if(ktraSukien==true)
		{
			
		}
	}
	
}
