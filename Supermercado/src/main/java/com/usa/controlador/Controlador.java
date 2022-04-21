
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.controlador;

import com.usa.modelo.Producto;
import com.usa.modelo.ProductoRepository;
import com.usa.vista.VentanaActualizar;
import com.usa.vista.Vista;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;


/**
 *
 * @author ASUS
 */
public class Controlador implements ActionListener {
    /**
     * ProductoRepository repositorio;
     */
    ProductoRepository repositorio;
    /**
     * Vista vista;
     */
    Vista vista;
    /**
     * VentanaActualizar ventanaActualizar;
     */
    VentanaActualizar ventanaActualizar;
    /**
     * DefaultTableModel defaulTableModel;
     */
    DefaultTableModel defaulTableModel;
    /**
     * String NombrePrecioMayor;
     */
    String NombrePrecioMayor;
    /**
     * String NombrePrecioMenor;
     */
    String NombrePrecioMenor;
    /**
     * constructor empty
     */
    public Controlador() {
        super();
    }

    /**
     *
     * @param repositorio
     * @param vista
     * @param ventanaActualizar
     */
    public Controlador(ProductoRepository repositorio, Vista vista, VentanaActualizar ventanaActualizar) {
        super();
        this.repositorio = repositorio;
        this.vista = vista;
        vista.setVisible(true);
        this.ventanaActualizar = ventanaActualizar;
        agregarEventos();
        actualizarTabla();
    }

