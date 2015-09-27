package com.klaskagan.zoo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.klaskagan.zoo.R;
import com.klaskagan.zoo.models.Animal;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/26.
 */
public class ExhibitsAdapter extends ArrayAdapter<Animal> {

    public ExhibitsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_exhibit_list_item, parent, false);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.species = (TextView) convertView.findViewById(R.id.species);
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.thumbnail.setVisibility(View.GONE);
        viewHolder.progressBar.setVisibility(View.VISIBLE);

        viewHolder.name.setText(getItem(position).getName());
        viewHolder.species.setText(getItem(position).getSpecies());

        final ViewHolder tmp = viewHolder;
        Picasso.with(getContext()).load(getItem(position).getThumbnail()).into(viewHolder.thumbnail, new Callback() {
            @Override
            public void onSuccess() {
                tmp.thumbnail.setVisibility(View.VISIBLE);
                tmp.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                tmp.progressBar.setVisibility(View.GONE);
            }
        });
        return convertView;

    }

    class ViewHolder {
        ImageView thumbnail;
        TextView name;
        TextView species;
        ProgressBar progressBar;
    }
}
