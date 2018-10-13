package function;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileType {
	
	/*
	 * DON'T MESS WITH OTHER SCRIPT THAN desc AND ext!
	 */
	
	private String[] desc = {
		"Word Document (.doc, .docx)", //1
		"Excel Document (.xlsx, .xls)",//2
		"PDF (.pdf)", //3
		"PowerPoint (.pptx, .ppt)", //4
		"Web File (.html, .htm, .css, .js, .php, .sql)", //5
		"Image File (.png, .jpg, .jpeg)", //6
		"Archive (.zip, .rar, .tar.gz)" //7
	};
	
	private String[][] ext = {
			{"doc", "docx"}, //1
			{"xlsx", "xls"}, //2
			{"pdf"}, //3
			{"pptx", "ppt"}, //4
			{"html", "htm", "css", "js", "php", "sql"}, //5
			{"png", "jpg", "jpeg"}, //6
			{"zip", "tar.gz", "rar"} //7
			
	};
	
	private int counts = desc.length;
	
	FileNameExtensionFilter fe;
	JFileChooser session;
	
	public FileType(JFileChooser session){
		this.session = session;
		
		generateCode();
	}
	
	void generateCode(){
		for(int i = 0; i < counts; i++){
			generateFileType(desc[i], ext[i]);	
			
		}
	}
	
	private void generateFileType(String a, String...b){
		fe = new FileNameExtensionFilter(a, b);
		session.addChoosableFileFilter(fe);
	}
	
}
