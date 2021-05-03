package com.common;
import org.apache.log4j.Logger;

public class LogWriter {

		

		/**
		* Prints the input string to standard output i.e console
		*/
	        
		     public  void print(String output,Logger logger){
		     System.out.println(output);
		     
			 
			 
			 try{
				 logger.info(" at "+new java.util.Date()+" : "+output);
				 	
			 }
		     catch(Exception ex){}
			
			
		 }
	 
		 
	   /*  public static void main(String ar[]){
	    	 
	    	 Logger logger = Logger.getLogger(LogWriter.class);
	    	 LogWriter log=new LogWriter();
	    	 
		    log.print("print message",logger);
		
	}*/

	}
