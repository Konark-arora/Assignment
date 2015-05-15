/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */
package com.meritnation.mnframework.application.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class ButtonRegular extends android.widget.Button
	{
		public ButtonRegular(Context context)
			{
				this(context, null);
			}

		public ButtonRegular(Context context, AttributeSet attrs)
			{
				super(context, attrs);
			Typeface tf=Typefaces.get(context,"PTsansregular.ttf");
				this.setTypeface(tf);
			}

	}
