package org.miscwidgets;

import org.miscwidgets.widget.VirtualKeyboard;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class TestVirtualKeyboard extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virtual_keyboard_main);
		EditText edit = (EditText) findViewById(R.id.edit);
        VirtualKeyboard kbd = (VirtualKeyboard) findViewById(R.id.kbd);
        kbd.bringToFront();
        kbd.setUp(edit);
    }
}
