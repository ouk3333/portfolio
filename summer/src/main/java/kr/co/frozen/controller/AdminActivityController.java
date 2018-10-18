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

import kr.co.frozen.util.AdminActivityUtil;
import kr.co.frozen.util.AdminOrderingUtil;

@RequestMapping(value="/admin/activity")
@Controller
public class AdminActivityController {

	private static final Logger logger = LoggerFactory.getLogger( AdminActivityController.class );
	
	@Resource(name="adminActivity")
	public AdminActivityUtil activity;
	
	@Resource(name="adminOrdering")
	public AdminOrderingUtil ordering;
	
	@RequestMapping(value="/getActivityData")
	public void getActivityData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= activity.getActivityData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getActivityStorageData")
	public void getActivityStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= activity.getActivityStorageData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/insertActivityStorageData")
	public void insertActivityStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= activity.insertActivityStorageData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/deleteActivityStorageData")
	public void deleteActivityStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= activity.deleteActivityStorageData(request);
		
		out.print( json );
		
		return;
	}

	@RequestMapping(value="/addActivityStorageDataModal")
	public void addActivityStorageDataModal( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= activity.addActivityStorageDataModal(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/removeActivityData")
	public void removeActivityData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= activity.removeActivityData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setAutoOrderingActivity")
	public void setAutoOrderingActivity( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= ordering.setDataSorting(request, "tbl_activity");
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setActivityDataOrdering")
	public void setActivityDataOrdering( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter			out		= response.getWriter();
		JsonObject			json	= ordering.setDataOrdering(request, "tbl_activity");
		
		out.print( json );
		
		return;
	}
	
}
