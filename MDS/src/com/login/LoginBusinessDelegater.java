package com.login;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


public class LoginBusinessDelegater 
{
	public static String chekLoginStatus(Connection con, String userId, String pass,HttpServletRequest request,String clientId,String userType)
	{
		String url=LoginDao.chekLoginStatus(con, userId, pass,request,clientId,userType);
		return url;
	}

	
}
