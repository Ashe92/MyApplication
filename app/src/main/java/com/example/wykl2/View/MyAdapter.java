package com.example.wykl2.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.wykl2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej Markiewicz on 20.11.15.
 */
public class MyAdapter extends ArrayAdapter<String> {

    private List<String> items;
    private LayoutInflater inflater;

    public MyAdapter(Context context, int resource) {
        super(context, resource);

        inflater = LayoutInflater.from(context);

        items = new ArrayList<>();
    }

    public void setItems(List<String> items){
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
        }

        String item = items.get(position);
        TextView textView = (TextView) convertView.findViewById(R.id.item_text);
        textView.setText(item);

        return convertView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public List<String> getItems(){
        return items;
    }
}
