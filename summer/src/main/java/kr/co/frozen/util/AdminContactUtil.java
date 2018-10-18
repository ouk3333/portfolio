package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminContactDAO;
import kr.co.frozen.model.AdminContactModel;

@Component("adminContact")
public class AdminContactUtil {

	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getContactData() {
		
		JsonObject						json 	= new JsonObject();
		JsonObject						result	= new JsonObject();
		JsonArray						array	= new JsonArray();
		AdminContactDAO					dao		= sqlSession.getMapper( AdminContactDAO.class );
		ArrayList<AdminContactModel>	model 	= null;
		
		try {
			
			model = dao.getContactData();
			
			for( AdminContactModel data: model ) {
				
				result = new JsonObject();
				
				result.addProperty( "uid"	, data.getUid() );
				result.addProperty( "name"	, data.getName() );
				result.addProperty( "type"	, data.getType() );
				result.addProperty( "value"	, data.getValue() );
				
				array.add( result );
			}
			
			json.add( "data", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject setContactData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json 		= new JsonObject();
		AdminContactDAO				dao			= sqlSession.getMapper( AdminContactDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		try {
			
			dao.setContactData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
}
