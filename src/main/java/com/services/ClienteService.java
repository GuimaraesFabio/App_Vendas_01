package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Cliente;
import com.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository _repository;

	public List<Cliente> findAll() {
		return _repository.findAll();
	}
}
