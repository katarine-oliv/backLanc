package br.ufsm.csi.poow2.spring_rest_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAutenticacao(AuthenticationManagerBuilder builder) throws Exception{
        System.out.println("Configurando o AuthenticationManager...");
        builder.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public FiltroAutenticacao filtroAutenticacao() throws Exception{
        return new FiltroAutenticacao();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
        //        http
        .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
              //  .authenticationProvider(this.authProvider())
                .authorizeHttpRequests()
                //.antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()


                //Clientes
                .antMatchers(HttpMethod.GET,"/cliente").hasAnyAuthority("administrador", "funcionario")
                .antMatchers(HttpMethod.POST,"/cliente/save").hasAnyAuthority("administrador", "funcionario")
                .antMatchers(HttpMethod.GET, "/cliente/clientes").hasAnyAuthority("administrador", "funcionario")

                //Produtos
                .antMatchers(HttpMethod.GET,"/produto").hasAnyAuthority("administrador", "funcionario")
                .antMatchers(HttpMethod.POST,"/produto/save").hasAnyAuthority("administrador", "funcionario")
                .antMatchers(HttpMethod.GET, "/produto/produtos").hasAnyAuthority("administrador", "funcionario")

                //Pedidos
                .antMatchers(HttpMethod.GET,"/pedido").hasAnyAuthority("administrador", "funcionario")
                .antMatchers(HttpMethod.POST,"/pedido/save").hasAnyAuthority("administrador", "funcionario")
                .antMatchers(HttpMethod.GET, "/pedido/pedidos").hasAnyAuthority("administrador", "funcionario")

                //Funcionarios
                .antMatchers(HttpMethod.GET, "/funcionario/ver/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/funcionario").hasAnyAuthority("administrador")
                .antMatchers(HttpMethod.GET, "/funcionario/funcionarios").hasAnyAuthority("administrador")
                .antMatchers(HttpMethod.POST,"/funcionario/save").hasAnyAuthority("administrador")
                .antMatchers(HttpMethod.PUT, "/funcionario/editar").hasAnyAuthority("administrador")
                .antMatchers(HttpMethod.DELETE, "/funcionario/excluir/{id}").hasAnyAuthority("administrador");

            http.addFilterBefore(this.filtroAutenticacao(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public CorsFilter corsFilter() {
        System.out.println("Configurando cors...");
        final var config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }


}

