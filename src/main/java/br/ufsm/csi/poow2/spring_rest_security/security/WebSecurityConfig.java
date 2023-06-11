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
    public void configureAutenticacao(AuthenticationManagerBuilder auth) throws Exception{
        System.out.println("Configurando o AuthenticationManager ****");
        auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
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
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                .antMatchers(HttpMethod.GET,"/cliente").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/cliente/save").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/cliente/clientes").hasAnyAuthority("USER", "ADMIN")

                .antMatchers(HttpMethod.GET,"/produto").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/produto/save").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/produto/produtos").hasAnyAuthority("USER", "ADMIN")

                .antMatchers(HttpMethod.GET,"/pedido").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/pedido/save").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/pedido/pedidos").hasAnyAuthority("USER", "ADMIN")

                .antMatchers(HttpMethod.GET,"/funcionario/funcionario").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST,"/funcionario/save").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/funcionario/funcionarios").hasAnyAuthority("ADMIN");

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
