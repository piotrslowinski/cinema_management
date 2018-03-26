package pl.com.piotrslowinski.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cinema.class)
public abstract class Cinema_ {

	public static volatile CollectionAttribute<Cinema, Show> shows;
	public static volatile SingularAttribute<Cinema, String> city;
	public static volatile SingularAttribute<Cinema, String> name;
	public static volatile SingularAttribute<Cinema, Long> id;

}

