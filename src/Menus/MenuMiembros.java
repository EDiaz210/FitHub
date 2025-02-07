package Menus;

import Conexion.Conexion;
import LogIn.LogIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuMiembros extends JFrame {
    public JPanel menu;
    private JTabbedPane tabbedPane1;
    private JButton pagarButton;
    private JList listaLunes;
    private JList listaMartes;
    private JList listaMierco;
    private JList listaJueves;
    private JList listaViernes;
    private JList listaSabado;
    private JButton comprarSuple;
    private JButton comprarDieta;
    private JButton comprarMembresia;
    private JButton verRutina;
    private JLabel etiquetaMembresia;
    private JLabel etiquetaDieta;
    private JLabel etiquetaSuple;
    private JButton cerrarSesiónButton;
    private JLabel etiquetaTotal;
    private JLabel membresía;
    private JLabel dieta;
    private JLabel suplementacion;
    private JLabel total;
    private JTextArea series;
    private JTextArea repeticiones;
    private JLabel etiquetaSeries;
    private JLabel etiquetaRepes;

    public MenuMiembros() {
        comprarMembresia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                membresía.setText("30"); //
                actualizarTotal();
            }
        });

        comprarDieta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dieta.setText("30"); //
                actualizarTotal();
            }
        });

        comprarSuple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suplementacion.setText("120");
                actualizarTotal();
            }
        });

        verRutina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String queryHombre1 = "SELECT ejercicio_uno, ejercicio_dos, ejercicio_tres, ejercicio_cuatro, ejercicio_cinco, ejercicio_seis, ejercicio_siete FROM rutina_hombre WHERE rutina_id = 1";
                String queryHombre2 = "SELECT ejercicio_uno, ejercicio_dos, ejercicio_tres, ejercicio_cuatro, ejercicio_cinco, ejercicio_seis, ejercicio_siete FROM rutina_hombre WHERE rutina_id = 2";
                String queryMujer1 = "SELECT ejercicio_uno, ejercicio_dos, ejercicio_tres, ejercicio_cuatro, ejercicio_cinco, ejercicio_seis, ejercicio_siete FROM rutina_mujer WHERE rutina_id = 1";
                String queryMujer2 = "SELECT ejercicio_uno, ejercicio_dos, ejercicio_tres, ejercicio_cuatro, ejercicio_cinco, ejercicio_seis, ejercicio_siete FROM rutina_mujer WHERE rutina_id = 2";

                try (Connection conexion = Conexion.connect()) {
                    PreparedStatement stmtHombre1 = conexion.prepareStatement(queryHombre1);
                    ResultSet rsHombre1 = stmtHombre1.executeQuery();
                    if (rsHombre1.next()) {
                        agregarEjerciciosALista(rsHombre1, listaLunes);
                        agregarEjerciciosALista(rsHombre1, listaJueves);
                    }

                    PreparedStatement stmtHombre2 = conexion.prepareStatement(queryHombre2);
                    ResultSet rsHombre2 = stmtHombre2.executeQuery();
                    if (rsHombre2.next()) {
                        agregarEjerciciosALista(rsHombre2, listaMartes);
                        agregarEjerciciosALista(rsHombre2, listaViernes);
                    }

                    PreparedStatement stmtMujer1 = conexion.prepareStatement(queryMujer1);
                    ResultSet rsMujer1 = stmtMujer1.executeQuery();
                    if (rsMujer1.next()) {
                        agregarEjerciciosALista(rsMujer1, listaMierco);
                    }

                    PreparedStatement stmtMujer2 = conexion.prepareStatement(queryMujer2);
                    ResultSet rsMujer2 = stmtMujer2.executeQuery();
                    if (rsMujer2.next()) {
                        agregarEjerciciosALista(rsMujer2, listaSabado);
                    }

                    series.setText("3 o 4");
                    repeticiones.setText("8-12");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Esto falta por implementar :)", "No nos ponga 0 por esto por favor", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jp = new JFrame();
                jp.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                jp.setTitle("Log In");
                jp.setContentPane(new LogIn().Login);
                jp.setSize(500, 590);
                jp.setResizable(false);
                jp.setLocationRelativeTo(null);
                jp.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(cerrarSesiónButton)).dispose();
            }
        });
    }

    private void agregarEjerciciosALista(ResultSet rs, JList lista) throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<>();

        model.addElement(rs.getString("ejercicio_uno"));
        model.addElement(rs.getString("ejercicio_dos"));
        model.addElement(rs.getString("ejercicio_tres"));
        model.addElement(rs.getString("ejercicio_cuatro"));
        model.addElement(rs.getString("ejercicio_cinco"));
        model.addElement(rs.getString("ejercicio_seis"));
        model.addElement(rs.getString("ejercicio_siete"));

        lista.setModel(model);
    }

    private void actualizarTotal() {
        try {
            int valorMembresia = getValorEtiqueta(membresía);
            int valorDieta = getValorEtiqueta(dieta);
            int valorSuplementacion = getValorEtiqueta(suplementacion);

            int totalValue = valorMembresia + valorDieta + valorSuplementacion;
            total.setText(String.valueOf(totalValue));

        } catch (NumberFormatException ex) {
            total.setText("Error en los valores");
        }
    }

    private int getValorEtiqueta(JLabel etiqueta) {
        String texto = etiqueta.getText();
        if (texto.isEmpty()) {
            return 0; //
        }
        return Integer.parseInt(texto);
    }
}
