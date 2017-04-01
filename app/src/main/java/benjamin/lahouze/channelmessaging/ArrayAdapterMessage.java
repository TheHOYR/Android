package benjamin.lahouze.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.util.ArrayList;

import channelmessaging.R;

/**
 * Created by Benjamin
 */

public class ArrayAdapterMessage extends ArrayAdapter<JsonMessage> {
    private final Context context;
    private final ArrayList<JsonMessage> values;
    public ArrayAdapterMessage(Context context, ArrayList<JsonMessage> values) {
        super(context, R.layout.activity_message, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_message, parent, false);
        TextView textView1 = (TextView) rowView.findViewById(R.id.textView4);
        TextView textView2 = (TextView) rowView.findViewById(R.id.textView5);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        textView1.setText(getItem(position).getUsername()+" : "+getItem(position).getMessage());
        textView2.setText("Date : "+getItem(position).getDate());

        Glide
                .with(this.getContext())
                .load(getItem(position).getImageUrl())
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(this.getContext()))
                .into(imageView);


        return rowView;
    }
}