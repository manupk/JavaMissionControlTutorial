package com.oracle.example;

import com.oracle.jrockit.jfr.FlightRecorder;

public class EnablementExample {
	public static void main(String [] args) {
		System.out.println(FlightRecorder.isActive());
	}
}
