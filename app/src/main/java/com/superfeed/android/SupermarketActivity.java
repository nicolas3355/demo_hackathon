package com.superfeed.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.superfeed.android.dummy.DummyContent;
import com.superfeed.android.dummy.DummySupermarket;

public class SupermarketActivity extends AppCompatActivity {

    private static final String ARG_SUPERMARKET_NAME = "supermarket_name";
    private LinearLayout mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mListView = (LinearLayout) findViewById(R.id.availability_list);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String superMarketName = bundle.getString(ARG_SUPERMARKET_NAME);

            setTitle(superMarketName);

            FoodListAdapter adapter = new FoodListAdapter(this, DummySupermarket.ITEM_MAP, superMarketName);
            for (int i = 0; i < adapter.getCount(); i++) {
                View view = adapter.getView(i, null, mListView);
                mListView.addView(view);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
