
package com.truecaller.assignment.application.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonBold extends Button
	{
		public ButtonBold(Context context)
			{
				super(context, null);
			}

		public ButtonBold(Context context, AttributeSet attrs)
			{
				super(context, attrs);
			Typeface tf=Typefaces.get(context, "PTbold.ttf");
				this.setTypeface(tf);
			}
	}
