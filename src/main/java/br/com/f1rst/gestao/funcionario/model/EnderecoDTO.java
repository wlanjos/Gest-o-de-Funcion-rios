package br.com.f1rst.gestao.funcionario.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class EnderecoDTO {

    private Long id;
    @NotBlank(message = "Campo deve ser informado")
    private String tipo;
    @NotBlank(message = "Campo deve ser informado")
    private String logradouro;
    @NonNull
    private Long cep;

}
