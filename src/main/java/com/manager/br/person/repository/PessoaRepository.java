package com.manager.br.person.repository;

import com.manager.br.person.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository <Pessoa, Long >{

}
