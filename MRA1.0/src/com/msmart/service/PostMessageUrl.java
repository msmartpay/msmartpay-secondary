package com.msmart.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Encoder;

public class PostMessageUrl {



	public static String postMessage(String urlLocation, 
			String requestContentType, String requestMethod
			) throws IOException {
		String response = null;
		/*
			  System.out.println("-----" + urlLocation + "-----" + postMessage
			    + "----" + requestContentType + "-----" + requestMethod
			    + "-----" + requestHeaders + "-----00000000000000000");
		 */
		System.out.println("am in post mehtod of utility....");
		if (urlLocation != null) {
			BufferedReader reader = null;
			OutputStreamWriter writer = null;
			HttpURLConnection connection = null;

			try {
				BASE64Encoder encoder = new BASE64Encoder();
				// Enter your basic authentication userid,pwd here
				String password = ""; 
				String authString = "Basic "
						+ encoder.encode(password.getBytes());
				//System.out.println("password getbytes is"+password.getBytes());
				URL url = new URL(urlLocation);
				connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(180000);
				connection.setReadTimeout(180000);
				connection.setDoOutput(true);
				connection.setRequestMethod(requestMethod);
				connection.setRequestProperty("Authorization", authString);

				if (requestContentType != null) {
					connection.setRequestProperty("Content-Type",
							requestContentType);
				}




				StringBuilder sb = new StringBuilder();
				String line = null;

				try {
					reader = new BufferedReader(new InputStreamReader(
							connection.getInputStream()));
				} catch (IOException e) {
					InputStream errorIp = connection.getErrorStream();
					if (errorIp != null) {
						BufferedReader errorReader = new BufferedReader(
								new InputStreamReader(errorIp));
						while ((line = errorReader.readLine()) != null) {
							sb.append(line);
						}
						System.out.println("Got Error Response: " + sb.toString());
					}     
					throw e;
				}

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				response = sb.toString();
				System.out.println("response-------------------" + response);
			} finally {
				try {
					if (writer != null) {
						writer.close();
					}
					if (reader != null) {
						reader.close();
					}
					if (connection != null) {
						connection.disconnect();
					}
				} catch (Exception e) {
				}
			}
		}
		if (response != null) {
			// Removes all special characters.
			response = new String(response.getBytes());
		}
		//			  System.out.println("am in last post method..");

		//			  System.out.println("from here response is returned.......");

		//			  String version="1.0";
		//				String version1="UTF-8";
		//				String urls="http://www.cleartrip.com/air/";
		//				String index1="1";
		//				String index2="2";
		//				response="<?xml version=\"1.0\" encoding=\"UTF-8\"?><air-search-result xmlns=\"http://www.cleartrip.com/air/\">    <onward-solutions>        <solution index=\"1\">           <pricing-summary>               <base-fare>1.00</base-fare> <discount>-0.01</discount>               <taxes>2239.01</taxes>               <total-fare>2240.00</total-fare>           </pricing-summary>           <flights>              <flight>                   <segments>                       <segment>                           <index>1</index>                            <departure-airport>BLR</departure-airport>                            <arrival-airport>AHD</arrival-airport>                            <departure-date-time>2009-11-30T10:10:00</departure-date-time>                            <arrival-date-time>2009-11-30T11:55:00</arrival-date-time>                            <airline>SG</airline>                           <flight-number>344</flight-number>                            <operating-airline>SG</operating-airline>                            <stops>0</stops>                           <equipment>737</equipment>                           <duration>6300</duration>                       </segment> 			<segment>                           <index>2</index>                            <departure-airport>AHD</departure-airport>                            <arrival-airport>BOM</arrival-airport>                           <departure-date-time>2009-11-30T12:20:00</departure-date-time>                            <arrival-date-time>2009-11-30T05:55:00</arrival-date-time>                            <airline>SG</airline>                           <flight-number>366</flight-number>                            <operating-airline>SG</operating-airline>                            <stops>0</stops>                           <equipment>737</equipment>                           <duration>6300</duration>                       </segment>                              </segments>               </flight>           </flights>           <pax-pricing-info-list>               <pax-pricing-info>                   <pax-type>ADT</pax-type>                    <pricing-info-list>                       <pricing-info>                           <index>1</index>                           <fare-basis-code>F</fare-basis-code>                            <fare-key>ZG9tZXN0aWMtU0ctUi1GLTM0NjYzMjcwODk=</fare-key>                           <fare-type>Refundable</fare-type>                           <pricing-elements>                               <pricing-element>                                   <category>DIS</category>                                   <code>SPECIAL</code>                                   <amount>-0.01</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>CLEARTRIP-SVC</code>                                   <amount>0.01</amount>                               </pricing-element>                               <pricing-element>                                   <category>BF</category>                                   <amount>1.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>PSF</code>                                   <amount>229.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>YQ</code>                                   <amount>1750.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>YR</code>                                   <amount>0.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>TF</code>                                   <amount>0.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>AIRLINE-MSC</code>                                   <amount>260.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>MKP</category>                                   <amount>0.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>DIS</category>                                   <amount>0.00</amount>                               </pricing-element>                           </pricing-elements>                       </pricing-info>                   </pricing-info-list>                   <booking-info-list>                       <booking-info>                           <index>1</index>                           <segment-index>1</segment-index>                           <pricing-info-index>1</pricing-info-index>                           <booking-class>R</booking-class>                           <cabin-type>E</cabin-type>                           <ticket-type>E</ticket-type>                       </booking-info>                   </booking-info-list>               </pax-pricing-info>           </pax-pricing-info-list>       </solution>       <solution index=\"2\">           <pricing-summary>               <base-fare>1.00</base-fare>               <discount>-0.01</discount>               <taxes>2239.01</taxes>               <total-fare>2240.00</total-fare>           </pricing-summary>           <flights>               <flight>                   <segments>                       <segment>                           <index>2</index>                           <departure-airport>BLR</departure-airport>                           <arrival-airport>BOM</arrival-airport>                           <departure-date-time>2009-11-30T19:05:00</departure-date-time>                            <arrival-date-time>2009-11-30T20:55:00</arrival-date-time>                           <airline>SG</airline>                           <flight-number>529</flight-number>                           <operating-airline>SG</operating-airline>                           <stops>0</stops>                           <equipment>737</equipment>                           <duration>6600</duration>                       </segment>                   </segments>               </flight>           </flights>           <pax-pricing-info-list>               <pax-pricing-info>                   <pax-type>ADT</pax-type>                    <pricing-info-list>                       <pricing-info>                           <index>2</index>                           <fare-basis-code>F</fare-basis-code>                           <fare-key>ZG9tZXN0aWMtU0ctUi1GLTM0NjYzMjcwODk=</fare-key>                           <fare-type>Refundable</fare-type>                           <pricing-elements>                               <pricing-element>                                   <category>DIS</category>                                   <code>SPECIAL</code>                                   <amount>-0.01</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>CLEARTRIP-SVC</code>                                   <amount>0.01</amount>                               </pricing-element>                               <pricing-element>                                   <category>BF</category>                                   <amount>1.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>PSF</code>                                   <amount>229.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>YQ</code>                                   <amount>1750.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>YR</code>                                   <amount>0.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>TAX</category>                                   <code>TF</code>                                   <amount>0.00</amount>                               </pricing-element>                          <pricing-element>                                    <category>TAX</category>                                   <code>AIRLINE-MSC</code>                                  <amount>260.00</amount>                               </pricing-element>                               <pricing-element>                                   <category>MKP</category>                                   <amount>0.00</amount>                              </pricing-element>                               <pricing-element>                                   <category>DIS</category>                                   <amount>0.00</amount>                               </pricing-element>                           </pricing-elements>                       </pricing-info>                   </pricing-info-list>                   <booking-info-list>                       <booking-info>                           <index>2</index>                           <segment-index>2</segment-index>                            <pricing-info-index>2</pricing-info-index>                            <booking-class>R</booking-class>                           <cabin-type>E</cabin-type>                           <ticket-type>E</ticket-type>                       </booking-info>                 </booking-info-list>               </pax-pricing-info>           </pax-pricing-info-list>       </solution>        </onward-solutions></air-search-result>";
		//			  System.out.println("new dummy response is"+response);

		return response;
	}



}
