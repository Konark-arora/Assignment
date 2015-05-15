/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */
package com.meritnation.mnframework.application.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonCondensed extends Button
	{
		public ButtonCondensed(Context context)
			{
				super(context, null);
			}

		public ButtonCondensed(Context context, AttributeSet attrs)
			{
				super(context, attrs);
			Typeface tf=Typefaces.get(context,"PTNarrwowbold.ttf");
				this.setTypeface(tf);
			}
	}
