package kr.co.frozen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.frozen.constance.ConstClass;
import kr.co.frozen.model.MemberModel;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger( LoginInterceptor.class );

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//logger.debug( "preHandle access" );
		
		//String server_url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String compare_url = request.getRequestURL().toString();
		
		HttpSession session = request.getSession();
		MemberModel userInfo = (MemberModel)session.getAttribute("userInfo");
		//String userID = (String)session.getAttribute("userInfo");
		
		String compare_split[] = compare_url.split("/");
		
		compare_url = "";
		
		for( int i = 0; i < compare_split.length; i++ ) {
			compare_url += compare_split[i] + "/";
		}
		
		if( userInfo == null ) {
			
			for( int i = 0; i < ConstClass.access_url.length; i++ ) {

				if( compare_url.contains( ConstClass.access_url[i].toString()) ) {
					return true;
				}
			}
			
			logger.debug( "로그인 필요" );
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/admin/login");
			response.setHeader("login", "fail");
			
			return false;
		} else {
			response.setHeader("login", "success");
			return true;
		}
		
	}
	
}
