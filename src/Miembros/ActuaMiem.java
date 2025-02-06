package Miembros;
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
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ActuaMiem extends Conexion {
    public JPanel actuMiem;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton actualizarButton;
    private JTextField textField3;
    private JLabel rol;
    private JLabel IdAc;
    private JLabel nombre;


    public ActuaMiem() {
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String queryUsuarios = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                String queryMiembrosSelect = "SELECT * FROM miembros";
                String queryMiembros = "UPDATE miembros SET fecha_ingreso = ?, telefono = ? WHERE miembro_id = '" + textField1.getText() + "'";

                String cedula = textField1.getText().trim();
                String telefono = textField2.getText().trim();
                String fecha_ingreso = textField3.getText().trim();

                // Verificar que los campos no estén vacíos
                if (cedula.isEmpty() || telefono.isEmpty() || fecha_ingreso.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!telefono.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar manualmente si la fecha cumple con el formato correcto antes de parsear
                if (!fecha_ingreso.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir si el formato es incorrecto
                }

                // Verificar manualmente si la fecha cumple con el formato correcto antes de parsear
                if (!fecha_ingreso.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(null, "Formato incorrecto. Usa yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Salir si el formato es incorrecto
                }


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(true);  // No aceptar fechas inválidas (como "2024-02-30")

                // Validar la fecha
                try {
                    sdf.parse(fecha_ingreso); // Intenta parsear la fecha
                } catch (ParseException eX) {}



                    try (Connection connection = connect()) {
                    Statement statement = connection.createStatement();
                    connection.setAutoCommit(false);

                    // Actualizar en la tabla usuarios
                    try (ResultSet resultSet = statement.executeQuery(queryUsuarios)){
                        while (resultSet.next()) {
                            IdAc.setText(resultSet.getString("cedula_usuario"));
                            rol.setText(resultSet.getString("rol"));
                        }
                    }

                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(queryMiembrosSelect)) {
                        try (ResultSet resultSet = statement.executeQuery(queryMiembrosSelect)) {
                            while (resultSet.next()) {
                                nombre.setText(resultSet.getString("nombre"));
                            }
                        }
                    }

                    // Actualizar en la tabla entrenadores
                    try (PreparedStatement stmtEntrenadores2 = connection.prepareStatement(queryMiembros)) {
                        stmtEntrenadores2.setString(1, fecha_ingreso);
                        stmtEntrenadores2.setString(2, telefono);
                        stmtEntrenadores2.executeUpdate();
                    }

                    // Confirmar la transacción
                    connection.commit();
                    JOptionPane.showMessageDialog(null, "Datos del miembro actualizados exitosamente.");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No se ha podido actualizar.");


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

