package com.example.admin.printqr;

import android.graphics.Bitmap;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ShowPrintQR extends AppCompatActivity {

    private Bitmap qRBit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_print_qr);
        qRBit = getIntent().getParcelableExtra("bitmap");
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageBitmap(qRBit);
    }

    public void print(View view) {
        doPhotoPrint();
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        //print
        photoPrinter.printBitmap("image.png_test_print", qRBit, new PrintHelper.OnPrintFinishCallback() {
            @Override
            public void onFinish() {
                Toast.makeText(ShowPrintQR.this, "Thank you!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
