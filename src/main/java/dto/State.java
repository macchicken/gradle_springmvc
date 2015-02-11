package main.java.dto;

public class State implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4495807212962455448L;

	private String stateAbbr;
	private String stateName;

	public String getStateAbbr() {
		return stateAbbr;
	}
	public State(String stateAbbr, String stateName) {
		this.stateAbbr = stateAbbr;
		this.stateName = stateName;
	}
	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}
