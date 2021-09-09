package com.ramon.servico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO {
       Connection con = null;

       public UserDAO() throws SQLException, InstantiationException,
        IllegalAccessException, ClassNotFoundException {
             String url = "jdbc:mysql://localhost:3306/WsJerseyJsonMavenJava";
             String user = "root";
             String password = "";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
             con = DriverManager.getConnection(url, user, password);
       }

       public void closeConnection() throws SQLException {
             con.close();
       }

       public void updateUser(User user) {
           try {
              PreparedStatement preparedStatement = con
              .prepareStatement("update user set user_name=?, password=?, address=?, email=? where id=?");
                  
                  preparedStatement.setString(1, user.getUserName());
                  preparedStatement.setString(2, user.getPassword());
                  preparedStatement.setString(3, user.getAddress());
                  preparedStatement.setString(4, user.getEmail());
                  preparedStatement.setInt(5, user.getId());
                  preparedStatement.execute();
           } catch (SQLException ex) {
                  Logger lgr = Logger.getLogger(UserDAO.class.getName());
                  lgr.log(Level.SEVERE, ex.getMessage(), ex);
           }
       }
       
       public void insertUser(User user) {
             try {
                PreparedStatement preparedStatement = con
                .prepareStatement("insert user (id, user_name, password, address, email) values(?,?,?,?,?)");
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setString(2, user.getUserName());
                    preparedStatement.setString(3, user.getPassword());
                    preparedStatement.setString(4, user.getAddress());
                    preparedStatement.setString(5, user.getEmail());
                    preparedStatement.execute();
             } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(UserDAO.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
             }
       }

       public User getUser(int id) {

             User user = null;
             try {

                    PreparedStatement preparedStatement = con
                     .prepareStatement("select * from user where id = ?");
                     preparedStatement.setInt(1, id);

                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()) {

                           user = new User();
                           user.setId(rs.getInt(1));
                           user.setUserName(rs.getString(2));
                           user.setPassword(rs.getString(3));
                           user.setAddress(rs.getString(4));
                           user.setEmail(rs.getString(5));

                    }

             } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(UserDAO.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);

             }
             return user;
       }

       public void deleteUser(int id) {

             try {

                    PreparedStatement preparedStatement = con
                       .prepareStatement("delete from user where id = ?");
                    preparedStatement.setInt(1, id);

                    preparedStatement.execute();

             } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(UserDAO.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);

             }
       }
}
