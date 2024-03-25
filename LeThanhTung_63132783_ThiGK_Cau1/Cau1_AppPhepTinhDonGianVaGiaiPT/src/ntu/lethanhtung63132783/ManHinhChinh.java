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
	private JButton btnNewButton;
	
	/**
	 * Create the frame.
	 */
	public ManHinhChinh() {
		setTitle("Màn hình chính");
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
		btnPhepToanDonGian.setBounds(45, 115, 187, 39);
		contentPane.add(btnPhepToanDonGian);
		
		lblNewLabel = new JLabel("Ứng dụng phép tính đơn giản và giải phương trình");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(63, 10, 410, 21);
		contentPane.add(lblNewLabel);
		
		btnGiaiPT = new JButton("Giải phương trình");
		btnGiaiPT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GiaiPT();
			}
		});
		btnGiaiPT.setBackground(new Color(0, 255, 255));
		btnGiaiPT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGiaiPT.setBounds(315, 115, 176, 39);
		contentPane.add(btnGiaiPT);
		
		btnNewButton = new JButton("Đóng App");
		btnNewButton.setForeground(new Color(64, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(315, 271, 231, 52);
		contentPane.add(btnNewButton);
	}
	void AppPhepToanDonGian()
	{
		ManHinhPhepToanDonGian ManHinhTinhToan = new ManHinhPhepToanDonGian();
		ManHinhTinhToan.setVisible(true);
		this.setVisible(false);
	}
	void GiaiPT()
	{
		ManHinhGiaiPT ManHinhGiaiPT = new ManHinhGiaiPT();
		ManHinhGiaiPT.setVisible(true);
		this.setVisible(false);
	}
	
	
}
