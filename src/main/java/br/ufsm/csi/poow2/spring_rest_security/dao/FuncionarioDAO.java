package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Funcionario;

import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDAO {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;


    public Funcionario getFuncionario(int id_func){
        Funcionario funcionario = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM funcionario WHERE id_func = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_func);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_func"));
                funcionario.setNome(resultSet.getString("nome_func"));
                funcionario.setCargo(resultSet.getString("cargo"));
                funcionario.setEmail(resultSet.getString("email_func"));
                funcionario.setSenha(resultSet.getString("senha"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return funcionario;
    }
    public Funcionario setFuncionario(Funcionario f) throws SQLException{

        try (Connection connection = new ConectaDBPostgres().getConexao()) {

            this.sql = "INSERT INTO funcionario (nome_func, cargo, email_func, senha) VALUES (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, f.getNome());
            this.preparedStatement.setString(2, f.getCargo());
            this.preparedStatement.setString(3, f.getEmail());
            this.preparedStatement.setString(4, f.getSenha());
            this.preparedStatement.execute();
        }
        return f;
    }

    public ArrayList<Funcionario> getFuncionarios(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try(Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "SELECT * FROM funcionario";
            this.stmt = connection.createStatement();
            this.resultSet = this.stmt.executeQuery(sql);

            while (this.resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(this.resultSet.getInt("id_func"));
                funcionario.setNome(this.resultSet.getString("nome_func"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setEmail(this.resultSet.getString("email_func"));
                funcionario.setSenha(this.resultSet.getString("senha"));
                funcionarios.add(funcionario);

            }

        }

        catch(SQLException e){
            e.printStackTrace();
        }

        return funcionarios;
    }

    public String editarFuncionario(Funcionario funcionario){
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "UPDATE funcionario SET nome_func=?, cargo=?, email_func=?, senha=? WHERE id_func= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setString(1, funcionario.getNome());
            this.preparedStatement.setString(2, funcionario.getCargo());
            this.preparedStatement.setString(3, funcionario.getEmail());
            this.preparedStatement.setString(4, funcionario.getSenha());
            this.preparedStatement.setInt(5, funcionario.getId());
            this.preparedStatement.executeUpdate();
            if(this.preparedStatement.getUpdateCount() > 0){
                this.status= "Funcionario editado com sucesso!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.status;
    }

    public Funcionario buscarFuncionario(int id){
        Funcionario funcionario= null;
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM funcionario WHERE id_func= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet= this.preparedStatement.executeQuery();
            while(this.resultSet.next()){
                funcionario = new Funcionario();
                funcionario.setId(id);
                funcionario.setNome(this.resultSet.getString("nome_cliente"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setEmail(this.resultSet.getString("email_func"));
                funcionario.setSenha(this.resultSet.getString("senha"));
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status= "error";
        }

        return funcionario;
    }

    public String excluirFuncionario(int id){
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "DELETE FROM funcionario WHERE id_func=?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status="Funcionario excluído!";
    }

    public Funcionario funcionarioId (int id){
        Funcionario funcionario = null;


        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = " SELECT * FROM funcionario WHERE id_func = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (resultSet.next()){
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_func"));
                funcionario.setNome(resultSet.getString("nome_func"));
                funcionario.setCargo(resultSet.getString("cargo"));
                funcionario.setEmail(resultSet.getString("email_func"));
                funcionario.setSenha(resultSet.getString("senha"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return funcionario;
    }



}
