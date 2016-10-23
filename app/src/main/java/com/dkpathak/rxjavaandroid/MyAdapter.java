package com.dkpathak.rxjavaandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by dharmendra on 23/10/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<RowItem> itemArrayList;
    RadioButton lastRadioButton;
    private int lastSelectedPosition = 0;
    private final PublishSubject<RowItem> onClickSubject = PublishSubject.create();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_row, parent, false));
    }

    public MyAdapter(ArrayList<RowItem> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final RowItem rowItem = itemArrayList.get(position);


        holder.textView.setText(itemArrayList.get(position).getName());
        holder.radioButton.setChecked(itemArrayList.get(position).isChecked());

        if(position==0 && rowItem.isChecked()) {
            lastRadioButton= holder.radioButton;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastRadioButton.setChecked(false);
                itemArrayList.get(lastSelectedPosition).setChecked(false);

                lastRadioButton = (RadioButton) v.findViewById(R.id.row_radio);
                lastSelectedPosition = position;

                ((RadioButton) v.findViewById(R.id.row_radio)).setChecked(true);
                rowItem.setChecked(true);


                onClickSubject.onNext(rowItem);
            }
        });

    }

    public Observable<RowItem> getPositionClicks(){
        return onClickSubject.asObservable();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RadioButton radioButton;
//        private View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_tv);
            radioButton = (RadioButton) itemView.findViewById(R.id.row_radio);
        }
    }

}
