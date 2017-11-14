from flask_restful import Resource, marshal_with, fields, reqparse
from . import format_response_with,success,failure,api
from models.models import DepManager

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
    
    @format_response_with(depMgr_profile_fields)
    def get(self):
        #TODO: validate user login status, and return the profile of department manager
        return failure(-1,'TODO LATER')
        # return DepManager.query.filter_by(dep_manager_account=depMgrname).first()
    
    @format_response_with(depMgr_profile_fields)
    def post(self):
        args = parser.parse_args()
        dep_mgr = DepManager.query.filter_by(dep_manager_account=args.depMgrname,password=args.password,role=DepManager.role_supervisor).first()
        if dep_mgr is None:
            return 'incorrect depMgrname or password',401
        return dep_mgr

api.add_resource(DepMgrResource,'/manager/signin',methods=['POST'])
# api.add_resource(DepMgrResource,'/ manager/profile',methods=['GET'])