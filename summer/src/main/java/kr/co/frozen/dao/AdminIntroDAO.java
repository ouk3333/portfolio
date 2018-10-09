package kr.co.frozen.dao;

import java.util.HashMap;

import kr.co.frozen.model.AdminIntroModel;

public interface AdminIntroDAO {

	public AdminIntroModel				getIntroData();
	public void							setIntroData( HashMap<String, Object> parameter );
}
