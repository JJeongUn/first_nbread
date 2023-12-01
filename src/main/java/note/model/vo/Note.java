package note.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Note implements Serializable {
	
	private static final long serialVersionUID = -2138342777947806459L;
	private int noNum;
	private int sendNum;
	private int recvNum;
	private String noCon;
	private Date noSenTime;
	private String noCh;
	
	public Note() {}
	
	

    public Note(int noNum, int sendNum, int recvNum, String noCon, Date noSenTime, String noCh) {
		super();
		this.noNum = noNum;
		this.sendNum = sendNum;
		this.recvNum = recvNum;
		this.noCon = noCon;
		this.noSenTime = noSenTime;
		this.noCh = noCh;
	}



	/**
     * @return int return the noNum
     */
    public int getNoNum() {
        return noNum;
    }

    /**
     * @param noNum the noNum to set
     */
    public void setNoNum(int noNum) {
        this.noNum = noNum;
    }

    /**
     * @return int return the sendNum
     */
    public int getSendNum() {
        return sendNum;
    }

    /**
     * @param sendNum the sendNum to set
     */
    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    /**
     * @return int return the recvNum
     */
    public int getRecvNum() {
        return recvNum;
    }

    /**
     * @param recvNum the recvNum to set
     */
    public void setRecvNum(int recvNum) {
        this.recvNum = recvNum;
    }

    /**
     * @return String return the noCon
     */
    public String getNoCon() {
        return noCon;
    }

    /**
     * @param noCon the noCon to set
     */
    public void setNoCon(String noCon) {
        this.noCon = noCon;
    }

    /**
     * @return Date return the noSenTime
     */
    public Date getNoSenTime() {
        return noSenTime;
    }

    /**
     * @param noSenTime the noSenTime to set
     */
    public void setNoSenTime(Date noSenTime) {
        this.noSenTime = noSenTime;
    }

    /**
     * @return String return the noCh
     */
    public String getNoCh() {
        return noCh;
    }

    /**
     * @param noCh the noCh to set
     */
    public void setNoCh(String noCh) {
        this.noCh = noCh;
    }



	@Override
	public String toString() {
		return "Note [noNum=" + noNum + ", sendNum=" + sendNum + ", recvNum=" + recvNum + ", noCon=" + noCon
				+ ", noSenTime=" + noSenTime + ", noCh=" + noCh + "]";
	}
    

}
