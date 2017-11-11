#!/usr/bin/env python
from flask import Flask
from api import api
from models import db


def main():
    app = Flask(__name__)
    app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://zsqxmatch:13110521828@120.25.241.49:3306/xmatch'
    api.init_app(app)
    db.init_app(app)
    app.run(debug=True)

if __name__ == '__main__':
    main()