package com.controlPanel.inventoryMgt.holidayPackg;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import com.controlPanel.inventoryMgt.holidayPackg.HolidayPackageFileUploadUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

public class HolidayPackageAction extends ActionSupport implements ModelDriven,ServletRequestAware
{
	private HolidayPackageForm holidayPackageForm=new HolidayPackageForm();
	private HolidayPackageFileUploadUtils FileUtils = new HolidayPackageFileUploadUtils();
	private HolidayPackageDao holidayPackageDao=null;
	private HttpServletRequest request=null;
	private HttpServletResponse response=null;
	private HttpSession session=null;
	private PrintWriter out=null;
	
	private Random r = new Random();
	
	public HolidayPackageForm getModel() 
	{
		return holidayPackageForm;
	}
	public String execute() throws IOException
	{
		String key="";
		
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		session=request.getSession();
		out=response.getWriter();
		
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String param=(String)request.getParameter("param");
		BigInteger clientUserId=new BigInteger(userId);
		
		if(param.equalsIgnoreCase("getHolidayPage"))
		{
			key="HolidayPage";
		}
		if(param.equalsIgnoreCase("saveHolidayDetails"))
		{
			int randomInt=1;
			for (int idx = 1; idx <= 3450934; ++idx)
			{
				randomInt = r.nextInt(39485793);
			     
			}
			
			
			System.out.println("--packageId----"+randomInt);
			holidayPackageForm.setPkgId(randomInt);
			
			String folderName="images";
			File image = holidayPackageForm.getUserImage();
            String imageFileName = holidayPackageForm.getUserImageFileName();
            System.out.println((new StringBuilder("imageFileName:--------")).append(imageFileName).toString());
            
            String headerHomeEx="";
			int dotPos1= imageFileName.lastIndexOf(".");
			headerHomeEx=imageFileName.substring(dotPos1+1,imageFileName.length());	
			
			if(headerHomeEx.equalsIgnoreCase("xlsx")||headerHomeEx.equalsIgnoreCase("docx")||headerHomeEx.equalsIgnoreCase("pdf")
					||headerHomeEx.equalsIgnoreCase("txt") || headerHomeEx.equalsIgnoreCase("bmp"))
			{
				request.setAttribute("message","Image File must be .jpg or .png or .gif or .jpeg.");
				return "HolidayPage";
			}
            String filePath = "";
            String newfilePath = "";
            try
            {
                filePath = request.getRealPath("/") + folderName + "/" +"holiday" + "/" + clientUserId + "/" + randomInt + "/";
                System.out.println("Server path:--------"+filePath);
                newfilePath = FileUtils.saveFile(image, imageFileName, filePath);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
                request.setAttribute("message", "Problem in uploading image file.");
                System.out.println("---------problem is in image upload-----------");
                key = "HolidayPage";
            }  
            String prePath="http://super.fairytrip.com/superadmin/";
			String packageImageFileName = prePath+folderName+"/"+ "holiday" + "/" +clientUserId+"/"+randomInt+"/"+imageFileName;
			
            holidayPackageForm.setUserImageFileName(packageImageFileName);
            System.out.println((new StringBuilder("--imagefile path final------")).append(holidayPackageForm.getUserImageFileName()).toString());

			
			holidayPackageForm.setUserId(clientUserId);
			
			String hotelBudgetFrom = holidayPackageForm.getHotelBudgetFrom();
			String hotelBudgetTo = holidayPackageForm.getHotelBudgetTo();
			
			String hotelBudget = hotelBudgetFrom+" - "+hotelBudgetTo;
			System.out.println("--------reached 1------------"+hotelBudget);
			holidayPackageForm.setHotelBudget(hotelBudget);
			
			String days = holidayPackageForm.getDays();
			String night = holidayPackageForm.getNights();
			
			String duration = days+" Days & "+night+" Nights";
			System.out.println("--------reached 2------------"+duration);
			holidayPackageForm.setDuration(duration);
			
			String packagePriceIncluded = holidayPackageForm.getPackagePriceIncludes();
			
			if(packagePriceIncluded.equalsIgnoreCase("Both"))
			{
				holidayPackageForm.setPackagePriceIncludes("AirPortTaxes & AirPortTransfer");
			}
			
			
			Date date = new Date(); 
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String datetime = dateFormat.format(date);
			
			holidayPackageForm.setDatetime(datetime);
			
			String [] packageInitierary = request.getParameterValues("packageInitierary");
			
			StringBuilder br = new StringBuilder();
			
			for(int i=0;i<packageInitierary.length;i++)
			{
				br=br.append("+"+packageInitierary[i]);
			}
			System.out.println("----string itinerary-------"+br);
			
			String arrivalCity = holidayPackageForm.getArrivalCity();
			System.out.println("----arrivalCity---1----"+arrivalCity);
		
			String arrivalCityCode = holidayPackageDao.getCityCode(arrivalCity);
			System.out.println("----arrivalCityCode---1----"+arrivalCityCode);
			
				holidayPackageForm.setArrivalCity(arrivalCityCode);
				System.out.println("----arrivalCity----1---"+holidayPackageForm.getArrivalCity());
				
			String departCity = holidayPackageForm.getDepartCity();
			System.out.println("----departCity---2----"+departCity);
			
			String departCityCode = holidayPackageDao.getCityCode(departCity);
			System.out.println("----departCityCode---1----"+departCityCode);
			
			holidayPackageForm.setDepartCity(departCityCode);
			System.out.println("----departCity---2----"+holidayPackageForm.getDepartCity());
				
			holidayPackageDao=HolidayPackageDao.getInstance();
			
			key=holidayPackageDao.saveHolidayDetails(holidayPackageForm);
			
			
			if(key.equalsIgnoreCase("failure"))
			{
				request.setAttribute("message", "Process abort due to technical failure.");
				key="success";
			}
			else
			{
				request.setAttribute("message", "Process completed successfully.");
			}
		}
		/*if(param.equalsIgnoreCase("destination"))
		{
			
			System.out.println("---- Inside City Details ------");
			ArrayList al = new ArrayList();
			
			if(al == null)
			{
				al = new ArrayList();
				System.out.println("-----------Array List is Empty--------------");
			}
			al=holidayPackageDao.cityDetails();
//			System.out.println("Cities------"+al);
			String cityInitial="<select id=\"destinationCity\" name=\"destinationCity\" ><option value=\"Select City\">Select City</option>";
			StringBuffer cityDetails=new StringBuffer();
			
			int size=0;
			size = al.size();
			
			if(size<0 || al==null)
			{
				request.setAttribute("message","No Details Found");
			}
			
			for(int i=0;i<size;i++)
			{
				HashMap hm = (HashMap)al.get(i);
				
				cityDetails.append("<option value=\""+hm.get("citycode")+"\">"+hm.get("citylist")+"</option>");
				
			}
			String cityend="</select>";
			
			String OutPut=cityInitial+cityDetails+cityend;
			
	//		System.out.println("Opt------"+OutPut);
			
			out.print(OutPut);
			return null;
		}
		if(param.equalsIgnoreCase("source"))
		{
			
			System.out.println("---- Inside City Details ------");
			ArrayList al = new ArrayList();
			
			if(al == null)
			{
				al = new ArrayList();
				System.out.println("-----------Array List is Empty--------------");
			}
			al=holidayPackageDao.cityDetails();
//			System.out.println("Cities------"+al);
			String cityInitial="<select id=\"sourceCity\" name=\"sourceCity\" ><option value=\"Select City\">Select City</option>";
			StringBuffer cityDetails=new StringBuffer();
			
			int size=0;
			size = al.size();
			
			if(size<0 || al==null)
			{
				request.setAttribute("message","No Details Found");
			}
			
			for(int i=0;i<size;i++)
			{
				HashMap hm = (HashMap)al.get(i);
				
				cityDetails.append("<option value=\""+hm.get("citycode")+"\">"+hm.get("citylist")+"</option>");
				
			}
			String cityend="</select>";
			
			String OutPut=cityInitial+cityDetails+cityend;
			
	//		System.out.println("Opt------"+OutPut);
			
			out.print(OutPut);
			return null;
		}*/
		return key;
	}
	@Override
	 public void setServletRequest(HttpServletRequest request) {
        this.request = request;
 
    }
	
}
