package com.example.ecoversex.HelperClass;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ecoversex.R;

public class Redemption extends ArrayAdapter<String>{

    private String [] redemptionItem;
    private String [] desc;
    private int [] img;
    private String [] redemptionPoint;
    private Activity context;
    public Redemption(Activity context, String [] redemptionItem, String [] desc, int [] img, String [] redemptionPoint) {
        super(context, R.layout.redemption_list_layout,redemptionItem);

        this.context = context;
        this.redemptionItem = redemptionItem;
        this.desc = desc;
        this.img = img;
        this.redemptionPoint = redemptionPoint;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder;
        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.redemption_list_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) r.getTag();

        }
        viewHolder.redemption_imageview.setImageResource(img[position]);
        viewHolder.redemption_name_textview.setText(redemptionItem[position]);
        viewHolder.redemption_description_textview.setText(desc[position]);
        viewHolder.redemption_point_textview.setText(redemptionPoint[position]);

        return r;
    }

    class ViewHolder{
        TextView redemption_name_textview;
        TextView redemption_description_textview;
        ImageView redemption_imageview;
        TextView redemption_point_textview;
        ViewHolder(View v){
            redemption_name_textview = (TextView) v.findViewById(R.id.redemption_name_textview);
            redemption_description_textview = (TextView) v.findViewById(R.id.redemption_description_textview);
            redemption_imageview = (ImageView) v.findViewById(R.id.redemption_imageview);
            redemption_point_textview = (TextView) v.findViewById(R.id.redemption_point_textview);
        }
    }
}
