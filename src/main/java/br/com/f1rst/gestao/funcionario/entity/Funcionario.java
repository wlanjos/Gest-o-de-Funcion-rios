package br.com.f1rst.gestao.funcionario.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String funcao;
    private BigDecimal salario;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "telefones_id")
    private Telefone telefones;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "endereco_id")
    private Endereco Endereco;
}
