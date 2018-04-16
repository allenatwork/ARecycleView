package manga.king.allen.arecycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<String> listData;
    public static final int TYPE_REGULAR = 0;
    public static final int TYPE_FOOTER = 1;

    public TestAdapter(List<String> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                View loadmoreview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more, parent, false);
                return new LoadMoreHolder(loadmoreview);
            default:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
                return new TestHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TestHolder) {
            ((TestHolder) holder).display(listData.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) return TYPE_FOOTER;
        return TYPE_REGULAR;
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size() + 1;
    }

    public static class TestHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TestHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }

        public void display(String text) {
            tv.setText(text);
        }
    }

    public static class LoadMoreHolder extends RecyclerView.ViewHolder {
        public LoadMoreHolder(View itemView) {
            super(itemView);
        }
    }
}
