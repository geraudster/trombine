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
        List<Person> persons = Person.find("order by organization.name asc, lastName asc, firstName asc").fetch(10);
        //        List<Person> persons = Person.findAll();
        render(persons);
    }

}