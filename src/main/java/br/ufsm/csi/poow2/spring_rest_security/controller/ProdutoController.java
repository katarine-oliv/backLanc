package br.ufsm.csi.poow2.spring_rest_security.controller;


import br.ufsm.csi.poow2.spring_rest_security.dao.ProdutoDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @GetMapping("/produtos")
    public ArrayList<Produto> getProdutos(){
        return new ProdutoDAO().getProdutos();
    }

    @GetMapping("/produto/{id}")
    public Produto getProduto(@PathVariable int id){
        return new ProdutoDAO().buscarProduto(id);
    }

    @PostMapping("/save")
    public Produto setProdutos(@RequestBody Produto produto) throws SQLException{
        return new ProdutoDAO().setProduto(produto);
    }

    @PutMapping("/editar")
    public void editarProduto(@RequestBody Produto produto){
        new ProdutoDAO().editarProduto(produto);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirProduto(@PathVariable("id") int id){
        new ProdutoDAO().excluirProduto(id);
    }

}
