package kr.co.frozen.dao;

import java.util.ArrayList;

import kr.co.frozen.model.AdminVisitHistoryModel;

public interface AdminVisitHistoryDAO {

	public ArrayList<AdminVisitHistoryModel>	getIpStatsData();
	public ArrayList<AdminVisitHistoryModel>	getOsStatsData();
	public ArrayList<AdminVisitHistoryModel>	getBrowserStatsData();
	public ArrayList<AdminVisitHistoryModel>	getVisitorIpAddress();
	
}
