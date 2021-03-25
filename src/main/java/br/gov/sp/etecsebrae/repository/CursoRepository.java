package br.gov.sp.etecsebrae.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.etecsebrae.entity.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

	default Optional<CursoEntity> findBySigla(String sigla) {
		return this.findAll().stream().filter(entity -> sigla.equalsIgnoreCase(entity.getSigla())).findAny();
	}

}
