package Administradores;

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


                String query = "INSERT INTO jugadores (id, nombre ,edad, sueldo) VALUES (?, ?, ?, ?)";
                String id = JTextField1.getText().trim();
                String nombre = JTextField2.getText().trim();
                int edad = Integer.parseInt(JTextField3.getText().trim());
                double sueldo = Double.parseDouble(JTextField4.getText().trim());

                try(Connection connection = LogIn.ConexionBD.getConnection()){
                    PreparedStatement cadenaPreparada = connection.prepareStatement(query);

                    cadenaPreparada.setString(1,id);
                    cadenaPreparada.setString(2,nombre);
                    cadenaPreparada.setInt(3,edad);
                    cadenaPreparada.setDouble(4, sueldo);

                    cadenaPreparada.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Datos insertados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }catch (SQLException e3){
                    e3.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al insertar los datos... \nIngrese correctamente el ID", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
