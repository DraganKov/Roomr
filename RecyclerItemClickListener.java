package com.example.ryan.roomr_ticketservice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public interface RecyclerItemClickListener {

    void onClick(View view, int position);
}
