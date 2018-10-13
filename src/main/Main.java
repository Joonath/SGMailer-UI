package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;

import function.FileType;


public class Main extends JFrame implements ActionListener, KeyListener, MouseListener{

	private static final long serialVersionUID = 4;
	//Initial Classes
	SendMsg send;
	ContentPanel fullscreen;
	FileType ft;
	
	//Main Components
	JLabel judul, dari, nama, penerima, isi, tipePengiriman;
	JButton submit, switchPanel;
	JTextField inpJudul, inpDari, inpNamaPengirim, inpPenerima;
	JTextArea inpIsi;
	
	JRadioButton tipe_plain, tipe_html;
	ButtonGroup groupTipe;
	JPanel panel_content, pJudul, pDari, pPenerima, pNama;
	
	JScrollPane scroll;
	
	String subj, from, sender, utk, content, getType;
	
	//==MenuBar==
	JMenuBar menu;
	JMenu about;
	
	//==Attachment==
	JFileChooser chooser;
	JLabel attach;
	JButton butt_attach;
	JPanel panel_attach;
	
	String getPath, getFullPath;
	JLabel showCurrentSelectedPath;
	File setFile;
	
	
	public Main(){
		initFrame();
		callFrame();
		setVisible(true);
	}
	
	void refreshData(){
		inpIsi.setText(content);
	}
	
	void refreshPath(){
		showCurrentSelectedPath.setText(getPath);
	}
	
	void initFrame(){
		setResizable(true);
		setSize(500,500);
		setTitle("Mailer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/icon.jpg"));
		
