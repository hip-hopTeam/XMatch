#!/usr/bin/env python

import unittest
import urllib

class TestURLHTTPCode(unittest.TestCase):
	def setUp(self):
		self.urlSuccessList = ["http://120.25.241.49:8080/xmatch-0.0.1/api/activity/getAll",
							   "http://120.25.241.49:8080/xmatch-0.0.1/api/department/findAll",
							   "http://120.25.241.49:8080/xmatch-0.0.1/api/depManager/get?depManagerId=1"]
		self.urlNotFoundList = ["http://120.25.241.49:8080/xmatch-0.0.1/api/activity",
								"http://120.25.241.49:8080/xmatch-0.0.1/api/department",
								"http://120.25.241.49:8080/xmatch-0.0.1/api/depManager"]

	def test_sucess(self):
		for url in self.urlSuccessList:
			httpCode = urllib.urlopen(url).getcode()
			self.assertEqual(httpCode, 200)

	def test_not_found(self):
		for url in self.urlNotFoundList:
			httpCode = urllib.urlopen(url).getcode()
			self.assertEqual(httpCode, 404)

if __name__ == '__main__':
	unittest.main()