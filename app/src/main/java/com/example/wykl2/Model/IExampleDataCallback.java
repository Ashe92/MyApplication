package com.example.wykl2.Model;

/**
 * Created by Maciej Markiewicz on 10.12.15.
 */
public interface IExampleDataCallback {
    void onDataLoaed(Object data);

    void onError();

    void onSuccess();
}
