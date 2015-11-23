package cl.inacap.clases;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import android.database.sqlite.SQLiteOpenHelper;

public class Clientes {
//Definición de Atributos 
 private int idcliente;
 private String Nombre;
 private String Direccion;
 private String Ciudad;
 private String Telefono;
 

 //Constructor por Defecto
 
 
 public Clientes(){
		
	
 }

 
 public Clientes(int id, String nombre, String direccion, String ciudad, String telefono) {
	
	idcliente = id;
	Nombre = nombre;
	Direccion = direccion;
	Ciudad = ciudad;
	Telefono = telefono;
}


//Implementación de Accesadores Getters y Setters
 public String getNombre() {
	return Nombre;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getDireccion() {
	return Direccion;
}
public void setDireccion(String direccion) {
	Direccion = direccion;
}
public String getTelefono() {
	return Telefono;
}
public void setTelefono(String telefono) {
	Telefono = telefono;
}
public int getidCliente() {
	return idcliente;
}
public void setidCliente(int id) {
	idcliente = id;
}
public void setCiudad(String ciudad)
{
	Ciudad=ciudad;
}
public String getCiudad()
{
   return Ciudad;	
}





public String toString()
{
	return String.valueOf(this.idcliente+" : "+this.Nombre+" - " + this.Direccion+" - "+this.Telefono); 
}


	

}
