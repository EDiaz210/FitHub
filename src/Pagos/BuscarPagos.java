package Pagos;

import LogIn.LogIn;
import Menus.MenuAdm;
import Menus.MenuEntre;
import Rol.UsuarioSesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarPagos {
    public JPanel BRP;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton volverButton;
    private JLabel id;
    private JLabel nombre;
    private JLabel costo_servicio;
    private JLabel costo_extras;
    private JLabel estado;

}
