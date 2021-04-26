package com.example.image.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.image.R;

import java.util.List;

public class Adapter extends BaseAdapter {

    private List<String> imageResponseList;
    private Context context;
    private LayoutInflater layoutInflater;

    public Adapter(List<String> imageResponseList, Context context) {
        this.imageResponseList = imageResponseList;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageResponseList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(context).load(imageResponseList.get(i)).apply(new RequestOptions().override(300, 300)).into(imageView);

        return view;
    }
}
