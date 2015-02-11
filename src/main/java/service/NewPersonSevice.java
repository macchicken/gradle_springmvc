package main.java.service;

import java.util.List;
import java.util.Map;

import main.java.dao.PersonDao;
import main.java.dto.PersonVS;

public class NewPersonSevice implements IPersonServie {

	private PersonDao personDao;

	@Override
	public PersonVS save(PersonVS person) {
		personDao.savePerson(person);
		return person;
	}

	@Override
	public PersonVS update(PersonVS person) {
		personDao.updatePerson(person);
		return person;
	}

	@Override
	public void delete(String id) {
		personDao.deletePerson(id);
	}

	@Override
	public PersonVS select(String id) {
		return personDao.selectPerson(id);
	}

	@Override
	public List<PersonVS> selectAll() {
		return personDao.selectAll();
	}

	@Override
	public List<PersonVS> selectBySize(Map<String,Integer> params) {
		return personDao.selectBySize(params);
	}

	@Override
	public Integer selectCountAll() {
		return personDao.selectCountAll();
	}

	public PersonDao getPersonDao() {
		return personDao;
	}
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
