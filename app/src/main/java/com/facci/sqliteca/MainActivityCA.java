import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

uttpackage com.facci.sqliteca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.facci.sqliteca.DBHelper;

public class MainActivityCA extends AppCompatActivity {
    DBHelper dbSQLite;

    EditText txtNombre,txtApellidos,txtRecintoElectoral,txtAñoNacimiento,txtID;

    Button btnInsertar, btnModificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_c);

     dbSQLite = new DBHelper(this);

    }

    public void InsertarClick(View v){

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellidos = (EditText)findViewById(R.id.txtApellidos);
        txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        txtAñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);

        boolean estaInsertado = dbSQLite.insertar(txtNombre.getText().toString(),txtApellidos.getText().toString(),txtRecintoElectoral.getText(),Integer.parseInt(txtAñoNacimiento.getText().toString()));

        if(estaInsertado)
            Toast.makeText(MainActivityCA.this,"Datos insertados", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityCA.this,"Oh, esto es algo embarazoso, a ocurrido un error", Toast.LENGTH_SHORT).show();
    }

    public void verTodosClick(View v){

        Cursor res = dbSQLite.selectVerTodos();
        if(res.getCount()== 0){
            mostrarMensaje("Error","No se encontraron datos");
            return;
        }

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellidos : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año Nacimiento : "+res.getInt(4)+"\n");
        }

          mostrarMensaje("Registros",buffer.toString());

        public void mostrarMensaje(String titulo, String Mensaje) {

        }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(titulo);
            builder.setMessage(Mensaje);
            builder.show();

    }

        public void eliminarRegistroClick(View v){

         txtID = (EditText)(findViewById(R.id.textID);

         Integer registrosEliminados = dbSQLite.eliminarRegistro(txtID.getText().toString());

            if(registrosEliminados > 0){

                Toast.makeText(MainActivityCA.this,"Registro (s) Eliminado (s)",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivityCA.this,"ERROR, NO Eliminado (s)",Toast.LENGTH_SHORT).show();
            }
        }

       public void modificarRegistroClick(View v){

           txtNombre = (EditText)findViewById(R.id.txtNombre);
           txtApellidos = (EditText)findViewById(R.id.txtApellidos);
           txtRecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
           txtAñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);
           txtID = (EditText) findViewById(R.id.txtID);


          boolean estaActualizado = dbSQLite.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(),txtApellidos.getText().toString(),txtRecintoElectoral.getText().toString(),Integer.parseInt(txtAñoNacimiento.getText().toString());

           if (estaActualizado == true){
               Toast.makeText(MainActivityCA.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(MainActivityCA.this,"OH,NO !! Registro No Actulizado",Toast.LENGTH_SHORT).show();
           }
       }
}
