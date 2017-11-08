#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class Activity_apply(db.Model):
    __tablename__ = 'activity_apply'
    activity_apply_id = db.Column(db.Integer, primary_key=True, nullable=False)
    activity_id = db.Column(db.Integer)
    apply_time = db.Column(db.Integer)
    sign_in_time = db.Column(db.Integer)
    state = db.Column(db.Integer)
    user_id = db.Column(db.Integer)
    user_name = db.Column(db.String(255))

    resource_fields = {
        'activity_apply_id': fields.Integer,
        'activity_id': fields.Integer,
        'apply_time': fields.Integer,
        'sign_in_time': fields.Integer,
        'state': fields.Integer,
        'user_id': fields.Integer,
        'user_name': fields.String
    }

    @classmethod
    def add(self, activity_apply_id, activity_id, apply_time, sign_in_time, state, user_id, user_name):
        new_activity_apply = Activity(activity_apply_id=activity_apply_id, activity_id=activity_id,
                                      apply_time=apply_time, sign_in_time=sign_in_time, 
                                      state=state, user_id=user_id, user_name=user_name)
        db.session.add(new_activity_apply)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, activity_apply_id):
        activity_apply = Activity_apply.query.filter_by(activity_apply_id=activity_apply_id).first()
        if activity_apply is None:
            return 'Activity_apply not found', 401
        db.session.delete(activity_apply)
        db.session.commit()

    @classmethod
    def alter(self, activity_apply_id=None, activity_id=None, apply_time=None, sign_in_time=None,
              state=None, user_id=None, user_name=None):
        activity_apply = Activity_apply.query.filter_by(activity_apply_id=activity_apply_id).first()
        if activity_apply is None:
            return 'Activity_apply not found', 401

        if activity_id is not None:
            activity_apply.activity_id = activity_id

        if apply_time is not None:
            activity_apply.apply_time = apply_time

        if sign_in_time is not None:
            activity_apply.sign_in_time = sign_in_time

        if state is not None:
            activity_apply.state = state

        if user_id is not None:
            activity_apply.user_id = user_id

        if user_name is not None:
            activity_apply.user_name = user_name

        db.session.commit()

    @marshal_with(Activity_apply.resource_fields, envelope='resource')
    def search(self, id):
        activity_apply = Activity_apply.query.get(id)
        return activity_apply
