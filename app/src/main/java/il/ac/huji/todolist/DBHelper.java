package il.ac.huji.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by karin_mar on 31/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "todo_db";
    private static final String TABLE_NAME = "todo";
    private static final String KEY_TODO_TITLE = "title";
    private static final String KEY_DUE_DATE = "due";
    //private static final String BUILD_TABLE = "create table " + TABLE_NAME + " ( _id primary key autoincrement, title String, due long);";
    private static final String BUILD_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ KEY_TODO_TITLE + " TEXT," + KEY_DUE_DATE + " TEXT)";


    private ArrayList<ToDoItem> allTasks;
    private SQLiteDatabase db;

    public DBHelper(Context context){
        super(context, DB_NAME, null, 2);
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BUILD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);*/
    }

    public void insertNewTodo(ToDoItem item)
    {
        ContentValues newTodo = new ContentValues();
        newTodo.put(KEY_TODO_TITLE, item.getToDoTxt());
        newTodo.put(KEY_DUE_DATE, item.getDueDateStr());
        db.insert(TABLE_NAME, null, newTodo);
    }

    public void deleteTodoItem(ToDoItem item){
        db.delete(TABLE_NAME, KEY_TODO_TITLE + " = '" + item.getToDoTxt() + "'", null);
    }

    public ArrayList<ToDoItem> getAllTasks(){
        allTasks = new ArrayList<>();
        String selectAllTasks = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllTasks, null);
        if(cursor.getCount() == 0){
            cursor.close();
            return allTasks;
        }

        cursor.moveToFirst();
        for(int i=0; i<cursor.getCount(); i++){
            String todo = cursor.getString(0);
            String dueDate = cursor.getString(1);
            Date d = parseTheDate(dueDate);
            ToDoItem newItem = new ToDoItem(todo, d);
            allTasks.add(newItem);
            cursor.moveToNext();
        }
        cursor.close();
        return allTasks;
    }

    public Date parseTheDate(String dueDate) {
        String[] splitted = dueDate.split("/");
        String day = splitted[0];
        String month = splitted[1];
        String year =  splitted[2];
        int dayInt = Integer.parseInt(day);
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        Calendar cal = Calendar.getInstance();
        cal.set(yearInt, monthInt, dayInt);
        return cal.getTime();
    }


}
