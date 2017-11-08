#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class User(db.Model):
    __tablename__ = 'user'
    user_id = db.Column(db.Integer, primary_key=True, nullable=False)
    bind_phone = db.Column(db.Integer)
    college = db.Column(db.String(255))
    passwd = db.Column(db.String(255))
    email = db.Column(db.String(255))
    phone_num = db.Column(db.String(255))
    username = db.Column(db.String(255))
    stu_no = db.Column(db.String(255))
    sex = db.Column(db.Integer, nullable=False)

    resource_fields = {
        'user_id': fields.Integer,
        'bind_phone': fields.Integer,
        'college': fields.String,
        'passwd': fields.String,
        'email': fields.String,
        'phone_num': fields.String,
        'username': fields.String,
        'stu_no': fields.String,
        'sex': fields.Integer
    }

    @classmethod
    def add(self, user_id, bind_phone, college, passwd, email, phone_num, username, stu_no, sex):
        new_user = User(user_id=user_id, bind_phone=bind_phone, college=college, 
                        passwd=passwd, email=email, phone_num=phone_num, 
                        username=username, stu_no=stu_no, sex=sex)
        db.session.add(new_user)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, user_id):
        user = User.query.filter_by(user_id=user_id).first()
        if user is None:
            return 'User not found', 401
        db.session.delete(user)
        db.session.commit()

    @classmethod
    def alter(self, user_id=None, bind_phone=None, college=None,
              passwd=None, email=None, phone_num=None, username=None,
              stu_no=None, sex=None):
        user = User.query.filter_by(user_id=user_id).first()
        if user is None:
            return 'User not found  ', 401

        if bind_phone is not None:
            user.bind_phone = bind_phone
        
        if college is not None:
            user.college = college
        
        if passwd is not None:
            user.passwd = passwd
        
        if email is not None:
            user.email = email
        
        if phone_num is not None:
            user.phone_num = phone_num

        if username is not None:
            user.username = username

        if stu_no is not None:
            user.stu_no = stu_no

        if sex is not None:
            user.sex = sex

        db.session.commit()

    @marshal_with(User.resource_fields, envelope='resource')
    def search(self, id):
        user = User.query.get(id)
        return user
