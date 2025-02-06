package Entrenadores;

import Conexion.Conexion;
import LogIn.LogIn;
import Menus.MenuAdm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearAdm extends Conexion {
    public JPanel CADM;
    private JButton registrarButton;
    private JTextField JTextField1;
    private JTextField JTextField4;
    private JTextField JTextField3;
    private JTextField JTextField2;
    private JButton volverButton;
    private JLabel ImagenLogin;

    public CrearAdm() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Usuariosquery = "INSERT INTO usuarios (cedula_usuario, rol) VALUES (?, ?)";
                String Entrenadoresquery = "INSERT INTO entrenadores (entrenador_id, nombre ,telefono, edad) VALUES (?, ?, ?, ?)";

                String id = JTextField1.getText().trim();
                String nombre = JTextField2.getText().trim();
                String telefono = JTextField3.getText().trim();
                String edadText = JTextField4.getText().trim();
                String rol = "Entrenador";

                // Verificar que los campos no estén vacíos
                if (id.isEmpty() || telefono.isEmpty() || edadText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!telefono.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                // Validar que la edad sea un número válido
                int edad;
                try {
                    edad = Integer.parseInt(edadText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }



                try (Connection connection = connect() ) {
                    connection.setAutoCommit(false);

                    // Insertar en la tabla usuarios
                    try (PreparedStatement stmtUsuarios = connection.prepareStatement(Usuariosquery)) {
                        stmtUsuarios.setString(1, id);
                        stmtUsuarios.setString(2, rol);
                        stmtUsuarios.executeUpdate();
                    }

                    // Insertar en la tabla entrenadores
                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(Entrenadoresquery)) {
                        stmtEntrenadores.setString(1, id);
                        stmtEntrenadores.setString(2, nombre);
                        stmtEntrenadores.setString(3, telefono);
                        stmtEntrenadores.setInt(4, edad);
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
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Menú Administrador");
                frame.setSize(350, 350);
                frame.setContentPane(new MenuAdm().menu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(volverButton)).dispose();
            }
        });
    }
}
