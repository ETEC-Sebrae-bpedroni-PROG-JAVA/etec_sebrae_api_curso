package br.gov.sp.etecsebrae.controller;

import java.util.List;

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

import br.gov.sp.etecsebrae.dto.CursoDto;
import br.gov.sp.etecsebrae.entity.CursoEntity;
import br.gov.sp.etecsebrae.repository.CursoRepository;

@RestController
@RequestMapping({ "/curso" })
public class CursoController {

	@Autowired
	CursoRepository repository;

	@GetMapping(path = { "", "/get_all", "/all" })
	public ResponseEntity<?> findAll() {
		List<CursoEntity> list = repository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(path = { "/{id}", "/get_id/{id}", "/id/{id}" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(repository.findById(id).orElse(null));
	}

	@GetMapping(path = { "/get/{sigla}", "/get_sigla/{sigla}", "/sigla/{sigla}" })
	public ResponseEntity<?> findBySigla(@PathVariable String sigla) {
		return ResponseEntity.ok(repository.findBySigla(sigla).orElse(null));
	}

	@PostMapping(path = { "", "/add", "/create" }, consumes = "application/json", produces = "application/json")
	public CursoEntity create(@RequestBody CursoDto dto) {
		CursoEntity entity = new CursoEntity(dto.getNome(), dto.getSigla());
		return repository.save(entity);
	}

	@PutMapping(path = { "", "/edit", "/update" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody CursoDto dto) {
		return repository.findBySigla(dto.getSigla()).map(record -> {
			record.setNome(dto.getNome());
			CursoEntity updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "", "/remove", "/delete" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> delete(@RequestBody CursoDto dto) {
		return repository.findBySigla(dto.getSigla()).map(record -> {
			repository.deleteById(record.getId());
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
