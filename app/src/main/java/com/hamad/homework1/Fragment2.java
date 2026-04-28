package com.hamad.homework1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class Fragment2 extends Fragment {

    public static final String KEY_USER_NAME = "user_name";

    public interface OnUserDetailsSentListener {
        void onUserDetailsSent(String name, String email, String gender,
                               String age, String university);
    }

    private OnUserDetailsSentListener listener;

    public static Fragment2 newInstance(String name) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(KEY_USER_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnUserDetailsSentListener) {
            listener = (OnUserDetailsSentListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnUserDetailsSentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String name = (getArguments() != null)
                ? getArguments().getString(KEY_USER_NAME, "")
                : "";

        TextView tvWelcome = view.findViewById(R.id.tvWelcome);
        TextInputEditText etEmail = view.findViewById(R.id.etEmail);
        TextInputEditText etAge = view.findViewById(R.id.etAge);
        TextInputEditText etUniversity = view.findViewById(R.id.etUniversity);
        RadioGroup rgGender = view.findViewById(R.id.rgGender);
        Button btnContinue = view.findViewById(R.id.btnContinue);

        tvWelcome.setText(getString(R.string.f2_welcome_prefix, name));

        btnContinue.setOnClickListener(v -> {
            String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(requireContext(), R.string.f2_error_email, Toast.LENGTH_SHORT).show();
                return;
            }

            String gender = (rgGender.getCheckedRadioButtonId() == R.id.rbFemale)
                    ? getString(R.string.f2_gender_female)
                    : getString(R.string.f2_gender_male);

            String age = etAge.getText() == null ? "" : etAge.getText().toString().trim();
            String university = etUniversity.getText() == null ? "" : etUniversity.getText().toString().trim();

            if (listener != null) {
                listener.onUserDetailsSent(name, email, gender, age, university);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
