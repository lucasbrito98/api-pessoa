package com.manager.br.person.service;

import com.manager.br.person.entity.Pessoa;
import com.manager.br.person.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {

        pessoaRepository.save(pessoa);
        pessoa.getEnderecoList().stream()
                .forEach(endereco -> endereco.setPessoa(pessoa));
        return pessoa;
    }

    public List<Pessoa> listaPessoa() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public void removerPorId(Long id) {
        pessoaRepository.deleteById(id);

    }

}

