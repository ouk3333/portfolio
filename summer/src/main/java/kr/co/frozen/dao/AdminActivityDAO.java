package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminActivityModel;

public interface AdminActivityDAO {

	public ArrayList<AdminActivityModel>		getActivityData();
	public ArrayList<AdminActivityModel>		getActivityStorageData();
	public void									insertActivityStorageData( HashMap<String, Object> parameter );
	public void									deleteActivityStorageData( HashMap<String, Object> parameter );
	public AdminActivityModel					getActivityStorageData_single( HashMap<String, Object> parameter );
	public void									addActivityStorageDataModal( HashMap<String, Object> parameter );
	public void									afterAddActivityStorageDataModal( HashMap<String, Object> parameter );
	public void									removeActivityData( HashMap<String, Object> parameter );
	public void									afterRemoveActivityData( HashMap<String, Object> parameter );
	
}
