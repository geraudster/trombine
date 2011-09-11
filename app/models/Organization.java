package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Organization extends Model {
    public String name;

    @ManyToOne
    public Organization parent;

    @OneToMany(mappedBy="organization", cascade=CascadeType.ALL)
    public List<Person> persons;

    public Organization(String name){
	this.name = name;

	// root organization
	this.parent = null;
    }

    public Organization(String name, Organization parent){
	this.name = name;
	this.parent = parent;
    }

    public Organization(String name, Long parentId){
        this.name = name;
        this.parent = Organization.findById(parentId);
    }
}