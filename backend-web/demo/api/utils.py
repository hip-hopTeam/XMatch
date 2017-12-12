#!/usr/bin/env python

from flask import session

def isLoggedIn():
	if "logged_in" in session:
		if session["logged_in"] == "True":
			return True
		else:
			return False
	return False