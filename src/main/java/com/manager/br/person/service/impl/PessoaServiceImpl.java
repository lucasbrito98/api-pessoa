package com.manager.br.person.service.impl;

import com.manager.br.person.dtoEndereco.EnderecoDTO;
import com.manager.br.person.dtoEndereco.PessoaDTO;
import com.manager.br.person.entity.Endereco;
import com.manager.br.person.entity.Pessoa;
import com.manager.br.person.repository.PessoaRepository;
import com.manager.br.person.service.PessoaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private final PessoaRepository pessoaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    public Pessoa salvar(PessoaDTO pessoaDTO) {
        Pessoa pessoa = Pessoa.builder()
                .nome(pessoaDTO.getNome())
                .dataNascimento(pessoaDTO.getDataNascimento())
                .build();

        pessoaRepository.save(pessoa);
        List<Endereco> enderecoList = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : pessoaDTO.getEnderecoList()) {
            Endereco endereco = Endereco.builder()
                    .cep(enderecoDTO.getCep())
                    .numero(enderecoDTO.getNumero())
                    .cidade(enderecoDTO.getCidade())
                    .logradouro(enderecoDTO.getLogradouro())
                    .principal(enderecoDTO.isPrincipal())
                    .pessoa(pessoa)
                    .build();
            enderecoList.add(endereco);
        }

        pessoa.setEnderecoList(enderecoList);

        return pessoa;
    }

    public List<PessoaDTO> listaPessoa() {
        List<Pessoa> pessoaList = pessoaRepository.findAll();
        List<PessoaDTO> pessoaDTOList = new ArrayList<>();
        for (Pessoa pessoa : pessoaList) {
            PessoaDTO pessoaDto = PessoaDTO.builder()
                    .id(pessoa.getId())
                    .nome(pessoa.getNome())
                    .dataNascimento(pessoa.getDataNascimento())
                    .build();

            pessoaDTOList.add(pessoaDto);
            List<EnderecoDTO> enderecoList = new ArrayList<>();
            for (Endereco endereco : pessoa.getEnderecoList()) {
                EnderecoDTO enderecoDto = EnderecoDTO.builder()
                        .id(endereco.getId())
                        .cep(endereco.getCep())
                        .numero(endereco.getNumero())
                        .cidade(endereco.getCidade())
                        .logradouro(endereco.getLogradouro())
                        .principal(endereco.isPrincipal())
                        .pessoa(pessoaDto)
                        .build();
                enderecoList.add(enderecoDto);
            }

            pessoaDto.setEnderecoList(enderecoList);

        }
        return pessoaDTOList;
    }

    public PessoaDTO buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));

        PessoaDTO pessoaDto = PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataNascimento(pessoa.getDataNascimento())
                .build();

        List<EnderecoDTO> enderecoList = new ArrayList<>();
        for (Endereco endereco : pessoa.getEnderecoList()) {
            EnderecoDTO enderecoDto = EnderecoDTO.builder()
                    .id(endereco.getId())
                    .cep(endereco.getCep())
                    .numero(endereco.getNumero())
                    .cidade(endereco.getCidade())
                    .logradouro(endereco.getLogradouro())
                    .principal(endereco.isPrincipal())
                    .pessoa(pessoaDto)
                    .build();
            enderecoList.add(enderecoDto);
        }

        pessoaDto.setEnderecoList(enderecoList);


        return pessoaDto;


    }

    public void removerPorId(Long id) {

        pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoaRepository.deleteById(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao econtrado"));


    }

    @Override
    public void atualizarPessoa(Long id, PessoaDTO pessoa) {
        pessoaRepository.findById(id)
                .map(pessoaBase -> {
                    modelMapper.map(pessoa, pessoaBase);
                    pessoaRepository.save(pessoaBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao econtrado"));
    }

}

