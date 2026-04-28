package com.hamad.homework1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.checkbox.MaterialCheckBox;

public class Fragment3 extends Fragment {

    public static final String KEY_USER_NAME = "user_name";

    public interface OnConfirmationChangedListener {
        void onConfirmationChanged(boolean isChecked);
    }

    private OnConfirmationChangedListener listener;

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(KEY_USER_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnConfirmationChangedListener) {
            listener = (OnConfirmationChangedListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnConfirmationChangedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String name = (getArguments() != null)
                ? getArguments().getString(KEY_USER_NAME, "")
                : "";

        TextView tvHello = view.findViewById(R.id.tvHello);
        MaterialCheckBox cbAgree = view.findViewById(R.id.cbAgree);
        Button btnContinue = view.findViewById(R.id.btnContinue);

        tvHello.setText(getString(R.string.f3_hello, name));

        cbAgree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onConfirmationChanged(isChecked);
            }
            if (isChecked) {
                btnContinue.setEnabled(true);
                btnContinue.setText(R.string.f3_finish);
            } else {
                btnContinue.setEnabled(false);
                btnContinue.setText(R.string.f3_continue);
            }
        });

        btnContinue.setOnClickListener(v ->
                Toast.makeText(
                        requireContext(),
                        getString(R.string.f3_complete, name),
                        Toast.LENGTH_LONG
                ).show()
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
