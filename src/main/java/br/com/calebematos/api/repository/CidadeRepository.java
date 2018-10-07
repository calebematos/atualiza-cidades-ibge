package br.com.calebematos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.calebematos.api.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
