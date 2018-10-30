package bawo.mysecondmvp.topmovies;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TopMoviesPresenter implements TopMoviesActivityMVP.Presenter {
    private TopMoviesActivityMVP.View view;
    private Subscription subscription = null;
    private TopMoviesActivityMVP.Model model;

    public TopMoviesPresenter(TopMoviesActivityMVP.Model model){
        this.model = model;
    }
    @Override
    public void loadData() {
        subscription = model.result().subsribe(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()){

        }
    }

    @Override
    public void rxUnsubscribe() {

    }

    @Override
    public void setView(TopMoviesActivityMVP.View view) {

    }
}
