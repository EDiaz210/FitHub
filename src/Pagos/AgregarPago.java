package Pagos;
import Conexion.Conexion;
import LogIn.LogIn;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Rol.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AgregarPago extends Conexion {
    public JPanel ARP;
    private JTextField textField1;
    private JButton ingresarButton;
    private JButton volverButton;
    private JComboBox comboBox1;


    public AgregarPago() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Pagosquery = "INSERT INTO pagos (pagos_id, costo_servicio, costo_extras, estado) VALUES (?, ?, ?, ?)";
                String Serviociosquery2 ="Select * from servicios";

                String id = textField1.getText().trim();
                int costo_servicio = 30;
                String estado = (String) comboBox1.getSelectedItem();
                double costo_extras = 0;


                // Verificar que los campos no estén vacíos
                if (id.isEmpty() || estado.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!id.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try (Connection connection = connect()) {
                    Statement statement = connection.createStatement();
                    connection.setAutoCommit(false);


                    try (PreparedStatement stmtServicios = connection.prepareStatement(Pagosquery)) {
                        try (ResultSet resultSet = statement.executeQuery(Serviociosquery2)) {
                            while (resultSet.next()) {
                                costo_extras = Double.parseDouble(resultSet.getString("costo_extras"));
                            }
                        }
                    }
                    // Insertar en la tabla servicios
                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(Pagosquery)) {
                        stmtEntrenadores.setString(1, id);
                        stmtEntrenadores.setInt(2,costo_servicio);
                        stmtEntrenadores.setDouble(3, costo_extras);
                        stmtEntrenadores.setString(4, estado);
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