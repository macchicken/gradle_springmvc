package main.java.model;

public class MessageStoreModel {

	private String message;
    private String userName;
    private int helloCount;
	
    public MessageStoreModel() {
        this.message="Hello Spring Mvc User";
        this.userName="Barry";
    }
 
    public MessageStoreModel(String message, String userName) {
		this.message = message;
		this.userName = userName;
	}

	public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getHelloCount() {
		return helloCount;
	}

	public void setHelloCount(int helloCount) {
		this.helloCount = helloCount;
	}

	public String toString() {
        return message + " (from toString method)";
    }
}
