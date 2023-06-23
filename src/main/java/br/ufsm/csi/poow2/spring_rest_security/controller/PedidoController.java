package br.ufsm.csi.poow2.spring_rest_security.controller;
import br.ufsm.csi.poow2.spring_rest_security.dao.PedidoDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Pedido;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("pedido")
public class PedidoController {
    @GetMapping("/pedidos")
    public ArrayList<Pedido> getPedidos(){
        return new PedidoDAO().getPedidos();
    }

    @GetMapping("/pedido/{id}")
    public Pedido getPedido(@PathVariable int id){
        return new PedidoDAO().getPedido(id);
    }

    @PostMapping("/save")
    public Pedido setPedido(@RequestBody Pedido pedido) throws SQLException {
        return new PedidoDAO().setPedido(pedido);
    }

    @PutMapping("/editar")
    public void editarPedido(@RequestBody Pedido pedido){
        new PedidoDAO().editarPedido(pedido);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirPedido(@PathVariable("id") int id){
        new PedidoDAO().excluirPedido(id);
    }

}
