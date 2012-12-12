package com.me.src.dao;

import org.springframework.stereotype.Component;

import com.me.src.pojo.ConsentRequest;

@Component
public class ConsentRequestDao extends MappedModelDao<ConsentRequest> {

	@Override
	public Class<ConsentRequest> getActualClass() {
		// TODO Auto-generated method stub
		return ConsentRequest.class;
	}
}
