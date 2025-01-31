package Entrenadores;

import LogIn.LogIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ElimAdm {
    public JPanel EME;
    private JTextField textField1;
    private JButton eliminarButton;

    public ElimAdm() {
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = LogIn.ConexionBD.getConnection()) {
                    System.out.println("Conectado a la base de datos");

                    String query = "DELETE  FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                    Statement statement = connection.createStatement();


                    int filasAfectadas = statement.executeUpdate(query);

                    if (filasAfectadas > 0) {

                        JOptionPane.showMessageDialog(null, "Jugador elimnado con exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {

                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar los datos... \nIngrese correctamente el ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
