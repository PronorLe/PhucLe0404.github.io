package com.lilphuc.examples.android.huflittournament.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lilphuc.examples.android.huflittournament.Activity.LoginActivity;
import com.lilphuc.examples.android.huflittournament.Activity.MainActivity;
import com.lilphuc.examples.android.huflittournament.R;

public class AccountFragment extends Fragment {
    private TextView textViewExit,userNameInformation,passWordInformation;
    private View view;
    private MainActivity mainActivity;
    public AccountFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_infomation, container, false);

        initView();
        textViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    private void initView() {
        mainActivity = (MainActivity)getActivity();
        textViewExit = view.findViewById(R.id.textExit);
        userNameInformation = view.findViewById(R.id.userNameInformation);
        userNameInformation.setText(mainActivity.userName);
        passWordInformation = view.findViewById(R.id.passWordInformation);
        passWordInformation.setText(mainActivity.passWord);
    }

}
