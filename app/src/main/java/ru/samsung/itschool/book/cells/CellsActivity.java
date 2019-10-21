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

     int WIDTH = 10;
     int HEIGHT = 10;

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


        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (Math.random() >= 0.5)
                {
                    cells[i][j].setBackgroundColor(Color.BLACK);
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
            int color1 = ((ColorDrawable)cells[tappedY][tappedX].getBackground()).getColor();

            if (color1 == 0xFFFFFFFF){
                cells[tappedY][tappedX].setBackgroundColor(Color.BLACK);
            }
            else{
                cells[tappedY][tappedX].setBackgroundColor(Color.WHITE);
            }

            for (int x = 0; x < WIDTH; x++)
            {
                int color = ((ColorDrawable)cells[tappedY][x].getBackground()).getColor();
                if (color == 0xFFFFFFFF){
                    cells[tappedY][x].setBackgroundColor(Color.BLACK);
                }
                else {
                    cells[tappedY][x].setBackgroundColor(Color.WHITE);
                }
            }
            for (int y = 0; y < WIDTH; y++)
            {
                int color = ((ColorDrawable)cells[y][tappedX].getBackground()).getColor();
                if(color == 0xFFFFFFFF){
                    cells[y][tappedX].setBackgroundColor(Color.BLACK);
                }
                else{
                    cells[y][tappedX].setBackgroundColor(Color.WHITE);
                }
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