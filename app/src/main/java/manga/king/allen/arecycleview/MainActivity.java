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
//        swipeContainer.setRefreshing(true);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                listData.clear();
                listData.addAll(generateData());
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        swipeContainer.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        rv = findViewById(R.id.list);
        listData = new ArrayList<>();
        setUpListView(rv);

    }

    private void setUpListView(final ARecycleView rv) {
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        adapter = new TestAdapter(listData);
        rv.setAdapter(adapter);
        listData.addAll(generateData());
        adapter.notifyDataSetChanged();
        rv.setOnScrolledToEndListener(new OnScrolledToEndListener() {
            @Override
            public void onScrolledToEnd() {
                Log.d(TAG, "Can't down");
                listData.addAll(generateData());
                rv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
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
