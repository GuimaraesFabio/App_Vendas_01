package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.domain.Categoria;
import com.domain.Produto;
import com.repositories.CategoriaRepository;
import com.repositories.ProdutoRepository;
import com.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository _repository;

	@Autowired
	CategoriaRepository _categoriaRepo;

	public Produto findById(Integer id) {
		Optional<Produto> obj = _repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found."));
	}

	@GetMapping
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linePerPage, String direction,
			String orderBy) {

		PageRequest pr = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = _categoriaRepo.findAllById(ids);
		return _repository.search(nome, categorias, pr);
	}

}
