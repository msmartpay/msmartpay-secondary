
package com.mdamountrequest;
import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
 public class MdAmountRequestBean extends ActionForm 
{


	
	      private FormFile attachCashFile;
	                      
	       public FormFile getAttachCashFile() {
		   return attachCashFile;
	        }
			public void setAttachCashFile(FormFile attachCashFile) {
            this.attachCashFile = attachCashFile;
	}
	 
}
