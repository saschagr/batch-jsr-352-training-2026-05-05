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

public class PersonClient {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        
        PersonDAO dao = (PersonDAO) 
        		context.lookup("hello-jpa/PersonDAOImpl!entity.PersonDAO");        
        
        String id = dao.erzeugePerson("Max","Mustermann");
        
        Person p = dao.findePerson(id);
        
        System.out.println("Gefunden: " + p);

        p = dao.aktualisierePerson(id, "Paula", "Musterfrau");

        System.out.println("Daten aktualisiert: " + p);

        p = dao.findePerson(id);
        
        System.out.println("Gefunden: " + p);
    }
    
}
