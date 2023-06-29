package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;


    public Cliente getCliente(int id_cliente){
        Cliente cliente = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM cliente WHERE id_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_cliente);
            this.resultSet = this.preparedStatement.executeQuery();

            System.out.println(this.sql);

            while (resultSet.next()){
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("id_cliente"));
                cliente.setNome(resultSet.getString("nome_cliente"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setCpf(resultSet.getString("cpf"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }
    public Cliente setCliente(Cliente c) throws SQLException{

        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "INSERT INTO cliente (nome_cliente, telefone, email, cpf) VALUES (?, ?, ?, ?)";
            System.out.println(c.getNome());
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, c.getNome());
            this.preparedStatement.setString(2, c.getTelefone());
            this.preparedStatement.setString(3, c.getEmail());
            this.preparedStatement.setString(4, c.getCpf());
            this.preparedStatement.execute();
        }
        return c;
    }

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try(Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "SELECT * FROM cliente";
            this.stmt = connection.createStatement();
            this.resultSet = this.stmt.executeQuery(sql);

            while (this.resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(this.resultSet.getInt("id_cliente"));
                cliente.setNome(this.resultSet.getString("nome_cliente"));
                cliente.setTelefone(this.resultSet.getString("telefone"));
                cliente.setEmail(this.resultSet.getString("email"));
                cliente.setCpf(this.resultSet.getString("cpf"));
                clientes.add(cliente);

            }

        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }

    public String editarCliente(Cliente cliente){
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "UPDATE cliente SET nome_cliente=?, telefone=?, email=?, cpf=? WHERE id_cliente= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setString(1, cliente.getNome());
            this.preparedStatement.setString(2, cliente.getTelefone());
            this.preparedStatement.setString(3, cliente.getEmail());
            this.preparedStatement.setString(4,cliente.getCpf());
            this.preparedStatement.setInt(5, cliente.getId());
            this.preparedStatement.executeUpdate();
            if(this.preparedStatement.getUpdateCount() > 0){
                this.status= "Cliente editado com sucesso!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.status;
    }

    public Cliente buscarCliente(int id){
        Cliente cliente= null;
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM cliente WHERE id_cliente= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet= this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(this.resultSet.getString("nome_cliente"));
                cliente.setTelefone(this.resultSet.getString("telefone"));
                cliente.setEmail(this.resultSet.getString("email"));
                cliente.setCpf(this.resultSet.getString("cpf"));
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status= "error";
        }

        return cliente;
    }

    public String excluirCliente(int id){
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "DELETE FROM cliente WHERE id_cliente=?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status="Cliente excluído!";
    }

    public Cliente clienteId (int id){
       Cliente cliente = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM cliente WHERE id_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                cliente = new Cliente();
                cliente .setId(resultSet.getInt("id_cliente"));
                cliente .setNome(resultSet.getString("nome_cliente"));
                cliente .setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setCpf(resultSet.getString("cpf"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }

}
