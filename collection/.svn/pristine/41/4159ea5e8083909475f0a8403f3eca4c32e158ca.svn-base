package in.softsolutionzone.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Util {

	@Autowired
	ObjectMapper objectMapper;

	public LocalDateTime convertStringtoLocalDateTime(String strdate) {

		LocalDateTime localDatetime=null;
		Date date;
		try {
			date = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(strdate);
			long milliseconds = date.getTime();
			System.out.println(milliseconds);
			localDatetime=LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), 
					TimeZone.getDefault().toZoneId()); 

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Parsed Date?= " + localDatetime);
		return localDatetime;
	}

	public LocalDateTime convertStringtoLocalDateTimeIncreasedBy23hr(String strdate) {

		LocalDateTime localDatetime=null;
		Date date;
		try {
			date = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH).parse(strdate);
			long milliseconds = date.getTime()+(1000*60*60*23);
			System.out.println(milliseconds);
			localDatetime=LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), 
					TimeZone.getDefault().toZoneId()); 

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Parsed Date?= " + localDatetime);
		return localDatetime;
	}


	public String convertObjectToJson(Object obj) {
		JSONObject gson = new JSONObject(obj);
		return gson.toString();
	}
	public String generateTocken(Object obj) {
		return new String(Base64.getEncoder().encode(convertObjectToJson(obj).getBytes()));
	}
	public String decodeToken(String obj) {

		String result=new String(Base64.getDecoder().decode(obj.getBytes()));

		return result;
	}
	/*
	 * public Admin jaxbJsonToAdmin(String json) {
	 * Admin admin=null;
	 * try {
	 * admin = objectMapper.readValue(json, Admin.class);
	 * log.info(admin.getUser_id()+"");
	 * } catch (Exception e) {
	 * e.printStackTrace();
	 * }
	 * return admin;
	 * }
	 */


	public String generate26DigitUniqueTransactionId(String initial2Digit,String userId4Digit) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSSS");
		Date date=new Date();
		String tran_id16Digit=sdf.format(date);
		String transaction_id=initial2Digit+userId4Digit+tran_id16Digit+RandomStringUtils.randomAlphanumeric(4).toUpperCase();
		return transaction_id;
	}

	public String generateOTP() {
		return RandomStringUtils.randomNumeric(4);
	}

	public File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
		File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
		multipart.transferTo(convFile);
		return convFile;
	}

}
