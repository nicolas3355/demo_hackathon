package com.superfeed.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.superfeed.android.dummy.DummyContent;
import com.superfeed.android.dummy.DummySupermarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jkerry on 3/4/17.
 */

public class FoodListAdapter extends ArrayAdapter<DummySupermarket.DummyItem> {

    private static List<DummySupermarket.DummyItem> filterSupermarketList(Map<Integer, DummySupermarket.DummyItem> itemsMap, String supermarketName) {
        ArrayList<DummySupermarket.DummyItem> items = new ArrayList<>(itemsMap.values());
//        Iterator<DummySupermarket.DummyItem> iterator = items.iterator();
//        while (iterator.hasNext()) {
//            DummySupermarket.DummyItem x = iterator.next();
//            if (x.id < product.id) {
//                iterator.remove();
//            }
//        }
        return items;
    }

    private String mSupermarketName;

    public FoodListAdapter(Context context, Map<Integer, DummySupermarket.DummyItem> itemsMap, String supermarketName) {
        super(context, R.layout.availability_item, filterSupermarketList(itemsMap, supermarketName));
        mSupermarketName = supermarketName;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            LayoutInflater inflater = LayoutInflater.from(getContext());
            layout = inflater.inflate(R.layout.availability_item, parent, false);
        } else {
            layout = convertView;
        }

        TextView name = (TextView) layout.findViewById(R.id.product_name);
        name.setText(getItem(position).content);

        TextView supermarket = (TextView) layout.findViewById(R.id.supermarket_name);
        supermarket.setText(mSupermarketName);

        TextView price = (TextView) layout.findViewById(R.id.supermarket_price);
        price.setText(getItem(position).details);
        return layout;

    }
}
