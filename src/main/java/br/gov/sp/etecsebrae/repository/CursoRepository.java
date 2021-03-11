package br.gov.sp.etecsebrae.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.etecsebrae.entity.CursoEntity;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

}
