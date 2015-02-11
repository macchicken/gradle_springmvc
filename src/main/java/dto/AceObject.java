package main.java.dto;

public class AceObject implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4231414529564216521L;

	private String id;

	public String getId() {
		return id;
	}

	public AceObject() {
		super();
		this.id = null;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString(){
		return "Id: "+id;
	}
}
