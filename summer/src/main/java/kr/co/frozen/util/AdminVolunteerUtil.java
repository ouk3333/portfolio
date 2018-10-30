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

import kr.co.frozen.dao.AdminVolunteerDAO;
import kr.co.frozen.model.AdminVolunteerModel;

@Component("adminVolunteer")
public class AdminVolunteerUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminVolunteerUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getVolunteerData() {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		ArrayList<AdminVolunteerModel>	volunteer	= null;
		AdminVolunteerDAO				dao			= sqlSession.getMapper( AdminVolunteerDAO.class );
		
		try {
			
			volunteer = dao.getVolunteerData();
			
			for( AdminVolunteerModel model: volunteer ) {
				result = new JsonObject();
				
				result.addProperty( "uid"		, model.getUid() );
				result.addProperty( "name"		, model.getName() );
				result.addProperty( "location"	, model.getLocation() );
				result.addProperty( "reg_date"	, model.getReg_date() );
				
				array.add( result );
			}
			
			json.add( "volunteer", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject insertVolunteerData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminVolunteerDAO				dao			= sqlSession.getMapper( AdminVolunteerDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			parameter.putAll( util.getJsonToHashMap(parameter.get("insert_data").toString()) );
			
			dao.insertVolunteerData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject deleteVolunteerData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminVolunteerDAO				dao			= sqlSession.getMapper( AdminVolunteerDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			dao.deleteVolunteerData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getModifyVolunteerData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminVolunteerDAO				dao			= sqlSession.getMapper( AdminVolunteerDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		AdminVolunteerModel				model		= null;
		
		try {
			
			model = dao.getVolunteerData_single(parameter);
			
			json.addProperty( "uid", model.getUid() );
			json.addProperty( "name", model.getName() );
			json.addProperty( "location", model.getLocation() );
			json.addProperty( "reg_date", model.getReg_date() );
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject updateVolunteerData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminVolunteerDAO				dao			= sqlSession.getMapper( AdminVolunteerDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			parameter.putAll( util.getJsonToHashMap(parameter.get("modify_data").toString()) );
			
			dao.updateVolunteerData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
}
