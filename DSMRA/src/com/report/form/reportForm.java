package com.report.form;

public class reportForm {
	
	
	
	private String param;
	
	private String type;
	private String reportof;
	private String ToDate;
	private String fromDate;
	private int start;
	private int end;
	private int pno;
	private int count1;
	private int pagecount;
	private int pgno;
	private String ReportBy;
	private String EnterAgentId;
	
	public int getPgno() {
		return pgno;
	}
	public void setPgno(int pgno) {
		this.pgno = pgno;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReportof() {
		return reportof;
	}
	public void setReportof(String reportof) {
		this.reportof = reportof;
	}
	public String getToDate() {
		return ToDate;
	}
	public void setToDate(String toDate) {
		ToDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getCount1() {
		return count1;
	}
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public String getReportBy() {
		return ReportBy;
	}
	public void setReportBy(String reportBy) {
		ReportBy = reportBy;
	}
	public String getEnterAgentId() {
		System.out.println("getEnterAgentId");
		return EnterAgentId;
	}
	public void setEnterAgentId(String enterAgentId) {
		EnterAgentId = enterAgentId;
	}

}
