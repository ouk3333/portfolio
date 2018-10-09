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

import kr.co.frozen.util.AdminIntroUtil;
import kr.co.frozen.util.AdminLicenseUtil;
import kr.co.frozen.util.AdminOrderingUtil;
import kr.co.frozen.util.AdminTimelineUtil;
import kr.co.frozen.util.advancedUtil;

@RequestMapping(value="/admin/profile")
@Controller
public class AdminProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger( AdminProfileController.class );
	
	@Resource(name="adminOrdering")
	public AdminOrderingUtil ordering;
	
	@Resource(name="adminTimeline")
	public AdminTimelineUtil timeline;
	
	@Resource(name="adminIntro")
	public AdminIntroUtil intro;
	
	@Resource(name="adminLicense")
	public AdminLicenseUtil license;
	
	@RequestMapping(value="/getTimelineData")
	public void getTimelineData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.getTimelineData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/addTimelineData")
	public void addTimelineData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.addTimelineData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getTimelineStorageData")
	public void getTimelineStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.getTimelineStorageData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/deleteTimelineStorageData")
	public void deleteTimelineStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.deleteTimelineStorageData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/modifyTimelineStorageData")
	public void modifyTimelineStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.modifyTimelineStorageData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/addTimelineStorageData")
	public void addTimelineStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.addTimelineStorageData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/removeTimelineData")
	public void removeTimelineData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.removeTimelineData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getModifyTimelineData")
	public void getModifyTimelineData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= timeline.getModifyTimelineData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setTimelineDataOrdering")
	public void setTimelineDataOrdering( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= ordering.setDataOrdering(request, "tbl_timeline");
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setAutoOrderingTimeline")
	public void setAutoOrderingTimeline( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= ordering.setDataSorting(request, "tbl_timeline");
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getIntroData")
	public void getIntroData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= intro.getIntroData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setIntroData")
	public void setIntroData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= intro.setIntroData(request);
	
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getLicenseData")
	public void getLicenseData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.getLicenseData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/addLicenseData")
	public void addLicenseData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.addLicenseData(request);
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getLicenseStorageData")
	public void getLicenseStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.getLicenseStorageData();
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/addLicenseStorageData")
	public void addlicenseStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.addlicenseStorageData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/removeLicenseData")
	public void removeLicenseData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.removeLicenseData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/deleteLicenseStorageData")
	public void deleteLicenseStorageData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.deleteLicenseStorageData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/getLicenseStorageModifyData")
	public void getLicenseStorageModifyData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.getLicenseStorageModifyData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setModifyLicenseData")
	public void setModifyLicenseData( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= license.setModifyLicenseData( request );
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setLicenseDataOrdering")
	public void setLicenseDataOrdering( HttpServletRequest request, HttpServletResponse response ) throws IOException {

		PrintWriter		out		= response.getWriter();
		JsonObject		json	= ordering.setDataOrdering(request, "tbl_license");
		
		out.print( json );
		
		return;
	}
	
	@RequestMapping(value="/setAutoOrderingLicense")
	public void setAutoOrderingLicense( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		PrintWriter		out		= response.getWriter();
		JsonObject		json	= ordering.setDataSorting(request, "tbl_license");
		
		out.print( json );
		
		return;
	}
	
}
