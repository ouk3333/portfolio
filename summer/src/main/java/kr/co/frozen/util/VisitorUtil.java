package kr.co.frozen.util;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

@Component("visitor")
public class VisitorUtil {

	public HashMap<String, Object> getClientAddress( HttpServletRequest request ) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		Enumeration header = request.getHeaderNames();
		
		while( header.hasMoreElements() ) {
			String key = header.nextElement().toString();
			String value = request.getHeader(key);
			
			result.put( key , value );
		}
		
		if( result.get("x-forwarded-for") == null ) {
			String addr = "";
			addr = request.getHeader("X-FORWARDED-FOR");
			
			if( addr == null || "".equals(addr) ) {
				addr = request.getRemoteAddr();
				result.put( "x-forwarded-for", addr );
			}
		}
		
		return result;
	}
	
}
