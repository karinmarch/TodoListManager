package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Adapter extends ArrayAdapter<ToDoItem> {
    private Context cnx;
    private ArrayList<ToDoItem> tasksTodo;
    //private ArrayList<Date> dueDatesArr;
    public Adapter(Context context, int resource, ArrayList<ToDoItem> tasks){
        super(context, resource, tasks);
        cnx = context;
        tasksTodo = tasks;
        //dueDatesArr = dueDates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(cnx);
        convertView = inflater.inflate(R.layout.activity_adapter,parent,false);
        TextView txtTask = (TextView) convertView.findViewById(R.id.txtTodoTitle);
        TextView dueDate = (TextView) convertView.findViewById(R.id.txtTodoDueDate);

        ToDoItem curTask = tasksTodo.get(position);
        txtTask.setText(curTask.getToDoTxt());
        dueDate.setText(curTask.getDueDateStr());

        if(curTask.isDueDatePassed()){
            txtTask.setTextColor(Color.RED);
            dueDate.setTextColor(Color.RED);
        }
        else{
            txtTask.setTextColor(Color.BLACK);
            dueDate.setTextColor(Color.BLACK);
        }
        return convertView;
    }

}
