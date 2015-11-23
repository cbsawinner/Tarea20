package cl.inacap.apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import cl.inacap.clases.BaseDatos;
import cl.inacap.clases.Clientes;

public class VerClientes extends FragmentActivity {
	ArrayList<Clientes> lista=new ArrayList<Clientes>();
	Clientes cliente;
	BaseDatos db=new BaseDatos(this,"BD",null,1);
	static final LatLng OSORNO = new LatLng(-40.581265, -73.111785);
	
	 private double latitude;
	 private double longitude;
	 private GoogleMap googleMap;
	   
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_ver_clientes);
	      
	     try {
	    	 setUpMapIfNeeded();
	    	 setMarker(OSORNO,"INACAP","Osorno"); 
	      // Move the camera instantly to hamburg with a zoom of 15.
	         googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(OSORNO, 19));
	         CargarCoordenadas();
	      // Zoom in, animating the camera.
	         googleMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	      } 
	   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	private void setUpMapIfNeeded() {
		// Configuramos el objeto GoogleMaps con valores iniciales.
		   if (googleMap == null) {
		      //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
			   googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		      // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
		      if (googleMap != null) {
		        // El objeto GoogleMap ha sido referenciado correctamente 
		        //ahora podemos manipular sus propiedades
		        
		        //Seteamos el tipo de mapa 
		    	  googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		        	
		        //Activamos la capa o layer MyLocation
		    	  googleMap.setMyLocationEnabled(true);
		      }
		   }
		}
	
	private void setMarker(LatLng position, String titulo, String info) {
		  // Agregamos marcadores para indicar sitios de interéses.
		  Marker myMaker = googleMap.addMarker(new MarkerOptions()
		       .position(position)
		       .title(titulo)  //Agrega un titulo al marcador
		       .snippet(info)   //Agrega información detalle relacionada con el marcador 
		       .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))); //Color del marcador
		}
	
	//Metodo para Transformar una dirección de Texto en Coordenadas
	public void CoordGPS(String address,Context context,String nombre){
		  Geocoder gc=new Geocoder(context,Locale.ENGLISH);
		  List<Address> addresses;
		  try {
		    addresses=gc.getFromLocationName(address,5);
		    if (addresses.size() > 0) {
		      this.latitude=addresses.get(0).getLatitude();
		      this.longitude=addresses.get(0).getLongitude();
		       LatLng coord=new LatLng(latitude,longitude);
		       //Agrega el Marcador en el Mapa.
		       setMarker(coord,address,nombre); 
		    }
		  }
		 catch (  IOException e) {
		    e.printStackTrace();
		  }
		}
	
	
/*Metodo que recibe la lista de clientes con direcciones
 * y genera las coordenadas de acuerdo a la direccion
 */
public void CargarCoordenadas()
{
	String direccion="";
	lista= db.getAllClientes();
	db.abrir();
	for(int i=0;i<lista.size();i++)
	{
		cliente=lista.get(i);
		direccion=cliente.getDireccion()+","+cliente.getCiudad()+",Chile";
		CoordGPS(direccion,this,cliente.getNombre());
	}
	db.cerrar();
}
}