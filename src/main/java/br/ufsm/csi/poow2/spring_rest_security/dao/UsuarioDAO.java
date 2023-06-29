package br.ufsm.csi.poow2.spring_rest_security.dao;

import br.ufsm.csi.poow2.spring_rest_security.model.Cliente;
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

            Usuario usuario = null;

        try (Connection connection = new ConectaDBPostgres().getConexao()){
            this.sql = "SELECT * FROM usuario WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, username);
            this.resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()){
                usuario = new Usuario();
                usuario.setLogin(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setPermissao(resultSet.getString("permissao"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
