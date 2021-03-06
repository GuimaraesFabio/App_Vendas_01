package com.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Categoria;
import com.domain.Cidade;
import com.domain.Cliente;
import com.domain.Endereco;
import com.domain.Estado;
import com.domain.ItemPedido;
import com.domain.Pagamento;
import com.domain.PagamentoComBoleto;
import com.domain.PagamentoComCartao;
import com.domain.Pedido;
import com.domain.Produto;
import com.enums.EstadoPagamento;
import com.enums.TipoCliente;
import com.repositories.CategoriaRepository;
import com.repositories.CidadeRepository;
import com.repositories.ClienteRepository;
import com.repositories.EnderecoRepository;
import com.repositories.EstadoRepository;
import com.repositories.ItemPedidoRepository;
import com.repositories.PagamentoRepository;
import com.repositories.PedidoRepository;
import com.repositories.ProdutoRepository;

@Service
public class DbService {

	@Autowired
	private CategoriaRepository _categoriaRepo;

	@Autowired
	private ProdutoRepository _produtoRepo;

	@Autowired
	private EstadoRepository _estadoRepo;

	@Autowired
	private CidadeRepository _cidadeRepo;

	@Autowired
	private ClienteRepository _clienteRepo;

	@Autowired
	private EnderecoRepository _enderecoRepo;

	@Autowired
	private PedidoRepository _pedidoRepo;

	@Autowired
	private PagamentoRepository _pagamentoRepo;

	@Autowired
	private ItemPedidoRepository _itemPedidoRepo;

	public void InstanciateTestDataBase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");

		_categoriaRepo.saveAll(Arrays.asList(cat1, cat2));

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

		_produtoRepo.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		_estadoRepo.saveAll(Arrays.asList(est1, est2));
		_cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria da Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		_clienteRepo.saveAll(Arrays.asList(cli1));
		_enderecoRepo.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		_pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		_pagamentoRepo.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		_itemPedidoRepo.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
