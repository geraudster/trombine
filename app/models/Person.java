package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Person extends Model {
    public String email;
    public String lastName;
    public String firstName;

    @ManyToOne
    public Organization organization;
    
    public Person(String email, String lastName, String firstName){
	this.email = email;
	this.lastName = lastName;
	this.firstName = firstName;
	this.organization = null;
    }
}