package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;


import androidx.annotation.ColorRes;

import task.Stub;
import task.Task;

public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

     int WIDTH = 9;
     int HEIGHT = 5;

     Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }

    void generate() {

        makeCells();


        int num = 1;
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                cells[i][j].setText(num + "");
                num++;
            }

    }

    @Override
    public boolean onLongClick(View v) {

        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        cells[tappedY][tappedX].setBackgroundColor(Color.GREEN);

        return false;
    }

    @Override
    public void onClick(View v) {

        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        int color = ((ColorDrawable)cells[tappedY][tappedX].getBackground()).getColor();

        if (color == 0xFFFF0000){
            cells[tappedY][tappedX].setBackgroundColor(Color.WHITE);
        }
        else{
            cells[tappedY][tappedX].setBackgroundColor(Color.RED);
        }

    }

    int getX(View v) { return Integer.parseInt(((String) v.getTag()).split(",")[1]); }

    int getY(View v) { return Integer.parseInt(((String) v.getTag()).split(",")[0]); }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(HEIGHT);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }
}