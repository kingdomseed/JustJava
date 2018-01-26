package org.holtnet.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;
    private int price = 5;
    private String customerName = "";
    private boolean hasWhippedCream = false;
    private boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        EditText nameEditText = findViewById(R.id.name_edit_text);
        customerName = nameEditText.getText().toString();
        hasChocolate = chocolateCheckBox.isChecked();
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        createOrderSummary(customerName, calculatePrice(hasWhippedCream, hasChocolate), hasWhippedCream, hasChocolate);
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void createOrderSummary(String name, int price, boolean addWhippedCream, boolean hasChocolate) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JustJava Order for: " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Name: " + name + "\nWhipped Cream? " + addWhippedCream +
                "\nChocolate? " + hasChocolate + "\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank You!");
        if(emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        if(hasChocolate)
        {
            price += 2;
        }
        if(hasWhippedCream)
        {
            price++;
        }
        return price = quantity * price;
    }

    public void increment(View view) {
        if(quantity <= 100)
        {
            quantity++;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot choose less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        } else
        {
            Toast.makeText(this, "You cannot choose less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
        }

    }

}