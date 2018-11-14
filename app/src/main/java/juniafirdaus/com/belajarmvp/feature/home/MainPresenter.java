package juniafirdaus.com.belajarmvp.feature.home;

import android.app.Activity;
import android.content.Intent;

import juniafirdaus.com.belajarmvp.base.ui.BasePresenter;
import juniafirdaus.com.belajarmvp.feature.detail.DetailActivity;
import juniafirdaus.com.belajarmvp.models.NowResponse;
import juniafirdaus.com.belajarmvp.models.ResultsItem;
import juniafirdaus.com.belajarmvp.network.NetworkCallback;


class MainPresenter extends BasePresenter<MainView> {

    MainPresenter(MainView view) {
        super.attachView(view);
    }

    void loadData() {
        view.showLoading();
        addSubscribe(apiStores.getNowPlaying(), new NetworkCallback<NowResponse>() {
            @Override
            public void onSuccess(NowResponse model) {
                view.getDataSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    void getItem(ResultsItem item, Activity activity) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("id", item.getId());
        view.moveToDetail(intent);
    }
}