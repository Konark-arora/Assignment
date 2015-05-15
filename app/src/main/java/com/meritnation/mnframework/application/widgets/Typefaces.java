/*
 * Copyright (c) 2015. This is under copyright of Applect Learning System pvt. ltd.
 * All rights are reserved.
 */

package com.meritnation.mnframework.application.widgets;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class Typefaces
	{
		private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

		public static Typeface get(Context c, String name){
			synchronized(cache){
				if(!cache.containsKey(name)){
					Typeface t = Typeface.createFromAsset(
							c.getAssets(), 
							/*String.format("fonts/%s.ttf", name)*/name
						);
					cache.put(name, t);
				}
				return cache.get(name);
			}
		}
	}
