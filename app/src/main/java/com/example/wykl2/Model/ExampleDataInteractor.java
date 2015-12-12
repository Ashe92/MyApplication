package com.example.wykl2.Model;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maciej Markiewicz on 10.12.15.
 */
public class ExampleDataInteractor {

    private IExampleDataCallback _callback;
    private SaveCallback _saveCallback = new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if(e == null){
                _callback.onSuccess();
            }else{
                _callback.onError();
            }
        }
    };
    private FindCallback<ParseObject> _findCallback = new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> list, ParseException e) {
            if(e == null){
                ParseObject object = list.get(0);
                List<String> items = object.getList("itemsList");

                ExampleDataModel exampleDataModel = new ExampleDataModel();
                exampleDataModel.setItems(items);

                _callback.onDataLoaed(exampleDataModel);
            }
        }
    };

    public void getExampleModel(IExampleDataCallback callback) {
        _callback = callback;

        loadData();
    }

    private void loadData() {
        ExampleDataModel exampleDataModel = new ExampleDataModel();
        String[] items = {"item1", "item2", "TEstowyitem1"};

        List<String> list = new ArrayList<>();

        list.addAll(Arrays.asList(items));

        exampleDataModel.setItems(list);

        _callback.onDataLoaed(exampleDataModel);
    }

    public void saveToParse(IExampleDataCallback callback, List<String> items){
        _callback = callback;

        ParseUser user = ParseUser.getCurrentUser();

        ParseACL acl = new ParseACL();
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(false);

        ParseObject object = new ParseObject("Items");
        object.put("itemsList", items);
        object.put("owner", user);
        object.setACL(acl);

        object.saveInBackground(_saveCallback);
    }

    public void getMyItems(IExampleDataCallback callback){
        _callback = callback;

        ParseUser user = ParseUser.getCurrentUser();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Items");
        query.whereEqualTo("owner", user);
        query.findInBackground(_findCallback);
    }

}
