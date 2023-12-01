package chat.model.vo;

import java.util.Date;
public class Chat implements java.io.Serializable {
    
    private int chatNum;
    private int paNum;
    private String meName;
    private String chatCon;
    private Date chatDate;

    


    public Chat() {
		super();
	}

	public Chat(int chatNum, int paNum, String meName, String chatCon, Date chatDate) {
		super();
		this.chatNum = chatNum;
		this.paNum = paNum;
		this.meName = meName;
		this.chatCon = chatCon;
		this.chatDate = chatDate;
	}

	/**
     * @return int return the chatNum
     */
    public int getChatNum() {
        return chatNum;
    }

    /**
     * @param chatNum the chatNum to set
     */
    public void setChatNum(int chatNum) {
        this.chatNum = chatNum;
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
     * @return String return the meName
     */
    public String getMeName() {
        return meName;
    }

    /**
     * @param meName the meName to set
     */
    public void setMeName(String meName) {
        this.meName = meName;
    }

    /**
     * @return String return the chatCom
     */
    public String getChatCon() {
        return chatCon;
    }

    /**
     * @param chatCom the chatCom to set
     */
    public void setChatCon(String chatCom) {
        this.chatCon = chatCom;
    }

    /**
     * @return Date return the chatDate
     */
    public Date getChatDate() {
        return chatDate;
    }

    /**
     * @param chatDate the chatDate to set
     */
    public void setChatDate(Date chatDate) {
        this.chatDate = chatDate;
    }

	@Override
	public String toString() {
		return "chat [chatNum=" + chatNum + ", paNum=" + paNum + ", meName=" + meName + ", chatCom=" + chatCon
				+ ", chatDate=" + chatDate + "]";
	}
    
}
