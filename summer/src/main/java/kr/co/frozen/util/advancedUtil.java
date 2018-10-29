package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class advancedUtil {

	public Object nullCheck( Object value ) {
		
		if( value == null ) {
			return "";
		} else {
			return value;
		}
		
	}
	
	public HashMap<String, Object> getRequestValues( HttpServletRequest request ) throws UnsupportedEncodingException {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		
		Enumeration names = request.getParameterNames();
		
		while( names.hasMoreElements() ) {
			String key = names.nextElement().toString();
			String value =  URLDecoder.decode(request.getParameter(key), "UTF-8");
			
			parameter.put( key, value );
		}
		
		return parameter;
	}
	
	public HashMap<String, Object> getJsonToHashMap( String JsonString ) {
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse( JsonString );
		Gson gson = new Gson();
		
		parameter.putAll( gson.fromJson(element, HashMap.class) );
		
		return parameter;
	}
	
	public String getCurrentTime() {
		
		String time = "";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		time = sdf.format(date);
		
		return time;
	}
	
	public String getConvertFileName() {
		
		String fileName = "";
		
		fileName = UUID.randomUUID().toString();
		
		return fileName;
	}
	
	public void sendEmail( String host, int port, String SMTPuser, String password, String fromEmail, String fromName, String subject, String message, String toEmail ) throws Exception {
		
		String charSet = "utf-8";
		
		HtmlEmail email = new HtmlEmail();
		
		email.setDebug(false);
		email.setCharset(charSet);
		email.setSSL(false);
		email.setHostName(host);
		email.setSmtpPort(port);
		email.setAuthentication(SMTPuser, password);
		email.setTLS(true);
		email.addTo(toEmail, "Frozen Portfolio Admin");
		email.setFrom(fromEmail, fromName, charSet);
		email.setSubject("** [ Portfolio - Notice ] ** | " + subject);
		email.setHtmlMsg(message);
		email.send();
		
	}
	
}