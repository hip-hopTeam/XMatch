#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class Department(db.Model):
    __tablename__ = 'user'
    department_id = db.Column(db.Integer, primary_key=True, nullable=False)
    activity_num = db.Column(db.Integer)
    child_dep_num = db.Column(db.Integer)
    create_time = db.Column(db.Integer)
    dep_manager_id = db.Column(db.Integer)
    dep_name = db.Column(db.String(255))
    dep_summary = db.Column(db.String(255))
    image_url = db.Column(db.String(255))
    member_num = db.Column(db.String(255))

    resource_fields = {
        'department_id': fields.Integer,
        'activity_num': fields.Integer,
        'child_dep_num': fields.Integer,
        'create_time': fields.Integer,
        'dep_manager_id': fields.Integer,
        'dep_name': fields.String,
        'dep_summary': fields.String,
        'image_url': fields.String,
        'member_num': fields.String
    }

    @classmethod
    def add(self, department_id, activity_num, child_dep_num, create_time, 
            dep_manager_id, dep_name, dep_summary, image_url, member_num):
        new_department = Department(department_id=department_id, activity_num=activity_num,
                                    child_dep_num=child_dep_num, create_time=create_time, 
                                    dep_manager_id=dep_manager_id, dep_name=dep_name, 
                                    dep_summary=dep_summary, image_url=image_url, member_num=member_num)
        db.session.add(new_department)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, department_id):
        department = Department.query.filter_by(department_id=department_id).first()
        if department is None:
            return 'department not found', 401
        db.session.delete(department)
        db.session.commit()

    @classmethod
    def alter(self, department_id=None, activity_num=None, child_dep_num=None, create_time=None, 
              dep_manager_id=None, dep_name=None, dep_summary=None, image_url=None, member_num=None):
        department = Department.query.filter_by(department_id=department_id).first()
        if department is None:
            return 'department not found  ', 401

        if activity_num is not None:
            department.activity_num = activity_num

        if child_dep_num is not None:
            department.child_dep_num = child_dep_num

        if create_time is not None:
            department.create_time = create_time

        if dep_manager_id is not None:
            department.dep_manager_id = dep_manager_id

        if dep_name is not None:
            department.dep_name = dep_name

        if dep_summary is not None:
            department.dep_summary = dep_summary

        if image_url is not None:
            department.image_url = image_url

        if member_num is not None:
            department.member_num = member_num

        db.session.commit()

    @marshal_with(Department.resource_fields, envelope='resource')
    def search(self, id):
        department = Department.query.get(id)
        return department
