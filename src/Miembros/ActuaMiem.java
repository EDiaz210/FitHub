package Miembros;
import LogIn.LogIn;
import Menus.MenuAdm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActuaMiem {
    public JPanel actuMiem;
    private JTextField textField1;
    private JTextField textField2;
    private JButton volverButton;
    private JButton actualizarButton;
    private JTextField textField3;
    private JLabel rol;
    private JLabel IdAc;
    private JLabel nombre;


    public ActuaMiem() {
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String queryUsuarios = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                String queryMiembrosSelect = "SELECT * FROM miembros";
                String queryMiembros = "UPDATE miembros SET fecha_ingreso = ?, telefono = ? WHERE miembro_id = '" + textField1.getText() + "'";

                String cedula = textField1.getText().trim();
                String telefono = textField2.getText().trim();
                String fecha_ingreso = textField3.getText().trim();

                // Verificar que los campos no estén vacíos
                if (cedula.isEmpty() || telefono.isEmpty() || fecha_ingreso.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!telefono.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);  // No aceptar fechas inválidas (como "2024-02-30")

                // Validar la fecha
                try {
                    sdf.parse(fecha_ingreso); // Intenta parsear la fecha
                    JOptionPane.showMessageDialog(null, "Fecha válida.");
                } catch (ParseException eX) {
                    JOptionPane.showMessageDialog(null, "Fecha inválida. Asegúrate de usar el formato yyyy-MM-dd", "Error", JOptionPane.ERROR_MESSAGE);



                    try (Connection connection = LogIn.ConexionBD.getConnection()) {
                    Statement statement = connection.createStatement();
                    connection.setAutoCommit(false);

                    // Actualizar en la tabla usuarios
                    try (ResultSet resultSet = statement.executeQuery(queryUsuarios)){
                        while (resultSet.next()) {
                            IdAc.setText(resultSet.getString("cedula_usuario"));
                            rol.setText(resultSet.getString("rol"));
                        }
                    }

                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(queryMiembrosSelect)) {
                        try (ResultSet resultSet = statement.executeQuery(queryMiembrosSelect)) {
                            while (resultSet.next()) {
                                nombre.setText(resultSet.getString("nombre"));
                            }
                        }
                    }

                    // Actualizar en la tabla entrenadores
                    try (PreparedStatement stmtEntrenadores2 = connection.prepareStatement(queryMiembros)) {
                        stmtEntrenadores2.setString(1, fecha_ingreso);
                        stmtEntrenadores2.setString(2, telefono);
                        stmtEntrenadores2.executeUpdate();
                    }

                    // Confirmar la transacción
                    connection.commit();
                    JOptionPane.showMessageDialog(null, "Datos del miembro actualizados exitosamente.");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    try (Connection connection = LogIn.ConexionBD.getConnection()) {
                        connection.rollback();
                    } catch (Exception rollbackEx) {
                        rollbackEx.printStackTrace();
                    }
                }
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

