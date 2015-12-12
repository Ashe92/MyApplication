package com.example.wykl2.View;

import com.example.wykl2.Presenter.IMainPresenter;

import java.util.List;

/**
 * Created by Maciej Markiewicz on 10.12.15.
 */
public interface IMainView {
    void registerPresenter(IMainPresenter presenter);

    void setSampleData(List<String> items);

    String getTextFromEditText();

    void showItemText(String text);
}
