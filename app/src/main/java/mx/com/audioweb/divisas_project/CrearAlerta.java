package mx.com.audioweb.divisas_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class CrearAlerta extends Activity {

    EditText editMinValue, editMaxValue;
    Button refresh, cancel;
    String leftIndex, rightIndex, sn = "2", maximo = "2", minimo = "2";
    Spinner Divisa1, Divisa2;
    Switch notificacion;
    CheckBox max, min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Alertas");
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
        editMinValue = (EditText) findViewById(R.id.editMinValue);
        editMaxValue = (EditText) findViewById(R.id.editMaxValue);
        refresh = (Button) findViewById(R.id.RefreshButton);
        Divisa1 = (Spinner) findViewById(R.id.Divisa1);
        Divisa2 = (Spinner) findViewById(R.id.Divisar2);
        max = (CheckBox) findViewById(R.id.checkBox2);
        min = (CheckBox) findViewById(R.id.checkBox);
        notificacion = (Switch) findViewById(R.id.switch1);
        cancel = (Button) findViewById(R.id.Cancelbutton);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftIndex = editMinValue.getText().toString();
                rightIndex = editMaxValue.getText().toString();

                if (max.isChecked()) {
                    maximo = "1";
                }
                if (min.isChecked()) {
                    minimo = "1";
                }
                if (notificacion.isChecked()) {
                    sn = "1";
                }
                new CrearAlertaTask(getApplicationContext(), String.valueOf(Divisa1.getSelectedItem()), String.valueOf(Divisa2.getSelectedItem()), maximo, rightIndex, minimo, leftIndex, "1", sn).execute();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}

class CrearAlertaTask extends AsyncTask<String, Void, Boolean> {
    private Context context;
    private String currency_from, currency_to, max, maxvalue, min, minvalue, userid, notificaciones;

    public CrearAlertaTask(Context ctx, String cf, String ct, String max, String maxv, String min, String minv, String usrid, String n) {
        this.context = ctx;
        this.currency_from = cf;
        this.currency_to = ct;
        this.max = max;
        this.maxvalue = maxv;
        this.min = min;
        this.minvalue = minv;
        this.userid = usrid;
        this.notificaciones = n;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
//        HttpGet request = new HttpGet("http://206.225.83.102/mobile_trigger/setAlert.php?" + "currency_from=" + currency_from + "&currency_to=" + currency_to + "&max=" + max + "&maxvalue=" + maxvalue + "&min=" + min + "&minvalue=" + minvalue + "&userid=" + userid + "&notificaciones=" + notificaciones);
        HttpGet request = new HttpGet("http://66.226.72.48/mobile_trigger/setAlert.php?" + "currency_from=" + currency_from + "&currency_to=" + currency_to + "&max=" + max + "&maxvalue=" + maxvalue + "&min=" + min + "&minvalue=" + minvalue + "&userid=" + userid + "&notificaciones=" + notificaciones);
        HttpResponse response;
        try {
            response = httpclient.execute(request);
            Log.d("Response of GET request", response.toString());
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        Toast.makeText(this.context, "Notificacion Guardada", Toast.LENGTH_SHORT).show();
        this.context.startActivity(new Intent(this.context, MenuOption.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}