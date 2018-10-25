package kr.co.frozen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.frozen.constance.ConstClass;
import kr.co.frozen.dao.AdminDAO;
import kr.co.frozen.model.ConfigModel;
import kr.co.frozen.model.MemberModel;

public class PortfolioInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger( PortfolioInterceptor.class );
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//logger.debug( "portfolio intercepter" );
		
		HttpSession session = request.getSession();
		AdminDAO	dao		= sqlSession.getMapper( AdminDAO.class );
		
		ConfigModel	config	= dao.getConfiguration();
		
		session.setAttribute( "main_color"		, config.getMain_color() );
		session.setAttribute( "point_color"		, config.getPoint_color() );
		session.setAttribute( "title_color"		, config.getTitle_color() );
		session.setAttribute( "page_title"		, config.getPage_title() );
		session.setAttribute( "sidemenu_image"	, config.getSidemenu_image() );
		
		return true;
		
	}
	
}
