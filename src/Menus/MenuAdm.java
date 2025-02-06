package Menus;

import Entrenadores.ActuAdm;
import Entrenadores.BuscarAdm;
import Entrenadores.CrearAdm;
import Entrenadores.ElimAdm;
import LogIn.LogIn;
import Miembros.ActuaMiem;
import Miembros.BuscarMiem;
import Miembros.CrearMiem;
import Miembros.EliminarMiem;
import Pagos.AgregarPago;
import Pagos.BuscarPagos;
import Pagos.VerPagos;
import Servicios.AgregarServ;
import Servicios.BuscarServ;
import Servicios.VerServi;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;


import javax.swing.*;
import com.itextpdf.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.*;

public class MenuAdm {
    public JPanel menu;
    private JTabbedPane tabbedPane1;
    private JButton crearMiembroButton;
    private JButton buscarMiembroButton;
    private JButton actualizarMiembroButton;
    private JButton eliminarMiembroButton;
    private JButton crearEntrenadorButton;
    private JButton buscarEntrenadorButton;
    private JButton actualizarEntrenadorButton;
    private JButton eliminarEntrenadorButton;
    private JButton volverButton;
    private JButton buscarServicioButton;
    private JButton verServiciosButton;
    private JButton crearServicioButton;
    private JButton buscarPagosButton;
    private JButton verPagosButton;
    private JButton crearPagoButton;
    private JButton cerrarSesionButton;
    private JButton gnerearReporteButton;

    public MenuAdm() {

        //Miembros
        crearMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Login");
                frame.setSize(450, 400);
                frame.setContentPane(new CrearMiem().CMIEM);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(crearMiembroButton)).dispose();
            }
        });
        buscarMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Buscar Miembro");
                frame.setSize(450, 400);
                frame.setContentPane(new BuscarMiem().BuscarMiem);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarMiembroButton)).dispose();
            }
        });
        actualizarMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Actualizar Miembro");
                frame.setSize(450, 400);
                frame.setContentPane(new ActuaMiem().actuMiem);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(actualizarMiembroButton)).dispose();

            }
        });
        eliminarMiembroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Eliminar Miembro");
                frame.setSize(500, 590);
                frame.setContentPane(new EliminarMiem().ElimMiem);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(eliminarMiembroButton)).dispose();
            }
        });


        //Entrenadores
        crearEntrenadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Crear Entrenador");
                frame.setSize(450, 400);
                frame.setContentPane(new CrearAdm().CADM);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(crearEntrenadorButton)).dispose();
            }
        });
        buscarEntrenadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Buscar Entrenador");
                frame.setSize(450, 400);
                frame.setContentPane(new BuscarAdm().BADM);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarEntrenadorButton)).dispose();
            }
        });
        actualizarEntrenadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Actualizar Entrenador");
                frame.setSize(450, 400);
                frame.setContentPane(new ActuAdm().ATE);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(actualizarEntrenadorButton)).dispose();
            }
        });
        eliminarEntrenadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Eliminar Entrenador");
                frame.setSize(500, 590);
                frame.setContentPane(new ElimAdm().ElimMiem);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(eliminarMiembroButton)).dispose();
            }
        });


        //Servicios

        crearServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Crear Servicio");
                frame.setSize(500, 400);
                frame.setContentPane(new AgregarServ().CRS);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(crearServicioButton)).dispose();
            }
        });
        verServiciosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Ver Servicios");
                frame.setSize(570, 450);
                frame.setContentPane(new VerServi().panel1);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(verServiciosButton)).dispose();
            }
        });
        buscarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Buscar Servicio");
                frame.setSize(500, 400);
                frame.setContentPane(new BuscarServ().BRS);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarServicioButton)).dispose();
            }
        });

        //Pagos


        crearPagoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Crear Pago");
                frame.setSize(500, 400);
                frame.setContentPane(new AgregarPago().ARP);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(crearPagoButton)).dispose();
            }
        });
        verPagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Ver Pagos");
                frame.setSize(500, 400);
                frame.setContentPane(new VerPagos().VRP);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(verPagosButton)).dispose();
            }
        });

        buscarPagosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                frame.setTitle("Bucar Pago");
                frame.setSize(500, 400);
                frame.setContentPane(new BuscarPagos().BRP);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarPagosButton)).dispose();
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
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(volverButton)).dispose();
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jp = new JFrame();
                jp.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logo.jpeg"));
                jp.setTitle("Log In");
                jp.setContentPane(new LogIn().Login);
                jp.setSize(500,590);
                jp.setResizable(false);
                jp.setLocationRelativeTo(null);
                jp.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(cerrarSesionButton)).dispose();
            }
        });
        gnerearReporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
