package com.forgetpassword;

import java.sql.Connection;
import java.util.HashMap;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;

public class ForgetPwdBusinessDelegater 
{
	public static HashMap chkEmail(  String email,String clientID,HttpSession session)
	{
		HashMap chkEmail=ForgetPwdDao.chkEmail(  email,clientID,session);
		return chkEmail;
	}
	public static HashMap getDynamicDetails(String clientId){
	return ForgetPwdDao.getDynamicDetails(clientId);
	}
	
}
