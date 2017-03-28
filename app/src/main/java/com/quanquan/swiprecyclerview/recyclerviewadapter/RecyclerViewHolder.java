package com.quanquan.swiprecyclerview.recyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by ChangquanSun on 2016/7/22
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private SparseArray<View> mViews;

    private OnItemClickListener mClickListener;

    final public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        itemView.setOnClickListener(this);
    }

    public View getItemView() {
        return itemView;
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (mClickListener != null) {
            mClickListener.onItemClick(itemView,getAdapterPosition());
        }
    }
    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }

    public RecyclerViewHolder setTextColor(int viewId, int resId) {
        TextView view = findViewById(viewId);
        view.setTextColor(resId);
        return this;
    }

    public RecyclerViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public RecyclerViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
