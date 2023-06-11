package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Pedido;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDAO {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;


    public Pedido getPedido(int id){
        Pedido pedido = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM pedido WHERE id_pedido = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                pedido = new Pedido();
                pedido.setId(resultSet.getInt("id_pedido"));
                pedido.setQuantidade(resultSet.getInt("quantidade"));
                pedido.setCliente(new ClienteDAO().getCliente(resultSet.getInt("id_cliente")));
                pedido.setProduto(new ProdutoDAO().getProduto(resultSet.getInt("id_produto")));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return pedido;
    }
    public Pedido setPedido(Pedido pe) throws SQLException{

        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "INSERT INTO pedido (id_cliente, id_produto, quantidade) VALUES (?, ?, ?)";
            System.out.println(pe.getId());
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, pe.getCliente().getId());
            this.preparedStatement.setInt(2,pe.getProduto().getId());
            this.preparedStatement.setInt(3, pe.getQuantidade());
            this.preparedStatement.execute();
        }
        return pe;
    }

    public ArrayList<Pedido> getPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try(Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "SELECT * FROM pedido";
            this.stmt = connection.createStatement();
            this.resultSet = this.stmt.executeQuery(sql);

            while (this.resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getInt("id_pedido"));
                pedido.setQuantidade(resultSet.getInt("quantidade"));
                pedido.setCliente(new ClienteDAO().getCliente(resultSet.getInt("id_cliente")));
                pedido.setProduto(new ProdutoDAO().getProduto(resultSet.getInt("id_produto")));
                pedidos.add(pedido);

            }

        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return pedidos;
    }

    public String editarPedido(Pedido pedido){
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "UPDATE pedido SET id_cliente=?, id_produto=?, quantidade=? WHERE id_pedido= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, pedido.getCliente().getId());
            this.preparedStatement.setInt(2, pedido.getProduto().getId());
            this.preparedStatement.setInt(3, pedido.getQuantidade());
            this.preparedStatement.setInt(4, pedido.getId());
            this.preparedStatement.executeUpdate();
            if(this.preparedStatement.getUpdateCount() > 0){
                this.status= "Pedido editado com sucesso!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.status;
    }

//    public Pedido buscarPedido(int id){
//        Pedido pedido= null;
//        try(Connection connection = new ConectaDBPostgres().getConexao()){
//            this.sql = "SELECT * FROM pedido WHERE id_pedido= ?";
//            this.preparedStatement= connection.prepareStatement(sql);
//            this.preparedStatement.setInt(1, id);
//            this.resultSet= this.preparedStatement.executeQuery();
//            while(this.resultSet.next()){
//                pedido = new Pedido();
//                pedido.setId(id);
//                pedido.setCliente(this.resultSet.getInt("id_cliente"));
//                pedido.setItem(this.resultSet.getString("id_item"));
//                pedido.setTotal(this.resultSet.getFloat("total_pedido"));
//
//            }
//
//        }catch (SQLException e){
//            e.printStackTrace();
//            this.status= "error";
//        }
//
//        return pedido;
//    }

    public void excluirPedido(int id){
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "DELETE FROM pedido WHERE id_pedido=?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
