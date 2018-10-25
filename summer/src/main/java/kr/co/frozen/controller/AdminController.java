package kr.co.frozen.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

import kr.co.frozen.model.ConfigModel;
import kr.co.frozen.model.MemberModel;
import kr.co.frozen.util.AdminUtil;

@RequestMapping(value="/admin")
@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger( AdminController.class );
	
	@Resource
	public AdminUtil util;
	
	@RequestMapping(value="/adminIdCheck")
	public void adminIdCheck( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter				out 		= response.getWriter();
		JsonObject 				json 		= new JsonObject();
		String					userID		= URLDecoder.decode( request.getParameter("userID"), "UTF-8" );
		boolean					id_exist	= false;
		
		try {
			
			json.addProperty( "state", "success" );
			json.addProperty( "exist", "n" );
			
			if( userID != null ) {
				
				id_exist = util.getAdminID( userID );
				
				if( id_exist == true ) {
					json.addProperty( "exist", "y" );
				}
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			out.print( json );
		}
		
		return;
	}
	
	@RequestMapping(value="/adminLogin")
	public void adminLogin( HttpServletResponse response, HttpServletRequest request ) throws IOException {
		
		PrintWriter				out 		= response.getWriter();
		JsonObject 				json 		= new JsonObject();
		String					userID		= URLDecoder.decode( request.getParameter("userID"), "UTF-8" );
		String					password	= request.getParameter("password");
		int						id_exist	= 0;
		
		id_exist = util.checkAdmin(userID, password, request);
		
		json.addProperty( "state", "success" );
		
		if( id_exist == 0 ) {
			json.addProperty( "admin", "not exist" );
		} else if( id_exist == -1 ) {
			json.addProperty( "admin", "password" );
		} else {
			json.addProperty( "admin", "exist" );
		}
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/powerGeneration/getData")
	public void getPowerGenrationData( HttpServletResponse response, HttpServletRequest request ) throws IOException, JDOMException, ParseException {
		PrintWriter 			out 	= response.getWriter();
		JsonObject 				json 	= new JsonObject();
		HashMap<String, Object> result 	= new HashMap<String, Object>();
		
		result = util.getPowerGenerationData();

		json.addProperty( "state"			, result.get("state").toString() );
		
		if( result.get("state").equals("success") ) {
			json.addProperty( "baseDatetime"	, result.get("baseDatetime").toString() );
			json.addProperty( "suppAbility"		, result.get("suppAbility").toString() );
			json.addProperty( "currPwrTot"		, result.get("currPwrTot").toString() );
			json.addProperty( "forecastLoad"	, result.get("forecastLoad").toString() );
			
			json.addProperty( "suppReservePwr"	, result.get("suppReservePwr").toString() );
			json.addProperty( "suppReserveRate"	, result.get("suppReserveRate").toString() );
			json.addProperty( "operReservePwr"	, result.get("operReservePwr").toString() );
			json.addProperty( "operReserveRate"	, result.get("operReserveRate").toString() );
		}
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/config/getConfig")
	public void getConfiguration( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		PrintWriter			out 	= response.getWriter();
		JsonObject			json 	= util.getConfiguration();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/config/setConfig")
	public void setConfiguration( HttpServletResponse response, HttpServletRequest request ) throws IOException {
		PrintWriter				out			= response.getWriter();
		JsonObject				json 		= util.setConfiguration(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getIconList")
	public void getIconList( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter				out			= response.getWriter();
		JsonObject				json		= util.getIconList();
		
		out.print( json );
		
		return;
	}

	/******************** page move method **********************/
	@RequestMapping(value="/login")
	public String goAdminLoginPage( HttpServletRequest request, HttpServletResponse response,
			Model model ) {
		
		return "admin/login";
	}
	
	@RequestMapping(value="/adminMain")
	public String goAdminMain( HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model ) {
		
		MemberModel userInfo = (MemberModel) session.getAttribute("userInfo");
		
		model.addAttribute( "userID", userInfo.getId() );
		
		return "admin/adminMain";
	}
	
	@RequestMapping(value="/adminLogout")
	public String goLogout( HttpServletRequest request, HttpServletResponse response, HttpSession session ) {
		
		session.invalidate();
		
		return "redirect:/portfolio";
	}
	
	@RequestMapping(value="/visitHistory")
	public String goVisitHistory( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/visitHistory";
	}
	
	@RequestMapping(value="/powerGeneration")
	public String goPowerGeneration( HttpServletRequest request, HttpServletResponse response, Model model ){
		
		return "admin/powerGeneration";
	}
	
	@RequestMapping(value="/pageSetting")
	public String goPageSetting( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/pageSetting";
	}
	
	@RequestMapping(value="/profile")
	public String goProfile( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/profile";
	}
	
	@RequestMapping(value="/program")
	public String goProgram( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/program";
	}
	
	@RequestMapping(value="/skill")
	public String goSkill( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/skill";
	}
	
	@RequestMapping(value="/activity")
	public String goExperience( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/activity";
	}
	
	@RequestMapping(value="/contact")
	public String goContact( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/contact";
	}
	
	@RequestMapping(value="/openSource")
	public String goOpenSource( HttpServletRequest request, HttpServletResponse response, Model model ) {
		
		return "admin/openSource";
	}
	/******************** page move method **********************/
}
