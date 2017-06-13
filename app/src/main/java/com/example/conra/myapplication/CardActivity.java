package com.example.conra.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        final Button pay = (Button) findViewById(R.id.pay);

        final EditText number = (EditText) findViewById(R.id.card_number);
        final EditText month = (EditText) findViewById(R.id.card_month);
        final EditText year = (EditText) findViewById(R.id.card_year);
        final EditText cvv = (EditText) findViewById(R.id.card_cvv);
        number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable editable = number.getText();
                    String value = editable.toString();
                    validationNumber(number, value);
                } else {
                    number.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String replace = s.toString().replace(" ", "");
                StringBuilder stringBuilder = new StringBuilder(replace);
                for (int i = 4; i < stringBuilder.length(); i += 5) {
                    stringBuilder.insert(i, " ");
                }
                replace = stringBuilder.toString();
                if (!s.toString().equals(replace)) {
                    int modifier = s.toString().length() >= replace.length() ? 0 : 1;
                    int selection = Math.min(number.getSelectionEnd() + modifier,
                            replace.length());
                    number.setText(replace);
                    number.setSelection(selection);
                }
                replace = replace.replace(" ", "");
                if (replace.length() == 16) {
                    month.requestFocus();
                }
            }
        });
        month.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable editable = month.getText();
                    String value = editable.toString();
                    validationMonth(month, value);
                } else {
                    month.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        month.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 2) {
                    year.requestFocus();
                }
            }
        });
        year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable editable = year.getText();
                    String value = editable.toString();
                    validationYear(year, value);
                } else {
                    year.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        year.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 2) {
                    cvv.requestFocus();
                }
            }
        });
        cvv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Editable editable = cvv.getText();
                    String value = editable.toString();
                    validationCVV(cvv, value);
                } else {
                    cvv.setTextColor(getColor(android.R.color.black));
                }
            }
        });
        cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 3) {
                    pay.callOnClick();
                }
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean numberError = validationNumber(number, number.getText().toString());
                boolean monthError = validationMonth(month, month.getText().toString());
                boolean yearError = validationYear(year, year.getText().toString());
                boolean cvvError = validationCVV(cvv, cvv.getText().toString());
                if (!numberError && !monthError && !yearError && !cvvError) {
                    Toast.makeText(CardActivity.this, "FORM OK", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validationNumber(EditText edit, String value) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(value)) {
            if (!value.matches("[\\d\\p{Space}]+")) {
                hasError = true;
                edit.setError(getString(R.string.error_digit_only));
            } else if (value.length() < 19) {
                hasError = true;
                edit.setError(getString(R.string.error_length, 16));
            }
        } else {
            hasError = true;
            edit.setError(getString(R.string.error_empty));
        }
        if (hasError) {
            edit.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            edit.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }

    private boolean validationMonth(EditText edit, String value) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(value)) {
            if (!value.matches("\\d+")) {
                hasError = true;
                edit.setError(getString(R.string.error_digit_only));
            } else if (value.length() < 2) {
                hasError = true;
                edit.setError(getString(R.string.error_length, 2));
            } else if (Integer.parseInt(value) < 1 || Integer.parseInt(value) > 12) {
                hasError = true;
                edit.setError(getString(R.string.error_month));
            }
        } else {
            hasError = true;
            edit.setError(getString(R.string.error_empty));
        }
        if (hasError) {
            edit.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            edit.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }

    private boolean validationYear(EditText edit, String value) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(value)) {
            if (!value.matches("\\d+")) {
                hasError = true;
                edit.setError(getString(R.string.error_digit_only));
            } else if (value.length() < 2) {
                hasError = true;
                edit.setError(getString(R.string.error_length, 2));
            }
        } else {
            hasError = true;
            edit.setError(getString(R.string.error_empty));
        }
        if (hasError) {
            edit.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            edit.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }

    private boolean validationCVV(EditText edit, String value) {
        boolean hasError = false;
        if (!TextUtils.isEmpty(value)) {
            if (!value.matches("\\d+")) {
                hasError = true;
                edit.setError(getString(R.string.error_digit_only));
            } else if (value.length() < 3) {
                hasError = true;
                edit.setError(getString(R.string.error_length, 3));
            }
        } else {
            hasError = true;
            edit.setError(getString(R.string.error_empty));
        }
        if (hasError) {
            edit.setTextColor(getColor(android.R.color.holo_red_light));
        } else {
            edit.setTextColor(getColor(android.R.color.black));
        }

        return hasError;
    }
}
