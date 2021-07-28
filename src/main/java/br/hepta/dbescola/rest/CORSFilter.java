package br.hepta.dbescola.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {
    
    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res){
        res.getHeaders().add("Access-Control-Allow-Origin", "*");
        res.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");   
        res.getHeaders().add("Access-Control-Allow-Credentials", "true");
        res.getHeaders().add("Access-Control-Allow-Methods",
                "DELETE, GET, POST, PUT, OPTIONS, HEAD, PATCH");
    }
}