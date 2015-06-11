
package com.truecaller.assignment.application.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextViewBold extends android.widget.TextView
	{

		public TextViewBold(Context context)
			{
				super(context, null);
			}

		public TextViewBold(Context context, AttributeSet attrs)
			{
				super(context, attrs);
				Typeface tf=Typefaces.get(context, "PTbold.ttf");
				this.setTypeface(tf);
			}

	}
