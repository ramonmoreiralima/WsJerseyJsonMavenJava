package com.ramon.servico;

import java.io.Serializable;

public class User implements Serializable {

       private static final long serialVersionUID = -3842844978617110554L;

       private int id;
       private String userName;
       private String password;
       private String address;
       private String email;

       public int getId() {
             return id;
       }

       public void setId(int id) {
             this.id = id;
       }

       public String getUserName() {
             return userName;
       }

       public void setUserName(String userName) {
             this.userName = userName;
       }

       public String getPassword() {
             return password;
       }

       public void setPassword(String password) {
             this.password = password;
       }

       public String getAddress() {
             return address;
       }

       public void setAddress(String address) {
             this.address = address;
       }

       public String getEmail() {
             return email;
       }

       public void setEmail(String email) {
             this.email = email;
       }

}