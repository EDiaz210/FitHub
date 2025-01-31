package Entrenadores;

import LogIn.LogIn;
import Menus.MenuAdm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BuscarAdm {
    private JTextField textField1;
    private JButton buscarButton;
    public JPanel BADM;
    private JLabel id;
    private JLabel nombre;
    private JLabel edad;
    private JLabel sueldo;
    private JLabel rol;
    private JButton volverButton;

    public BuscarAdm() {
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

                    String Usuariosquery = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                    String Entrenadoresquery = "SELECT * FROM entrenadores WHERE entrenador_id = '" + textField1.getText() + "'";
                    Statement statement = connection.createStatement();

                    try (ResultSet resultSet = statement.executeQuery(Entrenadoresquery);) {
                        while (resultSet.next()) {
                            id.setText(resultSet.getString("entrenador_id"));
                            nombre.setText(resultSet.getString("nombre"));
                            edad.setText(resultSet.getString("telefono"));
                            sueldo.setText(resultSet.getString("edad"));
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
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Menú Administrador");
                frame.setSize(350, 350);
                frame.setContentPane(new MenuAdm().menu);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                ((JFrame) SwingUtilities.getWindowAncestor(volverButton)).dispose();
            }
        });
    }
}
