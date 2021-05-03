package com.common;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import com.tradebalanceactivity.self.TradeBalanceFormBean;
import com.tradebalanceactivity.self.TradeBlanceDepositFormBean;
public class ConvertUtility {
	/**
	 *Created Date=8/App/2012,
	 *
	 * Created BY=Vibhor Kumar ,
	 * 
	 * Purpose=Action Class,
	 * 
	 * provide IP address of login system,convert one type form bean to other type ,
	 * 
	 * Updated Date="" ,Updated By=""
	 */
	
	public static final String FILE_PATH="C:/Testing/webapps/superadmin/";
	public static final String LOCAL_FILE_PATH="C:/usr/Trandata/";
	
	public static TradeBalanceFormBean convertTBDFormBeanToTBFormBean(TradeBlanceDepositFormBean tbd)
	{ TradeBalanceFormBean tb=new TradeBalanceFormBean();
				
	tb.setUserid(tbd.getUserid());
		tb.setTransactionId(tbd.getTransactionId());
		tb.setBankcharges(tbd.getBankcharges());
		tb.setRecieptNo(tbd.getRecieptNo());
		tb.setRecievingBankName(tbd.getRecievingBankName());
		tb.setRecievingBranchName(tbd.getRecievingBranchName());
		tb.setRecievingBankAccNo(tbd.getRecievingBankAccNo());
		tb.setRecievingBranchCity(tbd.getRecievingBranchCity());
		tb.setBankTranId(tbd.getBankTranId());
	    tb.setRefrenceId(tbd.getRefrenceId());
		tb.setDepositBankName(tbd.getDepositBankName());
		tb.setDepositLocation(tbd.getDepositLocation());
		tb.setChequeDDNo(tbd.getChequeDDNo());
		tb.setChequebankname(tbd.getChequebankname());
		tb.setApprovedBy(tbd.getApprovedBy());
		tb.setStatus(tbd.getStatus());
		tb.setCreditdays(tbd.getCreditdays());
		tb.setRemark(tbd.getRemark());
		tb.setRemarkAdmin(tbd.getRemarkAdmin());
		tb.setDepositorAccountNumber(tbd.getDepositorAccountNumber());
		tb.setRefereceno(tbd.getRefereceno());
		tb.setOrderId(tbd.getOrderId());
		tb.setCardholdername(tbd.getCardholdername());
		tb.setDepositerName(tbd.getDepositerName());
		tb.setReceiverName(tbd.getReceiverName());
		tb.setReceiverOfficeLocation(tbd.getReceiverOfficeLocation());
		
		
		return tb;
	}
	
	public static String getIPAddress()
	{
		InetAddress thisIp=null;
		String IpAddress=null;
		try
		{
			thisIp =InetAddress.getLocalHost();
			IpAddress=thisIp.getHostAddress();
			return IpAddress;
		}
			catch(Exception e) 
			{
				e.printStackTrace();
				return IpAddress;
			}
	}
	
	public static String transactionId()
	{
		Calendar currentDate = Calendar.getInstance();
		 SimpleDateFormat formatter= 
			  new SimpleDateFormat("yyyyMMddHHssmmmm");		
		 String transactionid=formatter.format(currentDate.getTime());
		 int value = (int)(Math.random()*999)+1000;
		 System.out.println(value);
		 String trId=transactionid+value;
		 
		 return trId;
	}
	public static String transactionNo()
	{
		Calendar currentDate = Calendar.getInstance();
		 SimpleDateFormat formatter= 
			  new SimpleDateFormat("yyyyMMddHHssmmmm");		
		 String transactionid=formatter.format(currentDate.getTime());
		 int value = (int)(Math.random()*999)+1000;
		 String trId="B"+transactionid+value;
		 
		 return trId;
	}
	
	public static Date convertStringToDate(String str)
	{
		Date date=null ;
		try 
		{  
			String str_date=str;
			DateFormat formatter ;  
		 
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date)formatter.parse(str_date);  
		
			System.out.println("Date is " +date );
		
		} catch (ParseException e)
		{
			System.out.println("Exception :"+e); 
		}  
		  return date;
		 
	}
	
	public static java.sql.Date convertStringToSqlDAte(String str)
	{
		Date date=null ;
		java.sql.Date sqlDate=null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 
		try 
		{
			date = df.parse(str);
			sqlDate = new java.sql.Date(date.getTime());
		} catch (ParseException e) 
		{
		e.printStackTrace();
		}
		
		  return sqlDate;
		 
	}
	
	public static Date setTime(String str)
	{
		Date date=new Date();
		date.setHours(Integer.parseInt(str));
		date.setMinutes(0);
		date.setSeconds(0);
		return date;
	}	

	public static String covertStringDateFormat(String dob)
	{
		try
		{
			SimpleDateFormat formatterA = new SimpleDateFormat("yyyy-mm-dd");
			Date date = formatterA.parse(dob);
			SimpleDateFormat formatterB = new SimpleDateFormat("dd/MM/yyyy");
			dob=formatterB.format(date);
		
			return "dob";
		}
		catch (Exception e) 
		{
			System.out.println(e.toString());
			return "dob";
		}
	}
	
	public static String getRandomNumber()
	{
		String uuid="";
		try
		{
			uuid = UUID.randomUUID().toString();
			uuid=uuid.substring(0,uuid.indexOf("-"));
			return uuid;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return uuid;
		}
	}
	
	public static String getDomainName(String domainName)
	{
		String str="";
		
		try
		{
			StringBuffer s=new StringBuffer(domainName);
			StringBuffer sb=new StringBuffer();
			domainName=domainName.toLowerCase();
			String s2="www.";
			int index=domainName.indexOf(s2);		  
			if(index==0)
			{
				sb=s.delete(0, 4);			   
				str=sb.toString();
			}
			else
			{
				str=domainName;
			}
			return str;
		}
		catch (Exception e)
		{
			return str;
		}
	}
	
	public static HashMap covertTimestampToSqlDate(Timestamp date)
	{
		HashMap map=new HashMap();
		java.sql.Timestamp st = date;		
		java.sql.Date date2 = new java.sql.Date(st.getTime());
		java.sql.Time time=new java.sql.Time(st.getTime());
		System.out.println(date2);
		System.out.println(time);
		map.put("Date", date2);
		map.put("time", time);
		return map;
	}
}
