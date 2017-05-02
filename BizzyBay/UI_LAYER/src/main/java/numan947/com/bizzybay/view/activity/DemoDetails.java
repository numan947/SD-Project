package numan947.com.bizzybay.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import numan947.com.bizzybay.R;

public class DemoDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.demotoolbar);
        setSupportActionBar(toolbar);

        Button b1 = (Button) findViewById(R.id.browse_products_button_add_to_wishlist);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DemoDetails.this,"Added to WishList",Toast.LENGTH_SHORT).show();
            }
        });


        Button b2 = (Button) findViewById(R.id.browse_products_button_add_to_cart);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DemoDetails.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });

        Button b3 = (Button) findViewById(R.id.browse_products_button_buy_now);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DemoDetails.this,"TO be implemented",Toast.LENGTH_SHORT).show();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)finish();

        return super.onOptionsItemSelected(item);
    }
}
