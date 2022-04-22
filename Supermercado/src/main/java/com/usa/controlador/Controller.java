
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.controlador;

import com.usa.modelo.Product;
import com.usa.modelo.ProductRepository;
import com.usa.vista.WindowUpdate;
import com.usa.vista.View;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;


/**
 *
 * @author Oscar Alvarado
 */
public class Controller implements ActionListener {

    ProductRepository repository;
    View view;
    WindowUpdate windowUpdate;
    DefaultTableModel defaultTableModel;
    String nameHigherPrice;
    String nameLowerPrice;

    public Controller() {
        super();
    }

    public Controller(ProductRepository repository, View view, WindowUpdate windowUpdate) {
        super();
        this.repository = repository;
        this.view = view;
        view.setVisible(true);
        this.windowUpdate = windowUpdate;
        addEvent();
        updateTable();
    }

    private void addEvent() {
        windowUpdate.getBtnActualizarProducto().addActionListener(this);
        view.getBotonAgrerar().addActionListener(this);
        view.getBotonActualizar().addActionListener(this);
        view.getBotonBorrar().addActionListener(this);
        view.getBotonInforme().addActionListener(this);
        view.getTablaProductos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                fillFields(mouseEvent);
            }
        });
    }

    private void updateTable() {
        String[] titles = new String[]{"id", "Nombre", "Precio", "Inventario"};
        defaultTableModel = new DefaultTableModel(titles, 0);
        List<Product> listProduct = (List<Product>) repository.findAll();
        listProduct.forEach(product -> defaultTableModel.addRow(new Object[]{product.getCode(), product.getName(), product.getPrice(), product.getInventory()})
        );
        view.getTablaProductos().setModel(defaultTableModel);
        view.getTablaProductos().setPreferredSize(new Dimension(350, defaultTableModel.getRowCount() * 16));
        view.getTablaProductos().removeColumn(view.getTablaProductos().getColumnModel().getColumn(0));
    }

    private void cleanFields() {
        view.getTextoCodigo().setText("");
        view.getTextoNombre().setText("");
        view.getTextoPrecio().setText("");
        view.getTextoInventario().setText("");
    }

    /**
     *
     * @return false if the fields are empty or the price is not a number else return true
     */
    private boolean validForm() {
        if ("".equals(view.getTextoNombre().getText()) || "".equals(view.getTextoPrecio().getText()) || "".equals(view.getTextoInventario().getText())) {
            JOptionPane.showMessageDialog(view, "Todos los campos deben estar llenos");
            return false;
        }
        if (!isNumber(view.getTextoPrecio().getText()) || !isNumber(view.getTextoInventario().getText())) {
            JOptionPane.showMessageDialog(view, "Los campos precio e inventario deben ser numericos");
            return false;
        }
        return true;
    }

    /**
     *
     * @return  false if the fields are empty or the price is not a number else return true
     */
    private boolean validFormUpdate() {
        if ("".equals(windowUpdate.getTxtNombreActualizado().getText()) || "".equals(windowUpdate.getTxtPrecioActualizado().getText()) || "".equals(windowUpdate.getTxtInventarioActualizado().getText())) {
            JOptionPane.showMessageDialog(windowUpdate, "Todos los campos deben estar llenos");
            return false;
        }
        if (!isNumber(windowUpdate.getTxtPrecioActualizado().getText()) || !isNumber(view.getTextoInventario().getText())) {
            JOptionPane.showMessageDialog(windowUpdate, "Los campos precio e inventario deben ser numericos");
            return false;
        }
        return true;
    }

    /**
     *
     * @param  mouseEvent the event of the mouse
     */
    private void fillFields(MouseEvent mouseEvent) {
        JTable target = (JTable) mouseEvent.getSource();
        view.getTextoCodigo().setText(view.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 0).toString());
        view.getTextoNombre().setText(view.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        view.getTextoPrecio().setText(view.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        view.getTextoInventario().setText(view.getTablaProductos().getModel().getValueAt(target.getSelectedRow(), 3).toString());
    }

    /**
     * 
     * @param numberText the text to validate
     * @return true if the text is a number else return false
     */
    private boolean isNumber(String numberText) {
        try {
            Double.parseDouble(numberText);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param  actionEvent is the event that is triggered
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == view.getBotonAgrerar()) {
            addProduct();
        }
        if (actionEvent.getSource() == view.getBotonActualizar()) {
            windowUpdate.setVisible(true);
            windowUpdate.setLocationRelativeTo(view);
            windowUpdate.getTxtCodigoActualizado().setText(view.getTextoCodigo().getText());
            windowUpdate.getTxtNombreActualizado().setText(view.getTextoNombre().getText());
            windowUpdate.getTxtPrecioActualizado().setText(view.getTextoPrecio().getText());
            windowUpdate.getTxtInventarioActualizado().setText(view.getTextoInventario().getText());
        }
        if (actionEvent.getSource() == view.getBotonBorrar()) {
            deleteProduct();
        }
        if (actionEvent.getSource() == view.getBotonInforme()) {
            generateReport();
        }
        if (actionEvent.getSource() == windowUpdate.getBtnActualizarProducto()) {
            updateProduct();
            windowUpdate.dispose();
        }

    }
    /**
     * method for add product to db
     */
    public void addProduct() {
        try {
            if (validForm()) {
                Product product = new Product(view.getTextoNombre().getText(), Double.parseDouble(view.getTextoPrecio().getText()), Integer.parseInt(view.getTextoInventario().getText()));
                repository.save(product);
                JOptionPane.showMessageDialog(view, "Producto agregado correctamente");
            }
        } catch (DbActionExecutionException e) {
            JOptionPane.showMessageDialog(view, " Producto ya existe en la base de datos");
        } finally {
            cleanFields();
            updateTable();
        }
    }
    /**
     * method for update db
     */
    public void updateProduct() {
        try {
            if (validFormUpdate()) {
                Product product = new Product(Integer.parseInt(windowUpdate.getTxtCodigoActualizado().getText()), windowUpdate.getTxtNombreActualizado().getText(), Double.parseDouble(windowUpdate.getTxtPrecioActualizado().getText()), Integer.parseInt(windowUpdate.getTxtInventarioActualizado().getText()));
                repository.save(product);
                JOptionPane.showMessageDialog(view, "Producto actualizado correctamente");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(view, " Producto no pudo ser actualizado");
        } finally {
            cleanFields();
            updateTable();
        }
    }
    /**
     * method for delete data in db
     */
    public void deleteProduct() {
        try {
            if (validForm()) {
                Product product = new Product(Integer.parseInt(view.getTextoCodigo().getText()), view.getTextoNombre().getText(), Double.parseDouble(view.getTextoPrecio().getText()), Integer.parseInt(view.getTextoInventario().getText()));
                repository.delete(product);
                JOptionPane.showMessageDialog(view, "Producto borrado correctamente");
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(view, " Producto no se pudo borrar");
        } finally {
            cleanFields();
            updateTable();
        }
    }

    /**
     *
     * @param price price of product
     * @param name  name of product
     * @param higherValue higher value of product
     * @return  higherValue
     */
    public double calculateHigher(double price, String name, double higherValue) {
        if (price > higherValue) {
            higherValue = price;
            this.nameHigherPrice = name;
        }
        return higherValue;
    }

    /**
     *
     * @param price  price of product
     * @param name   name of product
     * @param lowerValue    lower value of product
     * @return lowerValue
     */
    public double calculateLower(double price, String name, double lowerValue) {
        if (price < lowerValue) {
            lowerValue = price;
            this.nameLowerPrice = name;
        }
        return lowerValue;
    }

    /**
     *
     * @param price price of product
     * @param sum sum of product
     * @return sum + price
     */
    public double summation(double price, double sum) {
        return sum + price;
    }

    /**
     *
     * @param price price of product
     * @param amount amount of product
     * @param sumInventory sum of inventory
     * @return sumInventory + (price * amount);
     */
    public double inventoryCalculation(double price, int amount, double sumInventory) {
        return sumInventory + (price * amount);
    }
    /**
     * method for generate inform
     */
    public void generateReport() {
        List<Product> inventory ;
        inventory = extractData();
        double sum = 0;
        double inventoryCalculation = 0;
        double higherValue = inventory.get(0).getPrice();
        double lowerValue = higherValue;
        for (Product product : inventory) {
            higherValue = calculateHigher(product.getPrice(), product.getName(), higherValue);
            lowerValue = calculateLower(product.getPrice(), product.getName(), lowerValue);
            sum = summation(product.getPrice(), sum);
            inventoryCalculation = inventoryCalculation(product.getPrice(), product.getInventory(), inventoryCalculation);
        }
        DecimalFormat format = new DecimalFormat("#.0");


        double averageValue = sum / inventory.size();
        String average = format.format(averageValue);
        String inventoryPrice = format.format(inventoryCalculation);
        JOptionPane.showMessageDialog(view, "Product Precio Mayor: " + this.nameHigherPrice + "\nProduct Precio Menor: " + this.nameLowerPrice + "\nPromedio Precio: " + average + "\nValor del Inventario: " + inventoryPrice);
    }

    /**
     *
     * @return  inventory
     */
    public List <Product> extractData() {
        List<Product> inventory = new ArrayList<>();
        for (Product product : repository.findAll()) {
            inventory.add(product);
        }
        return inventory;
    }
}
