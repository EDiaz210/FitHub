package Menus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin {
    public JPanel menu;
    private JTabbedPane tabbedPane1;
    private JButton crearMiembroButton;
    private JButton eliminarUsuarioButton;
    private JButton actualizarMiembroButton;
    private JButton buscarMiembroButton;
    private JButton crearAdministradorButton;
    private JButton eliminarAdministradorButton;
    private JButton buscarAdministradorButton;
    private JButton actualizarAdministradorButton;
    private JButton verPagosButton;
    private JButton buscarPagoButton;
    private JButton agregarPagoButton;
    private JButton agregarServicioButton;
    private JButton verServiciosButton;
    private JButton buscarServicioButton;

    public MenuAdmin() {
        crearMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
            });

        eliminarUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actualizarMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buscarMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
