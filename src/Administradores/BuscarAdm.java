package Administradores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarAdm {
    private JTextField textField1;
    private JButton buscarButton;
    public JPanel BADM;
    private JLabel id;
    private JLabel nombre;
    private JLabel edad;
    private JLabel sueldo;

    public BuscarAdm() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = LogIn.ConexionBD.getConnection()) {


                    String query = "SELECT * FROM jugadores WHERE nombre = '" + textField1.getText() + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);


                    while (resultSet.next()) {
                        id.setText(resultSet.getString("id"));
                        nombre.setText(resultSet.getString("nombre"));
                        edad.setText(resultSet.getString("edad"));
                        sueldo.setText(resultSet.getString("sueldo"));

                    }

                } catch (SQLException ex) {

                    ex.printStackTrace();
                }
            }
        });
    }
}
