package com.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.Categoria;
import com.domain.Produto;
import com.repositories.CategoriaRepository;
import com.repositories.ProdutoRepository;

@Service
public class DbService {

	@Autowired
	private CategoriaRepository _categoriaRepo;

	@Autowired
	private ProdutoRepository _produtoRepo;

	public void InstanciateTestDataBase() {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		_categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		_produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
	}
}
