package br.com.f1rst.gestao.funcionario.controller;

import br.com.f1rst.gestao.funcionario.model.FuncionarioDTO;
import br.com.f1rst.gestao.funcionario.service.FuncionarioService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping
    @ApiOperation(value = "Adicionar novos funcionários")
    public ResponseEntity create(@RequestBody @Valid FuncionarioDTO request){
        return service.create(request);

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obter detalhes dos funcionários existentes")
    public ResponseEntity findById(@PathVariable Long id){
        return service.findByid(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar detalhes dos funcionários existentes")
    public ResponseEntity updateFuncionario(@PathVariable Long id, @RequestBody @Valid  FuncionarioDTO request){

        return service.updatefuncionario(id, request);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluir funcionários existentes")
    public ResponseEntity deleteFuncionario(@PathVariable Long id){
        return service.deleteFuncionario(id);
    }

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExcepetion(MethodArgumentNotValidException ex){
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String errorMessagem = objectError.getDefaultMessage();
            erros.put(fieldName,errorMessagem);
        });

        return erros;
    }
}
