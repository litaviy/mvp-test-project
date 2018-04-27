package com.dragon.mytestapplication;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PeoplePresenter implements PeopleContracts.Presenter {

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private PeopleContracts.View mView;
    private PeopleContracts.Interactor mInteractor;

    public PeoplePresenter(final PeopleContracts.View view, final PeopleContracts.Interactor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
        mView = null;
        mInteractor.onDestroy();
        mInteractor = null;
    }

    @Override
    public void onViewReady() {
        Log.d(PeopleContracts.TAG, "Presenter - onViewReady() called");
        mView.showProgress();
        mCompositeDisposable.add(mInteractor.prepare()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(PeopleContracts.TAG, "Presenter - Completable Completed.");
                        mView.hideProgress();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(final Throwable throwable) throws Exception {
                        Log.d(PeopleContracts.TAG, "Presenter - Completable Error.");
                        mView.hideProgress();
                        throwable.printStackTrace();
                    }
                }));
    }

    @Override
    public void onSetupContentClick() {
        Log.d(PeopleContracts.TAG, "Presenter - onSetupContentClick() called");
        mView.setPersonsList(
                mInteractor.getPersons(),
                new PeopleContracts.View.PersonClickListener() {
                    @Override
                    public void onPersonClick(final int position) {

                    }
                }
        );
    }
}
