package com.kwt.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kwt.app.entity.Product;
import com.kwt.app.repository.ProductRepository;
import com.kwt.app.service.PrintMessageService;

@Service
public class PrintMessageServiceImpl implements PrintMessageService {
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public String printMessage() {
		return "This is inject message";
	}

	@Override
	public Long addUser(String name) {
		Product u = new Product();
		u.setName(name);
		u = productRepo.save(u);
		return u.getId();
	}

}
