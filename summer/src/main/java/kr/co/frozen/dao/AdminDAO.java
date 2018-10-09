package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.ConfigModel;
import kr.co.frozen.model.MainModel;
import kr.co.frozen.model.MemberModel;

public interface AdminDAO {

	public MemberModel	getAdminID( HashMap<String, Object> parameter );
	public MemberModel	checkAdmin( HashMap<String, Object> parameter );
	
	public ConfigModel	getConfiguration(  );
	public void			setConfiguration( HashMap<String, Object> parameter );
	
}
