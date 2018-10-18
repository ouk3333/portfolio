package kr.co.frozen.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.frozen.model.AdminContactModel;
import kr.co.frozen.model.ConfigModel;
import kr.co.frozen.model.MainModel;
import kr.co.frozen.model.MemberModel;

public interface AdminContactDAO {

	public ArrayList<AdminContactModel>			getContactData();
	public void									setContactData( HashMap<String, Object> parameter );
	
}
