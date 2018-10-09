package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminOrderingDAO;

@Component("adminOrdering")
public class AdminOrderingUtil {

private static final Logger logger = LoggerFactory.getLogger( AdminTimelineUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	@Value("#{storage[storage]}")
	private String dev_storage;
	
	public advancedUtil util = new advancedUtil();
	
	@Transactional
	public JsonObject setDataOrdering( HttpServletRequest request, String table_name ) throws UnsupportedEncodingException {
		
		JsonObject				json 			= new JsonObject();
		AdminOrderingDAO		dao				= sqlSession.getMapper( AdminOrderingDAO.class );
		HashMap<String, Object> parameter		= util.getRequestValues(request);
		int						curr_order		= Integer.parseInt( parameter.get("order_no").toString() );
		int						change_order	= 0;

		parameter.put( "table_name", table_name );
		
		try {
			
			logger.debug( parameter.toString() );
			
			if( parameter.get("type").toString().equals("up") ) {
				
				change_order = curr_order - 1;
				
			} else {
				
				change_order = curr_order + 1;
				
			}
			/*
			 * 1. 바꿀 대상의 order_no 를 0으로 변경
			 * 2. 원본 대상을 변경하고자 하는 order_no로 변경
			 * 3. order_no가 0인 대상을 원본대상의 기존 order_no 로 변경
			 */
			
			// after_no = order_no 를 after_no로 바꿈
			// before_no = 기존의 order_no
			
			parameter.put( "after_no", 0 );
			parameter.put( "before_no", change_order );
			
			dao.setDataOrdering(parameter); // 1번 실행
			
			parameter.put( "after_no", change_order );
			parameter.put( "before_no", curr_order );
			
			dao.setDataOrdering(parameter); // 2번 실행
			
			parameter.put( "after_no", curr_order );
			parameter.put( "before_no", 0 );
			
			dao.setDataOrdering(parameter); // 3번 실행
			
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
	public JsonObject setDataSorting( HttpServletRequest request, String table_name ) throws UnsupportedEncodingException {
		
		JsonObject							json		= new JsonObject();
		AdminOrderingDAO					dao			= sqlSession.getMapper( AdminOrderingDAO.class );
		ArrayList<HashMap<String, Object>>	model		= null;
		HashMap<String, Object>				parameter 	= new HashMap<String, Object>();
		
		parameter.put( "table_name", table_name );
		
		try {
			
			model = dao.getSortingData(parameter);
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					
					int order_no = Integer.parseInt(model.get(i).get("order_no").toString());
					
					if( (i + 1) != order_no ) {
						parameter.put( "after_no", (i + 1) );
						parameter.put( "before_no", order_no );
						dao.setSortingData(parameter);
					}
					
				}
				
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