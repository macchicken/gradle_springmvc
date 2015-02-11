package main.java.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import main.java.dao.NewPersonDao;
import main.java.dto.PersonVS;

public class NewPersonCacheService implements IPersonServie {

	private NewPersonDao newPersonDao;

	@Override
	@CachePut(value="personCache",key="#person.id")
	public PersonVS save(PersonVS person) {
		newPersonDao.savePerson(person);
		return person;
	}

	@Override
	@CachePut(value="personCache",key="#person.id")
	public PersonVS update(PersonVS person) {
		newPersonDao.updatePerson(person);
		return person;
	}

	@Override
	@CacheEvict(value="personCache",key="#id")
	public void delete(String id) {
		newPersonDao.deletePerson(id);
	}

	@Override
	@Cacheable(value="personCache",key="#id")
	public PersonVS select(String id) {
		return newPersonDao.selectPerson(id);
	}

	@Override
	@Cacheable(value="personCache")
	public List<PersonVS> selectAll() {
		return newPersonDao.selectAll();
	}

	@Override
	@Cacheable(value="personCache")
	public List<PersonVS> selectBySize(Map<String, Integer> params) {
		return newPersonDao.selectBySize(params);
	}

	@Override
	public Integer selectCountAll() {
		return newPersonDao.selectCountAll();
	}

	public NewPersonDao getNewPersonDao() {
		return newPersonDao;
	}

	public void setNewPersonDao(NewPersonDao newPersonDao) {
		this.newPersonDao = newPersonDao;
	}

}
