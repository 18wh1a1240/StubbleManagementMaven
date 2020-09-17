package com.ts.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.rest.dto.Farmer;
import com.ts.db.HibernateTemplate;

public class FarmerDAO {

	private SessionFactory factory = null;

	public Farmer getFarmerByUserPass(String aadhar) {

		return (Farmer)HibernateTemplate.getObjectByUserPass(aadhar);
	}

	public int register(Farmer farmer) {
		System.out.println("Inside farmer registration");
		return HibernateTemplate.addObject(farmer);
	}

	public List<Farmer> getAllFarmers() {
		List<Farmer> farmers=(List)HibernateTemplate.getObjectListByQuery("From Farmer");
		System.out.println("Inside All Farmers ..."+farmers);
		return farmers;	
	}

	public Farmer getFarmer(int id) {
		return (Farmer)HibernateTemplate.getObject(Farmer.class, id);
	}


	/*public void getFarmers(int productId) {
		String query= "from Farmer where producttId = 1";
		System.out.println("get farmers is called...");
		List<Object> obj = (List<Object>) HibernateTemplate.getObjectListByQuery(query);
		System.out.println("Testing get farmers :" + obj); 
		for(Object f: obj){
			Farmer farmer = (Farmer)f;
			System.out.println(farmer.getFarmerName());
		}
	}*/

	/*public Farmer getEmpByEmail(String email) {
		return (Farmer)HibernateTemplate.getObjectByEmail(email);
	}*/
}