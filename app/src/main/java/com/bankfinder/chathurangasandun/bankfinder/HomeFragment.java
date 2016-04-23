package com.bankfinder.chathurangasandun.bankfinder;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Drawable myDrawable = getResources().getDrawable(R.drawable.ic_bank);
        Bitmap myLogo = ((BitmapDrawable) myDrawable).getBitmap();

        ImageView imageView = (ImageView)view.findViewById(R.id.ivLogo);
        imageView.setImageBitmap(RoundedImageView.getCroppedBitmap(myLogo, 250));



        return view;
    }

}
