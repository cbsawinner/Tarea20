package cl.inacap.apps;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import cl.inacap.clases.Clientes;
import cl.inacap.clases.BaseDatos;
import android.view.View;
import android.view.View.OnClickListener;



public class MantClientes extends Activity {
	//Definicion de Objetos para casting desde la activity
	private ImageButton btnListado;
	private ImageButton btnAgregar;
	private ImageButton btnBuscar;
	private ImageButton btnNuevo;
	private ImageButton btnModificar;
	private ImageButton btnEliminar;
	private ImageButton btnMapas;
	private EditText txtcodigo;
	private EditText txtnombre;
	private EditText txtdireccion;
	private EditText txtciudad;
	private EditText txttelefono;
	ArrayList<Clientes> lista=new ArrayList<Clientes>();
	Clientes cliente;
	BaseDatos db=new BaseDatos(this,"BD",null,1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clientes);
		
		/*Casteamos los valores de la activity considerando
		 los nombres de los objetos de las cajas de texto 
		 */
		txtcodigo=(EditText)findViewById(R.id.txtCodCliente);
		txtnombre=(EditText)findViewById(R.id.txtNombre);
		txtdireccion=(EditText)findViewById(R.id.txtDireccion);
		txtciudad=(EditText)findViewById(R.id.txtCiudad);
		txttelefono=(EditText)findViewById(R.id.txtFono);
		/*Casteamos los valores de la activity considerando
		 los nombres de los objetos de los ImageButtons 
		 */
		btnListado=(ImageButton)findViewById(R.id.btnListar);
		btnAgregar=(ImageButton)findViewById(R.id.btnGuardar);
		btnBuscar=(ImageButton)findViewById(R.id.btnBuscar);
		btnNuevo= (ImageButton)findViewById(R.id.btnNuevo);
		btnModificar=(ImageButton)findViewById(R.id.btnActualizar);
		btnEliminar= (ImageButton)findViewById(R.id.btnEliminar);
		btnMapas=(ImageButton)findViewById(R.id.btnMapas);
		//Implementamos el Metodo de Listar Clientes
		//No logramos enviar el arraylist por el Intent
		//Procedemos a rescatar los valores y enviarlos 
		//a través de un String
		btnListado.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    		 Intent intent = new Intent(MantClientes.this, ListadoClientes.class);
		    	 startActivity(intent);    		
	    	 }
	    	 });
		//Implementamos la Inserción en la base de datos
		btnAgregar.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    	    try{
	    	    	
	    	     int cod=Integer.parseInt(txtcodigo.getText().toString());
	    		 cliente= new Clientes(cod,txtnombre.getText().toString(),txtdireccion.getText().toString(),txtciudad.getText().toString(),txttelefono.getText().toString());
	    		 db.abrir();
	    		 db.crearCliente(cliente);
	    		 db.cerrar();
	    	     Toast.makeText(MantClientes.this, "Registro Agregado", Toast.LENGTH_SHORT).show();
	    	    }catch(Exception ex)
	    	    {
	    	    	
	    	    	Toast.makeText(MantClientes.this, "Error -- Registro No Insertado", Toast.LENGTH_SHORT).show();
	    	    }
	    	 }
	    	 }); 
		//Implementamos la búsqueda sobre el ArrayList
		 btnBuscar.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    		 int cod=Integer.parseInt(txtcodigo.getText().toString());
	    		 db.abrir();
	    		 cliente= db.Buscar(cod);
	    		 if(cliente!=null)//Registro Encontrado
	    		 {
	    			
	    			txtnombre.setText(cliente.getNombre()); 
	    			txtdireccion.setText(cliente.getDireccion());
	    			txtciudad.setText(cliente.getCiudad());
	    			txttelefono.setText(cliente.getTelefono());
	    		 }
	    		 else
	    			 Toast.makeText(MantClientes.this, "Registro No Encontrado", Toast.LENGTH_SHORT).show();
	    		 db.cerrar();
	    	 }
		 
	       }); 
		//Implementamos botón Nuevo que limpia los objetos
		//de la activity
		btnNuevo.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    		 Limpiar();
	    	 }
	       });
		//Implementamos el botón que modifica un cliente
		 btnModificar.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    	  try{
	    		 int cod=Integer.parseInt(txtcodigo.getText().toString());
	    		 db.abrir();
	    		 Clientes cli=db.Buscar(cod);
	    		 if(cli!=null)//Registro Encontrado
	    		 {
	    		    cliente= new Clientes(cod,txtnombre.getText().toString(),txtdireccion.getText().toString(),txtciudad.getText().toString(),txttelefono.getText().toString()); 
	    		    db.modificarCliente(cliente);
	    		    db.cerrar();
	    	        Toast.makeText(MantClientes.this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
	    		 }
	    	    }catch(Exception ex)
	    	    {
	    	    	
	    	    	Toast.makeText(MantClientes.this, "Error -- Registro No Modificado", Toast.LENGTH_SHORT).show();
	    	    }

	    	 }
	       }); 
		//Implementamos el botón que Elimina un cliente
		
	     btnEliminar.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    		 int cod=Integer.parseInt(txtcodigo.getText().toString());
	    		 db.abrir();
	    		 Clientes cli=db.Buscar(cod);
	    		 if(cli!=null)//Registro Encontrado
	    		 {
	    			 db.abrir();
	    			 cliente= new Clientes(cod,txtnombre.getText().toString(),txtdireccion.getText().toString(),txtciudad.getText().toString(),txttelefono.getText().toString());
	    			 db.EliminarCliente(cliente);
	    			 Toast.makeText(MantClientes.this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
	    			 Limpiar();
	    		 }
	    		 db.cerrar();
	    	 }

	    	 
	       }); 
		btnMapas.setOnClickListener(new OnClickListener() {
	    	 @Override
	    	 public void onClick(View arg0) {
	    		 Intent intent = new Intent(MantClientes.this, VerClientes.class);
	 	    	startActivity(intent);
	    	 }
	       });
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Implementación de Método para limpiar el Formulario
	//De esta manera se puede llamar al método varias veces
public void Limpiar()
{
	txtcodigo.setText("");
	 txtnombre.setText("");
	 txtdireccion.setText("");
	 txtciudad.setText("");
	 txttelefono.setText("");	
}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
