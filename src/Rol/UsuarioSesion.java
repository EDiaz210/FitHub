package Rol;

public class UsuarioSesion {
    private static UsuarioSesion instancia;
    private String rolUsuario;

    private UsuarioSesion() { }

    public static UsuarioSesion getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSesion();
        }
        return instancia;
    }

    public void setRolUsuario(String rol) {
        this.rolUsuario = rol;

    }

    public String getRolUsuario() {
        System.out.printf(rolUsuario);
        return rolUsuario;
    }
}

