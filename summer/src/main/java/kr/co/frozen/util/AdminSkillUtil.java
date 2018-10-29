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
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminSkillDAO;
import kr.co.frozen.model.AdminSkillModel;

@Component("adminSkill")
public class AdminSkillUtil {
	
	private static final Logger logger = LoggerFactory.getLogger( AdminSkillUtil.class );
	
	public advancedUtil util = new advancedUtil();
	
	@Autowired
	private SqlSession sqlSession;
	
	public JsonObject getSkillAbilityData() {
		
		JsonObject					json		= new JsonObject();
		JsonObject					result		= new JsonObject();
		JsonArray					array		= new JsonArray();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		ArrayList<AdminSkillModel>	model		= null;
		
		try {
			
			model = dao.getSkillAbilityData();
			
			for( AdminSkillModel data: model ) {
				
				result = new JsonObject();
				
				result.addProperty( "uid"				, data.getUid() );
				result.addProperty( "name"				, data.getName() );
				result.addProperty( "ability"			, data.getAbility() );
				result.addProperty( "background_color"	, data.getBackground_color() );
				result.addProperty( "font_color"		, data.getFont_color() );
				
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
	
	public JsonObject getSkillAbilityStorageData() {
		
		JsonObject					json	= new JsonObject();
		JsonObject					result	= new JsonObject();
		JsonArray					array	= new JsonArray();
		AdminSkillDAO				dao		= sqlSession.getMapper( AdminSkillDAO.class );
		ArrayList<AdminSkillModel>	model	= null;
		
		try {
			
			model = dao.getSkillAbilityStorageData();
			
			for( AdminSkillModel data: model ) {
				
				result = new JsonObject();
				
				result.addProperty( "uid"				, data.getUid() );
				result.addProperty( "name"				, data.getName() );
				result.addProperty( "ability"			, data.getAbility() );
				result.addProperty( "background_color"	, data.getBackground_color() );
				result.addProperty( "font_color"		, data.getFont_color() );
				
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
	public JsonObject insertSkillStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap(parameter.get("data").toString()) );
		
		try {
			
			dao.insertSkillStorageData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject deleteSkillStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		try {
			dao.deleteSkillStorageData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}

	@Transactional
	public JsonObject addSkillStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		AdminSkillModel				model		= null;
		
		try {
			model = dao.getSkillAbilityStorageData_single(parameter);
			
			parameter.put( "uid"				, model.getUid() );
			parameter.put( "name"				, model.getName() );
			parameter.put( "ability"			, model.getAbility() );
			parameter.put( "background_color"	, model.getBackground_color() );
			parameter.put( "font_color"			, model.getFont_color() );
			
			dao.addSkillStorageData(parameter);
			dao.afterAddSkillStorageData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getModifySkillStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		AdminSkillModel				model		= null;
		
		try {
			
			model = dao.getSkillAbilityStorageData_single(parameter);
			
			json.addProperty( "name"			, model.getName() );
			json.addProperty( "background_color", model.getBackground_color() );
			json.addProperty( "font_color"		, model.getFont_color() );
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject setModifySkillStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {

		JsonObject					json		= new JsonObject();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap(parameter.get("modify").toString()) );
		
		try {
			
			dao.setModifySkillStorageData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject removeSkillAbilityData( HttpServletRequest request ) throws UnsupportedEncodingException {
	
		JsonObject					json		= new JsonObject();
		AdminSkillDAO				dao			= sqlSession.getMapper( AdminSkillDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		try {
			
			dao.removeSkillAbilityData(parameter);
			dao.afterRemoveSkillAbilityData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject setSkillAbilityData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject							json		= new JsonObject();
		AdminSkillDAO						dao			= sqlSession.getMapper( AdminSkillDAO.class );
		ArrayList<HashMap<String, Object>>	parameter	= new ArrayList<HashMap<String, Object>>();
		String[] 							uid			= request.getParameterValues("uid");
		String[]							value		= request.getParameterValues("value");
		
		try {
			
			for( int i = 0; i < uid.length; i++ ) {
				
				HashMap<String, Object> temp = new HashMap<String, Object>();
				
				temp.put( "uid"		, uid[i] );
				temp.put( "value"	, value[i] );
				
				parameter.add( temp );
				
			}
			
			for( HashMap<String, Object> data : parameter ) {
				dao.setSkillAbilityData(data);
			}
			
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
