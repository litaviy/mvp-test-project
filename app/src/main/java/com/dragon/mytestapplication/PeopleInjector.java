package com.dragon.mytestapplication;

public class PeopleInjector {
    public static PeopleContracts.Presenter getPresenter(final PeopleContracts.View view) {
        final PeopleContracts.Service service = new PeopleService();
        final PeopleContracts.Interactor interactor = new PeopleInteractor(service);

        return new PeoplePresenter(view, interactor);
    }
}
