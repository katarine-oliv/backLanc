package br.ufsm.csi.poow2.spring_rest_security.controller;

import br.ufsm.csi.poow2.spring_rest_security.dao.FuncionarioDAO;
import br.ufsm.csi.poow2.spring_rest_security.model.Funcionario;
import br.ufsm.csi.poow2.spring_rest_security.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Funcionario funcionario){

        System.out.println(funcionario.getEmail_func());
        System.out.println(funcionario.getSenha());
        System.out.println(funcionario.getCargo());


        try{
            Funcionario funcionario1=new FuncionarioDAO().getUsuariobyemail(funcionario.getEmail_func());
            System.out.println("O cargo pelo email:"+funcionario1.getCargo());
            System.out.println("O cargo pelo email:"+funcionario1.getId_func());

            funcionario.setCargo(funcionario1.getCargo());
            System.out.println("Mandando pra outro:"+funcionario.getCargo());
            final Authentication autenticado= this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(funcionario.getEmail_func(), funcionario.getSenha()));

            if (autenticado.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(autenticado);
                System.out.println("Autenticado!!!");

                System.out.println("Gerando token de autenticação...");
                String token =new JWTUtil().geraToken(funcionario);

                funcionario.setToken(token);
                funcionario.setSenha("");

                return new ResponseEntity<>(funcionario, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/funcionarios")
    public ArrayList<Funcionario> getUsuarios(){
        return new FuncionarioDAO().getUsers();
    }

    @GetMapping("/funcionario/{id}")
    public Funcionario getfuncionario(@PathVariable int id){
        return new FuncionarioDAO().buscarFuncionario(id);
    }

    @PostMapping("/save")
    public Funcionario saveFuncionario(@RequestBody Funcionario funcionario) {
        System.out.println("Funcionario que sera cadastrado:"+funcionario.getEmail_func());
        System.out.println("Funcionario que sera cadastrado:"+funcionario.getNome_func());
        System.out.println("Funcionario que sera cadastrado:"+funcionario.getCargo());

        return new FuncionarioDAO().setUser(funcionario);
    }

    @PutMapping("/editar")
    public editarFuncionario(@RequestBody Funcionario funcionario){
        System.out.println("Nome do funcionario editado:"+funcionario.getNome_func());
       return new FuncionarioDAO().editarFuncionario(funcionario);
    }

    @DeleteMapping("/excluir/{id}")
    public excluirFuncionario(@PathVariable("id") int id){
        return new FuncionarioDAO().excluirFuncionario(id);
    }

    @GetMapping("/funcionario")
    public Funcionario funcionario(){

        return null;
    }


}
