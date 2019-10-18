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

public class shift_cipher extends AppCompatActivity {

    EditText pt, key;
    TextView cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_cipher);
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
            int k = Integer.parseInt(key.getText().toString());
            int l = plain.length();
            char c = ' ';
            String op = "";
            for (int i = 0; i < l; i++) {
                c = plain.charAt(i);
                c = (char) (((c - 'a' + k) % 26) + 'a');
                op = op + c;
            }
            cipher.setText(op);
        }
    }

    public void decrypt(View view) {
        pt = findViewById(R.id.inputText);
        key = findViewById(R.id.key1);
        cipher = findViewById(R.id.result);
        if (pt.getText().toString().matches("") || key.getText().toString().matches("")) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
        } else {
            String plain = pt.getText().toString();
            int k = Integer.parseInt(key.getText().toString());
            int l = plain.length();
            char c = ' ';
            String op = "";
            int d = 0;
            for (int i = 0; i < l; i++) {
                c = plain.charAt(i);
                c = (char) (c - k);
                if (c >= 97)
                    op = op + c;
                else {
                    d = 97 - c;
                    c = (char) (123 - d);
                    op = op + c;
                }
            }
            cipher.setText(op);
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
