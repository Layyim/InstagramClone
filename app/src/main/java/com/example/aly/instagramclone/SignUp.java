package com.example.aly.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener
{
    private Button btnSave, btnRetrieveData, btnTransition;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;
    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnRetrieveData = findViewById(R.id.btnRetrieveData);
        btnTransition = findViewById(R.id.btnNextActivity);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        txtGetData = findViewById(R.id.txtGetData);

        btnSave.setOnClickListener(SignUp.this);

        txtGetData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kickBoxer");
                parseQuery.getInBackground("gNQELeLbwe", new GetCallback<ParseObject>()
                {
                    @Override
                    public void done(ParseObject object, ParseException e)
                    {
                         if (object != null && e == null)
                         {
                             txtGetData.setText(object.get("name") + " - "
                                     + "Punch Power: " + object.get("punchPower"));
                         }
                    }
                });
            }
        });

        btnRetrieveData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("kickBoxer");

                //queryAll.whereGreaterThan("punchPower", 100);
                queryAll.whereGreaterThanOrEqualTo("punchPower", 1000);
                queryAll.setLimit(1);

                queryAll.findInBackground(new FindCallback<ParseObject>()
                {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e)
                    {
                        if (e == null)
                        {
                            if (objects.size() > 0)
                            {
                                for(ParseObject kickBoxer : objects)
                                {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                 }
                                FancyToast.makeText(SignUp.this, allKickBoxers,
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                        true).show();
                            }

                            else
                            {
                                FancyToast.makeText(SignUp.this, e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                        true).show();
                            }

                        }

                        else
                        {
                            FancyToast.makeText(SignUp.this, e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                    true).show();
                        }
                    }
                });
            }
        });
            btnTransition.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(SignUp.this,
                            SignUpLoginActivity.class);
                    startActivity(intent);
                }
            });
    }

    @Override
    public void onClick(View v)
    {
        try
        {
            final ParseObject kickBoxer = new ParseObject("kickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e)
                {
                    if (e == null)
                    {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") +
                                        " details is saved.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                true).show();
                    }

                    else
                    {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                FancyToast.ERROR, true).show();
                    }
                }
            });
        } catch (Exception e)
        {
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG,
                    FancyToast.ERROR, true).show();
        }

    }
}
