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

import kr.co.frozen.util.AdminVisitHistoryUtil;

@RequestMapping(value="/admin/visitHistory")
@Controller
public class AdminVisitHistoryController {

	private static final Logger logger = LoggerFactory.getLogger( AdminVisitHistoryController.class );
	
	@Resource(name="adminVisitHistory")
	public AdminVisitHistoryUtil visitHistory;
	
	@RequestMapping(value="/getIpStatsData")
	public void getIpStatsData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= visitHistory.getIpStatsData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getOsStatsData")
	public void getOsStatsData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= visitHistory.getOsStatsData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getBrowserStatsData")
	public void getBrowserStatsData( HttpServletResponse request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= visitHistory.getBrowserStatsData();
		
		out.print( json );
	}
	
	@RequestMapping(value="/getVisitorIpAddress")
	public void getVisitorIpAddress( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= visitHistory.getVisitorIpAddress();
		
		out.print( json );
	}
}
