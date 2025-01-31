package Miembros;

import LogIn.LogIn;
import Menus.MenuAdm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CrearMiem {
    private JButton button1;
    public JPanel CMIEM;
    private JTextField JTextField1;
    private JTextField JTextField4;
    private JTextField JTextField3;
    private JTextField JTextField2;
    private JButton volverButton;
    private JLabel ImagenLogin;


    public CrearMiem() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Usuariosquery = "INSERT INTO usuarios (cedula_usuario, rol) VALUES (?, ?)";
                String Entrenadoresquery = "INSERT INTO miembros (miembro_id, nombre, fecha_ingreso,telefono) VALUES (?, ?, ?, ?)";

                String id = JTextField1.getText().trim();
                String nombre = JTextField2.getText().trim();
                String fecha_ingreso = JTextField3.getText().trim();
                String telefono = JTextField4.getText().trim();
                String rol = "Miembro";

                // Verificar que los campos no estén vacíos
                if (id.isEmpty() || telefono.isEmpty() || fecha_ingreso.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!telefono.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);  // No aceptar fechas inválidas (como "2024-02-30")

                // Validar la fecha
                try {
                    sdf.parse(fecha_ingreso); // Intenta parsear la fecha
                    JOptionPane.showMessageDialog(null, "Fecha válida.");
                } catch (ParseException eX) {
                    JOptionPane.showMessageDialog(null, "Fecha inválida. Asegúrate de usar el formato yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);




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
                        stmtEntrenadores.setString(3, fecha_ingreso);
                        stmtEntrenadores.setString(4, telefono);

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

