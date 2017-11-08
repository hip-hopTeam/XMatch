#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
api = Api(app)
db = SQLAlchemy(app)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://zsqxmatch:13110521828@120.25.241.49:3306/xmatch'

#db.create_all()