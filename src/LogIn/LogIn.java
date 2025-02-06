package LogIn;
import Conexion.Conexion;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Menus.MenuMiembros;
import Rol.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn extends Conexion{
    private JTextField textField1;
    private JLabel ImagenLogin;
    private JComboBox comboBox1;
    private JButton ingresarButton;
    public JPanel Login;


    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public LogIn() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verificar que el campo no este vacío
                String cedula = textField1.getText().trim();

                if (cedula.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que la cedula contenga exactamente 10 dígitos numéricos
                /*if (!cedula.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe contener exactamente 10 dígitos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }*/

                String seleccionado = (String) comboBox1.getSelectedItem();
                UsuarioSesion.getInstancia().setRolUsuario(seleccionado);

                try (Connection connection = connect()) {
                    Statement statement = connection.createStatement();
                    String query = "SELECT * FROM usuarios WHERE cedula_usuario = '" + textField1.getText() + "'";
                    ResultSet resultSet = statement.executeQuery(query);


                    if(resultSet.next()) {
                        if (comboBox1.getSelectedItem().equals(resultSet.getString("rol"))) {
                            if (textField1.getText().equals(resultSet.getString("cedula_usuario"))) {
                                if (resultSet.getString("rol").equals("Administrador")) {
                                    System.out.println("Ingresaste a modo administrador ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(450, 400);
                                    frame.setContentPane(new MenuAdm().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setResizable(false);
                                    frame.setLocationRelativeTo(null);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                } else if (resultSet.getString("rol").equals("Miembro")) {
                                    System.out.println("Ingresaste a modo usuario ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(450, 400);
                                    frame.setContentPane(new MenuMiembros().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setResizable(false);
                                    frame.setLocationRelativeTo(null);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                }else if (resultSet.getString("rol").equals("Entrenador")) {
                                    System.out.println("Ingresaste a entrenador ");
                                    JFrame frame = new JFrame();
                                    frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                                    frame.setTitle("Login");
                                    frame.setSize(450, 400);
                                    frame.setContentPane(new MenuEntre().menu);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setResizable(false);
                                    frame.setLocationRelativeTo(null);
                                    frame.setVisible(true);
                                    ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();

                                }

                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Digite correctamente las credenciales");

                }
            }
        });
    }
}
