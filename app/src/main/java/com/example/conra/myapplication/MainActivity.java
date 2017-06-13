package com.example.conra.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button connect = (Button) findViewById(R.id.connect);
        final Button goToCard = (Button) findViewById(R.id.goto_card);
        goToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
            }
        });
        final EditText firstname = (EditText) findViewById(R.id.input_firstname);
        firstname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable firstnameEditable = firstname.getText();
                    String firstnameValue = firstnameEditable.toString();
                    validationName(firstname, "firstname", firstnameValue);
                } else {
                    firstname.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        final EditText lastname = (EditText) findViewById(R.id.input_lastname);
        lastname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable lastnameEditable = lastname.getText();
                    String lastnameValue = lastnameEditable.toString();
                    validationName(lastname, "lastname", lastnameValue);
                } else {
                    lastname.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        final EditText login = (EditText) findViewById(R.id.input_login);
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable loginEditable = login.getText();
                    String loginValue = loginEditable.toString();
                    validationLogin(login, loginValue);
                } else {
                    login.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        final EditText password = (EditText) findViewById(R.id.input_password);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    connect.callOnClick();
                }
                return false;
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable passwordEditable = password.getText();
                    String passwordValue = passwordEditable.toString();
                    validationPassword(password, passwordValue);
                } else {
                    password.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable firstnameEditable = firstname.getText();
                String firstnameValue = firstnameEditable.toString();
                Editable lastnameEditable = lastname.getText();
                String lastnameValue = lastnameEditable.toString();
                Editable loginEditable = login.getText();
                String loginValue = loginEditable.toString();
                Editable passwordEditable = password.getText();
                String passwordValue = passwordEditable.toString();
                boolean firstnameError = validationName(firstname, "firstname", firstnameValue);
                boolean lastnameError = validationName(lastname, "lastname", lastnameValue);
                boolean loginError = validationLogin(login, loginValue);
                boolean passwordError = validationPassword(password, passwordValue);

                if (!firstnameError && !lastnameError && !loginError && !passwordError) {
                    Intent intent = new Intent(MainActivity.this, FormResultActivity.class);
                    intent.putExtra("firstname", firstnameValue);
                    intent.putExtra("lastname", lastnameValue);
                    intent.putExtra("login", loginValue);
                    intent.putExtra("password", passwordValue);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validationName(EditText name, String type, String nameValue) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(nameValue)) {
            if (!nameValue.matches("[\\p{Alpha}\\p{Space}]+")) {
                if (type.equals("firstname")) {
                    name.setError(getString(R.string.error_firstname_invalid));
                } else {
                    name.setError(getString(R.string.error_lastname_invalid));
                }
                hasError = true;
            }
        } else {
            if (type.equals("firstname")) {
                name.setError(getString(R.string.error_firstname_empty));
            } else {
                name.setError(getString(R.string.error_lastname_empty));
            }
            hasError = true;
        }
        if (hasError) {
            name.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            name.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }

    private boolean validationLogin(EditText login, String loginValue) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(loginValue)) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginValue).matches()) {
                login.setError(getString(R.string.error_login_invalid));
                hasError = true;
            }
        } else {
            login.setError(getString(R.string.error_login_empty));
            hasError = true;
        }
        if (hasError) {
            login.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            login.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }

    private boolean validationPassword(EditText password, String passwordValue) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(passwordValue)) {
            if (passwordValue.length() < 8) {
                password.setError(getString(R.string.error_password_minlength));
                hasError = true;
            } else if (!passwordValue.matches(".*\\d.*")){
                password.setError(getString(R.string.error_password_hasdigit));
                hasError = true;
            }
        } else {
            password.setError(getString(R.string.error_password_empty));
            hasError = true;
        }
        if (hasError) {
            password.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            password.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }
}
