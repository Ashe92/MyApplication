package com.example.wykl2.Presenter;

import com.example.wykl2.Model.ExampleDataInteractor;
import com.example.wykl2.Model.ExampleDataModel;
import com.example.wykl2.Model.IExampleDataCallback;
import com.example.wykl2.View.IMainView;

import java.util.List;

/**
 * Created by Maciej Markiewicz on 10.12.15.
 */
public class MainPresenter implements IMainPresenter {

    private IMainView _mainView;
    private ExampleDataInteractor _exampleDataInteractor;
    private List<String> _items;
    private String text;

    private IExampleDataCallback _callback = new IExampleDataCallback() {
        @Override
        public void onDataLoaed(Object data) {
            ExampleDataModel model = (ExampleDataModel) data;

            _items = model.getItems();
            loadDataToView();
        }

        @Override
        public void onError() {

        }

        @Override
        public void onSuccess() {

        }
    };

    private void loadDataToView() {
        _mainView.setSampleData(_items);
    }

    public MainPresenter(IMainView mainView, ExampleDataInteractor exampleDataInteractor) {
        _mainView = mainView;
        _exampleDataInteractor = exampleDataInteractor;

        _mainView.registerPresenter(this);
    }

    @Override
    public void onItemClick(int elementIndex) {
        String text = _items.get(elementIndex);
        _mainView.showItemText(text);
    }

    @Override
    public void onButtonClick() {
        text = _mainView.getTextFromEditText();
        _items.add(text);
        _mainView.setSampleData(_items);

        _exampleDataInteractor.saveToParse(_callback, _items);
    }

    @Override
    public void viewReady() {
        _exampleDataInteractor.getExampleModel(_callback);
    }

    @Override
    public void getFromParseClick() {
        _exampleDataInteractor.getMyItems(_callback);
    }
}
