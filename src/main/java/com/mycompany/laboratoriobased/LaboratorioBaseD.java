package com.mycompany.laboratoriobased;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaboratorioBaseD {
    String bd="";
    Connection conexion;
    PreparedStatement ps=null;

    public LaboratorioBaseD(String bd) {
        this.bd=bd;
    }
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd,"root","");
            Statement s= conexion.createStatement();
            ps=conexion.prepareStatement("INSERT INTO persona (id, nombre, nacimiento) VALUES(?,?,?)");
            ps.setInt(1, 1);
            ps.setString(2, "Pedro");
            ps.setDate(3, java.sql.Date.valueOf("1995-09-12"));
            ps.executeUpdate();
            ResultSet rs = s.executeQuery("select * from persona");
            while (rs.next()) {                
                System.out.println(rs.getInt(1)+" " +rs.getString(2)+" "+ rs.getDate(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
    
//    public void desconectar(){
//        try {
//            conexion.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(LabDos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public static void main(String[] args) {
        LaboratorioBaseD conexion = new LaboratorioBaseD("prueba");
        conexion.conectar();
    }      
}
