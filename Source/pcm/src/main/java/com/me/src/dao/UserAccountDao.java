package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.UserAccount;

@Component
public class UserAccountDao extends MappedModelDao<UserAccount> {
	@Override
	public Class<UserAccount> getActualClass() {
		// TODO Auto-generated method stub
		return UserAccount.class;
	}
}
