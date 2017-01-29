package com.example.shaloin.seventhassignmentc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by shaloin on 28/1/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="employeeDBNAME.db";
    public static final String EMPLOYEE_TABLE_NAME="employees";
    public static final String EMPLOYEE_COLUMN_ID="id";
    public static final String EMPLOYEES_COLUMN_NAME="employeeName";
    public static final String EMPLOYEES_COLUMN_AGE="employeeAge";
    public static final String EMPLOYEES_COLUMN_IMAGE="employeeImage";
    public static final String CREATE_IMAGES_TABLE="CREATE TABLE "+EMPLOYEE_TABLE_NAME + " ("
            +EMPLOYEE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            +EMPLOYEES_COLUMN_NAME + " text,"
            +EMPLOYEES_COLUMN_AGE + " text,"
            +EMPLOYEES_COLUMN_IMAGE + " BLOB NOT NULL );";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_IMAGES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS employees");
        onCreate(sqLiteDatabase);

    }

    public boolean insertEmployee(String employeeName,String employeeAge,byte[] employeeImage){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("employeeName",employeeName);
        values.put("employeeAge",employeeAge);
        values.put("employeeImage",employeeImage);
        db.insert("employees",null,values);
        return true;
    }

    public boolean insertEmployee(Employee employee){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(EMPLOYEES_COLUMN_NAME,employee.employeeName);
        values.put(EMPLOYEES_COLUMN_AGE,employee.employeeAge);
        values.put(EMPLOYEES_COLUMN_IMAGE,employee.employeeImage);
        db.insert(EMPLOYEE_TABLE_NAME,null,values);
        return true;
    }
    public Cursor getData(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from employees where id="+id+"",null);
        return res;
    }

    public int numberOfRows(){
        int numRows;
        SQLiteDatabase db=this.getReadableDatabase();
        return numRows=(int) DatabaseUtils.queryNumEntries(db,EMPLOYEE_TABLE_NAME);
    }

    public boolean updateEmployee(Integer id,String employeeName,String employeeAge){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("employeeName",employeeName);
        values.put("employeeAge",employeeAge);
        db.update("employees",values,"id = ? ",new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteEmployee(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete("employees","id = ?",new String[]{Integer.toString(id)});
    }

    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> employeeList=new ArrayList<Employee>();
        String selectQuery="SELECT * FROM employees";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Employee employee=new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setEmployeeName(cursor.getString(1));
                employee.setEmployeeAge(cursor.getString(2));
                employee.setEmployeeImage(cursor.getBlob(3));
                employeeList.add(employee);
            }while (cursor.moveToNext());
        }
        db.close();
        return employeeList;
    }
}
