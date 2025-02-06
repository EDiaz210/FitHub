package Entrenadores;
import Conexion.Conexion;
import LogIn.LogIn;
import Menus.MenuAdm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ActuAdm extends Conexion {
    public JPanel ATE;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton actualizarButton;
    private JTextField textField3;
    private JLabel rol;
    private JLabel IdAc;
    private JLabel nombre;


    public ActuAdm() {
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String queryUsuarios = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                String queryEntrenadoresSelect = "SELECT * FROM entrenadores";
                String queryEntrenadores = "UPDATE entrenadores SET telefono = ?, edad = ? WHERE entrenador_id = '" + textField1.getText() + "'";

                String cedula = textField1.getText().trim();
                String telefono = textField2.getText().trim();
                String edadText = textField3.getText().trim();


                // Verificar que los campos no estén vacíos
                if (cedula.isEmpty() || telefono.isEmpty() || edadText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!telefono.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                // Validar que la edad sea un número válido
                int edad;
                try {
                    edad = Integer.parseInt(edadText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


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

                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(queryEntrenadoresSelect)) {
                        try (ResultSet resultSet = statement.executeQuery(queryEntrenadoresSelect)) {
                            while (resultSet.next()) {
                                nombre.setText(resultSet.getString("nombre"));
                            }
                        }
                    }

                    // Actualizar en la tabla entrenadores
                    try (PreparedStatement stmtEntrenadores2 = connection.prepareStatement(queryEntrenadores)) {
                        stmtEntrenadores2.setString(1, telefono);
                        stmtEntrenadores2.setInt(2,edad);
                        stmtEntrenadores2.executeUpdate();
                    }

                    // Confirmar la transacción
                    connection.commit();
                    JOptionPane.showMessageDialog(null, "Datos del entrenador actualizados exitosamente.");

                } catch (Exception eX) {
                    eX.printStackTrace();
                    JOptionPane.showMessageDialog(null, "No se ha podido actualizar.");
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
