package com.example.coffeeorder;



import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity,cookie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when order button is clicked.
     */
    public void submitOrder(View view){

        CheckBox checkBox=(CheckBox)findViewById(R.id.Whipped_check_box);
        CheckBox checkBox1=(CheckBox)findViewById(R.id.chocolate_check_box);
        EditText editText=(EditText) findViewById(R.id.name_edit_text);
        String name=editText.getText().toString();
        EditText editText1=(EditText) findViewById(R.id.address_edit_text);
        String address=editText1.getText().toString();

          boolean isWhipped=checkBox.isChecked();
        boolean isChocolate=checkBox1.isChecked();
         displayPrice(isWhipped,isChocolate,name,address);






    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number){
        TextView quantityTextView=(TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(boolean hasWhipped,boolean hasChocolate,String name,String address){

        String s="";
        int p =5*quantity + 2*cookie;
        if(quantity>1) {
            if (hasWhipped && hasChocolate) {
                s = getString(R.string.whippedC);
                p += quantity * 4;
            } else if (!(hasWhipped) && hasChocolate) {
                s = getString(R.string.choco);
                p += quantity * 2;
            } else if (hasWhipped && !(hasChocolate)) {
                s = getString(R.string.whipped);
                p += quantity * 2;
            }
        }
        String  ms=getString(R.string.orderName,name)+ "\n" +getString(R.string.address,address) + address +"\n"+getString(R.string.Coffee) + " " + s + quantity+"\n" + getString(R.string.Cookie) + " " + cookie+"\n"+getString(R.string.total) + p  +"\n" +getString(R.string.thank);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, ms);
        intent.putExtra(Intent.EXTRA_SUBJECT, name);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private void displayC(int number){
        TextView quantityTextView=(TextView) findViewById(R.id.cookie_text_view);
        quantityTextView.setText("" + number);}
    public void increment(View view){
         quantity++;
        display(quantity);

    }
    public void decrement(View view){

        if(quantity>0)
            quantity--;
        display(quantity);


    }



    public void whipped(View view){
        if(quantity>1) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.Whipped_check_box);
            View view1 = findViewById(R.id.linear_back);
            if (checkBox.isChecked()) {

                view1.setBackgroundResource(R.drawable.wh);
            } else {
                view1.setBackgroundResource(R.drawable.c1);
            }
        }
    }
    public void chocolate(View view){
        if(quantity>1) {
            CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_check_box);
            View view1 = findViewById(R.id.linear_back);
            ;
            if (checkBox.isChecked()) {

                view1.setBackgroundResource(R.drawable.ch);
            } else {
                view1.setBackgroundResource(R.drawable.c1);
            }
        }
    }
    public void incrementC(View view){
        cookie++;
        displayC(cookie);

    }
    public void decrementC(View view){

        if(cookie>0)
            cookie--;
        displayC(cookie);


    }


}
