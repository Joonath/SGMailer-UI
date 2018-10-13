package main;
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;


public class SendMsg {
	
	String statusCode, body, respond;
	
	public SendMsg(String a, String b, String c, String d, String e, String f) {
		try{
			setupMessage(a,b,c,d,e,f);
		} catch(Exception g){
			g.printStackTrace();
		}
	}
	
	public SendMsg(String a, String b, String c, String d, String e, String f, String g, String h, File i){
		try {
			setupMessage(a,b,c,d,e,f,g,h,i);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
    /*Attachments attachments = new Attachments();
    Base64 x = new Base64();
    String encodedString = x.encodeAsString(loadPdfFromClasspath());
    attachments.setContent(encodedString);
    attachments.setDisposition("attachment");
    attachments.setFilename("xyx.pdf");
    attachments.setType("application/pdf");
    mail.addAttachments(attachments);*/
    
	void setupMessage(String subj, String dari, String nama, String utk, String isi, String tipe, String filePath, String fileName, File file) throws IOException{
		
		//Initalize sender, receiver, message, type
		String subject = subj;
		Email from = new Email(dari);
		
		if(nama.isEmpty()){
			//bypass from setName
		} else {
			from.setName(nama);
		}

		Email to = new Email(utk);
		String message = isi;

		Content content = new Content(tipe, message);
		Mail mail = new Mail(from, subject, to, content);
		//Done Initialization.

		//Attachment
		Base64 enc = new Base64();
		byte[] byteArr = null;
		
		byteArr = IOUtils.toByteArray(new FileInputStream(file));
		
		String encoded = enc.encodeAsString(byteArr);
		
		Attachments att = new Attachments();
		att.setDisposition("attachment");
		att.setContent(encoded);
		att.setFilename(fileName);
		
		
		mail.addAttachments(att);

		mail.setTemplateId("bbd70a72-c5a1-402a-a9dc-327753b66ef5");

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			
			statusCode = Integer.toString(response.getStatusCode());
			body = response.getBody();
			respond = response.getHeaders().toString();
			
			System.out.println("getStatusCode = " + response.getStatusCode() + "\n");
			System.out.println("getBody = " + response.getBody() + "\n");
			System.out.println("response = " + response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
	

	void setupMessage(String subj, String dari, String nama, String utk, String isi, String tipe) throws IOException{
		
		//Initalize sender, receiver, message, type
		String subject = subj;
		Email from = new Email(dari);
		
		if(nama.isEmpty()){
			//bypass from setName
		} else {
			from.setName(nama);
		}
		
		Email to = new Email(utk);
		String message = isi;

		Content content = new Content(tipe, message);
		Mail mail = new Mail(from, subject, to, content);
		
		//Done Initialization.
		
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			
			
			statusCode = Integer.toString(response.getStatusCode());
			body = response.getBody();
			respond = response.getHeaders().toString();
			
			System.out.println("getStatusCode = " + response.getStatusCode() + "\n");
			System.out.println("getBody = " + response.getBody() + "\n");
			System.out.println("response = " + response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
	
/*	StringBuilder identifyAttachmentType(String a){
		String[] ret = a.toLowerCase().split("\\.");
		StringBuilder build = new StringBuilder();
		boolean found = false;
		
		
		for(int i = 0; i < mime.images.length; i++){
			if(ret[1].equalsIgnoreCase(mime.images[i])){
				build.append("image/*");
				found = true;
				break;
			}
		}
		
		if(found){
			//skip
		} else {
			for(int i = 0; i < mime.application.length; i++){
				if(ret[1].equalsIgnoreCase(mime.application[i])){
					build.append("application/*");
					found = true;
					break;
				}
			}
		}
		
		if(found){
			//skip
		} else {
			for(int i = 0; i < mime.microsoft_app.length; i++){
				if(ret[1].equalsIgnoreCase(mime.microsoft_app[i])){
					build.append("application/*");
					found = true;
					break;
				}
			}
		}
		
		return build;
	}*/
	
	public String getStatusCode(){
		return statusCode;
	}
	
	public String getBody(){
		return body;
	}
	
	public StringBuilder getHeaders(){
		String ret = respond;
		StringBuilder build = new StringBuilder();

		if(ret.contains(", ")){
			String temp = ret.replace(", ", "\n");
			build.append(temp);
		}


		return build;
	}
	
	
}