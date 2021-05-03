package com.utility;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DummyRead {
	
		public DummyRead()
		{
		}
		public String getFlightName(String code)
		{
			
			String val="";
		try {

	            
	    	 	Properties props = new Properties();
		        
					props.load(this.getClass().getResourceAsStream("/message.properties"));
				
		        String pathname = props.getProperty("message");
	    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder;
				
					docBuilder = docBuilderFactory.newDocumentBuilder();
				
	            Document doc = docBuilder.parse (new File(pathname+"/AirLineName.xml"));

	           


	            NodeList listOfPersons = doc.getElementsByTagName("Airline");
	            
	            for(int i=0;i<listOfPersons.getLength();i++)
	            {
	            	Node aaaa=listOfPersons.item(i);
	            	NamedNodeMap vvv=aaaa.getAttributes();
	            	Node aaa=vvv.getNamedItem("Code");
	            	String val1=aaa.getNodeValue();
	            	
	            	if(val1.endsWith(code)){
	            	val=aaaa.getNodeValue();
	            	}
	            }

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        }catch (SAXParseException err) {
	        System.out.println ("** Parsing error" + ", line " 
	             + err.getLineNumber () + ", uri " + err.getSystemId ());
	        System.out.println(" " + err.getMessage ());

	        }catch (SAXException e) {
	        Exception x = e.getException ();
	        ((x == null) ? e : x).printStackTrace ();

	        }
	        //System.exit (0);
		return val;
	    }//end of main

		private static String GetText(Element inNode, String nodeName)
	    {
	        NodeList tempNodes = inNode.getElementsByTagName(nodeName);
	        if (tempNodes.getLength() == 0)
	            return "";
	        return tempNodes.item(0).getFirstChild().getNodeValue();
	    }
		
		public String getCityName(String code)
		{
			String val="";
			try {

		            
		    	 	Properties props = new Properties();
			        
						props.load(this.getClass().getResourceAsStream("/message.properties"));
					
			        String pathname = props.getProperty("message");
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder;
					
						docBuilder = docBuilderFactory.newDocumentBuilder();
					
		            Document doc = docBuilder.parse (new File(pathname+"/cityName.xml"));

		            NodeList listOfPersons = doc.getElementsByTagName("City");
		            for(int i=0;i<listOfPersons.getLength();i++)
		            {
		            	Node aaaa=listOfPersons.item(i);
		            	NamedNodeMap CityAirportAttribute=aaaa.getAttributes();
		            	Node cityCode=CityAirportAttribute.getNamedItem("Code");
		            	String cityCodeValue=cityCode.getNodeValue();
		            	if(cityCodeValue.equals(code))
		            	{
		            		val=aaaa.getNodeValue();
		            	}
		            	
		            }
		         

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());

		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();

		        }
		        //System.exit (0);
			return val;
			
		}
		
		public String getCityCode(String name)
		{
			String val="";
			try {

		            
		    	 	Properties props = new Properties();
			        
						props.load(this.getClass().getResourceAsStream("/message.properties"));
					
			        String pathname = props.getProperty("message");
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder;
					
						docBuilder = docBuilderFactory.newDocumentBuilder();
					
		            Document doc = docBuilder.parse (new File(pathname+"/cityName.xml"));

		          


		            NodeList listOfPersons = doc.getElementsByTagName("City");
		            
		            for(int i=0;i<listOfPersons.getLength();i++)
		            {
		            	Node aaaa=listOfPersons.item(i);
		            	String cityname=aaaa.getNodeValue();
		            	if(name.equals(cityname))
		            	{
		            		NamedNodeMap vvv=aaaa.getAttributes();
			            	Node aaa=vvv.getNamedItem("Code");
			            	val=aaa.getNodeValue();	
		            	}
		            }

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());

		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();

		        }
		        //System.exit (0);
			return val;
			
		}
		public String getLogos(String flightName) {
			String val="";
			try {
		    	 	Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/message.properties"));
			        String pathname = props.getProperty("message");
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder;
					docBuilder = docBuilderFactory.newDocumentBuilder();
		            Document doc = docBuilder.parse (new File(pathname+"/AirLineImages.xml"));
		            NodeList listOfPersons = doc.getElementsByTagName("Airline");
		            for(int i=0;i<listOfPersons.getLength();i++)
		            {
		            	Node aaaa=listOfPersons.item(i);
		            	NamedNodeMap vvv=aaaa.getAttributes();
		            	Node aaa=vvv.getNamedItem("Code");
		            	String val1=aaa.getNodeValue();
		            	if(val1.endsWith(flightName))
		            	{
		            	val=aaaa.getNodeValue();
		            	}
		            }

				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());
		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();
		        }
			return val;
		}
		
		public String getInterNationCityName(String flightName) {
			String ValueOFAll="<select name=toText class=text size=5 id=toText onclick=selectDestinationCity() >" +
					"<option value=-1>---select---</option>";
			try {
		    	 	Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/message.properties"));
			        String pathname = props.getProperty("message");
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder;
					docBuilder = docBuilderFactory.newDocumentBuilder();
		            Document doc = docBuilder.parse (new File(pathname+"/cityAirportCode.xml"));
		            NodeList listOfPersons = doc.getElementsByTagName("CityAirport");
		            for(int i=0;i<listOfPersons.getLength();i++)
		            {
		            	Node aaaa=listOfPersons.item(i);
		            	NamedNodeMap CityAirportAttribute=aaaa.getAttributes();
		            	Node AirportCode=CityAirportAttribute.getNamedItem("AirportCode");
		            	String AirportCodeValue=AirportCode.getNodeValue();
		            	Node CityCode=CityAirportAttribute.getNamedItem("CityCode");
		            	String CityCodeValue=CityCode.getNodeValue();
		            	Node CityName=CityAirportAttribute.getNamedItem("CityName");
		            	String CityNameval=CityName.getNodeValue();
		            	if(CityNameval.toUpperCase().startsWith(flightName.toUpperCase()))
		            	{
		            		ValueOFAll+="<option value="+AirportCodeValue+">"+CityNameval+"("+AirportCodeValue+")</option>";
		            	}
		            	
		            }
		            ValueOFAll+="</select>"; 

				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());
		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();
		        }
			return ValueOFAll;
		}
		
		public String getInterNationNameD(String CityInitial) {
			String ValueOFAll="";
			try {
		    	 	Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/message.properties"));
			        String pathname = props.getProperty("message");
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder;
					docBuilder = docBuilderFactory.newDocumentBuilder();
		            Document doc = docBuilder.parse (new File(pathname+"/tarcity.xml"));
		            NodeList listOfRecord = doc.getElementsByTagName("RECORD");
		            for(int i=0;i<listOfRecord.getLength();i++)
		            {
		            	Element firstRecord=(Element)listOfRecord.item(i);
		            	NodeList cityCode=firstRecord.getElementsByTagName("TAR_city_code");
		            	NodeList cityName=firstRecord.getElementsByTagName("TAR_city_name");
		            	Element cityCodeEle=(Element)cityCode.item(0);
		            	Element cityNameEle=(Element)cityName.item(0);
		            	
		            	
		            	if(cityNameEle.getNodeValue().toUpperCase().startsWith(CityInitial.toUpperCase()))
		            	{
		            		String Cityname="";
			            	StringTokenizer st = new StringTokenizer(cityNameEle.getNodeValue()," ");
			            	int count=st.countTokens();
			            	
			            	for(int k=0; k<count; k++){
			            		String token=st.nextToken();
			            		if(k==0)
			            		{
				            		if(token.length()>1)
				            		{
				            			Cityname= Cityname+token.substring(0,1).toUpperCase()+token.substring(1, token.length()).toLowerCase();
				            		}
				            		else
				            		{
				            			Cityname= Cityname+token.substring(0,1).toUpperCase();
				            		}
			            		}
			            		else
			            		{
			            			if(token.length()>1)
				            		{
				            			Cityname= Cityname+" "+token.substring(0,1).toUpperCase()+token.substring(1, token.length()).toLowerCase();
				            		}
				            		else
				            		{
				            			Cityname= Cityname+" "+token.substring(0,1).toUpperCase();
				            		}
			            		}
			            		
							}
			            	
		            		ValueOFAll+=cityCodeEle.getNodeValue()+"###"+Cityname+"|";
		            		
		            	}
		            	
		            }
		           

				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());
		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();
		        }
			return ValueOFAll;
		}
		
		
		public String getTrainNameD(String CityInitial) {
			String ValueOFAll="";
			try {

		    	 	Properties props = new Properties();
					props.load(this.getClass().getResourceAsStream("/message.properties"));
			        String pathname = props.getProperty("message");

					System.out.println("hello we are inside get city class");

			       
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder;
					docBuilder = docBuilderFactory.newDocumentBuilder();
		            Document doc = docBuilder.parse (new File(pathname+"/traincity.xml"));
		            NodeList listOfRecord = doc.getElementsByTagName("RECORD");
		            for(int i=0;i<listOfRecord.getLength();i++)
		            {
		            	Element firstRecord=(Element)listOfRecord.item(i);
		            	NodeList cityCode=firstRecord.getElementsByTagName("Rail_city_code");
		            	NodeList cityName=firstRecord.getElementsByTagName("Rail_city_name");
		            	Element cityCodeEle=(Element)cityCode.item(0);
		            	Element cityNameEle=(Element)cityName.item(0);
		            	
		            	String Cityname="";
		            	String Citycode="";
		            	if((cityNameEle.getNodeValue().toUpperCase().startsWith(CityInitial.toUpperCase()))||(cityCodeEle.getNodeValue().toUpperCase().startsWith(CityInitial.toUpperCase())))
		            	{
		            		
			            	StringTokenizer st = new StringTokenizer(cityNameEle.getNodeValue()," ");
			            	int count=st.countTokens();
			            	
			            	for(int k=0; k<count; k++){
			            		String token=st.nextToken();
			            		if(k==0)
			            		{
				            		if(token.length()>1)
				            		{
				            			Cityname= Cityname+token.substring(0,1).toUpperCase()+token.substring(1, token.length()).toUpperCase();
				            		}
				            		else
				            		{
				            			Cityname= Cityname+token.substring(0,1).toUpperCase();
				            		}
			            		}
			            		else
			            		{
			            			if(token.length()>1)
				            		{
				            			Cityname= Cityname+" "+token.substring(0,1).toUpperCase()+token.substring(1, token.length()).toUpperCase();
				            		}
				            		else
				            		{
				            			Cityname= Cityname+" "+token.substring(0,1).toUpperCase();
				            		}
			            		}
			            		
							}
			            	
		            		ValueOFAll+=cityCodeEle.getNodeValue()+"###"+Cityname+"|";
		            		
		            	}
		            	
		            	/*if(cityCodeEle.getTextContent().toUpperCase().startsWith(CityInitial.toUpperCase()))
		            	{
		            		
			            	StringTokenizer st = new StringTokenizer(cityCodeEle.getTextContent()," ");
			            	int count=st.countTokens();
			            	
			            	for(int k=0; k<count; k++){
			            		String token=st.nextToken();
			            		if(k==0)
			            		{
				            		if(token.length()>1)
				            		{
				            			Citycode= Citycode+token.substring(0,1).toUpperCase()+token.substring(1, token.length()).toUpperCase();
				            		}
				            		else
				            		{
				            			Citycode= Citycode+token.substring(0,1).toUpperCase();
				            		}
			            		}
			            		else
			            		{
			            			if(token.length()>1)
				            		{
			            				Citycode= Citycode+" "+token.substring(0,1).toUpperCase()+token.substring(1, token.length()).toUpperCase();
				            		}
				            		else
				            		{
				            			Citycode= Citycode+" "+token.substring(0,1).toUpperCase();
				            		}
			            		}
			            		
							}
			            	
		            		ValueOFAll+=cityCodeEle.getTextContent()+"###"+Cityname+"|";
		            		
		            	}*/
		            
		            
		            }
		           

				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());
		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();
		        }
			return ValueOFAll;
		}
	}