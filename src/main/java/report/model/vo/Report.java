package report.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {
	
	private int rpNum;
	private String rpType;
	private Date rpDate;
	private String rpCon;
	private String rpProc;
	private Date rpProcDate;
	private int meNum;
	private int paNum;
	private int comNum;
	private int adNum;

    public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Report(int rpNum, String rpType, Date rpDate, String rpCon, String rpProc, Date rpProcDate, int meNum,
			int paNum, int comNum, int adNum) {
		super();
		this.rpNum = rpNum;
		this.rpType = rpType;
		this.rpDate = rpDate;
		this.rpCon = rpCon;
		this.rpProc = rpProc;
		this.rpProcDate = rpProcDate;
		this.meNum = meNum;
		this.paNum = paNum;
		this.comNum = comNum;
		this.adNum = adNum;
	}

	/**
     * @return int return the rpNum
     */
    public int getRpNum() {
        return rpNum;
    }

    /**
     * @param rpNum the rpNum to set
     */
    public void setRpNum(int rpNum) {
        this.rpNum = rpNum;
    }

    /**
     * @return String return the rpType
     */
    public String getRpType() {
        return rpType;
    }

    /**
     * @param rpType the rpType to set
     */
    public void setRpType(String rpType) {
        this.rpType = rpType;
    }

    /**
     * @return Date return the rpDate
     */
    public Date getRpDate() {
        return rpDate;
    }

    /**
     * @param rpDate the rpDate to set
     */
    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    /**
     * @return String return the rpCon
     */
    public String getRpCon() {
        return rpCon;
    }

    /**
     * @param rpCon the rpCon to set
     */
    public void setRpCon(String rpCon) {
        this.rpCon = rpCon;
    }

    /**
     * @return String return the rpProc
     */
    public String getRpProc() {
        return rpProc;
    }

    /**
     * @param rpProc the rpProc to set
     */
    public void setRpProc(String rpProc) {
        this.rpProc = rpProc;
    }

    /**
     * @return Date return the rpProcDate
     */
    public Date getRpProcDate() {
        return rpProcDate;
    }

    /**
     * @param rpProcDate the rpProcDate to set
     */
    public void setRpProcDate(Date rpProcDate) {
        this.rpProcDate = rpProcDate;
    }

    /**
     * @return int return the meNum
     */
    public int getMeNum() {
        return meNum;
    }

    /**
     * @param meNum the meNum to set
     */
    public void setMeNum(int meNum) {
        this.meNum = meNum;
    }

    /**
     * @return int return the paNum
     */
    public int getPaNum() {
        return paNum;
    }

    /**
     * @param paNum the paNum to set
     */
    public void setPaNum(int paNum) {
        this.paNum = paNum;
    }

    /**
     * @return int return the comNum
     */
    public int getComNum() {
        return comNum;
    }

    /**
     * @param comNum the comNum to set
     */
    public void setComNum(int comNum) {
        this.comNum = comNum;
    }

    /**
     * @return int return the adNum
     */
    public int getAdNum() {
        return adNum;
    }

    /**
     * @param adNum the adNum to set
     */
    public void setAdNum(int adNum) {
        this.adNum = adNum;
    }

	@Override
	public String toString() {
		return "Report [rpNum=" + rpNum + ", rpType=" + rpType + ", rpDate=" + rpDate + ", rpCon=" + rpCon + ", rpProc="
				+ rpProc + ", rpProcDate=" + rpProcDate + ", meNum=" + meNum + ", paNum=" + paNum + ", comNum=" + comNum
				+ ", adNum=" + adNum + "]";
	}
    
}
