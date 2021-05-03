package com.mobilesms;

import java.io.IOException;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;
import com.utility.UtilityP;
import java.net.*;
import java.io.*;





public class SmsToMobileDao {



	public static String sendMobileSmsAcl(String mobileNo,String message) {

		Statement stmt= null;
		ResultSet rs= null;
		String result="";
		//mobileNo="9891009321";

		try 
		{		

			message=message.replaceAll(" ", "%20");
			message=message.replaceAll("&", "and");


			String url ="http://push1.maccesssmspush.com/servlet/com.aclwireless.pushconnectivity.listeners.TextListener?userId=compark&pass=compark&appid=compark&subappid=compark&contenttype=1&selfid=true&from=Service&to="+mobileNo+"&text="+message+"&dlrreq=true&alert=1";		

			String sendingResponse = UtilityP.post(url, null, "text", "GET", null);

			System.out.println("sendingResponse===in====Controller=========="+sendingResponse);
			if (sendingResponse==null)
			{

				result="unsuccess";
			}
			else{
				result="success";
				System.out.println("result : "+result);
			}


			//System.out.println("the server ip is not same");


		}
		/*catch (SQLException e) 
	{
		e.printStackTrace();
	}*/ catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		finally
		{

		}
		return result;


	}


	public static String sendMobileSmsZone(String mobileNo,String message) {

		Statement stmt= null;
		ResultSet rs= null;
		String result="";
		//mobileNo="9891009321";

		try
		{
			message=message.replaceAll(" ", "%20");
			message=message.replaceAll("&", "and");

			String url="http://www.smszone.in/sendsms.asp?page=SendSmsBulk&username=917206671701&password=88088&number="+mobileNo+"&message="+message+"";

			String sendingResponse = UtilityP.post(url, null, "text", "GET", null);


			if (sendingResponse==null)
			{
				result="unsuccess";
			}
			else{
				result="success";
			}

		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return result;


	}

	public static void main(String ads[]){
		System.out.println("i m in main");
		try{


			String mobNo="9873494846";
			String pnrNumber1="2316245784";
			String trainName1="12345";
			String date1="3";
			String month1="2";
			String year1="2011";
			String passengers="P1-PQWL 1  //0000  /,P2-PQWL 2  //0000  /,P3-PQWL 3  //0000  /,P4-PQWL 4  //0000  /,P5-PQWL 5  //0000  /,P6-PQWL 6  //0000  /";

			//String message1="<html><body><table><tr><td>Your%20Rail%20ticket%20has%20been%20booked%20by%20Agent%20Name%209873494846%20&%20its%20PNR%20No:-%208888888888%20Dated:-%2000/00/0000%20Thanks%20for%20choosing%20Travel%20E%20Point</td></tr><table><body></html>";

			String testmessage = "Success Booking: PNR No:-"+pnrNumber1+" Trn:-"+trainName1+" DOJ:-"+date1+"/"+month1+"/"+year1+" , "+passengers+" Travel E Point";
			System.out.println("Ticket mobile SMS is <>..... "+testmessage);
			int lengthstring=testmessage.length();
			System.out.println("hello mobilesend sms  length is "+lengthstring);

			String smssendmessage = testmessage.replaceAll(" ", "%20"); 
			smssendmessage = smssendmessage.replaceAll("&", "and");



			System.out.println("final Ticket mobile SMS is <>..... "+smssendmessage);







			String respone=SmsToMobileDao.sendMobileSmsAcl(mobNo,smssendmessage);
			System.out.println("hello mobilesend sms  is "+respone);

		}catch(Exception e){
			System.out.println("Problem in calling method connection  "+e);
		}
	}






}