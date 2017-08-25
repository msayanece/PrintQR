package com.example.admin.printqr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Bitmap printQRCode(String textToQR){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(textToQR, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onClick(View view) {
        TextView text = (TextView) findViewById(R.id.editText);
        if (text.getText().toString().isEmpty()){
            Toast.makeText(this, "Kindly enter any text first", Toast.LENGTH_SHORT).show();
            return;
        }
        Bitmap QRBit = printQRCode(text.getText().toString());
        if (QRBit == null){
            Toast.makeText(this, "Unable to generate code!", Toast.LENGTH_SHORT).show();
        }else {
            Intent qRIntent = new Intent(this, ShowPrintQR.class);
            qRIntent.putExtra("bitmap", QRBit);
            startActivity(qRIntent);
        }
    }
}
