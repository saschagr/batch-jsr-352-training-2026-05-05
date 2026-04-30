/**********************************************************
* Begleitmaterial zum Buch "Enterprise JavaBeans 3.1"
* Das EJB-Praxisbuch fuer Ein- und Umsteiger
* Von Werner Eberling und Jan Lessner
* Hanser Fachbuchverlag Muenchen, 2011
* http://www.hanser.de/buch.asp?isbn=3-446-42259-5
* Feedback an ejb3buch@werner-eberling.de
**********************************************************/ 
package entity;

import javax.naming.InitialContext;

public class CustomerClient {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        
        CustomerDAO dao = (CustomerDAO) 
        		context.lookup("jpa-exercise/CustomerDAOImpl!entity.CustomerDAO");        
        
        String id = dao.createCustomer("Max","Mustermann","München");
        
        Customer c = dao.findCustomer(id);
        
        System.out.println("Gefunden: " + c);

        c = dao.updateCustomer(id, "Paula", "Musterfrau", "Pforzheim");

        System.out.println("Daten aktualisiert: " + c);

        c = dao.findCustomer(id);
        
        System.out.println("Gefunden: " + c);
    }
    
}
