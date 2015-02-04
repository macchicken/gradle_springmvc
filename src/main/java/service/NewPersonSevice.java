package main.java.service;

import java.util.List;

import main.java.dao.PersonDao;
import main.java.dto.PersonVS;

public class NewPersonSevice implements IPersonServie {

	private PersonDao personDao;
	@Override
	public void save(PersonVS person) {
		personDao.savePerson(person);
	}

	@Override
	public void update(PersonVS person) {
		personDao.updatePerson(person);
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

	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}


}
