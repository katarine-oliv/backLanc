package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioDAO {

    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Usuario getUsuario(String username) {
        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM usuario WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, username);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                String login = this.resultSet.getString("email");
                String senha = this.resultSet.getString("senha");
                String permissao = "ADMIN";

                Usuario user = new Usuario(login, new BCryptPasswordEncoder().encode(senha), permissao);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
