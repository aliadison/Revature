package Utility;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookiesHandler {

	public static boolean checkAuthentication(HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie authCookie : cookies) {
				if (authCookie.getName().equals("authenticated") && authCookie.getValue().equals("true")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void setCookieValue(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String value) throws IOException {
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				cookie.setValue(value);
				response.addCookie(cookie);
			}
		}
	}

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies)
			if (cookie.getName().equals(cookieName)) 
				return cookie.getValue();
		return "";
		
	}
}
