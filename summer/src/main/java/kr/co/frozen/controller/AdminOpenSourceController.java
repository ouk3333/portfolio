package kr.co.frozen.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

import kr.co.frozen.util.AdminOpenSourceUtil;

@RequestMapping(value="/admin/openSource")
@Controller
public class AdminOpenSourceController {

	private static final Logger logger = LoggerFactory.getLogger( AdminOpenSourceController.class );
	
	@Resource(name="adminOpenSource")
	public AdminOpenSourceUtil openSource;
	
	@RequestMapping(value="/getOpenSourceData")
	public void getOpenSourceData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= openSource.getOpenSourceData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/addOpenSourceData")
	public void addOpenSourceData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= openSource.addOpenSourceData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/deleteOpenSourceData")
	public void deleteOpenSourceData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= openSource.deleteOpenSourceData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/getModifyOpenSourceData")
	public void getModifyOpenSourceData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= openSource.getModifyOpenSourceData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/modifyOpenSourceData")
	public void modifyOpenSourceData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= openSource.modifyOpenSourceData(request);
		
		out.print( json );
	}
	
}