		menu = new JMenuBar();
		about = new JMenu("About");
		about.addMouseListener(this);
		menu.add(about);
		setJMenuBar(menu);
		
	}
	
	void callFrame(){

		JPanel dalam = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Dimension prefSize = new Dimension(200, 30);
		
		panel_content = new JPanel(new GridLayout(7,2));
		pJudul = new JPanel(new FlowLayout(FlowLayout.CENTER));
		judul = new JLabel("Subject: ");
		inpJudul = new JTextField();
		inpJudul.setPreferredSize(prefSize);
		
		pJudul.add(inpJudul);
		dalam.add(pJudul);
		
		panel_content.add(judul);
		panel_content.add(dalam);
		
		pDari = new JPanel(new FlowLayout(FlowLayout.CENTER));
		dari = new JLabel("From: ");
		inpDari = new JTextField();
		inpDari.setPreferredSize(prefSize);
		
		dalam = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		pDari.add(inpDari);
		dalam.add(pDari);
		
		panel_content.add(dari);
		panel_content.add(dalam);
		//--
		nama = new JLabel("Nama Pengirim (Optional)");
		inpNamaPengirim = new JTextField();
		pNama = new JPanel(new FlowLayout(FlowLayout.CENTER));
		inpNamaPengirim.setPreferredSize(prefSize);
		
		dalam = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		pNama.add(inpNamaPengirim);
		dalam.add(pNama);
		
		panel_content.add(nama);
		panel_content.add(dalam);
		
		inpNamaPengirim.addKeyListener(this);
		
		pPenerima = new JPanel(new FlowLayout(FlowLayout.CENTER));
		penerima = new JLabel("To: ");
		inpPenerima = new JTextField();
		inpPenerima.setPreferredSize(prefSize);
		
		dalam = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pPenerima.add(inpPenerima);
		dalam.add(pPenerima);
		
		panel_content.add(penerima);
		panel_content.add(dalam);
		
		
		isi = new JLabel("Content: ");
		
		inpIsi = new JTextArea();
		inpIsi.setLineWrap(true);
		scroll = new JScrollPane(inpIsi);
		
		panel_content.add(isi);
		panel_content.add(scroll);
		
		tipePengiriman = new JLabel("Content Type");
		tipe_html = new JRadioButton("text/html");
		tipe_plain = new JRadioButton("text/plain");
		
		groupTipe = new ButtonGroup();
		groupTipe.add(tipe_html);
		groupTipe.add(tipe_plain);
		dalam = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelType = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dalam.add(tipe_html);
		dalam.add(tipe_plain);
		panelType.add(dalam);
		
		panel_content.add(tipePengiriman);
		panel_content.add(panelType);
		
		submit = new JButton("Submit");

		
		switchPanel = new JButton("Expand Content Panel");
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		buttonPanel.add(submit);
		buttonPanel.add(switchPanel);
		
		JLabel title = new JLabel("Emailer v" + serialVersionUID + " feat. SendGrid");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Comic Sans MS", Font.ROMAN_BASELINE, 16));
		
		
		//ADD: Attachment
		dalam = new JPanel(new FlowLayout(FlowLayout.CENTER));
		attach = new JLabel("Attachment");
		
		showCurrentSelectedPath = new JLabel(getPath + "");
		butt_attach = new JButton("Add File");
		panel_attach = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		dalam.add(butt_attach);
		dalam.add(showCurrentSelectedPath);
		panel_attach.add(dalam);
		
		panel_content.add(attach);
		panel_content.add(panel_attach);
		//END OF ATTACHMENT
		
		//Action Listeners
		
		submit.addActionListener(this);
		switchPanel.addActionListener(this);
		butt_attach.addActionListener(this);
		
		inpJudul.addKeyListener(this);
		inpDari.addKeyListener(this);
		inpPenerima.addKeyListener(this);
		inpIsi.addKeyListener(this);
		
		add(panel_content, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		add(new JPanel(new FlowLayout()), BorderLayout.EAST);
		add(new JPanel(new FlowLayout()), BorderLayout.WEST);
		add(title, BorderLayout.NORTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == submit){
			checkValidity();
		} else if(e.getSource() == switchPanel){
			fullscreen = new ContentPanel(Main.this);
		} else if(e.getSource() == butt_attach){
			findFile();
		}
	}
	
	void findFile(){
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Choose file to upload");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(true);
		
		ft = new FileType(this.chooser);
		
		//chooser.addChoosableFileFilter(new FileNameExtensionFilter("Word Documents (.docx, .doc)", "docx", "doc"));
		//ft.generateFilter(this.chooser);
		
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			if(chooser.getSelectedFile().exists()){
				System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				getFullPath = chooser.getSelectedFile().toString();
				getPath = chooser.getSelectedFile().getName().toString();
				
				try {
					setFile = new File(getFullPath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("File doesn't exists bro");
			}
		} else {
			System.out.println("No Selection ");
			getFullPath = getPath = null;
		}

		refreshPath();
		
	}

	void checkValidity(){
		subj = inpJudul.getText();
		from = inpDari.getText();
		sender = inpNamaPengirim.getText();
		utk = inpPenerima.getText();
		content = inpIsi.getText();
		getType = "";
		boolean choosen = false;

		if(!tipe_html.isSelected() && !tipe_plain.isSelected()){
			choosen = false;
		} else {
			choosen = true;
			if(tipe_html.isSelected())
				getType = "text/html";
			else 
				getType = "text/plain";
		}

		if(subj.isEmpty() || from.isEmpty() || utk.isEmpty() || content.isEmpty() || !choosen){
			alert(null, "Please input all data", "Warning", 2);
			return;
		} else {
			if(!(from.contains("@") && from.contains(".")) ){
				alert(null, "The From section is not valid", "Warning", 2);
				return;
			} else if(!(utk.contains("@") || utk.contains(".")) ){
				alert(null, "The To section is not valid", "Warning", 2);
				return;
			}

			String splitMailFrom[] = from.split("@");
			String splitTLDFrom[] = splitMailFrom[1].split("\\.");

			String splitMailTo[] = utk.split("@");
			String splitTLDTo[] = splitMailTo[1].split("\\.");

			if(splitMailFrom[0].isEmpty() || splitMailTo[0].isEmpty() || splitTLDFrom[0].isEmpty() || splitTLDTo[0].isEmpty()
					|| splitTLDFrom.length < 2 || splitTLDTo.length < 2){
				alert(null, "Email format is not valid", "Warning", 2);
				return;
			} else {
				
				if(splitMailTo[1].toLowerCase().matches(".*yahoo.*") || splitMailTo[1].toLowerCase().matches(".*ymail.*")){
					alert(null, "Sorry, yahoo and ymail is throttled, please send with another mailing system.", "Sorry", 1);
					return;
				}
				
				
				alert(null, "Proceeding email.\n Cek status di sendgrid.com", "Notification", 1);
				
				//TODO: Convert enter jika yg dipilih text/plain 
				
				//CODE ABOVE
				if(getFullPath == null){
					//Tanpa Upload
					send = new SendMsg(subj, from, sender, utk, content, getType);
					checkEverything();
				} else {
					//Dgn Upload
					send = new SendMsg(subj, from, sender, utk, content, getType, getFullPath, getPath, setFile);
					checkEverything();
				}
				
				
				
				
			}
		}
	}
	
	void checkEverything(){
		String stat = "Code: " + send.getStatusCode() + "\n";
		String head = "Header: " + send.getHeaders() + "\n";
		String body = "Body: " + send.getBody();
		
		alert(null, "Status\n" + stat + head + body, "Status", 1);
	}
	
	void alert(Component a, Object b, String c, int d){
		JOptionPane.showMessageDialog(a, b, c, d);
	}
	
	public static void main(String[] args){
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		new Main();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == inpIsi){
			checkValidity();
		} else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(e.getSource() == inpDari || e.getSource() == inpJudul || e.getSource() == inpPenerima || e.getSource() == inpNamaPengirim){
				checkValidity();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == about){

			JOptionPane.showMessageDialog(this, "\u00a9" + "2018 Joo Nath. API By SendGrid");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
