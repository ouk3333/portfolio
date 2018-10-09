package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminIntroModel;
import kr.co.frozen.model.AdminTimelineModel;

public interface AdminTimelineDAO {

	public ArrayList<AdminTimelineModel>	getTimelineData();
	public void							createTimelineData( HashMap<String, Object> parameter );
	public ArrayList<AdminTimelineModel> getTimelineStorageData();
	
	public void							deleteTimelineStorageData( HashMap<String, Object> parameter );
	public void							modifyTimelineStorageData( HashMap<String, Object> parameter );
	public AdminTimelineModel			getTimelineStorageData_single( HashMap<String, Object> parameter );
	public int							getMaxTimelineStorageOrderNo();
	public void							addTimelineStorageData( HashMap<String, Object> parameter );
	public void							afterAddTimelineStorageData( HashMap<String, Object> parameter );
	
	public void							removeTimelineData( HashMap<String, Object> parameter );
	public void							afterRemoveTimelineData( HashMap<String, Object> parameter );
	
	public AdminTimelineModel			getModifyTimelineData( HashMap<String, Object> parameter );
	
}
