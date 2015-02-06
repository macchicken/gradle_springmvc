package main.java.service;

import java.util.List;
import java.util.Map;

import main.java.dto.PersonVS;

public interface IPersonServie {

	public void save(PersonVS person);
	public void update(PersonVS person);
	public void delete(String id);
	public PersonVS select(String id);
	public List<PersonVS> selectAll();
	public List<PersonVS> selectBySize(Map<String,Integer> params);
	public Integer selectCountAll();
}
