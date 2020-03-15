package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Pedido;
import com.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository _repository;

	public List<Pedido> findAll() {
		return _repository.findAll();
	}
}
