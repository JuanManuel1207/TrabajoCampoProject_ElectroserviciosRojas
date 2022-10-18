/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author juanm
 */
import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioDAO {
    Connection connection;
    
    public ServicioDAO(){
        ConexionBD connectBD = new ConexionBD();
        connection = connectBD.getConexion();
    }
    
    public List<Emple> listarEmpleado(){
        PreparedStatement ps;
        ResultSet rs;
        
        List<Emple> lista = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM EMPLEADO");
            rs = ps.executeQuery();
            
            while(rs.next()){
                String id = rs.getString("id");
                String empleado = rs.getString("empleado");
                Emple emple = new Emple(id, empleado);
                lista.add(emple);
            }
           return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Servicio> listarServicio(){
        PreparedStatement ps;
        ResultSet rs;
        
        List<Servicio> lista = new ArrayList<>();
        
        try {
            ps = connection.prepareStatement("SELECT * FROM SERVICE");
            rs = ps.executeQuery();
            
            while(rs.next()){
                String id = rs.getString("id");
                String cliente = rs.getString("cliente");
                String tipoServicio = rs.getString("tipo");
                String estado = rs.getString("estado");
                String fechaIngreso = ""+rs.getDate("entrada");
                String fechaSalida = ""+rs.getDate("salida");
                String descripcion = rs.getString("descripcion");
                String precio = ""+rs.getDouble("precio");
                String empleado = ""+rs.getInt("empleado");
                                
                Servicio servicio = new Servicio(id, cliente, tipoServicio, estado, fechaIngreso, fechaSalida, descripcion, precio, empleado);
                
                lista.add(servicio);
            }
           return lista;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public boolean ingresarServicio(Servicio servicio){
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("INSERT INTO SERVICE(id,cliente,tipo,estado,entrada,salida,descripcion,precio,empleado) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, servicio.getId());
            ps.setString(2, servicio.getCliente());
            ps.setString(3, servicio.getTipoServicio());
            ps.setString(4, servicio.getEstado());            
            ps.setDate(5, java.sql.Date.valueOf(servicio.getFechaIngreso()));
            ps.setDate(6, java.sql.Date.valueOf(servicio.getFechaSalida()));
            ps.setString(7, servicio.getDescripcion());
            ps.setDouble(8, Double.parseDouble(servicio.getPrecio()) );
            ps.setInt(9, Integer.parseInt(servicio.getEmpleado()));
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public Servicio buscarServicio(String id){
        PreparedStatement ps;
        ResultSet rs;
        Servicio servicio = null;
        
        try {
            ps = connection.prepareStatement("SELECT * FROM SERVICE WHERE id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String Id, Cliente, tipoServicio, estado,fechaIngreso, fechaSalida, descripcion, precio, empleado;
                Id = rs.getString("id");
                Cliente = rs.getString("cliente");
                tipoServicio = rs.getString("tipo");
                estado = rs.getString("estado");
                fechaIngreso = rs.getString("entrada");
                fechaSalida = rs.getString("salida");
                descripcion = rs.getString("descripcion");
                precio = rs.getString("precio");
                empleado = rs.getString("empleado");
                servicio = new Servicio(Id, Cliente, tipoServicio, estado, fechaIngreso, fechaSalida, descripcion, precio, empleado);
            }
            return servicio;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public boolean eliminarServicio(String id){
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("DELETE FROM SERVICE WHERE id = ?");
            ps.setString(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean actualizarServicio(Servicio servicio){
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("UPDATE SERVICE SET cliente=?,tipo=?,estado=?,entrada=?,salida=?,descripcion=?,precio=?,empleado=? WHERE id = ?");
            ps.setString(1, servicio.getCliente());
            ps.setString(2, servicio.getTipoServicio());
            ps.setString(3, servicio.getEstado());            
            ps.setDate(4, java.sql.Date.valueOf(servicio.getFechaIngreso()));
            ps.setDate(5, java.sql.Date.valueOf(servicio.getFechaSalida()));
            ps.setString(6, servicio.getDescripcion());
            ps.setDouble(7, Double.parseDouble(servicio.getPrecio()) );
            ps.setInt(8, Integer.parseInt(servicio.getEmpleado()));
            ps.setString(9, servicio.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    
}
