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

import kr.co.frozen.dao.AdminProgramDAO;
import kr.co.frozen.model.AdminProgramModel;

@Component("adminProgram")
public class AdminProgramUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminProgramUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getProgramData(  ) {
		
		JsonObject						json 	= new JsonObject();
		JsonArray						array	= new JsonArray();
		JsonObject						result	= new JsonObject();
		AdminProgramDAO					dao		= sqlSession.getMapper( AdminProgramDAO.class );
		ArrayList<AdminProgramModel> 	model 	= null;
		
		try {
			
			model = dao.getProgramData();
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					result = new JsonObject();
					
					result.addProperty( "uid"		, model.get(i).getUid() );
					result.addProperty( "title"		, model.get(i).getTitle() );
					result.addProperty( "language"	, model.get(i).getLanguage() );
					result.addProperty( "skill"		, model.get(i).getSkill() );
					result.addProperty( "start_date", model.get(i).getStart_date() );
					result.addProperty( "end_date"	, model.get(i).getEnd_date() );
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
	public JsonObject addProgramData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminProgramDAO			dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object> parameter	= util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap( parameter.get("addData").toString() ) );
		
		try {
			
			dao.addProgramData(parameter); // 새로운 프로그램 데이터 삽입
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getProgramStorageData(  ) {
		
		JsonObject						json	= new JsonObject();
		JsonArray						array	= new JsonArray();
		JsonObject						result	= new JsonObject();
		AdminProgramDAO					dao		= sqlSession.getMapper( AdminProgramDAO.class );
		ArrayList<AdminProgramModel>	model	= null;
		
		try {
			
			model = dao.getProgramStorageData();
		
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					
					result = new JsonObject();
					
					result.addProperty( "uid", model.get(i).getUid() );
					result.addProperty( "title", model.get(i).getTitle() );
					result.addProperty( "language", model.get(i).getLanguage() );
					result.addProperty( "skill", model.get(i).getSkill() );
					result.addProperty( "start_date", model.get(i).getStart_date() );
					result.addProperty( "end_date", model.get(i).getEnd_date() );
					
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
}
