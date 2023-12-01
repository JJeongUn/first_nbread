package qna.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Qna implements Serializable{
	private static final long serialVersionUID = -7606627212449014239L;
	
	private int qaNum;
	private int meNum;
	private String qaTitle;
	private String qaPwd;
	private String qaCon;
	private String qaCategory;
	private Date qaEnroll;
	private Date qaModDate;
	private Date qaDelDate;
	private int qaViews;
	private int qaComCount;
	private Date qaAttDate;
	private int qaPhotoNum;
	private int qaLev;
	
	public Qna() {}

	public Qna(int qaNum, int meNum, String qaTitle, String qaPwd, String qaCon, String qaCategory, Date qaEnroll,
			Date qaModDate, Date qaDelDate, int qaViews, int qaComCount, Date qaAttDate, int qaPhotoNum, int qaLev) {
		super();
		this.qaNum = qaNum;
		this.meNum = meNum;
		this.qaTitle = qaTitle;
		this.qaPwd = qaPwd;
		this.qaCon = qaCon;
		this.qaCategory = qaCategory;
		this.qaEnroll = qaEnroll;
		this.qaModDate = qaModDate;
		this.qaDelDate = qaDelDate;
		this.qaViews = qaViews;
		this.qaComCount = qaComCount;
		this.qaAttDate = qaAttDate;
		this.qaPhotoNum = qaPhotoNum;
		this.qaLev = qaLev;
	}

	public int getQaNum() {
		return qaNum;
	}

	public void setQaNum(int qaNum) {
		this.qaNum = qaNum;
	}

	public int getMeNum() {
		return meNum;
	}

	public void setMeNum(int meNum) {
		this.meNum = meNum;
	}

	public String getQaTitle() {
		return qaTitle;
	}

	public void setQaTitle(String qaTitle) {
		this.qaTitle = qaTitle;
	}

	public String getQaPwd() {
		return qaPwd;
	}

	public void setQaPwd(String qaPwd) {
		this.qaPwd = qaPwd;
	}

	public String getQaCon() {
		return qaCon;
	}

	public void setQaCon(String qaCon) {
		this.qaCon = qaCon;
	}

	public String getQaCategory() {
		return qaCategory;
	}

	public void setQaCategory(String qaCategory) {
		this.qaCategory = qaCategory;
	}

	public Date getQaEnroll() {
		return qaEnroll;
	}

	public void setQaEnroll(Date qaEnroll) {
		this.qaEnroll = qaEnroll;
	}

	public Date getQaModDate() {
		return qaModDate;
	}

	public void setQaModDate(Date qaModDate) {
		this.qaModDate = qaModDate;
	}

	public Date getQaDelDate() {
		return qaDelDate;
	}

	public void setQaDelDate(Date qaDelDate) {
		this.qaDelDate = qaDelDate;
	}

	public int getQaViews() {
		return qaViews;
	}

	public void setQaViews(int qaViews) {
		this.qaViews = qaViews;
	}

	public int getQaComCount() {
		return qaComCount;
	}

	public void setQaComCount(int qaComCount) {
		this.qaComCount = qaComCount;
	}

	public Date getQaAttDate() {
		return qaAttDate;
	}

	public void setQaAttDate(Date qaAttDate) {
		this.qaAttDate = qaAttDate;
	}

	public int getQaPhotoNum() {
		return qaPhotoNum;
	}

	public void setQaPhotoNum(int qaPhotoNum) {
		this.qaPhotoNum = qaPhotoNum;
	}

	public int getQaLev() {
		return qaLev;
	}

	public void setQaLev(int qaLev) {
		this.qaLev = qaLev;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Qna [qaNum=" + qaNum + ", meNum=" + meNum + ", qaTitle=" + qaTitle + ", qaPwd=" + qaPwd + ", qaCon="
				+ qaCon + ", qaCategory=" + qaCategory + ", qaEnroll=" + qaEnroll + ", qaModDate=" + qaModDate
				+ ", qaDelDate=" + qaDelDate + ", qaViews=" + qaViews + ", qaComCount=" + qaComCount + ", qaAttDate="
				+ qaAttDate + ", qaPhotoNum=" + qaPhotoNum + ", qaLev=" + qaLev + "]";
	}

	
}
