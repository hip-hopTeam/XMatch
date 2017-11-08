#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class Activity(db.Model):
    __tablename__ = 'activity'
    activity_id = db.Column(db.Integer, primary_key=True, nullable=False)
    activity_name = db.Column(db.String(255))
    address = db.Column(db.String(255))
    content = db.Column(db.TEXT)
    create_time = db.Column(db.Integer)
    start_time = db.Column(db.Integer)
    end_time = db.Column(db.Integer)
    image_rul = db.Column(db.String(255))
    manager_phone = db.Column(db.String(255))
    sign_in = db.Column(db.Integer)

    resource_fields = {
        'activity_id': fields.Integer,
        'create_time': fields.Integer,
        'start_time': fields.Integer,
        'end_time': fields.Integer,
        'activity_name': fields.String,
        'address': fields.String,
        'content': fields.TEXT,
        'image_rul': fields.String,
        'manager_phone': fields.String,
        'sign_in': fields.Integer
    }

    @classmethod
    def add(self, activity_id, create_time, start_time, end_time, activity_name,
            address, content, image_rul, manager_phone):
        new_activity = Activity(activity_id=activity_id, create_time=create_time,
                                start_time=start_time, end_time=end_time, 
                                activity_name=activity_name, address=address,
                                content=content, image_rul=image_rul, 
                                manager_phone=manager_phone, sign_in=sign_in)
        db.session.add(new_activity)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, activity_id):
        activity = Activity.query.filter_by(activity_id=activity_id).first()
        if activity is None:
            return 'Activity not found', 401
        db.session.delete(activity)
        db.session.commit()

    @classmethod
    def alter(self, activity_id=None, create_time=None, start_time=None,
              end_time=None, activity_name=None, address=None, 
              content=None, image_rul=None, manager_phone=None, sign_in=None):
        activity = Activity.query.filter_by(activity_id=activity_id).first()
        if activity is None:
            return 'Activity not found  ', 401

        if create_time is not None:
            activity.create_time = create_time

        if start_time is not None:
            activity.start_time = start_time

        if end_time is not None:
            activity.end_time = end_time

        if activity_name is not None:
            activity.activity_name = activity_name

        if address is not None:
            activity.address = address

        if content is not None:
            activity.content = content

        if image_rul is not None:
            activity.image_rul = image_rul

        if manager_phone is not None:
            activity.manager_phone = manager_phone

        if sign_in is not None:
            activity.sign_in = sign_in 

        db.session.commit()

    @marshal_with(Activity.resource_fields, envelope='resource')
    def search(self, id):
        activity = Activity.query.get(id)
        return activity
