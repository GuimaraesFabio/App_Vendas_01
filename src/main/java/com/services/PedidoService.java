package com.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.ItemPedido;
import com.domain.PagamentoComBoleto;
import com.domain.Pedido;
import com.enums.EstadoPagamento;
import com.repositories.ItemPedidoRepository;
import com.repositories.PagamentoRepository;
import com.repositories.PedidoRepository;
import com.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository _repository;

	@Autowired
	private BoletoService _boletoService;

	@Autowired
	private PagamentoRepository _pagamentoRepo;

	@Autowired
	private ProdutoService _produtoService;

	@Autowired
	private ItemPedidoRepository _itemPedidoRepo;

	public List<Pedido> findAll() {
		return _repository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> obj = _repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
	}

	@Transactional
	public Pedido insert(Pedido obj) {

		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setDescricao(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			_boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = _repository.save(obj);
		_pagamentoRepo.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {

			ip.setDesconto(0.0);
			ip.setPreco(_produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}

		_itemPedidoRepo.saveAll(obj.getItens());
		return obj;

	}
}
