package br.gov.sp.etecsebrae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.etecsebrae.dto.CursoDto;
import br.gov.sp.etecsebrae.entity.CursoEntity;
import br.gov.sp.etecsebrae.repository.CursoRepository;

@RestController
@RequestMapping({ "/curso" })
public class CursoController {

	@Autowired
	CursoRepository repository;

	@GetMapping(path = "/get_all")
	public ResponseEntity<?> findAll() {
		List<CursoEntity> list = repository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = "/get/{sigla}")
	public ResponseEntity<?> findByLogin(@PathVariable String sigla) {
		return ResponseEntity.ok(repository.findAll().stream()
				.filter(entity -> sigla.equalsIgnoreCase(entity.getSigla())).findAny().orElse(null));
	}

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public CursoEntity create(@RequestBody CursoDto dto) {
		CursoEntity entity = new CursoEntity(dto.getNome(), dto.getSigla());
		return repository.save(entity);
	}
}
