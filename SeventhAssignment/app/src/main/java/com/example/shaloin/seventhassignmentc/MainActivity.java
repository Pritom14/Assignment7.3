package com.example.shaloin.seventhassignmentc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name,age;
    ImageView image;
    Employee employee;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.nameEditTextID);
        age=(EditText)findViewById(R.id.ageEditTextID);
        image=(ImageView)findViewById(R.id.imageID);

        db =new DBHelper(this);
        if(db.numberOfRows()>0){
            Log.e("MA ","Database already exist.");
        }
        else{
            saveImageInDB();
            Log.e("MA ","Image Saved in Database.");

        }

        //blob to image conversion
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadImageFromDB()) {
                    Log.e("MA ","Image Loaded from Database.");
                }
            }
        }, 0);

    }

    Boolean saveImageInDB(){
        employee=new Employee();
        employee.setEmployeeName("Pritom");
        employee.setEmployeeAge("22");
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image1);
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte imageInByte[]=stream.toByteArray();
        employee.employeeImage =imageInByte;
        db.insertEmployee(employee);
        return true;
    }

    Boolean loadImageFromDB(){

        try {
            ArrayList array_list = db.getAllEmployee();
            Log.e("Employee Size ", String.valueOf(array_list.size()));

            if(!array_list.isEmpty()){
                Employee employee1 = (Employee) array_list.get(0);

                name.setText(employee1.employeeName);
                age.setText(employee1.employeeAge);

                image.setImageBitmap(Utils.getImage(employee1.getEmployeeImage()));

            }else {
                Log.e("MA ", "No Employee available ");
            }

            return true;
        } catch (Exception e) {
            Log.e("MA ", "<loadImageFromDB> Error : " + e.getLocalizedMessage());

            return false;
        }
    }
}
