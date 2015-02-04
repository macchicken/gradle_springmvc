package main.java.service;

import main.java.dto.Person;

public class EditServiceInMemory implements EditService {

	private static Person person ;
	private static String [] carModels = {"Benz","Nissan"};
	
	static {
		person = new Person();
		person.setFirstName("Barry");
		person.setLastName("Chan");
		person.setSport("basketball");
		person.setGender("not sure");
		person.setResidency("CHN");
		person.setOver21(true);
		person.setCarModels( carModels);		
	}
	@Override
	public Person getPerson() {
		return EditServiceInMemory.person;
	}

	@Override
	public void savePerson(Person person) {
		EditServiceInMemory.person.setFirstName(person.getFirstName());
		EditServiceInMemory.person.setLastName(person.getLastName());
		EditServiceInMemory.person.setEmail(person.getEmail());
		EditServiceInMemory.person.setPhone(person.getPhone());
		EditServiceInMemory.person.setSport(person.getSport());
		EditServiceInMemory.person.setGender(person.getGender());
		EditServiceInMemory.person.setResidency(person.getResidency());
		EditServiceInMemory.person.setOver21(person.isOver21());
		EditServiceInMemory.person.setCarModels(person.getCarModels());
	}

}
