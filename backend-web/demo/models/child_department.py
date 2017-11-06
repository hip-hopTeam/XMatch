#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class Child_department(db.Model):
    __tablename__ = 'child_department'
    child_department_id = db.Column(db.Integer, primary_key=True, nullable=False)
    email = db.Column(db.String(255))
    phone = db.Column(db.String(255))
    name = db.Column(db.String(255))

    resource_fields = {
        'child_department_id': fields.Integer,
        'email': fields.String,
        'phone': fields.String,
        'name': fields.String
    }

    @classmethod
    def add(self, child_department_id, email, phone, name):
        new_child_department = User(child_department_id=child_department_id, email=email, phone=phone, name=name)
        db.session.add(new_child_department)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, child_department_id):
        child_department = Child_department.query.filter_by(child_department_id=child_department_id).first()
        if child_department is None:
            return 'child_department not found', 401
        db.session.delete(child_department)
        db.session.commit()

    @classmethod
    def alter(self, child_department_id=None, email=None, phone=None, name=None):
        child_department = Child_department.query.filter_by(child_department_id=child_department_id).first()
        if child_department is None:
            return 'child_department not found  ', 401
        
        if email is not None:
            child_department.email = email
        
        if phone is not None:
            child_department.phone = phone 

        if name is not None:
            child_department.name = name

        db.session.commit()

    @marshal_with(Child_department.resource_fields, envelope='resource')
    def search(self, id):
        child_department = Child_department.query.get(id)
        return child_department

