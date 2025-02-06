package Menus;

import Conexion.Conexion;
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

public class MenuAdm extends Conexion {
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
                frame.setSize(450, 400);
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
                String rutaArchivo = "C:\\Users\\elkin\\Desktop\\BaseDeDatos.pdf";

                try (Connection connection = connect()) {
                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
                    document.open();

                    // Agregar el título "Reportes" al inicio
                    Font fontTituloR = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD); // Tamaño 18 y negrita
                    Paragraph tituloR = new Paragraph("Reportes", fontTituloR);
                    tituloR.setAlignment(Element.ALIGN_CENTER); // Alineación centrada
                    tituloR.setSpacingAfter(20); // Espacio después del título
                    document.add(tituloR);

                    // Obtener todas las tablas de la base de datos
                    DatabaseMetaData metaData = connection.getMetaData();
                    ResultSet tablas = metaData.getTables(null, null, "%", new String[]{"TABLE"});

                    while (tablas.next()) {
                        String nombreTabla = tablas.getString("TABLE_NAME"); // Obtener nombre de la tabla
                        System.out.println("Tabla: " + nombreTabla);

                        // Agregar título de la tabla
                        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
                        Paragraph titulo = new Paragraph(nombreTabla.toUpperCase(), fontTitulo);
                        titulo.setAlignment(Element.ALIGN_CENTER);
                        titulo.setSpacingAfter(10);
                        document.add(titulo);

                        // Crear tabla PDF
                        PdfPTable pdfTable = new PdfPTable(getColumnCount(connection, nombreTabla));
                        pdfTable.setWidthPercentage(100);

                        // Obtener datos de la tabla
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + nombreTabla);
                        ResultSetMetaData rsmd = resultSet.getMetaData();
                        int columnCount = rsmd.getColumnCount();

                        // Agregar nombres de columnas al PDF
                        for (int i = 1; i <= columnCount; i++) {
                            PdfPCell cell = new PdfPCell(new Phrase(rsmd.getColumnName(i)));
                            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            pdfTable.addCell(cell);
                        }

                        // Agregar datos de la tabla
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                pdfTable.addCell(resultSet.getString(i));
                            }
                        }

                        document.add(pdfTable);
                        document.add(new Paragraph("\n\n")); // Espacio entre tablas
                    }

                    document.close();
                    JOptionPane.showMessageDialog(null, "PDF generado con éxito: " + rutaArchivo);

                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al generar el PDF.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Método para obtener la cantidad de columnas de una tabla
            private static int getColumnCount(Connection connection, String tableName) throws SQLException {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1");
                return rs.getMetaData().getColumnCount();
            }
        });
    }
}
