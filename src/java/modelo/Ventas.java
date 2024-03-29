/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Ventas {
    
    private String idVenta;    
    private int cantidad;    
    private double precioTotal;
    private String fecha;
    private String cliente;
    
    private String idProducto;
    private String productName;
    private String productType;
    private double priceProduct;
    
    public Ventas(){
        
    }
    
    public Ventas(String idVenta,String idProducto, int cantidad, double priceProduct){
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.priceProduct = priceProduct;
        
    }
    
    public Ventas(String idVenta,String cliente, int cantidad, double valorUnitario, String fecha,String nombreProducto){
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.priceProduct = valorUnitario;
        this.fecha = fecha;
        this.productName = nombreProducto;
    }
    
        public Ventas(String idVenta,String cliente, int cantidad, double valorUnitario, String fecha,String nombreProducto,double precioTotal){
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.priceProduct = valorUnitario;
        this.fecha = fecha;
        this.productName = nombreProducto;
        this.precioTotal = precioTotal;
    }

    public Ventas(String cliente, String fecha, double monto){
        this.cliente = cliente;
        this.fecha = fecha;
        this.precioTotal = monto;        
    }
    
    
    public Ventas(String idVenta, int cantidad, double precioTotal, String fecha, String cliente, String idProducto, String productName, String typeProduct, double priceProduct) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
        this.cliente = cliente;
        this.idProducto = idProducto;
        this.productName = productName;
        this.productType = typeProduct;
        this.priceProduct = priceProduct;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String typeProduct) {
        this.productType = typeProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

 
}

