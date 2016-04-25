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
import android.widget.TextView;

import com.bankfinder.chathurangasandun.bankfinder.model.Bank;
import com.bankfinder.chathurangasandun.bankfinder.model.Branches;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {




    TextView tvLogo;
    private Branches mainBranch;

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

        tvLogo = (TextView) view.findViewById(R.id.tvMainbankName);
        tvLogo.setText(Bank.selectedBank);


        DatabaseOpenHelper db =new DatabaseOpenHelper(getContext());

        mainBranch = db.getMainBranch(Bank.selectedBank).get(0);

        TextView address = (TextView) view.findViewById(R.id.tvMainBranchAddress);
        TextView tp = (TextView) view.findViewById(R.id.tvMainBranchTP);
        TextView email = (TextView) view.findViewById(R.id.tvMainBranchEmail);

        address.setText(mainBranch.getAddress());
        tp.setText(mainBranch.getTp());


        int i= 0;
        for (String s:Bank.allBanksnames) {

            Log.d("email",s);

            if(s.equals(Bank.selectedBank)){
                break;
            }else{
                i++;
            }
        }

        Log.d("email",String.valueOf(i));

        email.setText(Bank.allEmails[i]);





        return view;
    }

}
