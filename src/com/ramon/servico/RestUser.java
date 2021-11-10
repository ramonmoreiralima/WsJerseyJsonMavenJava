package com.ramon.servico;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
// import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("/user")
public class RestUser {
		
	 @GET
     @Path("/xml/{i}")
     @Produces(MediaType.TEXT_XML)
     public String convertInchToFeet(@PathParam("i") int i) {

       int inch=i;
       double feet = 0;
       feet =(double) inch/12;
     
       return "<InchToFeetService>"
       + "<Inch>" + inch + "</Inch>"
         + "<Feet>" + feet + "</Feet>"
        + "</InchToFeetService>";
     }

       @GET
       @Path("/html/{id}")
       @Produces(MediaType.TEXT_HTML)
       public String getUserHTML(@PathParam("id") int id)
          throws SQLException, InstantiationException, IllegalAccessException,
          ClassNotFoundException {
             UserDAO userDAO = new UserDAO();
             User user = userDAO.getUser(id);
             userDAO.closeConnection();
             return "<HTML><BODY>" + user.getUserName() +
             "</BODY></HTML>";
       }
       
       

     @GET
  	 @Path("/json/{id}")
       @Produces(MediaType.APPLICATION_JSON)
       public User getUser(@PathParam("id") int id) throws SQLException,
        InstantiationException, IllegalAccessException, ClassNotFoundException {
             UserDAO userDAO = new UserDAO();
             User user = userDAO.getUser(id);
             userDAO.closeConnection();
             return user;
       }

     
       @PUT
       @Path("/create")
       public Response createUser(User user) throws SQLException,
         InstantiationException, IllegalAccessException,
         ClassNotFoundException {
             UserDAO userDAO = new UserDAO();
             userDAO.insertUser(user);
             userDAO.closeConnection();
             return Response.status(Status.OK).build();
       }
       /*
        * Metodo de Teste para verificar se o obj json
        * esta sendo recebid. É retornado na requisicao
        * mesmo na chamada.
        * */
       @PUT
       @Path("/createTeste")
       @Produces(MediaType.APPLICATION_JSON)
       public User createUserTeste(User user) throws SQLException,
         InstantiationException, IllegalAccessException,
         ClassNotFoundException {
             //Connect connect = new Connect();
             //connect.insertUser(user);
             //connect.closeConnection();
             // return Response.status(Status.OK).build();
    	   return user;
       }
       
       /*
        * Metodo usado para editar os registros
        * */
       @PUT
       @Path("/edit")
       public Response updateUser(User user) throws SQLException,
         InstantiationException, IllegalAccessException,
         ClassNotFoundException {
             UserDAO userDAO = new UserDAO();
             userDAO.updateUser(user);
             userDAO.closeConnection();
             return Response.status(Status.OK).build();
       }
       
       /*
        * Metodo usado para deletar os registros
        * */
       @DELETE
       @Path("/delete/{id}")
       public Response deleteUser(@PathParam("id") int id)
           throws SQLException, InstantiationException, IllegalAccessException,
           ClassNotFoundException {
             UserDAO userDAO = new UserDAO();
             userDAO.deleteUser(id);
             userDAO.closeConnection();
             return Response.status(Status.OK).build();
       }
}