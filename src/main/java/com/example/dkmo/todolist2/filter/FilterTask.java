package com.example.dkmo.todolist2.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.dkmo.todolist2.users.UsersRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class FilterTask extends OncePerRequestFilter {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var servlet = request.getServletPath();
                if(servlet.startsWith("/task/")){
                    var authorization = request.getHeader("Authorization");
       var auth_enconded =authorization.substring("Basic".length()).trim();
       byte[] decode = Base64.getDecoder().decode(auth_enconded);
       String user_pass = new String(decode);
       String[] user_passNew=user_pass.split(":");
       String user = user_passNew[0];
       String password = user_passNew[1];
       var username=usersRepository.findByUsername(user);
       if(username==null){
    response.sendError(401);   
    }else{
      var result =  BCrypt.verifyer().verify(password.toCharArray(),username.getPassword());
        if(result.verified){
            request.setAttribute("idUser", username.getId());
            filterChain.doFilter(request, response);

        }else{
            response.sendError(401);
        }
                
    }
    }else{
      filterChain.doFilter(request, response);  
    }
}
       
    
}

