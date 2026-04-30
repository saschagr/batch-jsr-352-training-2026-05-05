package entity;

import java.util.UUID;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Dependent
@Stateless
public class PersonDAOImpl implements PersonDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public String erzeugePerson(String vorname, String nachname) {
		Person p = new Person(UUID.randomUUID().toString(), vorname, nachname);
		em.persist(p);
		return p.getId();
	}

	@Override
	public Person findePerson(String id) {
		return em.find(Person.class, id);
	}

	@Override
	public Person aktualisierePerson(String id, String vorname, String nachname) {
		Person p = findePerson(id);
		if(p!=null) {
			p.setVorname(vorname);
			p.setNachname(nachname);
		}
		return p;
	}

}
