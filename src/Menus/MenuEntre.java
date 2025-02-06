package Menus;

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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEntre {
    public JPanel menu;
    private JTabbedPane tabbedPane1;
    private JButton crearMiembroButton;
    private JButton buscarMiembroButton;
    private JButton actualizarMiembroButton;
    private JButton eliminarMiembroButton;
    private JButton volverButton;
    private JButton buscarServicioButton;
    private JButton verServiciosButton;
    private JButton crearServicioButton;
    private JButton buscarPagosButton;
    private JButton verPagosButton;
    private JButton crearPagoButton;
    private JButton cerrarSesionButton;
}

