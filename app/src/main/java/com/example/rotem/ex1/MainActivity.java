package com.example.rotem.ex1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TwoLineListItem;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    ArrayList<Message> messages;
    MyAdapter adapter;
    ListView view_list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messages = new ArrayList<Message>();
        adapter =new MyAdapter(this, messages);


        view_list = (ListView)findViewById(R.id.list);
        view_list.setAdapter(adapter);
        //setListAdapter(adapter);
    }

    public void addItems(View v) {
        EditText input = (EditText) findViewById(R.id.editTextInput);

        //get date
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        String dateFacDB = DateFormat.format("HH:mm:ss", date).toString();

        //sends the message
        messages.add(new Message(input.getText().toString(),dateFacDB));
        adapter.notifyDataSetChanged();
        view_list.setSelection(adapter.getCount() - 1);
        input.setText("");

        // hide the keyboard
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

class Message {
    String name;
    String time;

    public Message(){
        this.name = "";
        this.time = "";
    }

    public Message(String name, String time){
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Message> messages;

    public MyAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        text1.setText(messages.get(position).getName());
        text2.setText("" + messages.get(position).getTime());

        return twoLineListItem;
    }
}

