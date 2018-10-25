package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminOpenSourceDAO;
import kr.co.frozen.model.AdminOpenSourceModel;

@Component(value="adminOpenSource")
public class AdminOpenSourceUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminOpenSourceUtil.class );
	
	public advancedUtil util = new advancedUtil();
	
	@Autowired
	private SqlSession sqlSession;
	
	public JsonObject getOpenSourceData() {
		
		JsonObject						json 		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		ArrayList<AdminOpenSourceModel>	opensource 	= null;
		AdminOpenSourceDAO				dao			= sqlSession.getMapper( AdminOpenSourceDAO.class );
		
		try {
			
			opensource = dao.getOpenSourceData();
			
			for( AdminOpenSourceModel model: opensource ) {
				result = new JsonObject();
				
				result.addProperty( "uid"	, model.getUid() );
				result.addProperty( "name"	, model.getName() );
				result.addProperty( "type"	, model.getType() );
				result.addProperty( "url"	, model.getUrl() );
				
				array.add( result );
			}
			
			json.add( "opensource", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject addOpenSourceData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json 		= new JsonObject();
		AdminOpenSourceDAO				dao			= sqlSession.getMapper( AdminOpenSourceDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {	
			
			parameter.putAll( util.getJsonToHashMap(parameter.get("add_data").toString()) );
			
			dao.addOpenSourceData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject deleteOpenSourceData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json 		= new JsonObject();
		AdminOpenSourceDAO				dao			= sqlSession.getMapper( AdminOpenSourceDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {	
			
			dao.deleteOpenSourceData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getModifyOpenSourceData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json 		= new JsonObject();
		AdminOpenSourceModel			opensource 	= null;
		AdminOpenSourceDAO				dao			= sqlSession.getMapper( AdminOpenSourceDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			opensource = dao.getModifyOpenSourceData(parameter);
			
			if( opensource != null ) {
				json.addProperty( "uid"	, opensource.getUid() );
				json.addProperty( "name", opensource.getName() );
				json.addProperty( "type", opensource.getType() );
				json.addProperty( "url"	, opensource.getUrl() );
			}
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject modifyOpenSourceData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json 		= new JsonObject();
		AdminOpenSourceDAO				dao			= sqlSession.getMapper( AdminOpenSourceDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {	
			
			parameter.putAll( util.getJsonToHashMap(parameter.get("modify_data").toString()) );
			
			dao.modifyOpenSourceData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
}
