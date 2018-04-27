package com.dragon.mytestapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class PeopleService implements PeopleContracts.Service {
    @Override
    public Single<List<Person>> getPersons() {
        // Some kind of API call.
        return Single.fromCallable(new Callable<List<Person>>() {
            @Override
            public List<Person> call() throws Exception {
                final Person person = new Person("Igor", 90);
                final List<Person> people = new ArrayList<>();
                people.add(person);
                return people;
            }
        }).delay(3, TimeUnit.SECONDS);
    }
}
