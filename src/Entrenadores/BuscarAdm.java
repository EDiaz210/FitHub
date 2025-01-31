package Entrenadores;

import LogIn.LogIn;

import javax.swing.*;
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

    public BuscarAdm() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    }
}
