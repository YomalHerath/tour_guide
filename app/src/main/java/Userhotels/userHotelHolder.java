package Userhotels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour_guide.R;

public class userHotelHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textVHotelName;
    public TextView textViewDesc;
    View v;

    public userHotelHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.user_view_hotel_Img);
        textVHotelName= itemView.findViewById(R.id.user_view_hotel_name);
        textViewDesc = itemView.findViewById(R.id.Hotel_desc_textView);
        v = itemView;
    }


}



