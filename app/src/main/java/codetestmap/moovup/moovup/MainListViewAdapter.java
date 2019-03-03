package codetestmap.moovup.moovup;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class MainListViewAdapter extends BaseAdapter  implements AdapterView.OnItemClickListener {
    List<Entity> data = new ArrayList<>();
    Context context;

    public MainListViewAdapter(List<Entity> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Entity getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         convertView = LayoutInflater.from(context).inflate(R.layout.listview_mainlistview_item, null);
        ((TextView) (convertView.findViewById(R.id.title))).setText(getItem(position).name);
        Glide.with(context).load(getItem(position).picture).into((((ImageView) convertView.findViewById(R.id.image_view))));
        convertView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hawk.put("data_item", data.get(position));
                context.startActivity(new Intent(context, LocationDetailActivity.class));
            }
        });
         return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Hawk.put("data_item", data.get(position));
        context.startActivity(new Intent(context, LocationDetailActivity.class));
    }
}
