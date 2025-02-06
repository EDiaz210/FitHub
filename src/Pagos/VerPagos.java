package Pagos;

import LogIn.LogIn;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Rol.UsuarioSesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerPagos {
    public JPanel VRP;
    private JButton volverButton;
    private JPanel panel1;
    private JButton mostrarPagosButton;
    private JTable table1;
    private JScrollPane scrollPane;


    public VerPagos() {

        // Asegurar que la tabla esté dentro de un JScrollPane
        table1 = new JTable();
        scrollPane = new JScrollPane(table1);

        // Agregar JScrollPane al JPanel VRS
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.add(mostrarPagosButton, BorderLayout.NORTH);

        mostrarPagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = LogIn.ConexionBD.getConnection()) {
                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM pagos";
                    ResultSet resultSet = statement.executeQuery(query);

                    // Crear el modelo de la tabla con columnas definidas
                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("pagos_id");
                    tableModel.addColumn("costo_servicio");
                    tableModel.addColumn("costo_extras");
                    tableModel.addColumn("estado");

                    // Llenar la tabla con los datos del ResultSet
                    while (resultSet.next()) {
                        Object[] row = {
                                resultSet.getString("pagos_id"),
                                resultSet.getInt("costo_servicio"),
                                resultSet.getDouble("costo_extras"),
                                resultSet.getString("estado"),
                        };
                        tableModel.addRow(row);
                    }

                    // Asignar el modelo actualizado a la tabla
                    table1.setModel(tableModel);

                    // Actualizar la interfaz
                    scrollPane.revalidate();
                    scrollPane.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al cargar los servicios", "Error", JOptionPane.ERROR_MESSAGE);
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