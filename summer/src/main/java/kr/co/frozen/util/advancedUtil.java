package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
	
}