package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminIntroDAO;
import kr.co.frozen.model.AdminIntroModel;

@Component("adminIntro")
public class AdminIntroUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminIntroUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	public advancedUtil util = new advancedUtil();
	
	public JsonObject getIntroData() {
		
		JsonObject		json 	= new JsonObject();
		AdminIntroDAO	dao		= sqlSession.getMapper( AdminIntroDAO.class );
		AdminIntroModel	model	= null;
		
		try {
			
			model = dao.getIntroData();
			
			if( model != null ) {
				json.addProperty( "intro"		, model.getIntro() );
				json.addProperty( "write_time"	, model.getWrite_time() );
				json.addProperty( "marker"		, model.getMarker() );
				json.addProperty( "preserve"	, model.getPreserve() == 1 ? true : false );
				json.addProperty( "delay"		, model.getDelay() );
			}
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject setIntroData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminIntroDAO			dao			= sqlSession.getMapper( AdminIntroDAO.class );
		HashMap<String, Object> parameter 	= util.getRequestValues(request);
		
		parameter.put( "write_time", util.getCurrentTime() );
		
		try {
			dao.setIntroData( parameter );
			
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
