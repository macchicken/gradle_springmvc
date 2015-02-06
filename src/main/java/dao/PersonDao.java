package main.java.dao;

import java.util.List;
import java.util.Map;


import main.java.dto.PersonVS;

public interface PersonDao {

	public PersonVS selectPerson(String id);
	   
    public void savePerson(PersonVS person);
   
    public void updatePerson(PersonVS person);
   
    public void deletePerson(String id);
   
    public List<PersonVS> selectAll();

    public List<PersonVS> selectBySize(Map<String,Integer> params);

    public Integer selectCountAll();
}
