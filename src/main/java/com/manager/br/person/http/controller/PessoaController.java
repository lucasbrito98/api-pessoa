package com.manager.br.person.http.controller;

import com.manager.br.person.dtoEndereco.PessoaDTO;
import com.manager.br.person.entity.Pessoa;
import com.manager.br.person.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa salvar(@RequestBody PessoaDTO pessoa) {
        return pessoaService.salvar(pessoa);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PessoaDTO> listaPessoa() {
        return pessoaService.listaPessoa();
    }

    @GetMapping("/{id}")
    public PessoaDTO buscarPessoaPorId(@PathVariable("id") Long id) {
        return pessoaService.buscarPorId(id);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerPessoa(@PathVariable("id") Long id) {

                    pessoaService.removerPorId(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPessoa(@PathVariable("id") Long id, @RequestBody PessoaDTO pessoa) {

        pessoaService.atualizarPessoa(id,pessoa);

    }
}


