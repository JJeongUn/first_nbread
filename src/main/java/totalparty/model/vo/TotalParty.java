package totalparty.model.vo;

public class TotalParty implements java.io.Serializable{
    private int paNum;
    private int meNum;
    private String payOk;
    public TotalParty() {
		super();
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
    
	public String getPayOk() {
		return payOk;
	}

	public void setPayOk(String payOk) {
		this.payOk = payOk;
	}

	@Override
	public String toString() {
		return "TotalParty [paNum=" + paNum + ", meNum=" + meNum + "]";
	}
}
