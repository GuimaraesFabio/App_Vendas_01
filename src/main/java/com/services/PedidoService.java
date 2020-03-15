package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Pedido;
import com.repositories.PedidoRepository;
import com.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository _repository;

	public List<Pedido> findAll() {
		return _repository.findAll();
	}
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj = _repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
	}
}
