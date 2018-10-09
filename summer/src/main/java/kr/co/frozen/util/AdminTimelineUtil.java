package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.frozen.dao.AdminTimelineDAO;
import kr.co.frozen.model.AdminTimelineModel;

@Component("adminTimeline")
public class AdminTimelineUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminTimelineUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	@Value("#{storage[storage]}")
	private String dev_storage;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getTimelineData() {
		
		AdminTimelineDAO					dao		= sqlSession.getMapper( AdminTimelineDAO.class );
		ArrayList<AdminTimelineModel>	model	= null;
		JsonObject 						json 	= new JsonObject();
		JsonObject						result	= new JsonObject();
		JsonArray						array	= new JsonArray();
		
		try {
			model = dao.getTimelineData();
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					
					result = new JsonObject();
					
					int		uid			= model.get(i).getUid();
					String 	event 		= model.get(i).getEvent();
					String 	date 		= model.get(i).getYear() + "-" + model.get(i).getMonth() + "-" + model.get(i).getDay();
					String 	remarks		= model.get(i).getRemarks();
					int		order_no 	= model.get(i).getOrder_no();
					
					result.addProperty( "uid"		, uid );
					result.addProperty( "event"		, event );
					result.addProperty( "date"		, date );
					result.addProperty( "remarks"	, remarks );
					result.addProperty( "order_no"	, order_no);
					
					array.add( result );
				}
				
			}
			
			json.add("data", array);
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject addTimelineData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject 				json 		= new JsonObject();
		HashMap<String, Object> parameter 	= util.getRequestValues( request );
		AdminTimelineDAO			dao			= sqlSession.getMapper( AdminTimelineDAO.class );
		
		try {
		
			dao.createTimelineData( parameter );
			
			json.addProperty( "state" , "success" );
			
		} catch (Exception e) {
			json.addProperty( "state"	, "error" );
			json.addProperty( "error"	, e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getTimelineStorageData() {
		
		JsonObject						json 	= new JsonObject();
		JsonObject						result 	= new JsonObject();
		JsonArray						array	= new JsonArray();
		AdminTimelineDAO					dao		= sqlSession.getMapper( AdminTimelineDAO.class );
		
		try {
			
			ArrayList<AdminTimelineModel>	model	= dao.getTimelineStorageData();
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					
					result = new JsonObject();
					
					int		uid		= model.get(i).getUid();
					String	event 	= model.get(i).getEvent();
					String	date	= model.get(i).getYear() + "-" + model.get(i).getMonth() + "-" + model.get(i).getDay();
					String	remarks	= model.get(i).getRemarks();
					
					Date new_date = new SimpleDateFormat("yyyy-MM-dd").parse( date );
					date = new SimpleDateFormat("yyyy-MM-dd").format( new_date );
					
					result.addProperty( "uid"		, uid );
					result.addProperty( "event"		, event );
					result.addProperty( "date"		, date );
					result.addProperty( "remarks"	, remarks );
					
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
	public JsonObject deleteTimelineStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject json = new JsonObject();
		AdminTimelineDAO dao = sqlSession.getMapper( AdminTimelineDAO.class );
		HashMap<String, Object> parameter = util.getRequestValues(request);
		
		try {
			
			dao.deleteTimelineStorageData( parameter );
			
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
	public JsonObject modifyTimelineStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject json = new JsonObject();
		AdminTimelineDAO dao = sqlSession.getMapper( AdminTimelineDAO.class );
		HashMap<String, Object> parameter = util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap(parameter.get("modify").toString()) );
		
		try {
			
			dao.modifyTimelineStorageData(parameter);
			
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
	public JsonObject addTimelineStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject json = new JsonObject();
		AdminTimelineDAO dao = sqlSession.getMapper( AdminTimelineDAO.class );
		HashMap<String, Object> parameter = util.getRequestValues(request);
		
		try {
			
			AdminTimelineModel 	model 		= dao.getTimelineStorageData_single(parameter);
			int					order_no 	= dao.getMaxTimelineStorageOrderNo();
			
			parameter.put( "event"		, model.getEvent() );
			parameter.put( "year"		, model.getYear() );
			parameter.put( "month"		, model.getMonth() );
			parameter.put( "day"		, model.getDay() );
			parameter.put( "remarks"	, model.getRemarks() );
			parameter.put( "order_no"	, order_no );
			
			dao.addTimelineStorageData(parameter);
			dao.afterAddTimelineStorageData(parameter);
			
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
	public JsonObject removeTimelineData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminTimelineDAO			dao			= sqlSession.getMapper( AdminTimelineDAO.class );
		HashMap<String, Object> parameter 	= util.getRequestValues(request);
		
		try {
			
			dao.removeTimelineData(parameter);
			dao.afterRemoveTimelineData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		
		return json;
	}
	
	// 수정 할 타임라인 데이터 가져오기
	public JsonObject getModifyTimelineData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminTimelineDAO			dao			= sqlSession.getMapper( AdminTimelineDAO.class );
		HashMap<String, Object> parameter	= util.getRequestValues(request);
		AdminTimelineModel		model		= null;
		
		try {
			
			model = dao.getModifyTimelineData(parameter);
			
			int		uid		= model.getUid();
			String	event 	= model.getEvent();
			String 	date	= model.getYear() + "-" + model.getMonth() + "-" + model.getDay();
			String 	remarks	= model.getRemarks();
			
			Date new_date = new SimpleDateFormat("yyyy-MM-dd").parse( date );
			date = new SimpleDateFormat("yyyy-MM-dd").format( new_date );
			
			json.addProperty( "uid"		, uid );
			json.addProperty( "event"	, event );
			json.addProperty( "date"	, date);
			json.addProperty( "remarks"	, remarks);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}

}
