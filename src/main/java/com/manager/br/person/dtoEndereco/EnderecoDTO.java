package com.manager.br.person.dtoEndereco;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private String cep;
    private Long numero;
    private String cidade;
    private boolean principal;
    @JsonBackReference
    private PessoaDTO pessoa;

}

