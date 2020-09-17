package com.ts.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Farmer;
import com.rest.dto.Manufacturer;
import com.ts.db.HibernateTemplate;

public class ManufacturerDAO {
	
	private SessionFactory factory = null;

	public Manufacturer getManufacturerByMail(String mailId) {

		return (Manufacturer)HibernateTemplate.getObjectByMail(mailId);
	}

	public int register(Manufacturer manufacturer) {
		//java.util.Date utilDate = new java.sql.Date(employee.getJoinDate().getTime()); 
		return HibernateTemplate.addObject(manufacturer);
	}

	public List<Manufacturer> getAllManufacturers() {
		List<Manufacturer> manufacturers=(List)HibernateTemplate.getObjectListByQuery("From Manufacturer");
		System.out.println("Inside All Manufacturers ..."+manufacturers);
		return manufacturers;	
	}

	public Manufacturer getManufacturer(int id) {
		return (Manufacturer)HibernateTemplate.getObject(Manufacturer.class,id);
	}

}
