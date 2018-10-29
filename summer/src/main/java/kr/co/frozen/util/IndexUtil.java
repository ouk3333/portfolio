package kr.co.frozen.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.frozen.dao.IndexDAO;
import kr.co.frozen.model.IndexActivityModel;
import kr.co.frozen.model.IndexContactModel;
import kr.co.frozen.model.IndexIntroModel;
import kr.co.frozen.model.IndexLicenseModel;
import kr.co.frozen.model.IndexOpenSourceModel;
import kr.co.frozen.model.IndexProgramModel;
import kr.co.frozen.model.IndexSkillModel;
import kr.co.frozen.model.IndexTimelineModel;

@Component("indexUtil")
public class IndexUtil {

	private static final Logger logger = LoggerFactory.getLogger( IndexUtil.class );
	
	@Autowired
	private SqlSession sqlsession;
	
	@Value("#{storage[storage]}")
	private String dev_storage;
	
	@Value("#{naverMail[host]}")
	private String host;
	
	@Value("#{naverMail[port]}")
	private int port;
	
	@Value("#{naverMail[SMTPuser]}")
	private String SMTPuser;

	@Value("#{naverMail[user]}")
	private String user;
	
	@Value("#{naverMail[password]}")
	private String password;
	
	advancedUtil util = new advancedUtil();
	
	public JsonObject getProfileData() {
		
		JsonObject						json 		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexTimelineModel>	timeline 	= null;
		ArrayList<IndexLicenseModel>	license		= null;
		ArrayList<IndexIntroModel>		intro		= null;
		
		try {
			
			timeline 	= dao.getProfileData_timeline();
			license 	= dao.getProfileData_license();
			intro		= dao.getProfileData_intro();
			
			for( IndexTimelineModel model: timeline ) {
				result = new JsonObject();
				
				result.addProperty( "year"		, model.getYear() );
				result.addProperty( "month"		, model.getMonth() );
				result.addProperty( "day"		, model.getDay() );
				result.addProperty( "event"		, model.getEvent() );
				result.addProperty( "remarks"	, model.getRemarks() );
				
				array.add( result );
			}
			
			json.add( "timeline", array ); // timeline insert
			array = new JsonArray(); // array init
			
			for( IndexLicenseModel model: license ) {
				result = new JsonObject();
				
				result.addProperty( "name"		, model.getName() );
				result.addProperty( "type"		, model.getType() );
				result.addProperty( "remarks"	, model.getRemarks() );
				
				array.add( result );
			}
			
			json.add( "license", array ); // license insert
			array = new JsonArray(); // array init
			
			for( IndexIntroModel model: intro ) {
				result = new JsonObject();
				
				result.addProperty( "intro"		, model.getIntro() );
				result.addProperty( "marker"	, model.getMarker() );
				result.addProperty( "preserve"	, model.getPreserve() );
				result.addProperty( "delay"		, model.getDelay() );
				
				array.add( result );
			}
			
			json.add( "intro", array ); // intro insert
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getProgramData() {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexProgramModel>	program		= null;
		
		try {
			
			program = dao.getProgramData();
			
			for( IndexProgramModel model: program ) {
				result = new JsonObject();
				
				result.addProperty( "uid"			, model.getUid() );
				result.addProperty( "title"			, model.getTitle() );
				result.addProperty( "language"		, model.getLanguage() );
				result.addProperty( "convert_name"	, model.getConvert_name() );
				result.addProperty( "ext"			, model.getExt() );
				result.addProperty( "program_layout", model.getProgram_layout() );
				
				array.add( result );
			}
			
			json.add( "program", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getProgramSubData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexProgramModel>	program		= null;
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {
			
			program = dao.getProgramSubData(parameter);
			
			for( IndexProgramModel model: program ) {
				result = new JsonObject();
				
				result.addProperty( "title"			, model.getTitle() );
				result.addProperty( "language"		, model.getLanguage() );
				result.addProperty( "skill"			, model.getSkill() );
				result.addProperty( "start_date"	, model.getStart_date() );
				result.addProperty( "end_date"		, model.getEnd_date() );
				result.addProperty( "convert_name"	, model.getConvert_name() );
				result.addProperty( "ext"			, model.getExt() );
				result.addProperty( "remarks"		, model.getRemarks() );
				
				array.add( result );
			}
			
			json.add( "program", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getSkillData() {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexSkillModel>		skill		= null;
		
		try {
			
			skill = dao.getSkillData();
			
			for( IndexSkillModel model: skill ) {
				result = new JsonObject();
				
				result.addProperty( "name"				, model.getName() );
				result.addProperty( "ability"			, model.getAbility() );
				result.addProperty( "background_color"	, model.getBackground_color() );
				result.addProperty( "font_color"		, model.getFont_color() );
				
				array.add( result );
			}
			
			json.add( "skill", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getActivityData() {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexActivityModel>	activity	= null;
		
		try {
			
			activity = dao.getActivityData();
			
			for( IndexActivityModel model: activity ) {
				result = new JsonObject();
				
				result.addProperty( "shape"		, model.getShape() );
				result.addProperty( "year"		, model.getYear() );
				result.addProperty( "contents"	, model.getContents() );
				
				array.add( result );
			}
			
			json.add( "activity", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getContactData() {
		
		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexContactModel>	contact		= null;
		
		try {
			
			contact = dao.getContactData();
			
			for( IndexContactModel model: contact ) {
				result = new JsonObject();
				
				result.addProperty( "name"	, model.getName() );
				result.addProperty( "value"	, model.getValue() );
				
				array.add( result );
			}
			
			json.add( "contact", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject setContactData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject						json		= new JsonObject();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		HashMap<String, Object>			parameter	= util.getRequestValues(request);
		
		try {

			parameter.putAll( util.getJsonToHashMap(parameter.get("contact_data").toString()) );
			parameter.put( "reg_time", util.getCurrentTime() );
			
			dao.setContactData(parameter);
			
			String email = parameter.get("email").toString();
			String name = parameter.get("name").toString();
			String subject = parameter.get("subject").toString();
			String message = parameter.get("remarks").toString();
			
			util.sendEmail(host, port, SMTPuser, password, email, name, subject, message, user);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getOpenSourceData() {

		JsonObject						json		= new JsonObject();
		JsonObject						result		= new JsonObject();
		JsonArray						array		= new JsonArray();
		IndexDAO						dao			= sqlsession.getMapper( IndexDAO.class );
		ArrayList<IndexOpenSourceModel>	opensource	= null;
		
		try {
			
			opensource = dao.getOpenSourceData();
			
			for( IndexOpenSourceModel model: opensource ) {
				result = new JsonObject();
				
				result.addProperty( "name"	, model.getName() );
				result.addProperty( "type"	, model.getType() );
				result.addProperty( "url"	, model.getUrl() );
				
				array.add( result );
			}
			
			json.add( "opensource", array );
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
}
