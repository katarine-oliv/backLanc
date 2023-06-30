package br.ufsm.csi.poow2.spring_rest_security.security;

import br.ufsm.csi.poow2.spring_rest_security.dao.FuncionarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Funcionario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Funcionario funcionario= new FuncionarioDAO().getUser(Integer.parseInt(username));
        System.out.println("detailservice"+funcionario.getEmail_func());
        System.out.println("detailservice"+funcionario.getCargo());

        if (funcionario == null){
            throw new UsernameNotFoundException("Usuário ou senha incorretos!");
        }else {
            UserDetails user = User.withUsername(funcionario.getEmail_func()).password(funcionario.getSenha()).authorities(funcionario.getCargo()).build();
            return user;
        }

    }


}
