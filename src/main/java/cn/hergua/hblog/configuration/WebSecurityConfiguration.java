package cn.hergua.hblog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @version : 1.0
 * @auther Hergua | Mr.hergua
 * @DATE : 2018/5/5
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/","/blog/**","/tag/**","friend","/login/**","/druid/**").permitAll()
                .antMatchers("/admin/**").authenticated()
                .and().rememberMe().tokenValiditySeconds(3600)
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/admin/article/list").permitAll()
                .and().logout().permitAll();


    }

    /**
     * 配置security忽略掉对于资源问价请求的拦截
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**/*.*");
    }


    /**
     * 设置security对于用户的验证使用数据库内的内容
     * @param auth 用户权限
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider();
    }

}