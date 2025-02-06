package Miembros;

import Conexion.Conexion;
import LogIn.LogIn;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Menus.MenuMiembros;
import Rol.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarMiem extends Conexion {
    public JPanel BuscarMiem;
    private JTextField textField1;
    private JButton buscarButton;
    private JLabel id;
    private JLabel nombre;
    private JLabel edad;
    private JLabel sueldo;
    private JLabel rol;
    private JButton volverButton;

    public BuscarMiem() {
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


                try (Connection connection = connect()) {

                    String Usuariosquery = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                    String Miembrosquery = "SELECT * FROM miembros WHERE miembro_id = '" + textField1.getText() + "'";
                    Statement statement = connection.createStatement();

                    try (ResultSet resultSet = statement.executeQuery(Miembrosquery);) {
                        while (resultSet.next()) {
                            id.setText(resultSet.getString("miembro_id"));
                            nombre.setText(resultSet.getString("nombre"));
                            edad.setText(resultSet.getString("fecha_ingreso"));
                            sueldo.setText(resultSet.getString("telefono"));
                        }
                    }

                    try (ResultSet resultSetUsuarios = statement.executeQuery(Usuariosquery);) {
                        while (resultSetUsuarios.next()) {
                            rol.setText(resultSetUsuarios.getString("rol"));
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


