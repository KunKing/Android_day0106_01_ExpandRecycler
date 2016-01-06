package com.edu.android_day0106_01_expandrecycler.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.edu.android_day0106_01_expandrecycler.R;

public class TextInputActivity extends AppCompatActivity implements TextWatcher, View.OnFocusChangeListener {

    private TextInputLayout textInputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        textInputLayout = (TextInputLayout) findViewById(R.id.text_Input);
        // 监听输入  如果要验证 用户名 邮箱 等等，写成一个类 去实现
        textInputLayout.getEditText().addTextChangedListener(this);

        textInputLayout.getEditText().setOnFocusChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

      /*  String str = s.toString();
        String reg = "[a-zA-Z0-9_]{6,12}@[a-zA-Z0-9]+(\\.[a-zA-Z]+)+";
        if (str.matches(reg)){
            textInputLayout.setErrorEnabled(false);
        }else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("邮箱格式错误");
        }*/

       /* if (s.length() == 11) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("电话长度为11位");
        }*/

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            EditText editText = (EditText) v;
            Editable text = editText.getText();
            if (text.length() == 11){
                textInputLayout.setErrorEnabled(false);
            }else {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError("电话号码长度");
            }

        }
    }
}
