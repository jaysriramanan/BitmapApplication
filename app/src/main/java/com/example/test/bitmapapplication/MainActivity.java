package com.example.test.bitmapapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.imageView);
        LinearLayout linearLayout=findViewById(R.id.linearLayout);
        //Bitmap bitmap= BitmapFactory.decodeFile("https://www.google.co.in/imgres?imgurl=http%3A%2F%2Fhyd.city%2Fwp-content%2Fuploads%2F2017%2F11%2FUntitled123.png&imgrefurl=http%3A%2F%2Fhyd.city%2Fhyderabad-city%2F&docid=rT1McsZHJaUDrM&tbnid=5VDfTmkml37FRM%3A&vet=10ahUKEwjk67fUq8TdAhXETX0KHf8eA-MQMwiNAigBMAE..i&w=955&h=401&hl=en&bih=839&biw=1440&q=hyderabad%20city&ved=0ahUKEwjk67fUq8TdAhXETX0KHf8eA-MQMwiNAigBMAE&iact=mrc&uact=8");
        //imageView.setImageBitmap(bitmap);
        new GetImageFromUrl(imageView).execute("http://hyd.city/wp-content/uploads/2017/11/Untitled123.png");
        Button button=new Button(this);
        button.setText("image set");
        linearLayout.addView(button);
    }

    public class GetImageFromUrl extends AsyncTask<String,Void,Bitmap>{
        ImageView img;

        public GetImageFromUrl(ImageView img) {
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img.setImageBitmap(bitmap);
        }
    }
}
