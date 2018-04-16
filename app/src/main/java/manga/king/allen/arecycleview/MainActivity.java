package manga.king.allen.arecycleview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ARecycleView rv;
    List<String> listData;
    TestAdapter adapter;
    private int index = 0;
    public static final String TAG = "TEST";
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                index = 0;
                listData.clear();
                listData.addAll(generateData());
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeContainer.setRefreshing(false);
                    }
                }, 3000);
            }
        });
        rv = findViewById(R.id.list);
        listData = new ArrayList<>();
        setUpListView(rv);

    }

    private void setUpListView(final ARecycleView rv) {
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        rv.setLayoutManager(layoutManager);
        adapter = new TestAdapter(listData);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == listData.size())
                    return 3;
                else return 1;
            }
        });

        rv.setAdapter(adapter);
        int oldSize = listData.size();
        listData.addAll(generateData());
        int newSize = listData.size();
        adapter.notifyItemRangeInserted(oldSize, newSize - oldSize);
        rv.setOnScrolledToEndListener(new OnScrolledToEndListener() {
            @Override
            public void onScrolledToEnd() {
                Log.d(TAG, "Can't down");
                final int oldSize = listData.size();
                listData.addAll(generateData());
                final int newSize = listData.size();
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemRangeInserted(oldSize, newSize - oldSize);
                    }
                }, 800);
            }
        });
    }

    private List<String> generateData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 75; i++) {
            index++;
            list.add(String.valueOf(index));
        }
        return list;
    }
}
