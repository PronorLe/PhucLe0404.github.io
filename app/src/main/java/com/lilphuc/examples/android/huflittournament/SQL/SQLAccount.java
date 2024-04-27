package com.lilphuc.examples.android.huflittournament.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import  com.lilphuc.examples.android.huflittournament.Model.Account;
import java.util.ArrayList;

public class SQLAccount {
    DBHeplper dtbH;

    public SQLAccount(Context context) {
        dtbH = new DBHeplper(context);
    }

    public ArrayList<Account> getListAccountSQL() {
        ArrayList<Account> listTK = new ArrayList<>();
        SQLiteDatabase dtb = dtbH.getReadableDatabase();
        Cursor cs = dtb.rawQuery("SELECT * FROM Account", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            try {
                String tk = cs.getString(0);
                String mk = cs.getString(1);
                String email = cs.getString(2);
                String phone = cs.getString(3);
                String sex = cs.getString(4);
                Account t = new Account(tk, mk,email,phone,sex);
                listTK.add(t);
                cs.moveToNext();
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        cs.close();
        return listTK;
    }
    public boolean setListAccountSQL(Account tk) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", tk.getUserName());
        values.put("passWord", tk.getPassWord());
        values.put("gmail",tk.getGmail());
        values.put("phoneNumber",tk.getPhoneNumber());
        values.put("sex",tk.getSex());
        long r = db.insert("Account", null, values);
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean updateListAccountSQL(Account tk) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("passWord", tk.getPassWord());
        int r = db.update("Account", values, "userName=?", new String[]{tk.getUserName()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
    public boolean deleteListAccountSQL(Account tk) {
        SQLiteDatabase db = dtbH.getWritableDatabase();
        int r = db.delete("Account", "userName=?", new String[]{tk.getUserName()});
        if (r <= 0) {
            return false;
        }
        return true;
    }
}
