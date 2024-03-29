package servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.ProductosEnum;

/**
 *
 * @author Asus
 */
@WebServlet(name = "servletProducto", urlPatterns = {"/servletProducto"})
public class servletProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        ProductoDAO productoDAO = new ProductoDAO();
        String accion;
        RequestDispatcher dispatcher = null;
        
        accion = req.getParameter("accion");
        
        if(accion == null || accion.isEmpty()){
            dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
            List<Producto> listProducts = productoDAO.listarProductos();
            req.setAttribute("listaProductos", listProducts);       
            
        }else if("Insertar".equals(accion)){
            //String productId = req.getParameter("idProducto");
            String productName = req.getParameter("name");
            String productType = req.getParameter("select");  
            ProductosEnum productEnum = (productType.equals("0"))? ProductosEnum.valueOf("ELECTRODOMESTICO") : ProductosEnum.valueOf("REPUESTO");
            int stock = Integer.parseInt(req.getParameter("cantidad"));
            double price = Double.parseDouble(req.getParameter("precio"));
            String brand = req.getParameter("marca");
            String modelo = req.getParameter("modelo");
            
            Producto product = new Producto("", productName, productEnum, stock, price, brand, modelo);
            if(productoDAO.insertarDB(product)){
                dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
                List<Producto> listProducts = productoDAO.listarProductos();
                req.setAttribute("listaProductos", listProducts);
                req.setAttribute("action", "1");                
            }else{
                dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
                List<Producto> listProducts = productoDAO.listarProductos();
                req.setAttribute("listaProductos", listProducts);
                req.setAttribute("action", "0");
            }
            //productoDAO.insertarDB(product);            
            
        }else if("Eliminar".equals(accion)){
            String id = req.getParameter("producto");
            if(productoDAO.eliminarProducto(id)){
                dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
                List<Producto> listProducts = productoDAO.listarProductos();
                req.setAttribute("listaProductos", listProducts);
                req.setAttribute("action", "1");
            }else{
                dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
                List<Producto> listProducts = productoDAO.listarProductos();
                req.setAttribute("listaProductos", listProducts);
                req.setAttribute("action", "0");
            }                                    
        }else if("Actualizar".equals(accion)){            
            String idProducto = req.getParameter("servicio");            
            dispatcher = req.getRequestDispatcher("editarProducto.jsp");
            List<Producto> listProductos = new ArrayList<>();
            listProductos.add(productoDAO.buscarProducto(idProducto));
            req.setAttribute("servicio", listProductos);            
        }else if("GuardarCambios".equals(accion)){
            String productId = req.getParameter("idProducto");
            String productName = req.getParameter("name");
            String productType = req.getParameter("select");
            ProductosEnum productEnum = (productType.equals("0"))? ProductosEnum.valueOf("ELECTRODOMESTICO") : ProductosEnum.valueOf("REPUESTO");
            int stock = Integer.parseInt(req.getParameter("cantidad"));
            double price = Double.parseDouble(req.getParameter("precio"));
            String brand = req.getParameter("marca");
            String modelo = req.getParameter("modelo");
            Producto product = new Producto(productId, productName, productEnum, stock, price, brand, modelo);
            if(productoDAO.actualiarProducto(product)){
                dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
                List<Producto> listProducts = productoDAO.listarProductos();
                req.setAttribute("listaProductos", listProducts);
                req.setAttribute("action", "1");
            }else{
                dispatcher = req.getRequestDispatcher("gestionProductos.jsp");
                List<Producto> listProducts = productoDAO.listarProductos();
                req.setAttribute("listaProductos", listProducts);
                req.setAttribute("action", "0");
            }                                               
        }
        
        dispatcher.forward(req,resp);        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        doGet(req,resp);
    }
    
    
    
    
    

}