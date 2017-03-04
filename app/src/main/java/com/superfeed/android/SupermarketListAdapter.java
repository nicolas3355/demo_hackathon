package com.superfeed.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.superfeed.android.dummy.DummyContent;
import com.superfeed.android.dummy.DummySupermarket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jkerry on 3/4/17.
 */

public class SupermarketListAdapter extends ArrayAdapter<DummySupermarket.DummyItem> {

    private static List<DummySupermarket.DummyItem> filterSupermarketList(Map<Integer, DummySupermarket.DummyItem> itemsMap, DummyContent.DummyItem product) {
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

    public SupermarketListAdapter(Context context, Map<Integer, DummySupermarket.DummyItem> itemsMap, DummyContent.DummyItem product) {
        super(context, R.layout.availability_item, filterSupermarketList(itemsMap, product));
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

        TextView name = (TextView) layout.findViewById(R.id.supermarket_name);
        name.setText(getItem(position).content);

        TextView price = (TextView) layout.findViewById(R.id.supermarket_price);
        price.setText(getItem(position).details);
        return layout;

    }
}
