package manga.king.allen.arecycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class ARecycleView extends RecyclerView {
    public static final int FAKE_SCROLLEND_PREVENT = 50;
    OnScrolledToEndListener onScrolledToEndListener;
    long lastScrollEnd;
    boolean isRefreshing;
    boolean isLoadingMore;


    public ARecycleView(Context context) {
        this(context, null);
    }

    public ARecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ARecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
        isLoadingMore = !isRefreshing;
    }

    public boolean isLoadingMore() {
        return isLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        isLoadingMore = loadingMore;
        isRefreshing = !isLoadingMore;
    }

    public void setOnScrolledToEndListener(OnScrolledToEndListener onScrolledToEndListener) {
        this.onScrolledToEndListener = onScrolledToEndListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        if (onScrolledToEndListener != null && !canScrollVertically(1)) {
            if (System.currentTimeMillis() > lastScrollEnd + FAKE_SCROLLEND_PREVENT && !isLoadingMore) {
                onScrolledToEndListener.onScrolledToEnd();
                lastScrollEnd = System.currentTimeMillis();
            }
        }
        super.onScrolled(dx, dy);
    }
}
