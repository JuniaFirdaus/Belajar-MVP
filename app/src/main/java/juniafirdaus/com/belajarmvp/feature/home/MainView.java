package juniafirdaus.com.belajarmvp.feature.home;

import android.content.Intent;

import juniafirdaus.com.belajarmvp.models.NowResponse;

interface MainView {
    void showLoading();

    void hideLoading();

    void getDataSuccess(NowResponse model);

    void getDataFail(String message);

    void moveToDetail(Intent intent);

}
