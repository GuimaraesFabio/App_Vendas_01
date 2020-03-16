package com.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Produto;
import com.services.ProdutoService;

@RestController
@RequestMapping(value = "/pedidos")
public class ProdutoResource {

	@Autowired
	private ProdutoService _service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {

		Produto obj = _service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
