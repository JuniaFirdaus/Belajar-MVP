package juniafirdaus.com.belajarmvp.feature.detail;
import juniafirdaus.com.belajarmvp.base.ui.BasePresenter;
import juniafirdaus.com.belajarmvp.models.ResultsItem;
import juniafirdaus.com.belajarmvp.network.NetworkCallback;


public class DetailPresenter extends BasePresenter<DetailView> {

    DetailPresenter(DetailView view) {
        super.attachView(view);
    }

    void loadData() {
        view.showLoading();
        addSubscribe(apiStores.getNowPlaying(), new NetworkCallback<ResultsItem>() {
            @Override
            public void onSuccess(ResultsItem model) {
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

}