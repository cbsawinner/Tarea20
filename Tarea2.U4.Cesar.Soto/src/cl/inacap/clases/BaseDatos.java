package cl.inacap.clases;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper  {
	private final Context context;
	public static int version_db = 1;
	public BaseDatos(Context context, String name, CursorFactory factory, int version)
    {
		super(context, name, factory, version);
		this.context=context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
		if(db.isReadOnly())
		{
			db = getWritableDatabase();
		}
		String  query = "CREATE TABLE clientes (" +
				_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
				"idcliente NUMERIC,"+
				"nombre TEXT,"+
				"direccion TEXT,"+
				"ciudad TEXT,"+
				"telefono TEXT)";
        db.execSQL(query);
	}
	//Metodo para insertar un Cliente
	public void crearCliente(Clientes cliente)
	{
		ContentValues valores  = new ContentValues();
		valores.put("idcliente", cliente.getidCliente());
		valores.put("nombre", cliente.getNombre());
		valores.put("direccion", cliente.getDireccion());
		valores.put("ciudad", cliente.getCiudad());
		valores.put("telefono", cliente.getTelefono());
		long insert_id=this.getWritableDatabase().insert("clientes", null, valores);
		String filas[] = {_ID, "idcliente","nombre", "direccion","ciudad","telefono"};
		Cursor cursor= this.getWritableDatabase().query("clientes", filas,"_ID="+insert_id, null,null,null,null);
		cursor.moveToFirst();
		Clientes nuevo= cursorToCliente(cursor);
		cursor.close();
		

	}
	
	//Metodo para modificar Cliente
	public void modificarCliente(Clientes cli)
	{
	   long id= cli.getidCliente();
	   ContentValues valores  = new ContentValues();
	   	valores.put("idcliente", cli.getidCliente());
		valores.put("nombre",cli.getNombre());
		valores.put("direccion", cli.getDireccion());
		valores.put("ciudad", cli.getCiudad());
		valores.put("telefono", cli.getTelefono());
		this.getWritableDatabase().update("clientes", valores, "idcliente =" +id,null);
		
	}
	
	//Este Método retorna la lista de todos los clientes
		//Existentes en la la Tabla de Clientes
		public ArrayList<Clientes> getAllClientes(){
			
			ArrayList<Clientes> list= new ArrayList<Clientes>();
			String filas[] = {_ID,"idcliente", "nombre", "direccion","ciudad","telefono"};
			Cursor c = this.getReadableDatabase().query("clientes", filas,null, null, null, null, null);
			c.moveToFirst();
						
			while(!c.isAfterLast())
			{
				 	Clientes cli= cursorToCliente(c);
				 	list.add(cli);
				 	c.moveToNext();
			}
			c.close();
			return list;
		}
		
		//Metodo para eliminar un cliente
		public void EliminarCliente(Clientes cli)
		{
		   	long id= cli.getidCliente();
		   	this.getWritableDatabase().delete("clientes", "idcliente= "+id, null);
		}
		
		public Clientes Buscar(int idcliente)
		{
			
			Clientes cli=null;
			String filas[] = {_ID,"idcliente", "nombre", "direccion","ciudad","telefono"};
			 
			//Cursor c = this.getReadableDatabase().query("clientes", filas,"idcliente="+idcliente,null, null, null, null);
			Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM clientes WHERE idcliente = "+idcliente, null);
			if(c.moveToFirst())// se encontró registro
			{
				 cli= cursorToCliente(c);	 
				 c.close();	
			}
			 return cli;	 
			  
		}

	private Clientes cursorToCliente(Cursor cursor)
	{
		Clientes cli= new Clientes();
		cli.setidCliente(cursor.getInt(1));
		cli.setNombre(cursor.getString(2));
		cli.setDireccion(cursor.getString(3));
		cli.setCiudad(cursor.getString(4));
		cli.setTelefono(cursor.getString(5));
	    return cli;
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	if(newVersion > oldVersion)
	{
	   //db.execSQL(UPDATE_DB);
	}
	}
	public void abrir(){
		this.getWritableDatabase();
	}
	
	
	public void cerrar(){
		this.close();
	}

}
