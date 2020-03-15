package com.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.domain.Categoria;
import com.dto.CategoriaDto;
import com.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService _service;

	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll() {
		List<Categoria> list = _service.findAll();
		List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria obj = _service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody CategoriaDto objDto) {
		Categoria obj = _service.fromDto(objDto);
		obj = _service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody CategoriaDto objDto, @PathVariable Integer id) {
		Categoria obj = _service.fromDto(objDto);
		obj.setId(id);
		_service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		_service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
