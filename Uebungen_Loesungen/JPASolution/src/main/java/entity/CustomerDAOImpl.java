package entity;

import java.util.UUID;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Dependent
@Stateless
public class CustomerDAOImpl implements CustomerDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public String createCustomer(String firstname, String lastname, String city) {
		Customer c = new Customer(UUID.randomUUID().toString(), firstname, lastname, city);
		em.persist(c);
		return c.getId();
	}

	@Override
	public Customer findCustomer(String id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Customer updateCustomer(String id, String firstname, String lastname, String city) {
		Customer c = findCustomer(id);
		if(c!=null) {
			c.setFirstname(firstname);
			c.setLastname(lastname);
			c.setCity(city);
		}
		return c;
	}

}
