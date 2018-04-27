package com.dragon.mytestapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.functions.Consumer;

public class PeopleInteractor implements PeopleContracts.Interactor {

    private List<Person> mPersons;
    private PeopleContracts.Service mService;

    public PeopleInteractor(final PeopleContracts.Service service) {
        mService = service;
        mPersons = new ArrayList<>();
    }

    @Override
    public Completable prepare() {
        Log.d(PeopleContracts.TAG, "Interactor - prepare() called");
        return mService.getPersons()
                .doOnSuccess(new Consumer<List<Person>>() {
                    @Override
                    public void accept(final List<Person> people) throws Exception {
                        Log.d(PeopleContracts.TAG, "Interactor - doOnSuccess() called");
                        mPersons.clear();
                        mPersons.addAll(people);
                    }
                })
                .toCompletable();
    }

    @Override
    public List<Person> getPersons() {
        Log.d(PeopleContracts.TAG, "Interactor - getPersons() called");
        return mPersons;
    }

    @Override
    public void onDestroy() {
        mPersons.clear();
        mPersons = null;
    }
}
