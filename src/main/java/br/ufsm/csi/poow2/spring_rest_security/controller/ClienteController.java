package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.ClienteDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    @GetMapping("/clientes")
    public ArrayList<Cliente> getClientes(){
        return new ClienteDAO().getClientes();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getcliente(@PathVariable int id){
        return new ClienteDAO().buscarCliente(id);
    }

    @PostMapping("/save")
    public Cliente setClientes(@RequestBody Cliente cliente) throws SQLException {
        return new ClienteDAO().setCliente(cliente);

    }

    @PutMapping("/editar")
    public void editarCliente(@RequestBody Cliente cliente){
        new ClienteDAO().editarCliente(cliente);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirCliente(@PathVariable("id") int id){
        new ClienteDAO().excluirCliente(id);
    }

}
