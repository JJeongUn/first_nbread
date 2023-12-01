package business.model.vo;

import java.sql.Date;

public class Business implements java.io.Serializable{
	private static final long serialVersionUID = -4054309927139569337L;

	private int buNum;
	private int meNum;
	private String buTitle;
	private String buPwd;
	private String buCon;
	private Date buEnroll;
	private Date buModDate;
	private Date buDelDate;
	private int buViews;
	private int buLev;
	private int buPhNum;
	
	public Business() {
		super();
	}

	public Business(int buNum, int meNum, String buTitle, String buPwd, String buCon, Date buEnroll, Date buModDate,
			Date buDelDate, int buViews, int buLev, int buPhNum) {
		super();
		this.buNum = buNum;
		this.meNum = meNum;
		this.buTitle = buTitle;
		this.buPwd = buPwd;
		this.buCon = buCon;
		this.buEnroll = buEnroll;
		this.buModDate = buModDate;
		this.buDelDate = buDelDate;
		this.buViews = buViews;
		this.buLev = buLev;
		this.buPhNum = buPhNum;
	}

	public int getBuNum() {
		return buNum;
	}

	public void setBuNum(int buNum) {
		this.buNum = buNum;
	}

	public int getMeNum() {
		return meNum;
	}

	public void setMeNum(int meNum) {
		this.meNum = meNum;
	}

	public String getBuTitle() {
		return buTitle;
	}

	public void setBuTitle(String buTitle) {
		this.buTitle = buTitle;
	}

	public String getBuPwd() {
		return buPwd;
	}

	public void setBuPwd(String buPwd) {
		this.buPwd = buPwd;
	}

	public String getBuCon() {
		return buCon;
	}

	public void setBuCon(String buCon) {
		this.buCon = buCon;
	}

	public Date getBuEnroll() {
		return buEnroll;
	}

	public void setBuEnroll(Date buEnroll) {
		this.buEnroll = buEnroll;
	}

	public Date getBuModDate() {
		return buModDate;
	}

	public void setBuModDate(Date buModDate) {
		this.buModDate = buModDate;
	}

	public Date getBuDelDate() {
		return buDelDate;
	}

	public void setBuDelDate(Date buDelDate) {
		this.buDelDate = buDelDate;
	}

	public int getBuViews() {
		return buViews;
	}

	public void setBuViews(int buViews) {
		this.buViews = buViews;
	}

	public int getBuLev() {
		return buLev;
	}

	public void setBuLev(int buLev) {
		this.buLev = buLev;
	}

	public int getBuPhNum() {
		return buPhNum;
	}

	public void setBuPhNum(int buPhNum) {
		this.buPhNum = buPhNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Business [buNum=" + buNum + ", meNum=" + meNum + ", buTitle=" + buTitle + ", buPwd=" + buPwd
				+ ", buCon=" + buCon + ", buEnroll=" + buEnroll + ", buModDate=" + buModDate + ", buDelDate="
				+ buDelDate + ", buViews=" + buViews + ", buLev=" + buLev + ", buPhNum=" + buPhNum + "]";
	}
	
	
}
