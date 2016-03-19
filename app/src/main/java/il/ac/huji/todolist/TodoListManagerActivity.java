package il.ac.huji.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class TodoListManagerActivity extends AppCompatActivity {

    private Adapter adp;
    private ListView tasksListView;
    private ArrayList<ToDoItem> tasksLst;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        ToDoItem[] tasks = {};
        tasksLst = new ArrayList<ToDoItem>(Arrays.asList(tasks));
        adp = new Adapter(this, R.layout.activity_adapter, tasksLst);
        tasksListView = (ListView)findViewById(R.id.lstTodoItems);
        tasksListView.setAdapter(adp);
        registerForContextMenu(tasksListView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_item_menu, menu);

        //get information about the selected item and gt the item itself
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        ToDoItem curTask = (ToDoItem) adp.getItem(info.position);
        String headerTitle = curTask.getToDoTxt();
        menu.setHeaderTitle(headerTitle);

        if(curTask.isCallItem()){
            menu.add(curTask.getToDoTxt());
        }

    }
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ToDoItem curTask = (ToDoItem) adp.getItem(info.position);

        switch(item.getItemId())
        {
            case R.id.menuItemDelete:
                tasksLst.remove(curTask);
                adp.notifyDataSetChanged();
                break;
            //if it is a a call
            case 0:
                System.out.println("***************: " + curTask.getCellNum());
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: " + curTask.getCellNum()));
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //if we want to add a new item to the list
            case R.id.menuItemAdd:
                Intent newTodoTask = new Intent(this, AddNewTodoItemActivity.class);
                startActivityForResult(newTodoTask, 2);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2)
        {
            String taskToDoTxt = data.getExtras().getString("Task");
            Date date = (Date)data.getExtras().getSerializable("Date");
            ToDoItem newItem = new ToDoItem(taskToDoTxt, date);
            adp.add(newItem);
        }
    }
}
