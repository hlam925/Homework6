package com.example.home.homework6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Home on 4/5/16.
 */
public class LoginFragment extends Fragment {

    private ArrayList<User> mUsersArray;

    private User mUser;

    private String mUserName;

    private long mUserId;

    private UserSQLiteHelper mUserSQLiteHelper;

    @Bind(R.id.fragment_login_edittext)
    EditText mLoginEditText;

    @Bind(R.id.fragment_login_login_button)
    Button mLoginButton;

    @Bind(R.id.fragment_login_adduser_button)
    Button mAddButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, rootView);

//        mUserName = mLoginEditText.getText().toString().trim();

        mUserSQLiteHelper = UserSQLiteHelper.getuInstance(getActivity().getApplicationContext());

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginButton.setEnabled(false);
                onLoginButtonClicked(v);
                mLoginButton.setEnabled(true);
            }
        });


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddButton.setEnabled(false);
                onAddButtonClicked(v);
                mAddButton.setEnabled(true);
            }
        });

        return rootView;
    }

    public void onLoginButtonClicked(View v){
        mUserName = mLoginEditText.getText().toString().trim();
        if (mUserName != null) {
            mUser = mUserSQLiteHelper.getUser(mUserName);
            if (mUser != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main_framelayout, MapFragment.newInstance(mUserName));
                transaction.commit();
            } else {
                Snackbar snackbar = Snackbar.make(v, getString(R.string.snackbar_error_text_1), Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }


    public void onAddButtonClicked(View v){
        mUserName = mLoginEditText.getText().toString().trim();
        if(mUserName != null) {
            mUser = mUserSQLiteHelper.getUser(mUserName);
            if(mUser != null) {
                Snackbar snackbar = Snackbar.make(v, getString(R.string.snackbar_error_text_2), Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                mUserSQLiteHelper.insertUser(new User(mUserName));
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main_framelayout, MapFragment.newInstance(mUserName));
                transaction.commit();
            }
        }

    }
}
