package main.java.service;

import main.java.dto.Person;

public interface EditService {

	Person getPerson() ;

	void savePerson(Person personBean);
}
