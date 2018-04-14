package manga.king.allen.arecycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    public List<String> listData;

    public TestAdapter(List<String> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        holder.display(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
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
}
