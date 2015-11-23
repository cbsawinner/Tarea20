package cl.inacap.clases;

import java.util.ArrayList;

public class Usuarios {
private int idVendedor;
private String Usuario;
private String Login;


private String Pass;


//Constructor por defecto
public Usuarios()
{
	
}
//Constructor con parámetros
public Usuarios(int idvendedor,String usuario, String login,String pass) {
	idVendedor=idvendedor;
	Usuario = usuario;
	Login=login;
	Pass = pass;
}
//Implementación de Accesadores Getters y Setters

public int getIdVendedor() {
	return idVendedor;
}
public void setIdVendedor(int idVendedor) {
	this.idVendedor = idVendedor;
}
public String getUsuario() {
	return Usuario;
}
public void setUsuario(String usuario) {
	Usuario = usuario;
}
public String getLogin() {
	return Login;
}
public void setLogin(String login) {
	Login = login;
}
public String getPass() {
	return Pass;
}
public void setPass(String pass) {
	Pass = pass;
}
//Se genera y obtiene la lista de usuarios
	public ArrayList<Usuarios> listaUsuarios()
	{
		ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
		
		Usuarios usuario = new Usuarios();
		usuario.idVendedor=1;
		usuario.Usuario = "Vendedor1";
		usuario.Login = "vendedor1";
		usuario.Pass = "ven001";
		
		lista.add(usuario);
		
		usuario = new Usuarios();
		usuario.idVendedor=2;
		usuario.Usuario = "Vendedor2";
		usuario.Login = "vendedor2";
		usuario.Pass = "ven002";
		
		lista.add(usuario);
		
		return lista;
	}
	
	//Se realiza la validacion del Login de usuario
	//Nos retornará el Código de Vendedor para 
	//Filtrar los clientes que sólo él puede 
	//Visitar
	public int validarLogin(String login, String pass)
	{
		Usuarios usuario;
		ArrayList<Usuarios> usuarios = listaUsuarios();
		int largo = usuarios.size();
		for(int i=0;i < largo;i++)
		{
			usuario = usuarios.get(i);
			if(usuario.Login.equals(login) && usuario.Pass.equals(pass))
			{
				return usuario.idVendedor;
			}
		}
		
		return -1;
	}
	

	

}
