package org.miscwidgets;

import org.miscwidgets.interpolator.BackInterpolator;
import org.miscwidgets.interpolator.BounceInterpolator;
import org.miscwidgets.interpolator.CircInterpolator;
import org.miscwidgets.interpolator.CubicInterpolator;
import org.miscwidgets.interpolator.ElasticInterpolator;
import org.miscwidgets.interpolator.ExpoInterpolator;
import org.miscwidgets.interpolator.QuadInterpolator;
import org.miscwidgets.interpolator.QuartInterpolator;
import org.miscwidgets.interpolator.QuintInterpolator;
import org.miscwidgets.interpolator.SineInterpolator;
import org.miscwidgets.interpolator.EasingType.Type;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TestInterpolators extends Activity {
    private Button button0;
    private Button button1;
	private PlotView plot;
	private Animation animLeft;
	private Animation animRight;
	private Animation animTop;
	private Spinner easing;
	private TextView text;
	private Interpolator interpolator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interpolator_main);
        
        animLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        animRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        animTop = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        
        plot = (PlotView) findViewById(R.id.plot);
        easing = (Spinner) findViewById(R.id.easing);
        button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(clickListener);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(clickListener);
        text = (TextView) findViewById(R.id.text);
        easing.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				text.setText((CharSequence) easing.getSelectedItem());
				switch (position) {
				case 0:
					interpolator = new BackInterpolator(Type.IN, 0);
					break;
				case 1:
					interpolator = new BackInterpolator(Type.OUT, 0);
					break;
				case 2:
					interpolator = new BackInterpolator(Type.INOUT, 0);
					break;
				case 3:
					interpolator = new ElasticInterpolator(Type.IN, 1, 0.3f);
					break;
				case 4:
					interpolator = new ElasticInterpolator(Type.OUT, 1, 0.3f);
					break;
				case 5:
					interpolator = new ElasticInterpolator(Type.INOUT, 1, 0.3f);
					break;
				case 6:
					interpolator = new BounceInterpolator(Type.IN);
					break;
				case 7:
					interpolator = new BounceInterpolator(Type.OUT);
					break;
				case 8:
					interpolator = new BounceInterpolator(Type.INOUT);
					break;
				case 9:
					interpolator = new CircInterpolator(Type.IN);
					break;
				case 10:
					interpolator = new CircInterpolator(Type.OUT);
					break;
				case 11:
					interpolator = new CircInterpolator(Type.INOUT);
					break;
				case 12:
					interpolator = new ExpoInterpolator(Type.IN);
					break;
				case 13:
					interpolator = new ExpoInterpolator(Type.OUT);
					break;
				case 14:
					interpolator = new ExpoInterpolator(Type.INOUT);
					break;
				case 15:
					interpolator = new QuadInterpolator(Type.IN);
					break;
				case 16:
					interpolator = new QuadInterpolator(Type.OUT);
					break;
				case 17:
					interpolator = new QuadInterpolator(Type.INOUT);
					break;
				case 18:
					interpolator = new CubicInterpolator(Type.IN);
					break;
				case 19:
					interpolator = new CubicInterpolator(Type.OUT);
					break;
				case 20:
					interpolator = new CubicInterpolator(Type.INOUT);
					break;
				case 21:
					interpolator = new QuartInterpolator(Type.IN);
					break;
				case 22:
					interpolator = new QuartInterpolator(Type.OUT);
					break;
				case 23:
					interpolator = new QuartInterpolator(Type.INOUT);
					break;
				case 24:
					interpolator = new QuintInterpolator(Type.IN);
					break;
				case 25:
					interpolator = new QuintInterpolator(Type.OUT);
					break;
				case 26:
					interpolator = new QuintInterpolator(Type.INOUT);
					break;
				case 27:
					interpolator = new SineInterpolator(Type.IN);
					break;
				case 28:
					interpolator = new SineInterpolator(Type.OUT);
					break;
				case 29:
					interpolator = new SineInterpolator(Type.INOUT);
					break;
				default:
					break;
				}
				plot.resetPath();
				for (float t=0; t<=1; t += 0.01f) {
					float interpolation = interpolator.getInterpolation(t);
//					Log.d("TestInterpolators", "interpolation " + interpolation);
					plot.addPoint(t, interpolation);
				}
				plot.invalidate();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
        });
    }

    OnClickListener clickListener = new OnClickListener() {
		public void onClick(View view) {
			animLeft.setInterpolator(interpolator);
			button0.startAnimation(animLeft);
			animRight.setInterpolator(interpolator);
			button1.startAnimation(animRight);
			animTop.setInterpolator(interpolator);
			plot.startAnimation(animTop);
		}
    };
}