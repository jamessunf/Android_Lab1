package com.example.android_lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<FriendInfo> {

    private Activity activity;

    public ListViewAdapter(Activity activity, int resource, List<FriendInfo> objects) {
        super(activity, resource, objects);
        this.activity = activity;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        // inflate layout from xml
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type
     //   int viewType = getItemViewType(position);
        if(getItem(position).isSender()){

            layoutResource = R.layout.row_receiver;

        }else{

            layoutResource = R.layout.row_send;

        }



        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        // set data to views
        if (getItem(position).isSender()) {
            holder.sender.setImageResource(R.drawable.row_send);
        } else {
            holder.sender.setImageResource(R.drawable.row_receive);
        }
        holder.massage.setText(getItem(position).getName());

        return convertView;
    }




    private class ViewHolder {
        private ImageView sender;
        private TextView massage;

        public ViewHolder(View v) {
            sender = (ImageView) v.findViewById(R.id.img_show);
            massage = (TextView) v.findViewById(R.id.txt_msg);
        }
    }

}
