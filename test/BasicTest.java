import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup(){
	Fixtures.deleteAll();
    }

    @Test
    public void createAndRetrievePerson(){
	new Person("toto@example.com", "Dupont", "Toto").save();
	Person toto = Person.find("byEmail", "toto@example.com").first();

	assertNotNull(toto);
	assertEquals("Dupont", toto.lastName);
    }

    @Test
    public void createAndRetrieveOrganization(){
	Organization rootOrga = new Organization("Root orga").save();
	new Organization("Child", rootOrga).save();
	Organization child = Organization.find("byName", "Child").first();
	
	assertNotNull(child);
	assertEquals("Root orga", child.parent.name);

	new Organization("Child2", rootOrga.id).save();
	 child = Organization.find("byName", "Child2").first();
	
	assertNotNull(child);
	assertEquals("Root orga", child.parent.name);
    }

    @Test
    public void fullTest(){
	Fixtures.loadModels("data.yml");
	
	assertEquals(2, Organization.count());
	assertEquals(2, Person.count());

	Person toto = Person.find("byEmail", "toto@example.com").first();

	assertNotNull(toto);
	assertEquals("Dupont", toto.lastName);	

	assertNotNull(toto.organization);
	assertEquals("child", toto.organization.name);
	assertEquals("root orga", toto.organization.parent.name);

	Organization orga = Organization.find("byName", "child").first();
	assertEquals(2, orga.persons.size());
    }

}
