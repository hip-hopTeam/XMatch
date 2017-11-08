#!/usr/bin/env python
from flask import Flask ,jsonify,g
from flask_restful import Api, Resource, request, reqparse, marshal_with, fields
from flask_sqlalchemy import SQLAlchemy
from demo.database import db, app, api

class App_notice(db.Model):
    __tablename__ = 'app_notice'
    app_notice_id = db.Column(db.Integer, primary_key=True, nullable=False)
    content = db.Column(db.TEXT)
    create_time = db.Column(db.Integer)
    department_id = db.Column(db.Integer)
    title = db.Column(db.String(255))
    notice_type = db.Column(db.Integer)

    resource_fields = {
        'app_notice_id': fields.Integer,
        'content': fields.TEXT,
        'create_time': fields.Integer,
        'department_id': fields.Integer,
        'title': fields.String,
        'notice_type': fields.Integer
    }

    @classmethod
    def add(self, app_notice_id, content, create_time, department_id, title, notice_type):
        new_app_notice = App_notice(app_notice_id=app_notice_id, content=content,
                                      create_time=create_time, department_id=department_id, 
                                      title=title, notice_type=notice_type)
        db.session.add(new_app_notice)
        db.session.commit()
        return 'Successfully', 200

    @classmethod
    def delete(self, app_notice_id):
        app_notice = App_notice.query.filter_by(app_notice_id=app_notice_id).first()
        if app_notice is None:
            return 'App_notice not found', 401
        db.session.delete(app_notice)
        db.session.commit()

    @classmethod
    def alter(self, app_notice_id=None, content=None, create_time=None, 
              department_id=None, title=None, notice_type=None):
        app_notice = App_notice.query.filter_by(app_notice_id=app_notice_id).first()
        if app_notice is None:
            return 'App_notice not found', 401

        if content is not None:
            app_notice.content = content

        if create_time is not None:
            app_notice.create_time = create_time

        if department_id is not None:
            app_notice.department_id = department_id

        if title is not None:
            app_notice.title = title

        if notice_type is not None:
            app_notice.notice_type = notice_type

        db.session.commit()

    @marshal_with(App_notice.resource_fields, envelope='resource')
    def search(self, id):
        app_notice = App_notice.query.get(id)
        return app_notice
