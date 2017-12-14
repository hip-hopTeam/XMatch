from flask_restful import Resource, marshal_with, fields, reqparse
from . import format_response_with,success,unauthorized,failure,api
from models.models import DepManager
from utils import *


parser = reqparse.RequestParser()
parser.add_argument('depMgrname',type=str)
parser.add_argument('password',type=str)

depMgr_profile_fields = {
    'dep_manager_id': fields.Integer,
    'department_id': fields.Integer,
    'dep_manager_account': fields.String,
    'email': fields.String,
    'phone_num': fields.String,
    'manager_name': fields.String,
    'manager_summary': fields.String,
    'role': fields.Integer,
}

class DepMgrResource(Resource):
    @format_response_with({})
    def get(self):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        #TODO: validate user login status, and return the profile of department manager
        return failure(-1,'TODO LATER')
        # return DepManager.query.filter_by(dep_manager_account=depMgrname).first()

# api.add_resource(DepMgrResource,'/manager/signin',methods=['POST'])
api.add_resource(DepMgrResource,'/manager/profile',methods=['GET'])
