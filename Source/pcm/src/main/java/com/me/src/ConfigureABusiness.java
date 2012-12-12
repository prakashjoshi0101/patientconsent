package com.me.src;

import java.util.Date;

import com.me.src.dao.UserAccountDao;
import com.me.src.pojo.Person;
import com.me.src.pojo.Role;
import com.me.src.pojo.UserAccount;

public class ConfigureABusiness {
	@SuppressWarnings("deprecation")
	public void initGlobalAdmin() {
		
		Person person = new Person();
		person.setFirstName("Thomas");
		person.setLastName("Hardy");
		person.setGender(true);
		person.setPhone("857-245-1872");
		person.setSsn("781-27-2837");
		person.setEmailId("global@cms.org");
		person.setAddress("500 Bolyston St, Boston");
		person.setDob(new Date(1980, 11, 10));		
				
		UserAccount ua = new UserAccount();
		ua.setUsername("admin");
		ua.setPassword("admin");
		ua.setRole(Role.GlobalAdmin.toString());
		ua.setPerson(person);
		
		UserAccountDao userAccountDao = new UserAccountDao();
		userAccountDao.saveOrUpdate(ua);
	}
}
