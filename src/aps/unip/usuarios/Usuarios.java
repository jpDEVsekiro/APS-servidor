package aps.unip.usuarios;

import java.util.HashMap;
import java.util.Map;

public class Usuarios {
	private static Map<Integer, Usuario> usuariosOnLine = new HashMap<>();
	
	public static void addUsuario(Usuario usuario) {
		usuariosOnLine.put(usuario.getUsuarioId(), usuario);
	}
	
	public static Usuario getUsuario(int id) {
		return usuariosOnLine.get(id);
	}
	
	public static void removeUsuario(int id) {
		usuariosOnLine.remove(id);
	}	
}
