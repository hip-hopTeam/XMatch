#!/usr/bin/env python 
from flask import session
from flask_restful import Resource, marshal_with, fields, reqparse, request
from . import format_response_with, success, failure, unauthorized, api, desc, db
from models.models import DepManager

global_fields = {
    "code": fields.Integer,
    "result": fields.String,
    "object": fields.List(fields.Raw),
}

class Login(Resource):
    @format_response_with({})
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('username', type=str, required=True)
        parser.add_argument('password', type=str, required=True)
        args = parser.parse_args()
        supervisor = DepManager.query.filter_by(manager_name=args.username, role=DepManager.role_supervisor, password=args.password).first()
        if supervisor is None:
            return failure(-1,'incorrect username or password')
        else:
            session['logged_in'] = "True"
            return success(None)

class Logout(Resource):
    @format_response_with({})
    def post(self):
        if not isLoggedIn():
            return unauthorized()
        session.pop('logged_in', None) 
        return success(None)

api.add_resource(Login, '/manager/login', methods=['POST','GET'])
api.add_resource(Logout,'/manager/logout',methods=['POST'])