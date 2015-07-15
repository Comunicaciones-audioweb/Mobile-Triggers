package mx.com.audioweb.divisas_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan Acosta on 9/1/2014.
 */
public class DivisaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Contacto> divisa_items;

    public DivisaAdapter(Context context, ArrayList<Contacto> divisa_items) {
        this.context = context;
        this.divisa_items = divisa_items;
    }

    public int getCount() {
        return this.divisa_items.size();
    }

    public Object getItem(int position) {
        return this.divisa_items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.divisa_list_adapt, null);
        }

        TextView CF = (TextView) convertView.findViewById(R.id.CFText);
        TextView CT = (TextView) convertView.findViewById(R.id.CTText);
        TextView min = (TextView) convertView.findViewById(R.id.minText);
        TextView max = (TextView) convertView.findViewById(R.id.maxText);

        CF.setText(divisa_items.get(position).getCurrency_from());
        CT.setText(divisa_items.get(position).getCurrency_to());
        min.setText("MIN: " + divisa_items.get(position).getMinimum());
        max.setText("MAX: " + divisa_items.get(position).getMaximum());

        return convertView;
    }
}
