package il.ac.huji.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class TodoListManagerActivity extends AppCompatActivity {

    private Adapter adp;
    private ListView tasksListView;
    private ArrayList<String> tasksLst;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        String[] tasks = {};
        tasksLst = new ArrayList<String>(Arrays.asList(tasks));
        adp = new Adapter(this, R.layout.activity_adapter, tasksLst);
        tasksListView = (ListView)findViewById(R.id.lstTodoItems);
        tasksListView.setAdapter(adp);
        final Context ctx = this;
        tasksListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle(tasksLst.get(position));
                builder.setPositiveButton("Delete Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tasksLst.remove(position);
                        adp.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }

        });
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
                EditText newTask = (EditText)findViewById(R.id.edtNewItem);
                String strNewTask = newTask.getText().toString();
                adp.add(strNewTask);
                newTask.setText("");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
