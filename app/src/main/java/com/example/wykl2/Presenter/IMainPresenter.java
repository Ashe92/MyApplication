package com.example.wykl2.Presenter;

/**
 * Created by Maciej Markiewicz on 10.12.15.
 */
public interface IMainPresenter {
    void onItemClick(int elementIndex);

    void onButtonClick();

    void viewReady();

    void getFromParseClick();
}
