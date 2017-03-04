package com.superfeed.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IdentifierFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IdentifierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdentifierFragment extends Fragment {

    private String mEmail;

    public IdentifierFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEmail = LocalPref.getUserEmail(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_identifier, container, false);

        int offColor = ContextCompat.getColor(getContext(), R.color.background_material_light);
        int onColor = ContextCompat.getColor(getContext(), R.color.background_material_dark);
        Bitmap myBitmap = QRCode.from(mEmail).withColor(onColor, offColor).withSize(1536, 1536).bitmap();
        ImageView myImage = (ImageView) layout.findViewById(R.id.qrcode_view);
        myImage.setImageBitmap(myBitmap);
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
