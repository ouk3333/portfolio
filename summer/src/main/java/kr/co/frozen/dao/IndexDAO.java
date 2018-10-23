package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.IndexActivityModel;
import kr.co.frozen.model.IndexContactModel;
import kr.co.frozen.model.IndexIntroModel;
import kr.co.frozen.model.IndexLicenseModel;
import kr.co.frozen.model.IndexProgramModel;
import kr.co.frozen.model.IndexSkillModel;
import kr.co.frozen.model.IndexTimelineModel;

public interface IndexDAO {

	public ArrayList<IndexTimelineModel>	getProfileData_timeline();
	public ArrayList<IndexLicenseModel>		getProfileData_license();
	public ArrayList<IndexIntroModel>		getProfileData_intro();
	
	public ArrayList<IndexProgramModel>		getProgramData();
	public ArrayList<IndexProgramModel>		getProgramSubData( HashMap<String, Object> parameter );
	
	public ArrayList<IndexSkillModel>		getSkillData();
	
	public ArrayList<IndexActivityModel>	getActivityData();
	
	public ArrayList<IndexContactModel>		getContactData();
	public void								setContactData( HashMap<String, Object> parameter );
	
}
