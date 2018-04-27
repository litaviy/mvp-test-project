package com.dragon.mytestapplication;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface PeopleContracts {

    String TAG = "my_awesome_tag";

    interface View {

        interface PersonClickListener {
            void onPersonClick(final int position);
        }

        void showProgress();

        void hideProgress();

        void setPersonsList(final List<Person> persons, final PersonClickListener clickListener);
    }

    interface Presenter extends OnDestroyListener {
        void onViewReady();

        void onSetupContentClick();
    }

    interface Interactor extends OnDestroyListener {
        Completable prepare();

        List<Person> getPersons();
    }

    interface Service {
        Single<List<Person>> getPersons();
    }
}
