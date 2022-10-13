package ar.edu.unlam.tallerweb1.domain.ventas;

import ar.edu.unlam.tallerweb1.domain.empleados.Empleado;
import ar.edu.unlam.tallerweb1.domain.empleados.ServicioEmpleado;
import ar.edu.unlam.tallerweb1.domain.productos.Producto;
import ar.edu.unlam.tallerweb1.domain.productos.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ServicioVentaImpl  implements   ServicioVenta{

    private RepositorioVenta repositorioVenta;
    private ServicioProducto servicioProducto;

    private ServicioEmpleado servicioEmpleado;

    double subtotalProducto1= 0.0;
    double subtotalProducto2= 0.0;

    double subtotalProductos= 0.0;

    @Autowired
    public ServicioVentaImpl(RepositorioVenta repositorioVenta, ServicioProducto servicioProducto, ServicioEmpleado servicioEmpleado){
        this.repositorioVenta = repositorioVenta;
        this.servicioProducto = servicioProducto;
        this.servicioEmpleado = servicioEmpleado;
    }


    @Override
    @Transactional
    public boolean addVenta(Venta venta) throws CantidadInsuficienteException, IdEmpleadoNoValidoException{
        try {
            validarEmpleado(venta);
            validarVenta(venta);
            fillTotal(venta);
            repositorioVenta.addVenta(venta);
            actualizarCantidadesStock(venta);
            return true;
        }catch(CantidadInsuficienteException cie){
            throw new CantidadInsuficienteException(cie.getMessage());
        }catch (IdEmpleadoNoValidoException ienve){
            servicioEmpleado.traemeTodosLosEmpleados();
            String mensaje = "El id ingresado no es valido. Los ids de los empleados registrados son : " + servicioEmpleado.listaDeIdsDeTodosLosEmpleados();
            throw new IdEmpleadoNoValidoException(mensaje);
        } catch (Exception ex){
            return false;
        }
    }



    public double fillTotal(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();

        for(Producto p : productos){
            if(venta.getIdProducto()==p.getId()){
                subtotalProducto1=p.getCosto()* venta.getCantidadProducto();
            }
            if(venta.getIdProducto2()==p.getId()){
                subtotalProducto2= p.getCosto()* venta.getCantidadProducto2();
            }
        }
        subtotalProductos = subtotalProducto1+subtotalProducto2;
        venta.setTotal(subtotalProductos);

        return subtotalProductos;
    }

    @Transactional
    private void actualizarCantidadesStock(Venta venta) {
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();
        for(Producto p : productos){
            if(venta.getIdProducto()==p.getId()){
                p.setCantidad(p.getCantidad() - venta.getCantidadProducto());
            }
            if(venta.getIdProducto2()==p.getId()){
                p.setCantidad(p.getCantidad() - venta.getCantidadProducto2());
            }
        }
        servicioProducto.updateProductos(productos);
    }

    @Override
    public boolean modifyVenta(Venta venta) {
        return false;
    }

    @Override
    public boolean deleteVenta(Venta venta) {
        return false;
    }

    @Override
    public Venta buscarVenta(Venta venta) {
        return null;
    }

    @Override
    public String buscarNombreEmpleado(int idEmpleado) {
        ArrayList<Empleado> empleados = (ArrayList) servicioEmpleado.traemeTodosLosEmpleados();
        for (Empleado e : empleados) {
            if (idEmpleado == e.getId()) {
                return e.getName();
            }
        }
        return null;
    }
    @Override
    public String buscarNombreProducto(int idProducto) {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();
        for (Producto p : productos) {
            if (idProducto == p.getId()) {
                String nombreProductoUno = p.getNombre();
                return nombreProductoUno;
            }
            if (idProducto == p.getId()) {
                String nombreProductoDos = p.getNombre();
                return nombreProductoDos;
            }
        }
        return null;
    }

    @Override
    public double buscarCostoProducto(int idProducto) {
        ArrayList<Producto> productos = (ArrayList) servicioProducto.buscarProductos();
        for (Producto p : productos) {
            if (idProducto == p.getId()) {
                double costoProductoUno = p.getCosto();
                return costoProductoUno;
            }
            if (idProducto == p.getId()) {
                double costoProductoDos = p.getCosto();
                return costoProductoDos;
            }
        }
        return 0.0;
    }

    public double calcularComisionEmpleado(double sumaTotal){
        return sumaTotal * 0.05;
    }

    public double getSubtotalProducto1() {
        return subtotalProducto1;
    }

    public double getSubtotalProducto2() {
        return subtotalProducto2;
    }

    public double getSubtotalProductos() {
        return subtotalProductos;
    }

    private void validarVenta(Venta v) throws CantidadInsuficienteException{
        ArrayList<Producto> productos = (ArrayList)servicioProducto.buscarProductos();

        for(Producto p : productos){
            if(v.getIdProducto()==p.getId()){
                if(v.getCantidadProducto() > p.getCantidad()){
                    throw new CantidadInsuficienteException(p.getNombre(), v.getCantidadProducto(), p.getCantidad());
                }
            }
            if(v.getIdProducto2()==p.getId()){
                if(v.getCantidadProducto2() > p.getCantidad()){
                    throw new CantidadInsuficienteException(p.getNombre(), v.getCantidadProducto2(), p.getCantidad());
                }
            }
        }

    }

    private void validarEmpleado(Venta v) throws IdEmpleadoNoValidoException{
        Long idEmpleadoVenta = Long.valueOf(v.getIdEmpleado());
        Empleado empleadoBuscado = new Empleado();
        empleadoBuscado.setId(idEmpleadoVenta);
        if (servicioEmpleado.buscarEmpleado(empleadoBuscado) == null){
            throw new IdEmpleadoNoValidoException(v.getIdEmpleado());
        }
    }

}