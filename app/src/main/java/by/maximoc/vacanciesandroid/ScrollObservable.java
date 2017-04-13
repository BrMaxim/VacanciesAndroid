package by.maximoc.vacanciesandroid;


import android.support.v7.widget.RecyclerView;

import io.reactivex.Observable;


public class ScrollObservable {
    public static Observable<Integer> getScrollObservable(RecyclerView recyclerView, int limit, int emptyListCount) {

        return Observable.create(subscriber -> {
            final RecyclerView.OnScrollListener sl = new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if (!subscriber.isDisposed()) {
                        int position = 0;
                        int updatePosition = recyclerView.getAdapter().getItemCount() - 1 - (limit / 2);
                        if (position >= updatePosition) {
                            subscriber.onNext(recyclerView.getAdapter().getItemCount());
                        }
                    }
                }
            };
            recyclerView.addOnScrollListener(sl);
            if (recyclerView.getAdapter().getItemCount() == emptyListCount) {
                subscriber.onNext(recyclerView.getAdapter().getItemCount());
            }
        });
    }
}
