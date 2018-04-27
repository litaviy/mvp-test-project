package com.dragon.mytestapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class PeopleActivity extends AppCompatActivity implements PeopleContracts.View {

    private PeopleContracts.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = PeopleInjector.getPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        findViewById(R.id.mainAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onSetupContentClick();
            }
        });

        mPresenter.onViewReady();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void showProgress() {
        Log.d(PeopleContracts.TAG, "showProgress() called");
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        Log.d(PeopleContracts.TAG, "hideProgress() called");
        mProgressDialog.dismiss();
    }

    @Override
    public void setPersonsList(final List<Person> persons, final PersonClickListener clickListener) {
        Log.d(PeopleContracts.TAG, "View - setPersonsList() called with: persons = [" + persons + "], clickListener = [" + clickListener + "]");
    }
}
