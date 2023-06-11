package br.ufsm.csi.poow2.spring_rest_security.dao;


import br.ufsm.csi.poow2.spring_rest_security.model.Produto;

import java.sql.*;
import java.util.ArrayList;


public class ProdutoDAO {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;


    public Produto getProduto(int id_produto){
        Produto produto = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM produto WHERE id_produto = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_produto);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                produto = new Produto();
                produto.setId(resultSet.getInt("id_produto"));
                produto.setNome(resultSet.getString("nome_produto"));
                produto.setValor(resultSet.getFloat("valor_produto"));
                produto.setDescricao(resultSet.getString("descricao"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    public Produto setProduto(Produto p) throws SQLException{
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO produto (nome_produto, valor_produto, descricao) VALUES (?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, p.getNome());
            this.preparedStatement.setFloat(2, p.getValor());
            this.preparedStatement.setString(3, p.getDescricao());
            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }



   /* public Produto setProduto(Produto p) throws SQLException{

        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "INSERT INTO produto (nome_produto, valor_produto, descricao) VALUES (?, ?, ?)";
            System.out.println(p.getNome());
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, p.getNome());
            this.preparedStatement.setFloat(2, p.getValor());
            this.preparedStatement.setString(3,p.getDescricao());
            this.preparedStatement.execute();
        }
        return p;
    }*/

    public ArrayList<Produto> getProdutos(){
        ArrayList<Produto> produtos = new ArrayList<>();
        try(Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "SELECT * FROM produto";
            this.stmt = connection.createStatement();
            this.resultSet = this.stmt.executeQuery(sql);

            while (this.resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(this.resultSet.getInt("id_produto"));
                produto.setNome(this.resultSet.getString("nome_produto"));
                produto.setValor(this.resultSet.getFloat("valor_produto"));
                produto.setDescricao(this.resultSet.getString("descricao"));
                produtos.add(produto);

            }

        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return produtos;
    }

    public String editarProduto(Produto produto){
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "UPDATE produto SET nome_produto=?, valor_produto=?, descricao=? WHERE id_produto::integer = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, produto.getNome());
            this.preparedStatement.setFloat(2, produto.getValor());
            this.preparedStatement.setString(3, produto.getDescricao());
            this.preparedStatement.setInt(4, produto.getId());
            this.preparedStatement.executeUpdate();
            if(this.preparedStatement.getUpdateCount() > 0){
                this.status= "Produto editado com sucesso!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.status;
    }

    public Produto buscarProduto(int id){
        Produto produto= null;
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM produto WHERE id_produto= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet= this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                produto = new Produto();
                produto.setId(id);
                produto.setNome(this.resultSet.getString("nome_produto"));
                produto.setValor(this.resultSet.getFloat("valor_produto"));
                produto.setDescricao(this.resultSet.getString("descricao"));

            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status= "error";
        }

        return produto;
    }

    public void excluirProduto(int n){
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "DELETE FROM produto WHERE id_produto=?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, n);
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addProduto(Produto produto) {
    }

    public Produto recuperar(int id){
        Produto produto = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM produto WHERE id_produto = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                produto = new Produto();
                produto.setId(resultSet.getInt("id_produto"));
                produto.setNome(resultSet.getString("nome_produto"));
                produto.setValor(resultSet.getFloat("valor_produto"));
                produto.setDescricao(resultSet.getString("descricao"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    public Produto pegarId (int id){
        Produto produto = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM produto WHERE id_produto = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                produto = new Produto();
                produto.setId(resultSet.getInt("id_produto"));
                produto.setNome(resultSet.getString("nome_produto"));
                produto.setValor(resultSet.getFloat("valor_produto"));
                produto.setDescricao(resultSet.getString("descricao"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }
}
