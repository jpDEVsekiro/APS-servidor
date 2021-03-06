package aps.unip.usuarios;

import java.util.HashMap;
import java.util.Map;

public class Usuarios {
	private static Map<String, Usuario> usuariosOnLine = new HashMap<>();
	
	public static void addUsuario(Usuario usuario) {
		usuariosOnLine.put(usuario.getNome(),usuario);
	}
	
	public static Usuario getUsuario(String usuarioApelido) {
		return usuariosOnLine.get(usuarioApelido);
	}
	
	public void removeUsuario(String usuarioNome) {
		usuariosOnLine.remove(usuarioNome);
	}	
}
