#!/usr/bin/env python
from flask import Flask, session
from flask_session import Session
from flask_cors import CORS, cross_origin
from api import api
from models import db

__env__ = "debug"

def main():
    app = Flask(__name__)
    #TODO: remove after frontend coder tested the api on localhost
    CORS(app,resources={r"/*": {"origins": "*"}})
    app.config['SESSION_TYPE'] = 'filesystem'
    app.config['SECRET_KEY'] = "secret key 4 prod Xmatch"
    Session(app)
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://zsqxmatch:13110521828@120.25.241.49:3306/xmatch'
    api.init_app(app)
    db.init_app(app)
    host = 'localhost'
    if __env__ == 'prod':
        host='0.0.0.0'
    app.run(debug=True, host=host)

if __name__ == '__main__':
    main()