package com.dkpathak.rxjavaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import rx.Observer;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager rLmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rView.setLayoutManager(rLmanager);
        ArrayList<RowItem> itemList = new ArrayList<>();
        itemList.add(new RowItem("DK",true));
        itemList.add(new RowItem("awda",false));
        itemList.add(new RowItem("PK",false));
        itemList.add(new RowItem("CK",false));
        itemList.add(new RowItem("KK",false));
        itemList.add(new RowItem("FK",false));
        itemList.add(new RowItem("YK",false));
        itemList.add(new RowItem("oK",false));


        adapter = new MyAdapter(itemList);

        rView.setAdapter(adapter);
        adapter.getPositionClicks().subscribe(new Observer<RowItem>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"finished",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(RowItem rowItem) {
                Toast.makeText(MainActivity.this,rowItem.getName(),Toast.LENGTH_SHORT).show();
                Log.i("Item",rowItem.getName());
            }
        });
    }


}
