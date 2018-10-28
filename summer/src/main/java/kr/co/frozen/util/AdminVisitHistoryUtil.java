package kr.co.frozen.util;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminVisitHistoryDAO;
import kr.co.frozen.model.AdminVisitHistoryModel;

@Component("adminVisitHistory")
public class AdminVisitHistoryUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminVisitHistoryUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getIpStatsData() {
		
		JsonObject							json			= new JsonObject();
		JsonArray							array			= new JsonArray();
		JsonArray							result			= new JsonArray();
		ArrayList<AdminVisitHistoryModel>	visitHistory 	= null;
		AdminVisitHistoryDAO				dao				= sqlSession.getMapper( AdminVisitHistoryDAO.class );
		
		try {
			
			visitHistory = dao.getIpStatsData();
			
			result.add( "Task" );
			result.add( "Hours per Day" );
			array.add( result );
			
			for( AdminVisitHistoryModel model: visitHistory ) {
				result = new JsonArray();
				
				result.add( model.getName() );
				result.add( model.getCnt() );
				
				array.add( result );
			}
			
			json.add( "stats", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getOsStatsData() {
		
		JsonObject							json			= new JsonObject();
		JsonArray							array			= new JsonArray();
		JsonArray							result			= new JsonArray();
		ArrayList<AdminVisitHistoryModel>	visitHistory 	= null;
		AdminVisitHistoryDAO				dao				= sqlSession.getMapper( AdminVisitHistoryDAO.class );
		
		try {
			
			visitHistory = dao.getOsStatsData();
			
			result.add( "Task" );
			result.add( "Hours per Day" );
			array.add( result );
			
			for( AdminVisitHistoryModel model: visitHistory ) {
				result = new JsonArray();
				
				result.add( model.getName() );
				result.add( model.getCnt() );
				
				array.add( result );
			}
			
			json.add( "stats", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getBrowserStatsData() {
		
		JsonObject							json			= new JsonObject();
		JsonArray							array			= new JsonArray();
		JsonArray							result			= new JsonArray();
		ArrayList<AdminVisitHistoryModel>	visitHistory 	= null;
		AdminVisitHistoryDAO				dao				= sqlSession.getMapper( AdminVisitHistoryDAO.class );
		
		try {
			
			visitHistory = dao.getBrowserStatsData();
			
			result.add( "Task" );
			result.add( "Hours per Day" );
			array.add( result );
			
			for( AdminVisitHistoryModel model: visitHistory ) {
				result = new JsonArray();
				
				result.add( model.getName() );
				result.add( model.getCnt() );
				
				array.add( result );
			}
			
			json.add( "stats", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getVisitorIpAddress() {
		
		JsonObject							json			= new JsonObject();
		JsonObject							result			= new JsonObject();
		JsonArray							array			= new JsonArray();
		ArrayList<AdminVisitHistoryModel>	visitHistory 	= null;
		AdminVisitHistoryDAO				dao				= sqlSession.getMapper( AdminVisitHistoryDAO.class );
		
		try {
			
			visitHistory = dao.getVisitorIpAddress();
			
			for( AdminVisitHistoryModel model: visitHistory ) {
				result = new JsonObject();
				
				result.addProperty( "name", model.getName() );
				
				array.add( result );
			}
			
			json.add( "list", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
}
