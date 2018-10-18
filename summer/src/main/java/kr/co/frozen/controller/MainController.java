package kr.co.frozen.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

import kr.co.frozen.dao.MainDAO;
import kr.co.frozen.util.VisitorUtil;

@Controller
public class MainController {
	
	private Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@Autowired
	private SqlSession sqlSession;
	
	@Resource
	private VisitorUtil visitor;
	
	@Value("#{storage[storage]}")
	private String dev_storage;
	
	@RequestMapping(value="/visitor")
	public void getVisitorInfo( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		HashMap<String, Object> result 			= new HashMap<String, Object>();
		HashMap<String, Object> parameter		= new HashMap<String, Object>();
		PrintWriter 			out 			= response.getWriter();
		JsonObject 				json 			= new JsonObject();
		MainDAO					dao				= sqlSession.getMapper( MainDAO.class );
		SimpleDateFormat		sdf				= new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String					client_ip		= "Unknown";
		String					client_browser 	= "Unknown";
		String					client_time		= "";
		String					client_os		= request.getParameter("type");
		
		result = visitor.getClientAddress(request);
		
		client_time = sdf.format(new Date());
		
		if( result.get("x-forwarded-for") != null ) {
			client_ip = result.get("x-forwarded-for").toString();
		}
		
		if( result.get("user-agent") != null ) {
			client_browser = result.get("user-agent").toString();
		}
		
		parameter.put( "ip"		, client_ip );
		parameter.put( "browser", client_browser );
		parameter.put( "time"	, client_time );
		parameter.put( "os"		, client_os );
		
		// ClientInfo insert
		dao.insertClientInfo( parameter );
		
		json.addProperty( "state", "success" );
		
		out.print( json );
		
		return;
	}
	
	/******************** page move method **********************/
	
	@RequestMapping(value="/")
	public String testMethod( HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		
		model.addAttribute( "title", "Coming Soon" );
		
		return "comingsoon";
	}
	
	@RequestMapping(value="/portfolio")
	public String goPortfolio( HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session ) {
		
		return "index";
	}
	
	/******************** page move method **********************/
}
