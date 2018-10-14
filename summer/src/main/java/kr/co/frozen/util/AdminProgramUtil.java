package kr.co.frozen.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.frozen.dao.AdminProgramDAO;
import kr.co.frozen.model.AdminProgramImageModel;
import kr.co.frozen.model.AdminProgramModel;

@Component("adminProgram")
public class AdminProgramUtil {

	private static final Logger logger = LoggerFactory.getLogger( AdminProgramUtil.class );
	
	@Autowired
	private SqlSession sqlSession;
	
	@Value("#{storage[storage]}")
	private String dev_storage;
	
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
					result.addProperty( "image_cnt"	, model.get(i).getImage_cnt() );
					
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
	
	@Transactional
	public JsonObject deleteProgramStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminProgramDAO			dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>	parameter	= util.getRequestValues(request);
		
		try {
			
			dao.deleteProgramStorageData(parameter);
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		
		return json;
	}
	
	public JsonObject getModifyProgramStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {		
		
		JsonObject				json		= new JsonObject();
		AdminProgramDAO			dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>	parameter	= util.getRequestValues(request);
		AdminProgramModel		model		= null;
		
		try {
			
			model = dao.getModifyProgramStorageData(parameter);
			
			if( model != null ) {
				
				json.addProperty( "uid"			, model.getUid() );
				json.addProperty( "title"		, model.getTitle() );
				json.addProperty( "language"	, model.getLanguage() );
				json.addProperty( "skill"		, model.getSkill() );
				json.addProperty( "start_date"	, model.getStart_date() );
				json.addProperty( "end_date"	, model.getEnd_date() );
				
			}
			
			json.addProperty( "state", "success" );
		} catch (Exception e) {
			json.addProperty( "state", "error" );
			json.addProperty( "error", e.getMessage() );
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Transactional
	public JsonObject modifyProgramStorageData( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminProgramDAO			dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>	parameter	= util.getRequestValues(request);
		
		parameter.putAll( util.getJsonToHashMap(parameter.get("modify").toString()) );
		
		try {
			
			dao.modifyProgramStorageData(parameter);
			
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
	public JsonObject insertProgramStoragedata( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		JsonObject				json		= new JsonObject();
		AdminProgramDAO			dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>	parameter	= util.getRequestValues(request);
		AdminProgramModel		model		= null;
		
		try {
			
			int order_no = dao.getMaxProgramStorageOrderNo();
			
			model = dao.getModifyProgramStorageData(parameter);

			parameter.put( "uid"		, model.getUid() );
			parameter.put( "title"		, model.getTitle() );
			parameter.put( "language"	, model.getLanguage() );
			parameter.put( "start_date"	, model.getStart_date() );
			parameter.put( "end_date"	, model.getEnd_date() );
			parameter.put( "skill"		, model.getSkill() );
			parameter.put( "order_no"	, order_no );
			
			dao.insertProgramStoragedata(parameter);
			dao.afterInsertProgramStoragedata(parameter);
		
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
	public JsonObject removeProgramData( HttpServletRequest request ) throws UnsupportedEncodingException {
	
		JsonObject				json		= new JsonObject();
		AdminProgramDAO			dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>	parameter	= util.getRequestValues(request);
		
		try {
			
			dao.removeProgramData(parameter);
			dao.afterRemoveProgramData(parameter);
			
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
	public void programImageFileUpload( HttpServletRequest request, MultipartFile file ) throws IOException {
		
		AdminProgramDAO			dao				= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>	parameter		= new HashMap<String, Object>();		
		String 					org_name		= file.getOriginalFilename();
		String					convert_name	= util.getConvertFileName();
		String					upload_time		= util.getCurrentTime();
		String					fid 			= request.getParameter("uid");
		String					ext 			= org_name.substring(org_name.lastIndexOf("."), org_name.length());
		String					path 			= dev_storage + "/" + convert_name + ext;
		BufferedOutputStream	bos				= new BufferedOutputStream(new FileOutputStream(new File(path)));
		
		try {
			
			FileCopyUtils.copy(file.getInputStream(), bos);
			
			parameter.put( "fid"			, fid );
			parameter.put( "org_name"		, org_name );
			parameter.put( "convert_name"	, convert_name );
			parameter.put( "ext"			, ext );
			parameter.put( "path"			, path );
			parameter.put( "remarks"		, "" );
			parameter.put( "upload_time"	, upload_time );
			
			dao.programImageFileUpload(parameter);
			
		} catch (Exception e) {
			new File(path).delete();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		} finally {
			bos.close();
		}
		
		return;
	}
	
	public JsonObject getPreviewProgramImage( HttpServletRequest request ) throws UnsupportedEncodingException {
		
		AdminProgramDAO						dao			= sqlSession.getMapper( AdminProgramDAO.class );
		HashMap<String, Object>				parameter	= util.getRequestValues(request);
		ArrayList<AdminProgramImageModel>	model		= null;
		JsonObject							json		= new JsonObject();
		JsonArray							array		= new JsonArray();
		JsonObject							result		= new JsonObject();
		
		try {
			
			model = dao.getPreviewProgramImage(parameter);
			
			if( model.isEmpty() == false ) {
				
				for( int i = 0; i < model.size(); i++ ) {
					
					result = new JsonObject();
					
					result.addProperty( "uid"			, model.get(i).getUid() );
					result.addProperty( "fid"			, model.get(i).getFid() );
					result.addProperty( "org_name"		, model.get(i).getOrg_name() );
					result.addProperty( "convert_name"	, model.get(i).getConvert_name() );
					result.addProperty( "ext"			, model.get(i).getExt() );
					result.addProperty( "path"			, model.get(i).getPath() );
					result.addProperty( "remarks"		, model.get(i).getRemarks() );
					
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
	
	public void getPreview( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		BufferedOutputStream		out			= null;
		InputStream					is			= null;
		String						ext			= request.getParameter("ext");
		String						fileName	= request.getParameter("f");
		String						path		= dev_storage + "/" + fileName + ext;
		
		if( ext.equals(".jpg") ) {
			ext = ".jpeg";
		}
		
		try {
			response.setContentType("image/" + ext);
			response.setHeader("Content-Disposition", "inline;filename=" + fileName);
			
			File file = new File(path);
			
			if(file.exists()){
				is = new FileInputStream(file);
				out = new BufferedOutputStream(response.getOutputStream());
				int len;
				byte[] buf = new byte[1024];
				while ((len = is.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null){ out.flush(); }
			if(out != null){ out.close(); }
			if(is != null){ is.close(); }
		}
		
		return;
	}
	
}
