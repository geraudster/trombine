package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
    @Before
    static void addDefaults() {
        renderArgs.put("trombineTitle", Play.configuration.getProperty("trombine.title"));
        renderArgs.put("trombineBaseline", Play.configuration.getProperty("trombine.baseline"));
    }

    public static void index() {
        List<Organization> organizations = Organization.find("order by name asc").fetch(10);
        render(organizations);
    }

    public static void showPerson(Long id) {
	Person person = Person.findById(id);
	render(person);
    }

    public static void showOrganization(Long id) {
	Organization organization = Organization.findById(id);
	render(organization);
    }

    public static void addOrganization(String name, String parent){
        Logger.info("Parent selection is %s", parent);
        Organization parentOrga = Organization.find("byName", parent).first();
        Organization orga = new Organization(name, parentOrga).save();
        showOrganization(orga.id);
    }

    public static void addPerson(String email, String lastName, String firstName){
        Person person = new Person(email, lastName, firstName).save();
        showPerson(person.id);
    }
}