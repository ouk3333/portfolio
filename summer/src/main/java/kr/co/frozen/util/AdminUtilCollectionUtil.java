package kr.co.frozen.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.frozen.dao.AdminDAO;
import kr.co.frozen.dao.AdminUtilCollectionDAO;

@Component("adminCollection")
public class AdminUtilCollectionUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminUtilCollectionUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getShortURL( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json			= new JsonObject();
		AdminDAO					api_dao			= sqlSession.getMapper( AdminDAO.class );
		HashMap<String, Object>		parameter		= util.getRequestValues(request);
		String						ClientID		= "";
		String						ClientSecret	= "";
		
		try {
			
			parameter.put("api_key", "naverClientID");
			ClientID = api_dao.getApiKey(parameter);
			
			parameter.put("api_key", "naverClientSecret" );
			ClientSecret = api_dao.getApiKey(parameter);
			
			String value 	= parameter.get("value").toString();
			String api_url	= "https://naveropenapi.apigw.ntruss.com/util/v1/shorturl";
			
			URL url = new URL(api_url);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type"			, "application/json");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID"	, ClientID);
			con.setRequestProperty("X-NCP-APIGW-API-KEY"	, ClientSecret);
			
			JsonObject json_url = new JsonObject();
			json_url.addProperty( "url", URLEncoder.encode(value, "UTF-8") );
			
			String postParams = json_url.toString();
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			
			if(responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
            	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            	
            	throw new Exception("responseCode: " + responseCode);
			}
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
						
			JsonParser	parser = new JsonParser();
			JsonElement element = parser.parse( response.toString() );
			
			json.add( "result", element );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getDBTableNames() {
		
		JsonObject							json			= new JsonObject();
		JsonObject							result			= new JsonObject();
		JsonArray							array			= new JsonArray();
		ArrayList<HashMap<String, Object>>	table_names		= null;
		AdminUtilCollectionDAO				dao				= sqlSession.getMapper( AdminUtilCollectionDAO.class );
		
		try {
		
			table_names = dao.getDBTableNames();
			
			for( HashMap<String, Object> model: table_names ) {
				result = new JsonObject();
				
				result.addProperty( "table_name", model.get("table_name").toString() );
				
				array.add( result );
			}
			
			json.add( "table_names", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getSelectDataBase( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		JsonObject					result		= new JsonObject();
		JsonArray					array		= new JsonArray();
		AdminUtilCollectionDAO		dao			= sqlSession.getMapper( AdminUtilCollectionDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		try {
			
			ArrayList<HashMap<String, Object>>	columns = dao.getSelectDataBase(parameter);
			ArrayList<HashMap<String, Object>>	data	= dao.getSelectDataBaseData(parameter);
			
			for( HashMap<String, Object> model: columns ) {
				result = new JsonObject();
				
				result.addProperty( "column", model.get("COLUMN_NAME").toString() );
				
				array.add( result );
			}
			
			json.add( "columns", array );
			
			array = new JsonArray();
			
			for( HashMap<String, Object> model: data ) {
				result = new JsonObject();
				
				for( int i = 0; i < columns.size(); i++ ) {
					String column_name = columns.get(i).get("COLUMN_NAME").toString();
					
					result.addProperty( column_name, model.get(column_name).toString() );
				}
				
				array.add( result );
			}
			
			json.add( "data", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
		}
		
		return json;
	}
	
}
