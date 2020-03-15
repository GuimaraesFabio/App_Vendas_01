package com.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Categoria;
import com.domain.Cidade;
import com.domain.Estado;
import com.domain.Produto;
import com.repositories.CategoriaRepository;
import com.repositories.CidadeRepository;
import com.repositories.EstadoRepository;
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

	public void InstanciateTestDataBase() {

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

	}
}
