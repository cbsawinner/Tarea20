package cl.inacap.apps;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cl.inacap.clases.BaseDatos;
import cl.inacap.clases.Clientes;



public class ListadoClientes extends Activity {
	private ArrayAdapter<Clientes> adapter;
	ArrayList<Clientes> lista=new ArrayList<Clientes>();
	BaseDatos db=new BaseDatos(this,"BD",null,1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_clientes);
		ListView lv_clientes = (ListView)findViewById(R.id.ls_Clientes);
	    //Cambiamos el fondo de color Gris
	    RelativeLayout layout=(RelativeLayout)findViewById(R.id.layout01);
        layout.setBackgroundColor(Color.DKGRAY);
		//Creamos una instancia de Clientes 
        Clientes cliente = new Clientes();
		//Generamos la Lista Filtrada por Vendedor	
		db.abrir();
		ArrayList<Clientes> clientes = db.getAllClientes();
	    adapter = new ArrayAdapter<Clientes>(getApplicationContext(), android.R.layout.simple_spinner_item, clientes);
		//Cargamos el Adaptador con el resultado
		//De los clientes filtrados.
		lv_clientes.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	    db.cerrar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listado_clientes, menu);
		return true;
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
