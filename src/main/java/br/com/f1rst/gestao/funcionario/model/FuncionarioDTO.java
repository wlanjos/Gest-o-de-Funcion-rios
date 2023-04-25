package br.com.f1rst.gestao.funcionario.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {


    private Long id;
    @NotBlank(message = "Campo deve ser informado")
    private String nome;
    @NotBlank(message = "Campo deve ser informado")
    private String funcao;
    @NonNull
    private BigDecimal salario;
    @NonNull
    private TelefoneDTO telefones;
    @NonNull
    private EnderecoDTO Endereco;
}
