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

import kr.co.frozen.dao.AdminActivityDAO;
import kr.co.frozen.model.AdminActivityModel;

@Component("adminActivity")
public class AdminActivityUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminActivityUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getActivityData() {
		
		JsonObject						json	= new JsonObject();
		JsonObject						result	= new JsonObject();
		JsonArray						array	= new JsonArray();
		AdminActivityDAO				dao		= sqlSession.getMapper( AdminActivityDAO.class );
		ArrayList<AdminActivityModel>	model	= null;
		
		try {
			
			model = dao.getActivityData();
			
			for( AdminActivityModel data: model ) {
				
				result = new JsonObject();
				
				result.addProperty( "uid"		, data.getUid() );
				result.addProperty( "icon"		, data.getIcon() );
				result.addProperty( "year"		, data.getYear() );
				result.addProperty( "contents"	, data.getContents() );
				result.addProperty( "order_no"	, data.getOrder_no() );
				
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
	
	public JsonObject getActivityStorageData() {
		
		JsonObject						json	= new JsonObject();
		JsonObject						result	= new JsonObject();
		JsonArray						array	= new JsonArray();
		AdminActivityDAO				dao		= sqlSession.getMapper( AdminActivityDAO.class );
		ArrayList<AdminActivityModel>	model	= null;
		
		try {
			
			model = dao.getActivityStorageData();
			
			for( AdminActivityModel data: model ) {
				
				result = new JsonObject();
				
				result.addProperty( "uid"		, data.getUid() );
				result.addProperty( "icon"		, data.getIcon() );
				result.addProperty( "year"		, data.getYear() );
				result.addProperty( "contents"	, data.getContents() );
				
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
	public JsonObject insertActivityStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminActivityDAO				dao			= sqlSession.getMapper( AdminActivityDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap(parameter.get("insert").toString()) );
		
		try {
			
			dao.insertActivityStorageData( parameter );
			
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
	public JsonObject deleteActivityStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminActivityDAO				dao			= sqlSession.getMapper( AdminActivityDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			dao.deleteActivityStorageData(parameter);
			
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
	public JsonObject addActivityStorageDataModal( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminActivityDAO				dao			= sqlSession.getMapper( AdminActivityDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		AdminActivityModel				model		= null;
		
		try {
			
			model = dao.getActivityStorageData_single(parameter);
			
			parameter.put( "icon", model.getIcon() );
			parameter.put( "year", model.getYear() );
			parameter.put( "contents", model.getContents() );
			
			dao.addActivityStorageDataModal(parameter);
			dao.afterAddActivityStorageDataModal(parameter);
			
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
	public JsonObject removeActivityData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		AdminActivityDAO				dao			= sqlSession.getMapper( AdminActivityDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			dao.removeActivityData(parameter);
			dao.afterRemoveActivityData(parameter);
			
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
