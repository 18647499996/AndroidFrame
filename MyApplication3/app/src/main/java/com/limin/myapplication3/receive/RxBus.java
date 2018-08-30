package com.limin.myapplication3.receive;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscription;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class RxBus {
    private static final String TAG = "RxBus";
    private static RxBus instance;

    public static synchronized RxBus get() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    private RxBus() {
    }

    private ConcurrentHashMap<Object, List<Subject<Event, Event>>> subjectMapper = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Observable, Subscription> subscriptionMapper = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public Observable<Event> register(@NonNull String tag) {
        return register(tag,null);
    }

    public Observable<Event> register(@NonNull String tag, EventSubscriber<Event> eventSubscriber) {
        List<Subject<Event, Event>> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }

        Subject<Event, Event> subject = PublishSubject.create();
        subjectList.add(subject);
        if (eventSubscriber != null) {
            subject.subscribe(eventSubscriber);
            subscriptionMapper.put(subject, eventSubscriber);
        }
        return subject;
    }


    public void unregister(@NonNull String tag, Observable<Event> observable) {
        if (observable == null){
            return;
        }
        List<Subject<Event, Event>> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove((Subject) observable);
            if (subjects.isEmpty()) {
                subjectMapper.remove(tag);
            }
        }

        if (subscriptionMapper.containsKey(observable)) {
            Subscription eventSubscriber = subscriptionMapper.get(observable);
            if (!eventSubscriber.isUnsubscribed()) {
                eventSubscriber.unsubscribe();
            }
            subscriptionMapper.remove(observable);
        }
    }

    public void post(@NonNull String tag) {
        post(tag, new Event());
    }

    public void post(@NonNull String tag, @NonNull Event event) {
        List<Subject<Event, Event>> subjectList = subjectMapper.get(tag);
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Subject<Event, Event> subject : subjectList) {
                subject.onNext(event);
            }
        }
    }

    public void postNoChange(@NonNull String tag){
        Event event = new Event();
        event.status = Event.STATUS_NO_MORE;
        post(tag, event);
    }

    public void postSuccess(@NonNull String tag) {
        Event event = new Event();
        event.status = Event.STATUS_OK;
        post(tag, event);
    }

    public void postNoCompleted(@NonNull String tag) {
        Event event = new Event();
        event.status = Event.STATUS_NOT_COMPLETE;
        post(tag, event);
    }


    public void postError(@NonNull String tag, @NonNull Throwable error) {
        Event event = new Event();
        event.status = Event.STATUS_FAILED;
        event.setError(error);
        post(tag, event);
    }
}
