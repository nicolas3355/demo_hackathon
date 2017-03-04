package com.superfeed.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.superfeed.android.dummy.DummyContent;
import com.superfeed.android.dummy.DummySupermarket;

public class DetailsActivity extends AppCompatActivity {

    public static final String ARG_PRODUCT_ID = "product_id";
    private DummyContent.DummyItem product;
    private ImageView mImageView;
    private LinearLayout mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        mNameTextView = (TextView) findViewById(R.id.product_name);
        mImageView = (ImageView) findViewById(R.id.product_image);
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
            int itemId = bundle.getInt(ARG_PRODUCT_ID);
            product = DummyContent.ITEM_MAP.get(itemId);

            setTitle(product.content);
            mImageView.setImageDrawable(ContextCompat.getDrawable(this, product.getDrawable()));

            TextView headerView = new TextView(this);
            headerView.setText(product.details);
            mListView.addView(headerView);

//            TextView emptyView = new TextView(this);
//            emptyView.setText(getString(R.string.no_availability));
//            mListView.setEmptyView(emptyView);

            SupermarketListAdapter adapter = new SupermarketListAdapter(this, DummySupermarket.ITEM_MAP, product);
            for (int i = 0; i < adapter.getCount(); i++) {
                View view = adapter.getView(i, null, mListView);
                mListView.addView(view);
            }
        }
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 1; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

//        View empty = findViewById(R.id.empty_list_message);
//        ListView list = (ListView) findViewById(R.id.availability_list);
//        list.setEmptyView(empty);
//        setListViewHeightBasedOnChildren(list);
    }
}
