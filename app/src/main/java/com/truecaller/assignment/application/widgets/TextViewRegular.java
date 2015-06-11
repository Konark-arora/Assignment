
package com.truecaller.assignment.application.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRegular extends TextView
	{
		public TextViewRegular(Context context)
			{
				this(context, null);
			}

		public TextViewRegular(Context context, AttributeSet attrs)
			{
				super(context, attrs);
				Typeface tf=Typefaces.get(context, "PTsansregular.ttf");
				this.setTypeface(tf);
			}

	}