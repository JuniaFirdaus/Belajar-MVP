package juniafirdaus.com.belajarmvp.feature.detail;

import juniafirdaus.com.belajarmvp.models.ResultsItem;

interface DetailView {

    void showLoading();

    void hideLoading();

    void getDataSuccess(ResultsItem model);

    void getDataFail(String message);

    void refreshData();
}
