package by.maximoc.vacanciesandroid;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.reactivex.Observable;


public class RxScrolling {
    public static Observable<Integer> getScrollObservable(RecyclerView recyclerView,
                                                          int emptyListCount, LinearLayoutManager layoutManager) {
        return Observable.create(subscriber -> {
            final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (!subscriber.isDisposed()) {
                        int position = layoutManager.findLastCompletelyVisibleItemPosition();
                        int updatePosition = recyclerView.getAdapter().getItemCount() - 1 - Constants.COUNT_PER_PAGE / 2;
                        if (position >= updatePosition) {
                            subscriber.onNext(recyclerView.getAdapter().getItemCount() / Constants.COUNT_PER_PAGE);
                        }
                    }
                }
            };
            if (recyclerView.getAdapter().getItemCount() == emptyListCount) {
                recyclerView.addOnScrollListener(sl);
                subscriber.onNext(recyclerView.getAdapter().getItemCount());
            }
        });
    }
}
