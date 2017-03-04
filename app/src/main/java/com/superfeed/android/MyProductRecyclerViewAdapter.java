package com.superfeed.android;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.superfeed.android.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link ProductListFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyProductRecyclerViewAdapter extends RecyclerView.Adapter<MyProductRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final ProductListFragment.OnListFragmentInteractionListener mListener;

    public MyProductRecyclerViewAdapter(List<DummyItem> items, ProductListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        int drawableId;
        switch (position % 8) {
            case 0: drawableId = R.drawable.sample_0; break;
            case 1: drawableId = R.drawable.sample_1; break;
            case 2: drawableId = R.drawable.sample_2; break;
            case 3: drawableId = R.drawable.sample_3; break;
            case 4: drawableId = R.drawable.sample_4; break;
            case 5: drawableId = R.drawable.sample_5; break;
            case 6: drawableId = R.drawable.sample_6; break;
            default:
            case 7: drawableId = R.drawable.sample_7; break;
        }
        holder.mImageView.setImageDrawable(ContextCompat.getDrawable(holder.mView.getContext(), drawableId));
        holder.mNameView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mNameView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.product_image);
            mNameView = (TextView) view.findViewById(R.id.product_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
