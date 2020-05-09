package sm.deeplink.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import sm.deeplink.service.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static sm.deeplink.mapper.Constants.HEADER_STRING;
import static sm.deeplink.mapper.Constants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        String name = null;
        String authToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX,"");
            try {
                name = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(name);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + name + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}