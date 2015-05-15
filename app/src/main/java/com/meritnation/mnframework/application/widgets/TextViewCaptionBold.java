/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */

package com.meritnation.mnframework.application.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextViewCaptionBold extends android.widget.TextView{

	public TextViewCaptionBold(Context context) {
		super(context,null);
	}
	public TextViewCaptionBold(Context context, AttributeSet attrs) {
		super(context, attrs);
		Typeface tf=Typefaces.get(context,"PTCcaption.ttf");
		this.setTypeface(tf);
	}


	}