    private void agregarEventos() {
        ventanaActualizar.getBtnActualizarProducto().addActionListener(this);
        vista.getBotonAgrerar().addActionListener(this);
        vista.getBotonActualizar().addActionListener(this);
        vista.getBotonBorrar().addActionListener(this);
        vista.getBotonInforme().addActionListener(this);
        vista.getTablaProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                llenarCampos(mouseEvent);
            }
        });
    }

    private void actualizarTabla() {
        String[] titulos = new String[]{"id", "Nombre", "Precio", "Inventario"};
        defaulTableModel = new DefaultTableModel(titulos, 0);
        List<Producto> listaProducto = (List<Producto>) repositorio.findAll();
        listaProducto.forEach((producto) -> {
            defaulTableModel.addRow(new Object[]{producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getInventario()});
        });
        vista.getTablaProductos().setModel(defaulTableModel);
        vista.getTablaProductos().setPreferredSize(new Dimension(350, defaulTableModel.getRowCount() * 16));
        vista.getTablaProductos().removeColumn(vista.getTablaProductos().getColumnModel().getColumn(0));
    }

    private void limpiarCampos() {
        vista.getTextoCodigo().setText("");
        vista.getTextoNombre().setText("");
        vista.getTextoPrecio().setText("");
        vista.getTextoInventario().setText("");
    }

    /**
     *
     * @return
     */
    private boolean formularioValido() {
        if ("".equals(vista.getTextoNombre().getText()) || "".equals(vista.getTextoPrecio().getText()) || "".equals(vista.getTextoInventario().getText())) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estan llenos");
            return false;
        }
        if (!esNumero(vista.getTextoPrecio().getText()) || !esNumero(vista.getTextoInventario().getText())) {
            JOptionPane.showMessageDialog(null, "Los campos precio e inventario deben ser numerico");
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    private boolean formularioActualizarValido() {
        if ("".equals(ventanaActualizar.getTxtNombreActualizado().getText()) || "".equals(ventanaActualizar.getTxtPrecioActualizado().getText()) || "".equals(ventanaActualizar.getTxtInventarioActualizado().getText())) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estan llenos");
            return false;
        }
        if (!esNumero(ventanaActualizar.getTxtPrecioActualizado().getText()) || !esNumero(vista.getTextoInventario().getText())) {
            JOptionPane.showMessageDialog(null, "Los campos precio e inventario deben ser numerico");
            return false;
        }
        return true;
    }

    /**
     *
     * @param mouseEvent
     */
    private void llenarCampos(MouseEvent mouseEvent) {
        JTable target = (JTable) mouseEvent.getSource();
        vista.getTextoCodigo().setText(vista.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 0).toString());
        vista.getTextoNombre().setText(vista.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getTextoPrecio().setText(vista.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getTextoInventario().setText(vista.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 3).toString());
    }

    /**
     * 
     * @param textoNumero
     * @return 
     */
    private boolean esNumero(String textoNumero) {
        try {
            Double.parseDouble(textoNumero);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == vista.getBotonAgrerar()) {
            agregarProducto();
        }
        if (actionEvent.getSource() == vista.getBotonActualizar()) {
            ventanaActualizar.setVisible(true);
            ventanaActualizar.setLocationRelativeTo(vista);
            ventanaActualizar.getTxtCodigoActualizado().setText(vista.getTextoCodigo().getText());
            ventanaActualizar.getTxtNombreActualizado().setText(vista.getTextoNombre().getText());
            ventanaActualizar.getTxtPrecioActualizado().setText(vista.getTextoPrecio().getText());
            ventanaActualizar.getTxtInventarioActualizado().setText(vista.getTextoInventario().getText());
        }
        if (actionEvent.getSource() == vista.getBotonBorrar()) {
            borrarProducto();
        }
        if (actionEvent.getSource() == vista.getBotonInforme()) {
            generarInforme();
        }
        if (actionEvent.getSource() == ventanaActualizar.getBtnActualizarProducto()) {
            actualizarProducto();
            ventanaActualizar.dispose();
        }

    }
    /**
     * method for add product to db
     */
    public void agregarProducto() {
        try {
            if (formularioValido()) {
                Producto producto = new Producto(vista.getTextoNombre().getText(), Double.parseDouble(vista.getTextoPrecio().getText()), Integer.parseInt(vista.getTextoInventario().getText()));
                repositorio.save(producto);
                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
            }
        } catch (DbActionExecutionException e) {
            JOptionPane.showMessageDialog(null, " Producto ya existe en la base de datos");
        } finally {
            limpiarCampos();
            actualizarTabla();
        }
    }
    /**
     * method for update db
     */
    public void actualizarProducto() {
        try {
            if (formularioActualizarValido()) {
                Producto producto = new Producto(Integer.parseInt(ventanaActualizar.getTxtCodigoActualizado().getText()), ventanaActualizar.getTxtNombreActualizado().getText(), Double.parseDouble(ventanaActualizar.getTxtPrecioActualizado().getText()), Integer.parseInt(ventanaActualizar.getTxtInventarioActualizado().getText()));
                repositorio.save(producto);
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, " Producto no pudo ser actualizado");
        } finally {
            limpiarCampos();
            actualizarTabla();
        }
    }
    /**
     * method for delete data in db
     */
    public void borrarProducto() {
        try {
            if (formularioValido()) {
                Producto producto = new Producto(Integer.parseInt(vista.getTextoCodigo().getText()), vista.getTextoNombre().getText(), Double.parseDouble(vista.getTextoPrecio().getText()), Integer.parseInt(vista.getTextoInventario().getText()));
                repositorio.delete(producto);
                JOptionPane.showMessageDialog(null, "Producto borrado correctamente");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, " Producto no se pudo borrar");
        } finally {
            limpiarCampos();
            actualizarTabla();
        }
    }

    /**
     *
     * @param precio
     * @param nombre
     * @param valorMayor
     * @return
     */
    public double calcularMayor(double precio, String nombre, double valorMayor) {
        if (precio > valorMayor) {
            valorMayor = precio;
            this.NombrePrecioMayor = nombre;
        }
        return valorMayor;
    }

    /**
     *
     * @param precio
     * @param nombre
     * @param valorMenor
     * @return
     */
    public double calculaMenor(double precio, String nombre, double valorMenor) {
        if (precio < valorMenor) {
            valorMenor = precio;
            this.NombrePrecioMenor = nombre;
        }
        return valorMenor;
    }

    /**
     *
     * @param precio
     * @param suma
     * @return
     */
    public double sumatoria(double precio, double suma) {
        return suma + precio;
    }

    /**
     *
     * @param precio
     * @param cantidad
     * @param sumaInv
     * @return
     */
    public double calculoInventario(double precio, int cantidad, double sumaInv) {
        return sumaInv + (precio * cantidad);
    }
    /**
     * method for generate inform
     */
    public void generarInforme() {
        ArrayList<Producto> inventario = new ArrayList<>();
        inventario = extraerDatos();
        double suma = 0;
        double calculoInvntario = 0;
        double valorMayor = inventario.get(0).getPrecio();
        double valorMenor = valorMayor;
        for (Producto elemento : inventario) {
            valorMayor = calcularMayor(elemento.getPrecio(), elemento.getNombre(), valorMayor);
            valorMenor = calculaMenor(elemento.getPrecio(), elemento.getNombre(), valorMenor);
            suma = sumatoria(elemento.getPrecio(), suma);
            calculoInvntario = calculoInventario(elemento.getPrecio(), elemento.getInventario(), calculoInvntario);
        }
        DecimalFormat formato = new DecimalFormat("#.0");
        double valroPromedio = suma / inventario.size();
        String promedio = formato.format(valroPromedio);
        String precioInventario = formato.format(calculoInvntario);
        JOptionPane.showMessageDialog(vista, "Producto Precio Mayor: " + this.NombrePrecioMayor + "\nProducto Precio Menor: " + this.NombrePrecioMenor + "\nPromedio Precio: " + promedio + "\nValor del Inventario: " + precioInventario);
    }

    /**
     *
     * @return
     */
    public ArrayList extraerDatos() {
        ArrayList<Producto> inventario = new ArrayList<>();
        for (Iterator<Producto> it = repositorio.findAll().iterator(); it.hasNext();) {
            Producto elemento = it.next();
            inventario.add(elemento);
        }
        return inventario;
    }
}
