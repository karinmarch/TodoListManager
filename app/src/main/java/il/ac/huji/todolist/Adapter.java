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

public class Adapter extends ArrayAdapter<String> {
    private Context cnx;
    private ArrayList<String> tasksTodo;
    public Adapter(Context context, int resource, ArrayList<String> tasks){
        super(context, resource, tasks);
        cnx = context;
        tasksTodo = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(cnx);
        convertView = inflater.inflate(R.layout.activity_adapter,parent,false);
        TextView itmTxtView = (TextView) convertView.findViewById(R.id.label);
        itmTxtView.setText(tasksTodo.get(position));
        if(position % 2 == 0){
            itmTxtView.setTextColor(Color.RED);
        }
        else{
            itmTxtView.setTextColor(Color.BLUE);
        }
        return convertView;
    }
}
