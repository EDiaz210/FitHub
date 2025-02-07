package Servicios;

import Conexion.Conexion;
import LogIn.LogIn;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Rol.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AgregarServ extends Conexion {
    public JPanel CRS;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ingresarButton;
    private JButton volverButton;
    private JComboBox comboBox1;


    public AgregarServ() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Serviciosquery = "INSERT INTO servicios (servicio_id, tipo_servicio, extras, costo_servicio, costo_extras) VALUES (?, ?, ?, ?, ?)";

                String id = textField1.getText().trim();
                String tipo_servicio = "Membresía";
                String extras = (String) comboBox1.getSelectedItem();
                int costo_servicio = 30;
                String  costo_extrasString = textField4.getText().trim();

                // Verificar que los campos no estén vacíos
                if (id.isEmpty()  || costo_extrasString.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!id.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double costo_extras;

                try {
                    costo_extras = Double.parseDouble(costo_extrasString); // Convertir a double
                } catch (NumberFormatException eX) {
                    JOptionPane.showMessageDialog(null, "El costo extra debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try (Connection connection = connect()) {
                    connection.setAutoCommit(false);


                    // Insertar en la tabla servicios
                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(Serviciosquery)) {
                        stmtEntrenadores.setString(1, id);
                        stmtEntrenadores.setString(2, tipo_servicio);
                        stmtEntrenadores.setString(3, extras);
                        stmtEntrenadores.setInt(4,costo_servicio);
                        stmtEntrenadores.setDouble(5,costo_extras);
                        stmtEntrenadores.executeUpdate();
                    }


                    // Confirmar la transacción
                    connection.commit();
                    JOptionPane.showMessageDialog(null, "Datos insertados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }catch (SQLException e3){
                    e3.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar los datos... \nIngrese correctamente el ID", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rolUsuario = UsuarioSesion.getInstancia().getRolUsuario();


                if ("Administrador".equals(rolUsuario)) {
                    JFrame frame = new JFrame();
                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                    frame.setTitle("Menú Administrador");
                    frame.setSize(450, 400);
                    frame.setContentPane(new MenuAdm().menu);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    (SwingUtilities.getWindowAncestor(volverButton)).dispose();

                } else if ("Entrenador".equals(rolUsuario)) {
                    JFrame frame = new JFrame();
                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                    frame.setTitle("Menú Entrenador");
                    frame.setSize(450, 400);
                    frame.setContentPane(new MenuEntre().menu);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    (SwingUtilities.getWindowAncestor(volverButton)).dispose();
                }
            }
        });
    }
}