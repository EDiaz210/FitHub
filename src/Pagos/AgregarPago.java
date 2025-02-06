package Pagos;
import LogIn.LogIn;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgregarPago {
    public JPanel ARP;
    private JTextField textField1;
    private JTextField textField2;
    private JButton ingresarButton;
    private JButton volverButton;
    private JTextField textField3;


    public AgregarPago() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Serviciosquery = "INSERT INTO pagos (pagos_id, costo_servicio, costo_extras, estado) VALUES (?, ?, ?, ?)";

                String id = textField1.getText().trim();
                int costo_servicio = 30;
                String  costo_extrasString = textField2.getText().trim();
                String estado = textField3.getText().trim();

                // Verificar que los campos no estén vacíos
                if (id.isEmpty() || estado.isEmpty() || costo_extrasString.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que el teléfono contenga exactamente 10 dígitos numéricos
                if (!id.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double costo_extras;

                try {
                    costo_extras = Double.parseDouble(costo_extrasString); // Convertir a double
                } catch (NumberFormatException eX) {
                    JOptionPane.showMessageDialog(null, "El costo extra debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try (Connection connection = LogIn.ConexionBD.getConnection()) {
                    connection.setAutoCommit(false);


                    // Insertar en la tabla servicios
                    try (PreparedStatement stmtEntrenadores = connection.prepareStatement(Serviciosquery)) {
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
    }
}