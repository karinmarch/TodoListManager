package il.ac.huji.todolist;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by karin_mar on 18/03/2016.
 */
public class ToDoItem implements Serializable{
    private String toDoTxt;
    private Date dueDate;
    private boolean isCall;

    public ToDoItem(String txt, Date date)
    {

        this.dueDate = date;
        if(txt.contains("Call ")){
            this.toDoTxt = txt;
            isCall = true;
        }
        else{
            isCall = false;
            this.toDoTxt = txt;
        }
    }

    public String getToDoTxt(){
       /* if(isCall){
            return "Call " + this.toDoTxt;
        }*/
        return this.toDoTxt;
    }

    public Date getDueDate(){
        return this.dueDate;
    }

    public String getCellNum(){
        return this.toDoTxt.substring("Call ".length());
    }

    public boolean isCallItem(){
        return this.isCall;
    }

    public String getDueDateStr(){
        Calendar calDate = Calendar.getInstance();
        calDate.setTime(this.dueDate);
        int year = calDate.get(Calendar.YEAR);
        int month  = calDate.get(Calendar.MONTH);
        int day = calDate.get(Calendar.DAY_OF_MONTH);
        return "" + day + "/" + month + "/" + year;
    }

    public boolean isDueDatePassed(){
        Date yesterday = new Date(System.currentTimeMillis()-24*60*60*1000);
        if(yesterday.after(this.dueDate)){
            return true;
        }
        return false;
    }
}
