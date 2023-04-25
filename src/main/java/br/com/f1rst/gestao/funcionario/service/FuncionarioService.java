package br.com.f1rst.gestao.funcionario.service;

import br.com.f1rst.gestao.funcionario.entity.Endereco;
import br.com.f1rst.gestao.funcionario.entity.Funcionario;
import br.com.f1rst.gestao.funcionario.entity.Telefone;
import br.com.f1rst.gestao.funcionario.model.FuncionarioDTO;
import br.com.f1rst.gestao.funcionario.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;

    private ModelMapper modelMapper = new ModelMapper();
    public ResponseEntity create(FuncionarioDTO request) {

        Funcionario funcionario = Funcionario.builder()
                .nome(request.getNome())
                .funcao(request.getFuncao())
                .salario(request.getSalario())
                .telefones(Telefone.builder()
                        .tipo(request.getTelefones().getTipo())
                        .ddd(request.getTelefones().getDdd())
                        .numero(request.getTelefones().getNumero())
                        .build())
                .Endereco(Endereco.builder()
                        .tipo(request.getEndereco().getTipo())
                        .logradouro(request.getEndereco().getLogradouro())
                        .cep(request.getEndereco().getCep())
                        .build())
                .build();
        repository.save(funcionario);

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    public ResponseEntity findByid(Long id) {
        Optional<Funcionario> func = repository.findById(id);
        if (func.isEmpty()) {
            return new ResponseEntity<>("Funcionario não encontrado", HttpStatus.UNPROCESSABLE_ENTITY);
        }else {

            FuncionarioDTO response = modelMapper.map(func.get(), FuncionarioDTO.class);
            return new ResponseEntity<>(response, HttpStatus.OK);}
    }
    public ResponseEntity updatefuncionario(Long id, FuncionarioDTO request) {
        Optional<Funcionario> func = repository.findById(id);
        if (func.isEmpty()) {
            return new ResponseEntity<>("Funcionario não encontrado", HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            Funcionario funcionario = func.get();

            funcionario.setNome(request.getNome().isBlank() ? funcionario.getNome() : request.getNome());

            repository.save(funcionario);

            FuncionarioDTO response = modelMapper.map(funcionario, FuncionarioDTO.class);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    public ResponseEntity deleteFuncionario(Long id) {
        Optional<Funcionario> func = repository.findById(id);
        if (func.isEmpty()) {
            return new ResponseEntity<>("Funcionario não encontrado", HttpStatus.UNPROCESSABLE_ENTITY);
        }else {
            repository.deleteById(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        }

    }
}
