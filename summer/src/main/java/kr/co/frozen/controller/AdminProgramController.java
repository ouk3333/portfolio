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

import kr.co.frozen.util.AdminProgramUtil;

@RequestMapping(value="/admin/program")
@Controller
public class AdminProgramController {

	private static final Logger logger = LoggerFactory.getLogger( AdminProgramController.class );
	
	@Resource(name="adminProgram")
	public AdminProgramUtil	program;
	
	@RequestMapping(value="/getProgramData")
	public void getProgramData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.getProgramData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/addProgramData")
	public void addProgramData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.addProgramData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getProgramStorageData")
	public void getProgramStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.getProgramStorageData();
		
		out.print( json );
		
		return;
	}
	
}
