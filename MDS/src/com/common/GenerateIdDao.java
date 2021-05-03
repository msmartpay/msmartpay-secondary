package com.common;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;




public class GenerateIdDao {


	public static String  getIdNo() {


		String transaction_id="";

		try
		{



			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
			Date date=new Date();
			String tran_id=sdf.format(date);
			Random rand = new Random();
			int n = 4;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			String ran = String.valueOf(randomNum);
			transaction_id=tran_id+ran;

		}
		catch(Exception e)
		{
			System.out.println("hello Execption in CheckAvailabilityDao"+e.toString());
		}

		return transaction_id;
	}


}
