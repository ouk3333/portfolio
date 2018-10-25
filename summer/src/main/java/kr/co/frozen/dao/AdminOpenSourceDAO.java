package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminOpenSourceModel;

public interface AdminOpenSourceDAO {
	
	public ArrayList<AdminOpenSourceModel>			getOpenSourceData();
	public void										addOpenSourceData( HashMap<String, Object> parameter );
	public void										deleteOpenSourceData( HashMap<String, Object> parameter );
	public AdminOpenSourceModel						getModifyOpenSourceData( HashMap<String, Object> parameter );
	public void										modifyOpenSourceData( HashMap<String, Object> parameter );
	
}
