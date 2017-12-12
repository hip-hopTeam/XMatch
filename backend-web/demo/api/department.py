from flask_restful import Resource, marshal_with, fields, reqparse, request
from . import format_response_with, success, failure, unauthorized, api, desc, db
from models.models import Department, ChildDepartment
from utils import *


department_fields = {
    'department_id': fields.Integer,
    'activity_num': fields.Integer,
    'child_dep_num': fields.Integer,
    'creat_time': fields.Integer,
    'dep_manager_id': fields.Integer,
    'dep_name': fields.String,
    'dep_summary': fields.String,
    'image_url': fields.String,
    'member_num': fields.Integer,
    'emergency_phone': fields.String,
    'state': fields.Integer,
}

global_fields = {
    "code": fields.Integer,
    "result": fields.String,
    "object": fields.List(fields.Raw),
}

child_department_fields = {
    'child_department_id': fields.Integer,
    'department_id': fields.Integer,
    'email': fields.String,
    'name': fields.String,
    'phone': fields.String,
}

class DepartmentDetails(Resource):
    @format_response_with(department_fields)
    def get(self, department_id=None):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        if department_id == None:
            return failure(-1,'department_id is required')
        department = Department.query.filter_by(department_id=department_id,state=Department.audit_approved).first()
        if department == None:
            return failure(-1,'the department does not exist')
        return success(department)

class DepartmentResource(Resource):
    @format_response_with(department_fields)
    def get(self):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        try:
            page = int(request.args.get("page") or 1)
        except ValueError:
            page = 1
        if page <= 0:
            failure(-1,'page number should be at least 1')
        pagination = Department.query.filter_by(state=Department.audit_approved).order_by(Department.department_id).paginate(page, per_page=10, error_out=True)
        return success(pagination.items)

class ChildDepartmentResource(Resource):
    @format_response_with(child_department_fields)
    def get(self, department_id=None):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        if department_id == None:
            failure(-1,'department_id is required')
        child_department = ChildDepartment.query.filter_by(department_id=department_id,state=Department.audit_approved).all()
        return success(child_department)

class AuditDepartment(Resource):
    @format_response_with(department_fields)
    def get(self):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        try:
            page = int(request.args.get("page") or 1)
        except ValueError:
            page = 1
        if page <= 0:
            return failure(-1,'page number should be at least 1')
        pagination = Department.query.filter_by(state=Department.audit_processing).order_by(Department.department_id).paginate(page, per_page=10, error_out=True)
        return success(pagination.items)

    @format_response_with(department_fields)
    def post(self):
        # status: if the user is a supervisor, status is True
        status = isLoggedIn()
        if not status:
            return unauthorized()

        parser = reqparse.RequestParser()
        parser.add_argument('department_id', type=int, required=True)
        parser.add_argument('state',choices=(str(Department.audit_approved), str(Department.audit_rejected)), required=True)
        args = parser.parse_args()
        department = Department.query.filter_by(department_id=args.department_id, state=Department.audit_processing).first()
        if department is None:
            return failure(-1,'there is no such department')
        department.state = int(args.state)
        db.session.add(department)
        db.session.commit()
        return success(department)

api.add_resource(DepartmentResource, '/department',methods=['GET'])
api.add_resource(DepartmentDetails, '/department/<int:department_id>',methods=['GET'])
api.add_resource(ChildDepartmentResource, '/department/<int:department_id>/child',methods=['GET'])
api.add_resource(AuditDepartment, '/department/audit',methods=['GET','POST'])
