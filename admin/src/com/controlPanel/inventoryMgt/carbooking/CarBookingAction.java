package com.controlPanel.inventoryMgt.carbooking;

import java.io.File;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.controlPanel.inventoryMgt.holidayPackg.HolidayPackageFileUploadUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CarBookingAction extends ActionSupport implements ModelDriven
{
	private CarBookingForm carBookingForm=new CarBookingForm();
	private CarBookingDao carBookingDao=null;
	private HttpServletRequest request=null;
	private HttpSession session=null;
	private Random r = new Random();
	private HolidayPackageFileUploadUtils FileUtils = new HolidayPackageFileUploadUtils();
	public Object getModel() 
	{
		return carBookingForm;
	}
	
	public String execute() 
	{
		String key="";
		
		System.out.println("In CarBooking Action ------------------------------");
		
		request=ServletActionContext.getRequest();
		session=request.getSession();
		
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		String param=(String)request.getParameter("param");
		BigInteger clientUserId=new BigInteger(userId);
		if(param.equalsIgnoreCase("getCarBookingPage"))
		{
			return "getCarBookingPage";
		}
		if(param.equalsIgnoreCase("saveCarDetails"))
		{
			
			carBookingForm.setUserId(clientUserId);
			
			int randomInt=1;
			for (int idx = 1; idx <= 3450934; ++idx)
			{
				randomInt = r.nextInt(39485793);
			     
			}
						
			System.out.println("--packageId----"+randomInt);
			carBookingForm.setBookingId(randomInt);
			
			String BookingType = carBookingForm.getBookingType();
			String SubBookingType = "NA";
			if(BookingType.equalsIgnoreCase("OutStation"))
			{
				SubBookingType = request.getParameter("OutStationBookingType");
			}
			else
			if(BookingType.equalsIgnoreCase("Local"))
			{
				SubBookingType = request.getParameter("LocalBookingType");
			}
			else
			if(BookingType.equalsIgnoreCase("Transfer"))
			{
				SubBookingType = request.getParameter("TransferBookingType");
			}
			System.out.println("--sub booking type-------"+SubBookingType);
			carBookingForm.setSubBookingType(SubBookingType);
			
			
			String destinationCity = carBookingForm.getDestinationCity();
			System.out.println("----destinationCity---1----"+destinationCity);
		
			String destinationCityCityCode = carBookingDao.getCityCode(destinationCity);
			System.out.println("----destinationCity---1----"+destinationCityCityCode);
			
			carBookingForm.setDestinationCity(destinationCityCityCode);
				System.out.println("----destinationCityCityCode----1---"+carBookingForm.getDestinationCity());
				
			String originCity = carBookingForm.getOriginCity();
			System.out.println("----originCity---2----"+originCity);
			
			String originCityCityCode = carBookingDao.getCityCode(originCity);
			System.out.println("----originCityCityCode---1----"+originCityCityCode);
			
			carBookingForm.setOriginCity(originCityCityCode);
			System.out.println("----departCity---2----"+carBookingForm.getOriginCity());
			
			
			String folderName="images";
			File image = carBookingForm.getUserImage();
            String imageFileName = carBookingForm.getUserImageFileName();
            System.out.println((new StringBuilder("imageFileName:--------")).append(imageFileName).toString());
            
            String headerHomeEx="";
			int dotPos1= imageFileName.lastIndexOf(".");
			headerHomeEx=imageFileName.substring(dotPos1+1,imageFileName.length());	
			
			if(headerHomeEx.equalsIgnoreCase("xlsx")||headerHomeEx.equalsIgnoreCase("docx")||headerHomeEx.equalsIgnoreCase("pdf")
					||headerHomeEx.equalsIgnoreCase("txt") || headerHomeEx.equalsIgnoreCase("bmp"))
			{
				request.setAttribute("message","Image File must be .jpg or .png or .gif or .jpeg.");
				return "getCarBookingPage";
			}
            String filePath = "";
            String newfilePath = "";
            try
            {
                filePath = request.getRealPath("/") + folderName + "/" +"CarBook" + "/" + clientUserId + "/" + randomInt + "/";
                System.out.println("Server path:--------"+filePath);
                newfilePath = FileUtils.saveFile(image, imageFileName, filePath);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
                request.setAttribute("message", "Problem in uploading image file.");
                System.out.println("---------problem is in image upload-----------");
                key = "getCarBookingPage";
            }  
            
            String prePath="http://superadmin.commsoft.in/";
			String packageImageFileName = prePath+folderName+"/"+ "CarBook" + "/" +clientUserId+"/"+randomInt+"/"+imageFileName;
			
			carBookingForm.setUserImageFileName(packageImageFileName);
            System.out.println((new StringBuilder("--imagefile path final------")).append(carBookingForm.getUserImageFileName()).toString());
			
			System.out.println("--form bean data--------\ngetCarBooking--------"+carBookingForm.getsNo());
			System.out.println("\ngetUserId-----"+carBookingForm.getUserId()+"\ngetCarBooking------"+carBookingForm.getCarBooking()+"\n"+carBookingForm.getSubBookingType()+
					"\n"+carBookingForm.getSegment()+"\n"+carBookingForm.getOriginCity()+"\n"+carBookingForm.getDestinationCity()+"\n"+carBookingForm.getDaysavail()+"\n"+
					carBookingForm.getCarName()+"\n"+carBookingForm.getVendorName()+"\n"+carBookingForm.getSeatingCapacity()+"\n"+carBookingForm.getTripPrice()+"\n"+
					carBookingForm.getUserImageFileName()+"\n"+carBookingForm.getAirCondition()+"\n"+carBookingForm.getStereo()+"\n"+carBookingForm.getChargePerKm()+"\n"+
					carBookingForm.getAdvancePayment()+"\n"+carBookingForm.getSpecialOffers()+"\n"+carBookingForm.getTermCondition()+"\n"+carBookingForm.getCanPolicy()+
					carBookingForm.getFuelType()+"\n"+carBookingForm.getTransmissionType()+"\n"+carBookingForm.getNofBaggage());
				
			carBookingDao=CarBookingDao.getInstance();
			
			key=carBookingDao.saveCarBookingDetails(carBookingForm);
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
		return key;
	}

}
