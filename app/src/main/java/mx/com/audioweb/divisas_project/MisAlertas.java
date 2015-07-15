package mx.com.audioweb.divisas_project;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;


public class MisAlertas extends Activity implements Serializable {
    private ListView DivisasList;
    private ArrayList<Contacto> div;
    private DivisaAdapter adapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("DivisaTask", "Entro");
        setContentView(R.layout.activity_my_divisas);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Tus Alertas");
        ImageButton imageButton = (ImageButton) mCustomView
                .findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);


        DivisasList = (ListView) findViewById(R.id.DivisasList);
        div = (ArrayList<Contacto>) getIntent().getSerializableExtra("alertas");
        //refresh();
        Log.e("ARRAYLIST", div.toString());
        if (div.size() > 0) {
            adapter = new DivisaAdapter(getApplicationContext(), div);
            DivisasList.setAdapter(adapter);
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MisAlertas.this);
            alertDialog.setTitle("Alertas");
            alertDialog.setMessage("No tienes alertas registradas");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(MisAlertas.this, MenuOption.class);
                    startActivity(i);
                }
            });
            alertDialog.show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(MisAlertas.this, MenuOption.class);
        startActivity(i);
    }

}
