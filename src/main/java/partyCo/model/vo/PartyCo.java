package partyCo.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PartyCo implements Serializable {
	
	private static final long serialVersionUID = -2376085233957247759L;
	
	private int comNum;
	private int paNum;
	private int comParent;
	private int comDepth;
	private String comCon;
	private int comViews;
	private int comCount;
	private Date comEnroll;
	private Date comModDate;
	private Date comDelDate;
	private int comPhotoNum;
	private int meNum;
	private String meAka;
	private String mePhotoAdd;
	private String meId;
	
	public PartyCo() {}
	
	public PartyCo(int paNum, int comParent, int comDepth, String comCon, int meNum) {
		super();
		this.paNum = paNum;
		this.comParent = comParent;
		this.comDepth = comDepth;
		this.comCon = comCon;
		this.meNum = meNum;
	}
	
	
	public PartyCo(int paNum, int comParent, int comDepth, String comCon, int meNum, String meId) {
		super();
		this.paNum = paNum;
		this.comParent = comParent;
		this.comDepth = comDepth;
		this.comCon = comCon;
		this.meNum = meNum;
		this.meId = meId;
	}
	
	public PartyCo(int comNum, int paNum, int comParent, int comDepth, String comCon, int comViews, int comCount,
			Date comEnroll, Date comModDate, Date comDelDate, int comPhotoNum) {
		super();
		this.comNum = comNum;
		this.paNum = paNum;
		this.comParent = comParent;
		this.comDepth = comDepth;
		this.comCon = comCon;
		this.comViews = comViews;
		this.comCount = comCount;
		this.comEnroll = comEnroll;
		this.comModDate = comModDate;
		this.comDelDate = comDelDate;
		this.comPhotoNum = comPhotoNum;
	}
	

	public PartyCo(int comNum, int paNum, int comParent, int comDepth, String comCon, int comViews, int comCount,
			Date comEnroll, Date comModDate, Date comDelDate, int comPhotoNum, int meNum, String meAka, String mePhotoAdd) {
		super();
		this.comNum = comNum;
		this.paNum = paNum;
		this.comParent = comParent;
		this.comDepth = comDepth;
		this.comCon = comCon;
		this.comViews = comViews;
		this.comCount = comCount;
		this.comEnroll = comEnroll;
		this.comModDate = comModDate;
		this.comDelDate = comDelDate;
		this.comPhotoNum = comPhotoNum;
		this.meNum = meNum;
		this.meAka = meAka;
		this.mePhotoAdd = mePhotoAdd;
	}

	public int getComNum() {
		return comNum;
	}
	
	public void setComNum(int comNum) {
		this.comNum = comNum;
	}

	public int getPaNum() {
		return paNum;
	}

	public void setPaNum(int paNum) {
		this.paNum = paNum;
	}

	public int getComParent() {
		return comParent;
	}

	public void setComParent(int comParent) {
		this.comParent = comParent;
	}

	public int getComDepth() {
		return comDepth;
	}

	public void setComDepth(int comDepth) {
		this.comDepth = comDepth;
	}

	public String getComCon() {
		return comCon;
	}

	public void setComCon(String comCon) {
		this.comCon = comCon;
	}

	public int getComViews() {
		return comViews;
	}

	public void setComViews(int comViews) {
		this.comViews = comViews;
	}

	public int getComCount() {
		return comCount;
	}

	public void setComCount(int comCount) {
		this.comCount = comCount;
	}

	public Date getComEnroll() {
		return comEnroll;
	}

	public void setComEnroll(Date comEnroll) {
		this.comEnroll = comEnroll;
	}

	public Date getComModDate() {
		return comModDate;
	}

	public void setComModDate(Date comModDate) {
		this.comModDate = comModDate;
	}

	public Date getComDelDate() {
		return comDelDate;
	}

	public void setComDelDate(Date comDelDate) {
		this.comDelDate = comDelDate;
	}

	public int getComPhotoNum() {
		return comPhotoNum;
	}

	public void setComPhotoNum(int comPhotoNum) {
		this.comPhotoNum = comPhotoNum;
	}
	
	public int getMeNum() {
		return meNum;
	}

	public void setMeNum(int meNum) {
		this.meNum = meNum;
	}
	
	

	public String getMeAka() {
		return meAka;
	}

	public void setMeAka(String meAka) {
		this.meAka = meAka;
	}

	public String getMePhotoAdd() {
		return mePhotoAdd;
	}

	public void setMePhotoAdd(String mePhotoAdd) {
		this.mePhotoAdd = mePhotoAdd;
	}
	
	public String getMeId() {
		return meId;
	}

	public void setMeId(String meId) {
		this.meId = meId;
	}

	@Override
	public String toString() {
		return "PartyReply [comNum=" + comNum + ", paNum=" + paNum + ", comParent=" + comParent + ", comDepth="
				+ comDepth + ", comCon=" + comCon + ", comViews=" + comViews + ", comCount=" + comCount + ", comEnroll="
				+ comEnroll + ", comModDate=" + comModDate + ", comDelDate=" + comDelDate + ", comPhotoNum="
				+ comPhotoNum + "]";
	}
	
}
