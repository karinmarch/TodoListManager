package il.ac.huji.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class AddNewTodoItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button okBtn;
        Button cancelBtn;
        final EditText newTask;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo_item_activity);
        setTitle("Add New Item");
        newTask = (EditText)findViewById(R.id.edtNewItem);
        final DatePicker dueDate = (DatePicker)findViewById(R.id.datePicker);


        okBtn = (Button)findViewById(R.id.btnOK);
        cancelBtn = (Button)findViewById(R.id.btnCancel);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = dueDate.getYear();
                int month = dueDate.getMonth();//+1 maybe?
                int day = dueDate.getDayOfMonth();
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);

                String newTaskTxt = newTask.getText().toString();
               // System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

                //System.out.println("in ADD newTaskTxt: " + newTaskTxt);
                //System.out.println("in ADD calendar: " + calendar.getTime().toString());
                Intent result = new Intent();
                result.putExtra("Task", newTaskTxt);
                result.putExtra("Date", calendar.getTime());
                setResult(2, result);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });


    }
}
