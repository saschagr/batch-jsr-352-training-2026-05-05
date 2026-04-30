/**********************************************************
* Begleitmaterial zum Buch "Enterprise JavaBeans 3.1"
* Das EJB-Praxisbuch fuer Ein- und Umsteiger
* Von Werner Eberling und Jan Lessner
* Hanser Fachbuchverlag Muenchen, 2011
* http://www.hanser.de/buch.asp?isbn=3-446-42259-5
* Feedback an ejb3buch@werner-eberling.de
**********************************************************/ 
package entity;

@jakarta.ejb.Remote
public interface CustomerDAO {
	public String createCustomer(String firstname, String lastname, String city);
	public Customer findCustomer(String id);
    public Customer updateCustomer(String id, String firstname, String lastname, String city);
}
