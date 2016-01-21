package com.xhr.mvpdemo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xhr.mvpdemo.R;
import com.xhr.mvpdemo.repository.model.Repository;

import java.util.List;


/**
 * Created by Miroslaw Stanek on 24.04.15.
 */
public class RepositoriesListAdapter extends ArrayAdapter<Repository> {

    private LayoutInflater inflater;

    public RepositoriesListAdapter(Context context, List<Repository> objects) {
        super(context, 0, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepositoryHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            holder = new RepositoryHolder();
            holder.text1=(TextView)convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (RepositoryHolder) convertView.getTag();
        }

        Repository repository = getItem(position);
        holder.text1.setText(repository.name);

        return convertView;
    }

    static class RepositoryHolder {
        TextView text1;
    }
}
