package com.manager.br.person.service;

import com.manager.br.person.dtoEndereco.PessoaDTO;
import com.manager.br.person.entity.Pessoa;

import java.util.List;
import java.util.Optional;


public interface PessoaService {

    Pessoa salvar(PessoaDTO pessoa);

    List<PessoaDTO> listaPessoa();

    PessoaDTO buscarPorId(Long id);

    void removerPorId(Long id);

    void atualizarPessoa(Long id, PessoaDTO pessoa);
}
