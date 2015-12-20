/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acciones;

import Controlador.ControladorEmail;
import Controlador.ControladorUsuario;
import Persistencia.Modelo.Usuario;
import Soporte.Encriptar;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Angelo
 */
public class UsuarioAction extends ActionSupport {

    private final int minutoMinimo = 15;
    private Usuario usuario;
    private String username;
    private String password;
    private final Map<String, Object> sesion = ActionContext.getContext().getSession();
    private final ControladorUsuario controladorUsuario = new ControladorUsuario();
    private String email, codigo, clave1, clave2;

    public boolean validarLogin() {
        boolean flag = true;
        if (password == null || password.trim().isEmpty()) {
            addFieldError("password", "Ingrese una clave.");
        }
        if (username == null || username.trim().isEmpty()) {
            addFieldError("username", "Ingrese el usuario.");
        }
        return flag;
    }

    public String login() {
        if (!validarLogin()) {
            return INPUT;
        }
        if (controladorUsuario.iniciarSesion(username, password)) {
            Usuario u = controladorUsuario.getUsuarioByNick(username);
            sesion.put("user", u);
        } else {
            addFieldError("", "Usuario o Contraseña incorrectos.");
            return INPUT;
        }
        return SUCCESS;
    }

    private boolean validarRecuperar() {
        boolean flag = true;
        if (email == null || email.trim().isEmpty()) {
            addFieldError("email", "Ingrese un email.");
            flag = false;
        } else {
            usuario = controladorUsuario.getUsuarioByEmail(email);
            if (usuario == null) {
                addFieldError("email", "Error al validar el usuario.");
                flag = false;
            }
        }
        return flag;
    }

    public String recuperarCuenta() {
        if (!validarRecuperar()) {
            return INPUT;
        }
        Date ahora = new Date();
        long segundos = (ahora.getTime() - usuario.getFechaCreacionCodigo().getTime()) / 1000;
        //900 segundos = 15 minutos
        long tiempoMinimo = 60 * minutoMinimo;
        if (segundos > tiempoMinimo) {
            String codigoCreado = controladorUsuario.crearCodigoRecuperacion(usuario);
            //enviar codigo al mail
            ControladorEmail ce = new ControladorEmail();
            ce.metodo(usuario.getEmail(), "Recuperar Contraseña", "El codigo para poder cambiar su contraseña es:"
                    + "\n" + codigoCreado + "");
            addActionMessage("Se ha enviado un email con el codigo a la direccion ingresada. Verifique en la carpeta de SPAM.");
        } else {
            addActionMessage("Ya se ha enviado un email con el codigo a la direccion ingresada. En " + minutoMinimo + " minutos podra generar otro codigo.");
        }

        return SUCCESS;
    }

    private boolean validarResetear() {
        boolean flag = true;
        if (email == null || email.trim().isEmpty()) {
            addFieldError("email", "Ingrese un email.");
            flag = false;
        } else {
            usuario = controladorUsuario.getUsuarioByEmail(email);
            if (usuario == null) {
                addFieldError("email", "Error al validar el usuario.");
                flag = false;
            }
        }
        if (codigo == null || codigo.trim().isEmpty()) {
            addFieldError("codigo", "Ingrese el codigo.");
            flag = false;
        }
        if (clave1 == null || clave1.trim().isEmpty()) {
            addFieldError("clave1", "Ingrese la nueva clave.");
            flag = false;
        } else {
            if (clave1.trim().length() < 5) {
                addFieldError("clave1", "Minimo 5 caracteres");
                flag = false;
            } else {
                if (clave2 == null || clave2.trim().isEmpty()) {
                    addFieldError("clave2", "Repita la clave.");
                    flag = false;
                } else {
                    if (clave2.trim().length() < 5) {
                        addFieldError("clave2", "Minimo 5 caracteres");
                        flag = false;
                    }
                }
            }
        }
        if (clave1 != null && clave2 != null && !clave1.isEmpty() && !clave2.isEmpty()) {
            if (!clave1.equals(clave2)) {
                addFieldError("clave2", "La clave no coincide.");
                flag = false;
            }
        }

        return flag;
    }

    public String resetearCuenta() {
        if (!validarResetear()) {
            return INPUT;
        }
        if (usuario.getCodigo().equals(codigo)) {
            String claveMD5 = Encriptar.encriptaEnMD5(clave1);
            usuario.setClave(claveMD5);
            controladorUsuario.actualizar(usuario);
            addActionMessage("Clave cambiada con exito!");
            //si se cambia la clave, cambio el codigo por otro..
            controladorUsuario.crearCodigoRecuperacion(usuario);
        } else {
            addActionError("El codigo ingresado no es correcto.");
            return INPUT;
        }
        return SUCCESS;
    }

    public boolean validarModificar() {
        boolean flag = true;
        Usuario user = (Usuario) sesion.get("user");
        if (user == null || user.getNombre().trim().isEmpty()) {
            addFieldError("", "Ingrese un nombre.");
            flag = false;
        }
        if (user == null || user.getApellido().trim().isEmpty()) {
            addFieldError("", "Ingrese un apellido.");
            flag = false;
        }
        if (user == null || user.getNick().trim().isEmpty()) {
            addFieldError("", "Ingrese un nick.");
            flag = false;
        }
        if (user == null || user.getEmail().trim().isEmpty()) {
            addFieldError("", "Ingrese un email.");
            flag = false;
        }
        return flag;
    }

    public String modificar() {

        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
