package Servicios;

import LogIn.LogIn;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Rol.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarServ {
    public JPanel BRS;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton volverButton;
    private JLabel id;
    private JLabel nombre;
    private JLabel tipo_servicio;
    private JLabel extras;
    private JLabel costo_servicio;
    private JLabel costo_extras;

    public BuscarServ() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verificar que el campo no este vacío
                String cedula = textField1.getText().trim();

                if (cedula.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que la cedula contenga exactamente 10 dígitos numéricos
                if (!cedula.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try (Connection connection = LogIn.ConexionBD.getConnection()) {

                    String Serviciosquery = "SELECT * FROM servicios WHERE cedula_usuario = '" + textField1.getText() + "'";
                    String Miembrosquery = "SELECT * FROM miembros WHERE miembro_id = '" + textField1.getText() + "'";
                    Statement statement = connection.createStatement();

                    try (ResultSet resultSet = statement.executeQuery(Miembrosquery);) {
                        while (resultSet.next()) {
                            id.setText(resultSet.getString("miembro_id"));
                            nombre.setText(resultSet.getString("nombre"));
                        }
                    }

                    try (ResultSet resultSetServicios = statement.executeQuery(Serviciosquery);) {
                        while (resultSetServicios.next()) {
                            tipo_servicio.setText(resultSetServicios.getString("tipo_servicio"));
                            extras.setText(resultSetServicios.getString("extras"));
                            costo_servicio.setText(resultSetServicios.getString("costo_servicio"));
                            costo_extras.setText(resultSetServicios.getString("costo_extras"));
                        }
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
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
                    frame.setSize(350, 350);
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
                    frame.setSize(350, 350);
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