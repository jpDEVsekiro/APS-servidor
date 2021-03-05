package aps.unip.usuarios;

import java.util.HashMap;
import java.util.Map;

public class Usuarios {
	private static Map<String, Usuario> usuariosOnLine = new HashMap<String, Usuario>();
	
	public static void setUsuario(Usuario usuario) {
		usuariosOnLine.put(usuario.getNome(), usuario);
	}
	
	public Usuario getUsuario(String usuarioNome) {
		return usuariosOnLine.get(usuarioNome);
	}
	
	public void removeUsuario(String usuarioNome) {
		usuariosOnLine.remove(usuarioNome);
	}	
}
