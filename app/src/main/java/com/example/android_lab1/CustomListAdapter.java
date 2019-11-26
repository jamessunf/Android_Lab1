package com.example.android_lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private Context myContext;
    private LayoutInflater myLayoutInflater;
    private ArrayList<Person> person;


    public CustomListAdapter(Lab8Activity context, ArrayList<Person> p) {
        this.myContext = context;
        this.myLayoutInflater = LayoutInflater.from(context);
        this.person = p;
    }

    @Override
    public int getCount() {
        return person.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{

        public ImageView imgView;
        public TextView txtTitle, txtContant;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder = null;
         if(person.get(position).getSend() == 1){
             convertView = myLayoutInflater.inflate(R.layout.custom_item_send,null);
             holder = new ViewHolder();
             holder.imgView = (ImageView) convertView.findViewById(R.id.img_send);
             holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_send_title);
             holder.txtContant = (TextView) convertView.findViewById(R.id.txt_send_message);
             convertView.setTag(holder);
             holder.imgView.setImageResource(R.drawable.row_send);

             holder.txtContant.setText(person.get(position).getMassage());

         }else{

             convertView = myLayoutInflater.inflate(R.layout.custom_item_receive,null);
             holder = new ViewHolder();
             holder.imgView = (ImageView) convertView.findViewById(R.id.img_receive);
             holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_receive_title);
             holder.txtContant = (TextView) convertView.findViewById(R.id.txt_receive_message);
             convertView.setTag(holder);
             holder.imgView.setImageResource(R.drawable.row_receive);

             holder.txtContant.setText(person.get(position).getMassage());

         }

        return convertView;
    }
}
