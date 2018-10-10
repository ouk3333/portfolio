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

import kr.co.frozen.util.AdminOrderingUtil;
import kr.co.frozen.util.AdminProgramUtil;

@RequestMapping(value="/admin/program")
@Controller
public class AdminProgramController {

	private static final Logger logger = LoggerFactory.getLogger( AdminProgramController.class );
	
	@Resource(name="adminProgram")
	public AdminProgramUtil	program;
	
	@Resource(name="adminOrdering")
	public AdminOrderingUtil ordering;
	
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
	
	@RequestMapping(value="/deleteProgramStorageData")
	public void deleteProgramStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.deleteProgramStorageData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getModifyProgramStorageData")
	public void getModifyProgramStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.getModifyProgramStorageData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/modifyProgramStorageData")
	public void modifyProgramStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.modifyProgramStorageData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/insertProgramStoragedata")
	public void insertProgramStoragedata( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.insertProgramStoragedata(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/removeProgramData")
	public void removeProgramData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= program.removeProgramData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setAutoOrderingProgram")
	public void setAutoOrderingProgram( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= ordering.setDataSorting(request, "tbl_program");
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setProgramDataOrdering")
	public void setProgramDataOrdering( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= ordering.setDataOrdering(request, "tbl_program");
		
		out.print( json );
		
		return;
	}
	
}
