package com.example.navaneeth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resume_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.resume)
        {
            Intent intent = new Intent(this, resume.class);
            startActivity(intent);
        }
        return true;
    }

    public void launchshift(View view)
    {
        Intent intent = new Intent(this, shift_cipher.class);
        startActivity(intent);
    }

    public void launchproduct(View view)
    {
        Intent intent = new Intent(this,product_cipher.class);
        startActivity(intent);
    }

    public void launchaffine(View view)
    {
        Intent intent = new Intent(this,affine_cipher.class);
        startActivity(intent);
    }


}
