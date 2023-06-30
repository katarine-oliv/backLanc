package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Funcionario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDAO {

    private String sql;
    private PreparedStatement preparedStatement;
    private Statement stmt;
    private ResultSet resultSet;
    private String status;


    public Funcionario getUsuariobyemail(int id_func) {
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM funcionario WHERE id_func = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_func);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setNome_func(this.resultSet.getString("nome_func"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setEmail_func(this.resultSet.getString("email_func"));
                funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));

                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Funcionario> getUsers(){
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT id_func, nome_func, cargo, email_func, senha FROM funcionario";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setNome_func(this.resultSet.getString("nome_func"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setEmail_func(this.resultSet.getString("email_func"));
                funcionario.setSenha(this.resultSet.getString("senha"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    public Funcionario setUser(Funcionario funcionario) {
        try (Connection connection = new ConectaDBPostgres().getConexao()) {
            this.sql = "INSERT INTO funcionario (nome_func, cargo, email_func, senha) VALUES (?,?,?,?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, funcionario.getNome_func());
            this.preparedStatement.setString(2, funcionario.getCargo());
            this.preparedStatement.setString(3, funcionario.getEmail_func());
            this.preparedStatement.setString(4, funcionario.getSenha());

            this.preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    public Funcionario getUser(int id_func) {
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM funcionario WHERE id_func = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_func);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId_func(this.resultSet.getInt("id_func"));
                funcionario.setNome_func(this.resultSet.getString("nome_func"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setEmail_func(this.resultSet.getString("email_func"));
                funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));

                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Funcionario editarFuncionario(Funcionario funcionario){
        try(Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "UPDATE funcionario SET nome_func=?, cargo=?, email_func=?, senha=? WHERE id_func= ?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setString(1, funcionario.getNome_func());
            this.preparedStatement.setString(2, funcionario.getCargo());
            this.preparedStatement.setString(3, funcionario.getEmail_func());
            this.preparedStatement.setString(4, funcionario.getSenha());
            this.preparedStatement.setInt(5, funcionario.getId_func());
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
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
                funcionario.setId_func(id);
                funcionario.setNome_func(this.resultSet.getString("nome_func"));
                funcionario.setCargo(this.resultSet.getString("cargo"));
                funcionario.setEmail_func(this.resultSet.getString("email_func"));
                funcionario.setSenha(this.resultSet.getString("senha"));
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status= "error";
        }

        return funcionario;
    }

    public Funcionario excluirFuncionario(int id){
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql= "DELETE FROM funcionario WHERE id_func=?";
            this.preparedStatement= connection.prepareStatement(sql);
            this.preparedStatement.setInt(1, id);
            this.preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public Funcionario funcionarioId (int id){
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
    }*/



}
