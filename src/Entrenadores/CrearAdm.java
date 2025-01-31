package Entrenadores;

import LogIn.LogIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearAdm {
    public JPanel CADM;
    private JButton registrarButton;
    private JTextField JTextField1;
    private JTextField JTextField4;
    private JTextField JTextField3;
    private JTextField JTextField2;

    public CrearAdm() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Usuariosquery = "INSERT INTO usuarios (cedula_usuario, rol) VALUES (?, ?)";
                String Entrenadoresquery = "INSERT INTO entrenadores (entrenador_id, nombre ,telefono, edad) VALUES (?, ?, ?, ?)";

                String id = JTextField1.getText().trim();
                String nombre = JTextField2.getText().trim();
                String telefono = JTextField3.getText().trim();
                int edad = Integer.parseInt(JTextField4.getText().trim());
                String rol = "Entrenador";

                try (Connection connection = LogIn.ConexionBD.getConnection()) {
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
    }
}
