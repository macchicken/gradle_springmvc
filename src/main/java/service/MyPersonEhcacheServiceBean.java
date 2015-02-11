package main.java.service;

import java.util.List;
import java.util.Map;

import main.java.dao.NewPersonDao;
import main.java.dto.PersonVS;

public class MyPersonEhcacheServiceBean {

	private NewPersonDao newPersonDao;

	public NewPersonDao getNewPersonDao() {
		return newPersonDao;
	}

	public void setNewPersonDao(NewPersonDao newPersonDao) {
		this.newPersonDao = newPersonDao;
	}
	
	public PersonVS save(PersonVS person) {
		newPersonDao.savePerson(person);
		return person;
	}
	
	public PersonVS update(PersonVS person) {
		newPersonDao.updatePerson(person);
		return person;
	}
	
	public PersonVS select(String id) {
		return newPersonDao.selectPerson(id);
	}
	
	public List<PersonVS> selectBySize(Map<String, Integer> params) {
		return newPersonDao.selectBySize(params);
	}
	
	public Integer selectCountAll() {
		return newPersonDao.selectCountAll();
	}
}
