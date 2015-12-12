package com.example.wykl2.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wykl2.Model.ExampleDataInteractor;
import com.example.wykl2.Presenter.IMainPresenter;
import com.example.wykl2.Presenter.MainPresenter;
import com.example.wykl2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    private EditText editText;
    private Button button;
    private ListView listView;
    private MyAdapter adapter;

    private IMainPresenter _presenter;

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                _presenter.onItemClick(index);
        }
    };

    private View.OnClickListener myClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            _presenter.onButtonClick();
        }
    };
    private View button2;
    private View.OnClickListener _fromParseCLick= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            _presenter.getFromParseClick();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();

        editText = (EditText) findViewById(R.id.edittext1);
        button = (Button) findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(_fromParseCLick);

        //click listener
        button.setOnClickListener(myClickListener);

        //addapter initialization
//        adapter = new MyAdapter(MainActivity.this, R.layout.item_layout);

        //listView
        listView = (ListView) findViewById(R.id.listview1);
        listView.setOnItemClickListener(itemClickListener);

        _presenter.viewReady();
    }

    private void initPresenter() {
         new MainPresenter(this, new ExampleDataInteractor());
    }

    @Override
    public void registerPresenter(IMainPresenter presenter) {
        _presenter = presenter;
    }

    @Override
    public void setSampleData(List<String> items) {
        adapter = new MyAdapter(MainActivity.this, R.layout.item_layout);
        adapter.setItems(items);
        listView.setAdapter(adapter);
    }

    @Override
    public String getTextFromEditText() {
        return editText.getText().toString();
    }

    @Override
    public void showItemText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
