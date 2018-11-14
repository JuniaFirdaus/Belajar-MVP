package juniafirdaus.com.belajarmvp.base.ui;

import android.util.Log;

import juniafirdaus.com.belajarmvp.network.InitRetrofit;
import juniafirdaus.com.belajarmvp.network.ApiServices;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class BasePresenter<V> {
    public V view;
    protected ApiServices apiStores;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    protected void attachView(V view) {
        this.view = view;
        apiStores = InitRetrofit.getRetrofit().create(ApiServices.class);
    }

    public void dettachView() {
        this.view = null;
        onUnsubscribe();
    }

    private void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
