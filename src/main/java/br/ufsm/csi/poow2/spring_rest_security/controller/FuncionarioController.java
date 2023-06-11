package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.FuncionarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Funcionario;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @GetMapping("/funcionarios")
    public ArrayList<Funcionario> getFuncionarios(){
        return new FuncionarioDAO().getFuncionarios();
    }

    @GetMapping("/funcionario/{id}")
    public Funcionario getfuncionario(@PathVariable int id){
        return new FuncionarioDAO().buscarFuncionario(id);
    }

    @PostMapping("/save")
    public Funcionario setFuncionarios(@RequestBody Funcionario funcionario) throws SQLException {
        return new FuncionarioDAO().setFuncionario(funcionario);

    }

    @PutMapping("/editar")
    public void editarFuncionario(@RequestBody Funcionario funcionario){
        new FuncionarioDAO().editarFuncionario(funcionario);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirFuncionario(@PathVariable("id") int id){
        new FuncionarioDAO().excluirFuncionario(id);
    }


}
