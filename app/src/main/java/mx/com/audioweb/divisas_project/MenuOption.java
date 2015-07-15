package mx.com.audioweb.divisas_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.json.JSONException;

import java.util.ArrayList;


public class MenuOption extends Activity {

    Button ca, div, salir;
    private ArrayList<Contacto> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ca = (Button) findViewById(R.id.caButton);
        div = (Button) findViewById(R.id.divButton);
        salir = (Button) findViewById(R.id.salirButton);

        ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuOption.this, CrearAlerta.class);
                startActivity(i);
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divisas();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void divisas() {
        new MisAlertasTask(getApplicationContext(), this.contactos).execute("1");

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MenuOption.this, Login.class);
        startActivity(i);
    }

}

class MisAlertasTask extends AsyncTask<String, Void, Boolean> {
    private Context context;
    private ArrayList<Contacto> contactos;
    private ArrayList<Contacto> divisa;
    private Bundle informacion;

    public MisAlertasTask(Context context, ArrayList<Contacto> contactos) {
        this.context = context;
        this.contactos = contactos;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String usr_id = params[0];

        try {
            ClienteHttp clienteHttp = new ClienteHttp();
            this.contactos = clienteHttp.contacto(usr_id);
            if (this.contactos == null) {
                Log.d("Message", "No existen contactos en tu libreta de direcciones");
            } else {
                informacion = new Bundle();
                informacion.putSerializable("alertas", this.contactos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Contactos", "Error cargando contactos");
        } catch (Exception e) {
            Log.e("Contactos", "Error inesperado");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Log.d("ContactosTask", "Entro onPostExecute");
        this.context.startActivity(new Intent(this.context, MisAlertas.class).putExtras(informacion).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
