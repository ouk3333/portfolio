package kr.co.frozen.util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminDAO;
import kr.co.frozen.model.ConfigModel;
import kr.co.frozen.model.MemberModel;

@Component("admin")
public class AdminUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminUtil.class );
	
	@Autowired
	private SqlSession sqlsession;
	
	@Value("#{storage[storage]}")
	private String dev_storage;
	
	advancedUtil advanced = new advancedUtil();
	
	// 어드민 아이디 체크
	public boolean getAdminID( String userID ) {
		
		AdminDAO 				dao 		= sqlsession.getMapper( AdminDAO.class );
		HashMap<String, Object> parameter 	= new HashMap<String, Object>();
		MemberModel				member		= null;
		
		parameter.put( "userID", userID );
		
		member = dao.getAdminID(parameter);
		
		if( member != null ) {
			
			if( member.getId().equals(userID) == false ) {
				return false;
			}
			
			return true;
			
		} else {
			return false;
		}
	}
	
	// 관리자 체크
	public int checkAdmin( String userID, String password, HttpServletRequest request ) {
		
		AdminDAO				dao 		= sqlsession.getMapper( AdminDAO.class );
		HashMap<String, Object> parameter 	= new HashMap<String, Object>();
		MemberModel				member		= null;
		HttpSession				session		= null;
		
		parameter.put( "userID"		, userID );
		
		member = dao.checkAdmin( parameter );
		
		if( member == null ) {
			return 0; // 존재하지 않는 관리자
		}
		
		if( member.getPassword().equals(password) == false ) {
			return -1; // 비밀번호 불일치
		}
		
		if( member.getType().equals("A") == false ) {
			return 0; // 존재하지 않는 관리자
		}
		session = request.getSession();
		
		session.setAttribute( "userInfo", member );
		
		return 1;
	}
	
	// 전력량 데이터 가져오기
	public HashMap<String, Object> getPowerGenerationData() throws IOException, JDOMException {
		
		HashMap<String, Object> result 		= new HashMap<String, Object>();
		HashMap<String, Object> parameter	= new HashMap<String, Object>();
		AdminDAO				dao			= sqlsession.getMapper( AdminDAO.class );
		
		parameter.put("api_key", "powerGeneration");
		
		SAXBuilder jdom = new SAXBuilder();
		String serviceURL = "https://openapi.kpx.or.kr/openapi/sukub5mMaxDatetime/getSukub5mMaxDatetime?ServiceKey=";
		String serviceKey = dao.getApiKey(parameter);
		URL url = new URL(serviceURL + serviceKey);
		
		Document document = jdom.build( url.openConnection().getInputStream() );
		Element root = document.getRootElement();
		Element header = root.getChild("header");
		
		List<Element> header_list = header.getChildren();
		
		for( int i = 0; i < header_list.size(); i++ ) {
			if( header_list.get(i).getName().equals("resultCode") ) {
				Element resultCode = (Element) header_list.get(i);
				
				if( resultCode.getValue().equals("00") == false ) {
					result.put( "state", "error" );
					
					return result;
				}
			}
		}
		
		Element body = root.getChild("body");
		Element body_outer = body.getChild("items");
		Element body_inner = body_outer.getChild("item");
		
		List<Element> body_list = body_inner.getChildren();
		
		for( int i = 0; i < body_list.size(); i++ ) {
			Element node = body_list.get(i);
			
			result.put( node.getName(), node.getValue() );
		}
		
		result.put( "state", "success" );
		
		return result;
	}
	
	// 페이지 기본 설정 데이터 가져오기
	public JsonObject getConfiguration() {
		JsonObject 	json = new JsonObject();
		AdminDAO 	dao	 = sqlsession.getMapper( AdminDAO.class );
		
		try {
			
			ConfigModel model = dao.getConfiguration();
			
			if( model == null ) {
				model = new ConfigModel();
			}
			
			json.addProperty( "page_title"		, advanced.nullCheck(model.getPage_title()).toString() );
			json.addProperty( "main_color"		, advanced.nullCheck(model.getMain_color()).toString() );
			json.addProperty( "point_color"		, advanced.nullCheck(model.getPoint_color()).toString() );
			json.addProperty( "title_color"		, advanced.nullCheck(model.getTitle_color()).toString() );
			json.addProperty( "sidemenu_image"	, advanced.nullCheck(model.getSidemenu_image()).toString() );
			json.addProperty( "program_layout"	, model.getProgram_layout() );
			json.addProperty( "state"			, "success" );
			
		} catch (Exception e) {
			
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			
		}
		
		return json;
	}
	
	// 페이지 기본 설정 저장하기
	@Transactional
	public JsonObject setConfiguration( HttpServletRequest request ) {
		JsonObject	json 	= new JsonObject();
		AdminDAO	dao		= sqlsession.getMapper( AdminDAO.class );
		
		try {
			
			HashMap<String, Object> parameter = advanced.getRequestValues(request);
			
			dao.setConfiguration( parameter ); // 설정값 insert
			
			json.addProperty( "state", "success" );
			
		} catch (Exception e) {
			
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
		}
		
		return json;
	}
}
