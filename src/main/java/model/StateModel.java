package main.java.model;

public class StateModel {

	private String stateAbbr;
	private String stateName;

	public String getStateAbbr() {
		return stateAbbr;
	}
	public StateModel(String stateAbbr, String stateName) {
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
