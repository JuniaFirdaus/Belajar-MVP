package juniafirdaus.com.belajarmvp.feature.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import juniafirdaus.com.belajarmvp.BuildConfig;
import juniafirdaus.com.belajarmvp.R;
import juniafirdaus.com.belajarmvp.base.mvp.MvpActivity;
import juniafirdaus.com.belajarmvp.models.ResultsItem;


public class DetailActivity extends MvpActivity<DetailPresenter> implements DetailView, View.OnClickListener {

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.descriptionLayout)
    CardView descriptionLayout;

    private String bookId;

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        bookId = getIntent().getStringExtra("id");
        presenter.loadData();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        descriptionLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        descriptionLayout.setVisibility(View.VISIBLE);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getDataSuccess(ResultsItem item) {
        description.setText(item.getReleaseDate());
        Glide.with(this).load(BuildConfig.IMAGE + item.getBackdropPath());
        collapsingToolbarLayout.setTitle(item.getTitle());
    }


    @Override
    public void getDataFail(String message) {
        Toast.makeText(this, "Please load again",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshData() {
        presenter.loadData();
    }

    @Override
    public void onClick(View view) {
        refreshData();
    }
}
