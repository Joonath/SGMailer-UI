package main;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.ckeditor.*;

public class ContentPanel extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Main main;
	JTextArea curhat;
	JLabel title;
	
	JPanel panel;
	JScrollPane scroll;
	
	JButton submit;
	
	public String pass;
	
	public ContentPanel(Main main){
		this.main = main;
		pass = main.inpIsi.getText();
		initFrame();
		callFrame();
		revalidate();
	}
	
	void initFrame(){
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	void callFrame(){
		
		panel = new JPanel(new FlowLayout());
		curhat = new JTextArea();
		curhat.setMargin(new Insets(20, 20, 20, 20));
		
		curhat.setText(pass);
		curhat.setFont(new Font("Georgia", Font.LAYOUT_LEFT_TO_RIGHT, 14));
		curhat.setLineWrap(true);
		curhat.setTabSize(3);
		scroll = new JScrollPane(curhat);
		
		title = new JLabel("Input The Content");
		title.setFont(new Font("Calibri", Font.BOLD, 22));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		submit = new JButton("Confirm Changes");
		panel.add(submit);
		
		submit.addActionListener(this);
		curhat.addKeyListener(this);
		
		add(scroll, BorderLayout.CENTER);
		add(title, BorderLayout.NORTH);
		add(panel, BorderLayout.SOUTH);
		add(new JPanel(new FlowLayout()), BorderLayout.EAST);
		add(new JPanel(new FlowLayout()), BorderLayout.WEST);
	}

	@Override
	public void actionPerformed(ActionEvent x) {
		if(x.getSource() == submit){
			main.content = curhat.getText();
			main.refreshData();
			dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER){
			if(e.getSource() == curhat){
				submit.doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
