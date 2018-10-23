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

import kr.co.frozen.util.IndexUtil;

@RequestMapping(value="/portfolio")
@Controller
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger( IndexController.class );
	
	@Resource(name="indexUtil")
	private IndexUtil index;
	
	@RequestMapping(value="/getProfileData")
	public void getProfileData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.getProfileData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getProgramData")
	public void getProgramData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.getProgramData();
		
		out.print( json );		
	}
	
	@RequestMapping(value="/getProgramSubData")
	public void getProgramSubData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.getProgramSubData(request);
		
		out.print( json );
	}
	
	@RequestMapping(value="/getSkillData")
	public void getSkillData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.getSkillData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getActivityData")
	public void getActivityData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.getActivityData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getContactData")
	public void getContactData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.getContactData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/setContactData")
	public void setContactData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= index.setContactData(request);
		
		out.print( json );
	}
	
}
