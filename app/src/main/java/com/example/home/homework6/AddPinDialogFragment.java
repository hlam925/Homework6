package com.example.home.homework6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Home on 4/14/16.
 */
public class AddPinDialogFragment extends DialogFragment {

    public static final String ARG_LISTENER = "AddPinDialogFragment.listener";

    public static final String ARG_LATLNG = "AddPinDialogFragment.latlng";

    @Nullable public String mTitle;
    @Nullable public String mSnippet;

    @Bind(R.id.fragment_dialog_title_edittext)
    EditText mTitleEditText;

    @Bind(R.id.fragment_dialog_snippet_edittext)
    EditText mSnippetEditText;

    @Bind(R.id.fragment_dialog_ok_button)
    Button mButton;

    private AddPinListener mAddPinListener;

    public interface AddPinListener {
        void onAddPinClicked(String title, String snippet);
    }

    public AddPinDialogFragment() {

    }

    public static AddPinDialogFragment newInstance(){
        AddPinDialogFragment fragment = new AddPinDialogFragment();
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, rootView);

//        mTitle = mTitleEditText.getText().toString().trim();
//        mSnippet = mSnippetEditText.getText().toString().trim();

//
        mAddPinListener = (AddPinListener) getActivity();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddPinListener.onAddPinClicked(getTitle(), getSnippet());
            }
        });


        return rootView;
    }


    public String getTitle(){
        mTitle = mTitleEditText.getText().toString().trim();
        return mTitle;
    }

    public String getSnippet(){

        mSnippet = mSnippetEditText.getText().toString().trim();
        return mSnippet;
    }

}
