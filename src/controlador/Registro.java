
package controlador;

import db.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Paciente;

public class Registro {
    public void Agregar(Paciente paciente){
        try
        {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO PACIENTE VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
               ps.setString(1, paciente.getRut());
               ps.setString(2, paciente.getNombre());
               ps.setString(3, paciente.getGenero());
               ps.setInt(4, paciente.getEdad());
               ps.setString(5, paciente.getDireccion());
               ps.setString(6, paciente.getCiudad());
               ps.setString(7, paciente.getIsapre());
               ps.setBoolean(8, paciente.isDonante());
               
               ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public ArrayList<Paciente> pacienteListar(){
        try
        {
            ArrayList<Paciente> lista = new ArrayList();
            
            Conexion conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            PreparedStatement ps = con.prepareStatement("SELECT RUT, NOMBRE, GENERO, EDAD, DIRECCION, CIUDAD, ISAPRE, DONANTE "
                                                        + "FROM PACIENTE;");
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Paciente p = new Paciente();
                
                p.setRut(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setGenero(rs.getString(3));
                p.setEdad(rs.getInt(4));
                p.setDireccion(rs.getString(5));
                p.setCiudad(rs.getString(6));
                p.setIsapre(rs.getString(7));
                p.setDonante(rs.getBoolean(8));
                
                lista.add(p);
        }
        return lista;
    }
    catch(Exception ex){
        ex.printStackTrace();
        return new ArrayList<>();
}
}
public int pacienteContarPorRut(String Rut)
{
    try
    {
        int cantidad = 0;
        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT COUNT (RUT) FROM PACIENTE " + "WHERE RUT = ?;");
        ps.setString(1, Rut);
        ResultSet rs= ps.executeQuery();

        while(rs.next())
        {   
            cantidad = rs.getInt(1);
        }
        return cantidad;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
    public void pacienteEliminar(String rut)
    {
        try
        {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConnection(); 
            
            PreparedStatement ps = con.prepareStatement("DELETE FROM PACIENTE WHERE RUT = ?;");
            ps.setString(1, rut);
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void pacienteModificar(Paciente paciente)
    {
        try
        {
            Conexion conexion = new Conexion();
            Connection con = conexion.getConnection();   
                
            PreparedStatement ps = con.prepareStatement("UPDATE PACIENTE SET "
                            + "RUT = ?, "
                            + "NOMBRE = ?, "
                            + "GENERO = ?, "
                            + "EDAD = ?, "
                            + "DIRECCION = ?, "
                            + "CIUDAD = ?, "
                            + "ISAPRE = ?, "
                            + "DONANTE = ?;");
                            
            ps.setString(1, paciente.getRut());
            ps.setString(2,paciente.getNombre());
            ps.setString(3, paciente.getGenero());
            ps.setInt(4, paciente.getEdad());
            ps.setString(5, paciente.getDireccion());
            ps.setString(6, paciente.getCiudad());
            ps.setString(7, paciente.getIsapre());
            ps.setBoolean(8, paciente.isDonante());
            
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public ArrayList<Paciente> pacienteBuscarPorNombre(String titulo) {
        try {
            ArrayList<Paciente> listaPaciente = new ArrayList<>();
            Conexion conexion = new Conexion();
            Connection con = conexion.getConnection();

            PreparedStatement ps = con.prepareStatement("SELECT RUT, NOMBRE, GENERO, EDAD, DIRECCION, CIUDAD, ISAPRE, DONANNTE "
                                  + "FROM PACIENTE WHERE NOMBRE LIKE ?;");
            String nombre = null;
            ps.setString(1, nombre + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();

                p.setRut(rs.getString(1));
                p.setNombre(rs.getString(2));
                p.setGenero(rs.getString(3));
                p.setEdad(rs.getInt(4));
                p.setDireccion(rs.getString(5));
                p.setCiudad(rs.getString(6));
                p.setIsapre(rs.getString(7));
                p.setDonante(rs.getBoolean(8));

                listaPaciente.add(p);
                }
                return listaPaciente;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return new ArrayList<>();
            }
    }
}