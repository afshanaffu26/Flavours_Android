package com.example.flavours;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFragment extends Fragment implements View.OnClickListener {

    Button btnSend,btnDiscard;
    EditText editTextTextMultiLine;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FeedbackFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedbackFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedbackFragment newInstance(String param1, String param2) {
        FeedbackFragment fragment = new FeedbackFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        btnSend = v.findViewById(R.id.btnSend);
        btnDiscard = v.findViewById(R.id.btnDiscard);
        editTextTextMultiLine = v.findViewById(R.id.editTextTextMultiLine);
        btnSend.setOnClickListener(this);
        btnDiscard.setOnClickListener(this);
        return v;
    }
    private void sendEmailWithoutChooser() {
        String email = "flavours@gmail.com";
        String feedback_msg = editTextTextMultiLine.getText().toString().trim();
        if(feedback_msg.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your experience..", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        String aEmailList[] = {email};
        emailIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<i><font color='your color'>" + feedback_msg + "</font></i>"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, feedback_msg);

        PackageManager packageManager = getActivity().getPackageManager();
        boolean isIntentSafe = emailIntent.resolveActivity(packageManager) != null;
        if (isIntentSafe) {
            startActivity(emailIntent);
        } else {
            Toast.makeText(getActivity(), "Email app is not found", Toast.LENGTH_SHORT).show();
        }
}
    protected void sendEmailWithChooser() {
        String feedbackMessage = editTextTextMultiLine.getText().toString().trim();
        if(feedbackMessage.isEmpty()) {
            Toast.makeText(getContext(), "Please enter your experience..", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "flavours@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, feedbackMessage);
        startActivity(Intent.createChooser(intent, ""));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnSend:
                sendEmailWithoutChooser();
                break;
            case R.id.btnDiscard:
                discardEmail();
                break;
        }
    }

    private void discardEmail() {
        editTextTextMultiLine.setText("");
    }
}