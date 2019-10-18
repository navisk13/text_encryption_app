package com.example.navaneeth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class product_cipher extends AppCompatActivity {

    EditText pt, key;
    TextView cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cipher);
        TextView resulttext = findViewById(R.id.result);
        registerForContextMenu(resulttext);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, v.getId(), 0,
                "Copy");
        TextView yourTextView = (TextView) v;
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(yourTextView.getText());
    }

    public void encrypt(View view) {
        pt = findViewById(R.id.inputText);
        key = findViewById(R.id.key1);
        cipher = findViewById(R.id.result);
        if (pt.getText().toString().matches("") || key.getText().toString().matches("")) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
        } else {
            String plain = pt.getText().toString();
            int shift = Integer.parseInt(key.getText().toString());
            int n = plain.length();
            char ch1[] = plain.toCharArray();
            char ch3;
            String op = "";
            for (int i = 0; i < n; i++) {
                if (Character.isLetter(ch1[i])) {
                    ch3 = (char) (((int) ch1[i] * shift - 97) % 26 + 97);
                    op = op + ch3;
                }
            }
            cipher.setText(op);
        }
    }

    public void decrypt(View view) {
        char ch4;
        String op = "";
        key = findViewById(R.id.key1);
        pt = findViewById(R.id.inputText);
        cipher = findViewById(R.id.result);
        if (pt.getText().toString().matches("") || key.getText().toString().matches("")) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
        } else {
            String str1 = pt.getText().toString();
            int shift = Integer.parseInt(key.getText().toString());
            int q = 0, flag = 0;
            for (int i = 0; i < 26; i++) {
                if (((i * 26) + 1) % shift == 0) {
                    q = ((i * 26) + 1) / shift;
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                char ch2[] = str1.toCharArray();
                for (int i = 0; i < str1.length(); i++) {
                    if (Character.isLetter(ch2[i])) {

                        ch4 = (char) (((int) ch2[i] * q - 97) % 26 + 97);
                        op = op + ch4;
                    } else if (ch2[i] == ' ') {
                        op = op + ch2[i];
                    }
                }
                cipher.setText(op);
            } else {
                Toast.makeText(this, "Invalid Key!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void sharetext(View view) {
        cipher = findViewById(R.id.result);
        if (cipher.getText().toString().matches("")) {
            Toast.makeText(this, "Please encrypt/decrypt text first!", Toast.LENGTH_SHORT).show();
        } else {
            String textshare = cipher.getText().toString();
            String mimetype = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimetype)
                    .setChooserTitle("Share text with:")
                    .setText(textshare)
                    .startChooser();
        }
    }
}

