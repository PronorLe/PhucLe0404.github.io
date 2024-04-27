package com.lilphuc.examples.android.huflittournament.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lilphuc.examples.android.huflittournament.Model.Account;
import com.lilphuc.examples.android.huflittournament.R;
import com.lilphuc.examples.android.huflittournament.SQL.SQLAccount;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin, btnRegister;
    private EditText edtNameUser, edtPassWordUser;
    private SQLAccount sqlAccount;
    private ArrayList<Account> userArrayList;
    private CheckBox cbLuuThongTin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        layThongTin();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlAccount = new SQLAccount(LoginActivity.this);
                userArrayList = new ArrayList<>();
                userArrayList = sqlAccount.getListAccountSQL();
                String nameUser = edtNameUser.getText().toString();
                String passUser = edtPassWordUser.getText().toString();
                boolean checkAc = false;
                if (nameUser.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Tên tài khoản không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (passUser.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    } else {
                        for (Account ac : userArrayList) {
                            if (nameUser.equals(ac.getUserName().trim()) && passUser.equals(ac.getPassWord().trim())) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                checkAc = true;
                                break;
                            }
                        }
                        if (checkAc == false) {
                            Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        } else {
                            luuThongTin();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("userName",nameUser.toString().trim());
                            intent.putExtra("passWord",passUser.toString().trim());
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 999);
            }
        });
    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        edtNameUser = findViewById(R.id.edtUserNamePayment);
        edtPassWordUser = findViewById(R.id.edtPassword);
        cbLuuThongTin = findViewById(R.id.cbLuuThongTin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            String nameUser = data.getStringExtra("nameUser");
            String passWordUser = data.getStringExtra("passWordUser");
            edtNameUser.setText(nameUser.trim());
            edtPassWordUser.setText(passWordUser.trim());
        }
    }

    private void luuThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = edtNameUser.getText().toString();
        String pass = edtPassWordUser.getText().toString();
        boolean check = cbLuuThongTin.isChecked();
        if (!check) {
            editor.clear();
        } else {
            editor.putString("name", name);
            editor.putString("pass", pass);
            editor.putBoolean("checkStatus", check);
        }
        editor.commit();
    }

    private void layThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);

        boolean check = sharedPreferences.getBoolean("checkStatus", false);
        if (check) {
            String tenNguoiDung = sharedPreferences.getString("name", "");
            String matKhau = sharedPreferences.getString("pass", "");
            edtNameUser.setText(tenNguoiDung);
            edtPassWordUser.setText(matKhau);
        } else {
            edtNameUser.setText("");
            edtPassWordUser.setText("");
        }
        cbLuuThongTin.setChecked(check);
    }
}
