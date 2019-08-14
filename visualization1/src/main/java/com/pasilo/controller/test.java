package com.pasilo.controller;

import java.util.regex.Pattern;

public class test {
	public static void main(String[] args) {
		String usernamePattern = "^[\\da-zA-Z\\*#@]{6,25}$";
		String passwdPattern = "^[a-zA-Z\\d][\\dA-Za-z\\-\\*#@]{6,25}$";

		System.out.println(Pattern.matches(usernamePattern,"hfzispasilo"));
	}
}
