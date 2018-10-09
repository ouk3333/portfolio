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

import kr.co.frozen.dao.AdminLicenseDAO;
import kr.co.frozen.model.AdminLicenseModel;

@Component("adminLicense")
public class AdminLicenseUtil {
	
	private static final Logger logger = LoggerFactory.getLogger( AdminLicenseUtil.class );
	
	public advancedUtil util = new advancedUtil();
	
	@Autowired
	private SqlSession sqlSession;
	
	public JsonObject getLicenseData() {
		
		JsonObject						json 	= new JsonObject();
		AdminLicenseDAO					dao		= sqlSession.getMapper( AdminLicenseDAO.class );
		ArrayList<AdminLicenseModel>	model	= null;
		JsonObject						result	= new JsonObject();
		JsonArray						array	= new JsonArray();
		
		try {
			
			model = dao.getLicenseData();
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					result = new JsonObject();
					
					result.addProperty( "uid"		, model.get(i).getUid() );
					result.addProperty( "type"		, model.get(i).getType() );
					result.addProperty( "name"		, model.get(i).getName() );
					result.addProperty( "remarks"	, model.get(i).getRemarks() );
					result.addProperty( "order_no"	, model.get(i).getOrder_no() );
					
					array.add( result );
				}
				
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
	public JsonObject addLicenseData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminLicenseDAO			dao			= sqlSession.getMapper( AdminLicenseDAO.class );
		HashMap<String, Object> parameter 	= util.getRequestValues(request);
		
		parameter.putAll(util.getJsonToHashMap(parameter.get("data").toString()));
		
		try {
			
			dao.addLicenseData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getLicenseStorageData() {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		AdminLicenseDAO					dao			= sqlSession.getMapper( AdminLicenseDAO.class );
		ArrayList<AdminLicenseModel> 	model		= null;
		
		try {
			
			model = dao.getLicenseStorageData();
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					result = new JsonObject();
					
					result.addProperty( "uid"		, model.get(i).getUid() );
					result.addProperty( "type"		, model.get(i).getType() );
					result.addProperty( "name"		, model.get(i).getName() );
					result.addProperty( "remarks"	, model.get(i).getRemarks() );
					
					array.add( result );
				}				
				
			}
			
			json.add( "data", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject addlicenseStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json 		= new JsonObject();
		HashMap<String, Object> parameter 	= util.getRequestValues(request);
		AdminLicenseDAO			dao			= sqlSession.getMapper( AdminLicenseDAO.class );
		AdminLicenseModel		model		= dao.getLicenseStorageData_single(parameter);
		
		try {
			
			if( model == null ) {
				throw new Exception("License Data Not Found");
			}
			
			int order_no = dao.getMaxLicenseStorageOrderNo();
			
			parameter.put( "type"		, model.getType() );
			parameter.put( "name"		, model.getName() );
			parameter.put( "remarks"	, model.getRemarks() );
			parameter.put( "order_no"	, order_no );
			
			dao.addLicenseStorageData(parameter);
			dao.afterAddLicenseStorageData(parameter);
			
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
	public JsonObject removeLicenseData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json 		= new JsonObject();
		AdminLicenseDAO				dao 		= sqlSession.getMapper( AdminLicenseDAO.class );
		HashMap<String, Object>		parameter 	= util.getRequestValues(request);
		
		try {
			
			dao.removeLicenseData(parameter);
			dao.afterRemoveLicenseData(parameter);
			
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
	public JsonObject deleteLicenseStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminLicenseDAO				dao			= sqlSession.getMapper( AdminLicenseDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		try {
			
			dao.deleteLicenseStorageData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getLicenseStorageModifyData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminLicenseDAO				dao			= sqlSession.getMapper( AdminLicenseDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		AdminLicenseModel			model		= null;
		
		try {
			
			model = dao.getLicenseStorageData_single(parameter);
			
			if( model != null ) {
				json.addProperty( "type"	, model.getType() );
				json.addProperty( "name"	, model.getName() );
				json.addProperty( "remarks"	, model.getRemarks() );
			}
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject setModifyLicenseData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject					json		= new JsonObject();
		AdminLicenseDAO				dao			= sqlSession.getMapper( AdminLicenseDAO.class );
		HashMap<String, Object>		parameter	= util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap( parameter.get("modify").toString() ) );
		
		try {
			
			dao.setModifyLicenseData(parameter);
			
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
