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

import kr.co.frozen.util.AdminVolunteerUtil;

@RequestMapping(value="/admin/volunteer")
@Controller
public class AdminVolunteerController {

	private static final Logger logger = LoggerFactory.getLogger( AdminVolunteerController.class );
	
	@Resource(name="adminVolunteer")
	public AdminVolunteerUtil volunteer;
	
	@RequestMapping(value="/getVolunteerData")
	public void getVolunteerData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= volunteer.getVolunteerData();
		
		out.print( json );		
	}
	
	@RequestMapping(value="/insertVolunteerData")
	public void insertVolunteerData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= volunteer.insertVolunteerData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/deleteVolunteerData")
	public void deleteVolunteerData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= volunteer.deleteVolunteerData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/getModifyVolunteerData")
	public void getModifyVolunteerData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= volunteer.getModifyVolunteerData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/updateVolunteerData")
	public void updateVolunteerData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= volunteer.updateVolunteerData(request);
		
		out.print( json );
	}
}
