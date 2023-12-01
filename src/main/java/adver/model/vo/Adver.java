package adver.model.vo;

import java.sql.Date;

public class Adver implements java.io.Serializable{
	
	private static final long serialVersionUID = 3270377917901990388L;
	private int adNum;
    private String adTitle;
    private String adUrl;
    private int adPay;
    private String adSignOffOn;
    private Date adEnroll;
    private Date adStaDate;
    private Date adEndDate;
    private int adLev;
    private int meNum;
    private int adPhNum;
    private String adPhoto1;
    private String adPhoto2;
    private String adPhoto3;
    private String adPhoto4;
    private String adPhoto5;
    
    public Adver() {
		super();
	}

	public Adver(int adNum, String adTitle, String adUrl, int adPay, String adSignOffOn, Date adEnroll, Date adStaDate,
			Date adEndDate, int adLev, int meNum, int adPhNum, String adPhoto1, String adPhoto2, String adPhoto3,
			String adPhoto4, String adPhoto5) {
		super();
		this.adNum = adNum;
		this.adTitle = adTitle;
		this.adUrl = adUrl;
		this.adPay = adPay;
		this.adSignOffOn = adSignOffOn;
		this.adEnroll = adEnroll;
		this.adStaDate = adStaDate;
		this.adEndDate = adEndDate;
		this.adLev = adLev;
		this.meNum = meNum;
		this.adPhNum = adPhNum;
		this.adPhoto1 = adPhoto1;
		this.adPhoto2 = adPhoto2;
		this.adPhoto3 = adPhoto3;
		this.adPhoto4 = adPhoto4;
		this.adPhoto5 = adPhoto5;
	}

	public int getAdNum() {
		return adNum;
	}

	public void setAdNum(int adNum) {
		this.adNum = adNum;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public int getAdPay() {
		return adPay;
	}

	public void setAdPay(int adPay) {
		this.adPay = adPay;
	}

	public String getAdSignOffOn() {
		return adSignOffOn;
	}

	public void setAdSignOffOn(String adSignOffOn) {
		this.adSignOffOn = adSignOffOn;
	}

	public Date getAdEnroll() {
		return adEnroll;
	}

	public void setAdEnroll(Date adEnroll) {
		this.adEnroll = adEnroll;
	}

	public Date getAdStaDate() {
		return adStaDate;
	}

	public void setAdStaDate(Date adStaDate) {
		this.adStaDate = adStaDate;
	}

	public Date getAdEndDate() {
		return adEndDate;
	}

	public void setAdEndDate(Date adEndDate) {
		this.adEndDate = adEndDate;
	}

	public int getAdLev() {
		return adLev;
	}

	public void setAdLev(int adLev) {
		this.adLev = adLev;
	}

	public int getMeNum() {
		return meNum;
	}

	public void setMeNum(int meNum) {
		this.meNum = meNum;
	}

	public int getAdPhNum() {
		return adPhNum;
	}

	public void setAdPhNum(int adPhNum) {
		this.adPhNum = adPhNum;
	}

	public String getAdPhoto1() {
		return adPhoto1;
	}

	public void setAdPhoto1(String adPhoto1) {
		this.adPhoto1 = adPhoto1;
	}

	public String getAdPhoto2() {
		return adPhoto2;
	}

	public void setAdPhoto2(String adPhoto2) {
		this.adPhoto2 = adPhoto2;
	}

	public String getAdPhoto3() {
		return adPhoto3;
	}

	public void setAdPhoto3(String adPhoto3) {
		this.adPhoto3 = adPhoto3;
	}

	public String getAdPhoto4() {
		return adPhoto4;
	}

	public void setAdPhoto4(String adPhoto4) {
		this.adPhoto4 = adPhoto4;
	}

	public String getAdPhoto5() {
		return adPhoto5;
	}

	public void setAdPhoto5(String adPhoto5) {
		this.adPhoto5 = adPhoto5;
	}

	@Override
	public String toString() {
		return "Adver [adNum=" + adNum + ", adTitle=" + adTitle + ", adUrl=" + adUrl + ", adPay=" + adPay
				+ ", adSignOffOn=" + adSignOffOn + ", adEnroll=" + adEnroll + ", adStaDate=" + adStaDate
				+ ", adEndDate=" + adEndDate + ", adLev=" + adLev + ", meNum=" + meNum + ", adPhNum=" + adPhNum
				+ ", adPhoto1=" + adPhoto1 + ", adPhoto2=" + adPhoto2 + ", adPhoto3=" + adPhoto3 + ", adPhoto4="
				+ adPhoto4 + ", adPhoto5=" + adPhoto5 + "]";
	}
    
    
}


