package com.lilphuc.examples.android.huflittournament.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lilphuc.examples.android.huflittournament.R;

public class PaymentActivity extends AppCompatActivity {
    private TextView textPayment,textCancel,textTotalMoney;
    private EditText edtUserName,edtUserPhone,edtUserAddress;
    //private MainActivity mainActivity;
    // private ImageView imageHome;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        intiView();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String money = bundle.getString("total");
            textTotalMoney.setText(money);
        }
        textPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(edtUserName.getText().toString().trim().equals("")) {
                        throw new RuntimeException();
                    }
                    if(edtUserPhone.getText().toString().trim().equals("")) {
                        throw new RuntimeException();
                    }
                    if(edtUserAddress.getText().toString().trim().equals("")) {
                        throw new RuntimeException();
                    }
                    Toast.makeText(PaymentActivity.this, "Thanh toán thành công !!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(PaymentActivity.this,MainActivity.class);
                    startActivity(intent1);
                }catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PaymentActivity.this, "Vui lòng nhập đầy đủ thông tin !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this, "Hủy thanh toán !!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(PaymentActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void intiView() {
        textPayment = findViewById(R.id.textPayment);
        textCancel = findViewById(R.id.textCancelPayment);
        textTotalMoney = findViewById(R.id.textTotalMoney);
        edtUserName = findViewById(R.id.edtUserNamePayment);
        edtUserPhone = findViewById(R.id.edtUserPhonePayment);
        edtUserAddress = findViewById(R.id.edtUserAddressPayment);
        // imageHome = findViewById(R.id.imageHome);
    }
}
