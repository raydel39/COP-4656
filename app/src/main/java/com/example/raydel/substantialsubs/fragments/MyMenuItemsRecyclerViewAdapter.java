package com.example.raydel.substantialsubs.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.raydel.substantialsubs.R;
import com.example.raydel.substantialsubs.fragments.MenuItemsFragment.OnListFragmentInteractionListener;
import com.example.raydel.substantialsubs.model.MenuItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MenuItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyMenuItemsRecyclerViewAdapter extends RecyclerView.Adapter<MyMenuItemsRecyclerViewAdapter.ViewHolder> {

    private final List<MenuItem> menuItems;
    private final OnListFragmentInteractionListener mListener;

    public MyMenuItemsRecyclerViewAdapter(List<MenuItem> items, OnListFragmentInteractionListener listener) {
        menuItems = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_menuitems, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.menuItem = menuItems.get(position);
        holder.nameView.setText(menuItems.get(position).getName());
        holder.priceView.setText(String.valueOf(menuItems.get(position).getPrice()));
        holder.descriptionView.setText(menuItems.get(position).getDescription());
        holder.qtyView.setText(Integer.toString(menuItems.get(position).getQuantity()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.menuItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameView;
        public final TextView priceView;
        public final TextView descriptionView;
        public final TextView qtyView;
        public MenuItem menuItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameView = (TextView) view.findViewById(R.id.menuItemNameTextView);
            priceView = (TextView) view.findViewById(R.id.priceTextView);
            descriptionView = (TextView) view.findViewById(R.id.menuItemDescriptionTextView);
            qtyView = (TextView) view.findViewById(R.id.selectedQtyTextView);
        }
    }
}
