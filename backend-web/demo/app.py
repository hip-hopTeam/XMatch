#!/usr/bin/env python
from flask import Flask
from flask_restful import Api

app = Flask(__name__)
api = Api(app)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://zsqxmatch:13110521828@120.25.241.49:3306/xmatch'

if __name__ == 'main':
    app.run("localhost")
