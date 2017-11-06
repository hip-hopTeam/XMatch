#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class Dep_manager(db.Model):
    __tablename__ = 'dep_manager'
    dep_manager_id = db.Column(db.Integer, primary_key=True, nullable=False)
    dep_manager_account = db.Column(db.String(255))
    department_id = db.Column(db.Integer)
    password = db.Column(db.String(255))
    email = db.Column(db.String(255))
    phone_num = db.Column(db.String(255))
    manager_name = db.Column(db.String(255))

    resource_fields = {
        'dep_manager_id': fields.Integer,
        'department_id': fields.Integer,
        'dep_manager_account': fields.String,
        'password': fields.String,
        'email': fields.String,
        'phone_num': fields.String,
        'manager_name': fields.String
    }

    @classmethod
    def add(self, dep_manager_id, department_id, dep_manager_account,
            password, email, phone_num, manager_name):
        new_dep_manager = Dep_manager(dep_manager_id=dep_manager_id, department_id=department_id, 
                                      dep_manager_account=dep_manager_account, password=password, 
                                      email=email, phone_num=phone_num, manager_name=manager_name)
        db.session.add(new_dep_manager)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, user_id):
        dep_manager = Dep_manager.query.filter_by(dep_manager_id=dep_manager_id).first()
        if dep_manager is None:
            return 'dep_manager not found', 401
        db.session.delete(dep_manager)
        db.session.commit()

    @classmethod
    def alter(self, dep_manager_id=None, department_id=None, dep_manager_account=None,
              password=None, email=None, phone_num=None, manager_name=None):
        dep_manager = Dep_manager.query.filter_by(dep_manager_id=dep_manager_id).first()
        if dep_manager is None:
            return 'dep_manager not found  ', 401

        if department_id is not None:
            dep_manager.department_id = department_id
        
        if dep_manager_account is not None:
            dep_manager.dep_manager_account = dep_manager_account

        if password is not None:
            dep_manager.password = password

        if email is not None:
            dep_manager.email = email

        if phone_num is not None:
            dep_manager.phone_num = phone_num

        if manager_name is not None:
            dep_manager.manager_name = manager_name        

        db.session.commit()

    @marshal_with(Dep_manager.resource_fields, envelope='resource')
    def search(self, id):
        dep_manager = Dep_manager.query.get(id)
        return dep_manager
