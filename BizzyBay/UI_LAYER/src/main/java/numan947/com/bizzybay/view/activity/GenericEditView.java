package numan947.com.bizzybay.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.view.fragment.UserDetailsFragment;

/**
 * @author numan947
 * @since 7/8/17.<br>
 **/

public class GenericEditView extends AppCompatActivity {

    public static final String PASSED_DATA = "numan947.com.bizzybay.view.activity.GenericEditView.PASSED_DATA";


    private Toolbar toolbar;
    private TextView currentTv;
    private TextInputEditText newTV;
    private TextInputLayout textInputLayout;
    private Button submitButton;

    private String currentString;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_edit_view);

        toolbar = (Toolbar) findViewById(R.id.generic_edit_view_toolbar);
        currentTv = (TextView)findViewById(R.id.generic_edit_view_currentTV);
        newTV = (TextInputEditText)findViewById(R.id.generic_edit_view_newTV);
        textInputLayout = (TextInputLayout)findViewById(R.id.generic_edit_view_til);
        submitButton = (Button)findViewById(R.id.generic_edit_view_submit);

        this.getArguments();

        if(currentString!=null){
            this.currentTv.setText("old version : " +currentString);

            this.textInputLayout.setHint("new version");

        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTV.getText().toString().equals("")){
                    textInputLayout.setError("Input Can't be empty");
                }
                else{
                    Intent data = new Intent();
                    data.putExtra(UserDetailsFragment.EDITED_DATA,newTV.getText().toString());
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void getArguments() {
        Bundle bundle = getIntent().getExtras();

        this.currentString = bundle.getString(PASSED_DATA,null);

    }
}
